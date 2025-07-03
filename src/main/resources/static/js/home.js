document.addEventListener('DOMContentLoaded', () => {
    // ⭐ Efecto elegante para todos los botones con clase .btn
    document.querySelectorAll('.btn').forEach(button => {
        button.addEventListener('mouseenter', () => {
            button.style.transition = 'transform 0.25s ease, box-shadow 0.3s ease';
            button.style.transform = 'scale(1.08)';
            button.style.boxShadow = '0 8px 20px rgba(0, 114, 255, 0.4)';
        });

        button.addEventListener('mouseleave', () => {
            button.style.transform = 'scale(1)';
            button.style.boxShadow = '0 6px 15px rgba(0, 114, 255, 0.3)';
        });
    });

    // 👁️ Agrega clase visible con IntersectionObserver para animaciones suaves
    const seccion = document.querySelector('.bienvenida, .login-wrapper, .registro-container');
    if (seccion) {
        const observer = new IntersectionObserver((entries, observer) => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    entry.target.classList.add('visible');
                    observer.unobserve(entry.target);
                }
            });
        }, { threshold: 0.4 });

        observer.observe(seccion);
    }
});
