package Frames;

import DAOs.DAOManager;
import Modelos.Modelos;
import Modelos.Problemas;
import java.util.List;
import javax.swing.DefaultListModel;

public class PanelContactarProblemas extends javax.swing.JFrame {

    private boolean bLogout = false;
    private boolean bHome = false;
    private boolean bSaveChanges = false;
    private boolean badd = false;
    DefaultListModel modelo = new DefaultListModel();
    
    public PanelContactarProblemas() {
        initComponents();
    }
    
    public void displayProductos(List<Modelos> lista){
        modelo.removeAllElements();
        for(int i = 0; i < lista.size(); i++){
            DAOManager.modelos().obtenerNombresEmpresas(lista.get(i));
            modelo.addElement("Nombre: "+lista.get(i).getNombre());
            modelo.addElement("Precio: " + lista.get(i).getPrecioMinimo() + " €");
            modelo.addElement("Vendedor: "+lista.get(i).getNombreVendedor());
            modelo.addElement("Distribuidor: "+lista.get(i).getNombreDistribuidor());
            modelo.addElement("----------------------------------------------");
        }
    }
    
    public void resetButtonState(){
        bLogout = false;
        bHome = false;
        bSaveChanges = false;
        badd = false;
    }
        
    public Problemas getDatos(){
        return new Problemas(numeroPedido.getText(), Comentario.getText());
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
    
    public void guardadoCorrectamente(){
        saveStatus.setText("Comentario enviado correctamente");
    }
    
    public void createdUnnownError(){
        saveStatus.setText("Error creando el modelo (SQL)");
    }
    
    public void repeatedError(){
        saveStatus.setText("Error, modelo ya registrado");
    }
    
    public void numeroPedidoIncorrecto(){
        saveStatus.setText("Error, el numero de pedido no es correcto");
    }
    
    public void comentarioVacio(){
        saveStatus.setText("Error, el comentario no puede estar vacio");
    }
    
    public void clienteIncorrecto(){
        saveStatus.setText("Error, no has comprado este producto");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        saveStatus = new javax.swing.JTextField();
        home = new javax.swing.JButton();
        logOut = new javax.swing.JButton();
        Comentario = new javax.swing.JTextField();
        numeroPedido = new javax.swing.JTextField();
        saveChanges = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        saveStatus.setBackground(new java.awt.Color(252, 224, 222));
        saveStatus.setFont(new java.awt.Font("DejaVu Sans", 0, 20)); // NOI18N
        saveStatus.setForeground(new java.awt.Color(0, 102, 102));
        saveStatus.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        saveStatus.setBorder(null);
        saveStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveStatusActionPerformed(evt);
            }
        });
        bg.add(saveStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 510, 610, 30));

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
        bg.add(logOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 50, 40));

        Comentario.setBackground(new java.awt.Color(248, 204, 216));
        Comentario.setFont(new java.awt.Font("DejaVu Sans", 0, 28)); // NOI18N
        Comentario.setForeground(new java.awt.Color(51, 51, 51));
        Comentario.setBorder(null);
        Comentario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComentarioActionPerformed(evt);
            }
        });
        bg.add(Comentario, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 340, 340, 140));

        numeroPedido.setBackground(new java.awt.Color(248, 204, 216));
        numeroPedido.setFont(new java.awt.Font("DejaVu Sans", 0, 28)); // NOI18N
        numeroPedido.setForeground(new java.awt.Color(51, 51, 51));
        numeroPedido.setBorder(null);
        numeroPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeroPedidoActionPerformed(evt);
            }
        });
        bg.add(numeroPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, 340, 40));

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

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ContactarProblemas.png"))); // NOI18N
        background.setAlignmentY(0.0F);
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

    private void saveStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveStatusActionPerformed

    private void numeroPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numeroPedidoActionPerformed

    private void ComentarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComentarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComentarioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Comentario;
    private javax.swing.JLabel background;
    private javax.swing.JPanel bg;
    private javax.swing.JButton home;
    private javax.swing.JButton logOut;
    private javax.swing.JTextField numeroPedido;
    private javax.swing.JButton saveChanges;
    private javax.swing.JTextField saveStatus;
    // End of variables declaration//GEN-END:variables
}
