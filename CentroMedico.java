import java.util.ArrayList;
import java.util.Scanner;

public class CentroMedico {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int contNinos = 0;
        int contAdolescentes = 0;
        int contAdultos = 0;
        int contAdultosMayores = 0;

        double dineroRecaudado = 0;
        double descuentosAplicados = 0;

        // Listas para guardar el historial de la sesión y mostrarlo al final
        ArrayList<String> historialCategorias = new ArrayList<>();
        ArrayList<Double> historialCostosOriginales = new ArrayList<>();
        ArrayList<Double> historialDescuentos = new ArrayList<>();
        ArrayList<Double> historialTotales = new ArrayList<>();

        char continuar = 's';

        System.out.println("=== SISTEMA DE REGISTRO - CENTRO MEDICO ===");

        while (continuar == 's' || continuar == 'S') {
            int edad;
            double costo;

            // Validar la edad
            do {
                System.out.print("\nIngresa la edad del paciente: ");
                edad = scanner.nextInt();

                if (edad < 0) {
                    System.out.println("Error: La edad no puede ser negativa.");
                }
            } while (edad < 0);

            // Validar el costo de la consulta
            do {
                System.out.print("Ingresa el costo de la consulta: ");
                costo = scanner.nextDouble();

                if (costo <= 0) {
                    System.out.println("Error: El costo debe ser mayor que cero.");
                }
            } while (costo <= 0);

            double descuento = 0;
            String categoria = "";

            // Clasificar al paciente por edad
            if (edad < 12) {
                contNinos++;
                categoria = "Niño";
            } else if (edad <= 17) {
                contAdolescentes++;
                categoria = "Adolescente";
            } else if (edad <= 59) {
                contAdultos++;
                categoria = "Adulto";
            } else {
                contAdultosMayores++;
                categoria = "Adulto Mayor";
                descuento = costo * 0.15;
            }

            double costoFinal = costo - descuento;

            // Acumular los datos financieros y globales
            dineroRecaudado += costoFinal;
            descuentosAplicados += descuento;

            // Guardar en el historial para el reporte final
            historialCategorias.add(categoria + " (" + edad + " años)");
            historialCostosOriginales.add(costo);
            historialDescuentos.add(descuento);
            historialTotales.add(costoFinal);

            System.out.print("¿Deseas registrar otro paciente? (s/n): ");
            continuar = scanner.next().charAt(0);
        }

        System.out.println("\n==========================================");
        System.out.println("          REPORTE GENERAL DEL DIA          ");
        System.out.println("==========================================");

        int totalPacientes = historialCategorias.size();

        if (totalPacientes > 0) {
            System.out.println("\n--- Detalle de Pacientes Atendidos ---");
            for (int i = 0; i < totalPacientes; i++) {
                System.out.printf("Paciente %d: %-22s | Original: $%6.2f | Desc: $%5.2f | Pagó: $%6.2f%n",
                        (i + 1),
                        historialCategorias.get(i),
                        historialCostosOriginales.get(i),
                        historialDescuentos.get(i),
                        historialTotales.get(i));
            }

            System.out.println("\n--- Conteo por Categoría ---");
            System.out.println("• Niños (< 12 años): " + contNinos);
            System.out.println("• Adolescentes (12 a 17 años): " + contAdolescentes);
            System.out.println("• Adultos (18 a 59 años): " + contAdultos);
            System.out.println("• Adultos mayores (60 o más): " + contAdultosMayores);
            System.out.println("• Total de pacientes atendidos: " + totalPacientes);

            System.out.println("\n--- Balance Financiero ---");
            System.out.printf("• Total de descuentos aplicados: $%.2f%n", descuentosAplicados);
            System.out.printf("• Dinero total recaudado: $%.2f%n", dineroRecaudado);
            System.out.printf("• Promedio pagado por paciente: $%.2f%n", (dineroRecaudado / totalPacientes));
        } else {
            System.out.println("No se registraron pacientes en el día.");
        }

        System.out.println("==========================================");
        scanner.close();
    }
}