package controller;

public class Usuario {
    public String nombre = "";
    public String contrasena = "";
    public String rol = "cliente";
    public String apellido = "";
    public float salario = 0;

    public Usuario (String nombreString, String contrString, String rolString, float salario) {
        nombre = nombreString;
        contrasena = contrString;
        rol = rolString;
        this.salario = salario;
    }
}
