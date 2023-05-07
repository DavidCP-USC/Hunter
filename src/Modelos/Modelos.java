package Modelos;

import java.util.Objects;

public class Modelos {
    //Atributos
    private Integer idModelo;
    private String nombre;
    private Float precioBase;
    private Float precioMinimo;
    private Integer distribuidor;
    private Integer empresa;
    private String nombreDistribuidor;
    private String nombreVendedor;

    public Modelos(Integer idModelo, String nombre, Float precioBase, Integer distribuidor, Integer empresa) {
        this.idModelo = idModelo;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.distribuidor = distribuidor;
        this.empresa = empresa;
    }
    
    public Modelos()
    {
        this.idModelo = null;
        this.nombre = null;
        this.precioBase = null;
        this.distribuidor = null;
        this.empresa = null;
    }
    
    public String getNombreDistribuidor() {
        return nombreDistribuidor;
    }

    public void setNombreDistribuidor(String nombreDistribuidor) {
        this.nombreDistribuidor = nombreDistribuidor;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }
    
    public Integer getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Integer idModelo) {
        this.idModelo = idModelo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(Float precioBase) {
        this.precioBase = precioBase;
    }

    public Float getPrecioMinimo() {
        return precioMinimo;
    }

    public void setPrecioMinimo(Float precioMinimo) {
        this.precioMinimo = precioMinimo;
    }

    public Integer getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(Integer distribuidor) {
        this.distribuidor = distribuidor;
    }

    public Integer getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Integer empresa) {
        this.empresa = empresa;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.idModelo);
        hash = 59 * hash + Objects.hashCode(this.distribuidor);
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
        final Modelos other = (Modelos) obj;
        if (!Objects.equals(this.idModelo, other.idModelo)) {
            return false;
        }
        if (!Objects.equals(this.distribuidor, other.distribuidor)) {
            return false;
        }
        return Objects.equals(this.empresa, other.empresa);
    }

    @Override
    public String toString() {
        return "Modelos{" + "idModelo=" + idModelo + ", nombre=" + nombre + ", precioBase=" + precioBase + ", precioMinimo=" + precioMinimo + ", distribuidor=" + distribuidor + ", empresa=" + empresa + '}';
    }
    
}
