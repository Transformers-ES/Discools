/* =====================================================
   CARRINHO GLOBAL — Funciona em qualquer página
   ===================================================== */

const CART_KEY = 'discools_cart_v1';

/* ---------- Helpers ---------- */
function loadCart() {
  const raw = localStorage.getItem(CART_KEY);
  return raw ? JSON.parse(raw) : [];
}

function saveCart(cart) {
  localStorage.setItem(CART_KEY, JSON.stringify(cart));
}

function addToCart(item) {
  const cart = loadCart();
  const found = cart.find(i => i.id === item.id);

  if (found) {
    found.qty = (found.qty || 1) + 1;
  } else {
    cart.push({ ...item, qty: 1 });
  }

  saveCart(cart);
  renderCart();
}

/* =====================================================
   RENDERIZAÇÃO DO CARRINHO
   ===================================================== */

let cartSidebar, mainLayout, cartToggle;

function openCart() {
  if (!cartSidebar || !mainLayout) return;

  cartSidebar.classList.add('open');
  mainLayout.classList.add('shifted');
  cartSidebar.setAttribute('aria-hidden', 'false');
}

function closeCart() {
  if (!cartSidebar || !mainLayout) return;

  cartSidebar.classList.remove('open');
  mainLayout.classList.remove('shifted');
  cartSidebar.setAttribute('aria-hidden', 'true');
}

function renderCart() {
  const container = document.getElementById('cartItems');
  if (!container) return; // Página sem carrinho? Ignora.

  const cart = loadCart();
  container.innerHTML = '';

  if (cart.length === 0) {
    container.innerHTML = '<p class="empty-msg">Nenhum produto no carrinho.</p>';
    return;
  }

  cart.forEach(item => {
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

  // Eventos
  container.querySelectorAll('.btn-inc').forEach(btn =>
    btn.addEventListener('click', e => {
      const id = Number(e.target.dataset.id);
      const cart = loadCart();
      const it = cart.find(i => i.id === id);
      if (it) {
        it.qty++;
        saveCart(cart);
        renderCart();
      }
    })
  );

  container.querySelectorAll('.btn-dec').forEach(btn =>
    btn.addEventListener('click', e => {
      const id = Number(e.target.dataset.id);
      let cart = loadCart();
      const it = cart.find(i => i.id === id);

      if (!it) return;

      it.qty--;
      if (it.qty <= 0) {
        cart = cart.filter(i => i.id !== id);
      }

      saveCart(cart);
      renderCart();
    })
  );
}

/* =====================================================
   INICIALIZAÇÃO GLOBAL — SEM ERROS EM NENHUMA PÁGINA
   ===================================================== */

document.addEventListener("DOMContentLoaded", () => {

  /* ----- Seleciona elementos do carrinho (se existirem) ----- */
  cartSidebar = document.getElementById('cartSidebar');
  mainLayout  = document.getElementById('mainLayout');
  cartToggle  = document.getElementById('cartToggle');
  const closeCartBtn = document.getElementById('closeCart');

  if (cartSidebar && mainLayout && cartToggle && closeCartBtn) {

    closeCartBtn.addEventListener('click', closeCart);

    cartToggle.addEventListener('click', () => {
      cartSidebar.classList.contains('open') ? closeCart() : openCart();
    });
  }

  /* ----- Botão checkout (nem todas páginas têm) ----- */
  const checkoutButton = document.getElementById('checkoutBtn');
  if (checkoutButton) {
    checkoutButton.addEventListener('click', () => {
      saveCart([]);              // limpa o carrinho
      renderCart();              // atualiza visualmente (se estiver na home)
      window.location.href = '/pagamento';  // redireciona
    });
  }

  /* ----- Botões estáticos .btn-add (se existirem) ----- */
  document.querySelectorAll('.btn-add').forEach(btn => {
    btn.addEventListener('click', e => {
      const prod = JSON.parse(e.currentTarget.dataset.prod);
      addToCart(prod);
      openCart();
    });
  });

  /* ----- Renderiza carrinho inicialmente ----- */
  renderCart();
});
