document.addEventListener('DOMContentLoaded', () => {
  const sidebar   = document.querySelector('.sidebar');
  const toggleBtn = document.getElementById('toggleSidebar') || document.querySelector('.sidebar-toggle');
  const body      = document.body;
  const links     = document.querySelectorAll('.sidebar a');

  // 1️⃣ Resaltar enlace activo
  const currentUrl = window.location.href.split(/[?#]/)[0];
  links.forEach(link => {
    if (link.href === currentUrl) {
      link.classList.add('active');
      link.setAttribute('aria-current', 'page');
    }

    // 2️⃣ Cerrar menú en móvil al hacer clic en un link
    link.addEventListener('click', () => {
      if (window.innerWidth <= 768) {
        sidebar?.classList.remove('active');
        body.classList.remove('sidebar-open');
        toggleBtn?.setAttribute('aria-expanded', false);
      }
    });
  });

  // 3️⃣ Mostrar/ocultar menú
  if (toggleBtn && sidebar) {
    toggleBtn.addEventListener('click', () => {
      const isActive = sidebar.classList.toggle('active');
      body.classList.toggle('sidebar-open', isActive);
      toggleBtn.setAttribute('aria-expanded', isActive);
    });
  }

  // 4️⃣ Cerrar menú al hacer clic fuera
  document.addEventListener('click', (e) => {
    if (
      body.classList.contains('sidebar-open') &&
      !sidebar.contains(e.target) &&
      !(toggleBtn && toggleBtn.contains(e.target))
    ) {
      sidebar.classList.remove('active');
      body.classList.remove('sidebar-open');
      toggleBtn?.setAttribute('aria-expanded', false);
    }
  });

  // 5️⃣ Accesibilidad
  if (toggleBtn) {
    toggleBtn.setAttribute('aria-label', 'Alternar menú lateral');
    toggleBtn.setAttribute('aria-expanded', false);
  }
});
