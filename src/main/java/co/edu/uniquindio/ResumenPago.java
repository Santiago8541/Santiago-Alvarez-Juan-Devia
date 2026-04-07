package co.edu.uniquindio;

/**
 * Record que almacena el resumen de pago de un empleado
 * Archivo independiente: ResumenPago.java
 */
public record ResumenPago(
        String documento,
        String nombre,
        String tipoEmpleado,
        float salarioBruto,
        float descuentos,
        float salarioNeto
) {

    // Constructor compacto con validaciones
    public ResumenPago {
        if (documento == null || documento.isBlank()) {
            throw new IllegalArgumentException("El documento no puede estar vacío");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (salarioBruto < 0) {
            throw new IllegalArgumentException("El salario bruto no puede ser negativo");
        }
        if (descuentos < 0) {
            throw new IllegalArgumentException("Los descuentos no pueden ser negativos");
        }
    }

    // Método para mostrar resumen formateado
    public void mostrarResumen() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return String.format(
                """
                
                │         RESUMEN DE PAGO                  │
                
                │ Documento:      %-24s │
                │ Nombre:         %-24s │
                │ Tipo Empleado:  %-24s │
                │ Salario Bruto:  $%-23.2f │
                │ Descuentos:     $%-23.2f │
                │ Salario Neto:   $%-23.2f │
                
                """,
                documento, nombre, tipoEmpleado, salarioBruto, descuentos, salarioNeto
        );
    }
}
