document.addEventListener('DOMContentLoaded', function () {
    var clienteRadio = document.getElementById('cliente');
    var proveedorRadio = document.getElementById('proveedor');
    var mixtoRadio = document.getElementById('mixto');
    var userRadio= document.getElementById('user');
    var tipoServicioDiv = document.getElementById('tipoServicio');

    function toggleTipoServicio() {
        if (proveedorRadio.checked || mixtoRadio.checked) {
            tipoServicioDiv.style.display = 'block';            
        } else {
            tipoServicioDiv.style.display = 'none';
        }
    }
    clienteRadio.addEventListener('change', toggleTipoServicio);
    userRadio.addEventListener('change', toggleTipoServicio);
    proveedorRadio.addEventListener('change', toggleTipoServicio);
    mixtoRadio.addEventListener('change', toggleTipoServicio);
    toggleTipoServicio();
});

document.getElementById("usuario").addEventListener('click', function() {
    let detallesUsuario = document.getElementById('detalles-usuario');
    let listItems = document.querySelectorAll('#detalles-usuario .list-group-item');
    
    // Calcular la altura total de los elementos de la lista con un margen adicional
    let totalHeight = 0;
    listItems.forEach(item => {
        totalHeight += item.clientHeight;
    });

    // Agregar un margen adicional para asegurar que todos los elementos se muestren completamente
    totalHeight += 8; // Puedes ajustar este valor según sea necesario

    if (detallesUsuario.style.height == '0px') {
        detallesUsuario.style.height = totalHeight + 'px';
        notificationPopup.classList.add('show');
    } else {
        detallesUsuario.style.height = '0';
        notificationPopup.classList.remove('show');
    }
});

document.getElementById("campana").addEventListener('click', function() {
    let notificationPopup = document.getElementById('notification');
    let listItems = document.querySelectorAll('#notification .list-group-item');
    
    // Calcular la altura total de los elementos de la lista con un margen adicional
    let totalHeight = 0;
    listItems.forEach(item => {
        totalHeight += item.clientHeight;
    });

    // Agregar un margen adicional para asegurar que todos los elementos se muestren completamente
    totalHeight += 12; // Puedes ajustar este valor según sea necesario

    if (notificationPopup.style.height == '0px') {
        notificationPopup.style.height = totalHeight + 'px';
        notificationPopup.classList.add('show');
        document.querySelector('#campana .notification-bell .notification-count').textContent="0";
    } else {
        notificationPopup.style.height = '0';
        notificationPopup.classList.remove('show');
    }
});

let primeraVez=true;

document.getElementById("mensajes").addEventListener('click', function() {
    let notificationPopup = document.getElementById('noti-mensajes');
    let listGroup = document.querySelector('#noti-mensajes .list-group');

    // Crear los mensajes
    if (primeraVez) {
        let mensajes = ["Proveedor 1", "Proveedor 2", "Proveedor 3"];
        mensajes.forEach((proveedor, index) => {
            let li = document.createElement("li");
            li.classList.add('list-group-item');
            let boton = document.createElement("button");
            boton.textContent = proveedor;
            boton.classList.add('btn', 'btn-outline-light');
            boton.setAttribute('type', 'button');
            boton.addEventListener("click", function() {
                if(document.querySelector('.mensaje-container').style.display = 'none'){
                    document.querySelector('.mensaje-container').style.display = 'block';
                    document.getElementById('nombre-proveedor').textContent = boton.textContent;
                } else{
                    document.getElementById('nombre-proveedor').textContent = boton.textContent;
                }
            });
            li.append(boton);
            listGroup.append(li);
        });
    primeraVez = false;
    }
    
    let listItems = document.querySelectorAll('#noti-mensajes .list-group-item');

    // Calcular la altura total de los elementos de la lista con un margen adicional
    let totalHeight = 0;
    listItems.forEach(item => {
        totalHeight += item.clientHeight;
    });

    // Agregar un margen adicional para asegurar que todos los elementos se muestren completamente
    totalHeight += 8; // Puedes ajustar este valor según sea necesario

    if (notificationPopup.style.height == '0px' || notificationPopup.style.height === '') {
        notificationPopup.style.height = totalHeight + 'px';
        notificationPopup.classList.add('show');
    } else {
        notificationPopup.style.height = '0';
        notificationPopup.classList.remove('show');
    }
});

document.getElementById("filtro").addEventListener('click', function() {
    let detallesFiltro = document.getElementById('detalles-filtro');
    let listItems = document.querySelectorAll('#detalles-filtro .list-group-item');

    // Calcular la altura total de los elementos de la lista con un margen adicional
    let totalHeight = 0;
    listItems.forEach(item => {
        totalHeight += item.clientHeight;
    });

    // Agregar un margen adicional para asegurar que todos los elementos se muestren completamente
    totalHeight += 8; // Puedes ajustar este valor según sea necesario

    if (detallesFiltro.style.height == '0px') {
        detallesFiltro.style.height = totalHeight + 'px';
        notificationPopup.classList.add('show');
    } else {
        detallesFiltro.style.height = '0';
        notificationPopup.classList.remove('show');
    }
});

