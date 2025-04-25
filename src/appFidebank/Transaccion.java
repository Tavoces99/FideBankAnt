
package appFidebank;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Transaccion implements Serializable {
    private static final long serialVersionUID = 1L;
    protected LocalDateTime fechaHora;
    protected double monto;
    protected Cuenta cuentaOrigen;
    protected String descripcion;

    public Transaccion(Cuenta cuentaOrigen, double monto, String descripcion) {
        this.fechaHora = LocalDateTime.now();
        this.monto = monto;
        this.cuentaOrigen = cuentaOrigen;
        this.descripcion = descripcion;
    }

    public abstract void ejecutar();

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public double getMonto() {
        return monto;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public String getDescripcion() {
        return descripcion;
    }
}