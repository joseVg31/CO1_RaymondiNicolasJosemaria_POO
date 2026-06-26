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
        setSize(480, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(22, 48, 95));

        // ── Header con logo ──────────────────────────────────
        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.Y_AXIS));
        panelHeader.setBackground(new Color(22, 48, 95));
        panelHeader.setBorder(BorderFactory.createEmptyBorder(35, 0, 30, 0));

        JLabel lblLogo = new JLabel("VT");
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblLogo.setForeground(new Color(22, 48, 95));
        lblLogo.setBackground(new Color(100, 160, 255));
        lblLogo.setOpaque(true);
        lblLogo.setPreferredSize(new Dimension(52, 52));
        lblLogo.setMaximumSize(new Dimension(52, 52));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTitulo = new JLabel("ValleTech");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSub = new JLabel("Sistema de Gestión de Productos");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSub.setForeground(new Color(160, 190, 240));
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelHeader.add(lblLogo);
        panelHeader.add(Box.createVerticalStrut(10));
        panelHeader.add(lblTitulo);
        panelHeader.add(Box.createVerticalStrut(4));
        panelHeader.add(lblSub);
        panelPrincipal.add(panelHeader, BorderLayout.NORTH);

        // ── Card blanca ──────────────────────────────────────
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createEmptyBorder(28, 36, 28, 36));

        // Usuario
        JLabel lblUser = new JLabel("Usuario");
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblUser.setForeground(new Color(80, 90, 110));
        lblUser.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        txtUsuario.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtUsuario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 205, 220)),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)));

        // Contraseña
        JLabel lblPass = new JLabel("Contraseña");
        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblPass.setForeground(new Color(80, 90, 110));
        lblPass.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtContrasena = new JPasswordField();
        txtContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtContrasena.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        txtContrasena.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtContrasena.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 205, 220)),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)));

        // Botón
        btnIngresar = new JButton("Ingresar al Sistema");
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnIngresar.setBackground(new Color(22, 48, 95));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setOpaque(true);
        btnIngresar.setContentAreaFilled(true);
        btnIngresar.setBorderPainted(false);
        btnIngresar.setFocusPainted(false);
        btnIngresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnIngresar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        btnIngresar.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Mensaje
        lblMensaje = new JLabel(" ", SwingConstants.CENTER);
        lblMensaje.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(lblUser);
        card.add(Box.createVerticalStrut(5));
        card.add(txtUsuario);
        card.add(Box.createVerticalStrut(14));
        card.add(lblPass);
        card.add(Box.createVerticalStrut(5));
        card.add(txtContrasena);
        card.add(Box.createVerticalStrut(20));
        card.add(btnIngresar);
        card.add(Box.createVerticalStrut(10));
        card.add(lblMensaje);

        panelPrincipal.add(card, BorderLayout.CENTER);

        // Footer
        JPanel footer = new JPanel();
        footer.setBackground(new Color(22, 48, 95));
        footer.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        JLabel lblFooter = new JLabel("© 2026 ValleTech - Todos los derechos reservados");
        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        lblFooter.setForeground(new Color(120, 150, 200));
        footer.add(lblFooter);
        panelPrincipal.add(footer, BorderLayout.SOUTH);

        add(panelPrincipal);
        getRootPane().setDefaultButton(btnIngresar);
    }

    public void mostrarMensaje(String mensaje, boolean exito) {
        lblMensaje.setText(mensaje);
        lblMensaje.setForeground(exito ? new Color(0, 140, 70) : new Color(190, 40, 40));
    }

    public JTextField getTxtUsuario()      { return txtUsuario; }
    public JPasswordField getTxtContrasena() { return txtContrasena; }
    public JButton getBtnIngresar()        { return btnIngresar; }
}
