package DAOs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Modelos.Clientes;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientesDAO implements DAO<Clientes>{
    
    private static ClientesDAO instancia = null; // instancia única de DAOCliente
    private static Connection conn = null; // instancia única de conexion

    // Constructor privado para evitar la creación de más de una instancia
    private ClientesDAO() {
    }

    // Método estático para obtener la única instancia de DAOCliente
    public static ClientesDAO inicializarSingleton(Connection con) {
        if (instancia == null) {
            instancia = new ClientesDAO();
            conn = con;
        }
        
        return instancia;
    }

    // Método para insertar un cliente en la BD
    final String INSERTAR = "INSERT INTO Clientes (idCliente, nombre, nombreUsuario, contrasenha, esAdmin, telefono, fechaNacimiento) VALUES(?, ?, ?, ?, ?, ?, ?)";
    final String MODIFICAR = "UPDATE Clientes SET nombre = ?, nombreUsuario = ?, contrasenha = ?, telefono = ? WHERE idCliente = ?";
    final String ELIMINAR_POR_ID = "DELETE FROM Clientes WHERE idCliente = ?";
    final String SELECT_ALL = "SELECT * FROM Clientes";
    
    // Funcion para insertar clientes en la BD
    @Override
    public void insertar(Clientes a) throws SQLException{
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(INSERTAR);
            stat.setInt(1, a.getIdCliente());
            stat.setString(2, a.getNombre());
            stat.setString(3, a.getNombreUsuario());
            stat.setString(4, a.getContrasenha());
            stat.setBoolean(5, a.getEsAdmin());
            stat.setString(6, a.getTelefono());
            stat.setDate(7, new Date(a.getFechaNacimiento().getTime()));
            stat.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            if(ex.getMessage().contains("llave duplicada viola restricción de unicidad «clientes_nombreusuario_key»"))
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

    // Funcion para modificar clientes en la BD
    @Override
    public void modificar(Clientes a) throws SQLException{
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(MODIFICAR);
            stat.setString(1, a.getNombre());
            stat.setString(2, a.getNombreUsuario());
            stat.setString(3, a.getContrasenha());
            stat.setString(4, a.getTelefono());
            stat.setInt(5, a.getIdCliente());
            stat.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            if(ex.getMessage().contains("llave duplicada viola restricción de unicidad «clientes_nombreusuario_key»"))
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

    // Funcion para eliminar clientes en la BD
    @Override
    public void eliminar(Clientes a) throws SQLException{
    PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(ELIMINAR_POR_ID);
            stat.setInt(1, a.getIdCliente());
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
    
    // Funcion para eliminar clientes en la BD
    public void eliminarPorCredenciales(String[] datos) throws SQLException{
        PreparedStatement stat = null;
        final String ELIMINAR_POR_CREDENCIALES = "DELETE FROM Clientes WHERE nombreUsuario = '"+datos[0]+"' AND contrasenha = '"+datos[1]+"'";
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
    public Clientes convertir(ResultSet rs) throws SQLException
    {
        Integer idCliente = rs.getInt("idCliente") != 0? rs.getInt("idCliente"): null;
        String nombre = rs.getString("nombre");
        String nombreUsuario = rs.getString("nombreUsuario");
        String contrasenha = rs.getString("contrasenha");
        boolean esAdmin = rs.getBoolean("esAdmin");
        String telefono = rs.getString("telefono");
        Date fechaNac = rs.getDate("fechaNacimiento");

        Clientes cliente = new Clientes(idCliente, nombre, nombreUsuario, contrasenha, esAdmin, telefono, fechaNac);
        return cliente;
    }

    // Esta función hace un select all
    @Override
    public List<Clientes> selectAll()
    {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Clientes> clientes = new ArrayList<>();

        try {
            stat = conn.prepareStatement(SELECT_ALL);
            rs = stat.executeQuery();
            while(rs.next())
            {
                clientes.add(convertir(rs));
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
        return clientes;
    }
    
    // Esta función busca el ultimo id de la tabla
    public Integer getLastId(){
        PreparedStatement stmCatalogo=null;
        ResultSet rsCatalogo;
        
        String consulta = "SELECT MAX(idCliente) as last_id FROM Clientes";
        
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

    // Esta función busca un cliente por su nombre de usuario
    public List<Clientes> consultarClientes(String username){
        java.util.List<Clientes> resultado = new java.util.ArrayList<>();
        Clientes clienteActual;
        PreparedStatement stmCatalogo=null;
        ResultSet rsCatalogo;
        
        String consulta = "SELECT * FROM Clientes where nombreUsuario like '%"+username+"%'";
        
        try  {
         stmCatalogo=conn.prepareStatement(consulta);

         rsCatalogo=stmCatalogo.executeQuery();
        while (rsCatalogo.next())
        {
            clienteActual = new Clientes(rsCatalogo.getInt("idCliente"), rsCatalogo.getString("nombre"),rsCatalogo.getString("nombreUsuario"), rsCatalogo.getString("contrasenha"), rsCatalogo.getBoolean("esAdmin"), rsCatalogo.getString("telefono"), rsCatalogo.getDate("fechaNacimiento"));
            resultado.add(clienteActual);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
        }finally{
          try {stmCatalogo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
}