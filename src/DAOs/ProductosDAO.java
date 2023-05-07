package DAOs;

import Modelos.Cesta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import Modelos.Productos;
import Modelos.Modelos;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ProductosDAO implements DAO<Productos>{
    
    private static ProductosDAO instancia = null; // instancia única de DAOCliente
    private static Connection conn = null; // instancia única de conexion

    // Constructor privado para evitar la creación de más de una instancia
    private ProductosDAO() {
    }

    // Método estático para obtener la única instancia de DAOCliente
    public static ProductosDAO inicializarSingleton(Connection con) {
        if (instancia == null) {
            instancia = new ProductosDAO();
            conn = con;
        }
        
        return instancia;
    }
    
    final String INSERTAR = "INSERT INTO Productos(idProducto, modelo, cliente, numeroPedido, direccionEnvio, precioTotal, fechaSalida, fechaLlegada, estadoLlegada, transportista) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final String MODIFICAR = "UPDATE Productos Set cliente = ?, numeroPedido = ?, direccionEnvio = ?, precioTotal = ?, fechaSalida = ?, fechaLlegada = ?, estadoLlegada = ?, transportista = ? WHERE idProducto = ? AND modelo = ? ";
    final String ELIMINAR = "DELETE FROM Productos WHERE idProducto = ? AND modelo = ? ";
    final String SELECT_ALL = "SELECT * FROM Productos";
    final String ELIMINAR_ALL_BY_MODELO = "DELET FROM Productos WHERE modelo = ?";

    // Funcion para insertar un producto en la BD
    @Override
    public void insertar(Productos a) throws SQLException{
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(INSERTAR);
            
            if(a.getIdProducto() == null)
                stat.setNull(1, Types.INTEGER);
            else
                stat.setInt(1, a.getIdProducto());
            if (a.getModelo() == null) {
                stat.setNull(2, Types.INTEGER);
            } else {
                stat.setInt(2, a.getModelo());
            }
            if(a.getCliente() == null){
                stat.setNull(3, Types.INTEGER);
            }
            else{
                stat.setInt(3, a.getCliente());
            }
            if(a.getNumeroPedido() != null)
                stat.setInt(4, a.getNumeroPedido());
            else{
                if(getLastPedido() != null)
                    stat.setInt(4, getLastPedido()+1);
                else
                    stat.setInt(4, 1);
            }
            stat.setString(5, a.getDireccionEnvio());
            //Comprobamos que el precio total es mayor que el precio base del modelo
            if(a.getPrecioTotal() >= DAOManager.modelos().getById(a.getModelo()).getPrecioBase())
                stat.setFloat(6, a.getPrecioTotal());
            else
                throw new SQLException("precio insuficiente");
            if(a.getFechaSalida() == null){
                stat.setNull(7, Types.DATE);
            }
            else{
                stat.setDate(7, new Date(a.getFechaSalida().getTime()));
            }
            if (a.getFechaLlegada() == null) {
                stat.setNull(8, Types.DATE);
            } else {
                stat.setDate(8, new Date(a.getFechaLlegada().getTime()));
            }
            stat.setString(9, a.getEstadoLlegada());
            stat.setString(10, a.getTransportista());
            stat.executeUpdate();
        }
        catch (SQLException ex) {
            if(ex.getMessage().contains("llave duplicada viola restricción de unicidad «productos_idproducto_key»"))
                    throw new SQLException("ya registrado");
            else
                throw ex;
        }
        finally {
            if(stat != null){
                try {
                    stat.close();
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    // Funcion para modificar un producto en la BD
    @Override
    public void modificar(Productos a) throws SQLException{
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(MODIFICAR);
            if(a.getCliente() == null){
                stat.setNull(1, Types.INTEGER);
            }
            else{
                stat.setInt(1, a.getCliente());
            }
            stat.setInt(2, a.getNumeroPedido());
            stat.setString(3, a.getDireccionEnvio());
            stat.setFloat(4, a.getPrecioTotal());
            stat.setDate(5, a.getFechaSalida() != null ? new Date(a.getFechaSalida().getTime()) : null);
            stat.setDate(6, a.getFechaLlegada() != null ? new Date(a.getFechaLlegada().getTime()) : null);
            stat.setString(7, a.getEstadoLlegada());
            stat.setString(8, a.getTransportista());
            if (a.getIdProducto() != null) {
                stat.setInt(9, a.getIdProducto());
            } else {
                stat.setNull(9, Types.INTEGER);
            }
            if (a.getModelo() != null) {
                stat.setInt(10, a.getModelo());
            } else {
                stat.setNull(10, Types.INTEGER);
            }
            stat.executeUpdate();

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            if(stat != null){
                try {
                    stat.close();
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    // Funcion para eliminar un producto en la BD
    @Override
    public void eliminar(Productos a) throws SQLException{
    PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(ELIMINAR);
            if(a.getIdProducto() == null)
                stat.setNull(1, Types.INTEGER);
            else
                stat.setInt(1, a.getIdProducto());
            
            if(a.getIdProducto() == null)
                stat.setNull(2, Types.INTEGER);
            else
                stat.setInt(2, a.getModelo());
            stat.executeUpdate();
            if(stat.getUpdateCount() == 0)
                throw new SQLException("no registrado");
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            if(stat != null){
                try {
                    stat.close();
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    // Funcion para eliminar un modelo en la BD y todos sus productos
    public void eliminarModelo(Modelos m) {
    PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(ELIMINAR_ALL_BY_MODELO);
            stat.setInt(1, m.getIdModelo());
            stat.executeUpdate();
            if(stat.getUpdateCount() == 0)
                throw new SQLException("no registrado");
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            if(stat != null){
                try {
                    stat.close();
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    // Esta función simplemente coge una fila del result set y te devuelve un objeto con las características de cada tupla
    private Productos convertir(ResultSet rs) throws SQLException
    {
        Integer idProducto = rs.getInt("idProducto") != 0? rs.getInt("idProducto"): null;
        Integer modelo = rs.getInt("modelo") != 0? rs.getInt("modelo"): null;
        Integer cliente = rs.getInt("cliente") != 0? rs.getInt("cliente"): null;
        Integer numeroPedido = rs.getInt("numeroPedido");
        String direccionEnvio = rs.getString("direccionEnvio");
        Float precioTotal = rs.getFloat("precioTotal");
        Date fechaSalida = rs.getDate("fechaSalida");
        Date fechaLlegada = rs.getDate("fechaLlegada");
        String estadoLlegada = rs.getString("estadoLlegada");
        String transportista = rs.getString("transportista");
        
        Productos producto = new Productos(idProducto, modelo, cliente, numeroPedido, direccionEnvio, precioTotal, fechaSalida, fechaLlegada, estadoLlegada, transportista);
        return producto;
    }

    // Esta función hace un select all
    @Override
    public List<Productos> selectAll()
    {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Productos> productos = new ArrayList<>();

        try {
            stat = conn.prepareStatement(SELECT_ALL);
            rs = stat.executeQuery();
            while(rs.next())
            {
                productos.add(convertir(rs));
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            if(rs != null)
            {
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if(stat != null)
            {
                try {
                    stat.close();
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return productos;
    }
    
    // Funcion que busca los producto por un nombre
    public List<Productos> buscarProductos(String modelo)
    {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Productos> productos = new ArrayList<>();

        try {
            final String SELECT_BY_MODEL = "SELECT * FROM Productos WHERE modelo IN (SELECT idModelo FROM Modelos WHERE nombre LIKE ?) AND cliente IS NULL";
            stat = conn.prepareStatement(SELECT_BY_MODEL);
            stat.setString(1, "%" + modelo + "%");
            rs = stat.executeQuery();
            while(rs.next())
            {
                productos.add(convertir(rs));
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            if(rs != null)
            {
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if(stat != null)
            {
                try {
                    stat.close();
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return productos;
    }
    
    // Funcion que busca los productos seleccionados pero con el precio mínimo
    public Productos buscarProductoMinimoPrecio(String modelo, Cesta cesta) throws SQLException{
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            final String SELECT_BY_MODEL = "SELECT * FROM Productos WHERE modelo IN (SELECT idModelo FROM Modelos WHERE nombre = ?) AND cliente IS NULL ORDER BY precioTotal ASC";
            stat = conn.prepareStatement(SELECT_BY_MODEL);
            stat.setString(1, modelo);
            rs = stat.executeQuery();
            boolean hasResult = rs.next();
            if (!hasResult)
                throw new SQLException("producto no disponible");
            else{
                do {
                    if(cesta.sizeCesta() == 0)
                        return convertir(rs);
                    else{
                        // Comprobamos que no está en la cesta
                        boolean anhadir = true;
                        for (Productos producto : cesta.getProductos()) {
                            if(convertir(rs).getIdProducto() == producto.getIdProducto())
                                anhadir = false;
                        }
                        if(anhadir)
                            return convertir(rs);
                    }
                } while (rs.next());
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        finally {
            if(rs != null)
            {
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if(stat != null)
            {
                try {
                    stat.close();
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        throw new SQLException("producto no disponible");
    }
    
    // Funcion que consulta el catalogo de productos disponibles a partir de un modelo
    public java.util.List<Productos> consultarCatalogoDisponible(Modelos modelo){
        java.util.List<Productos> resultado = new java.util.ArrayList<>();
        Productos productoActual;
        PreparedStatement stmCatalogo=null;
        ResultSet rsCatalogo;
        
        String consulta = "SELECT * FROM productos p WHERE p.modelo = ? AND p.cliente IS NULL";

        try  {
         stmCatalogo=conn.prepareStatement(consulta);
         
         stmCatalogo.setInt(1, modelo.getIdModelo());
         
         rsCatalogo=stmCatalogo.executeQuery();
        while (rsCatalogo.next())
        {
            resultado.add(convertir(rsCatalogo));
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
        }finally{
          try {stmCatalogo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    
    // Funcion que obtiene el último id del ultimo producto insertado
    public Integer getLastId(){
        PreparedStatement stmCatalogo=null;
        ResultSet rsCatalogo;
        
        String consulta = "SELECT MAX(idProducto) as last_id FROM Productos";
        
        try  {
            stmCatalogo=conn.prepareStatement(consulta);

            rsCatalogo=stmCatalogo.executeQuery();
            while (rsCatalogo.next())
            {
                return rsCatalogo.getInt("last_id");
            }

        } catch (SQLException e){
          System.out.println(e.getMessage());
        }finally{
          try {stmCatalogo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return null;
    }
    
    // Funcion que obtiene el último pedido insertado
    public Integer getLastPedido(){
        PreparedStatement stmCatalogo=null;
        ResultSet rsCatalogo;
        
        String consulta = "SELECT max(p.numeropedido) FROM productos p where p.numeropedido is not null";
        
        Integer numeroPedido = null;
        
        try{
         stmCatalogo=conn.prepareStatement(consulta);         
         rsCatalogo=stmCatalogo.executeQuery();
        
         rsCatalogo.next();
         numeroPedido = rsCatalogo.getInt(1);
        }
        catch (SQLException e){
          System.out.println(e.getMessage());
        }finally{
          try {stmCatalogo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return numeroPedido;
    }

}
