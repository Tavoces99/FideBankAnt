package interfaces;

import appFidebank.Conexion;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
public class VistaPersonalAdmin extends javax.swing.JPanel {

    public VistaPersonalAdmin() {
        initComponents();
    }

    private void buscarPorCedula() {
        String cedulaTexto = txtCedula.getText().trim();

        if (cedulaTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese una cédula.");
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) tblResultado.getModel();
        modelo.setRowCount(0);

        try {
            Connection conn = Conexion.getConnection();
            if (conn == null) {
                JOptionPane.showMessageDialog(this, "Error de conexión con la base de datos.");
                return;
            }

            String sql = "SELECT cedula, nombre, num_cuenta, saldo FROM tbl_usuario WHERE cedula = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(cedulaTexto));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int cedula = rs.getInt("cedula");
                String nombre = rs.getString("nombre");
                int cuenta = rs.getInt("num_cuenta");
                double saldo = rs.getDouble("saldo");

                modelo.addRow(new Object[]{cedula, nombre, cuenta, saldo});
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún usuario con esa cédula.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cédula debe ser un número entero.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al realizar la búsqueda.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResultado = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(590, 290));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Vista por Cliente");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));
        jPanel1.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 180, 30));

        jLabel2.setText("Resumen");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        tblResultado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Cedula", "Cliente", "Numero de cuenta", "Saldo"
            }
        ));
        jScrollPane1.setViewportView(tblResultado);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 530, 60));

        btnBuscar.setBackground(new java.awt.Color(153, 153, 255));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/lupa.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, -1, -1));

        jLabel4.setText("Ingrese cedula del cliente");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

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

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarPorCedula();
    }//GEN-LAST:event_btnBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblResultado;
    private javax.swing.JTextField txtCedula;
    // End of variables declaration//GEN-END:variables
}
