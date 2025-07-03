document.addEventListener('DOMContentLoaded', () => {
  const content = document.querySelector('.dashboard-content');

  // Animación de entrada al cargar
  if (content) {
    content.style.opacity = '0';
    content.style.transform = 'translateY(30px)';
    content.style.transition = 'opacity 0.6s ease, transform 0.6s ease';

    requestAnimationFrame(() => {
      setTimeout(() => {
        content.classList.add('visible');
        content.style.opacity = '1';
        content.style.transform = 'translateY(0)';
      }, 100);
    });
  }

  // Tarjetas interactivas
  const cards = document.querySelectorAll('.card-link');
  cards.forEach(card => {
    card.addEventListener('mouseenter', () => {
      card.classList.add('hovered');
    });

    card.addEventListener('mouseleave', () => {
      card.classList.remove('hovered');
    });

    // Accesibilidad: foco por teclado
    card.addEventListener('focus', () => {
      card.classList.add('hovered');
    });

    card.addEventListener('blur', () => {
      card.classList.remove('hovered');
    });
  });
});
