package org.kalum.core.models;

public class Estudiante extends Persona{
    private String carne;

    public Estudiante(String carne, String nombres, String apellidos, int edad, String telefono, String email, String direccion) {
        super(nombres, apellidos, edad, telefono, email, direccion);
        this.carne = carne;
    }

    public String getCarne() {
        return carne;
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }
}
