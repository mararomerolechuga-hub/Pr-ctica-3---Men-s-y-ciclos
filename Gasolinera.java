import java.util.Scanner;

public class Gasolinera {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalCargas = 0;
        double totalLitrosVendidos = 0;
        double importeSinDescuentoTotal = 0;
        double totalCobrado = 0;
        int cargasConDescuento = 0;

        char continuar = 's';

        while (continuar == 's' || continuar == 'S') {
            double litros, precio;

            // Validar los litros
            do {
                System.out.print("Ingresa los litros cargados: ");
                litros = scanner.nextDouble();

                if (litros <= 0) {
                    System.out.println(
                            "Error: Los litros deben ser mayores que cero."
                    );
                }
            } while (litros <= 0);

            // Validar el precio
            do {
                System.out.print("Ingresa el precio por litro: ");
                precio = scanner.nextDouble();

                if (precio <= 0) {
                    System.out.println(
                            "Error: El precio debe ser mayor que cero."
                    );
                }
            } while (precio <= 0);

            double importeOriginal = litros * precio;

            double descuento = 0;

            // Descuento del 5% si la carga es de 40 litros o más
            if (litros >= 40) {
                descuento = importeOriginal * 0.05;
                cargasConDescuento++;
            }

            double importeFinal = importeOriginal - descuento;

            totalCargas++;
            totalLitrosVendidos += litros;
            importeSinDescuentoTotal += importeOriginal;
            totalCobrado += importeFinal;

            System.out.printf(
                    "Importe antes del descuento: $%.2f%n",
                    importeOriginal
            );
            System.out.printf(
                    "Descuento aplicado: $%.2f%n",
                    descuento
            );
            System.out.printf(
                    "Total a pagar: $%.2f%n",
                    importeFinal
            );

            System.out.print(
                    "¿Deseas registrar otra carga? (s/n): "
            );
            continuar = scanner.next().charAt(0);
        }

        System.out.println("\n--- RESUMEN FINAL ---");
        System.out.println(
                "Número de cargas registradas: " + totalCargas
        );
        System.out.printf(
                "Total de litros vendidos: %.2f%n",
                totalLitrosVendidos
        );
        System.out.println(
                "Cargas con descuento: " + cargasConDescuento
        );
        System.out.printf(
                "Importe total antes de descuentos: $%.2f%n",
                importeSinDescuentoTotal
        );
        System.out.printf(
                "Total cobrado: $%.2f%n",
                totalCobrado
        );

        if (totalCargas > 0) {
            double promedioPorCarga = totalCobrado / totalCargas;

            System.out.printf(
                    "Importe promedio por carga: $%.2f%n",
                    promedioPorCarga
            );
        } else {
            System.out.println("No se registraron cargas.");
        }

        scanner.close();
    }
}