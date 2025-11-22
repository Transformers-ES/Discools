/* Comportamento do carrinho, botão de adicionar, e integração mínima com backend REST */

/* ---------- helpers de carrinho (usa localStorage) ---------- */
const CART_KEY = 'discools_cart_v1';

function loadCart(){
  const raw = localStorage.getItem(CART_KEY);
  return raw ? JSON.parse(raw) : [];
}
function saveCart(cart){
  localStorage.setItem(CART_KEY, JSON.stringify(cart));
}
function addToCart(item){
  const cart = loadCart();
  const found = cart.find(i=>i.id===item.id);
  if(found) found.qty = (found.qty||1)+1;
  else cart.push(Object.assign({qty:1}, item));
  saveCart(cart);
  renderCart();
}

/* ---------- render carrinho ---------- */
let cartSidebar, mainLayout, cartToggle;

function openCart(){
  cartSidebar.classList.add('open');
  mainLayout.classList.add('shifted');
  cartSidebar.setAttribute('aria-hidden','false');
}
function closeCart(){
  cartSidebar.classList.remove('open');
  mainLayout.classList.remove('shifted');
  cartSidebar.setAttribute('aria-hidden','true');
}

/* render items */
function renderCart(){
  const container = document.getElementById('cartItems');
  if (!container) return;
  const cart = loadCart();
  container.innerHTML = '';
  if(cart.length===0){
    container.innerHTML = '<p class="empty-msg">Nenhum produto no carrinho.</p>';
    return;
  }
  cart.forEach(item=>{
    const el = document.createElement('div');
    el.className = 'cart-item';
    el.style.padding = '12px';
    el.style.borderBottom = '1px solid rgba(0,0,0,0.06)';
    el.innerHTML = `
      <div style="display:flex;justify-content:space-between;align-items:center">
        <div>
          <div style="font-weight:800">${item.title}</div>
          <div style="font-size:13px;color:#2b1b3c">R$ ${item.price.toFixed(2)} x ${item.qty}</div>
        </div>
        <div>
          <button class="btn-dec" data-id="${item.id}">-</button>
          <button class="btn-inc" data-id="${item.id}">+</button>
        </div>
      </div>
    `;
    container.appendChild(el);
  });

  // eventos simples de +/-:
  container.querySelectorAll('.btn-inc').forEach(b=>{
    b.addEventListener('click', e=>{
      const id = Number(e.target.dataset.id);
      const cart = loadCart();
      const it = cart.find(i=>i.id===id);
      if(it){ it.qty = (it.qty||1)+1; saveCart(cart); renderCart(); }
    });
  });
  container.querySelectorAll('.btn-dec').forEach(b=>{
    b.addEventListener('click', e=>{
      const id = Number(e.target.dataset.id);
      let cart = loadCart();
      const it = cart.find(i=>i.id===id);
      if(!it) return;
      it.qty = (it.qty||1)-1;
      if(it.qty<=0) cart = cart.filter(x=>x.id!==id);
      saveCart(cart);
      renderCart();
    });
  });
}

/* Inicialização quando o DOM estiver pronto */
document.addEventListener("DOMContentLoaded", () => {
  // Inicializar variáveis do DOM
  cartSidebar = document.getElementById('cartSidebar');
  mainLayout = document.getElementById('mainLayout');
  cartToggle = document.getElementById('cartToggle');
  
  // Configurar eventos do carrinho
  document.getElementById('closeCart').addEventListener('click', closeCart);
  cartToggle.addEventListener('click', ()=> {
    if(cartSidebar.classList.contains('open')) closeCart();
    else openCart();
  });
  
  /* botão comprar */
  document.getElementById('checkoutBtn').addEventListener('click', ()=>{
    window.location.href = '/checkout';
  });
  
  /* vincular botões "Adicionar" nas cards estáticas (se houver) */
  document.querySelectorAll('.btn-add').forEach(btn=>{
    btn.addEventListener('click', e=>{
      const prod = JSON.parse(e.currentTarget.dataset.prod);
      addToCart(prod);
      openCart();
    });
  });
  
  /* carregar carrinho ao iniciar */
  renderCart();
});




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
        <a href="/product/${disco.id}" class="product-link">
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
