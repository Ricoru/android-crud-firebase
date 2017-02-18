package com.gdgtrujillo.crudfirebase.entidad;

import com.google.firebase.database.Exclude;

/**
 * Created by brianbritto on 09/02/17.
 */

public class Contacto {

    //al agregar la anotación exclude, le estoy diciendo a firebase que los únicos campos que necesito
    //para enviar y obtener, son los q no están excluidos para este objecto o clase.

    @Exclude
    public String key;

    public String nombres;
    public String apellidoPaterno;
    public String apellidoMaterno;
    public String telefono;
    public String email;
    public String direccion;

    public Contacto(){}

    public Contacto(String nombres, String apellidoPaterno, String apellidoMaterno, String telefono, String email, String direccion) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
