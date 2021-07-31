package org.kalum.core.models;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Entity
@Table(name="horario")
@NamedQueries({@NamedQuery(name="Horario.findAll", query="from Horario")})
public class Horario implements Serializable{
    private final StringProperty horario_id;
    private final ObjectProperty<Date> horario_inicio;
    private final ObjectProperty<Date>  horario_final;

    private List<Clase> clases;

    public Horario() {
        this.horario_id = new SimpleStringProperty();
        this.horario_inicio = new SimpleObjectProperty<>();
        this.horario_final = new SimpleObjectProperty<>();
    }
    @Override
    public String toString(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        DateFormat formato = new SimpleDateFormat("HH:mm");
        return formato.format(this.getHorario_inicio()).concat(" ").concat(formato.format(this.getHorario_final()));
    }

    @Id
    @Column(name="horario_id")
    public String getHorario_id(){ return this.horario_id.get(); }
    public void setHorario_id(String horario_id){ this.horario_id.set(horario_id); }
    public StringProperty horario_id() { return horario_id; }

    @Column(name="horario_inicio", nullable = false)
    @Temporal(value=TemporalType.TIME)
    public Date getHorario_inicio(){ return this.horario_inicio.get(); }
    public void setHorario_inicio(Date horario_inicio){ this.horario_inicio.set(horario_inicio); }
    public ObjectProperty<Date> horario_inicio() { return horario_inicio; }


    @Column(name="horario_final", nullable = false)
    @Temporal(value=TemporalType.TIME)
    public Date getHorario_final(){ return this.horario_final.get(); }
    public void setHorario_final(Date horario_final){ this.horario_final.set(horario_final); }
    public ObjectProperty<Date> horario_final() { return horario_final; }


    @OneToMany (mappedBy = "horario", fetch = FetchType.LAZY)
    public List<Clase> getClases() {
        return clases;
    }
    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }
}
