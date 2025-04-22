package bomberos.tarapoto.webbomberos.inventario.model.enums;

public enum estadoMantenimiento {
    EN_PROCESO("EN PROCESO"),
    FINALIZADO("FINALIZADO"),
    CANCELADO("CANCELADO");

    private final String estado;

    estadoMantenimiento(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
    public static estadoMantenimiento fromString(String estado) {
        for (estadoMantenimiento em : estadoMantenimiento.values()) {
            if (em.estado.equalsIgnoreCase(estado)) {
                return em;
            }
        }
        throw new IllegalArgumentException("No enum constant " + estado);
    }
}
