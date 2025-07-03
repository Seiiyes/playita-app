/* global Swal */

document.addEventListener('DOMContentLoaded', () => {
  const modalNuevo = document.getElementById('modalNuevoPedido');
  const modalDetalle = document.getElementById('modalDetallePedido');
  const btnNuevo = document.getElementById('btnNuevoPedido');
  const cerrarNuevo = document.getElementById('cerrarNuevo');
  const cancelarNuevo = document.getElementById('cancelarNuevo');
  const cerrarDetalle = document.getElementById('cerrarDetalle');
  const lineas = document.getElementById('lineasPedido');
  let contadorLineas = 0;

  // Mostrar modal nuevo
  btnNuevo.addEventListener('click', () => {
    modalNuevo.classList.add('active');
  });

  // Cerrar modal nuevo
  [cerrarNuevo, cancelarNuevo].forEach(btn => {
    btn.addEventListener('click', () => {
      modalNuevo.classList.remove('active');
      lineas.innerHTML = '';
      contadorLineas = 0;
    });
  });

  // Agregar línea producto
  document.getElementById('agregarLineaPedido').addEventListener('click', () => {
    contadorLineas++;
    const div = document.createElement('div');
    div.className = 'linea-producto';

    const productosOptions = Array.from(document.querySelectorAll('option[th\\:each]'))
      .map(opt => `<option value="${opt.value}">${opt.textContent}</option>`).join('');

    div.innerHTML = `
      <select name="productoId" required>
        <option disabled selected>Producto</option>
        ${productosOptions}
      </select>
      <input type="number" name="cantidad" min="1" placeholder="Cantidad" required>
      <input type="number" name="precio_unitario" step="0.01" placeholder="Precio unitario" required>
      <button type="button" class="btn small danger btnEliminarLinea">✖</button>
    `;
    lineas.appendChild(div);

    div.querySelector('.btnEliminarLinea').addEventListener('click', () => {
      div.remove();
    });
  });

  // Ver detalle pedido
  document.querySelectorAll('.btnVerDetalle').forEach(btn => {
    btn.addEventListener('click', () => {
      const id = btn.getAttribute('data-id');
      fetch(`/pedidos/detalle/${id}`)
        .then(res => res.text())
        .then(html => {
          document.getElementById('detalleBodyPedido').innerHTML = html;
          modalDetalle.classList.add('active');
        });
    });
  });

  // Cerrar modal detalle
  cerrarDetalle.addEventListener('click', () => {
    modalDetalle.classList.remove('active');
  });

  // Eliminar pedido
  document.querySelectorAll('.formEliminarPedido').forEach(btn => {
    btn.addEventListener('click', (e) => {
      e.preventDefault();
      const id = btn.getAttribute('data-id');

      Swal.fire({
        title: '¿Eliminar pedido?',
        text: 'No se puede recuperar luego.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar'
      }).then(res => {
        if (res.isConfirmed) {
          window.location = `/pedidos/eliminar/${id}`;
        }
      });
    });
  });
});
