document.addEventListener('DOMContentLoaded', () => {
    const modalCrear = document.getElementById('modalCrearProveedor');
    const modalEditar = document.getElementById('modalEditarProveedor');

    // Mostrar modal crear
    document.getElementById('btnNuevoProveedor').addEventListener('click', () => {
        modalCrear.classList.add('active');
    });

    // Cerrar modal crear
    document.getElementById('cerrarModalCrearProveedor').addEventListener('click', () => {
        modalCrear.classList.remove('active');
    });

    document.getElementById('cerrarModalCrearProveedor2').addEventListener('click', () => {
        modalCrear.classList.remove('active');
    });

    // Mostrar modal editar con datos
    document.querySelectorAll('.btnEditarProveedor').forEach(btn => {
        btn.addEventListener('click', () => {
            const id = btn.getAttribute('data-id');
            const pNombre = btn.getAttribute('data-pnombre');
            const sNombre = btn.getAttribute('data-snombre');
            const pApellido = btn.getAttribute('data-papellido');
            const sApellido = btn.getAttribute('data-sapellido');
            const telefono = btn.getAttribute('data-telefono');
            const correo = btn.getAttribute('data-correo');
            const direccion = btn.getAttribute('data-direccion');

            // Rellenar formulario
            document.getElementById('editarIdProveedor').value = id;
            document.getElementById('editarPNombre').value = pNombre;
            document.getElementById('editarSNombre').value = sNombre;
            document.getElementById('editarPApellido').value = pApellido;
            document.getElementById('editarSApellido').value = sApellido;
            document.getElementById('editarTelefono').value = telefono;
            document.getElementById('editarCorreo').value = correo;
            document.getElementById('editarDireccion').value = direccion;

            modalEditar.classList.add('active');
        });
    });

    // Cerrar modal editar
    document.getElementById('cerrarModalEditarProveedor').addEventListener('click', () => {
        modalEditar.classList.remove('active');
    });

    document.getElementById('cerrarModalEditarProveedor2').addEventListener('click', () => {
        modalEditar.classList.remove('active');
    });

    // Confirmación SweetAlert al eliminar
    document.querySelectorAll('.formEliminarProveedor').forEach(form => {
        form.addEventListener('submit', (e) => {
            e.preventDefault();
            Swal.fire({
                title: '¿Estás seguro?',
                text: "Esta acción no se puede deshacer.",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#d33',
                cancelButtonColor: '#3085d6',
                confirmButtonText: 'Sí, eliminar',
                cancelButtonText: 'Cancelar'
            }).then((result) => {
                if (result.isConfirmed) {
                    form.submit();
                }
            });
        });
    });

    // Cerrar modal al hacer clic fuera del contenido
    window.addEventListener('click', (e) => {
        if (e.target === modalCrear) modalCrear.classList.remove('active');
        if (e.target === modalEditar) modalEditar.classList.remove('active');
    });

    // Cerrar con tecla ESC
    document.addEventListener('keydown', (e) => {
        if (e.key === 'Escape') {
            modalCrear.classList.remove('active');
            modalEditar.classList.remove('active');
        }
    });
});
