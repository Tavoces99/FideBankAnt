
package appFidebank;

public class Sesion {
    public static int cedula;
    public static String nombre;
    public static int numCuenta;
    public static double saldo;
    
    public static void cerrarSesion() {
        cedula = 0;
        nombre = null;
        numCuenta = 0;
        saldo = 0;
    }
}
