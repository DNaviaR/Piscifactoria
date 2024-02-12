package helper;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Gestiona la obtención de datos por parte del usuario.
 * @author Adrián
 */
public final class InputHelper {
    /** El <code>Scanner</code> de entrada */
    private static Scanner sc = new Scanner(System.in);

    /**
     * Solicita al usuario una opción numérica dentro de un rango
     * @param min La opción mínima.
     * @param max La opción máxima.
     * @return La opción seleccionada por el usuario dentro del rango permitido.
     */
    public static int inputOption(int min, int max)
    {
        return inputOption(min, max, "Introduzca un valor entre " + min + " y " + max + ".");
    }

    /**
     * Solicita al usuario una opción numérica en un rango desde 0 a un máximo.
     * @param max La opción máxima.
     * @return La opción seleccionada por el usuario dentro del rango permitido.
     */
    public static int inputOption(int max)
    {
        return inputOption(0, max, "Introduzca un valor entre 0 y " + max + ".");
    }

    /**
     * Solicita al usuario una opción numérica en un rango desde 0 a un máximo.
     * @param max La opción máxima.
     * @param err El texto de error a mostrar.
     * @return La opción seleccionada por el usuario dentro del rango permitido.
     */
    public static int inputOption(int max, String err)
    {
        return inputOption(0, max, err);
    }

    /**
     * Solicita al usuario una opción numérica dentro de un rango
     * @param min La opción mínima.
     * @param max La opción máxima.
     * @param err El texto de error a mostrar.
     * @return La opción seleccionada por el usuario dentro del rango permitido.
     */
    public static int inputOption(int min, int max, String err)
    {
        int op = Integer.MIN_VALUE;
        while(op == Integer.MIN_VALUE) {
            try {
                op = sc.nextInt();
                if(op < min || op > max)
                {
                    System.out.println(err);
                    op = Integer.MIN_VALUE;
                }
            }catch(InputMismatchException ex){
                System.out.println(err);
                op = Integer.MIN_VALUE;
                sc.next();
            }
        }
        sc.nextLine();
        return op;
    }

    /**
     * Solicita al usuario una opción numérica dentro de un rango más las añadidas
     * @param min La opción mínima.
     * @param max La opción máxima.
     * @param extra Los valores añadidos.
     * @return La opción seleccionada por el usuario dentro del rango permitido.
     */
    public static int inputMenu(int min, int max, int[] extra)
    {
        return inputMenu(min, max, extra, "Introduzca un valor entre " + min + " y " + max + ".");
    }

    /**
     * Solicita al usuario una opción numérica desde 0 a un máximo dado más las añadidas
     * @param max La opción máxima.
     * @param extra Los valores añadidos.
     * @return La opción seleccionada por el usuario dentro del rango permitido.
     */
    public static int inputMenu(int max, int[] extra)
    {
        return inputMenu(0, max, extra, "Introduzca un valor entre 0 y " + max + ".");
    }

    /**
     * Solicita al usuario una opción numérica desde 0 a un máximo dado más las añadidas
     * @param max La opción máxima.
     * @param extra Los valores añadidos.
     * @param err El texto de error a mostrar.
     * @return La opción seleccionada por el usuario dentro del rango permitido.
     */
    public static int inputMenu(int max, int[] extra, String err)
    {
        return inputMenu(0, max, extra, err);
    }

    /**
     * Solicita al usuario una opción numérica dentro de un rango más las añadidas
     * @param min La opción mínima.
     * @param max La opción máxima.
     * @param extra Los valores añadidos.
     * @param err El texto de error a mostrar.
     * @return La opción seleccionada por el usuario dentro del rango permitido.
     */
    public static int inputMenu(int min, int max, int[] extra, String err)
    {
        int op = Integer.MIN_VALUE;
        while(op == Integer.MIN_VALUE) {
            try {
                op = sc.nextInt();
                if((op < min || op > max) && !inExtra(extra, op))
                {
                    System.out.println(err);
                    op = Integer.MIN_VALUE;
                }
            }catch(InputMismatchException ex){
                System.out.println(err);
                op = Integer.MIN_VALUE;
                sc.next();
            }
        }
        sc.nextLine();
        return op;
    }

    /**
     * Verifica si existe el valor.
     * @param extra El conjunto de valores.
     * @param op El valor a buscar
     * @return Si el valor existe.
     */
    private static boolean inExtra(int[] extra, int op)
    {
        for(int v : extra)
        {
            if(v == op)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Cierra el <code>Scanner</code>.
     */
    public static void close()
    {
        InputHelper.sc.close();
    }
}
