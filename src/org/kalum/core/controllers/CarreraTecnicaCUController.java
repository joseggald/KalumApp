package org.kalum.core.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.Alumno;
import org.kalum.core.models.CarreraTecnica;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class CarreraTecnicaCUController implements Initializable {

    private CarreraTecnica carreraTecnica;
    private Main escenarioPrincipal;

    @FXML private TextField txtNombre;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    public void salir(){
        this.escenarioPrincipal.cambiarEscenaCarrera();
    }
    public void guardar() {
        try {
            if (carreraTecnica == null) {
                CarreraTecnica carrera = new CarreraTecnica();
                carrera.setCodigo_carrera(UUID.randomUUID().toString());
                carrera.setNombre(this.txtNombre.getText());
                ConexionPU.getInstancia().agregar(carrera);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Kalum v1.0.0");
                aviso.setHeaderText("Administración de Carreras");
                aviso.setContentText("REGISTRO ALMACENADO");
                aviso.show();
            } else {
                carreraTecnica.setNombre(this.txtNombre.getText());
                ConexionPU.getInstancia().modificar(carreraTecnica);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Kalum v1.0.0");
                aviso.setHeaderText("Administración de Carreras");
                aviso.setContentText("REGISTRO MODIFICADO");
                aviso.show();
            }
        }catch(KalumException e) {
            KalumViewMessage.getInstancia().mostrarMensaje("Administación de Carreras","",e.getMessage(),50303);
        }
        this.escenarioPrincipal.cambiarEscenaCarrera();

    }

    public void setCarreraTecnica(CarreraTecnica carreraTecnica) {
        this.carreraTecnica = carreraTecnica;
        this.txtNombre.setText(carreraTecnica.getNombre());

    }
}
