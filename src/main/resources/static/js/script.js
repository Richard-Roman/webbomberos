
  function togglePassword() {
    const passwordInput = document.getElementById("password");
    const toggleText = document.querySelector(".toggle-password");
    if (passwordInput.type === "password") {
      passwordInput.type = "text";
      toggleText.textContent = "Ocultar";
    } else {
      passwordInput.type = "password";
      toggleText.textContent = "Mostrar";
    }
  }

  
  function validarLogin(event) {
    event.preventDefault(); // Prevenir que el formulario se envíe normal

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const errorMsg = document.getElementById("error-message");

    fetch('/auth/login2', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: username,
            password: password
        })
    })
    .then(response => {
        if (response.ok) {
            return response.json(); // Convertir la respuesta en JSON
        } else {
            throw new Error('Usuario o contraseña incorrectos');
        }
    })
    .then(data => {
        console.log('Login exitoso:', data);
        // Aquí puedes guardar el token que devuelve tu backend
        //localStorage.setItem('token', data.token); // Suponiendo que devuelve un campo llamado 'token'

        // Redirigir a la página principal o dashboard
        window.location.href = '/webbomberos/intranet/main.html';
    })
    .catch(error => {
        console.error('Error en login:', error);
        errorMsg.classList.remove("hidden"); // Mostrar error
    });

    return false; // Para que el form no recargue la página
}

  // Detectar si hay error en la URL (?error=1)
  const urlParams = new URLSearchParams(window.location.search);
  if (urlParams.get("error") === "1") {
    document.getElementById("mensaje-recuperacion").innerHTML = `
      <p class="error-message">Correo inválido</p>
      <p class="instruccion">Por favor intente nuevamente</p>
    `;
  }

  function logout() {
    fetch('/auth/logout', {
        method: 'GET',
    })
    .then(response => {
        if (response.redirected) {
            // Si el backend hace un redirect, seguimos esa URL
            window.location.href = response.url;
        }
    })
    .catch(error => {
        console.error('Error al cerrar sesión:', error);
    });
}