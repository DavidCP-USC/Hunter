package Modelos;

import java.sql.Date;
import java.util.Objects;

public class Clientes {
    //Atributos
    private Integer idCliente;
    private String nombre;
    private String nombreUsuario;
    private String contrasenha;
    private boolean esAdmin;
    private String telefono;
    private Date fechaNacimiento;
    private Cesta cestaCompra;

    public Clientes(Integer idCliente, String nombre, String nombreUsuario, String contrasenha, boolean esAdmin, String telefono, Date fechaNacimiento) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.contrasenha = contrasenha;
        this.esAdmin = esAdmin;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.cestaCompra = new Cesta(this);
    }

    public Integer getIdCliente() {
        return idCliente;
    }
    
    public Cesta getCesta(){
        return cestaCompra;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public boolean getEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.idCliente);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Clientes other = (Clientes) obj;
        return Objects.equals(this.idCliente, other.idCliente);
    }

    @Override
    public String toString() {
        return "Clientes{" + "idCliente=" + idCliente + ", nombre=" + nombre + ", nombreUsuario=" + nombreUsuario + ", contrasenha=" + contrasenha + ", esAdmin=" + esAdmin + ", telefono=" + telefono + ", fechaNacimiento=" + fechaNacimiento + '}';
    }
    
    
    
}
