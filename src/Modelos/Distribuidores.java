package Modelos;

import java.util.Objects;

public class Distribuidores {
    //Atributos
    private Integer idDistribuidor;
    private String nombre;

    public Distribuidores(Integer idDistribuidor, String nombre) {
        this.idDistribuidor = idDistribuidor;
        this.nombre = nombre;
    }

    public Integer getIdDistribuidor() {
        return idDistribuidor;
    }

    public void setIdDistribuidor(Integer idDistribuidor) {
        this.idDistribuidor = idDistribuidor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.idDistribuidor);
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
        final Distribuidores other = (Distribuidores) obj;
        return Objects.equals(this.idDistribuidor, other.idDistribuidor);
    }

    @Override
    public String toString() {
        return "Distribuidores{" + "idDistribuidor=" + idDistribuidor + ", nombre=" + nombre + '}';
    }
    
    
}
