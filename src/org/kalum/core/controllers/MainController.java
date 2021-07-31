package org.kalum.core.controllers;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import org.kalum.core.sistema.Main;

public class MainController implements Initializable {
    private Main escenarioPrincipal;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setEscenarioPrincipal (Main escenarioPrincipal) {

        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void ventanaAlumnos(){

        this.escenarioPrincipal.cambiarEscenaAlumnos();
    }
    public void ventanaHorario(){

        this.escenarioPrincipal.cambiarEscenaHorarios();
    }
    public void ventanaSalon(){

        this.escenarioPrincipal.cambiarEscenaSalones();
    }

    public void ventanaCarrera(){
        this.escenarioPrincipal.cambiarEscenaCarrera();
    }

    public void ventanaInstructor(){
        this.escenarioPrincipal.cambiarEscenaInstructor();
    }

    public void ventanaClase(){
        this.escenarioPrincipal.cambiarEscenaClases();
    }


    public void login(){
        this.escenarioPrincipal.ventanaLogin();
    }

    public void cerrarSesion(){
        this.escenarioPrincipal.ventanaLogin();
    }

    public void acercaDe(){
        this.escenarioPrincipal.cambiarEscenaAcercaDe();
    }

}
