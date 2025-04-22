package bomberos.tarapoto.webbomberos.inventario.model.enums;

public enum estadoSeguro {
    VIGENTE("VIGENTE"),
    VENCIDO("VENCIDO"),
    POR_VENCER("POR VENCER");

    private final String estadoSeguro;

    estadoSeguro(String estadoSeguro) {
        this.estadoSeguro = estadoSeguro;
    }

    public String getEstadoSeguro() {
        return estadoSeguro;
    }
}
