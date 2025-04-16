function openModal(modalId) {
        document.getElementById(modalId).style.display = "block";
    }

    function closeModal(modalId) {
        document.getElementById(modalId).style.display = "none";
    }

    // Evento para botón "+ Asignar Nuevo"
    document.addEventListener('DOMContentLoaded', () => {
        document.getElementById("FormRegist").addEventListener("click", () => {
            openModal('modalRegistrar');
        });

        // Delegación para enlaces de visualizar y editar
        document.querySelectorAll(".accion").forEach(el => {
            el.addEventListener("click", function (event) {
                event.preventDefault();
                const href = this.getAttribute("href");
                if (href.includes("viewuser")) {
                    openModal('modalVisualizar');
                    // Aquí puedes cargar datos si lo deseas con AJAX
                } else if (href.includes("edituser")) {
                    openModal('modalEditar');
                    // Aquí también podrías cargar datos dinámicamente
                }
            });
        });
    });