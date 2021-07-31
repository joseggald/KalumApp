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
import org.kalum.core.models.Salon;
import org.kalum.core.reports.GenerarReporte;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.*;

public class SalonController implements Initializable {

    private Main escenarioPrincipal;

    @FXML private TableView<Salon> tblSalon;
    @FXML private TableColumn<Salon, String> colSalonId;
    @FXML private TableColumn<Salon, Integer> colCapacidad;
    @FXML private TableColumn<Salon, String> colNomSalon;
    @FXML private TableColumn<Salon, String> colDes;

    private ObservableList<Salon> ListaSalon;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            this.ListaSalon = FXCollections.observableArrayList((List<Salon>) ConexionPU.getInstancia().findAll("Salon.findAll"));
        }catch(KalumException e) {
            KalumViewMessage.getInstancia().mostrarMensaje("Administación de Salones:","",e.getMessage(),50302);
        }
        this.tblSalon.setItems(this.ListaSalon);
        this.colSalonId.setCellValueFactory(cellData -> cellData.getValue().salon_id());
        this.colCapacidad.setCellValueFactory(cellData -> cellData.getValue().capacidad().asObject());
        this.colNomSalon.setCellValueFactory(cellData -> cellData.getValue().nombreSalon());
        this.colDes.setCellValueFactory(cellData -> cellData.getValue().descripcion());
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void nuevo(){
        this.escenarioPrincipal.cambiarEscenaSalonesCU();
    }

    public void salir(){
        this.escenarioPrincipal.cambiarEscenaMain();
    }

    public void modificar(){
        if(this.tblSalon.getSelectionModel().getSelectedItem()== null){
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de Salones.");
            aviso.setContentText("Debe de seleccionar un registro.");
            aviso.show();
        }else{
            this.escenarioPrincipal.cambiarEscenaSalonesCU(this.tblSalon.getSelectionModel().getSelectedItem());
        }

    }

    public void eliminar(){
        if(this.tblSalon.getSelectionModel().getSelectedItem()== null)
        {
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de Salones");
            aviso.setContentText("Debe de seleccionar un registro.");
            aviso.show();
        } else{
            Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de Salones");
            aviso.setContentText("Esta seguro de eliminar?");
            Optional<ButtonType> result = aviso.showAndWait();
            if(result.get() == ButtonType.OK){
                int posicion = tblSalon.getSelectionModel().getSelectedIndex();
                ConexionPU.getInstancia().eliminar(this.tblSalon.getSelectionModel().getSelectedItem());
                this.ListaSalon.remove(posicion);
            }
        }
    }

    public void mostrarReporte(){
        Map parametros = new HashMap();
        parametros.put("LOGO_KALUM",getClass().getResourceAsStream("/org/kalum/resource/images/7.png"));
        GenerarReporte.getInstancia().mostrarReporte("Lista de Salones", "ReporteSalones.jasper", parametros);
    }


}
