package Frames;

import DAOs.DAOManager;
import Modelos.Clientes;
import java.sql.Date;


public class AnhadirCliente extends javax.swing.JFrame {
    
    private boolean bLogout = false;
    private boolean bHome = false;
    private boolean bcreate = false;
        
    public AnhadirCliente() {
        initComponents();
    }
    
    public Clientes getDatos(){
        if(DAOManager.clientes().getLastId() != null)
            return new Clientes(DAOManager.clientes().getLastId()+1, nombre.getText(), nombreUsuario.getText(), contrasena.getText(), esAdmin.isSelected(), telefono.getText(), Date.valueOf(nacimiento.getText()));
        else
            return new Clientes(1, nombre.getText(), nombreUsuario.getText(), contrasena.getText(), esAdmin.isSelected(), telefono.getText(), Date.valueOf(nacimiento.getText()));
    }
    
    public boolean logOut(){
        return bLogout;
    }
    
    public boolean returnHome(){
        return bHome;
    }
    
    public boolean create(){
        return bcreate;
    }
    
    public void resetSaveButton(){
        saveStatus.setText("");
        bcreate = false;
    }
    
    public void createdSuccesfull(){
        saveStatus.setText("Cliente creado correctamente");
    }
    
    public void createdUnnownError(){
        saveStatus.setText("Error creando el cliente (SQL)");
    }
    
    public void repeatedError(){
        saveStatus.setText("Error, cliente ya registrado");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        home = new javax.swing.JButton();
        logOut = new javax.swing.JButton();
        create = new javax.swing.JButton();
        nombre = new javax.swing.JTextField();
        nombreUsuario = new javax.swing.JTextField();
        contrasena = new javax.swing.JTextField();
        telefono = new javax.swing.JTextField();
        nacimiento = new javax.swing.JTextField();
        esAdmin = new javax.swing.JCheckBox();
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
        bg.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 50, 40));

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

        create.setBorder(null);
        create.setBorderPainted(false);
        create.setContentAreaFilled(false);
        create.setFocusPainted(false);
        create.setFocusable(false);
        create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createActionPerformed(evt);
            }
        });
        bg.add(create, new org.netbeans.lib.awtextra.AbsoluteConstraints(803, 174, 60, 50));

        nombre.setBackground(new java.awt.Color(248, 204, 216));
        nombre.setFont(new java.awt.Font("DejaVu Sans", 0, 28)); // NOI18N
        nombre.setBorder(null);
        bg.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 177, 340, 40));

        nombreUsuario.setBackground(new java.awt.Color(248, 204, 216));
        nombreUsuario.setFont(new java.awt.Font("DejaVu Sans", 0, 28)); // NOI18N
        nombreUsuario.setBorder(null);
        bg.add(nombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 242, 340, 40));

        contrasena.setBackground(new java.awt.Color(250, 214, 218));
        contrasena.setFont(new java.awt.Font("DejaVu Sans", 0, 28)); // NOI18N
        contrasena.setBorder(null);
        bg.add(contrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 308, 340, 40));

        telefono.setBackground(new java.awt.Color(251, 220, 220));
        telefono.setFont(new java.awt.Font("DejaVu Sans", 0, 28)); // NOI18N
        telefono.setBorder(null);
        bg.add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 372, 340, 40));

        nacimiento.setBackground(new java.awt.Color(251, 220, 220));
        nacimiento.setFont(new java.awt.Font("DejaVu Sans", 0, 28)); // NOI18N
        nacimiento.setBorder(null);
        bg.add(nacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 438, 340, 40));

        esAdmin.setActionCommand("");
        esAdmin.setMaximumSize(new java.awt.Dimension(30, 30));
        esAdmin.setPreferredSize(new java.awt.Dimension(29, 29));
        esAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                esAdminActionPerformed(evt);
            }
        });
        bg.add(esAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 503, 25, 25));

        saveStatus.setBackground(new java.awt.Color(252, 224, 222));
        saveStatus.setFont(new java.awt.Font("DejaVu Sans", 0, 20)); // NOI18N
        saveStatus.setForeground(new java.awt.Color(0, 102, 102));
        saveStatus.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        saveStatus.setBorder(null);
        bg.add(saveStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 500, 360, 40));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/anhadirCliente.png"))); // NOI18N
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

    private void logOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutActionPerformed
        bLogout = true;
    }//GEN-LAST:event_logOutActionPerformed

    private void createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createActionPerformed
        bcreate = true;
    }//GEN-LAST:event_createActionPerformed

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        bHome = true;
    }//GEN-LAST:event_homeActionPerformed

    private void esAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_esAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_esAdminActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JPanel bg;
    private javax.swing.JTextField contrasena;
    private javax.swing.JButton create;
    private javax.swing.JCheckBox esAdmin;
    private javax.swing.JButton home;
    private javax.swing.JButton logOut;
    private javax.swing.JTextField nacimiento;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField nombreUsuario;
    private javax.swing.JTextField saveStatus;
    private javax.swing.JTextField telefono;
    // End of variables declaration//GEN-END:variables
}
