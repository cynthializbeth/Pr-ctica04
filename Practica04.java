import java.util.Random;

/**
 * Praćtica que representa una simulación de tráfico en una intersección de dos
 * calles.
 * Implementa la interfaz Runnable para poder correr la simulación usando tiempo
 * real.
 */
public class Practica04 implements Runnable {
    /* Entero que representa la cantidad de autos en la calle A */
    private static int calleA = 0;
    /* Entero que representa la cantidad de autos en la calle B */
    private static int calleB = 0;
    /* Entero que representa los segundos que han pasado */
    private static int segundos = 0;
    /* Booleano que indica si los autos de la calle A pasan */
    private static boolean pasoA = false;
    /* Contador de veces que se ha dado el paso a la calle A */
    private static int contadorA = 0;
    /* Contador de veces que se ha dado el paso a la calle B */
    private static int contadorB = 0;
    /* Constantes para los colores de la consola */
    private static String RESET = "\u001B[0m";
    private static String YELLOW = "\u001B[33m";
    private static String RED = "\u001B[31m";
    /* Variable tipo random para generar números aleatorios */
    private static Random random = new Random();

    /**
     * Método principal de la simulación.
     * Crea una instancia de la clase Practica04 y un hilo que representa el
     * semáforo para correr la simulación.
     *
     * @param args los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        Practica04 practica = new Practica04();
        calleA = random.nextInt(121) + 180;
        calleB = random.nextInt(121) + 180;
        System.out.println("INICIO");
        System.out.println("CALLE A: \n" + calleA + "\nCALLE B: \n" + calleB);
        Thread hilo = new Thread(practica, "semaforo");
        hilo.start();
    }

    /**
     * Método run que se ejecuta en el hilo del semáforo.
     * Imprime el segundo actual de la simulación y el estado actual de las calles.
     * Determina si los autos de la calle A o B tienen el paso cada 5 segundos.
     */
    public void run() {
        while (segundos <= 60) {
            System.out.println(YELLOW + "SEGUNDO: " + segundos++ + RESET);
            imprimeSimulacion();
            if (segundos % 5 == 0) {
                int avanzan = random.nextInt(4) + 7;
                if ((calleA >= calleB || contadorB >= 2) && contadorA < 2) {
                    System.out.println("contador A: " + contadorA);
                    pasoA = true;
                    contadorA++;
                    System.out.println(RED + "******** SE DA EL PASO A LA CALLE A ********" + RESET);
                    calleA -= avanzan;
                    contadorB = 0;
                } else if (contadorB < 2) {
                    System.out.println("contador B: " + contadorB);
                    pasoA = false;
                    contadorB++;
                    System.out.println(RED + "******** SE DA EL PASO A LA CALLE B ********" + RESET);
                    calleB -= avanzan;
                    contadorA = 0;
                }
                System.out.println("Avanzan: " + avanzan);
            }
            // Esperar 1 segundo
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método que imprime la simulación de tráfico.
     * De manera aleatoria se determina cuántos autos avanzan y cuántos llegan a
     * cada calle.
     */
    public static void imprimeSimulacion() {
        System.out.println("Calle A: \n" + calleA);
        // Generar un número aleatorio entre 3 y 5 para los autos que llegan a la calle
        // A
        int nuevoValor = random.nextInt(3) + 3;
        calleA += nuevoValor;
        System.out.println("Llegan: " + nuevoValor);
        System.out.println("Total: " + calleA);
        System.out.println("Calle B: \n" + calleB);
        // Generar un número aleatorio entre 3 y 5 para los autos que llegan a la calle
        // B
        nuevoValor = random.nextInt(3) + 3;
        calleB += nuevoValor;
        System.out.println("Llegan: " + nuevoValor);
        System.out.println("Total: " + calleB);
    }
}
