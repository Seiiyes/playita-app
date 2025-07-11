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

  // Cerrar modal al hacer clic fuera del contenido
  [modalCrear, modalEditar].forEach(modal => {
    modal?.addEventListener('click', e => {
      if (e.target === modal) cerrarModal(modal, modal === modalCrear ? formCrear : formEditar);
    });
  });

  // Envío formulario de crear
  formCrear?.addEventListener('submit', e => {
    e.preventDefault();
    const validacion = validarFormulario(formCrear, true);
    if (!validacion.valido) {
      mostrarAlerta('Datos inválidos', validacion.mensaje, 'warning');
      return;
    }

    Swal.fire({
      title: '¿Deseas crear este usuario?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Sí, crear',
      cancelButtonText: 'Cancelar'
    }).then(result => {
      if (result.isConfirmed) formCrear.submit();
    });
  });

  // Envío formulario de editar
  formEditar?.addEventListener('submit', e => {
    e.preventDefault();
    const validacion = validarFormulario(formEditar, false);
    if (!validacion.valido) {
      mostrarAlerta('Datos inválidos', validacion.mensaje, 'warning');
      return;
    }

    Swal.fire({
      title: '¿Deseas actualizar este usuario?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Sí, actualizar',
      cancelButtonText: 'Cancelar'
    }).then(result => {
      if (result.isConfirmed) formEditar.submit();
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
      setTimeout(() => document.getElementById('editarDocumento')?.focus(), 200);
    });
  });

  // Confirmación al eliminar
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
        if (result.isConfirmed) this.submit();
      });
    });
  });

  // Cierra el modal y resetea el formulario
  function cerrarModal(modal, form) {
    modal.classList.remove('active');
    form.reset();
    form.querySelectorAll('select').forEach(select => select.selectedIndex = 0);
  }

  // Validación completa de formulario
  function validarFormulario(form, esCreacion) {
    const get = name => form.querySelector(`[name="${name}"]`)?.value.trim();
    const documento = get("documento");
    const primerNombre = get("primerNombre");
    const segundoNombre = get("segundoNombre");
    const primerApellido = get("primerApellido");
    const segundoApellido = get("segundoApellido");
    const correo = get("correo");
    const contrasena = get("contrasena");
    const rol = get("fkIdRoles");
    const estado = form.id === "formEditarUsuario" ? get("estado") : null;

    const letrasRegex = /^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]+$/;
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!documento || !/^\d{6,15}$/.test(documento))
      return { valido: false, mensaje: "El documento debe tener entre 6 y 15 dígitos numéricos." };

    if (!primerNombre || !letrasRegex.test(primerNombre))
      return { valido: false, mensaje: "El primer nombre solo debe contener letras." };

    if (segundoNombre && !letrasRegex.test(segundoNombre))
      return { valido: false, mensaje: "El segundo nombre solo debe contener letras." };

    if (!primerApellido || !letrasRegex.test(primerApellido))
      return { valido: false, mensaje: "El primer apellido solo debe contener letras." };

    if (segundoApellido && !letrasRegex.test(segundoApellido))
      return { valido: false, mensaje: "El segundo apellido solo debe contener letras." };

    if (!correo || !emailRegex.test(correo))
      return { valido: false, mensaje: "Ingresa un correo electrónico válido." };

    if (esCreacion && (!contrasena || contrasena.length < 6))
      return { valido: false, mensaje: "La contraseña debe tener al menos 6 caracteres." };

    if (!rol)
      return { valido: false, mensaje: "Debes seleccionar un rol válido." };

    if (form.id === "formEditarUsuario" && !estado)
      return { valido: false, mensaje: "Debes seleccionar un estado." };

    return { valido: true };
  }

  // Alerta SweetAlert
  function mostrarAlerta(titulo, texto, tipo) {
    Swal.fire({ title: titulo, text: texto, icon: tipo });
  }
});
