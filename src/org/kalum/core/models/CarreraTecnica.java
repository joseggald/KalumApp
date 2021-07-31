package org.kalum.core.models;

import com.lowagie.text.pdf.draw.LineSeparator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "carrera_tecnica")
@NamedQueries({@NamedQuery(name = "CarreraTecnica.findAll", query = "from CarreraTecnica")})
public class CarreraTecnica implements Serializable {

    private final StringProperty codigo_carrera;
    private final StringProperty nombre;
    private List<Clase> clases;
    public CarreraTecnica() {
        this.codigo_carrera= new SimpleStringProperty();
        this.nombre  = new SimpleStringProperty();
    }
    @Override
    public String toString(){
        return this.getNombre();
    }

    @Id
    @Column(name = "codigo_carrera")
    public String getCodigo_carrera() {
        return codigo_carrera.get();
    }

    public StringProperty codigo_carreraProperty() {
        return codigo_carrera;
    }

    public void setCodigo_carrera(String codigo_carrera) {
        this.codigo_carrera.set(codigo_carrera);
    }

    @Column(name = "nombre")
    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    @OneToMany (mappedBy = "carreraTecnica", fetch = FetchType.LAZY)
    public List<Clase> getClases() {
        return clases;
    }

    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }
}