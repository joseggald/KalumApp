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

import org.kalum.core.models.Instructor;
import org.kalum.core.reports.GenerarReporte;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;

import java.net.URL;
import java.util.*;

public class InstructorController implements Initializable {
    private Main escenarioPrincipal;

    private ObservableList<Instructor> listaInstructor;
    @FXML private TableView<Instructor> tblInstructor;
    @FXML private TableColumn<Instructor, String> colId;
    @FXML private TableColumn<Instructor, String> colNom;
    @FXML private TableColumn<Instructor, String> colApe;
    @FXML private TableColumn<Instructor, String> colComen;
    @FXML private TableColumn<Instructor, String> colDic;
    @FXML private TableColumn<Instructor, String> colTel;
    @FXML private TableColumn<Instructor, String> colEstatus;
    @FXML private TableColumn<Instructor, String> colFoto;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            listaInstructor = FXCollections.observableArrayList((List<Instructor>) ConexionPU.getInstancia().findAll("Instructor.findAll"));
        }catch(KalumException e) {
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de Instructores.");
            aviso.setContentText(e.getMessage());
            aviso.show();
        }
        this.tblInstructor.setItems(listaInstructor);
        this.colId.setCellValueFactory(cellId -> cellId.getValue().instructor_id());
        this.colNom.setCellValueFactory(cellNom -> cellNom.getValue().nombres());
        this.colApe.setCellValueFactory(cellApe -> cellApe.getValue().apellidos());
        this.colComen.setCellValueFactory(cellComen -> cellComen.getValue().cometario());
        this.colDic.setCellValueFactory(cellDic -> cellDic.getValue().direccion());
        this.colTel.setCellValueFactory(cellTel -> cellTel.getValue().telefono());
        this.colEstatus.setCellValueFactory(cellEstatus -> cellEstatus.getValue().estatus());
        this.colFoto.setCellValueFactory(cellFoto-> cellFoto.getValue().foto());
    }
    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void nuevo(){
        this.escenarioPrincipal.cambiarEscenaInstructorCU();
    }
    public void salir(){
        this.escenarioPrincipal.cambiarEscenaMain();
    }
    public void modificar(){
        if(this.tblInstructor.getSelectionModel().getSelectedItem()== null){
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de Instructores.");
            aviso.setContentText("Debe de seleccionar un registro.");
            aviso.show();
        }else{
            this.escenarioPrincipal.cambiarEscenaInstructorCU(this.tblInstructor.getSelectionModel().getSelectedItem());
        }
    }
    public void eliminar(){
        if(this.tblInstructor.getSelectionModel().getSelectedItem()== null){
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de instructores.");
            aviso.setContentText("Debe de seleccionar un registro.");
            aviso.show();
        }else {
            Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de instructores.");
            aviso.setContentText("Esta seguro de eliminar?");
            Optional<ButtonType> result = aviso.showAndWait();
            if (result.get() == ButtonType.OK) {
                int posicion = tblInstructor.getSelectionModel().getSelectedIndex();
                ConexionPU.getInstancia().eliminar(this.tblInstructor.getSelectionModel().getSelectedItem());
                this.listaInstructor.remove(posicion);
            }
        }
    }
    public void mostrarReporte(){
        Map parametros = new HashMap();
        parametros.put("LOGO_KALUM",getClass().getResourceAsStream("/org/kalum/resource/images/7.png"));
        GenerarReporte.getInstancia().mostrarReporte("Lista de Instructores", "ReporteInstructores.jasper", parametros);
    }
}
