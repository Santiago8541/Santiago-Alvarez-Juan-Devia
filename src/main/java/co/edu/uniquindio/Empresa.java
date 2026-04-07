package co.edu.uniquindio;

import java.util.ArrayList;

/**
 * Clase que administra los empleados de la empresa
 * Utiliza ArrayList para almacenar empleados
 */
public class Empresa {

    // Colección de empleados
    private ArrayList<Empleado> empleados;
    private String nombre;

    // Constructor
    public Empresa(String nombre) {
        this.nombre = nombre;
        this.empleados = new ArrayList<>();
    }

    // ==================== MÉTODOS CRUD ====================

    // Agregar empleado
    public boolean agregarEmpleado(Empleado empleado) {
        // Verificar duplicados por documento
        if (buscarEmpleadoPorDocumento(empleado.getDocumento()) != null) {
            System.out.println("✗ Error: Ya existe un empleado con documento " + empleado.getDocumento());
            return false;
        }
        empleados.add(empleado);
        System.out.println("✓ Empleado " + empleado.getNombre() + " agregado exitosamente.");
        return true;
    }

    // Buscar empleado por documento
    public Empleado buscarEmpleadoPorDocumento(String documento) {
        for (Empleado emp : empleados) {
            if (emp.getDocumento().equals(documento)) {
                return emp;
            }
        }
        return null;
    }

    // Eliminar empleado por documento
    public boolean eliminarEmpleado(String documento) {
        Empleado emp = buscarEmpleadoPorDocumento(documento);
        if (emp != null) {
            empleados.remove(emp);
            System.out.println("✓ Empleado eliminado exitosamente.");
            return true;
        }
        System.out.println("✗ No se encontró el empleado.");
        return false;
    }

    // ==================== MÉTODOS DE CONSULTA ====================

    // Mostrar todos los empleados
    public void mostrarTodosEmpleados() {
        if (empleados.isEmpty()) {
            System.out.println("\n⚠ No hay empleados registrados.");
            return;
        }

        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║           LISTADO DE EMPLEADOS - " + nombre);
        System.out.println("╠═══════════════════════════════════════════════════════════╣");
        System.out.println("║  Total de empleados: " + empleados.size());
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");

        for (int i = 0; i < empleados.size(); i++) {
            System.out.println("━━━ Empleado #" + (i + 1) + " ━━━");
            empleados.get(i).mostrarInformacion();
            System.out.println();
        }
    }

    // Obtener empleado con mayor salario
    public Empleado obtenerEmpleadoMayorSalario() {
        if (empleados.isEmpty()) {
            return null;
        }

        Empleado mayorSalario = empleados.get(0);

        for (Empleado emp : empleados) {
            if (emp.calcularSalarioNeto() > mayorSalario.calcularSalarioNeto()) {
                mayorSalario = emp;
            }
        }

        return mayorSalario;
    }

    // Mostrar empleado con mayor salario
    public void mostrarEmpleadoMayorSalario() {
        Empleado emp = obtenerEmpleadoMayorSalario();

        if (emp == null) {
            System.out.println("\n⚠ No hay empleados registrados.");
            return;
        }

        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║         EMPLEADO CON MAYOR SALARIO NETO                   ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");
        emp.mostrarInformacion();
    }

    // ==================== MÉTODOS DE NÓMINA ====================

    // Calcular nómina total
    public float calcularNominaTotal() {
        float total = 0;
        for (Empleado emp : empleados) {
            total += emp.calcularSalarioNeto();
        }
        return total;
    }

    // Mostrar nómina total
    public void mostrarNominaTotal() {
        if (empleados.isEmpty()) {
            System.out.println("\n⚠ No hay empleados registrados.");
            return;
        }

        float totalBruto = 0;
        float totalDescuentos = 0;
        float totalNeto = 0;

        for (Empleado emp : empleados) {
            totalBruto += emp.calcularSalarioBruto();
            totalDescuentos += emp.calcularDescuentos();
            totalNeto += emp.calcularSalarioNeto();
        }

        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║               NÓMINA TOTAL DE LA EMPRESA                  ║");
        System.out.println("╠═══════════════════════════════════════════════════════════╣");
        System.out.printf("║  Empresa:                  %-30s ║%n", nombre);
        System.out.printf("║  Total Empleados:          %-30d ║%n", empleados.size());
        System.out.println("╠═══════════════════════════════════════════════════════════╣");
        System.out.printf("║  Total Salarios Brutos:    $%-29.2f ║%n", totalBruto);
        System.out.printf("║  Total Descuentos:         $%-29.2f ║%n", totalDescuentos);
        System.out.printf("║  NÓMINA TOTAL (Neto):      $%-29.2f ║%n", totalNeto);
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
    }

    // ==================== MÉTODOS CON RECORD ====================

    // Mostrar resúmenes de pago usando el record
    public void mostrarResumenesPago() {
        if (empleados.isEmpty()) {
            System.out.println("\n⚠ No hay empleados registrados.");
            return;
        }

        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║         RESÚMENES DE PAGO (usando Record)                 ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");

        for (Empleado emp : empleados) {
            ResumenPago resumen = emp.generarResumenPago();
            System.out.println(resumen);
        }
    }

    // Obtener lista de resúmenes de pago
    public ArrayList<ResumenPago> obtenerResumenesPago() {
        ArrayList<ResumenPago> resumenes = new ArrayList<>();

        for (Empleado emp : empleados) {
            resumenes.add(emp.generarResumenPago());
        }

        return resumenes;
    }

    // ==================== ESTADÍSTICAS ====================

    public void mostrarEstadisticas() {
        if (empleados.isEmpty()) {
            System.out.println("\n⚠ No hay empleados registrados.");
            return;
        }

        int planta = 0, ventas = 0, temporal = 0;
        int junior = 0, semiSenior = 0, senior = 0;

        for (Empleado emp : empleados) {
            // Contar por tipo usando instanceof
            if (emp instanceof EmpleadoPlanta) planta++;
            else if (emp instanceof EmpleadoVentas) ventas++;
            else if (emp instanceof EmpleadoTemporal) temporal++;

            // Contar por categoría
            switch (emp.getCategoria()) {
                case JUNIOR -> junior++;
                case SEMI_SENIOR -> semiSenior++;
                case SENIOR -> senior++;
            }
        }

        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║               ESTADÍSTICAS DE EMPLEADOS                   ║");
        System.out.println("╠═══════════════════════════════════════════════════════════╣");
        System.out.println("║  POR TIPO:                                                ║");
        System.out.printf("║    • Empleados de Planta:    %-28d ║%n", planta);
        System.out.printf("║    • Empleados de Ventas:    %-28d ║%n", ventas);
        System.out.printf("║    • Empleados Temporales:   %-28d ║%n", temporal);
        System.out.println("╠═══════════════════════════════════════════════════════════╣");
        System.out.println("║  POR CATEGORÍA:                                           ║");
        System.out.printf("║    • Junior:                 %-28d ║%n", junior);
        System.out.printf("║    • Semi-Senior:            %-28d ║%n", semiSenior);
        System.out.printf("║    • Senior:                 %-28d ║%n", senior);
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
    }

    // ==================== GETTERS ====================

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadEmpleados() {
        return empleados.size();
    }
}
