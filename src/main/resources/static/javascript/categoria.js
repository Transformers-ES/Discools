/* ============================================
   INTEGRAÇÃO: CARREGAR CATEGORIAS DINAMICAMENTE
   ============================================ */

document.addEventListener("DOMContentLoaded", () => {
  const categories = document.querySelectorAll("article.category");

  categories.forEach(article => {
    const categoriaId = article.dataset.categoryId;
    const titleEl = article.querySelector(".category-title");
    const gridEl  = article.querySelector(".product-grid");

    carregarCategoria(categoriaId, titleEl, gridEl);
  });
});

/**
 * Carrega uma categoria do Spring:
 * GET /api/categorias/{id}
 */
async function carregarCategoria(id, titleElement, gridElement) {
  try {
    const response = await fetch(`/api/categorias/${id}`);

    if (!response.ok) {
      console.error("Erro ao carregar categoria ID:", id);
      return;
    }

    const categoria = await response.json();

    /* ---------- 1. TÍTULO DA CATEGORIA ---------- */
    titleElement.textContent = categoria.nome;

    /* ---------- 2. LIMPA O GRID ---------- */
    gridElement.innerHTML = "";

    /* ---------- 3. INSERE OS PRODUTOS ---------- */
    categoria.discosCd.forEach(disco => {
      const card = document.createElement("div");
      card.className = "product-card";
      card.dataset.id = disco.id;

      card.innerHTML = `
        <a href="produto/${disco.id}" class="product-link">
          <div class="product-thumb">
            <img src="${disco.capa}" alt="${disco.nome}">
          </div>
          <div class="product-meta">
            <div class="product-title">${disco.nome}</div>
            <div class="product-artist">${disco.artista}</div>
            <div class="product-price">R$ ${disco.preco.toFixed(2)}</div>
          </div>
        </a>
        
        <button class="btn-add" data-prod='${JSON.stringify({
          id: disco.id,
          title: disco.nome,
          price: disco.preco
        })}'>
          Adicionar
        </button>
      `;

      gridElement.appendChild(card);
    });

    /* ---------- 4. REBIND DOS BOTÕES "ADICIONAR" ---------- */
    gridElement.querySelectorAll(".btn-add").forEach(btn => {
      btn.addEventListener("click", e => {
        const prod = JSON.parse(e.currentTarget.dataset.prod);
        addToCart(prod);
        openCart();
      });
    });

  } catch (error) {
    console.error("Erro inesperado ao carregar categoria:", error);
  }
}
