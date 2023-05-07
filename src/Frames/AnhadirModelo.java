package Frames;

import DAOs.DAOManager;
import Modelos.EmpresasVendedoras;
import Modelos.Modelos;
import java.sql.SQLException;

public class AnhadirModelo extends javax.swing.JFrame {

    private boolean bLogout = false;
    private boolean bHome = false;
    private boolean bSaveChanges = false;
    private boolean badd = false;
    EmpresasVendedoras vendedor = null;
    
    public AnhadirModelo(EmpresasVendedoras e) {
        initComponents();
        vendedor = e;
    }
    
    public Modelos getDatos() throws SQLException{
        if(DAOManager.distribuidores().getIdDistribuidor(distribuidor.getText()) == null)
            throw new SQLException("distribuidor no registrado");
        if(DAOManager.modelos().getLastId() != null)
            return new Modelos(DAOManager.modelos().getLastId()+ 1, nombre.getText(), Float.valueOf(precioBase.getText()), DAOManager.distribuidores().getIdDistribuidor(distribuidor.getText()), vendedor.getIdEmpresa());
        else
            return new Modelos(1, nombre.getText(), Float.valueOf(precioBase.getText()), DAOManager.distribuidores().getIdDistribuidor(distribuidor.getText()), vendedor.getIdEmpresa());
    }

    public boolean logOut(){
        return bLogout;
    }
    
    public void logOutNow(){
        bLogout = true;
    }
    
    public boolean returnHome(){
        return bHome;
    }
    
    public void returnHomeNow(){
        bHome = true;
    }
    
    public boolean saveChanges(){
        return bSaveChanges;
    }
    
    public boolean addProductos(){
        return badd;
    }
    
    public void resetSaveButton(){
        saveStatus.setText("");
        bSaveChanges = false;
    }
    
    public void resetAddButton(){
        badd = false;
    }
    
    public void createdSuccesfull(){
        saveStatus.setText("Modelo creado correctamente");
    }
    
    public void createdUnnownError(){
        saveStatus.setText("Error creando el modelo (SQL)");
    }
    
    public void repeatedError(){
        saveStatus.setText("Error, modelo ya registrado");
    }
    
    public void noDistribuidorError(){
        saveStatus.setText("Error, distribuidor no registrado");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        home = new javax.swing.JButton();
        productos = new javax.swing.JButton();
        logOut = new javax.swing.JButton();
        saveChanges = new javax.swing.JButton();
        nombre = new javax.swing.JTextField();
        precioBase = new javax.swing.JTextField();
        distribuidor = new javax.swing.JTextField();
        saveStatus = new javax.swing.JTextField();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        home.setBorder(null);
        home.setBorderPainted(false);
        home.setContentAreaFilled(false);
        home.setFocusable(false);
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });
        bg.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 50, 50));

        productos.setBorder(null);
        productos.setBorderPainted(false);
        productos.setContentAreaFilled(false);
        productos.setFocusable(false);
        productos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productosActionPerformed(evt);
            }
        });
        bg.add(productos, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 50, 40));

        logOut.setBorder(null);
        logOut.setBorderPainted(false);
        logOut.setContentAreaFilled(false);
        logOut.setFocusable(false);
        logOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutActionPerformed(evt);
            }
        });
        bg.add(logOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(803, 100, 50, 40));

        saveChanges.setBorder(null);
        saveChanges.setBorderPainted(false);
        saveChanges.setContentAreaFilled(false);
        saveChanges.setFocusPainted(false);
        saveChanges.setFocusable(false);
        saveChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveChangesActionPerformed(evt);
            }
        });
        bg.add(saveChanges, new org.netbeans.lib.awtextra.AbsoluteConstraints(803, 174, 60, 50));

        nombre.setBackground(new java.awt.Color(248, 204, 216));
        nombre.setFont(new java.awt.Font("DejaVu Sans", 0, 28)); // NOI18N
        nombre.setBorder(null);
        bg.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 267, 340, 40));

        precioBase.setBackground(new java.awt.Color(250, 214, 218));
        precioBase.setFont(new java.awt.Font("DejaVu Sans", 0, 28)); // NOI18N
        precioBase.setBorder(null);
        bg.add(precioBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 331, 340, 40));

        distribuidor.setBackground(new java.awt.Color(251, 220, 220));
        distribuidor.setFont(new java.awt.Font("DejaVu Sans", 0, 28)); // NOI18N
        distribuidor.setBorder(null);
        bg.add(distribuidor, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 398, 340, 40));

        saveStatus.setBackground(new java.awt.Color(252, 224, 222));
        saveStatus.setFont(new java.awt.Font("DejaVu Sans", 0, 20)); // NOI18N
        saveStatus.setForeground(new java.awt.Color(0, 102, 102));
        saveStatus.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        saveStatus.setBorder(null);
        bg.add(saveStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 490, 380, 30));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/anhadirModelo.png"))); // NOI18N
        bg.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 1020, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        bHome = true;
    }//GEN-LAST:event_homeActionPerformed

    private void productosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productosActionPerformed
        badd = true;
    }//GEN-LAST:event_productosActionPerformed

    private void logOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutActionPerformed
        bLogout = true;
    }//GEN-LAST:event_logOutActionPerformed

    private void saveChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveChangesActionPerformed
        bSaveChanges = true;
    }//GEN-LAST:event_saveChangesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JPanel bg;
    private javax.swing.JTextField distribuidor;
    private javax.swing.JButton home;
    private javax.swing.JButton logOut;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField precioBase;
    private javax.swing.JButton productos;
    private javax.swing.JButton saveChanges;
    private javax.swing.JTextField saveStatus;
    // End of variables declaration//GEN-END:variables
}
