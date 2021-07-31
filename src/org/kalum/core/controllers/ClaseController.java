package org.kalum.core.controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.Alumno;
import org.kalum.core.models.Clase;
import org.kalum.core.reports.GenerarReporte;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;

import java.net.URL;
import java.util.*;

public class ClaseController implements Initializable {
    private Main escenarioPrincipal;
    private ObservableList<Clase> listaClases;
    @FXML private TableView<Clase> tblClase;
    @FXML private TableColumn<Clase, String> colDescripcion;
    @FXML private TableColumn<Clase, Number> colCiclo;
    @FXML private TableColumn<Clase, String> colCarrera;
    @FXML private TableColumn<Clase, String> colHorario;
    @FXML private TableColumn<Clase, String> colInstructor;
    @FXML private TableColumn<Clase, String> colSalon;
    @FXML private TableColumn<Clase, String> colCupo;

    public ClaseController() throws KalumException{

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        try {
            listaClases = FXCollections.observableArrayList((List<Clase>) ConexionPU.getInstancia().findAll("Clase.findAll"));
        }catch(KalumException e) {
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de alumnos");
            aviso.setContentText(e.getMessage());
            aviso.show();
        }
        this.tblClase.setItems(listaClases);
        this.colDescripcion.setCellValueFactory(cellDescripcion -> cellDescripcion.getValue().descripcion());
        this.colCiclo.setCellValueFactory(cellCiclo -> cellCiclo.getValue().ciclo());
        this.colCarrera.setCellValueFactory(cellCarrera -> cellCarrera.getValue().getCarreraTecnica().nombreProperty());
        this.colHorario.setCellValueFactory(cellHorario -> new ReadOnlyStringWrapper(cellHorario .getValue().getHorario().getHorario_inicio()+ " || " +
                cellHorario .getValue().getHorario().getHorario_final()));
        this.colInstructor.setCellValueFactory(cellInstructor -> new ReadOnlyStringWrapper(cellInstructor.getValue().getInstructor().getApellidos()+ ", " +
                cellInstructor.getValue().getInstructor().getNombres()));
        this.colSalon.setCellValueFactory(cellSalon-> cellSalon.getValue().getSalon().nombreSalon());
        this.colCupo.setCellValueFactory(cellCupo -> new ReadOnlyStringWrapper(cellCupo.getValue().getCupoMinimo() + " a " +
                cellCupo.getValue().getCupoMaximo()+" personas."));
    }
    public void setEscenarioPrincipal(Main escenarioPrincipal) {

        this.escenarioPrincipal = escenarioPrincipal;
    }
    public void salir(){
        this.escenarioPrincipal.cambiarEscenaMain();
    }

    public void cambioCU(){
        this.escenarioPrincipal.cambiarEscenaClaseCU();
    }

    public void modificar(){
        if(this.tblClase.getSelectionModel().getSelectedItem()== null){
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de Clases.");
            aviso.setContentText("Debe de seleccionar un registro.");
            aviso.show();
        }else{
            this.escenarioPrincipal.cambiarEscenaClaseCU(this.tblClase.getSelectionModel().getSelectedItem());
        }

    }
    public void eliminar(){
        if(this.tblClase.getSelectionModel().getSelectedItem()== null){
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de clases");
            aviso.setContentText("Debe de seleccionar un registro.");
            aviso.show();
        }else {
            Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de clases");
            aviso.setContentText("Esta seguro de eliminar?");
            Optional<ButtonType> result = aviso.showAndWait();
            if (result.get() == ButtonType.OK) {
                int posicion = tblClase.getSelectionModel().getSelectedIndex();
                ConexionPU.getInstancia().eliminar(this.tblClase.getSelectionModel().getSelectedItem());
                this.listaClases.remove(posicion);
            }
        }
    }

    public void mostrarReporte(){
        Map parametros = new HashMap();
        parametros.put("LOGO_KALUM",getClass().getResourceAsStream("/org/kalum/resource/images/7.png"));
        GenerarReporte.getInstancia().mostrarReporte("Lista de clases.", "ReporteClases.jasper", parametros);
    }
}
