
package appFidebank;

import java.util.ArrayList;

public class Banco {
    private ArrayList<Cliente> clientes;

    public Banco() {
        this.clientes = new ArrayList<>();
    }

    public void registrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void abrirCuenta(Cliente cliente, Cuenta cuenta) {
        cliente.agregarCuenta(cuenta);
    }
}