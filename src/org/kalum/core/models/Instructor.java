package org.kalum.core.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "instructor")
@NamedQueries({@NamedQuery(name = "Instructor.findAll", query = "from Instructor")})

public class Instructor implements Serializable {

    private final StringProperty instructor_id;
    private final StringProperty apellidos;
    private final StringProperty nombres;
    private final StringProperty direccion;
    private final StringProperty estatus;
    private final StringProperty telefono;
    private final StringProperty cometario;
    private final StringProperty foto;
    private List<Clase> clases;
    public Instructor() {
        this.instructor_id = new SimpleStringProperty();
        this.apellidos = new SimpleStringProperty();
        this.nombres = new SimpleStringProperty();
        this.direccion = new SimpleStringProperty();
        this.estatus = new SimpleStringProperty();
        this.telefono = new SimpleStringProperty();
        this.cometario = new SimpleStringProperty();
        this.foto = new SimpleStringProperty();
    }

    @Override
    public String toString(){
        return this.getApellidos().concat(" ").concat(getNombres());
    }
    @Id
    @Column(name = "instructor_id")
    public String getInstructor_id() {
        return this.instructor_id.get();
    }

    public void setInstructor_id(String instructor_id) {
        this.instructor_id.set(instructor_id);
    }

    public StringProperty instructor_id() {
        return instructor_id;
    }

    @Column(name = "apellidos")
    public String getApellidos() {
        return this.apellidos.get();
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }

    public StringProperty apellidos() {
        return apellidos;
    }

    @Column(name = "nombres")
    public String getNombres() {
        return this.nombres.get();
    }

    public void setNombres(String nombres) {
        this.nombres.set(nombres);
    }

    public StringProperty nombres() {
        return nombres;
    }

    @Column(name = "direccion")
    public String getDireccion() {
        return this.direccion.get();
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public StringProperty direccion() {
        return direccion;
    }

    @Column(name = "telefono")
    public String getTelefono() {
        return this.telefono.get();
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public StringProperty telefono() {
        return telefono;
    }

    @Column(name = "cometario")
    public String getCometario() {
        return this.cometario.get();
    }

    public void setCometario(String cometario) {
        this.cometario.set(cometario);
    }

    public StringProperty cometario() {
        return cometario;
    }

    @Column(name = "foto")
    public String getFoto() {
        return this.foto.get();
    }

    public void setFoto(String foto) {
        this.foto.set(foto);
    }

    public StringProperty foto() {
        return foto;
    }

    @Column(name = "estatus")
    public String getEstatus() {
        return this.estatus.get();
    }

    public void setEstatus(String estatus) {
        this.estatus.set(estatus);
    }

    public StringProperty estatus() {
        return estatus;
    }

    @OneToMany (mappedBy = "instructor", fetch = FetchType.LAZY)
    public List<Clase> getClases() {
        return clases;
    }
    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }
}
