package DAOs;

import Modelos.Clientes;
import Modelos.EmpresasVendedoras;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import Modelos.Problemas;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContactarProblemasDAO implements DAO<Problemas>{
    
    private static ContactarProblemasDAO instancia = null; // instancia única de DAOCliente
    private static Connection conn = null; // instancia única de conexion

    // Constructor privado para evitar la creación de más de una instancia
    private ContactarProblemasDAO() {
    }

    // Método estático para obtener la única instancia de DAOCliente
    public static ContactarProblemasDAO inicializarSingleton(Connection con) {
        if (instancia == null) {
            instancia = new ContactarProblemasDAO();
            conn = con;
        }
        
        return instancia;
    }
    
    final String INSERTAR = "INSERT INTO ContactarProblemas(empresa, cliente, fecha, comentario) VALUES(?, ?, ?, ?)";
    final String MODIFICAR = "UPDATE ContactarProblemas Set empresa = ?, cliente = ?, fecha = ?, comentario = ? WHERE empresa = ? AND cliente = ? AND fecha = ?";
    final String ELIMINAR = "DELETE FROM ContactarProblemas WHERE empresa = ? AND cliente = ? AND fecha = ?";
    final String SELECT_ALL = "SELECT * FROM ContactarProblemas";
   
    // Funcion para insertar un problema nuevo en la BD
    @Override
    public void insertar(Problemas a) throws SQLException{
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(INSERTAR);
            
            if(a.getEmpresa() == null)
                stat.setNull(1, Types.INTEGER);
            else
                stat.setInt(1, a.getEmpresa());
            if (a.getCliente() == null) {
                stat.setNull(2, Types.INTEGER);
            } else {
                stat.setInt(2, a.getCliente());
            }
            if(a.getFecha() == null){
                stat.setNull(3, Types.DATE);
            }
            else{
                stat.setDate(3, a.getFecha());
            }
            stat.setString(4, a.getComentario());
            stat.executeUpdate();
        }
        catch (SQLException ex) {
            if(ex.getMessage().contains("llave duplicada viola restricción de unicidad «contactarproblemas_pkey»"))
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

    // Funcion para modificar la tabla de contactarProblemas en la BD
    @Override
    public void modificar(Problemas a) throws SQLException{
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(MODIFICAR);
            if(a.getEmpresa() == null){
                stat.setNull(1, Types.INTEGER);
            }
            else{
                stat.setInt(1, a.getEmpresa());
            }
            if(a.getCliente() == null){
                stat.setNull(2, Types.INTEGER);
            }
            else{
                stat.setInt(2, a.getCliente());
            }
            if(a.getFecha() == null){
                stat.setNull(3, Types.DATE);
            }
            else{
                stat.setDate(3, a.getFecha());
            }
            stat.setString(4, a.getComentario());
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

    // Funcion para eliminar un problema de la BD
    @Override
    public void eliminar(Problemas a) throws SQLException{
    PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(ELIMINAR);
            if(a.getEmpresa() == null){
                stat.setNull(1, Types.INTEGER);
            }
            else{
                stat.setInt(1, a.getEmpresa());
            }
            if(a.getCliente() == null){
                stat.setNull(2, Types.INTEGER);
            }
            else{
                stat.setInt(2, a.getCliente());
            }
            if(a.getFecha() == null){
                stat.setNull(3, Types.DATE);
            }
            else{
                stat.setDate(3, a.getFecha());
            }
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
    
    // Funcion para comprobar si existe algun pedido con el numero de pedido que se le pasa
    public Problemas comprobarNumeroPedido(Problemas p) throws SQLException {
        final String SELECT = "SELECT * FROM productos WHERE numeropedido = '" + p.getNumeroPedido()+"';";
        PreparedStatement stat = null;
        ResultSet rs = null;
        Problemas auxiliar = new Problemas();
        try {
            stat = conn.prepareStatement(SELECT);
            rs = stat.executeQuery();
            rs.next(); // Solo puede haber una salida porque es de tipo UNIQUE
            auxiliar = convertirProblema(rs);
            System.out.println(auxiliar.getModelo());
            p.setModelo(auxiliar.getModelo());
            p.setCliente(auxiliar.getCliente());
            p.setFecha(auxiliar.getFecha());
            p.setModelo(auxiliar.getModelo());
            return p;
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
        return p;
    }
    
    // Funcion que comprueba si el cliente que hizo el pedido es el mismo que quiere contactar con la empresa
    public boolean comprobarCliente(Problemas p, Clientes c){
        return p.getCliente().equals(c.getIdCliente());
    }
    
    
    // Esta función simplemente coge una fila del result set y te devuelve un objeto con las características de cada tupla
    private Problemas convertir(ResultSet rs) throws SQLException {
        Integer empresa = rs.getInt("empresa") != 0? rs.getInt("empresa"): null;
        Integer cliente = rs.getInt("cliente") != 0? rs.getInt("cliente"): null;
        Date fecha = rs.getInt("cliente") != 0? rs.getDate("fecha"): null;
        String comentario = rs.getString("comentario");        
        Problemas p = new Problemas(cliente, empresa, fecha, comentario);
        return p;
    }
    
    // Esta función simplemente coge una fila del result set y te devuelve un objeto con las características de cada tupla
    private Problemas convertirProblema(ResultSet rs) throws SQLException {
        Integer cliente = rs.getInt("cliente") != 0? rs.getInt("cliente"): null;
        Integer modelo = rs.getInt("modelo") != 0? rs.getInt("modelo"): null;
        Date fecha = null;
        if (rs.getInt("cliente") != 0){
            fecha = Date.valueOf(LocalDate.now());
        }      
        Problemas p = new Problemas(cliente, modelo, fecha);
        return p;
    }

    // Esta función hace un select all
    @Override
    public List<Problemas> selectAll()
    {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Problemas> p = new ArrayList<>();

        try {
            stat = conn.prepareStatement(SELECT_ALL);
            rs = stat.executeQuery();
            while(rs.next())
            {
                p.add(convertir(rs));
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
        return p;
    }
    
    // Funcion que anhade el nombre de un cliente al problema
    public void actualizarNombresClientes(Problemas m){
        PreparedStatement stmCatalogo=null;
        ResultSet rsCatalogo;
        
        String consulta = "SELECT nombre AS nombre_cliente FROM clientes WHERE idcliente = " + m.getCliente() + ";";
        
        try  {
            stmCatalogo=conn.prepareStatement(consulta);
            rsCatalogo=stmCatalogo.executeQuery();
            rsCatalogo.next(); // Solo puede salir un resultado porque es una PK
            m.setNombreCliente(rsCatalogo.getString("nombre_cliente"));
        } catch (SQLException e){
            e.printStackTrace();
        }finally{
          try {stmCatalogo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    
    // Funcion que anhade el nombre de un la empresa al problema
    public void actualizarIdEmpresa(Problemas m){
        PreparedStatement stmCatalogo=null;
        ResultSet rsCatalogo;
        
        String consulta = "SELECT empresa AS id_empresa FROM modelos WHERE idmodelo = " + m.getModelo() + ";";
        
        try  {
            stmCatalogo=conn.prepareStatement(consulta);
            rsCatalogo=stmCatalogo.executeQuery();
            rsCatalogo.next(); // Solo puede salir un resultado porque es una PK
            m.setEmpresa(Integer.valueOf(rsCatalogo.getString("id_empresa")));
        } catch (SQLException e){
            e.printStackTrace();
        }finally{
          try {stmCatalogo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
}
