/* global Swal */

document.addEventListener('DOMContentLoaded', () => {
    // Animación inicial
    const card = document.querySelector('.login-card');
    if (card) setTimeout(() => card.classList.add('visible'), 100);

    // Estilos al enfocar inputs
    document.querySelectorAll('.login-card input').forEach(input => {
        input.addEventListener('focus', () => input.style.borderColor = '#1e90ff');
        input.addEventListener('blur', () => input.style.borderColor = 'rgba(255, 255, 255, 0.2)');
    });

    // Deshabilitar botón al enviar
    const form = document.querySelector('.login-card form');
    const btn = document.querySelector('.btn-login');
    if (form && btn) {
        form.addEventListener('submit', () => {
            btn.textContent = "Entrando...";
            btn.disabled = true;
            btn.style.opacity = 0.6;
        });
    }

    // Mostrar alertas desde la URL
    const params = new URLSearchParams(window.location.search);
    if (params.has('error')) mostrarAlerta("Credenciales inválidas. Intenta nuevamente.", 'error');
    if (params.has('logout')) mostrarAlerta("Has cerrado sesión correctamente.", 'success');
    if (params.has('registroExitoso')) mostrarAlerta("Tu cuenta fue creada exitosamente.", 'success');
    if (params.has('recuperarExitoso')) mostrarAlerta("📧 Se envió el enlace de recuperación a tu correo.", 'success');
    if (params.has('errorRecuperar')) mostrarAlerta("❌ No se encontró una cuenta con ese correo.", 'error');

    const btnRecuperar = document.getElementById('btnRecuperar');
    if (btnRecuperar) {
        btnRecuperar.addEventListener('click', () => {
            Swal.fire({
                title: '🔑 Recuperar contraseña',
                html: `<input type="email" id="recuperarCorreo" class="swal2-input" placeholder="Ingresa tu correo">`,
                confirmButtonText: 'Enviar enlace',
                showCancelButton: true,
                cancelButtonText: 'Cancelar',
                focusConfirm: false,
                preConfirm: () => {
                    const correo = document.getElementById('recuperarCorreo').value.trim();
                    if (!correo || !validarCorreo(correo)) {
                        Swal.showValidationMessage('Por favor ingresa un correo válido.');
                        return false;
                    }

                    return fetch('/auth/recuperar', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        },
                        body: new URLSearchParams({ correo })
                    })
                        .then(res => res.text())
                        .then(text => {
                            if (text === 'OK') {
                                window.location.href = '/auth/login?recuperarExitoso=true';
                            } else {
                                window.location.href = '/auth/login?errorRecuperar=true';
                            }
                        })
                        .catch(error => {
                            console.error('Error al enviar el formulario:', error);
                            Swal.showValidationMessage('Ocurrió un error. Intenta nuevamente.');
                        });
                }
            });
        });
    }
});

// 🔐 Mostrar u ocultar contraseña
function togglePassword() {
    const input = document.getElementById('password-input');
    input.type = input.type === "password" ? "text" : "password";
}

// 🚨 Alerta tipo SweetAlert2
function mostrarAlerta(mensaje, tipo) {
    Swal.fire({
        toast: true,
        position: 'top-end',
        icon: tipo,
        title: mensaje,
        showConfirmButton: false,
        timer: 4000,
        timerProgressBar: true,
        background: '#333',
        color: '#fff',
        didOpen: toast => {
            toast.addEventListener('mouseenter', Swal.stopTimer);
            toast.addEventListener('mouseleave', Swal.resumeTimer);
        }
    });
}

function validarCorreo(correo) {
    const regex = /^[^@\s]+@[^@\s]+\.[^@\s]+$/;
    return regex.test(correo);
}