document.getElementById("usuario-proveedor").addEventListener('click', function() {
    let detallesUsuario = document.getElementById('detalles-proveedor');
    let listItems = document.querySelectorAll('#detalles-proveedor .list-group-item');

    // Calcular la altura total de los elementos de la lista con un margen adicional
    let totalHeight = 0;
    listItems.forEach(item => {
        totalHeight += item.clientHeight;
    });

    // Agregar un margen adicional para asegurar que todos los elementos se muestren completamente
    totalHeight -= 95; // Puedes ajustar este valor según sea necesario

    if (detallesUsuario.style.height == '0px') {
        detallesUsuario.style.height = totalHeight + 'px';
        notificationPopup.classList.add('show');
    } else {
        detallesUsuario.style.height = '0';
        notificationPopup.classList.remove('show');
    }
});

let cambiarFuncion= document.getElementById("cambiar-funcion");

    cambiarFuncion.addEventListener('click', () => {
    if(cambiarFuncion.textContent === "Trabajo completo"){
        cambiarFuncion.textContent = "Pagar";
        cambiarFuncion.classList.remove('btn-outline-success');
        cambiarFuncion.classList.add('btn-outline-warning');
    }else if(cambiarFuncion.textContent === "Pagar"){
        cambiarFuncion.textContent = "Calificar";
        cambiarFuncion.classList.remove('btn-outline-warning');
        cambiarFuncion.classList.add('btn-outline-success');
    } else if(cambiarFuncion.textContent === "Calificar"){
        cambiarFuncion.textContent = "Modificar Calificacion";
    } else{
        cambiarFuncion.textContent = "Trabajo completo";
    }
})

document.querySelector('.controls .icon:nth-child(1)').addEventListener('click', () => {
    if(document.querySelector('.controls .icon:nth-child(1)').textContent=="👇"){
    document.querySelector('.controls .icon:nth-child(1)').textContent="👆";
    } else{
    document.querySelector('.controls .icon:nth-child(1)').textContent="👇";
    }
    const content = document.querySelector('.mensaje-content');
    const form = document.querySelector('.mensaje-form');
    content.style.display = content.style.display === 'none' ? 'block' : 'none';
    form.style.display = form.style.display === 'none' ? 'flex' : 'none';
});

document.querySelector('.controls .icon:nth-child(2)').addEventListener('click', () => {
    document.querySelector('.mensaje-container').style.display = 'none';
});
          
document.querySelector('.send').addEventListener('click', () => {
    const input = document.querySelector('.mensaje-form input[type="text"]');
    const mensaje = input.value.trim();
    const fileInput = document.getElementById('file-upload');
    const files = fileInput.files;

    const mensajeContent = document.querySelector('.mensaje-content');

    if (files.length > 0) {
        Array.from(files).forEach(file => {
            const nuevoMensaje = document.createElement('div');
            nuevoMensaje.classList.add('mensaje', 'usuario');
            const reader = new FileReader();
            reader.onload = function(e) {
                nuevoMensaje.innerHTML = `<div class="texto"><img src="${e.target.result}" class="message-image" onclick="viewImage('${e.target.result}')"></div>`;
                mensajeContent.appendChild(nuevoMensaje);
                mensajeContent.scrollTop = mensajeContent.scrollHeight;
            }
            reader.readAsDataURL(file);
        });
    }

    if (mensaje) {
        const nuevoMensaje = document.createElement('div');
        nuevoMensaje.classList.add('mensaje', 'usuario');
        nuevoMensaje.innerHTML = `<div class="texto">${mensaje}</div>`;
        mensajeContent.appendChild(nuevoMensaje);
        input.value = '';
        mensajeContent.scrollTop = mensajeContent.scrollHeight;
    }

    fileInput.value = '';
    document.getElementById('preview-container').innerHTML = '';
});
          
document.getElementById('file-upload').addEventListener('change', (event) => {
    const files = event.target.files;
    const previewContainer = document.getElementById('preview-container');
    previewContainer.innerHTML = '';

    Array.from(files).forEach((file, index) => {
        const reader = new FileReader();
        reader.onload = function(e) {
            const previewImage = document.createElement('div');
            previewImage.classList.add('preview-image');
            previewImage.innerHTML = `
                <img src="${e.target.result}" alt="Imagen adjunta">
                <button class="close-preview" data-index="${index}">&times;</button>
            `;
            previewContainer.appendChild(previewImage);
        }
        reader.readAsDataURL(file);
    });

    previewContainer.style.display = files.length > 0 ? 'flex' : 'none';
});
          
