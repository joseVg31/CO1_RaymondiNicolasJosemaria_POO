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
        setSize(420, 340);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal con fondo azul
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(30, 60, 114));

        // ── Header ──────────────────────────────────────────
        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setBackground(new Color(30, 60, 114));
        panelHeader.setBorder(BorderFactory.createEmptyBorder(25, 0, 20, 0));

        JLabel lblTitulo = new JLabel("ValleTech", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);
        panelHeader.add(lblTitulo, BorderLayout.CENTER);

        panelPrincipal.add(panelHeader, BorderLayout.NORTH);

        // ── Formulario ───────────────────────────────────────
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(Color.WHITE);
        panelForm.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 5, 7, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Label Usuario
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblUsuario.setForeground(new Color(40, 40, 80));
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        panelForm.add(lblUsuario, gbc);

        // Campo Usuario
        txtUsuario = new JTextField(18);
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtUsuario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 200), 1),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panelForm.add(txtUsuario, gbc);

        // Label Contraseña
        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblPass.setForeground(new Color(40, 40, 80));
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        panelForm.add(lblPass, gbc);

        // Campo Contraseña
        txtContrasena = new JPasswordField(18);
        txtContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtContrasena.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 200), 1),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panelForm.add(txtContrasena, gbc);

        // Botón Ingresar
        btnIngresar = new JButton("Ingresar");
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnIngresar.setBackground(new Color(30, 60, 114));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFocusPainted(false);
        btnIngresar.setOpaque(true);
        btnIngresar.setContentAreaFilled(true);
        btnIngresar.setBorderPainted(false);
        btnIngresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnIngresar.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        btnIngresar.setPreferredSize(new Dimension(130, 38));

        JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBtn.setBackground(Color.WHITE);
        panelBtn.add(btnIngresar);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 5, 5, 5);
        panelForm.add(panelBtn, gbc);

        // Mensaje
        lblMensaje = new JLabel("", SwingConstants.CENTER);
        lblMensaje.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridy = 3;
        gbc.insets = new Insets(4, 5, 0, 5);
        panelForm.add(lblMensaje, gbc);

        panelPrincipal.add(panelForm, BorderLayout.CENTER);
        add(panelPrincipal);

        getRootPane().setDefaultButton(btnIngresar);
    }

    public void mostrarMensaje(String mensaje, boolean exito) {
        lblMensaje.setText(mensaje);
        lblMensaje.setForeground(exito ? new Color(0, 150, 0) : Color.RED);
    }

    public JTextField getTxtUsuario() { return txtUsuario; }
    public JPasswordField getTxtContrasena() { return txtContrasena; }
    public JButton getBtnIngresar() { return btnIngresar; }
}
