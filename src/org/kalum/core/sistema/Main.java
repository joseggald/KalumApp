package org.kalum.core.sistema;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.kalum.core.controllers.*;
import org.kalum.core.db.Conexion;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.ResultSet;

public class Main extends Application {

    private final String Paquete_View="../views/";


    private Stage escenarioPrincipal;

    @Override
    public void start(Stage escenarioPrincipal) throws Exception {
        Conexion conexion = new Conexion();
        this.escenarioPrincipal=escenarioPrincipal;
        this.escenarioPrincipal.setTitle("Kalum v1.0.0");
        this.escenarioPrincipal.getIcons().add(new Image("/org/kalum/resource/images/7.png"));
        this.ventanaLogin();
        this.escenarioPrincipal.show();

    }

    public void cambiarEscenaAlumnos(){
        try{
            AlumnoController controlador = (AlumnoController)this.cambiarEscena("AlumnoView.fxml",666,443);
            controlador.setEscenarioPrincipal(this);

        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public void cambiarEscenaMain(){
        try{
            MainController controlador = (MainController) this.cambiarEscena("MainView.fxml",600,400);
            controlador.setEscenarioPrincipal(this);
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public void cambiarEscenaAlumnoCU(){
        try{
            AlumnoCUController controlador = (AlumnoCUController) this.cambiarEscena("AlumnoCUView.fxml",471,325);
            controlador.setEscenarioPrincipal(this);
        }catch(IOException e){

            e.printStackTrace();
        }
    }
    public void cambiarEscenaAlumnoCU(Alumno elemento){
        try{
            AlumnoCUController controlador = (AlumnoCUController) this.cambiarEscena("AlumnoCUView.fxml",471,325);
            controlador.setAlumno(elemento);
            controlador.setEscenarioPrincipal(this);
        }catch(IOException e){

            e.printStackTrace();
        }
    }
    public void cambiarEscenaHorarios(){
        try{
            HorarioController controlador = (HorarioController)this.cambiarEscena("HorariosView.fxml",613,451);
            controlador.setEscenarioPrincipal(this);

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void cambiarEscenaHorariosCU(){
        try{
            HorarioCUController controlador = (HorarioCUController)this.cambiarEscena("HorariosCUView.fxml",446,343);
            controlador.setEscenarioPrincipal(this);

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void cambiarEscenaHorariosCU(Horario elemento){
        try{
            HorarioCUController controlador = (HorarioCUController)this.cambiarEscena("HorariosCUView.fxml",446,343);
            controlador.setHorario(elemento);
            controlador.setEscenarioPrincipal(this);

        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public void cambiarEscenaSalones(){
        try{
            SalonController controlador = (SalonController)this.cambiarEscena("SalonView.fxml",571,460);
            controlador.setEscenarioPrincipal(this);

        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public void cambiarEscenaSalonesCU(){
        try{
            SalonCUController controlador = (SalonCUController)this.cambiarEscena("SalonCUView.fxml",609,319);
            controlador.setEscenarioPrincipal(this);

        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public void cambiarEscenaSalonesCU(Salon elemento){
        try{
            SalonCUController controlador = (SalonCUController)this.cambiarEscena("SalonCUView.fxml",609,319);
            controlador.setEscenarioPrincipal(this);
            controlador.setSalon(elemento);
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public void cambiarEscenaClases(){
        try{
            ClaseController controlador = (ClaseController)this.cambiarEscena("ClaseView.fxml",1038,460);
            controlador.setEscenarioPrincipal(this);

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void cambiarEscenaAcercaDe(){
        try{
            AcercaDeController controlador = (AcercaDeController)this.cambiarEscena("AcercaDeView.fxml",600,600);
            controlador.setEscenarioPrincipal(this);

        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public void cambiarEscenaClaseCU(){
        try{
            ClaseCUController controlador = (ClaseCUController)this.cambiarEscena("ClaseCUView.fxml",754,597);
            controlador.setEscenarioPrincipal(this);

        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public void cambiarEscenaClaseCU(Clase elemento){
        try{
            ClaseCUController controlador = (ClaseCUController)this.cambiarEscena("ClaseCUView.fxml",725,597);
            controlador.setEscenarioPrincipal(this);
            controlador.setClase(elemento);
        }catch(IOException e){
            e.printStackTrace();
        }

    }

   public void cambiarEscenaCarrera(){
        try{
            CarreraTecnicaController controlador = (CarreraTecnicaController)this.cambiarEscena("CarreraTecnicaView.fxml", 507,511);
            controlador.setEscenarioPrincipal(this);
        }catch (IOException e){
            e.printStackTrace();
        }
   }
    public void cambiarEscenaCarreraCU(){
        try{
            CarreraTecnicaCUController controlador = (CarreraTecnicaCUController)this.cambiarEscena("CarreraTecnicaCUView.fxml", 600,211);
            controlador.setEscenarioPrincipal(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void cambiarEscenaCarreraCU(CarreraTecnica elemento){
        try{
            CarreraTecnicaCUController controlador = (CarreraTecnicaCUController)this.cambiarEscena("CarreraTecnicaCUView.fxml", 600,211);
            controlador.setEscenarioPrincipal(this);
            controlador.setCarreraTecnica(elemento);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void cambiarEscenaInstructor(){
        try{
            InstructorController controlador = (InstructorController)this.cambiarEscena("InstructorView.fxml", 1195,450);
            controlador.setEscenarioPrincipal(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void cambiarEscenaInstructorCU(){
        try{
            InstructorCUController controlador = (InstructorCUController)this.cambiarEscena("InstructorCUView.fxml", 716,581);
            controlador.setEscenarioPrincipal(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void cambiarEscenaInstructorCU(Instructor elemento){
        try{
            InstructorCUController controlador = (InstructorCUController)this.cambiarEscena("InstructorCUView.fxml", 716,581);
            controlador.setInstructor(elemento);
            controlador.setEscenarioPrincipal(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void ventanaLogin(){
        try{
            LoginController controlador = (LoginController) this.cambiarEscena("LoginView.fxml",601,196);
            controlador.setEscenarioPrincipal(this);
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public Initializable cambiarEscena(String escena, int ancho, int alto) throws IOException {
        Initializable resultado = null;
        FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource(this.Paquete_View + escena));
        AnchorPane root = (AnchorPane) cargadorFXML.load();
        Scene scene = new Scene(root, ancho, alto);
        scene.getStylesheets().add("org/kalum/core/resources/styles/estilo.css");
        this.escenarioPrincipal.setScene(scene);
        this.escenarioPrincipal.sizeToScene();
        resultado = (Initializable) cargadorFXML.getController();
        return resultado;
    }



   /* public Initializable cambiarEscena(String escena, int ancho, int alto) throws IOException {
        Initializable resultado=null;

        FXMLLoader cargadorFXLM=new FXMLLoader();
        InputStream archivo=Main.class.getResourceAsStream(this.Paquete_View + escena);
        cargadorFXLM.setBuilderFactory(new JavaFXBuilderFactory());
        cargadorFXLM.setLocation(PrintStream.class.getResource(this.Paquete_View + escena));
        Scene nuevaEscena=new Scene((AnchorPane)cargadorFXLM.load(archivo), ancho, alto);
        nuevaEscena.getStylesheets().add("org/kalum/core/resources/styles/estilo.css");
        this.escenarioPrincipal.setScene(nuevaEscena);
        this.escenarioPrincipal.sizeToScene();
        resultado=(Initializable) cargadorFXLM.getController();
        return resultado;
    }*/


    public static void main(String[] args) {
        launch(args);
    }
    }
