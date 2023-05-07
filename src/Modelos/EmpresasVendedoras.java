package Modelos;

import java.sql.Date;
import java.util.Objects;

public class EmpresasVendedoras {
    //Atributos
    private Integer idEmpresa;
    private String nombre;
    private String nombreUsuario;
    private String contrasenha;
    private boolean esAdmin;
    private Date fechaAsociacion;

    public EmpresasVendedoras(Integer idEmpresa, String nombre, String nombreUsuario, String contrasenha, boolean esAdmin, Date fechaAsociacion) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.contrasenha = contrasenha;
        this.esAdmin = esAdmin;
        this.fechaAsociacion = fechaAsociacion;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
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

    public Date getFechaAsociacion() {
        return fechaAsociacion;
    }

    public void setFechaAsociacion(Date fechaAsociacion) {
        this.fechaAsociacion = fechaAsociacion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.idEmpresa);
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
        final EmpresasVendedoras other = (EmpresasVendedoras) obj;
        return Objects.equals(this.idEmpresa, other.idEmpresa);
    }

    @Override
    public String toString() {
        return "EmpresasVendedoras{" + "idEmpresa=" + idEmpresa + ", nombre=" + nombre + ", nombreUsuario=" + nombreUsuario + ", contrasenha=" + contrasenha + ", esAdmin=" + esAdmin + ", fechaAsociacion=" + fechaAsociacion + '}';
    }

    
    
}
