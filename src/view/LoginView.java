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
        setSize(820, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel(new GridLayout(1, 2));

        // ══ PANEL IZQUIERDO (azul) ════════════════════════════
        JPanel panelLeft = new JPanel();
        panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
        panelLeft.setBackground(new Color(22, 48, 95));
        panelLeft.setBorder(BorderFactory.createEmptyBorder(60, 40, 40, 40));

        JLabel lblLogo = new JLabel("VT");
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblLogo.setForeground(new Color(22, 48, 95));
        lblLogo.setBackground(new Color(100, 160, 255));
        lblLogo.setOpaque(true);
        lblLogo.setPreferredSize(new Dimension(60, 60));
        lblLogo.setMaximumSize(new Dimension(60, 60));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblNombre = new JLabel("ValleTech");
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblSlogan = new JLabel("Sistema de Gestión");
        lblSlogan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSlogan.setForeground(new Color(160, 195, 255));
        lblSlogan.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblSlogan2 = new JLabel("de Productos");
        lblSlogan2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSlogan2.setForeground(new Color(160, 195, 255));
        lblSlogan2.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Separador
        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        sep.setForeground(new Color(60, 90, 140));
        sep.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblDesc = new JLabel("<html><div style='color:#8AAEE0;font-size:11px;width:200px'>"
            + "Administre sus productos, genere reportes y exporte información de manera eficiente."
            + "</div></html>");
        lblDesc.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblFooter = new JLabel("© 2026 ValleTech");
        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblFooter.setForeground(new Color(80, 110, 160));
        lblFooter.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelLeft.add(lblLogo);
        panelLeft.add(Box.createVerticalStrut(20));
        panelLeft.add(lblNombre);
        panelLeft.add(Box.createVerticalStrut(6));
        panelLeft.add(lblSlogan);
        panelLeft.add(lblSlogan2);
        panelLeft.add(Box.createVerticalStrut(24));
        panelLeft.add(sep);
        panelLeft.add(Box.createVerticalStrut(20));
        panelLeft.add(lblDesc);
        panelLeft.add(Box.createVerticalGlue());
        panelLeft.add(lblFooter);

        // ══ PANEL DERECHO (blanco) ════════════════════════════
        JPanel panelRight = new JPanel();
        panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
        panelRight.setBackground(Color.WHITE);
        panelRight.setBorder(BorderFactory.createEmptyBorder(55, 45, 45, 45));

        JLabel lblBienvenido = new JLabel("Bienvenido");
        lblBienvenido.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblBienvenido.setForeground(new Color(22, 48, 95));
        lblBienvenido.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblSub = new JLabel("Ingresa tus credenciales para continuar");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSub.setForeground(new Color(140, 150, 170));
        lblSub.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Campo Usuario
        JLabel lblUser = new JLabel("Usuario");
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblUser.setForeground(new Color(80, 90, 110));
        lblUser.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtUsuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtUsuario.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtUsuario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(210, 215, 230)),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)));

        // Campo Contraseña
        JLabel lblPass = new JLabel("Contraseña");
        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblPass.setForeground(new Color(80, 90, 110));
        lblPass.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtContrasena = new JPasswordField();
        txtContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtContrasena.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtContrasena.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtContrasena.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(210, 215, 230)),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)));

        // Botón
        btnIngresar = new JButton("Ingresar");
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnIngresar.setBackground(new Color(22, 48, 95));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setOpaque(true);
        btnIngresar.setContentAreaFilled(true);
        btnIngresar.setBorderPainted(false);
        btnIngresar.setFocusPainted(false);
        btnIngresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnIngresar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        btnIngresar.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblMensaje = new JLabel(" ");
        lblMensaje.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblMensaje.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelRight.add(lblBienvenido);
        panelRight.add(Box.createVerticalStrut(5));
        panelRight.add(lblSub);
        panelRight.add(Box.createVerticalStrut(30));
        panelRight.add(lblUser);
        panelRight.add(Box.createVerticalStrut(6));
        panelRight.add(txtUsuario);
        panelRight.add(Box.createVerticalStrut(16));
        panelRight.add(lblPass);
        panelRight.add(Box.createVerticalStrut(6));
        panelRight.add(txtContrasena);
        panelRight.add(Box.createVerticalStrut(24));
        panelRight.add(btnIngresar);
        panelRight.add(Box.createVerticalStrut(10));
        panelRight.add(lblMensaje);

        panelPrincipal.add(panelLeft);
        panelPrincipal.add(panelRight);
        add(panelPrincipal);
        getRootPane().setDefaultButton(btnIngresar);
    }

    public void mostrarMensaje(String mensaje, boolean exito) {
        lblMensaje.setText(mensaje);
        lblMensaje.setForeground(exito ? new Color(0, 140, 70) : new Color(190, 40, 40));
    }

    public JTextField getTxtUsuario()        { return txtUsuario; }
    public JPasswordField getTxtContrasena() { return txtContrasena; }
    public JButton getBtnIngresar()          { return btnIngresar; }
}
