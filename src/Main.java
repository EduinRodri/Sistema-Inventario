import controller.*;
import views.*;

public class Main {
    private static Registro baseDatos;
    
    private static void adminView (Usuario user) {
        baseDatos.form.Frame.setVisible(false);
        AdminPanel frame = new AdminPanel(resultado -> {
            if (resultado.equals("cerrar")) {
                baseDatos.form.Frame.setVisible(true);
            }
            if (resultado.equals("")) {
                
            }
        });
    }

    public static void main(String[] args) {
        Registro registro = new Registro(user -> {
            if (user.rol.equals("admin")) {
                adminView(user);
            }
        });
        registro.addUsuario(new Usuario("Pablo", "12345", "admin", 0));
        baseDatos = registro;
    }

}
