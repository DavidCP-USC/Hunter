package Frames;

import DAOs.DAOManager;
import Modelos.EmpresasVendedoras;
import Modelos.Productos;
import java.sql.SQLException;

public class AnhadirProducto extends javax.swing.JFrame {

    private boolean bLogout = false;
    private boolean bHome = false;
    private boolean bSaveChanges = false;
    EmpresasVendedoras vendedor = null;
    
    public AnhadirProducto(EmpresasVendedoras e) {
        initComponents();
        vendedor = e;
    }
    
    public Productos getDatos() throws SQLException{
        if(DAOManager.modelos().getIdModelo(nombreModelo.getText()) == null)
            throw new SQLException("modelo no registrado");
        if(DAOManager.productos().getLastId() != null)
            return new Productos(DAOManager.productos().getLastId()+ 1, DAOManager.modelos().getIdModelo(nombreModelo.getText()), null, null, null, Float.valueOf(precio.getText()), null, null, null, null);
        else
            return new Productos(1, DAOManager.modelos().getIdModelo(nombreModelo.getText()), null, null, null, Float.valueOf(precio.getText()), null, null, null, null);
    }

    public boolean logOut(){
        return bLogout;
    }
    
    public boolean returnHome(){
        return bHome;
    }
    
    public boolean saveChanges(){
        return bSaveChanges;
    }
    
    public void resetSaveButton(){
        saveStatus.setText("");
        bSaveChanges = false;
    }
    
    public void createdSuccesfull(){
        saveStatus.setText("Producto creado correctamente");
    }
    
    public void createdUnnownError(){
        saveStatus.setText("Error creando el producto (SQL)");
    }
    
    public void repeatedError(){
        saveStatus.setText("Error, producto ya registrado");
    }
    
    public void noModelError(){
        saveStatus.setText("Error, modelo no registrado");
    }
    
    public void priceError(){
        saveStatus.setText("Error, precio inferior al precio base");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        home = new javax.swing.JButton();
        logOut = new javax.swing.JButton();
        saveChanges = new javax.swing.JButton();
        nombreModelo = new javax.swing.JTextField();
        precio = new javax.swing.JTextField();
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

        nombreModelo.setBackground(new java.awt.Color(248, 204, 216));
        nombreModelo.setFont(new java.awt.Font("DejaVu Sans", 0, 28)); // NOI18N
        nombreModelo.setBorder(null);
        bg.add(nombreModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 267, 340, 40));

        precio.setBackground(new java.awt.Color(250, 214, 218));
        precio.setFont(new java.awt.Font("DejaVu Sans", 0, 28)); // NOI18N
        precio.setBorder(null);
        bg.add(precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 347, 340, 40));

        saveStatus.setBackground(new java.awt.Color(252, 224, 222));
        saveStatus.setFont(new java.awt.Font("DejaVu Sans", 0, 20)); // NOI18N
        saveStatus.setForeground(new java.awt.Color(0, 102, 102));
        saveStatus.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        saveStatus.setBorder(null);
        bg.add(saveStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 490, 380, 30));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/anhadirProducto.png"))); // NOI18N
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

    private void logOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutActionPerformed
        bLogout = true;
    }//GEN-LAST:event_logOutActionPerformed

    private void saveChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveChangesActionPerformed
        bSaveChanges = true;
    }//GEN-LAST:event_saveChangesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JPanel bg;
    private javax.swing.JButton home;
    private javax.swing.JButton logOut;
    private javax.swing.JTextField nombreModelo;
    private javax.swing.JTextField precio;
    private javax.swing.JButton saveChanges;
    private javax.swing.JTextField saveStatus;
    // End of variables declaration//GEN-END:variables
}
