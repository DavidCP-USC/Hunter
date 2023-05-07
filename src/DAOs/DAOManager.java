package DAOs;

import java.sql.Connection;
import DAOs.ClientesDAO;
import DAOs.DistribuidoresDAO;
import DAOs.ModelosDAO;
import DAOs.EmpresasVendedorasDAO;
import DAOs.ProductosDAO;
import DAOs.TransportistasDAO;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DAOManager {
    private static Connection conn;
    private static ClientesDAO DAOclientes = null;
    private static DistribuidoresDAO DAOdistribuidores = null;
    private static ModelosDAO DAOmodelos = null;
    private static EmpresasVendedorasDAO DAOempresas = null;
    private static ProductosDAO DAOproductos = null;
    private static TransportistasDAO DAOtransportistas = null;
    private static ContactarProblemasDAO DAOContactarProblemas = null;
    
    private DAOManager(){        
    }
    
    // Método estático para obtener la única instancia de DAOManager
    public static void init(String username, String passwd) throws SQLException{
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/amazon", username, passwd);
        DAOclientes = ClientesDAO.inicializarSingleton(conn);
        DAOdistribuidores = DistribuidoresDAO.inicializarSingleton(conn);
        DAOmodelos = ModelosDAO.inicializarSingleton(conn);
        DAOempresas = EmpresasVendedorasDAO.inicializarSingleton(conn);
        DAOproductos = ProductosDAO.inicializarSingleton(conn);
        DAOtransportistas = TransportistasDAO.inicializarSingleton(conn);
        DAOContactarProblemas = ContactarProblemasDAO.inicializarSingleton(conn);
    }

    public static Connection getConn() {
        return conn;
    }   
    
    public static ClientesDAO clientes(){
        return DAOclientes;
    }
    
    public static DistribuidoresDAO distribuidores(){
        return DAOdistribuidores;
    }
    
    public static ModelosDAO modelos(){
        return DAOmodelos;
    }
    
    public static EmpresasVendedorasDAO empresas(){
        return DAOempresas;
    }
    
    public static ProductosDAO productos(){
        return DAOproductos;
    }
    
    public static TransportistasDAO transportistas(){
        return DAOtransportistas;
    }
    
    public static ContactarProblemasDAO problemas(){
        return DAOContactarProblemas;
    }
}
