
package appFidebank;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private int cedula;
    private String pin;
    private ArrayList<Cuenta> cuentas;
    private ArrayList<Transaccion> historialTransaccion;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCedula() {
        return cedula;
    }

    public String getPin() {
        return pin;
    }

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public ArrayList<Transaccion> getHistorialTransaccion() {
        return historialTransaccion;
    }

    public Cliente(String nombre, int cedula, String pin) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.pin = pin;
        this.cuentas = new ArrayList<>();
        this.historialTransaccion = new ArrayList<>();
    }

    public boolean flagPIN(String notiPin) {
        return this.pin.equals(notiPin);
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public void registrarTransaccion(Transaccion transaccion) {
        historialTransaccion.add(transaccion);
    }
}