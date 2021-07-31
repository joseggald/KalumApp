package org.kalum.core.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.Alumno;
import org.kalum.core.sistema.Main;
import javafx.scene.control.TextField;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AlumnoCUController implements Initializable {

    @FXML
    private TextField txtCarne;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtExp;
    @FXML
    private TextField txtEmail;


    private Main escenarioPrincipal;

    private Alumno alumno;

    public void initialize(URL location, ResourceBundle resources) {


    }

    public void guardar() {
        try {
            if (alumno == null) {
                Alumno alumno = new Alumno();
                alumno.setCarne(txtCarne.getText());
                alumno.setApellidos(txtApellidos.getText());
                alumno.setNombres(txtNombres.getText());
                alumno.setNoExpediente(txtExp.getText());
                alumno.setEmail(txtEmail.getText());
                ConexionPU.getInstancia().agregar(alumno);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Kalum v1.0.0");
                aviso.setHeaderText("Administración de alumnos");
                aviso.setContentText("REGISTRO ALMACENADO");
                aviso.show();
            } else {
                alumno.setApellidos(txtApellidos.getText());
                alumno.setNombres(txtNombres.getText());
                alumno.setEmail(txtEmail.getText());
                alumno.setNoExpediente(txtExp.getText());
                ConexionPU.getInstancia().modificar(alumno);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Kalum v1.0.0");
                aviso.setHeaderText("Administración de alumnos");
                aviso.setContentText("REGISTRO MODIFICADO");
                aviso.show();
            }
        }catch(KalumException e) {
            KalumViewMessage.getInstancia().mostrarMensaje("Administación de Clases","",e.getMessage(),50303);
        }
        this.escenarioPrincipal.cambiarEscenaAlumnos();

    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {

        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void cancelarAlumno(){

        this.escenarioPrincipal.cambiarEscenaAlumnos();
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
        this.txtCarne.setText(alumno.getCarne());
        this.txtCarne.setEditable(false);
        this.txtApellidos.setText(alumno.getApellidos());
        this.txtNombres.setText(alumno.getNombres());
        this.txtEmail.setText(alumno.getEmail());
        this.txtExp.setText(alumno.getNoExpediente());
    }
}
