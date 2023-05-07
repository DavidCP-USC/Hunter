package Modelos;

import java.util.Date;
import java.util.Objects;

public class Productos {
    //Atributos
    private Integer idProducto;
    private Integer modelo;
    private Integer cliente;
    private Integer numeroPedido;
    private String direccionEnvio;
    private Float precioTotal;
    private Date fechaSalida;
    private Date fechaLlegada;
    private String estadoLlegada;
    private String transportista;

    public Productos(Integer idProducto, Integer modelo, Integer cliente, Integer numeroPedido, String direccionEnvio, Float precioTotal, Date fechaSalida, Date fechaLlegada, String estadoLlegada, String transportista) {
        this.idProducto = idProducto;
        this.modelo = modelo;
        this.cliente = cliente;
        this.numeroPedido = numeroPedido;
        this.direccionEnvio = direccionEnvio;
        this.precioTotal = precioTotal;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.estadoLlegada = estadoLlegada;
        this.transportista = transportista;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getModelo() {
        return modelo;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public Integer getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(Integer numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public Float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public String getEstadoLlegada() {
        return estadoLlegada;
    }

    public void setEstadoLlegada(String estadoLlegada) {
        this.estadoLlegada = estadoLlegada;
    }

    public String getTransportista() {
        return transportista;
    }

    public void setTransportista(String transportista) {
        this.transportista = transportista;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.idProducto);
        hash = 47 * hash + Objects.hashCode(this.modelo);
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
        final Productos other = (Productos) obj;
        if (!Objects.equals(this.idProducto, other.idProducto)) {
            return false;
        }
        return Objects.equals(this.modelo, other.modelo);
    }

    @Override
    public String toString() {
        return "Productos{" + "idProducto=" + idProducto + ", modelo=" + modelo + ", cliente=" + cliente + ", numeroPedido=" + numeroPedido + ", direccionEnvio=" + direccionEnvio + ", precioTotal=" + precioTotal + ", fechaSalida=" + fechaSalida + ", fechaLlegada=" + fechaLlegada + ", estadoLlegada=" + estadoLlegada + ", transportista=" + transportista + '}';
    }
    
}
