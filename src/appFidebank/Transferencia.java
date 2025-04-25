
package appFidebank;

public class Transferencia extends Transaccion {
    private Cuenta cuentaDestino;

    public Transferencia(Cuenta cuentaOrigen, Cuenta cuentaDestino, double monto) {
        super(cuentaOrigen, monto, "Transferencia");
        this.cuentaDestino = cuentaDestino;
    }

    @Override
    public void ejecutar() {
        if (cuentaOrigen.transferir(cuentaDestino, monto)) {
            cuentaDestino.depositar(monto);
        }
    }
}