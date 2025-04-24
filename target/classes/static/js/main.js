let timeout;

// Detectar cualquier actividad del usuario
window.onload = resetTimer;
document.onmousemove = resetTimer;
document.onkeypress = resetTimer;
document.onclick = resetTimer;
document.onscroll = resetTimer;

function resetTimer() {
    clearTimeout(timeout);
    timeout = setTimeout(logout, 5 * 60 * 1000); // 5 minutos de inactividad
}

function logout() {
    window.location.href = '/webbomberos/auth/logout'; 
}

document.addEventListener("DOMContentLoaded", () => {
    const dropdowns = document.querySelectorAll(".dropdown");

    dropdowns.forEach(dropdown => {
        dropdown.addEventListener("click", () => {
            const submenu = dropdown.querySelector(".submenu");

            if (submenu) {
                submenu.classList.toggle("active");
            }

            dropdowns.forEach(otherDropdown => {
                if (otherDropdown !== dropdown) {
                    const otherSubmenu = otherDropdown.querySelector(".submenu");
                    if (otherSubmenu) {
                        otherSubmenu.classList.remove("active");
                    }
                }
            });

        });
    });
});


