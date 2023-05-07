package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.SQLException;
import Modelos.EmpresasVendedoras;
import Modelos.Modelos;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpresasVendedorasDAO implements DAO<EmpresasVendedoras>{
    
    private static EmpresasVendedorasDAO instancia = null; // instancia única de DAOCliente
    private static Connection conn = null; // instancia única de conexion

    // Constructor privado para evitar la creación de más de una instancia
    private EmpresasVendedorasDAO() {
    }

    // Método estático para obtener la única instancia de DAOCliente
    public static EmpresasVendedorasDAO inicializarSingleton(Connection con) {
        if (instancia == null) {
            instancia = new EmpresasVendedorasDAO();
            conn = con;
        }
        
        return instancia;
    }
    
    final String INSERTAR = "INSERT INTO EmpresasVendedoras(idEmpresa, nombre, nombreUsuario, contrasenha, esAdmin, fechaAsociacion) VALUES(?, ?, ?, ?, ?, ?)";
    final String MODIFICAR = "UPDATE EmpresasVendedoras SET nombre = ?, nombreUsuario = ?, contrasenha = ?, fechaAsociacion = ? WHERE idEmpresa = ?";
    final String ELIMINAR_BY_ID = "DELETE FROM EmpresasVendedoras WHERE idEmpresas = ?";
    final String SELECT_ALL = "SELECT * FROM EmpresasVendedoras";
    final String ELIMINAR_MODELOS = "DELETE FROM Modelos WHERE empresa = ?";
    final String ELIMINAR_PRODUCTOS = "DELETE FROM Productos WHERE modelo = ?";    

    // Funcion para insertar una EmpresaVendedora en la BD
    @Override
    public void insertar(EmpresasVendedoras a) throws SQLException{
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(INSERTAR);
            stat.setInt(1, a.getIdEmpresa());
            stat.setString(2, a.getNombre());
            stat.setString(3, a.getNombreUsuario());
            stat.setString(4, a.getContrasenha());
            stat.setBoolean(5, a.getEsAdmin());
            stat.setDate(6, new Date(a.getFechaAsociacion().getTime()));
            stat.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            if(ex.getMessage().contains("llave duplicada viola restricción de unicidad «empresasvendedoras_nombreusuario_key»"))
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

    // Funcion para modificar una EmpresaVendedora en la BD
    @Override
    public void modificar(EmpresasVendedoras a) throws SQLException{
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(MODIFICAR);
            stat.setString(1, a.getNombre());
            stat.setString(2, a.getNombreUsuario());
            stat.setString(3, a.getContrasenha());
            stat.setDate(4, new Date(a.getFechaAsociacion().getTime()));
            stat.setInt(5, a.getIdEmpresa());
            stat.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            if(ex.getMessage().contains("llave duplicada viola restricción de unicidad «empresasvendedoras_nombreusuario_key»"))
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

    // Funcion para eliminar una EmpresaVendedora en la BD
    @Override
    public void eliminar(EmpresasVendedoras a) throws SQLException{
        PreparedStatement stat = null;
        
        //Elimino cada uno de los productos que hay por cada modelo de la empresa
        //Necesito seleccionar todos los modelos, invoco al DAO de Modelos para esta tarea
        List<Modelos> modelos = DAOManager.modelos().modelosDeEmpresa(a);
        //Ahora que tengo todos los modelos, voy a tener que eliminar cada producto que tenga el id de cada uno de los modelos
        //Me apoyo en el DAO de los productos para hacerlo
        for(Modelos m : modelos)
        {
            DAOManager.modelos().eliminar(m);
        }
        
        //Ahora toca borrar los modelos
        try {
            stat = conn.prepareStatement(ELIMINAR_MODELOS);
            stat.setInt(1, a.getIdEmpresa());
            stat.executeUpdate();
            if(stat.getUpdateCount() == 0)
                throw new SQLException("no registrado");
        }
        catch (SQLException ex) {

        }
        finally {
            if(stat != null){
                try {
                    stat.close();
                }
                catch (SQLException ex) {

                }
            }
        }
        
        //Borrar la empresa al final
        try {
            stat = conn.prepareStatement(ELIMINAR_BY_ID);
            stat.setInt(1, a.getIdEmpresa());
            stat.executeUpdate();
            if(stat.getUpdateCount() == 0)
                throw new SQLException("no registrado");
        }
        catch (SQLException ex) {

        }
        finally {
            if(stat != null){
                try {
                    stat.close();
                }
                catch (SQLException ex) {

                }
            }
        }
    } 
    
    // Funcion para eliminar una EmpresaVendedora en la BD
    public void eliminarPorCredenciales(String[] datos) throws SQLException{
        PreparedStatement stat = null;
        final String ELIMINAR_POR_CREDENCIALES = "DELETE FROM EmpresasVendedoras WHERE nombreUsuario = '"+datos[0]+"' AND contrasenha = '"+datos[1]+"'";
        try {
            stat = conn.prepareStatement(ELIMINAR_POR_CREDENCIALES);
            stat.executeUpdate();
            if(stat.getUpdateCount() == 0)
                throw new SQLException("no registrado");
        }
        catch (SQLException ex) {
            ex.printStackTrace();
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
    
    // Esta función simplemente coge una fila del result set y te devuelve un objeto con las características de cada tupla
    public EmpresasVendedoras convertir(ResultSet rs) throws SQLException
    {
        Integer idEmpresa = rs.getInt("idEmpresa")!=0? rs.getInt("idEmpresa"):null;
        String nombre = rs.getString("nombre");
        String nombreUsuario = rs.getString("nombreUsuario");
        String contrasenha = rs.getString("contrasenha");
        boolean esAdmin = rs.getBoolean("esAdmin");
        Date fechaAsociacion = rs.getDate("fechaAsociacion");

        EmpresasVendedoras empresa = new EmpresasVendedoras(idEmpresa, nombre, nombreUsuario, contrasenha, esAdmin, fechaAsociacion);
        return empresa;
    }

    // Esta función hace un select all
    @Override
    public List<EmpresasVendedoras> selectAll()
    {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<EmpresasVendedoras> empresas = new ArrayList<>();

        try {
            stat = conn.prepareStatement(SELECT_ALL);
            rs = stat.executeQuery();
            while(rs.next())
            {
                empresas.add(convertir(rs));
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
        return empresas;
    }
    
    // Funciono que devuelve el ultimo id de la tabla
    public Integer getLastId(){
        PreparedStatement stmCatalogo=null;
        ResultSet rsCatalogo;
        
        String consulta = "SELECT MAX(idEmpresa) as last_id FROM EmpresasVendedoras";
        
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
    
    // Funcion que comprueba si existe una empresa con las credenciales dadas
    public List<EmpresasVendedoras> consultarEmpresas(String username){
        java.util.List<EmpresasVendedoras> resultado = new java.util.ArrayList<>();
        EmpresasVendedoras empresaActual;
        PreparedStatement stmCatalogo=null;
        ResultSet rsCatalogo;
        
        String consulta = "SELECT * FROM EmpresasVendedoras where nombreUsuario like '%"+username+"%'";
        
        try  {
         stmCatalogo=conn.prepareStatement(consulta);

         rsCatalogo=stmCatalogo.executeQuery();
        while (rsCatalogo.next())
        {
            empresaActual = new EmpresasVendedoras(rsCatalogo.getInt("idEmpresa"), rsCatalogo.getString("nombre"),rsCatalogo.getString("nombreUsuario"), rsCatalogo.getString("contrasenha"), rsCatalogo.getBoolean("esAdmin"), rsCatalogo.getDate("fechaAsociacion"));
            resultado.add(empresaActual);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
        }finally{
          try {stmCatalogo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    
    // Funcion que consulta los modelos de una empresa
    public List<Modelos> consultarModelos(String nombreModelo, EmpresasVendedoras e){
        java.util.List<Modelos> resultado = new java.util.ArrayList<>();
        PreparedStatement stmCatalogo=null;
        Modelos modeloActual = null;
        ResultSet rsCatalogo;
        
        String consulta = "SELECT * FROM Modelos where empresa = "+e.getIdEmpresa() + " AND nombre LIKE '%"+nombreModelo+"%'";
        
        try  {
         stmCatalogo=conn.prepareStatement(consulta);

         rsCatalogo=stmCatalogo.executeQuery();
        while (rsCatalogo.next())
        {
            modeloActual = new Modelos(rsCatalogo.getInt("idmodelo"), rsCatalogo.getString("nombre"), rsCatalogo.getBigDecimal("preciobase").floatValue(), rsCatalogo.getInt("distribuidor"), rsCatalogo.getInt("empresa"));
            resultado.add(modeloActual);
        }

        } catch (SQLException ex){
          ex.printStackTrace();
        }finally{
          try {stmCatalogo.close();} catch (SQLException ex){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
}
