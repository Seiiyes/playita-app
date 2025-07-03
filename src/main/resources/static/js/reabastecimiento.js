/* global Swal */

document.addEventListener('DOMContentLoaded', () => {
  const modalNuevo = document.getElementById('modalNuevoReab');
  const modalDetalle = document.getElementById('modalDetalleReab');
  const btnNuevo = document.getElementById('btnNuevoReab');
  const cerrarNuevo = document.getElementById('cerrarNuevo');
  const cancelarNuevo = document.getElementById('cancelarNuevo');
  const cerrarDetalle = document.getElementById('cerrarDetalle');
  const lineas = document.getElementById('lineas');
  const formNuevo = document.getElementById('formNuevoReab');
  const productos = Array.from(document.querySelectorAll('option[th\\:each]'));

  // Abrir modal nuevo
  btnNuevo.addEventListener('click', () => {
    modalNuevo.classList.add('active');
  });

  // Cerrar modal nuevo
  [cerrarNuevo, cancelarNuevo].forEach(btn =>
    btn.addEventListener('click', () => {
      modalNuevo.classList.remove('active');
      lineas.innerHTML = '';
    })
  );

  // Agregar línea de producto
  document.getElementById('agregarLinea').addEventListener('click', () => {
    const div = document.createElement('div');
    div.classList.add('linea-producto');

    const opciones = productos.map(o =>
      `<option value="${o.value}">${o.textContent}</option>`
    ).join('');

    div.innerHTML = `
      <select name="productoId" required>
        <option value="" disabled selected>Producto</option>
        ${opciones}
      </select>
      <input type="number" name="cantidad" min="1" placeholder="Cantidad" required>
      <button type="button" class="btn small danger btnEliminarLinea">❌</button>
    `;

    lineas.appendChild(div);

    // Botón eliminar línea
    div.querySelector('.btnEliminarLinea').addEventListener('click', () => {
      div.remove();
    });
  });

  // Validación del formulario (mínimo 1 línea)
  formNuevo.addEventListener('submit', (e) => {
    const lineasActuales = lineas.querySelectorAll('.linea-producto');
    if (lineasActuales.length === 0) {
      e.preventDefault();
      Swal.fire('Advertencia', 'Debes agregar al menos un producto.', 'warning');
    }
  });

  // Ver detalles con AJAX
  document.querySelectorAll('.btnVerDetalle').forEach(btn => {
    btn.addEventListener('click', () => {
      const id = btn.getAttribute('data-id');
      fetch(`/reabastecimientos/detalle/${id}`)
        .then(res => res.text())
        .then(html => {
          const parser = new DOMParser();
          const doc = parser.parseFromString(html, 'text/html');
          const detalleBody = doc.querySelector('tbody');

          document.getElementById('detalleBody').innerHTML = detalleBody ? detalleBody.innerHTML : '<tr><td colspan="2">Sin datos</td></tr>';
          modalDetalle.classList.add('active');
        })
        .catch(err => {
          console.error('Error al cargar detalle:', err);
          Swal.fire('Error', 'No se pudo cargar el detalle del reabastecimiento.', 'error');
        });
    });
  });

  cerrarDetalle.addEventListener('click', () => {
    modalDetalle.classList.remove('active');
  });

  // Confirmar eliminación
  document.querySelectorAll('.formEliminarReab').forEach(btn => {
    btn.addEventListener('click', (e) => {
      e.preventDefault();
      const id = btn.getAttribute('data-id');
      Swal.fire({
        title: '¿Eliminar reabastecimiento?',
        text: 'No se puede recuperar luego.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar'
      }).then(res => {
        if (res.isConfirmed) {
          window.location = `/reabastecimientos/eliminar/${id}`;
        }
      });
    });
  });
});
