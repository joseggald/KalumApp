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

import org.kalum.core.models.CarreraTecnica;
import org.kalum.core.reports.GenerarReporte;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.*;

public class CarreraTecnicaController implements Initializable {
    private Main escenarioPrincipal;
    @FXML private TableView<CarreraTecnica> tblCarrera;
    @FXML private TableColumn<CarreraTecnica, String> colId;
    @FXML private TableColumn<CarreraTecnica, String> colNombre;

    private ObservableList<CarreraTecnica> ListaCarreras;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.ListaCarreras = FXCollections.observableArrayList((List<CarreraTecnica>) ConexionPU.getInstancia().findAll("CarreraTecnica.findAll"));
        }catch(KalumException e) {
            KalumViewMessage.getInstancia().mostrarMensaje("Administación de carreras.:","",e.getMessage(),50302);
        }
        this.tblCarrera.setItems(this.ListaCarreras);
        this.colId.setCellValueFactory(cellData -> cellData.getValue().codigo_carreraProperty());
        this.colNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    public void salir(){
        this.escenarioPrincipal.cambiarEscenaMain();
    }
    public void nuevo(){
        this.escenarioPrincipal.cambiarEscenaCarreraCU();
    }
    public void modificar(){
        if(this.tblCarrera.getSelectionModel().getSelectedItem()== null){
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de carreras");
            aviso.setContentText("Debe de seleccionar un registro.");
            aviso.show();
        }else{
            this.escenarioPrincipal.cambiarEscenaCarreraCU(this.tblCarrera.getSelectionModel().getSelectedItem());
        }
    }
    public void eliminar(){
        if(this.tblCarrera.getSelectionModel().getSelectedItem()== null){
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de carreras");
            aviso.setContentText("Debe de seleccionar un registro.");
            aviso.show();
        }else {
            Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de carreras");
            aviso.setContentText("Esta seguro de eliminar?");
            Optional<ButtonType> result = aviso.showAndWait();
            if (result.get() == ButtonType.OK) {
                int posicion = tblCarrera.getSelectionModel().getSelectedIndex();
                ConexionPU.getInstancia().eliminar(this.tblCarrera.getSelectionModel().getSelectedItem());
                this.ListaCarreras.remove(posicion);
            }
        }
    }
    public void mostrarReporte(){
        Map parametros = new HashMap();
        parametros.put("LOGO_KALUM",getClass().getResourceAsStream("/org/kalum/resource/images/7.png"));
        GenerarReporte.getInstancia().mostrarReporte("Lista de Carreras", "ReporteCarreras.jasper", parametros);
    }
}
