package org.kalum.core.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.Horario;
import org.kalum.core.models.Salon;
import org.kalum.core.reports.GenerarReporte;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.*;

public class HorarioController implements Initializable{
    private Main escenarioPrincipal;
    @FXML
    private TableView<Horario> tblHorarios;
    @FXML
    private TableColumn<Horario, String> colHorario_Id;
    @FXML
    private TableColumn<Horario, Date> colHorario_inicio;
    @FXML
    private TableColumn<Horario, Date> colHorario_final;

    private ObservableList<Horario> listaHorarios;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        try {
            this.listaHorarios = FXCollections.observableArrayList((List<Horario>)ConexionPU.getInstancia().findAll("Horario.findAll"));
        }catch(KalumException e) {
            KalumViewMessage.getInstancia().mostrarMensaje("Administación de Horarios:","",e.getMessage(),50302);
        }

        this.tblHorarios.setItems(this.listaHorarios);
        this.colHorario_Id.setCellValueFactory(cellData -> cellData.getValue().horario_id());
        this.colHorario_inicio.setCellValueFactory(cellData -> cellData.getValue().horario_inicio());
        this.colHorario_final.setCellValueFactory(cellData -> cellData.getValue().horario_final());

    }
    public void actualizar(){
        try {
            this.listaHorarios = FXCollections.observableArrayList((List<Horario>)ConexionPU.getInstancia().findAll("Horario.findAll"));
        }catch(KalumException e) {
            KalumViewMessage.getInstancia().mostrarMensaje("Administación de Horarios:","",e.getMessage(),50302);
        }

        this.tblHorarios.setItems(this.listaHorarios);
        this.colHorario_Id.setCellValueFactory(cellData -> cellData.getValue().horario_id());
        this.colHorario_inicio.setCellValueFactory(cellData -> cellData.getValue().horario_inicio());
        this.colHorario_final.setCellValueFactory(cellData -> cellData.getValue().horario_final());
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    public void nuevo(){
        this.escenarioPrincipal.cambiarEscenaHorariosCU();
    }

    public void salir(){
        this.escenarioPrincipal.cambiarEscenaMain();
    }

    public void modificar(){
        if(this.tblHorarios.getSelectionModel().getSelectedItem()== null){
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de Horarios.");
            aviso.setContentText("Debe de seleccionar un registro.");
            aviso.show();
        }else{
            this.escenarioPrincipal.cambiarEscenaHorariosCU(this.tblHorarios.getSelectionModel().getSelectedItem());
        }
    }
    public void eliminar(){
        if(this.tblHorarios.getSelectionModel().getSelectedItem()== null){
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de Horarios");
            aviso.setContentText("Debe de seleccionar un registro.");
            aviso.show();
        }else {
            Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de Horarios");
            aviso.setContentText("Esta seguro de eliminar?");
            Optional<ButtonType> result = aviso.showAndWait();
            if (result.get() == ButtonType.OK) {
                int posicion = tblHorarios.getSelectionModel().getSelectedIndex();
                ConexionPU.getInstancia().eliminar(this.tblHorarios.getSelectionModel().getSelectedItem());
                this.listaHorarios.remove(posicion);
            }
        }
    }
    public void mostrarReporte(){
        Map parametros = new HashMap();
        parametros.put("LOGO_KALUM",getClass().getResourceAsStream("/org/kalum/resource/images/7.png"));
        GenerarReporte.getInstancia().mostrarReporte("Lista de Horarios", "ReporteHorarios.jasper", parametros);
    }

}
