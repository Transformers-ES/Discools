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
const cartSidebar = document.getElementById('cartSidebar');
const mainLayout = document.getElementById('mainLayout');
const cartToggle = document.getElementById('cartToggle');

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

document.getElementById('closeCart').addEventListener('click', closeCart);
cartToggle.addEventListener('click', ()=> {
  if(cartSidebar.classList.contains('open')) closeCart();
  else openCart();
});

/* render items */
function renderCart(){
  const container = document.getElementById('cartItems');
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

/* botão comprar -> redireciona (página ainda não criada) */
document.getElementById('checkoutBtn').addEventListener('click', ()=>{
  // por enquanto redireciona para /checkout (crie essa rota no seu Spring)
  window.location.href = '/checkout';
});

/* vincular botões "Adicionar" nas cards */
document.querySelectorAll('.btn-add').forEach(btn=>{
  btn.addEventListener('click', e=>{
    const prod = JSON.parse(e.currentTarget.dataset.prod);
    addToCart(prod);
    openCart();
  });
});

/* carregar carrinho ao iniciar */
renderCart();

/* ---------- INTEGRAÇÃO REST (spring) - como puxar produtos dinamicamente ---------- */
/*
  Sugestão:
  - No Spring crie um endpoint GET /api/products que retorne JSON:
      [
        { "id": 101, "title":"DISCO 1", "artist":"Nome", "price":100, "imageUrl":"/img/101.jpg", "categoryId":1 },
        ...
      ]
  - No JS você faz:
      fetch('/api/products')
        .then(r=>r.json())
        .then(list => renderProducts(list));

  Implementação de renderProducts (exemplo abaixo) insere os cards nas divs #cat-1, #cat-2 etc.
*/

function renderProducts(list){
  // limpa todas as categorias (melhor mapear dinamicamente)
  const cats = {};
  list.forEach(p=> {
    const container = cats[p.categoryId] || (cats[p.categoryId] = []);
    container.push(p);
  });

  Object.keys(cats).forEach(catId=>{
    const grid = document.getElementById('cat-'+catId);
    if(!grid) return;
    grid.innerHTML = '';
    cats[catId].forEach(p=>{
      const card = document.createElement('div');
      card.className = 'product-card';
      card.innerHTML = `
        <a class="product-link" href="/product/${p.id}">
          <div class="product-thumb" style="background-image:url('${p.imageUrl||''}');background-size:cover;background-position:center"></div>
          <div class="product-meta">
            <div class="product-title">${p.title}</div>
            <div class="product-artist">${p.artist||''}</div>
            <div class="product-price">R$ ${Number(p.price).toFixed(2)}</div>
          </div>
        </a>
        <button class="btn-add" data-prod='${JSON.stringify({id:p.id,title:p.title,price:p.price})}'>Adicionar</button>
      `;
      grid.appendChild(card);
    });
  });

  // re-bind events para os novos botões:
  document.querySelectorAll('.btn-add').forEach(btn=>{
    btn.removeEventListener && btn.removeEventListener('click', ()=>{});
    btn.addEventListener('click', e=>{
      const prod = JSON.parse(e.currentTarget.dataset.prod);
      addToCart(prod);
      openCart();
    });
  });
}

/* Exemplo de uso (descomente quando tiver endpoint):
fetch('/api/products')
  .then(r=>r.json())
  .then(data => renderProducts(data));
*/

/* Troca de tela de processo para sucesso na Tela de Pagamento */
document.getElementById("btnFazerPedido").onclick = () => {
  const main = document.getElementById("mainLayout");

  main.classList.remove("ativo-pagamento");
  main.classList.add("ativo-sucesso");
};