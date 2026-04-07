package co.edu.uniquindio;

public abstract class Empleado {

    // Atributos protected
    protected String nombre;
    protected String documento;
    protected int edad;
    protected float salarioBase;
    protected CategoriaEmpleado categoria;
    protected float descuentoSalud;
    protected float descuentoPension;

    // Constructor
    public Empleado(String nombre, String documento, int edad, float salarioBase,
                    CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension) {

        // Validaciones
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (documento == null || documento.trim().isEmpty()) {
            throw new IllegalArgumentException("El documento no puede estar vacío");
        }
        if (edad <= 0) {
            throw new IllegalArgumentException("La edad debe ser mayor a 0");
        }
        if (salarioBase < 0) {
            throw new IllegalArgumentException("El salario base no puede ser negativo");
        }
        if (descuentoSalud < 0 || descuentoSalud > 100) {
            throw new IllegalArgumentException("El descuento de salud debe estar entre 0 y 100");
        }
        if (descuentoPension < 0 || descuentoPension > 100) {
            throw new IllegalArgumentException("El descuento de pensión debe estar entre 0 y 100");
        }

        this.nombre = nombre;
        this.documento = documento;
        this.edad = edad;
        this.salarioBase = salarioBase;
        this.categoria = categoria;
        this.descuentoSalud = descuentoSalud;
        this.descuentoPension = descuentoPension;
    }

    // Método abstracto - debe ser implementado por las clases hijas
    public abstract float calcularSalarioBruto();

    // Método para calcular la bonificación según la categoría
    public float calcularBonificacionCategoria() {
        return salarioBase * (categoria.getPorcentajeBonificacion() / 100);
    }

    // Método para calcular los descuentos (salud + pensión)
    public float calcularDescuentos() {
        float salarioBruto = calcularSalarioBruto();
        float descuentoSaludValor = salarioBruto * (descuentoSalud / 100);
        float descuentoPensionValor = salarioBruto * (descuentoPension / 100);
        return descuentoSaludValor + descuentoPensionValor;
    }

    // Método para calcular el salario neto
    public float calcularSalarioNeto() {
        return calcularSalarioBruto() - calcularDescuentos();
    }

    // Método para mostrar información del empleado
    public void mostrarInformacion() {
        System.out.println("═══════════════════════════════════════════");
        System.out.println("INFORMACIÓN DEL EMPLEADO");
        System.out.println("═══════════════════════════════════════════");
        System.out.println("Nombre:            " + nombre);
        System.out.println("Documento:         " + documento);
        System.out.println("Edad:              " + edad + " años");
        System.out.println("Categoría:         " + categoria);
        System.out.println("Tipo:              " + getTipoEmpleado());
        System.out.printf("Salario Base:      $%.2f%n", salarioBase);
        System.out.printf("Bonificación:      $%.2f%n", calcularBonificacionCategoria());
        System.out.printf("Salario Bruto:     $%.2f%n", calcularSalarioBruto());
        System.out.printf("Descuento Salud:   %.2f%%%n", descuentoSalud);
        System.out.printf("Descuento Pensión: %.2f%%%n", descuentoPension);
        System.out.printf("Total Descuentos:  $%.2f%n", calcularDescuentos());
        System.out.printf("Salario Neto:      $%.2f%n", calcularSalarioNeto());
        mostrarInformacionAdicional();
        System.out.println("═══════════════════════════════════════════");
    }

    // Método para mostrar información adicional específica de cada tipo
    protected abstract void mostrarInformacionAdicional();

    // Método para obtener el tipo de empleado
    public abstract String getTipoEmpleado();

    // Método para generar el resumen de pago usando el record
    public ResumenPago generarResumenPago() {
        return new ResumenPago(
                documento,
                nombre,
                getTipoEmpleado(),
                calcularSalarioBruto(),
                calcularDescuentos(),
                calcularSalarioNeto()
        );
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public int getEdad() {
        return edad;
    }

    public float getSalarioBase() {
        return salarioBase;
    }

    public CategoriaEmpleado getCategoria() {
        return categoria;
    }

    public float getDescuentoSalud() {
        return descuentoSalud;
    }

    public float getDescuentoPension() {
        return descuentoPension;
    }

    // Setters con validaciones
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public void setDocumento(String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            throw new IllegalArgumentException("El documento no puede estar vacío");
        }
        this.documento = documento;
    }

    public void setEdad(int edad) {
        if (edad <= 0) {
            throw new IllegalArgumentException("La edad debe ser mayor a 0");
        }
        this.edad = edad;
    }

    public void setSalarioBase(float salarioBase) {
        if (salarioBase < 0) {
            throw new IllegalArgumentException("El salario base no puede ser negativo");
        }
        this.salarioBase = salarioBase;
    }

    public void setCategoria(CategoriaEmpleado categoria) {
        this.categoria = categoria;
    }

    public void setDescuentoSalud(float descuentoSalud) {
        if (descuentoSalud < 0 || descuentoSalud > 100) {
            throw new IllegalArgumentException("El descuento de salud debe estar entre 0 y 100");
        }
        this.descuentoSalud = descuentoSalud;
    }

    public void setDescuentoPension(float descuentoPension) {
        if (descuentoPension < 0 || descuentoPension > 100) {
            throw new IllegalArgumentException("El descuento de pensión debe estar entre 0 y 100");
        }
        this.descuentoPension = descuentoPension;
    }
}
