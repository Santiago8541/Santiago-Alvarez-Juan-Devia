package co.edu.uniquindio;

/**
 * Clase que representa un empleado de ventas
 * Hereda de Empleado
 */
public class EmpleadoVentas extends Empleado {

    // Atributos propios
    private float totalVentas;
    private float porcentajeComision;

    // Constructor
    public EmpleadoVentas(String nombre, String documento, int edad, float salarioBase,
                          CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension,
                          float totalVentas, float porcentajeComision) {

        super(nombre, documento, edad, salarioBase, categoria, descuentoSalud, descuentoPension);

        // Validaciones propias
        validarTotalVentas(totalVentas);
        validarPorcentajeComision(porcentajeComision);

        this.totalVentas = totalVentas;
        this.porcentajeComision = porcentajeComision;
    }

    // ==================== MÉTODOS PROPIOS ====================

    public float calcularComision() {
        return totalVentas * (porcentajeComision / 100);
    }

    // ==================== MÉTODOS SOBRESCRITOS ====================

    @Override
    public float calcularSalarioBruto() {
        float bonificacion = calcularBonificacionCategoria();
        float comision = calcularComision();
        return salarioBase + bonificacion + comision;
    }

    @Override
    protected void mostrarInformacionAdicional() {
        System.out.println("───────────────────────────────────────────");
        System.out.println("    INFORMACIÓN ADICIONAL - VENTAS");
        System.out.println("───────────────────────────────────────────");
        System.out.printf("Total Ventas:      $%.2f%n", totalVentas);
        System.out.printf("% Comisión:        %.2f%%%n", porcentajeComision);
        System.out.printf("Comisión Ganada:   $%.2f%n", calcularComision());
    }

    @Override
    public String getTipoEmpleado() {
        return "Empleado de Ventas";
    }

    // ==================== VALIDACIONES ====================

    private void validarTotalVentas(float totalVentas) {
        if (totalVentas < 0) {
            throw new IllegalArgumentException("El total de ventas no puede ser negativo");
        }
    }

    private void validarPorcentajeComision(float porcentaje) {
        if (porcentaje < 0 || porcentaje > 100) {
            throw new IllegalArgumentException("El porcentaje de comisión debe estar entre 0 y 100");
        }
    }

    // ==================== GETTERS ====================

    public float getTotalVentas() {
        return totalVentas;
    }

    public float getPorcentajeComision() {
        return porcentajeComision;
    }

    // ==================== SETTERS ====================

    public void setTotalVentas(float totalVentas) {
        validarTotalVentas(totalVentas);
        this.totalVentas = totalVentas;
    }

    public void setPorcentajeComision(float porcentajeComision) {
        validarPorcentajeComision(porcentajeComision);
        this.porcentajeComision = porcentajeComision;
    }
}
