package org.kalum.core.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.*;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.*;

public class InstructorCUController implements Initializable {
    private Instructor instructor;
    private Main escenarioPrincipal;
    @FXML private TextField txtNom;
    @FXML private TextField txtApe;
    @FXML private TextField txtComen;
    @FXML private TextField txtDic;
    @FXML private TextField txtTel;
    @FXML private ComboBox<String> cmbEstatus;
    @FXML private ComboBox<String> cmbFoto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,new String[]{"Activo", "Inactivo"});
        cmbEstatus.getItems().addAll(list);
        ArrayList<String> list2 = new ArrayList<>();
        Collections.addAll(list2,new String[]{"Disponible", "No Disponible"});
        cmbFoto.getItems().addAll(list2);
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
        this.txtNom.setText(instructor.getNombres());
        this.txtApe.setText(instructor.getApellidos());
        this.txtDic.setText(instructor.getDireccion());
        this.txtComen.setText(instructor.getCometario());
        this.txtTel.setText(instructor.getTelefono());
        this.cmbEstatus.getSelectionModel().select(instructor.getEstatus());
        this.cmbFoto.getSelectionModel().select(instructor.getFoto());
    }

    public void salir(){
        this.escenarioPrincipal.cambiarEscenaInstructor();
    }
    public void guardar(){
        try{
            if (this.instructor== null){
                Instructor nuevo = new Instructor();
                nuevo.setInstructor_id(UUID.randomUUID().toString());
                nuevo.setApellidos(this.txtApe.getText());
                nuevo.setNombres(this.txtNom.getText());
                nuevo.setCometario(this.txtComen.getText());
                nuevo.setDireccion(this.txtDic.getText());
                nuevo.setTelefono(this.txtTel.getText());
                nuevo.setEstatus(this.cmbEstatus.getSelectionModel().getSelectedItem());
                nuevo.setFoto(this.cmbFoto.getSelectionModel().getSelectedItem());
                ConexionPU.getInstancia().agregar(nuevo);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Kalum v1.0.0");
                aviso.setHeaderText("Administración de Instructores.");
                aviso.setContentText("REGISTRO ALMACENADO");
                aviso.show();

            }else{
                instructor.setApellidos(this.txtApe.getText());
                instructor.setNombres(this.txtNom.getText());
                instructor.setCometario(this.txtComen.getText());
                instructor.setDireccion(this.txtDic.getText());
                instructor.setTelefono(this.txtTel.getText());
                instructor.setEstatus(this.cmbEstatus.getSelectionModel().getSelectedItem());
                instructor.setFoto(this.cmbFoto.getSelectionModel().getSelectedItem());
                ConexionPU.getInstancia().agregar(instructor);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Kalum v1.0.0");
                aviso.setHeaderText("Administración de Instructores.");
                aviso.setContentText("REGISTRO MODIFICADO");
                aviso.show();
            }
        }catch(KalumException e){
            KalumViewMessage.getInstancia().mostrarMensaje("Administación de Instructores.","",e.getMessage(),50303);
        }
        this.escenarioPrincipal.cambiarEscenaInstructor();
    }

}
