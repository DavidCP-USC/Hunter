
package Modelos;

import DAOs.DAOManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;


public class Cesta {
    private Set<Productos> productos;
    private Clientes cliente;

    public Cesta(Clientes cliente) {
        this.cliente = cliente;
        this.productos = new HashSet<>();
    }

    public Set<Productos> getProductos() {
        return productos;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }
    
    public int sizeCesta(){
        return this.productos.size();
    }
    
    //conocer el precio total de la compra
    public float precioTotal(){
        float sum = 0;
        for (Productos producto : productos) {
            if(producto.getPrecioTotal() != null) sum += producto.getPrecioTotal();
        }
        return sum;
    }
    
    //añade a la cesta un producto
    public boolean addProductos(Productos prod) throws SQLException{
        if(prod == null || prod.getIdProducto() == null) return false;
        
        return this.productos.add(prod);
    }
    
    //añade a la celda un conjunto de productos
    public boolean addProductos(Set<Productos> productos){
        if(productos == null || productos.isEmpty()) return false;
        
        return this.productos.addAll(productos);
    }
    
    //se elimina de la cesta un producto
    public boolean removeProductos(Productos prod){
        if(prod == null || prod.getIdProducto() == null) return false;
        
        return this.productos.remove(prod);
    }
    
    //elimina de la celda un conjunto de productos
    public boolean removeProductos(Set<Productos> productos){
        if(productos == null || productos.isEmpty()) return false;
        
        return this.productos.removeAll(productos);
    }
    
    
    //borrar el contenido de la cesta
    public void vaciar(){
        this.productos.clear();
    }
    
    
    //escribir productos en la base de datos (QUIZÁ ES UNA TRANSACCIÓN!!!)
    private boolean guardarBD(){
        
        for (Productos producto : productos) {
            try {
                DAOManager.productos().modificar(producto);
            } catch (SQLException ex) {
                Logger.getLogger(Cesta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return true;
    }
    
    // Funcion que confirma la compra
    public boolean confirmarCompra(String direccionEnvio){
        if(this.productos.isEmpty() || direccionEnvio == null) return false;
        
        Date fechaSalida = new Date();
        Random random = new Random();
        
        List<Transportistas> transportistas = DAOManager.transportistas().selectAll();
        if(transportistas.isEmpty()) return false;
        
        int ultimoNumeroPedido = DAOManager.productos().getLastPedido();
        int i = 1;
        //Actualizamos los valores de los productos
        for (Productos producto : productos) {
            producto.setCliente(cliente.getIdCliente());
            producto.setNumeroPedido(ultimoNumeroPedido + (i++));
            producto.setDireccionEnvio(direccionEnvio);
            producto.setFechaSalida(fechaSalida);
            producto.setFechaLlegada(null);
            producto.setEstadoLlegada("Pendiente");
            producto.setTransportista(transportistas.get(random.nextInt(transportistas.size())).getDni()); //transportista aleatorio
        }
        
        //y al final se escriben en la base de datos
        return guardarBD();
    }
    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.cliente);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cesta other = (Cesta) obj;
        return Objects.equals(this.cliente.getIdCliente(), other.cliente.getIdCliente());
    }

    @Override
    public String toString() {
        return "Cesta{ cliente= " + cliente.getIdCliente() + " ,  " + "productos= " + productos + '}';
    }
    
    
    
    
}
