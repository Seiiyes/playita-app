/* global Swal */

// productos.js
document.addEventListener('DOMContentLoaded', () => {
  const modalCrear = document.getElementById('modalCrearProducto');
  const modalEditar = document.getElementById('modalEditarProducto');
  const formCrear = document.getElementById('formCrearProducto');
  const formEditar = document.getElementById('formEditarProducto');

  // Botones
  const btnAbrirCrear = document.getElementById('btnNuevoProducto');
  const btnCerrarCrear1 = document.getElementById('cerrarModalCrearProducto');
  const btnCerrarCrear2 = document.getElementById('cerrarModalCrearProducto2');
  const btnCerrarEditar1 = document.getElementById('cerrarModalEditarProducto');
  const btnCerrarEditar2 = document.getElementById('cerrarModalEditarProducto2');

  // Abrir modal crear
  btnAbrirCrear?.addEventListener('click', () => modalCrear.classList.add('active'));

  // Cerrar modales
  [btnCerrarCrear1, btnCerrarCrear2].forEach(btn => btn?.addEventListener('click', () => cerrarModal(modalCrear, formCrear)));
  [btnCerrarEditar1, btnCerrarEditar2].forEach(btn => btn?.addEventListener('click', () => cerrarModal(modalEditar, formEditar)));

  // Cerrar si se hace clic fuera del modal
  [modalCrear, modalEditar].forEach(modal => {
    modal?.addEventListener('click', e => {
      if (e.target === modal) cerrarModal(modal, modal === modalCrear ? formCrear : formEditar);
    });
  });

  // Crear producto
  formCrear?.addEventListener('submit', e => {
    e.preventDefault();
    if (!validarFormulario(formCrear)) {
      mostrarAlerta('Campos incompletos', 'Todos los campos son obligatorios.', 'warning');
      return;
    }

    Swal.fire({
      title: '¿Crear producto?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Sí, crear',
      cancelButtonText: 'Cancelar'
    }).then(result => {
      if (result.isConfirmed) formCrear.submit();
    });
  });

  // Editar producto
  formEditar?.addEventListener('submit', e => {
    e.preventDefault();
    if (!validarFormulario(formEditar)) {
      mostrarAlerta('Campos incompletos', 'Todos los campos son obligatorios.', 'warning');
      return;
    }

    Swal.fire({
      title: '¿Actualizar producto?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Sí, actualizar',
      cancelButtonText: 'Cancelar'
    }).then(result => {
      if (result.isConfirmed) formEditar.submit();
    });
  });

  // Botón editar
  document.querySelectorAll('.btnEditarProducto').forEach(btn => {
    btn.addEventListener('click', () => {
      document.getElementById('editarIdProducto').value = btn.getAttribute('data-id');
      document.getElementById('editarNombre').value = btn.getAttribute('data-nombre');
      document.getElementById('editarPrecio').value = btn.getAttribute('data-precio');
      document.getElementById('editarIva').value = btn.getAttribute('data-iva') || '';
      document.getElementById('editarStock').value = btn.getAttribute('data-stock');
      document.getElementById('editarCaducidad').value = btn.getAttribute('data-caducidad');
      document.getElementById('editarDescripcion').value = btn.getAttribute('data-descripcion');
      document.getElementById('editarCategoria').value = btn.getAttribute('data-categoria-id');

      modalEditar.classList.add('active');
    });
  });

  // Botón eliminar
  document.querySelectorAll('.formEliminarProducto').forEach(form => {
    form.addEventListener('submit', e => {
      e.preventDefault();

      Swal.fire({
        title: '¿Eliminar producto?',
        text: 'Esta acción no se puede deshacer.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar',
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
      }).then(result => {
        if (result.isConfirmed) {
          form.submit();
        }
      });
    });
  });

  // Helpers
  function cerrarModal(modal, form) {
    modal.classList.remove('active');
    form.reset();
  }

    function validarFormulario(form) {
        const campos = form.querySelectorAll('input, select');
        return [...campos].every(campo => {
            if (campo.hasAttribute('required')) {
                return campo.value.trim() !== '';
            }
            return true; // Los opcionales como IVA pueden ir vacíos
        });
    }


  function mostrarAlerta(titulo, texto, icono) {
    Swal.fire({ title: titulo, text: texto, icon: icono });
  }
});
