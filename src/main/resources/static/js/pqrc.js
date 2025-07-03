/* global Swal */
document.addEventListener('DOMContentLoaded', () => {
  const modalCrear = document.getElementById('modalPQRC');
  const modalEditar = document.getElementById('modalEditarPQRC');
  const formCrear = document.getElementById('pqrcForm');
  const formEditar = document.getElementById('editarPQRCForm');

  const btnAbrirCrear = document.getElementById('btnNuevoPQRC');
  const btnCerrarCrear = document.getElementById('cerrarModalPQRC');
  const btnCancelarCrear = document.getElementById('cerrarModalPQRC2');
  const btnCerrarEditar = document.getElementById('cerrarModalEditarPQRC');
  const btnCancelarEditar = document.getElementById('cerrarModalEditarPQRC2');

  // Abrir modal crear
  btnAbrirCrear?.addEventListener('click', () => modalCrear.classList.add('active'));

  // Cerrar modales
  btnCerrarCrear?.addEventListener('click', () => cerrarModal(modalCrear, formCrear));
  btnCancelarCrear?.addEventListener('click', () => cerrarModal(modalCrear, formCrear));
  btnCerrarEditar?.addEventListener('click', () => cerrarModal(modalEditar, formEditar));
  btnCancelarEditar?.addEventListener('click', () => cerrarModal(modalEditar, formEditar));

  // Cerrar modal con ESC
  document.addEventListener('keydown', e => {
    if (e.key === 'Escape') {
      cerrarModal(modalCrear, formCrear);
      cerrarModal(modalEditar, formEditar);
    }
  });

  // Cerrar modal al hacer clic fuera
  [modalCrear, modalEditar].forEach(modal => {
    modal?.addEventListener('click', e => {
      if (e.target === modal) cerrarModal(modal, modal === modalCrear ? formCrear : formEditar);
    });
  });

  // Validación formulario crear
  formCrear?.addEventListener('submit', e => {
    if (!validarFormulario(formCrear)) {
      e.preventDefault();
      mostrarAlertaCampos();
    }
  });

  // Validación formulario editar
// Confirmar antes de enviar formulario editar
    formEditar?.addEventListener('submit', e => {
        e.preventDefault(); // prevenir envío inmediato

        if (!validarFormulario(formEditar)) {
            mostrarAlertaCampos();
            return;
        }

        Swal.fire({
            title: '¿Confirmar actualización?',
            text: '¿Deseas guardar los cambios realizados?',
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

  // Rellenar modal de edición
  document.querySelectorAll('.btnEditarPQRC').forEach(btn => {
    btn.addEventListener('click', () => {
      const tipo = btn.getAttribute('data-tipo');
      const descripcion = btn.getAttribute('data-descripcion');
      const estado = btn.getAttribute('data-estado');
      const documento = btn.getAttribute('data-documento');
      const id = btn.getAttribute('data-id');

      formEditar.pk_id_pqrc.value = id;
      formEditar['cliente.documentoC'].value = documento;
      formEditar.tipo.value = tipo;
      formEditar.descripcion.value = descripcion;
      formEditar.estado.value = estado;

      modalEditar.classList.add('active');
    });
  });

  // Confirmar eliminación con SweetAlert
  document.querySelectorAll('.formEliminarPQRC').forEach(form => {
    const btn = form.querySelector('button');
    btn?.addEventListener('click', e => {
      e.preventDefault();
      Swal.fire({
        title: '¿Estás seguro?',
        text: 'Esta acción eliminará el PQRC de forma permanente.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar'
      }).then(result => {
        if (result.isConfirmed) {
          form.submit();
        }
      });
    });
  });

  // Función para cerrar y limpiar formularios
  function cerrarModal(modal, form) {
    modal.classList.remove('active');
    form.reset();
  }

  // Validación de campos requeridos
  function validarFormulario(form) {
    const doc = form.querySelector('[name="cliente.documentoC"]')?.value.trim();
    const tipo = form.querySelector('[name="tipo"]')?.value.trim();
    const desc = form.querySelector('[name="descripcion"]')?.value.trim();
    return doc && tipo && desc;
  }

  // Alerta para campos vacíos
  function mostrarAlertaCampos() {
    Swal.fire({
      icon: 'warning',
      title: 'Campos requeridos',
      text: 'Por favor, completa todos los campos del formulario.'
    });
  }
});
