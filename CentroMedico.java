
import java.util.Scanner;

public class CentroMedico {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int contNinos = 0;
        int contAdolescentes = 0;
        int contAdultos = 0;
        int contAdultosMayores = 0;
        int totalPacientes = 0;

        double dineroRecaudado = 0;
        double descuentosAplicados = 0;

        char continuar = 's';

        while (continuar == 's' || continuar == 'S') {
            int edad;
            double costo;

            // Validar la edad
            do {
                System.out.print("Ingresa la edad del paciente: ");
                edad = scanner.nextInt();

                if (edad < 0) {
                    System.out.println(
                            "Error: La edad no puede ser negativa."
                    );
                }
            } while (edad < 0);

            // Validar el costo de la consulta
            do {
                System.out.print("Ingresa el costo de la consulta: ");
                costo = scanner.nextDouble();

                if (costo <= 0) {
                    System.out.println(
                            "Error: El costo debe ser mayor que cero."
                    );
                }
            } while (costo <= 0);

            double descuento = 0;

            // Clasificar al paciente por edad
            if (edad < 12) {
                contNinos++;
            } else if (edad <= 17) {
                contAdolescentes++;
            } else if (edad <= 59) {
                contAdultos++;
            } else {
                contAdultosMayores++;
                descuento = costo * 0.15;
            }

            // Calcular el costo final
            double costoFinal = costo - descuento;

            // Acumular los datos
            totalPacientes++;
            dineroRecaudado += costoFinal;
            descuentosAplicados += descuento;

            // Mostrar el resultado de la consulta
            System.out.printf(
                    "Descuento aplicado: $%.2f%n",
                    descuento
            );
            System.out.printf(
                    "Costo final a pagar: $%.2f%n",
                    costoFinal
            );

            System.out.print(
                    "¿Deseas registrar otro paciente? (s/n): "
            );
            continuar = scanner.next().charAt(0);
        }

        // Mostrar el resumen del día
        System.out.println("\n--- RESUMEN DEL DIA ---");
        System.out.println(
                "Pacientes niños (menores de 12): " + contNinos
        );
        System.out.println(
                "Pacientes adolescentes (12 a 17): " + contAdolescentes
        );
        System.out.println(
                "Pacientes adultos (18 a 59): " + contAdultos
        );
        System.out.println(
                "Pacientes adultos mayores (60 o más): "
                        + contAdultosMayores
        );

        System.out.printf(
                "Dinero total recaudado: $%.2f%n",
                dineroRecaudado
        );
        System.out.printf(
                "Total de descuentos aplicados: $%.2f%n",
                descuentosAplicados
        );

        if (totalPacientes > 0) {
            double promedioPagado =
                    dineroRecaudado / totalPacientes;

            System.out.printf(
                    "Promedio pagado por paciente: $%.2f%n",
                    promedioPagado
            );
        } else {
            System.out.println("No se registraron pacientes.");
        }

        scanner.close();
    }
}
