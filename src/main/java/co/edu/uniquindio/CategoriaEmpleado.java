package co.edu.uniquindio;

/**
 * Enum que representa las categorías de empleados
 * Archivo independiente: CategoriaEmpleado.java
 */
public enum CategoriaEmpleado {

    JUNIOR(5.0f),
    SEMI_SENIOR(10.0f),
    SENIOR(15.0f);

    private final float porcentajeBonificacion;

    // Constructor del enum
    CategoriaEmpleado(float porcentajeBonificacion) {
        this.porcentajeBonificacion = porcentajeBonificacion;
    }

    // Getter para obtener el porcentaje de bonificación
    public float getPorcentajeBonificacion() {
        return porcentajeBonificacion;
    }

    // Método para obtener descripción legible
    public String getDescripcion() {
        return switch (this) {
            case JUNIOR -> "Junior (5% bonificación)";
            case SEMI_SENIOR -> "Semi-Senior (10% bonificación)";
            case SENIOR -> "Senior (15% bonificación)";
        };
    }

    @Override
    public String toString() {
        return name() + " (" + porcentajeBonificacion + "%)";
    }
}
