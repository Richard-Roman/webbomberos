function validarFormulario() {
    const dni = document.getElementById("dni").value.trim();
    const nombres = document.getElementById("nombres").value.trim();
    const apellidos = document.getElementById("apellidos").value.trim();
    const fechaNacimiento = document.getElementById("fechaNacimiento").value;

    // Validar DNI
    if (!/^\d{8}$/.test(dni)) {
        alert("El DNI debe tener 8 dígitos numéricos.");
        return false;
    }

    // Validar nombre y apellido
    if (nombres.length < 2 || apellidos.length < 2) {
        alert("Nombres y apellidos deben tener al menos 2 caracteres.");
        return false;
    }

    // Validar fecha de nacimiento
    const hoy = new Date();
    const nacimiento = new Date(fechaNacimiento);
    if (nacimiento >= hoy) {
        alert("La fecha de nacimiento no puede ser hoy o futura.");
        return false;
    }

    // Validar teléfonos de emergencia
    const telefonos = document.querySelectorAll('.telefono-item');
    for (let i = 0; i < telefonos.length; i++) {
        const nombre = telefonos[i].querySelector('input[name^="telefonosEmergencia"][name$=".nombreContacto"]')?.value.trim();
        const parentesco = telefonos[i].querySelector('input[name^="telefonosEmergencia"][name$=".parentesco"]')?.value.trim();
        const telefono = telefonos[i].querySelector('input[name^="telefonosEmergencia"][name$=".telefono"]')?.value.trim();

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