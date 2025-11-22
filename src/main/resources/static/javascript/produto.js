document.addEventListener("DOMContentLoaded", async () => {
  const container = document.getElementById("product-container");

  const id = window.location.pathname.split("/").pop();


  try {
    const response = await fetch(`/api/discos/${id}`);
    const disco = await response.json();

    container.innerHTML = `
      <div class="product-card hor" data-id="${disco.id}">
        <img class="pthumb" src="${disco.capa}" alt="${disco.nome}">

        <div class="product-info">
          <div class="ptitle">${disco.nome}</div>
          <div class="partist">${disco.artista}</div>
          <div class="pprice">R$ ${disco.preco.toFixed(2)}</div>

          <div class="pdesc">${disco.descricao}</div>

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

    // REBIND BOTÃO
    container.querySelectorAll(".btn-add").forEach(btn => {
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
