package org.kalum.core.models;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="salon")
@NamedQueries({@NamedQuery(name="Salon.findAll", query="from Salon")})
public class Salon implements Serializable {
    private final StringProperty salon_id;
    private final IntegerProperty capacidad;
    private final StringProperty descripcion;
    private final StringProperty nombre_salon;
    private List<Clase> clases;
    public Salon() {
        this.salon_id = new SimpleStringProperty();
        this.capacidad = new SimpleIntegerProperty();
        this.descripcion = new SimpleStringProperty();
        this.nombre_salon = new SimpleStringProperty();
    }
    @Override
    public String toString(){
        return this.getNombre_salon();
    }

    @Id
    @Column(name="salon_id")
    public String getSalon_id(){ return this.salon_id.get(); }
    public void setSalon_id(String salon_id){ this.salon_id.set(salon_id); }
    public StringProperty salon_id() { return salon_id; }

    @Column(name="capacidad")
    public Integer getCapacidad(){ return this.capacidad.get(); }
    public void setCapacidad(Integer capacidad){ this.capacidad.set(capacidad); }
    public IntegerProperty capacidad() { return capacidad; }

    @Column(name="descripcion")
    public String getDescripcion(){ return this.descripcion.get(); }
    public void setDescripcion(String descripcion){ this.descripcion.set(descripcion); }
    public StringProperty descripcion() { return descripcion; }

    @Column(name="nombre_salon")
    public String getNombre_salon(){ return this.nombre_salon.get(); }
    public void setNombre_salon(String nombre_salon){ this.nombre_salon.set(nombre_salon); }
    public StringProperty nombreSalon() { return nombre_salon; }

    @OneToMany (mappedBy = "salon", fetch = FetchType.LAZY)
    public List<Clase> getClases() {
        return clases;
    }
    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }
}
