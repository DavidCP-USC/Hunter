package Frames;

import DAOs.DAOManager;
import Modelos.EmpresasVendedoras;
import Modelos.Modelos;
import Modelos.Problemas;
import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultListModel;

public class PanelContactarProblemasEmpresa extends javax.swing.JFrame {

    private boolean bLogout = false;
    private boolean bHome = false;
    private boolean bSaveChanges = false;
    private boolean badd = false;
    DefaultListModel problemas = new DefaultListModel();
    
    public PanelContactarProblemasEmpresa() {
        initComponents();
        listaProblemas.setModel(problemas);
    }
    
    public void displayProblemas(List<Problemas> lista, EmpresasVendedoras e){
        problemas.removeAllElements();
        for(int i = 0; i < lista.size(); i++){
            if (e.getIdEmpresa().equals(lista.get(i).getEmpresa())){
                DAOManager.problemas().actualizarNombresClientes(lista.get(i));
                problemas.addElement("Cliente: " + lista.get(i).getNombreCliente());
                problemas.addElement("Fecha: "+lista.get(i).getFecha());
                problemas.addElement("Comentario: "+lista.get(i).getComentario());
                problemas.addElement("----------------------------------------------");
            }
        }
    }
    
    public void resetButtonState(){
        bLogout = false;
        bHome = false;
        bSaveChanges = false;
        badd = false;
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        Scroll = new javax.swing.JScrollPane();
        listaProblemas = new javax.swing.JList<>();
        saveStatus = new javax.swing.JTextField();
        home = new javax.swing.JButton();
        logOut = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listaProblemas.setBackground(new java.awt.Color(255, 204, 204));
        listaProblemas.setBorder(new javax.swing.border.MatteBorder(null));
        listaProblemas.setFont(new java.awt.Font("DejaVu Sans", 0, 25)); // NOI18N
        listaProblemas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Scroll.setViewportView(listaProblemas);

        bg.add(Scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, 560, 250));

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

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ContactarProblemasEmpresa.png"))); // NOI18N
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

    private void saveStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveStatusActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane Scroll;
    private javax.swing.JLabel background;
    private javax.swing.JPanel bg;
    private javax.swing.JButton home;
    private javax.swing.JList<String> listaProblemas;
    private javax.swing.JButton logOut;
    private javax.swing.JTextField saveStatus;
    // End of variables declaration//GEN-END:variables
}
