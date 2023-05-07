package Frames;

import DAOs.DAOManager;

public class PanelAdministradorCliente extends javax.swing.JFrame {
    
    private boolean blogout = false;
    private boolean bprofile = false;
    private boolean bcreate = false;
    private boolean bdelete = false;
    private boolean blist = false;
    
    public PanelAdministradorCliente() {
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
    
    public boolean addCliente(){
        return bcreate;
    }
    
    public boolean deleteCliente(){
        return bdelete;
    }
    
    public boolean listarClientes(){
        return blist;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        listClientes = new javax.swing.JButton();
        deleteClient = new javax.swing.JButton();
        addClient = new javax.swing.JButton();
        profile = new javax.swing.JButton();
        logOut = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listClientes.setBorder(null);
        listClientes.setBorderPainted(false);
        listClientes.setContentAreaFilled(false);
        listClientes.setFocusable(false);
        listClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listClientesActionPerformed(evt);
            }
        });
        bg.add(listClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 410, 420, 50));

        deleteClient.setBorder(null);
        deleteClient.setBorderPainted(false);
        deleteClient.setContentAreaFilled(false);
        deleteClient.setFocusable(false);
        deleteClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteClientActionPerformed(evt);
            }
        });
        bg.add(deleteClient, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, 420, 50));

        addClient.setBorder(null);
        addClient.setBorderPainted(false);
        addClient.setContentAreaFilled(false);
        addClient.setFocusable(false);
        addClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addClientActionPerformed(evt);
            }
        });
        bg.add(addClient, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, 420, 50));

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

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/PanelAdministradorCliente.png"))); // NOI18N
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

    private void addClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addClientActionPerformed
        bcreate = true;
    }//GEN-LAST:event_addClientActionPerformed

    private void deleteClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteClientActionPerformed
        bdelete = true;
    }//GEN-LAST:event_deleteClientActionPerformed

    private void listClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listClientesActionPerformed
        blist = true;
    }//GEN-LAST:event_listClientesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addClient;
    private javax.swing.JLabel background;
    private javax.swing.JPanel bg;
    private javax.swing.JButton deleteClient;
    private javax.swing.JButton listClientes;
    private javax.swing.JButton logOut;
    private javax.swing.JButton profile;
    // End of variables declaration//GEN-END:variables
}
