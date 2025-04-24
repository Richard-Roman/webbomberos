let index = document.querySelectorAll(".telefono-item").length;

function agregarTelefono() {
    const container = document.getElementById("telefonosContainer");

    const html = `
        <div class="telefono-item">
            <label>Nombre del Contacto:</label>
            <input type="text" name="telefonosEmergencia[${index}].nombreContacto" required />

            <label>Parentesco:</label>
            <input type="text" name="telefonosEmergencia[${index}].parentesco" required />

            <label>Teléfono:</label>
            <input type="text" name="telefonosEmergencia[${index}].telefono" required />

            <input type="hidden" name="telefonosEmergencia[${index}].desactivado" value="0" />

            <button type="button" class="btn-remove" onclick="eliminarTelefono(this)">Eliminar</button>
        </div>
    `;
    container.insertAdjacentHTML("beforeend", html);
    index++;
}

function eliminarTelefono(btn) {
    const container = btn.closest('.telefono-item');

    // Buscar o crear el input hidden 'desactivado'
    let inputDesactivado = container.querySelector('input[name$=".desactivado"]');
    if (!inputDesactivado) {
        inputDesactivado = document.createElement("input");
        inputDesactivado.type = "hidden";

        const nombreInput = container.querySelector('input[name$=".nombreContacto"]');
        const nameBase = nombreInput.name.replace("nombreContacto", "desactivado");

        inputDesactivado.name = nameBase;
        container.appendChild(inputDesactivado);
    }

    inputDesactivado.value = "1";

    // Oculta visualmente el bloque
    container.style.display = "none";
}

function validarFormulario() {
    const dni = document.getElementById("dni").value.trim();
    const nombres = document.getElementById("nombres").value.trim();
    const apellidos = document.getElementById("apellidos").value.trim();
    const fechaNacimiento = document.getElementById("fechaNacimiento").value;

    if (!/^\d{8}$/.test(dni)) {
        alert("El DNI debe tener 8 dígitos numéricos.");
        return false;
    }

    if (nombres.length < 2 || apellidos.length < 2) {
        alert("Nombres y apellidos deben tener al menos 2 caracteres.");
        return false;
    }

    const hoy = new Date();
    const nacimiento = new Date(fechaNacimiento);
    if (nacimiento >= hoy) {
        alert("La fecha de nacimiento no puede ser hoy o futura.");
        return false;
    }

    const telefonos = document.querySelectorAll('.telefono-item');
    for (let i = 0; i < telefonos.length; i++) {
        const contenedor = telefonos[i];

        // Si está oculto (desactivado), saltar validación
        if (contenedor.style.display === "none") continue;

        const nombre = contenedor.querySelector('input[name$=".nombreContacto"]')?.value.trim();
        const parentesco = contenedor.querySelector('input[name$=".parentesco"]')?.value.trim();
        const telefono = contenedor.querySelector('input[name$=".telefono"]')?.value.trim();

        if (!nombre || nombre.length < 2) {
            alert(`El nombre del contacto en el teléfono ${i + 1} es inválido.`);
            return false;
        }
        if (!parentesco || parentesco.length < 2) {
            alert(`El parentesco en el teléfono ${i + 1} es inválido.`);
            return false;
        }
        if (!/^\d{7,15}$/.test(telefono)) {
            alert(`El número telefónico en el teléfono ${i + 1} debe tener entre 7 y 15 dígitos.`);
            return false;
        }
    }

    return true;
}
