function openModal(modalId) {
        document.getElementById(modalId).style.display = "block";
    }

function closeModal(modalId) {
    const modal = document.getElementById(modalId);
    const inputs = modal.querySelectorAll('input, textarea, select');
    let hayDatos = false;
    
    if(modalId === "modalVisualizar"){
        modal.style.display = "none";
        return;
    }

    inputs.forEach(input => {
        const type = input.type;
        const tag = input.tagName;
        if ((tag === 'INPUT' && (type === 'text' || type === 'email' || type === 'number' || type === 'password')) ||tag === 'TEXTAREA') 
        {
            if (input.value.trim() !== '') 
            {hayDatos = true;}
        }
        if (tag === 'SELECT') {
            if (input.selectedIndex > 0) 
            {hayDatos = true;}
        }
    });
    if (hayDatos) {
        Swal.fire({
            title: '¿Estás seguro?',
            text: 'Hay datos escritos. Si cierras, se perderán.',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sí, cerrar',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                modal.style.display = "none";
                inputs.forEach(input => {
                    if (input.tagName === 'INPUT' || input.tagName === 'TEXTAREA') {
                        input.value = '';
                    } else if (input.tagName === 'SELECT') {
                        input.selectedIndex = 0;
                    }
                });
            }
        });
    } else {
        modal.style.display = "none";
    }
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
            if (href.includes("visualizaruser")) {
                fetch(href)
                  .then(res => {
                    if (!res.ok) throw new Error("No se pudo cargar usuario");
                    return res.text();
                  })
                  .then(html => {
                    // 2) Inyectar en el modalVisualizar
                    const mc = document.querySelector("#modalVisualizar .modal-content");
                    mc.innerHTML = `
                      <span class="close" onclick="closeModal('modalVisualizar')">&times;</span>
                      ${html}
                    `;
                    // 3) Abrir
                    openModal('modalVisualizar');
                  })
                  .catch(err => {
                    console.error(err);
                    Swal.fire("Error", "No se pudieron cargar los datos del usuario", "error");
                  });
            } else if (href.includes("editaruser")) {
                fetch(href)
                  .then(res => {
                    if (!res.ok) throw new Error("No se pudo cargar usuario");
                    return res.text();
                  })
                  .then(html => {
                    // 2) Inyectar en el modalEditar
                    const mc = document.querySelector("#modalEditar .modal-content");
                    mc.innerHTML = `
                      <span class="close" onclick="closeModal('modalEditar')">&times;</span>
                      ${html}
                    `;
                    // 3) Abrir
                    openModal('modalEditar');
                  })
                  .catch(err => {
                    console.error(err);
                    Swal.fire("Error", "No se pudieron cargar los datos del usuario", "error");
                  });
            }
        });
    });
    document.querySelectorAll('.modal').forEach(modal => {
        modal.addEventListener('click', function (event) {
            if (event.target === modal) {
                closeModal(modal.id);;
            }
            });
        });
    });

    //seccion para mostrar los roles
    function toggleDropdown(btn) {
        const dropdown = btn
          .closest('.custom-dropdown')
          .querySelector('.dropdown-content');
        dropdown.classList.toggle('show');
      }

    // Cierra el dropdown si haces clic fuera la seccion de roles
    document.addEventListener('click', function(event) {
        document.querySelectorAll('.dropdown-content.show').forEach(menu => {
          const wrapper = menu.closest('.custom-dropdown');
          if (!wrapper.contains(event.target)) {
            menu.classList.remove('show');
          }
        });
      });