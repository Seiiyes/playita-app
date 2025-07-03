/* global Swal */

document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('.registro-container form');
    const inputs = form.querySelectorAll('input[required]');
    const button = form.querySelector('button[type="submit"]');
    const password = document.getElementById('password');
    const confirmPassword = document.getElementById('confirmPassword');
    const passwordError = document.getElementById('password-error');
    const correo = document.getElementById('correo');
    const correoError = document.getElementById('correo-error');
    const telefono = document.getElementById('telefono');

    // Animación
    document.querySelector('.registro-container')?.classList.add('visible');

    // Estilo focus
    inputs.forEach(input => {
        input.addEventListener('focus', () => input.classList.add('focused'));
        input.addEventListener('blur', () => input.classList.remove('focused'));
    });

    // Validar correo
    correo.addEventListener('input', () => {
        const email = correo.value.trim();
        correo.classList.remove('valid', 'invalid');
        correoError.textContent = '';

        const regexEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (regexEmail.test(email)) {
            correo.classList.add('valid');
        } else {
            correo.classList.add('invalid');
            correoError.textContent = 'Formato de correo inválido.';
        }
    });

    // Validar teléfono
    telefono.addEventListener('input', () => {
        const clean = telefono.value.replace(/\D/g, '');
        telefono.value = clean;
        telefono.classList.remove('valid', 'invalid');

        if (clean.length >= 7 && clean.length <= 12) {
            telefono.classList.add('valid');
        } else {
            telefono.classList.add('invalid');
        }
    });

    // Validar contraseñas
    function validarPasswords() {
        passwordError.style.display = 'none';
        password.classList.remove('error');
        confirmPassword.classList.remove('error');

        if (password.value.length < 6) {
            passwordError.textContent = 'La contraseña debe tener al menos 6 caracteres.';
            passwordError.style.display = 'block';
            password.classList.add('error');
            return false;
        }

        if (password.value !== confirmPassword.value) {
            passwordError.textContent = 'Las contraseñas no coinciden.';
            passwordError.style.display = 'block';
            confirmPassword.classList.add('error');
            return false;
        }

        return true;
    }

    password.addEventListener('input', validarPasswords);
    confirmPassword.addEventListener('input', validarPasswords);

    // Validación al enviar
    form.addEventListener('submit', (e) => {
        let valido = true;

        inputs.forEach(input => {
            const errorMsg = input.nextElementSibling;
            if (input.value.trim() === '') {
                valido = false;
                input.classList.add('error', 'shake');
                setTimeout(() => input.classList.remove('shake'), 500);
                if (!errorMsg || !errorMsg.classList.contains('input-error')) {
                    const div = document.createElement('div');
                    div.className = 'input-error';
                    div.textContent = 'Este campo es obligatorio';
                    input.insertAdjacentElement('afterend', div);
                }
            } else {
                input.classList.remove('error');
                if (errorMsg && errorMsg.classList.contains('input-error')) errorMsg.remove();
            }
        });

        if (!validarPasswords()) {
            confirmPassword.classList.add('shake');
            setTimeout(() => confirmPassword.classList.remove('shake'), 500);
            valido = false;
        }

        const regexEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!regexEmail.test(correo.value.trim())) {
            correo.classList.add('invalid');
            correoError.textContent = 'Formato de correo inválido.';
            valido = false;
        }

        if (!valido) {
            e.preventDefault();
            return;
        }

        button.disabled = true;
        button.textContent = 'Registrando...';
        button.classList.add('sending');
    });

    // Mostrar alertas desde Thymeleaf con SweetAlert
    const exito = document.getElementById('registroExito');
    if (exito) {
        Swal.fire({
            icon: 'success',
            title: exito.dataset.msg,
            timer: 4000,
            showConfirmButton: false,
            toast: true,
            position: 'top-end'
        });
    }

    const error = document.getElementById('registroError');
    if (error) {
        Swal.fire({
            icon: 'error',
            title: error.dataset.msg,
            timer: 5000,
            showConfirmButton: false,
            toast: true,
            position: 'top-end'
        });
    }
});

// Mostrar u ocultar contraseña
function togglePassword(id) {
    const input = document.getElementById(id);
    input.type = input.type === 'password' ? 'text' : 'password';
}
