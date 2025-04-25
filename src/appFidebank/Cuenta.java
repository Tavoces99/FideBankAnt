
package appFidebank;

import java.io.Serializable;

public abstract class Cuenta implements Serializable {
    private static final long serialVersionUID = 1L;
    protected int numCuenta;
    protected double saldo;
    protected Cliente titular;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getNumCuenta() {
        return numCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getTitular() {
        return titular;
    }

    public Cuenta(int numCuenta, Cliente titular) {
        this.numCuenta = numCuenta;
        this.titular = titular;
        this.saldo = 0;
    }

    public void depositar(double monto) {
        saldo += monto;
    }

    public boolean retirar(double monto) {
        if (saldo >= monto) {
            saldo -= monto;
            return true;
        }
        return false;
    }

    public boolean transferir(Cuenta destino, double monto) {
        if (retirar(monto)) {
            destino.depositar(monto);
            return true;
        }
        return false;
    }
}