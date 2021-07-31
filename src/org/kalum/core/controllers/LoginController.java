package org.kalum.core.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.kalum.core.db.Conexion;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.Usuario;
import org.kalum.core.sistema.Main;

import javax.persistence.ParameterMode;
import javax.swing.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

public class LoginController implements Initializable {
    private Main escenarioPrincipal;
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void autenticar(){
       try{
           List<Usuario> usuario =ConexionPU.getInstancia().getEntityManager()
                   .createStoredProcedureQuery("sp_Autenticar", Usuario.class)
                   .registerStoredProcedureParameter(1,String.class, ParameterMode.IN)
                   .registerStoredProcedureParameter(2,String.class, ParameterMode.IN)
                   .setParameter(1,txtUsuario.getText())
                   .setParameter(2,txtPassword.getText())
                   .getResultList();
           if(usuario.isEmpty()){
               Alert aviso = new Alert(Alert.AlertType.ERROR);
               aviso.setTitle("KALUM V.1");
               aviso.setHeaderText("Login");
               aviso.setContentText("No exite ese usuario con estas credenciales.");
               aviso.show();

           }else{
               Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
               aviso.setTitle("KALUM V.1");
               aviso.setHeaderText("Login");
               aviso.setContentText("Bienvenido "+usuario.get(0).getUsername());
               aviso.show();
               this.escenarioPrincipal.cambiarEscenaMain();
           }

       }catch(Exception e){
           e.printStackTrace();
       }


    }
    public void setEscenarioPrincipal(Main escenarioPrincipal) {

        this.escenarioPrincipal = escenarioPrincipal;
    }
}
