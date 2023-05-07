package Modelos;

import java.sql.Date;
import java.util.Objects;

public class ContactarProblemas {
    //Atributos
    private Integer empresa;
    private Integer cliente;
    private Date fecha;
    private String comentario;

    public ContactarProblemas(Integer empresa, Integer cliente, Date fecha, String comentario) {
        this.empresa = empresa;
        this.cliente = cliente;
        this.fecha = fecha;
        this.comentario = comentario;
    }

    public Integer getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Integer empresa) {
        this.empresa = empresa;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.empresa);
        hash = 53 * hash + Objects.hashCode(this.cliente);
        hash = 53 * hash + Objects.hashCode(this.fecha);
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
        final ContactarProblemas other = (ContactarProblemas) obj;
        if (!Objects.equals(this.empresa, other.empresa)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        return Objects.equals(this.fecha, other.fecha);
    }

    @Override
    public String toString() {
        return "ContactarProblemas{" + "empresa=" + empresa + ", cliente=" + cliente + ", fecha=" + fecha + ", comentario=" + comentario + '}';
    }
    
}
