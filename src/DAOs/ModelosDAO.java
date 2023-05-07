package DAOs;

import Modelos.Cesta;
import Modelos.EmpresasVendedoras;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Modelos.Modelos;
import Modelos.Productos;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ModelosDAO implements DAO<Modelos>{
    
    private static ModelosDAO instancia = null; // instancia única de DAOCliente
    private static Connection conn = null; // instancia única de conexion

    // Constructor privado para evitar la creación de más de una instancia
    private ModelosDAO() {
    }

    // Método estático para obtener la única instancia de DAOCliente
    public static ModelosDAO inicializarSingleton(Connection con) {
        if (instancia == null) {
            instancia = new ModelosDAO();
            conn = con;
        }
        
        return instancia;
    }
    
    final String INSERTAR = "INSERT INTO Modelos(idModelo, nombre, precioBase, distribuidor, empresa) VALUES(?, ?, ?, ?, ?)";
    final String MODIFICAR = "UPDATE Modelos Set nombre = ?, precioBase = ?, distribuidor = ?, empresa = ? WHERE idModelo = ?";
    final String ELIMINAR = "DELETE FROM Modelos WHERE idModelo = ?";
    final String SELECT_ALL = "SELECT * FROM Modelos";
    final String SELECT_BY_ID = "SELECT * FROM Modelos WHERE idModelo = ?";
    final String BUSCAR_MODELO = "SELECT idModelo FROM Modelos WHERE nombre = ?";
    final String SELECT_ALL_BY_EMPRESA = "SELECT idModelo FROM Modelos WHERE empresa = ?";

    // Funcion para insertar un modelo en la BD
    @Override
    public void insertar(Modelos a) throws SQLException{
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(INSERTAR);
            stat.setInt(1, a.getIdModelo());
            stat.setString(2, a.getNombre());
            stat.setFloat(3, a.getPrecioBase());
            stat.setInt(4, a.getDistribuidor());
            stat.setInt(5, a.getEmpresa());
            stat.executeUpdate();
        }
        catch (SQLException ex) {
            if(ex.getMessage().contains("llave duplicada viola restricción de unicidad «modelos_nombre_key»"))
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

    // Funcion para modificar un modelo en la BD
    @Override
    public void modificar(Modelos a) throws SQLException{
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(MODIFICAR);
            stat.setString(1, a.getNombre());
            stat.setFloat(2, a.getPrecioBase());
            stat.setInt(3, a.getDistribuidor());
            stat.setInt(4, a.getIdModelo());
            stat.setInt(5, a.getEmpresa());
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

    // Funcion para eliminar un modelo en la BD
    @Override
    public void eliminar(Modelos a) throws SQLException{
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(ELIMINAR);
            stat.setLong(1, a.getIdModelo());
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
    private Modelos convertir(ResultSet rs) throws SQLException
    {
        Integer idModelo = rs.getInt("idModelo") != 0? rs.getInt("idModelo"): null;
        String nombre = rs.getString("nombre");
        Float precioBase = rs.getFloat("precioBase");
        Integer distribuidor = rs.getInt("distribuidor") != 0? rs.getInt("distribuidor"): null;
        Integer empresa = rs.getInt("empresa") != 0? rs.getInt("empresa"): null;

        Modelos modelo = new Modelos(idModelo, nombre, precioBase, distribuidor, empresa);
        return modelo;
    }

    // Esta función hace un select all
    @Override
    public List<Modelos> selectAll()
    {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Modelos> modelos = new ArrayList<>();

        try {
            stat = conn.prepareStatement(SELECT_ALL);
            rs = stat.executeQuery();
            while(rs.next())
            {
                modelos.add(convertir(rs));
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
        return modelos;
    }
    
    // Funcion que obtiene el nombre de la empresa y del distribuidor de un modelo
    public void obtenerNombresEmpresas(Modelos m){
        PreparedStatement stmCatalogo=null;
        ResultSet rsCatalogo;
        
        String consulta = "SELECT e.nombre AS nombre_empresa, d.nombre AS nombre_distribuidor FROM empresasvendedoras e, distribuidores d WHERE e.idempresa = ? AND d.iddistribuidor = ?";
        
        try  {
            stmCatalogo=conn.prepareStatement(consulta);
            stmCatalogo.setInt(1, m.getEmpresa());
            stmCatalogo.setInt(2, m.getDistribuidor());
            rsCatalogo=stmCatalogo.executeQuery();
        while (rsCatalogo.next())
        {
            m.setNombreVendedor(rsCatalogo.getString("nombre_empresa"));
            m.setNombreDistribuidor(rsCatalogo.getString("nombre_distribuidor"));
        }

        } catch (SQLException e){
            e.printStackTrace();
        }finally{
          try {stmCatalogo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    
    // Funcion para consultar el catalogo disponible de modelos en funcion de los parametros dados
    public java.util.List<Modelos> consultarCatalogoDisponible(String nombre, Float precioMin, Float precioMax, Cesta cesta){
        java.util.List<Modelos> resultado = new java.util.ArrayList<>();
        Modelos modeloActual;
        PreparedStatement stmCatalogo=null;
        ResultSet rsCatalogo;
        
        String condicionAdicional = " ";
        
        // Anhadimos una condición adicional a la consulta para que no aparezcan los modelos que ya están en la cesta
        for (Productos producto : cesta.getProductos()) { 
            condicionAdicional += "AND p.idproducto != " + producto.getIdProducto() + " ";
        }
        
        String consulta = "SELECT m.*, (SELECT MIN(p.precioTotal) FROM productos p WHERE p.modelo = m.idmodelo AND p.cliente IS null";
        
        // Anhadimos las condiciones de precio máximo y mínimo
        if(precioMax != null) 
            consulta +=  " AND p.precioTotal <= " + precioMax;
        
        if(precioMin != null)
            consulta +=  " AND p.precioTotal >= " + precioMin;
        
        consulta += condicionAdicional;
        
        consulta += ") AS precio_minimo  FROM modelos m WHERE m.nombre like '%"+nombre+"%' AND EXISTS (SELECT * FROM productos p WHERE p.modelo = m.idmodelo AND p.cliente IS NULL";        
        
        if(precioMax != null)
            consulta +=  " AND p.precioTotal <= " + precioMax;
        
        if(precioMin != null)
            consulta +=  " AND p.precioTotal >= " + precioMin;
        
        consulta += condicionAdicional + ")";
        
        try  {
         stmCatalogo=conn.prepareStatement(consulta);

         rsCatalogo=stmCatalogo.executeQuery();
        while (rsCatalogo.next())
        {
            modeloActual = new Modelos(rsCatalogo.getInt("idmodelo"), rsCatalogo.getString("nombre"),rsCatalogo.getFloat("precioBase"), rsCatalogo.getInt("distribuidor"), rsCatalogo.getInt("empresa"));
            if(rsCatalogo.getBigDecimal("precio_minimo") != null) modeloActual.setPrecioMinimo(rsCatalogo.getBigDecimal("precio_minimo").floatValue());
            resultado.add(modeloActual);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
        }finally{
          try {stmCatalogo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    
    // Funcion que devuelve el id del último modelo insertado
    public Integer getLastId(){
        PreparedStatement stmCatalogo=null;
        ResultSet rsCatalogo;
        
        String consulta = "SELECT MAX(idModelo) as last_id FROM Modelos";
        
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
    
    // Funcion que devuelve el id de un modelo a partir de su nombre
    public Integer getIdModelo(String nombre)
    {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Integer id = null;

        try {
            stat = conn.prepareStatement(BUSCAR_MODELO);
            stat.setString(1, nombre);
            rs = stat.executeQuery();
            while(rs.next())
            {
                id = rs.getInt(1);
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
        return id;
    }
        
    // Funcion que devuelve los modelos de una empresa
    public List<Modelos> modelosDeEmpresa(EmpresasVendedoras e)
    {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Modelos> modelos = new ArrayList<>();

        try {
            stat = conn.prepareStatement(SELECT_ALL_BY_EMPRESA);
            stat.setInt(1, e.getIdEmpresa());
            rs = stat.executeQuery();
            while(rs.next())
            {
                modelos.add(convertir(rs));
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
        return modelos;
    }
    
    // Funcion que devuelve un modelo a partir de su id
    public Modelos getById(Integer id){
        PreparedStatement stat = null;
        ResultSet rs = null;
        
        try {
            stat = conn.prepareStatement(SELECT_BY_ID);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            while(rs.next())
            {
                return convertir(rs);
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
        return null;
    }
}
