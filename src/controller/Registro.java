package controller;

import callbacks.callbackUsuario;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import views.LoginApp;

public class Registro {
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    public LoginApp form;

    public Usuario getUsuario (int index) {
        if (usuarios.size() > index) {
            return usuarios.get(index);
        }
        return null;
    }
    

    public void addUsuario (Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario login (String nombre, String pass) {
        int length = usuarios.size();
        Usuario isLoged = null;
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                Usuario usuario = getUsuario(i);
                if (usuario.nombre.equals(nombre) && usuario.contrasena.equals(pass)) {
                    // aqui iria el codigo para el login Correcto
                    isLoged = usuario;
                }
            }
        }
        return isLoged;
    }

    public Registro (callbackUsuario callback) {
        LoginApp loged = new LoginApp();
        form = loged;
        loged.ingresar(resultado -> {
            Usuario user = login(resultado.get(0), resultado.get(1));
            if (user == null) {
                JOptionPane.showMessageDialog(loged.Frame, "Usuario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                callback.ejecutar(user);
            }
        });
    }
}
