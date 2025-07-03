/* global Swal */

document.addEventListener('DOMContentLoaded', () => {
    // Botones
    const btnNuevaCategoria = document.getElementById('btnNuevaCategoria');
    const modalCrear = document.getElementById('modalCrearCategoria');
    const modalEditar = document.getElementById('modalEditarCategoria');
    const cerrarModalCrear = document.getElementById('cerrarModalCrearCategoria');
    const cerrarModalCrear2 = document.getElementById('cerrarModalCrearCategoria2');
    const cerrarModalEditar = document.getElementById('cerrarModalEditarCategoria');
    const cerrarModalEditar2 = document.getElementById('cerrarModalEditarCategoria2');

    // Mostrar modal crear
    btnNuevaCategoria.addEventListener('click', () => {
        modalCrear.classList.add('mostrar');
    });

    // Cerrar modales
    [cerrarModalCrear, cerrarModalCrear2].forEach(btn => {
        btn.addEventListener('click', () => {
            modalCrear.classList.remove('mostrar');
        });
    });

    [cerrarModalEditar, cerrarModalEditar2].forEach(btn => {
        btn.addEventListener('click', () => {
            modalEditar.classList.remove('mostrar');
        });
    });

    // Botones editar
    document.querySelectorAll('.btnEditarCategoria').forEach(btn => {
        btn.addEventListener('click', () => {
            const id = btn.getAttribute('data-id');
            const nombre = btn.getAttribute('data-nombre');
            document.getElementById('editarIdCategoria').value = id;
            document.getElementById('editarNombreCategoria').value = nombre;
            modalEditar.classList.add('mostrar');
        });
    });

    // Confirmar antes de eliminar
    document.querySelectorAll('.formEliminarCategoria').forEach(form => {
        form.addEventListener('submit', (e) => {
            e.preventDefault();
            Swal.fire({
                title: '¿Estás seguro?',
                text: "Esta acción no se puede deshacer.",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Sí, eliminar',
                cancelButtonText: 'Cancelar'
            }).then((result) => {
                if (result.isConfirmed) {
                    form.submit();
                }
            });
        });
    });

    // Mensajes de éxito (guardar, actualizar, eliminar)
    const params = new URLSearchParams(window.location.search);
    if (params.has('success')) {
        Swal.fire('¡Guardado!', 'La categoría fue registrada exitosamente.', 'success');
    } else if (params.has('updated')) {
        Swal.fire('¡Actualizado!', 'La categoría fue actualizada exitosamente.', 'success');
    } else if (params.has('deleted')) {
        Swal.fire('¡Eliminado!', 'La categoría fue eliminada exitosamente.', 'success');
    }
});
