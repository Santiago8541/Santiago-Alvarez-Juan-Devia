package co.edu.uniquindio;

/**
 * Clase que representa un empleado temporal
 * Hereda de Empleado
 */
public class EmpleadoTemporal extends Empleado {

    // Atributos propios
    private int diasTrabajados;
    private float valorDia;

    // Constructor
    public EmpleadoTemporal(String nombre, String documento, int edad, float salarioBase,
                            CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension,
                            int diasTrabajados, float valorDia) {

        super(nombre, documento, edad, salarioBase, categoria, descuentoSalud, descuentoPension);

        // Validaciones propias
        validarDiasTrabajados(diasTrabajados);
        validarValorDia(valorDia);

        this.diasTrabajados = diasTrabajados;
        this.valorDia = valorDia;
    }

    // ==================== MÉTODOS PROPIOS ====================

    public float calcularPagoDiasTrabajados() {
        return diasTrabajados * valorDia;
    }

    // ==================== MÉTODOS SOBRESCRITOS ====================

    @Override
    public float calcularSalarioBruto() {
        float pagoDias = calcularPagoDiasTrabajados();
        float bonificacion = calcularBonificacionCategoria();
        return pagoDias + bonificacion;
    }

    @Override
    protected void mostrarInformacionAdicional() {
        System.out.println("───────────────────────────────────────────");
        System.out.println("    INFORMACIÓN ADICIONAL - TEMPORAL");
        System.out.println("───────────────────────────────────────────");
        System.out.println("Días Trabajados:   " + diasTrabajados);
        System.out.printf("Valor por Día:     $%.2f%n", valorDia);
        System.out.printf("Pago por Días:     $%.2f%n", calcularPagoDiasTrabajados());
    }

    @Override
    public String getTipoEmpleado() {
        return "Empleado Temporal";
    }

    // ==================== VALIDACIONES ====================

    private void validarDiasTrabajados(int dias) {
        if (dias < 0) {
            throw new IllegalArgumentException("Los días trabajados no pueden ser negativos");
        }
    }

    private void validarValorDia(float valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("El valor por día no puede ser negativo");
        }
    }

    // ==================== GETTERS ====================

    public int getDiasTrabajados() {
        return diasTrabajados;
    }

    public float getValorDia() {
        return valorDia;
    }

    // ==================== SETTERS ====================

    public void setDiasTrabajados(int diasTrabajados) {
        validarDiasTrabajados(diasTrabajados);
        this.diasTrabajados = diasTrabajados;
    }

    public void setValorDia(float valorDia) {
        validarValorDia(valorDia);
        this.valorDia = valorDia;
    }
}
