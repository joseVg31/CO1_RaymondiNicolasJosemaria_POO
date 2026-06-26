package controller;

import model.Usuario;
import model.UsuarioDAO;
import view.LoginView;
import view.DashboardView;

public class LoginController {

    private LoginView vista;
    private UsuarioDAO dao;

    public LoginController(LoginView vista) {
        this.vista = vista;
        this.dao = new UsuarioDAO();
        initListeners();
    }

    private void initListeners() {
        vista.getBtnIngresar().addActionListener(e -> autenticar());
    }

    private void autenticar() {
        String usuario = vista.getTxtUsuario().getText().trim();
        String contrasena = new String(vista.getTxtContrasena().getPassword()).trim();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            vista.mostrarMensaje("Por favor complete todos los campos.", false);
            return;
        }

        Usuario u = dao.autenticar(usuario, contrasena);
        if (u != null) {
            vista.mostrarMensaje("¡Acceso correcto! Bienvenido, " + u.getUsuario(), true);
            vista.dispose();
            new DashboardView(u.getUsuario());
        } else {
            vista.mostrarMensaje("Usuario o contraseña incorrectos.", false);
        }
    }
}
