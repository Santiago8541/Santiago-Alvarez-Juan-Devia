package co.edu.uniquindio;

import java.util.Scanner;

/**
 * Clase principal con menú interactivo
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Empresa empresa = new Empresa("TechCorp S.A.");

    public static void main(String[] args) {

        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║     SISTEMA DE GESTIÓN DE NÓMINA DE EMPLEADOS             ║");
        System.out.println("║                  " + empresa.getNombre() + "                          ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");

        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("➤ Seleccione una opción: ");
            procesarOpcion(opcion);
        } while (opcion != 0);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n┌───────────────────────────────────────────────────────────┐");
        System.out.println("│                    MENÚ PRINCIPAL                         │");
        System.out.println("├───────────────────────────────────────────────────────────┤");
        System.out.println("│  1. Agregar empleado de planta                            │");
        System.out.println("│  2. Agregar empleado de ventas                            │");
        System.out.println("│  3. Agregar empleado temporal                             │");
        System.out.println("│  4. Mostrar todos los empleados                           │");
        System.out.println("│  5. Buscar empleado por documento                         │");
        System.out.println("│  6. Mostrar empleado con mayor salario neto               │");
        System.out.println("│  7. Mostrar nómina total                                  │");
        System.out.println("│  8. Mostrar resumen de pagos (Record)                     │");
        System.out.println("│  9. Mostrar estadísticas                                  │");
        System.out.println("│  0. Salir                                                 │");
        System.out.println("└───────────────────────────────────────────────────────────┘");
    }

    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> agregarEmpleadoPlanta();
            case 2 -> agregarEmpleadoVentas();
            case 3 -> agregarEmpleadoTemporal();
            case 4 -> empresa.mostrarTodosEmpleados();
            case 5 -> buscarEmpleado();
            case 6 -> empresa.mostrarEmpleadoMayorSalario();
            case 7 -> empresa.mostrarNominaTotal();
            case 8 -> empresa.mostrarResumenesPago();
            case 9 -> empresa.mostrarEstadisticas();
            case 0 -> System.out.println("\n¡Gracias por usar el sistema! Hasta pronto. 👋");
            default -> System.out.println("\n⚠ Opción no válida. Intente nuevamente.");
        }
    }

    // ==================== AGREGAR EMPLEADOS ====================

    private static void agregarEmpleadoPlanta() {
        System.out.println("\n══════ AGREGAR EMPLEADO DE PLANTA ══════\n");

        try {
            // Datos comunes
            String nombre = leerTexto("Nombre: ");
            String documento = leerTexto("Documento: ");
            int edad = leerEntero("Edad: ");
            float salarioBase = leerFlotante("Salario base: $");
            CategoriaEmpleado categoria = leerCategoria();
            float descuentoSalud = leerFlotante("Descuento salud (%): ");
            float descuentoPension = leerFlotante("Descuento pensión (%): ");

            // Datos específicos
            String cargo = leerTexto("Cargo: ");
            int horasExtra = leerEntero("Horas extra: ");
            float valorHoraExtra = leerFlotante("Valor hora extra: $");
            float auxilioTransporte = leerFlotante("Auxilio transporte: $");

            EmpleadoPlanta empleado = new EmpleadoPlanta(
                    nombre, documento, edad, salarioBase, categoria,
                    descuentoSalud, descuentoPension, cargo, horasExtra,
                    valorHoraExtra, auxilioTransporte
            );

            empresa.agregarEmpleado(empleado);

        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    private static void agregarEmpleadoVentas() {
        System.out.println("\n══════ AGREGAR EMPLEADO DE VENTAS ══════\n");

        try {
            // Datos comunes
            String nombre = leerTexto("Nombre: ");
            String documento = leerTexto("Documento: ");
            int edad = leerEntero("Edad: ");
            float salarioBase = leerFlotante("Salario base: $");
            CategoriaEmpleado categoria = leerCategoria();
            float descuentoSalud = leerFlotante("Descuento salud (%): ");
            float descuentoPension = leerFlotante("Descuento pensión (%): ");

            // Datos específicos
            float totalVentas = leerFlotante("Total ventas: $");
            float porcentajeComision = leerFlotante("Porcentaje comisión (%): ");

            EmpleadoVentas empleado = new EmpleadoVentas(
                    nombre, documento, edad, salarioBase, categoria,
                    descuentoSalud, descuentoPension, totalVentas, porcentajeComision
            );

            empresa.agregarEmpleado(empleado);

        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    private static void agregarEmpleadoTemporal() {
        System.out.println("\n══════ AGREGAR EMPLEADO TEMPORAL ══════\n");

        try {
            // Datos comunes
            String nombre = leerTexto("Nombre: ");
            String documento = leerTexto("Documento: ");
            int edad = leerEntero("Edad: ");
            float salarioBase = leerFlotante("Salario base: $");
            CategoriaEmpleado categoria = leerCategoria();
            float descuentoSalud = leerFlotante("Descuento salud (%): ");
            float descuentoPension = leerFlotante("Descuento pensión (%): ");

            // Datos específicos
            int diasTrabajados = leerEntero("Días trabajados: ");
            float valorDia = leerFlotante("Valor por día: $");

            EmpleadoTemporal empleado = new EmpleadoTemporal(
                    nombre, documento, edad, salarioBase, categoria,
                    descuentoSalud, descuentoPension, diasTrabajados, valorDia
            );

            empresa.agregarEmpleado(empleado);

        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    // ==================== BUSCAR EMPLEADO ====================

    private static void buscarEmpleado() {
        System.out.println("\n══════ BUSCAR EMPLEADO ══════\n");

        String documento = leerTexto("Ingrese el documento: ");
        Empleado emp = empresa.buscarEmpleadoPorDocumento(documento);

        if (emp != null) {
            System.out.println("\n✓ Empleado encontrado:");
            emp.mostrarInformacion();
        } else {
            System.out.println("\n✗ No se encontró empleado con documento: " + documento);
        }
    }

    // ==================== UTILIDADES DE LECTURA ====================

    private static CategoriaEmpleado leerCategoria() {
        System.out.println("\nCategorías disponibles:");
        System.out.println("  1. JUNIOR (5% bonificación)");
        System.out.println("  2. SEMI_SENIOR (10% bonificación)");
        System.out.println("  3. SENIOR (15% bonificación)");

        int opcion;
        do {
            opcion = leerEntero("Seleccione categoría (1-3): ");
        } while (opcion < 1 || opcion > 3);

        return switch (opcion) {
            case 1 -> CategoriaEmpleado.JUNIOR;
            case 2 -> CategoriaEmpleado.SEMI_SENIOR;
            case 3 -> CategoriaEmpleado.SENIOR;
            default -> CategoriaEmpleado.JUNIOR;
        };
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠ Debe ingresar un número entero válido.");
            }
        }
    }

    private static float leerFlotante(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Float.parseFloat(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠ Debe ingresar un número válido.");
            }
        }
    }
}
