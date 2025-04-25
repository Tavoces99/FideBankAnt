
package appFidebank;

import java.util.ArrayList;

public class Cajero {
    private String ubicacion;
    private ArrayList<Cliente> clientesRegistrados;

    public Cajero(String ubicacion) {
        this.ubicacion = ubicacion;
        this.clientesRegistrados = new ArrayList<>();
    }

    public Cliente autenticarCliente(int cedula, String pin) {
        for (Cliente cliente : clientesRegistrados) {
            if (cliente.getCedula() == cedula && cliente.flagPIN(pin)) {
                return cliente;
            }
        }
        return null;
    }

    public void realizarDeposito(Cliente cliente, int numCuenta, double monto) {
        for (Cuenta cuenta : cliente.getCuentas()) {
            if (cuenta.getNumCuenta() == numCuenta) {
                cuenta.depositar(monto);
                break;
            }
        }
    }

    public void realizarRetiro(Cliente cliente, int numCuenta, double monto) {
        for (Cuenta cuenta : cliente.getCuentas()) {
            if (cuenta.getNumCuenta() == numCuenta) {
                cuenta.retirar(monto);
                break;
            }
        }
    }

    public void realizarTransferencia(Cliente cliente, int cuentaOrigen, int cuentaDestino, double monto) {
        Cuenta origen = null, destino = null;
        for (Cuenta cuenta : cliente.getCuentas()) {
            if (cuenta.getNumCuenta() == cuentaOrigen) origen = cuenta;
            if (cuenta.getNumCuenta() == cuentaDestino) destino = cuenta;
        }
        if (origen != null && destino != null) {
            origen.transferir(destino, monto);
        }
    }
    
    public void imprimirComprobante(Transaccion transaccion) {
        Impresora impresora = new Impresora();
        impresora.imprimir(transaccion);
    }
}