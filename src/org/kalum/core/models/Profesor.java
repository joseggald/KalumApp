package org.kalum.core.models;

public class Profesor extends Persona{
    private int codProfesor;


    public Profesor(int codProfesor, String nombres, String apellidos, int edad, String telefono, String email, String direccion) {
        super(nombres, apellidos, edad, telefono, email, direccion);
        this.codProfesor=codProfesor;

    }



    public int getCodProfesor() {
        return codProfesor;
    }

    public void setCodProfesor(int codProfesor) {
        this.codProfesor = codProfesor;
    }
}
