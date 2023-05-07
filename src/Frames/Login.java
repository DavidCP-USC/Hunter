package Frames;

public class Login extends javax.swing.JFrame {

    private boolean acceder = false;
    
    public Login() {
        initComponents();
    }
    
    public boolean accedido(){
        return acceder;
    }
    
    public void credencialesIncorrectas(){
        acceder = false;
        loginStatus.setText("Credenciales Incorrectas");
    }
    
    public void nuevoLogin(){
        acceder = false;
        user.setText("");
        passwd.setText("");
        loginStatus.setText("");
    }
    
    public String getUser(){
        return user.getText();
    }
    
    public String getPassword(){
        return new String(passwd.getPassword());
    }
            
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        user = new javax.swing.JTextField();
        login = new javax.swing.JButton();
        passwd = new javax.swing.JPasswordField();
        loginStatus = new javax.swing.JTextField();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user.setBackground(new java.awt.Color(248, 204, 216));
        user.setFont(new java.awt.Font("DejaVu Sans", 0, 28)); // NOI18N
        user.setBorder(null);
        user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userActionPerformed(evt);
            }
        });
        bg.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 268, 260, 30));

        login.setBackground(new java.awt.Color(255, 219, 224));
        login.setFont(new java.awt.Font("Ubuntu Mono", 1, 30)); // NOI18N
        login.setText("ACCEDER");
        login.setBorder(null);
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        bg.add(login, new org.netbeans.lib.awtextra.AbsoluteConstraints(703, 430, 150, 30));

        passwd.setBackground(new java.awt.Color(255, 212, 223));
        passwd.setFont(new java.awt.Font("DejaVu Sans", 0, 28)); // NOI18N
        passwd.setBorder(null);
        bg.add(passwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 340, 260, 30));

        loginStatus.setBackground(new java.awt.Color(251, 218, 219));
        loginStatus.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        loginStatus.setForeground(new java.awt.Color(0, 0, 204));
        loginStatus.setBorder(null);
        loginStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginStatusActionPerformed(evt);
            }
        });
        bg.add(loginStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 390, 240, 30));

        fondo.setBackground(new java.awt.Color(251, 218, 219));
        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/PanelLogin.png"))); // NOI18N
        fondo.setText("jLabel1");
        bg.add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, -2, 1020, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        acceder = true;
    }//GEN-LAST:event_loginActionPerformed

    private void loginStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginStatusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JLabel fondo;
    private javax.swing.JButton login;
    private javax.swing.JTextField loginStatus;
    private javax.swing.JPasswordField passwd;
    private javax.swing.JTextField user;
    // End of variables declaration//GEN-END:variables
}
