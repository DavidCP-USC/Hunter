package Frames;

public class EliminarCliente extends javax.swing.JFrame {
    
    private boolean bLogout = false;
    private boolean bHome = false;
    private boolean bdelete = false;
        
    public EliminarCliente() {
        initComponents();
    }
    
    public String[] getDatos(){
        String[] datos = new String[2];
        datos[0] = nombreUsuario.getText();
        datos[1] = contrasena.getText();
        return datos;
    }
    
    public boolean logOut(){
        return bLogout;
    }
    
    public boolean returnHome(){
        return bHome;
    }
    
    public boolean delete(){
        return bdelete;
    }
    
    public void resetSaveButton(){
        deleteStatus.setText("");
        bdelete = false;
    }
    
    public void deletedSuccesfull(){
        deleteStatus.setText("Cliente eliminado correctamente");
    }
    
    public void deletedUnnowkError(){
        deleteStatus.setText("Error eliminando el cliente (SQL)");
    }
    
    public void notRegisteredError(){
        deleteStatus.setText("Error, el cliente no está registrado");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        home = new javax.swing.JButton();
        logOut = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        nombreUsuario = new javax.swing.JTextField();
        contrasena = new javax.swing.JTextField();
        deleteStatus = new javax.swing.JTextField();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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

        delete.setBorder(null);
        delete.setBorderPainted(false);
        delete.setContentAreaFilled(false);
        delete.setFocusPainted(false);
        delete.setFocusable(false);
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        bg.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(803, 174, 60, 50));

        nombreUsuario.setBackground(new java.awt.Color(248, 204, 216));
        nombreUsuario.setFont(new java.awt.Font("DejaVu Sans", 0, 28)); // NOI18N
        nombreUsuario.setBorder(null);
        bg.add(nombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 242, 340, 40));

        contrasena.setBackground(new java.awt.Color(250, 214, 218));
        contrasena.setFont(new java.awt.Font("DejaVu Sans", 0, 28)); // NOI18N
        contrasena.setBorder(null);
        bg.add(contrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 344, 340, 40));

        deleteStatus.setBackground(new java.awt.Color(252, 224, 222));
        deleteStatus.setFont(new java.awt.Font("DejaVu Sans", 0, 20)); // NOI18N
        deleteStatus.setForeground(new java.awt.Color(0, 102, 102));
        deleteStatus.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        deleteStatus.setBorder(null);
        bg.add(deleteStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 420, 390, 40));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminarCliente.png"))); // NOI18N
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

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        bdelete = true;
    }//GEN-LAST:event_deleteActionPerformed

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        bHome = true;
    }//GEN-LAST:event_homeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JPanel bg;
    private javax.swing.JTextField contrasena;
    private javax.swing.JButton delete;
    private javax.swing.JTextField deleteStatus;
    private javax.swing.JButton home;
    private javax.swing.JButton logOut;
    private javax.swing.JTextField nombreUsuario;
    // End of variables declaration//GEN-END:variables
}
