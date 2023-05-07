package Frames;

import java.sql.Date;
import Modelos.EmpresasVendedoras;

public class PerfilEmpresa extends javax.swing.JFrame {
    
    private boolean bLogout = false;
    private boolean bHome = false;
    private boolean bSaveChanges = false;
    private EmpresasVendedoras Empresa = null;
    
    public PerfilEmpresa(EmpresasVendedoras e) {
        initComponents();
        Empresa = e;
        setDatos();
    }
    
    public final void setDatos(){
        nombre.setText(Empresa.getNombre());
        nombreUsuario.setText(Empresa.getNombreUsuario());
        contrasena.setText(Empresa.getContrasenha());
        asociacion.setText(Empresa.getFechaAsociacion().toString());
    }
    
    public EmpresasVendedoras getDatos(){
        return new EmpresasVendedoras(Empresa.getIdEmpresa(), nombre.getText(), nombreUsuario.getText(), contrasena.getText(), Empresa.getEsAdmin(), Date.valueOf(asociacion.getText()));
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
    
    public void submitChanges(){
        saveStatus.setText("Cambios guardados correctamente");
    }
    
    public void modifiedUnnownError(){
        saveStatus.setText("Error modificando la empresa (SQL)");
    }
    
    public void repeatedError(){
        saveStatus.setText("Error, empresa ya registrada");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        home = new javax.swing.JButton();
        logOut = new javax.swing.JButton();
        saveChanges = new javax.swing.JButton();
        nombre = new javax.swing.JTextField();
        nombreUsuario = new javax.swing.JTextField();
        contrasena = new javax.swing.JTextField();
        asociacion = new javax.swing.JTextField();
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
        bg.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 224, 340, 40));

        nombreUsuario.setBackground(new java.awt.Color(248, 204, 216));
        nombreUsuario.setFont(new java.awt.Font("DejaVu Sans", 0, 28)); // NOI18N
        nombreUsuario.setBorder(null);
        bg.add(nombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 290, 340, 40));

        contrasena.setBackground(new java.awt.Color(250, 214, 218));
        contrasena.setFont(new java.awt.Font("DejaVu Sans", 0, 28)); // NOI18N
        contrasena.setBorder(null);
        bg.add(contrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 355, 340, 40));

        asociacion.setBackground(new java.awt.Color(251, 220, 220));
        asociacion.setFont(new java.awt.Font("DejaVu Sans", 0, 28)); // NOI18N
        asociacion.setBorder(null);
        asociacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asociacionActionPerformed(evt);
            }
        });
        bg.add(asociacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 420, 340, 40));

        saveStatus.setBackground(new java.awt.Color(252, 224, 222));
        saveStatus.setFont(new java.awt.Font("DejaVu Sans", 0, 20)); // NOI18N
        saveStatus.setForeground(new java.awt.Color(0, 102, 102));
        saveStatus.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        saveStatus.setBorder(null);
        bg.add(saveStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(406, 490, 380, 30));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/PerfilEmpresa.png"))); // NOI18N
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

    private void saveChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveChangesActionPerformed
        bSaveChanges = true;
    }//GEN-LAST:event_saveChangesActionPerformed

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        bHome = true;
    }//GEN-LAST:event_homeActionPerformed

    private void asociacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asociacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_asociacionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField asociacion;
    private javax.swing.JLabel background;
    private javax.swing.JPanel bg;
    private javax.swing.JTextField contrasena;
    private javax.swing.JButton home;
    private javax.swing.JButton logOut;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField nombreUsuario;
    private javax.swing.JButton saveChanges;
    private javax.swing.JTextField saveStatus;
    // End of variables declaration//GEN-END:variables
}
