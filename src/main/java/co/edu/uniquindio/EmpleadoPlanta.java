package co.edu.uniquindio;

/**
 * Clase que representa un empleado de planta
 * Hereda de Empleado
 */
public class EmpleadoPlanta extends Empleado {

    // Atributos propios
    private String cargo;
    private int horasExtra;
    private float valorHoraExtra;
    private float auxilioTransporte;

    // Constructor
    public EmpleadoPlanta(String nombre, String documento, int edad, float salarioBase,
                          CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension,
                          String cargo, int horasExtra, float valorHoraExtra, float auxilioTransporte) {

        super(nombre, documento, edad, salarioBase, categoria, descuentoSalud, descuentoPension);

        // Validaciones propias
        validarCargo(cargo);
        validarHorasExtra(horasExtra);
        validarValorHoraExtra(valorHoraExtra);
        validarAuxilioTransporte(auxilioTransporte);

        this.cargo = cargo;
        this.horasExtra = horasExtra;
        this.valorHoraExtra = valorHoraExtra;
        this.auxilioTransporte = auxilioTransporte;
    }

    // ==================== MÉTODOS SOBRESCRITOS ====================

    @Override
    public float calcularSalarioBruto() {
        float pagoHorasExtra = horasExtra * valorHoraExtra;
        float bonificacion = calcularBonificacionCategoria();
        return salarioBase + bonificacion + pagoHorasExtra + auxilioTransporte;
    }

    @Override
    protected void mostrarInformacionAdicional() {
        System.out.println("───────────────────────────────────────────");
        System.out.println("    INFORMACIÓN ADICIONAL - PLANTA");
        System.out.println("───────────────────────────────────────────");
        System.out.println("Cargo:             " + cargo);
        System.out.println("Horas Extra:       " + horasExtra);
        System.out.printf("Valor Hora Extra:  $%.2f%n", valorHoraExtra);
        System.out.printf("Pago Horas Extra:  $%.2f%n", horasExtra * valorHoraExtra);
        System.out.printf("Auxilio Transporte:$%.2f%n", auxilioTransporte);
    }

    @Override
    public String getTipoEmpleado() {
        return "Empleado de Planta";
    }

    // ==================== VALIDACIONES ====================

    private void validarCargo(String cargo) {
        if (cargo == null || cargo.trim().isEmpty()) {
            throw new IllegalArgumentException("El cargo no puede estar vacío");
        }
    }

    private void validarHorasExtra(int horasExtra) {
        if (horasExtra < 0) {
            throw new IllegalArgumentException("Las horas extra no pueden ser negativas");
        }
    }

    private void validarValorHoraExtra(float valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("El valor de la hora extra no puede ser negativo");
        }
    }

    private void validarAuxilioTransporte(float auxilio) {
        if (auxilio < 0) {
            throw new IllegalArgumentException("El auxilio de transporte no puede ser negativo");
        }
    }

    // ==================== GETTERS ====================

    public String getCargo() {
        return cargo;
    }

    public int getHorasExtra() {
        return horasExtra;
    }

    public float getValorHoraExtra() {
        return valorHoraExtra;
    }

    public float getAuxilioTransporte() {
        return auxilioTransporte;
    }

    // ==================== SETTERS ====================

    public void setCargo(String cargo) {
        validarCargo(cargo);
        this.cargo = cargo;
    }

    public void setHorasExtra(int horasExtra) {
        validarHorasExtra(horasExtra);
        this.horasExtra = horasExtra;
    }

    public void setValorHoraExtra(float valorHoraExtra) {
        validarValorHoraExtra(valorHoraExtra);
        this.valorHoraExtra = valorHoraExtra;
    }

    public void setAuxilioTransporte(float auxilioTransporte) {
        validarAuxilioTransporte(auxilioTransporte);
        this.auxilioTransporte = auxilioTransporte;
    }
}
