package org.kalum.core.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.Alumno;

import java.util.*;

import java.net.URL;


import org.kalum.core.reports.GenerarReporte;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

public class AlumnoController implements Initializable{

    private Main escenarioPrincipal;


    @FXML private TableView<Alumno> tblAlumno;
    @FXML private TableColumn<Alumno, String> colCarne;
    @FXML private TableColumn<Alumno, String> colApellidos;
    @FXML private TableColumn<Alumno, String> colNombres;
    @FXML private TableColumn<Alumno, String> colNoExp;
    @FXML private TableColumn<Alumno, String> colEmail;

    private ObservableList<Alumno> ListaAlumnos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            this.ListaAlumnos = FXCollections.observableArrayList((List<Alumno>)ConexionPU.getInstancia().findAll("Alumno.findAll"));
        }catch(KalumException e) {
            KalumViewMessage.getInstancia().mostrarMensaje("Administación de Alumnos.:","",e.getMessage(),50302);
        }
       this.tblAlumno.setItems(this.ListaAlumnos);
       this.colCarne.setCellValueFactory(cellData -> cellData.getValue().carne());
        this.colApellidos.setCellValueFactory(cellData -> cellData.getValue().apellidos());
        this.colEmail.setCellValueFactory(cellData -> cellData.getValue().email());
        this.colNoExp.setCellValueFactory(cellData -> cellData.getValue().noExpediente());
        this.colNombres.setCellValueFactory(cellData -> cellData.getValue().nombres());
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {

        this.escenarioPrincipal = escenarioPrincipal;
    }
    public void cambiarMenu(){
        this.escenarioPrincipal.cambiarEscenaMain();
    }
    public void cambioCU(){
        this.escenarioPrincipal.cambiarEscenaAlumnoCU();
    }

    public void ventaModificar(){
        if(this.tblAlumno.getSelectionModel().getSelectedItem()== null)
        {
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de alumnos");
            aviso.setContentText("Debe de seleccionar un registro.");
            aviso.show();
        } else{
            this.escenarioPrincipal.cambiarEscenaAlumnoCU(this.tblAlumno.getSelectionModel().getSelectedItem());

        }

    }
    public void eliminarAlumno(){
        if(this.tblAlumno.getSelectionModel().getSelectedItem()== null)
        {
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de alumnos");
            aviso.setContentText("Debe de seleccionar un registro.");
            aviso.show();
        } else{
            Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de alumnos");
            aviso.setContentText("Esta seguro de eliminar?");
            Optional<ButtonType> result = aviso.showAndWait();
            if(result.get() == ButtonType.OK){
                int posicion = tblAlumno.getSelectionModel().getSelectedIndex();
                ConexionPU.getInstancia().eliminar(this.tblAlumno.getSelectionModel().getSelectedItem());
                this.ListaAlumnos.remove(posicion);
            }
        }
    }

    public void mostrarReporte(){
        Map parametros = new HashMap();
        parametros.put("LOGO_KALUM",getClass().getResourceAsStream("/org/kalum/resource/images/7.png"));
        GenerarReporte.getInstancia().mostrarReporte("Lista de alumnos", "ReporteAlumnos.jasper", parametros);
    }


}
