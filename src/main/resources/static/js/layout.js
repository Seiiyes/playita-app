document.addEventListener("DOMContentLoaded", () => {
    // ==============================
    // Efecto Hover en Links de Navegación
    // ==============================
    const navLinks = document.querySelectorAll("header nav a");

    navLinks.forEach(link => {
        link.style.transition = "transform 0.3s ease, box-shadow 0.3s ease";

        link.addEventListener("mouseenter", () => {
            link.style.transform = "scale(1.08)";
            link.style.boxShadow = "0 0 10px rgba(0, 255, 255, 0.8)";
        });

        link.addEventListener("mouseleave", () => {
            link.style.transform = "scale(1)";
            link.style.boxShadow = "none";
        });
    });

    // ==============================
    // Botón Scroll to Top
    // ==============================
    const scrollBtn = document.createElement("button");
    scrollBtn.innerHTML = "↑";
    scrollBtn.classList.add("scroll-to-top");

    // Estilos del botón
    Object.assign(scrollBtn.style, {
        position: "fixed",
        bottom: "30px",
        right: "30px",
        width: "50px",
        height: "50px",
        borderRadius: "50%",
        fontSize: "1.5rem",
        fontWeight: "bold",
        background: "rgba(0, 255, 255, 0.8)",
        color: "#000",
        border: "none",
        cursor: "pointer",
        zIndex: "999",
        display: "none",
        transition: "background 0.3s ease, opacity 0.3s ease"
    });

    scrollBtn.title = "Volver arriba";

    // Hover botón
    scrollBtn.addEventListener("mouseenter", () => {
        scrollBtn.style.background = "#ffd700";
    });

    scrollBtn.addEventListener("mouseleave", () => {
        scrollBtn.style.background = "rgba(0, 255, 255, 0.8)";
    });

    scrollBtn.addEventListener("click", () => {
        window.scrollTo({ top: 0, behavior: "smooth" });
    });

    document.body.appendChild(scrollBtn);

    // Mostrar/ocultar botón según scroll
    window.addEventListener("scroll", () => {
        scrollBtn.style.display = window.scrollY > 300 ? "block" : "none";
    });

    // ==============================
    // Animación de Entrada al <main>
    // ==============================
    const main = document.querySelector("main");

    if (main) {
        main.style.opacity = "0";
        main.style.transform = "translateY(20px)";
        main.style.transition = "opacity 0.6s ease, transform 0.6s ease";

        setTimeout(() => {
            main.style.opacity = "1";
            main.style.transform = "translateY(0)";
        }, 100);
    }
});
