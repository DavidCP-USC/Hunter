package Conexion;

import Modelos.Clientes;
import Modelos.EmpresasVendedoras;
import DAOs.DAOManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    public static Clientes loginCliente(Connection conexion, String usuario, String password) throws SQLException {  
        Statement statement = null;
        ResultSet set = null;
        Clientes c = null;
        // Se crea un Statement, para realizar la consulta de login de cliente
        try {
            statement = conexion.createStatement();
            set = statement.executeQuery("select idCliente, nombre, nombreUsuario, contrasenha, esAdmin, telefono, fechaNacimiento from Clientes where nombreUsuario = '" + usuario + "' and contrasenha = '" + password + "';");
            if (set.next()){
                c = DAOManager.clientes().convertir(set);
            }
            set.close();            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (set != null) {
                set.close();
            }
            if (statement != null) {
                statement.close();
            }
    }
              
        return c;
    }
    
    public static EmpresasVendedoras loginEmpresa(Connection conexion, String usuario, String password) throws SQLException {  
        Statement statement = null;
        ResultSet set = null;
        EmpresasVendedoras e = null;
        // Se crea un Statement, para realizar la consulta de login de empresa
        try {
            statement = conexion.createStatement();
            set = statement.executeQuery("select idEmpresa, nombre, nombreUsuario, contrasenha, esAdmin, fechaAsociacion from EmpresasVendedoras where nombreUsuario = '" + usuario + "' and contrasenha = '" + password + "';");
            if (set.next()){
                e = DAOManager.empresas().convertir(set);
            }
            set.close();            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (set != null) {
                set.close();
            }
            if (statement != null) {
                statement.close();
            }
    }
              
    return e;
    }
}
