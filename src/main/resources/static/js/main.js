document.addEventListener('DOMContentLoaded', function () {
    var clienteRadio = document.getElementById('cliente');
    var proveedorRadio = document.getElementById('proveedor');
    var mixtoRadio = document.getElementById('mixto');
    var tipoServicioDiv = document.getElementById('tipoServicio');

    function toggleTipoServicio() {
        if (proveedorRadio.checked || mixtoRadio.checked) {
            tipoServicioDiv.style.display = 'block';
        } else {
            tipoServicioDiv.style.display = 'none';
        }
    }
    clienteRadio.addEventListener('change', toggleTipoServicio);
    proveedorRadio.addEventListener('change', toggleTipoServicio);
    mixtoRadio.addEventListener('change', toggleTipoServicio);
    toggleTipoServicio();
});