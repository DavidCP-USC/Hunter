package Frames;

import Modelos.Productos;
import DAOs.DAOManager;
import Modelos.Clientes;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

public class CestaCompra extends javax.swing.JFrame {
    
    private boolean blogout = false;
    private boolean badd = false;
    private boolean bhome = false;
    private boolean bship = false;
    DefaultListModel modelo = new DefaultListModel();
    Clientes cliente = null;
    
    public CestaCompra(Clientes c) {
        initComponents();
        cestaActual.setModel(modelo);
        cliente = c;
        precioTotal.setText("0 €");
    }
    
    public void addProductToCart()throws SQLException{
        try{
            cliente.getCesta().addProductos(DAOManager.productos().buscarProductoMinimoPrecio(buscador.getText(), cliente.getCesta()));
            displayCesta();
        }catch(SQLException ex){
            throw ex;
        }
    }

    public void displayCesta(){
        modelo.removeAllElements();
        List<Productos> productos = new ArrayList<>(cliente.getCesta().getProductos());
        for(int i = 0; i < productos.size(); i++){
            DAOManager.modelos().obtenerNombresEmpresas(DAOManager.modelos().getById(productos.get(i).getModelo()));
            modelo.addElement("Nombre: "+DAOManager.modelos().getById(productos.get(i).getModelo()).getNombre());
            modelo.addElement("Precio: " + productos.get(i).getPrecioTotal() + " €");
            modelo.addElement("-------------------------------");
        }
        precioTotal.setText(String.valueOf(cliente.getCesta().precioTotal()));
    }
    
    public boolean logOut(){
        return blogout;
    }
    
    public boolean addProduct(){
        return badd;
    }

    public boolean returnHome(){
        return bhome;
    }
    
    public boolean processCart(){
        return bship;
    }
    
    public void resetButtonState(){
        blogout = false;
        badd = false;
        bhome = false;
        bship = false;
    }
    
    public String productToSearch(){
        return buscador.getText();
    }
    
    public void shipProducts()throws SQLException{
        if(cliente.getCesta().precioTotal() > 0){
            if(direccionEntrega.getText().equals(""))
                    throw new SQLException("no direccion");
            else
                cliente.getCesta().confirmarCompra(direccionEntrega.getText());
            modelo.removeAllElements();
            cliente.getCesta().vaciar();
            precioTotal.setText("0 €");
        }else{
            throw new SQLException("no productos");
        }
    }
    
    public void shipCorrect(){
        buyStatus.setText("Compra realizada correctamente");
    }
    
    public void emptyCartError(){
        buyStatus.setText("Error, la cesta está vacía");
    }
    
    public void unknownShipError(){
        buyStatus.setText("Error en la compra (SQL)");
    }
    
    public void productAddedCorrectly(){
        buyStatus.setText("Producto añadido correctamente");
    }
    
    public void unknownAddError(){
        buyStatus.setText("Error añadiendo producto (SQL)");
    }
    
    public void notAvailableError(){
        buyStatus.setText("El producto no está disponible");
    }
    
    public void noAddressError(){
        buyStatus.setText("Dirección de entrega inválida");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        direccionEntrega = new javax.swing.JTextField();
        buscador = new javax.swing.JTextField();
        home = new javax.swing.JButton();
        logOut = new javax.swing.JButton();
        process = new javax.swing.JButton();
        add = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        cestaActual = new javax.swing.JList<>();
        buyStatus = new javax.swing.JTextField();
        precioTotal = new javax.swing.JTextField();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        direccionEntrega.setBackground(new java.awt.Color(248, 204, 216));
        direccionEntrega.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        direccionEntrega.setForeground(new java.awt.Color(51, 51, 51));
        direccionEntrega.setBorder(null);
        bg.add(direccionEntrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 130, 30));

        buscador.setBackground(new java.awt.Color(248, 204, 216));
        buscador.setFont(new java.awt.Font("DejaVu Sans", 0, 28)); // NOI18N
        buscador.setForeground(new java.awt.Color(51, 51, 51));
        buscador.setText("Nombre del producto");
        buscador.setBorder(null);
        bg.add(buscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 208, 340, 50));

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

        process.setBorder(null);
        process.setBorderPainted(false);
        process.setContentAreaFilled(false);
        process.setFocusPainted(false);
        process.setFocusable(false);
        process.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processActionPerformed(evt);
            }
        });
        bg.add(process, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 460, 60, 50));

        add.setBorder(null);
        add.setBorderPainted(false);
        add.setContentAreaFilled(false);
        add.setFocusPainted(false);
        add.setFocusable(false);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        bg.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 210, 60, 50));

        cestaActual.setBackground(new java.awt.Color(255, 204, 204));
        cestaActual.setBorder(new javax.swing.border.MatteBorder(null));
        cestaActual.setFont(new java.awt.Font("DejaVu Sans", 0, 25)); // NOI18N
        cestaActual.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(cestaActual);

        bg.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 330, 360, 150));

        buyStatus.setBackground(new java.awt.Color(252, 227, 223));
        buyStatus.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        buyStatus.setBorder(null);
        bg.add(buyStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 490, 360, 30));

        precioTotal.setBackground(new java.awt.Color(248, 204, 216));
        precioTotal.setFont(new java.awt.Font("DejaVu Sans", 0, 22)); // NOI18N
        precioTotal.setBorder(null);
        bg.add(precioTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 284, 110, 30));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/CestaCompra.png"))); // NOI18N
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

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        badd = true;
    }//GEN-LAST:event_addActionPerformed

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        bhome = true;
    }//GEN-LAST:event_homeActionPerformed

    private void processActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processActionPerformed
        bship = true;
    }//GEN-LAST:event_processActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JLabel background;
    private javax.swing.JPanel bg;
    private javax.swing.JTextField buscador;
    private javax.swing.JTextField buyStatus;
    private javax.swing.JList<String> cestaActual;
    private javax.swing.JTextField direccionEntrega;
    private javax.swing.JButton home;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logOut;
    private javax.swing.JTextField precioTotal;
    private javax.swing.JButton process;
    // End of variables declaration//GEN-END:variables
}
