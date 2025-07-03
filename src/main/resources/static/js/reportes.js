let chartProductos = null;
let chartVentas = null;

document.addEventListener('DOMContentLoaded', () => {
    const productosEl = document.getElementById('datosProductos');
    const ventasEl = document.getElementById('datosVentas');

    console.log('📦 productosEl:', productosEl?.textContent);
    console.log('📦 ventasEl:', ventasEl?.textContent);

    // Opciones generales con texto blanco
    const opcionesGenerales = {
        responsive: true,
        plugins: {
            legend: {
                labels: {
                    color: '#ffffff', // blanco para leyendas
                    font: {
                        weight: 'bold'
                    }
                }
            },
            tooltip: {
                titleColor: '#ffffff',
                bodyColor: '#ffffff',
                backgroundColor: 'rgba(0,0,0,0.8)',
                borderColor: '#ffffff',
                borderWidth: 1
            }
        },
        scales: {
            x: {
                ticks: { color: '#ffffff' },
                grid: { color: 'rgba(255,255,255,0.1)' }
            },
            y: {
                beginAtZero: true,
                ticks: { color: '#ffffff' },
                grid: { color: 'rgba(255,255,255,0.1)' }
            }
        }
    };

    // Gráfico de Productos más vendidos
    const canvasProductos = document.getElementById('graficoProductosVendidos');
    if (productosEl && productosEl.textContent.trim() && canvasProductos) {
        try {
            const productosData = JSON.parse(productosEl.textContent);

            const nombres = productosData.map(p => p.nombreProducto);
            const cantidades = productosData.map(p => p.cantidadVendida);

            if (chartProductos) chartProductos.destroy();

            chartProductos = new Chart(canvasProductos, {
                type: 'bar',
                data: {
                    labels: nombres,
                    datasets: [{
                        label: 'Cantidad Vendida',
                        data: cantidades,
                        backgroundColor: 'rgba(255, 174, 66, 0.7)',
                        borderColor: 'rgba(255, 174, 66, 1)',
                        borderWidth: 2
                    }]
                },
                options: opcionesGenerales
            });
        } catch (e) {
            console.error('❌ Error al parsear productos JSON:', e);
        }
    }

    // Gráfico de Ingresos por Fecha
    const canvasVentas = document.getElementById('graficoIngresosPorFecha');
    if (ventasEl && ventasEl.textContent.trim() && canvasVentas) {
        try {
            const ventasData = JSON.parse(ventasEl.textContent);

            const fechas = ventasData.map(v => v.fecha);
            const ingresos = ventasData.map(v => v.total);

            if (chartVentas) chartVentas.destroy();

            chartVentas = new Chart(canvasVentas, {
                type: 'line',
                data: {
                    labels: fechas,
                    datasets: [{
                        label: 'Ingresos',
                        data: ingresos,
                        fill: true,
                        backgroundColor: 'rgba(0, 255, 163, 0.2)',
                        borderColor: '#00ffa2',
                        tension: 0.3
                    }]
                },
                options: opcionesGenerales
            });
        } catch (e) {
            console.error('❌ Error al parsear ventas JSON:', e);
        }
    }
});
