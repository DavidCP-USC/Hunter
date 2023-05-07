package DAOs;

import java.sql.SQLException;
import java.util.List;
// Interfaz DAO genérica para definir los métodos CRUD
public interface DAO<T> {
    
    void insertar(T a) throws SQLException;
    
    void modificar(T a) throws SQLException;
    
    void eliminar(T a) throws SQLException;
    
    List<T> selectAll();
    
}
