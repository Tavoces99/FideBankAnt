
package appFidebank;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.*;

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
    
    public static String validarCredenciales(String usuario, String clave) {
        Connection conn = Conexion.getConnection();

        if (conn == null) {
            return "error conexion";
        }

        try {
            String sqlEmp = "SELECT * FROM tbl_empleado WHERE cod_emp = ? AND contra = ?";
            PreparedStatement psEmp = conn.prepareStatement(sqlEmp);
            psEmp.setInt(1, Integer.parseInt(usuario));
            psEmp.setString(2, clave);
            ResultSet rsEmp = psEmp.executeQuery();

            if (rsEmp.next()) {
                return "empleado";
            }

            String sqlCli = "SELECT * FROM tbl_usuario WHERE cedula = ? AND pin = ?";
            PreparedStatement psCli = conn.prepareStatement(sqlCli);
            psCli.setInt(1, Integer.parseInt(usuario));
            psCli.setInt(2, Integer.parseInt(clave));
            ResultSet rsCli = psCli.executeQuery();

            if (rsCli.next()) {
                Sesion.cedula = rsCli.getInt("cedula");
                Sesion.nombre = rsCli.getString("nombre");
                Sesion.numCuenta = rsCli.getInt("num_cuenta");
                Sesion.saldo = rsCli.getDouble("saldo");
                return "cliente";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "no valido";
    }
}