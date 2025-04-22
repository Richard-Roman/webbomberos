package bomberos.tarapoto.webbomberos.inventario.model.enums;

public enum resultadoRevision {
    APROBADO("APROBADO"),
    DESAPROBADO("DESAPROBADO"),
    OBSERVADO("OBSERVADO");

    private final String resultadoRevision;

    resultadoRevision(String resultadoRevision) {
        this.resultadoRevision = resultadoRevision;
    }

    public String getResultadoRevision() {
        return resultadoRevision;
    }

    
}
