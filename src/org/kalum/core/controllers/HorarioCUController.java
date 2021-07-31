package org.kalum.core.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.Horario;

import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.TimeZone;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;

public class HorarioCUController implements Initializable {
    private Horario horario;
    private Main escenarioPrincipal;


    @FXML private ComboBox<String> cmbHorarioInicio;
    @FXML private ComboBox<String> cmbHorarioFinal;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,new String[]{"7:10:00", "8:00:00","8:50:00","9:00:00","9:50:00","10:40:00","11:30:00","12:20:00","13:30:00"});
       cmbHorarioFinal.getItems().addAll(list);
       cmbHorarioInicio.getItems().addAll(list);
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void toDate(){

    }
    Date date1=null;
    Date date2=null;

    public void guardar(){
        try{
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
            DateFormat formato = new SimpleDateFormat("HH:mm");
            date1=(Date)formato.parse(String.valueOf(cmbHorarioInicio.getSelectionModel().getSelectedItem()));
            date2=(Date)formato.parse(String.valueOf(cmbHorarioFinal.getSelectionModel().getSelectedItem()));

            if (this.horario== null){
                Horario nuevo = new Horario();
                nuevo.setHorario_id(UUID.randomUUID().toString());
                nuevo.setHorario_inicio(date1);
                nuevo.setHorario_final(date2);
                ConexionPU.getInstancia().agregar(nuevo);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Kalum v1.0.0");
                aviso.setHeaderText("Administración de Horarios.");
                aviso.setContentText("REGISTRO ALMACENADO");
                aviso.show();
            }else{
                horario.setHorario_inicio(date1);;
                horario.setHorario_final(date2);
                ConexionPU.getInstancia().agregar(horario);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Kalum v1.0.0");
                aviso.setHeaderText("Administración de Horarios.");
                aviso.setContentText("REGISTRO MODIFICADO");
                aviso.show();
            }
        }catch(KalumException | ParseException e){
            KalumViewMessage.getInstancia().mostrarMensaje("Administación de Horarios.","",e.getMessage(),50303);
        }
        this.escenarioPrincipal.cambiarEscenaHorarios();
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
        this.cmbHorarioInicio.getSelectionModel().select(horario.getHorario_inicio().toString());
        this.cmbHorarioFinal.getSelectionModel().select(horario.getHorario_final().toString());
    }
}
