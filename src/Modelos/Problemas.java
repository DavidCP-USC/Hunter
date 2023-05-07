package Modelos;


import java.sql.Date;
import java.util.Objects;

public class Problemas {
    //Atributos
    private Integer empresa;
    private Integer cliente;
    private Integer modelo;
    private Date fecha;
    private String comentario;
    private String numeroPedido;
    private String nombreCliente;


    
    public Problemas(Integer idCliente, Integer empresa, Date fecha, String comentario) {
        this.cliente = idCliente;
        this.empresa = empresa;
        this.fecha = fecha;
        this.comentario = comentario;
    }
    
    public Problemas(String numeroPedido, String comentario) {
        this.numeroPedido = numeroPedido;
        this.comentario = comentario;
    }
    
    public Problemas(Integer idCliente, Integer modelo, Date fecha) {
        this.cliente = idCliente;
        this.modelo = modelo;
        this.fecha = fecha;
    }
    
    
    public Problemas()
    {
        this.cliente = null;
        this.empresa = null;
        this.fecha = null;
        this.comentario = null;
    }

    public Integer getEmpresa() {
        return empresa;
    }

    public Integer getCliente() {
        return cliente;
    }

    public String getComentario() {
        return comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public Integer getModelo() {
        return modelo;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }


    public void setEmpresa(Integer empresa) {
        this.empresa = empresa;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.cliente);
        hash = 59 * hash + Objects.hashCode(this.fecha);
        hash = 59 * hash + Objects.hashCode(this.empresa);
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
        final Problemas other = (Problemas) obj;
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.empresa, other.empresa)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelos{" + "cliente=" + cliente + ", empresa=" + empresa + ", fecha=" + fecha + ", comentario=" + comentario + '}';
    }
    
}
