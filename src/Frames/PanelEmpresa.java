package Frames;

import DAOs.DAOManager;
import Modelos.Modelos;
import java.util.List;
import javax.swing.DefaultListModel;

public class PanelEmpresa extends javax.swing.JFrame {
    
    private boolean blogout = false;
    private boolean badd = false;
    private boolean bsearch = false;
    private boolean bprofile = false;
    private boolean bcontactarproblemas = false;
    DefaultListModel modelo = new DefaultListModel();
    
    public PanelEmpresa() {
        initComponents();
        listaModelos.setModel(modelo);
    }
    
    public void displayModelos(List<Modelos> lista){
        modelo.removeAllElements();
        for(int i = 0; i < lista.size(); i++){
            DAOManager.modelos().obtenerNombresEmpresas(lista.get(i));
            modelo.addElement("Nombre: "+lista.get(i).getNombre());
            modelo.addElement("Precio base: " + lista.get(i).getPrecioBase()+ " €");
            modelo.addElement("Distribuidor: "+lista.get(i).getNombreDistribuidor());
            modelo.addElement("----------------------------------------------");
        }
    }
    
    public boolean logOut(){
        return blogout;
    }
    
    public boolean addModelo(){
        return badd;
    }
    
    public boolean makeSearch(){
        return bsearch;
    }
    
    public boolean openProfile(){
        return bprofile;
    }
    
    public boolean abrirContactarProblemas(){
        return bcontactarproblemas;
    }
    
    public void resetButtonState(){
        blogout = false;
        badd = false;
        bsearch = false;
        bprofile = false;
        bcontactarproblemas = false;
    }
    
    public String modelToSearch(){
        return search.getText();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        profile = new javax.swing.JButton();
        ContactarProblemas = new javax.swing.JButton();
        logOut = new javax.swing.JButton();
        addModel = new javax.swing.JButton();
        search = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaModelos = new javax.swing.JList<>();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setBackground(new java.awt.Color(248, 204, 216));
        jTextField1.setFont(new java.awt.Font("DejaVu Sans", 0, 28)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(51, 51, 51));
        jTextField1.setText("Mis modelos");
        jTextField1.setBorder(null);
        bg.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 183, 360, 40));

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

        ContactarProblemas.setBorder(null);
        ContactarProblemas.setBorderPainted(false);
        ContactarProblemas.setContentAreaFilled(false);
        ContactarProblemas.setFocusPainted(false);
        ContactarProblemas.setFocusable(false);
        ContactarProblemas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContactarProblemasActionPerformed(evt);
            }
        });
        bg.add(ContactarProblemas, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 60, 50));

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

        addModel.setBorder(null);
        addModel.setBorderPainted(false);
        addModel.setContentAreaFilled(false);
        addModel.setFocusPainted(false);
        addModel.setFocusable(false);
        addModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addModelActionPerformed(evt);
            }
        });
        bg.add(addModel, new org.netbeans.lib.awtextra.AbsoluteConstraints(803, 174, 60, 50));

        search.setBorder(null);
        search.setBorderPainted(false);
        search.setContentAreaFilled(false);
        search.setFocusPainted(false);
        search.setFocusable(false);
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        bg.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 174, 50, 50));

        listaModelos.setBackground(new java.awt.Color(255, 204, 204));
        listaModelos.setFont(new java.awt.Font("DejaVu Sans", 0, 25)); // NOI18N
        jScrollPane1.setViewportView(listaModelos);

        bg.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 250, 440, 270));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/PanelEmpresa.png"))); // NOI18N
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

    private void addModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addModelActionPerformed
        badd = true;
    }//GEN-LAST:event_addModelActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        bsearch = true;
    }//GEN-LAST:event_searchActionPerformed

    private void profileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileActionPerformed
        bprofile = true;
    }//GEN-LAST:event_profileActionPerformed

    private void ContactarProblemasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContactarProblemasActionPerformed
        bcontactarproblemas = true;
    }//GEN-LAST:event_ContactarProblemasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ContactarProblemas;
    private javax.swing.JButton addModel;
    private javax.swing.JLabel background;
    private javax.swing.JPanel bg;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JList<String> listaModelos;
    private javax.swing.JButton logOut;
    private javax.swing.JButton profile;
    private javax.swing.JButton search;
    // End of variables declaration//GEN-END:variables
}