document.getElementById('preview-container').addEventListener('click', (event) => {
    if (event.target.classList.contains('close-preview')) {
        const index = event.target.getAttribute('data-index');
        const fileInput = document.getElementById('file-upload');
        const dt = new DataTransfer();
        const files = Array.from(fileInput.files);

        files.splice(index, 1);
        files.forEach(file => dt.items.add(file));
        fileInput.files = dt.files;

        event.target.parentElement.remove();
    }
});

document.getElementById('image-viewer').addEventListener('click', (event) => {
    if (event.target.classList.contains('close-viewer')) {
        document.getElementById('image-viewer').style.display = 'none';
    }
});

function viewImage(src) {
    const viewer = document.getElementById('image-viewer');
    const viewerImage = document.getElementById('viewer-image');
    const downloadLink = document.getElementById('download-link');
    
    viewerImage.src = src;
    downloadLink.href = src;
    viewer.style.display = 'flex';
}
        
        

// script.js
document.getElementById("reportar").addEventListener('click', function() {
    document.getElementById('report-form-overlay').style.display = 'block';
    document.getElementById('report-form').style.display = 'block';
});

document.getElementById('report-form-overlay').addEventListener('click', function() {
    document.getElementById('report-form-overlay').style.display = 'none';
    document.getElementById('report-form').style.display = 'none';
});

document.querySelector('.report-header .close').addEventListener('click', function() {
    document.getElementById('report-form-overlay').style.display = 'none';
    document.getElementById('report-form').style.display = 'none';
});

document.getElementById('formulario-reporte').addEventListener('submit', function(event) {
    event.preventDefault();
    // Aquí puedes añadir la lógica para enviar el formulario
    // Luego de enviar el formulario, puedes cerrar el modal:
    document.getElementById('report-form-overlay').style.display = 'none';
    document.getElementById('report-form').style.display = 'none';
});


// Espera a que se cargue completamente la página
document.addEventListener('DOMContentLoaded', function() {
    // Crear el elemento div con la clase "card"
    var cardDiv = document.createElement('div');
    cardDiv.classList.add('card');

    // Crear la imagen dentro del div.card
    var img = document.createElement('img');
    img.src = '...'; // Aquí va la URL de la imagen
    img.classList.add('card-img-top');
    img.alt = '...'; // Texto alternativo de la imagen
    cardDiv.appendChild(img);

    // Crear el div.card-body para el título
    var cardBodyTitle = document.createElement('div');
    cardBodyTitle.classList.add('card-body');

    // Crear el título h5 dentro del div.card-body
    var cardTitle = document.createElement('h5');
    cardTitle.classList.add('card-title');
    cardTitle.textContent = 'Nombre de la empresa';
    cardBodyTitle.appendChild(cardTitle);

    // Añadir el div.card-body del título al div.card
    cardDiv.appendChild(cardBodyTitle);

    // Crear la lista ul.list-group.list-group-flush
    var listGroup = document.createElement('ul');
    listGroup.classList.add('list-group', 'list-group-flush');

    // Crear los elementos li dentro de la lista
    var listItem1 = document.createElement('li');
    listItem1.classList.add('list-group-item');
    listItem1.textContent = 'Servicio que ofrece';
    listGroup.appendChild(listItem1);

    var listItem2 = document.createElement('li');
    listItem2.classList.add('list-group-item');
    listItem2.textContent = 'Su ubicación';
    listGroup.appendChild(listItem2);

    var listItem3 = document.createElement('li');
    listItem3.classList.add('list-group-item');
    listItem3.textContent = 'Precio del servicio en el día';
    listGroup.appendChild(listItem3);

    // Añadir la lista ul al div.card
    cardDiv.appendChild(listGroup);

    // Crear el segundo div.card-body para los botones
    var cardBodyButtons = document.createElement('div');
    cardBodyButtons.classList.add('card-body');

    // Crear el primer botón "Más Info"
    var cardLink1 = document.createElement('a');
    cardLink1.href = '#';
    cardLink1.classList.add('card-link');
    var button1 = document.createElement('button');
    button1.type = 'button';
    button1.classList.add('btn', 'btn-outline-info');
    button1.textContent = 'Más Info';
    cardLink1.appendChild(button1);
    cardBodyButtons.appendChild(cardLink1);

    // Crear el segundo botón "Solicitar Servicio"
    var cardLink2 = document.createElement('a');
    cardLink2.href = '#';
    cardLink2.classList.add('card-link');
    var button2 = document.createElement('button');
    button2.type = 'button';
    button2.classList.add('btn', 'btn-outline-success');
    button2.textContent = 'Solicitar Servicio';
    cardLink2.appendChild(button2);
    cardBodyButtons.appendChild(cardLink2);

    // Añadir el segundo div.card-body de los botones al div.card
    cardDiv.appendChild(cardBodyButtons);

    // Obtener el elemento donde se insertará la nueva tarjeta (por ejemplo, el body)
    var container = document.body; // Puedes cambiar esto por el contenedor deseado

    // Añadir la tarjeta creada al contenedor
    container.appendChild(cardDiv);
});