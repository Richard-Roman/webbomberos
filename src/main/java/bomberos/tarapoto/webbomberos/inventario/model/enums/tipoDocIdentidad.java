package bomberos.tarapoto.webbomberos.inventario.model.enums;

public enum tipoDocIdentidad {
    DNI("DNI"),
    CARNET_EXTRANJERIA("CARNET DE EXTRANJERIA"),
    PASAPORTE("PASAPORTE"),
    OTRO("OTRO");

    private final String tipoDocIdentidad;

    tipoDocIdentidad(String tipoDocIdentidad) {
        this.tipoDocIdentidad = tipoDocIdentidad;
    }

    public String getTipoDocIdentidad() {
        return tipoDocIdentidad;
    }

}
