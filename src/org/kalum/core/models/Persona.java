package org.kalum.core.models;

public class Persona{
    private String nombres;
    private String apellidos;
    private int edad;
    private String telefono;
    private String email;
    private String direccion;

    public Persona() {

    }

    public Persona(String nombres, String apellidos, int edad, String telefono, String email, String direccion) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    @Override
    public String toString(){
        return nombres.concat(" ").concat(apellidos);
    }


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
