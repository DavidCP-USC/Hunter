package Frames;

import DAOs.DAOManager;

public class PanelAdministradorEmpresa extends javax.swing.JFrame {
    
    private boolean blogout = false;
    private boolean bprofile = false;
    private boolean bcreate = false;
    private boolean bdelete = false;
    private boolean blist = false;
    
    public PanelAdministradorEmpresa() {
        initComponents();
    }
    
    public boolean logOut(){
        return blogout;
    }
    
    public boolean openProfile(){
        return bprofile;
    }
    
    public void resetButtonState(){
        blogout = false;
        bprofile = false;
        bcreate = false;
        bdelete = false;
        blist = false;
    }
    
    public boolean addEmpresa(){
        return bcreate;
    }
    
    public boolean deleteEmpresa(){
        return bdelete;
    }
    
    public boolean listarEmpresas(){
        return blist;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        listEmpresas = new javax.swing.JButton();
        deleteEmpresa = new javax.swing.JButton();
        addEmpresa = new javax.swing.JButton();
        profile = new javax.swing.JButton();
        logOut = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listEmpresas.setBorder(null);
        listEmpresas.setBorderPainted(false);
        listEmpresas.setContentAreaFilled(false);
        listEmpresas.setFocusable(false);
        listEmpresas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listEmpresasActionPerformed(evt);
            }
        });
        bg.add(listEmpresas, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 400, 420, 50));

        deleteEmpresa.setBorder(null);
        deleteEmpresa.setBorderPainted(false);
        deleteEmpresa.setContentAreaFilled(false);
        deleteEmpresa.setFocusable(false);
        deleteEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteEmpresaActionPerformed(evt);
            }
        });
        bg.add(deleteEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, 420, 50));

        addEmpresa.setBorder(null);
        addEmpresa.setBorderPainted(false);
        addEmpresa.setContentAreaFilled(false);
        addEmpresa.setFocusable(false);
        addEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEmpresaActionPerformed(evt);
            }
        });
        bg.add(addEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, 420, 50));

        profile.setBorder(null);
        profile.setBorderPainted(false);
        profile.setContentAreaFilled(false);
        profile.setFocusable(false);
        profile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileActionPerformed(evt);
            }
        });
        bg.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 50, 40));

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

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/PanelAdministradorEmpresa.png"))); // NOI18N
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
        blogout = true;
    }//GEN-LAST:event_logOutActionPerformed

    private void profileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileActionPerformed
        bprofile = true;
    }//GEN-LAST:event_profileActionPerformed

    private void addEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEmpresaActionPerformed
        bcreate = true;
    }//GEN-LAST:event_addEmpresaActionPerformed

    private void deleteEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteEmpresaActionPerformed
        bdelete = true;
    }//GEN-LAST:event_deleteEmpresaActionPerformed

    private void listEmpresasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listEmpresasActionPerformed
        blist = true;
    }//GEN-LAST:event_listEmpresasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addEmpresa;
    private javax.swing.JLabel background;
    private javax.swing.JPanel bg;
    private javax.swing.JButton deleteEmpresa;
    private javax.swing.JButton listEmpresas;
    private javax.swing.JButton logOut;
    private javax.swing.JButton profile;
    // End of variables declaration//GEN-END:variables
}
