
package appFidebank;

public class Impresora {
    
    public void imprimir(Transaccion transaccion) {
        System.out.println("===== COMPROBANTE =====");
        System.out.println("Fecha y Hora: " + transaccion.getFechaHora());
        System.out.println("Descripción: " + transaccion.getDescripcion());
        System.out.println("Monto: ₡" + transaccion.getMonto());
        System.out.println("=======================");
    }
}
