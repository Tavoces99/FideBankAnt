
package interfaces;

import appFidebank.Conexion;
import appFidebank.Sesion;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class retiro extends javax.swing.JPanel {

    public retiro() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textMonto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaResumen = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(590, 290));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Resultado");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, -1, -1));

        jLabel3.setText("Monto");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));
        jPanel1.add(textMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 90, -1));

        jLabel2.setText("Resumen");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        tablaResumen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Cliente", "Cuenta", "Monto", "Estado"
            }
        ));
        jScrollPane1.setViewportView(tablaResumen);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 530, 70));

        txtResultado.setColumns(20);
        txtResultado.setRows(5);
        jScrollPane2.setViewportView(txtResultado);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 240, -1));

        jButton1.setText("RETIRAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String montoStr = textMonto.getText();
        double monto;

        try {
            monto = Double.parseDouble(montoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un monto válido.");
            return;
        }

        String pinIngresado = JOptionPane.showInputDialog("Ingrese su PIN:");

        try (Connection conn = Conexion.getConnection()) {

            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Error de conexión con la base de datos.");
                return;
            }

            String sqlVerifica = "SELECT * FROM tbl_usuario WHERE cedula = ? AND pin = ?";
            PreparedStatement ps = conn.prepareStatement(sqlVerifica);
            ps.setInt(1, Sesion.cedula);
            ps.setInt(2, Integer.parseInt(pinIngresado));
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "PIN incorrecto.");
                return;
            }

            double saldoActual = rs.getDouble("saldo");

            if (saldoActual < monto) {
                JOptionPane.showMessageDialog(null, "Saldo insuficiente.");
                return;
            }

            String sqlInsert = "INSERT INTO detalle_transaccion (cedula, id_operacion, cant) VALUES (?, 1, ?)";
            PreparedStatement psInsert = conn.prepareStatement(sqlInsert);
            psInsert.setInt(1, Sesion.cedula);
            psInsert.setDouble(2, monto);
            psInsert.executeUpdate();

            String sqlUpdateSaldo = "UPDATE tbl_usuario SET saldo = saldo - ? WHERE cedula = ?";
            PreparedStatement psUpdate = conn.prepareStatement(sqlUpdateSaldo);
            psUpdate.setDouble(1, monto);
            psUpdate.setInt(2, Sesion.cedula);
            psUpdate.executeUpdate();

            txtResultado.setText("Retiro exitoso de ₡/. " + monto);

            DefaultTableModel model = (DefaultTableModel) tablaResumen.getModel();
            model.addRow(new Object[]{
                Sesion.nombre,
                Sesion.numCuenta,
                monto,
                "Aprobado"
            });

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error durante el retiro.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaResumen;
    private javax.swing.JTextField textMonto;
    private javax.swing.JTextArea txtResultado;
    // End of variables declaration//GEN-END:variables
}
