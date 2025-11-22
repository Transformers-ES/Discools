document.addEventListener("DOMContentLoaded", async () => {
  const container = document.getElementById("product-container");

  // 1. PEGAR O ID DA URL
  const id = window.location.pathname.split("/").pop();

  try {
    // 2. BUSCAR PRODUTO NA API
    const response = await fetch(`/api/discos/${id}`);
    const disco = await response.json();

    // 3. MONTAR HTML DO PRODUTO
    container.innerHTML = `
      <div class="product-card hor" data-id="${disco.id}">
        
          <img class="pthumb" src="${disco.capa}" alt="${disco.nome}">
        

        <div class="product-info">
          <div class="ptitle">${disco.nome}</div>
          <div class="partist">${disco.artista}</div>
          <div class="pprice">R$ ${disco.preco.toFixed(2)}</div>

          <div class="pdesc">
            ${disco.descricao}
          </div>

          <button class="btn-add" data-prod='${JSON.stringify({
            id: disco.id,
            title: disco.nome,
            price: disco.preco
          })}'>
            Comprar
          </button>
        </div>
      </div>
    `;


    /* ---------- 4. REBIND DOS BOTÕES "ADICIONAR" ---------- */
    gridElement.querySelectorAll(".btn-add").forEach(btn => {
      btn.addEventListener("click", e => {
        const prod = JSON.parse(e.currentTarget.dataset.prod);
        addToCart(prod);
        openCart();
      });
    });
    
  } catch (err) {
    container.innerHTML = "<p>Erro ao carregar produto.</p>";
    console.error(err);
  }
});
