package bomberos.tarapoto.webbomberos.inventario.model.enums;

import java.util.List;

public enum categoria {
    VEHICULOS("VEHICULOS"),
    EQUIPOS("EQUIPOS"),
    HERRAMIENTAS("HERRAMIENTAS"),
    MATERIALES("MATERIALES"),
    OTROS("OTROS");

    private final String categoria;

    categoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

}
