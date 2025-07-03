/* global Swal */

document.addEventListener('DOMContentLoaded', () => {
  const modal = document.getElementById('modalVenta');
  const btnAbrir = document.getElementById('btnAbrirModal');

  const cerrarVenta = () => {
    const form = modal.querySelector('form');
    if (tieneDatosEnFormulario(form)) {
      Swal.fire({
        title: '¿Cerrar sin guardar?',
        text: 'Hay datos en el formulario. ¿Seguro que quieres cerrar?',
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: 'Sí, cerrar',
        cancelButtonText: 'Cancelar'
      }).then(res => {
        if (res.isConfirmed) modal.classList.remove('active');
      });
    } else {
      modal.classList.remove('active');
    }
  };

  const abrirVenta = () => modal.classList.add('active');

  btnAbrir?.addEventListener('click', abrirVenta);
  document.getElementById('cerrarModal')?.addEventListener('click', cerrarVenta);
  document.getElementById('cerrarModal2')?.addEventListener('click', cerrarVenta);
  modal.querySelector('.modal-overlay')?.addEventListener('click', cerrarVenta);

  const actualizarTotal = () => {
    let total = 0;
    document.querySelectorAll('.producto-item').forEach(item => {
      const select = item.querySelector('select');
      const input = item.querySelector('input[type=number]');
      const subtotalSpan = item.querySelector('.input-subtotal');

      const precio = parseFloat(select?.selectedOptions[0]?.dataset.precio || 0);
      const stock = parseInt(select?.selectedOptions[0]?.dataset.stock || 0);
      const cantidad = parseInt(input?.value || 0);

      // Validación contra stock
      if (cantidad > stock) {
        input.value = stock;
        Swal.fire({
          icon: 'warning',
          title: 'Cantidad excede stock',
          text: `Solo hay ${stock} unidades disponibles.`
        });
      }

      const subtotal = precio * (input.value || 0);
      subtotalSpan.textContent = isNaN(subtotal) ? '0.00' : subtotal.toFixed(2);
      total += isNaN(subtotal) ? 0 : subtotal;
    });

    document.getElementById('totalVenta').textContent = total.toFixed(2);
    document.getElementById('totalInput').value = total.toFixed(2);
  };

  document.getElementById('productosContainer')?.addEventListener('input', actualizarTotal);

  document.getElementById('btnAgregarProducto')?.addEventListener('click', () => {
    const container = document.getElementById('productosContainer');
    const base = container.firstElementChild;
    const nuevo = base.cloneNode(true);
    const index = container.children.length;

    nuevo.querySelectorAll('input, select').forEach(el => {
      const campo = el.name.split('.').pop();
      el.name = `productos[${index}].${campo}`;
      el.value = '';
      if (el.tagName === 'SELECT') el.selectedIndex = 0;
    });

    nuevo.querySelector('.input-subtotal').textContent = '0.00';
    container.appendChild(nuevo);
  });

  document.getElementById('productosContainer')?.addEventListener('click', e => {
    if (e.target.classList.contains('btn-quitar')) {
      const item = e.target.closest('.producto-item');
      const container = document.getElementById('productosContainer');
      if (container.children.length > 1) {
        item.remove();
        actualizarTotal();
      }
    }
  });

  // Modal Cliente
  const modalCliente = document.getElementById('modalCliente');
  const abrirModalCliente = () => modalCliente.classList.add('active');
  const cerrarModalCliente = () => {
    const form = document.getElementById('formNuevoCliente');
    if (tieneDatosEnFormulario(form)) {
      Swal.fire({
        title: '¿Cerrar sin guardar?',
        text: 'Se perderán los datos ingresados.',
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: 'Sí, cerrar',
        cancelButtonText: 'Cancelar'
      }).then(res => {
        if (res.isConfirmed) {
          modalCliente.classList.remove('active');
          form.reset();
        }
      });
    } else {
      modalCliente.classList.remove('active');
      form.reset();
    }
  };

  document.getElementById('btnNuevoCliente')?.addEventListener('click', abrirModalCliente);
  document.getElementById('cerrarModalCliente')?.addEventListener('click', cerrarModalCliente);
  document.getElementById('cerrarModalCliente2')?.addEventListener('click', cerrarModalCliente);
  modalCliente.querySelector('.modal-overlay')?.addEventListener('click', cerrarModalCliente);

  document.getElementById('formNuevoCliente')?.addEventListener('submit', async e => {
    e.preventDefault();
    const form = e.target;
    const datos = new URLSearchParams(new FormData(form));

    try {
      const response = await fetch('/api/clientes/crear-rapido', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: datos
      });

      if (!response.ok) {
        const error = await response.text();
        return Swal.fire('Error', error, 'warning');
      }

      const cliente = await response.json();
      const select = document.getElementById('cliente');
      const option = document.createElement('option');
      option.value = cliente.pkIdClientes;
      option.textContent = `${cliente.pNombreC} ${cliente.pApellidoC}`;
      option.selected = true;
      select.appendChild(option);

      Swal.fire('Cliente creado', 'Se agregó exitosamente', 'success');
      cerrarModalCliente();
    } catch (err) {
      console.error(err);
      Swal.fire('Error', 'Error inesperado al crear cliente.', 'error');
    }
  });

  // Confirmación para eliminar venta
  document.querySelectorAll('.form-eliminar').forEach(form => {
    form.addEventListener('submit', e => {
      e.preventDefault();
      Swal.fire({
        title: '¿Estás seguro?',
        text: 'Esta acción eliminará la venta de forma permanente.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#aaa',
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar'
      }).then(result => {
        if (result.isConfirmed) form.submit();
      });
    });
  });

  actualizarTotal();
});

function tieneDatosEnFormulario(form) {
  return Array.from(form.querySelectorAll('input, select')).some(el => el.value?.trim());
}
