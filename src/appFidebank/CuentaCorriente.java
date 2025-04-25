
package appFidebank;

public class CuentaCorriente extends Cuenta {
    private double limiteSobregiro;

    public CuentaCorriente(int numCuenta, Cliente titular, double limiteSobregiro) {
        super(numCuenta, titular);
        this.limiteSobregiro = limiteSobregiro;
    }

    public boolean permitirSobregiro(double monto) {
        return saldo + limiteSobregiro >= monto;
    }
}