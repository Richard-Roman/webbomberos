package bomberos.tarapoto.webbomberos.inventario.model.enums;

public enum tipoMantenimiento {
    PREVENTIVO("PREVENTIVO"),
    CORRECTIVO("CORRECTIVO"),
    MEJORATIVO("MEJORATIVO");

    private final String tipo;

    tipoMantenimiento(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public static tipoMantenimiento fromString(String tipo) {
        for (tipoMantenimiento tm : tipoMantenimiento.values()) {
            if (tm.tipo.equalsIgnoreCase(tipo)) {
                return tm;
            }
        }
        throw new IllegalArgumentException("No enum constant " + tipo);
    }
}
