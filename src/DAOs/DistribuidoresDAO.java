package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Modelos.Distribuidores;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DistribuidoresDAO implements DAO<Distribuidores>{
    
    private static DistribuidoresDAO instancia = null; // instancia única de DAOCliente
    private static Connection conn = null; // instancia única de conexion

    // Constructor privado para evitar la creación de más de una instancia
    private DistribuidoresDAO() {
    }

    // Método estático para obtener la única instancia de DAOCliente
    public static DistribuidoresDAO inicializarSingleton(Connection con) {
        if (instancia == null) {
            instancia = new DistribuidoresDAO();
            conn = con;
        }
        
        return instancia;
    }

    final String INSERTAR = "INSERT INTO Distribuidores(idDistribuidor, nombre) VALUES(?, ?)";
    final String MODIFICAR = "UPDATE Distribuidores Set nombre = ? WHERE idDistribuidor = ?";
    final String ELIMINAR = "DELETE FROM Distribuidores WHERE idDistribuidor = ?";
    final String SELECT_ALL = "SELECT * FROM Distribuidores";
    final String BUSCAR_DISTRIBUIDOR = "SELECT idDistribuidor FROM Distribuidores WHERE nombre = ?";    

    // Funcion para insertar un distribuidor en la BD
    @Override
    public void insertar(Distribuidores a) throws SQLException{
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(INSERTAR);
            stat.setInt(1, a.getIdDistribuidor());
            stat.setString(2, a.getNombre());
            stat.executeUpdate();
        }
        catch (SQLException ex) {
            if(ex.getMessage().contains("llave duplicada viola restricción de unicidad «distribuidores_iddistribuidor_key»"))
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

    // Funcion para modificar un distribuidor en la BD
    @Override
    public void modificar(Distribuidores a) throws SQLException{
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(MODIFICAR);
            stat.setString(1, a.getNombre());
            stat.setInt(2, a.getIdDistribuidor());
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

    // Funcion para eliminar un distribuidor en la BD
    @Override
    public void eliminar(Distribuidores a) throws SQLException{
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(ELIMINAR);
            stat.setInt(1, a.getIdDistribuidor());
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
    private Distribuidores convertir(ResultSet rs) throws SQLException
    {
        Integer idDistribuidor = rs.getInt("idDistribuidor") != 0? rs.getInt("idDistribuidor"): null;
        String nombre = rs.getString("nombre");

        Distribuidores distribuidor = new Distribuidores(idDistribuidor, nombre);
        return distribuidor;
    }

    // Esta función hace un select all
    @Override
    public List<Distribuidores> selectAll()
    {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Distribuidores> distribuidores = new ArrayList<>();

        try {
            stat = conn.prepareStatement(SELECT_ALL);
            rs = stat.executeQuery();
            while(rs.next())
            {
                distribuidores.add(convertir(rs));
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
        return distribuidores;
    }
    
    // Esta función busca un distribuidor por su nombre y devuelve su id
    public Integer getIdDistribuidor(String nombre)
    {
        PreparedStatement stm=null;
        ResultSet rs;
        
        try  {
            stm=conn.prepareStatement(BUSCAR_DISTRIBUIDOR);
            stm.setString(1, nombre);
            rs=stm.executeQuery();
            while (rs.next())
            {
                return rs.getInt(1);
            }

        } catch (SQLException ex){
            ex.printStackTrace();
        }finally{
            try {stm.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return null;
    }
}
