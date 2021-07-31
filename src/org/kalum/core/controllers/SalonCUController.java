package org.kalum.core.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.Alumno;
import org.kalum.core.models.Clase;
import org.kalum.core.models.Salon;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class SalonCUController implements Initializable {
    private Main escenarioPrincipal;
    private Salon salon;
    private SpinnerValueFactory<Integer> valueFactoryCapacidad= new SpinnerValueFactory.IntegerSpinnerValueFactory(1,40,1);

    @FXML private TextField txtNombreSalon;
    @FXML private TextField txtDescSalon;
    @FXML private Spinner<Integer> txtCapacidad;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.txtCapacidad.setValueFactory(valueFactoryCapacidad);
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void salir(){
        this.escenarioPrincipal.cambiarEscenaSalones();
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
        this.txtNombreSalon.setText(salon.getNombre_salon());
        this.txtDescSalon.setText(salon.getDescripcion());
        this.valueFactoryCapacidad.setValue(salon.getCapacidad());
    }

    public void guardar(){
        try{
            if (this.salon == null){
                Salon nuevoSalon = new Salon();
                nuevoSalon.setSalon_id(UUID.randomUUID().toString());
                nuevoSalon.setNombre_salon(txtNombreSalon.getText());
                nuevoSalon.setDescripcion(txtDescSalon.getText());
                nuevoSalon.setCapacidad(txtCapacidad.getValue());
                ConexionPU.getInstancia().agregar(nuevoSalon);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Kalum v1.0.0");
                aviso.setHeaderText("Administración de Salones.");
                aviso.setContentText("REGISTRO ALMACENADO");
                aviso.show();

            }else{
                salon.setNombre_salon(this.txtNombreSalon.getText());
                salon.setDescripcion(this.txtDescSalon.getText());
                salon.setCapacidad(this.txtCapacidad.getValue());
                ConexionPU.getInstancia().agregar(salon);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Kalum v1.0.0");
                aviso.setHeaderText("Administración de Salones.");
                aviso.setContentText("REGISTRO MODIFICADO");
                aviso.show();
            }
        }catch(KalumException e){
            KalumViewMessage.getInstancia().mostrarMensaje("Administación de Salones","",e.getMessage(),50303);
        }
        this.escenarioPrincipal.cambiarEscenaSalones();
    }

}

