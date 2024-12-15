import controller.*;
import views.*;

public class Main {
    private static Registro baseDatos;
    public static final String ROLUSERADMIN = "admin";
    public static final String ROLUSERCLIENTE = "client";
    public static final String ROLUSEREMPLEADO = "emp";
    
    private static void adminView (Usuario user) {
        baseDatos.form.Frame.setVisible(false);
        AdminPanel frame = new AdminPanel(resultado -> {
            if (resultado.equals("cerrar")) {
                baseDatos.form.Frame.setVisible(true);
            }
        });
    }

    private static void empleadoView (Usuario user) {
        baseDatos.form.Frame.setVisible(false);
        Empleado frame = new Empleado(e -> {
            if (e.equals("cerrar")) {
                baseDatos.form.Frame.setVisible(true);
            }
        });
    }

    private static void mainStoreView (Usuario user) {
        baseDatos.form.Frame.setVisible(false);
        MainStoreView mainStore = new MainStoreView(e -> {
            if (e.equals("cerrar")) {
                baseDatos.form.Frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        Registro registro = new Registro(user -> {
            switch (user.rol) {
                case "admin":
                    adminView(user);
                    break;
                case "emp":
                    empleadoView(user);
                    break;
                default:
                    mainStoreView(user);
                    break;
            }
        });
        registro.addUsuario(new Usuario("Eduin", "12345", ROLUSERADMIN, 0));
        registro.addUsuario(new Usuario("Sara", "12345", ROLUSEREMPLEADO, 0));
        baseDatos = registro;
    }

}
