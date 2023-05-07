package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Modelos.Transportistas;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransportistasDAO implements DAO<Transportistas>{
    
    private static TransportistasDAO instancia = null; // instancia única de DAOCliente
    private static Connection conn = null; // instancia única de conexion

    // Constructor privado para evitar la creación de más de una instancia
    private TransportistasDAO() {
    }

    // Método estático para obtener la única instancia de DAOCliente
    public static TransportistasDAO inicializarSingleton(Connection con) {
        if (instancia == null) {
            instancia = new TransportistasDAO();
            conn = con;
        }
        
        return instancia;
    }
    
    final String INSERTAR = "INSERT INTO Transportistas(dni, nombre, telefono) VALUES(?, ?, ?)";
    final String MODIFICAR = "UPDATE Transportistas Set nombre = ?, telefono = ? WHERE dni = ?";
    final String ELIMINAR = "DELETE FROM Transportistas WHERE dni = ?";
    final String SELECT_ALL = "SELECT * FROM Transportistas";

    // Funcion para insertar un transportista en la BD
    @Override
    public void insertar(Transportistas a) throws SQLException{
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(INSERTAR);
            stat.setString(1, a.getDni());
            stat.setString(2, a.getNombre());
            stat.setString(3, a.getTelefono());
            stat.executeUpdate();
        }
        catch (SQLException ex) {
            if(ex.getMessage().contains("llave duplicada viola restricción de unicidad «transportistas_dni_key»"))
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

    // Funcion para modificar un transportista en la BD
    @Override
    public void modificar(Transportistas a) throws SQLException{
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(MODIFICAR);
            stat.setString(1, a.getNombre());
            stat.setString(2, a.getTelefono());
            stat.setString(3, a.getDni());
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

    // Funcion para eliminar un transportista en la BD
    @Override
    public void eliminar(Transportistas a) throws SQLException{
    PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(ELIMINAR);
            stat.setString(1, a.getDni());
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
    private Transportistas convertir(ResultSet rs) throws SQLException
    {
        String dni = rs.getString("dni");
        String nombre = rs.getString("nombre");
        String telefono = rs.getString("telefono");

        Transportistas transportista = new Transportistas(dni, nombre, telefono);
        return transportista;
    }

    // Esta función hace un select all
    @Override
    public List<Transportistas> selectAll()
    {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Transportistas> transportistas = new ArrayList<>();

        try {
            stat = conn.prepareStatement(SELECT_ALL);
            rs = stat.executeQuery();
            while(rs.next())
            {
                transportistas.add(convertir(rs));
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
        return transportistas;
    }

}
