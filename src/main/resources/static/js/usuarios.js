/* global Swal */

document.addEventListener('DOMContentLoaded', () => {
  const modalCrear = document.getElementById('modalCrearUsuario');
  const modalEditar = document.getElementById('modalEditarUsuario');
  const formCrear = document.getElementById('formCrearUsuario');
  const formEditar = document.getElementById('formEditarUsuario');

  const btnAbrirCrear = document.getElementById('btnNuevoUsuario');
  const btnCerrarCrear = document.getElementById('cerrarModalCrearUsuario');
  const btnCerrarCrear2 = document.getElementById('cerrarModalCrearUsuario2');
  const btnCerrarEditar = document.getElementById('cerrarModalEditarUsuario');
  const btnCerrarEditar2 = document.getElementById('cerrarModalEditarUsuario2');

  // Abrir modal de creación
  btnAbrirCrear?.addEventListener('click', () => {
    modalCrear.classList.add('active');
  });

  // Cerrar modales
  btnCerrarCrear?.addEventListener('click', () => cerrarModal(modalCrear, formCrear));
  btnCerrarCrear2?.addEventListener('click', () => cerrarModal(modalCrear, formCrear));
  btnCerrarEditar?.addEventListener('click', () => cerrarModal(modalEditar, formEditar));
  btnCerrarEditar2?.addEventListener('click', () => cerrarModal(modalEditar, formEditar));

  // Cerrar modales si se hace clic fuera del contenido
  [modalCrear, modalEditar].forEach(modal => {
    modal?.addEventListener('click', e => {
      if (e.target === modal) cerrarModal(modal, modal === modalCrear ? formCrear : formEditar);
    });
  });

  // Validación y envío con confirmación (Crear)
  formCrear?.addEventListener('submit', e => {
    e.preventDefault();
    if (!validarFormulario(formCrear)) {
      mostrarAlerta('Campos incompletos', 'Por favor, completa todos los campos.', 'warning');
      return;
    }

    Swal.fire({
      title: '¿Deseas crear este usuario?',
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

  // Validación y envío con confirmación (Editar)
  formEditar?.addEventListener('submit', e => {
    e.preventDefault();
    if (!validarFormulario(formEditar)) {
      mostrarAlerta('Campos incompletos', 'Por favor, completa todos los campos.', 'warning');
      return;
    }

    Swal.fire({
      title: '¿Deseas actualizar este usuario?',
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

  // Botones de editar
  document.querySelectorAll('.btnEditarUsuario').forEach(btn => {
    btn.addEventListener('click', () => {
      document.getElementById('editarIdUsuario').value = btn.getAttribute('data-id');
      document.getElementById('editarDocumento').value = btn.getAttribute('data-documento');
      document.getElementById('editarPrimerNombre').value = btn.getAttribute('data-primer-nombre');
      document.getElementById('editarSegundoNombre').value = btn.getAttribute('data-segundo-nombre');
      document.getElementById('editarPrimerApellido').value = btn.getAttribute('data-primer-apellido');
      document.getElementById('editarSegundoApellido').value = btn.getAttribute('data-segundo-apellido');
      document.getElementById('editarCorreo').value = btn.getAttribute('data-correo');
      document.getElementById('editarRol').value = btn.getAttribute('data-rol-id');
      document.getElementById('editarEstado').value = btn.getAttribute('data-estado');

      modalEditar.classList.add('active');

      // Enfocar automáticamente el documento
      setTimeout(() => {
        document.getElementById('editarDocumento')?.focus();
      }, 200);
    });
  });

  // Botones de eliminar con SweetAlert
  document.querySelectorAll('.formEliminarUsuario').forEach(form => {
    form.addEventListener('submit', function (e) {
      e.preventDefault();

      Swal.fire({
        title: '¿Eliminar usuario?',
        text: 'Esta acción no se puede deshacer.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar'
      }).then(result => {
        if (result.isConfirmed) {
          this.submit();
        }
      });
    });
  });

  // Función para cerrar un modal y limpiar su formulario
  function cerrarModal(modal, form) {
    modal.classList.remove('active');
    form.reset();
  }

  // Validación básica de campos requeridos
  function validarFormulario(form) {
    const campos = form.querySelectorAll('input[required], select[required]');
    for (let campo of campos) {
      if (campo.value.trim() === '') return false;
    }
    return true;
  }

  // Mostrar alerta con SweetAlert
  function mostrarAlerta(titulo, texto, tipo) {
    Swal.fire({
      title: titulo,
      text: texto,
      icon: tipo
    });
  }
});
