package Frames;

import Modelos.EmpresasVendedoras;
import java.util.List;
import javax.swing.DefaultListModel;

public class ListadoEmpresas extends javax.swing.JFrame {
    
    private boolean blogout = false;
    private boolean bsearch = false;
    private boolean bhome = false;
    DefaultListModel modelo = new DefaultListModel();
    
    public ListadoEmpresas() {
        initComponents();
        listaProductos.setModel(modelo);
    }

    public void displayEmpresas(List<EmpresasVendedoras> lista){
        modelo.removeAllElements();
        for(int i = 0; i < lista.size(); i++){
            modelo.addElement("Usuario: "+lista.get(i).getNombreUsuario());
            modelo.addElement("Contraseña: " + lista.get(i).getContrasenha());
            modelo.addElement("----------------------------------------------");
        }
    }
    
    public boolean logOut(){
        return blogout;
    }
    
    public boolean returnHome(){
        return bhome;
    }
    
    public boolean makeSearch(){
        return bsearch;
    }
    
    public void resetButtonState(){
        blogout = false;
        bsearch = false;
        bhome = false;
    }
    
    public String usernameToSearch(){
        return buscador.getText();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        buscador = new javax.swing.JTextField();
        home = new javax.swing.JButton();
        logOut = new javax.swing.JButton();
        search = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaProductos = new javax.swing.JList<>();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buscador.setBackground(new java.awt.Color(248, 204, 216));
        buscador.setFont(new java.awt.Font("DejaVu Sans", 0, 28)); // NOI18N
        buscador.setForeground(new java.awt.Color(51, 51, 51));
        buscador.setText("Buscador de empresas");
        buscador.setBorder(null);
        bg.add(buscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 180, 360, 40));

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

        listaProductos.setBackground(new java.awt.Color(255, 204, 204));
        listaProductos.setBorder(new javax.swing.border.MatteBorder(null));
        listaProductos.setFont(new java.awt.Font("DejaVu Sans", 0, 25)); // NOI18N
        listaProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(listaProductos);

        bg.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 250, 440, 270));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ListadoEmpresas.png"))); // NOI18N
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

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        bsearch = true;
    }//GEN-LAST:event_searchActionPerformed

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        bhome = true;
    }//GEN-LAST:event_homeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JPanel bg;
    private javax.swing.JTextField buscador;
    private javax.swing.JButton home;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listaProductos;
    private javax.swing.JButton logOut;
    private javax.swing.JButton search;
    // End of variables declaration//GEN-END:variables
}
