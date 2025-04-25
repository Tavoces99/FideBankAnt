
package appFidebank;

public class CuentaAhorros extends Cuenta {
    private double tasaInteres;

    public CuentaAhorros(int numCuenta, Cliente titular, double tasaInteres) {
        super(numCuenta, titular);
        this.tasaInteres = tasaInteres;
    }

    public void aplicarIntereses() {
        saldo += saldo * tasaInteres;
    }
}