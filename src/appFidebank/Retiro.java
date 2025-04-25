
package appFidebank;

public class Retiro extends Transaccion {
    public Retiro(Cuenta cuentaOrigen, double monto) {
        super(cuentaOrigen, monto, "Retiro");
    }

    @Override
    public void ejecutar() {
        cuentaOrigen.retirar(monto);
    }
}