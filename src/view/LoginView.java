package view;

import controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnIngresar;
    private JLabel lblMensaje;

    public LoginView() {
        initUI();
        new LoginController(this);
        setVisible(true);
    }

    private void initUI() {
        setTitle("ValleTech - Iniciar Sesión");
        setSize(400, 320);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(30, 60, 114));

        // ── Header ──────────────────────────────────────────
        JLabel lblTitulo = new JLabel("ValleTech", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 5, 0));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        // ── Formulario ───────────────────────────────────────
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(Color.WHITE);
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 5, 6, 5);

        JLabel lblUser = new JLabel("Usuario:");
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 0; gbc.gridy = 0;
        panelForm.add(lblUser, gbc);

        txtUsuario = new JTextField(20);
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtUsuario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        gbc.gridy = 1;
        panelForm.add(txtUsuario, gbc);

        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridy = 2;
        panelForm.add(lblPass, gbc);

        txtContrasena = new JPasswordField(20);
        txtContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtContrasena.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        gbc.gridy = 3;
        panelForm.add(txtContrasena, gbc);

        btnIngresar = new JButton("Ingresar");
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnIngresar.setBackground(new Color(30, 60, 114));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFocusPainted(false);
        btnIngresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnIngresar.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        gbc.gridy = 4;
        gbc.insets = new Insets(14, 5, 4, 5);
        panelForm.add(btnIngresar, gbc);

        lblMensaje = new JLabel("", SwingConstants.CENTER);
        lblMensaje.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridy = 5;
        gbc.insets = new Insets(4, 5, 4, 5);
        panelForm.add(lblMensaje, gbc);

        panelPrincipal.add(panelForm, BorderLayout.CENTER);
        add(panelPrincipal);

        // Enter key
        getRootPane().setDefaultButton(btnIngresar);
    }

    public void mostrarMensaje(String mensaje, boolean exito) {
        lblMensaje.setText(mensaje);
        lblMensaje.setForeground(exito ? new Color(0, 150, 0) : Color.RED);
    }

    // ── Getters ───────────────────────────────────────────────
    public JTextField getTxtUsuario() { return txtUsuario; }
    public JPasswordField getTxtContrasena() { return txtContrasena; }
    public JButton getBtnIngresar() { return btnIngresar; }
}
