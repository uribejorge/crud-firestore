package com.example.prueba_crud_firestore;

public class Usuario {
    private String cedula;
    private String nombre;
    private String email;
    private String telefono;
    private int edad;
    private String direccion;

    // Constructor vacío requerido para Firestore
    public Usuario() {}

    // Constructor con todos los campos
    public Usuario(String cedula, String nombre, String email, String telefono, int edad, String direccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.edad = edad;
        this.direccion = direccion;
    }

    // Getters y setters para cada campo
    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
}
