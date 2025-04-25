
package appFidebank;

public class Deposito extends Transaccion {
    public Deposito(Cuenta cuentaOrigen, double monto) {
        super(cuentaOrigen, monto, "Depósito");
    }

    @Override
    public void ejecutar() {
        cuentaOrigen.depositar(monto);
    }
}