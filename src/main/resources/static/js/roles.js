// public/js/roles.js
document.addEventListener('DOMContentLoaded', () => {
  const modalCrear = document.getElementById('modalCrearRol');
  const modalEditar = document.getElementById('modalEditarRol');
  const formCrear = document.getElementById('formCrearRol');
  const formEditar = document.getElementById('formEditarRol');

  // Abrir modal de crear
  document.getElementById('btnNuevoRol')?.addEventListener('click', () => {
    modalCrear.classList.add('active');
  });

  // Cerrar modal crear
  document.getElementById('cerrarModalCrearRol')?.addEventListener('click', () => cerrarModal(modalCrear, formCrear));
  document.getElementById('cerrarModalCrearRol2')?.addEventListener('click', () => cerrarModal(modalCrear, formCrear));

  // Cerrar modal editar
  document.getElementById('cerrarModalEditarRol')?.addEventListener('click', () => cerrarModal(modalEditar, formEditar));
  document.getElementById('cerrarModalEditarRol2')?.addEventListener('click', () => cerrarModal(modalEditar, formEditar));

  // Cerrar modal al hacer clic fuera
  [modalCrear, modalEditar].forEach(modal => {
    modal?.addEventListener('click', e => {
      if (e.target === modal) cerrarModal(modal, modal === modalCrear ? formCrear : formEditar);
    });
  });

  // Submit crear
  formCrear?.addEventListener('submit', e => {
    e.preventDefault();
    const input = formCrear.querySelector('input[name="descRol"]');
    if (!input.value.trim()) {
      Swal.fire('Campo requerido', 'El nombre del rol no puede estar vacío.', 'warning');
      return;
    }

    Swal.fire({
      title: '¿Crear nuevo rol?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Sí, crear',
      cancelButtonText: 'Cancelar'
    }).then(result => {
      if (result.isConfirmed) {
        formCrear.submit();
      }
    });
  });

  // Submit editar
  formEditar?.addEventListener('submit', e => {
    e.preventDefault();
    const input = formEditar.querySelector('input[name="descRol"]');
    if (!input.value.trim()) {
      Swal.fire('Campo requerido', 'El nombre del rol no puede estar vacío.', 'warning');
      return;
    }

    Swal.fire({
      title: '¿Actualizar rol?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Sí, actualizar',
      cancelButtonText: 'Cancelar'
    }).then(result => {
      if (result.isConfirmed) {
        formEditar.submit();
      }
    });
  });

// Botón editar
document.querySelectorAll('.btnEditarRol').forEach(btn => {
  btn.addEventListener('click', () => {
    const id = btn.getAttribute('data-id');
    const desc = btn.getAttribute('data-desc-rol'); // CORREGIDO

    document.getElementById('editarIdRol').value = id;
    document.getElementById('editarDescRol').value = desc;

    modalEditar.classList.add('active');
  });
});


  // Botón eliminar
  document.querySelectorAll('.formEliminarRol').forEach(form => {
    form.addEventListener('submit', e => {
      e.preventDefault();
      Swal.fire({
        title: '¿Eliminar rol?',
        text: 'Esta acción no se puede deshacer.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar'
      }).then(result => {
        if (result.isConfirmed) form.submit();
      });
    });
  });

  function cerrarModal(modal, form) {
    modal.classList.remove('active');
    form.reset();
  }
});
