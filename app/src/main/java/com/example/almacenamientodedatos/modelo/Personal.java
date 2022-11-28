package com.example.almacenamientodedatos.modelo;

public class Personal {
    String nombre, apellido, rut, correo, cargo;

    public Personal() {}

    public Personal(String nombre, String apellido, String rut, String correo, String cargo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.correo = correo;
        this.cargo = cargo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }



}