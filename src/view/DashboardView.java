package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DashboardView extends JFrame {

    private String usuarioActivo;

    public DashboardView(String usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
        initUI();
        setVisible(true);
    }

    private void initUI() {
        setTitle("ValleTech - Dashboard");
        setSize(700, 430);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(240, 242, 248));

        // ── Header ──────────────────────────────────────────
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(22, 48, 95));
        header.setBorder(BorderFactory.createEmptyBorder(14, 24, 14, 24));

        JPanel headerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        headerLeft.setBackground(new Color(22, 48, 95));

        JLabel lblLogo = new JLabel("VT");
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblLogo.setForeground(new Color(22, 48, 95));
        lblLogo.setBackground(new Color(100, 160, 255));
        lblLogo.setOpaque(true);
        lblLogo.setPreferredSize(new Dimension(34, 34));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblTitulo = new JLabel("  ValleTech");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lblTitulo.setForeground(Color.WHITE);

        headerLeft.add(lblLogo);
        headerLeft.add(lblTitulo);
        header.add(headerLeft, BorderLayout.WEST);

        JPanel headerRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 2));
        headerRight.setBackground(new Color(22, 48, 95));

        JLabel lblUsuario = new JLabel(usuarioActivo);
        lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblUsuario.setForeground(new Color(180, 210, 255));

        JLabel lblAvatar = new JLabel(String.valueOf(usuarioActivo.charAt(0)).toUpperCase());
        lblAvatar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblAvatar.setForeground(new Color(22, 48, 95));
        lblAvatar.setBackground(new Color(100, 160, 255));
        lblAvatar.setOpaque(true);
        lblAvatar.setPreferredSize(new Dimension(30, 30));
        lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);

        headerRight.add(lblUsuario);
        headerRight.add(lblAvatar);
        header.add(headerRight, BorderLayout.EAST);
        panelPrincipal.add(header, BorderLayout.NORTH);

        // ── Bienvenida ───────────────────────────────────────
        JPanel panelBienvenida = new JPanel();
        panelBienvenida.setLayout(new BoxLayout(panelBienvenida, BoxLayout.Y_AXIS));
        panelBienvenida.setBackground(new Color(240, 242, 248));
        panelBienvenida.setBorder(BorderFactory.createEmptyBorder(28, 40, 10, 40));

        JLabel lblBienvenida = new JLabel("Bienvenido, " + usuarioActivo);
        lblBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblBienvenida.setForeground(new Color(22, 48, 95));

        JLabel lblSub = new JLabel("¿Qué deseas hacer hoy?");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSub.setForeground(new Color(120, 130, 155));

        panelBienvenida.add(lblBienvenida);
        panelBienvenida.add(Box.createVerticalStrut(4));
        panelBienvenida.add(lblSub);
        panelPrincipal.add(panelBienvenida, BorderLayout.CENTER);

        // ── Tarjetas ─────────────────────────────────────────
        JPanel panelCards = new JPanel(new GridLayout(1, 3, 16, 0));
        panelCards.setBackground(new Color(240, 242, 248));
        panelCards.setBorder(BorderFactory.createEmptyBorder(14, 40, 40, 40));

        panelCards.add(crearCard(
            "Gestión de Productos",
            "Registrar, modificar y eliminar productos del sistema",
            new Color(22, 48, 95), new Color(35, 70, 140),
            e -> new ProductoView()
        ));
        panelCards.add(crearCard(
            "Reportes",
            "Ver y exportar reportes de productos en PDF",
            new Color(0, 105, 75), new Color(0, 135, 95),
            e -> new ReporteView()
        ));
        panelCards.add(crearCard(
            "Cerrar Sesión",
            "Salir del sistema de gestión ValleTech",
            new Color(150, 30, 30), new Color(185, 45, 45),
            e -> {
                int op = JOptionPane.showConfirmDialog(this,
                    "¿Desea cerrar sesión?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (op == JOptionPane.YES_OPTION) { dispose(); new LoginView(); }
            }
        ));

        panelPrincipal.add(panelCards, BorderLayout.SOUTH);
        add(panelPrincipal);
    }

    private JPanel crearCard(String titulo, String desc, Color colorBase, Color colorHover, ActionListener accion) {
        JPanel card = new JPanel(new BorderLayout(0, 8));
        card.setBackground(colorBase);
        card.setBorder(BorderFactory.createEmptyBorder(18, 16, 18, 16));
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel lblTitulo = new JLabel("<html><b>" + titulo + "</b></html>");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTitulo.setForeground(Color.WHITE);

        JLabel lblDesc = new JLabel("<html><div style='width:140px;color:white;font-size:10px'>" + desc + "</div></html>");
        lblDesc.setForeground(new Color(210, 225, 255));

        JButton btn = new JButton("Abrir");
        btn.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(0, 0, 0, 60));
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(75, 26));
        btn.addActionListener(accion);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        bottom.setOpaque(false);
        bottom.add(btn);

        card.add(lblTitulo, BorderLayout.NORTH);
        card.add(lblDesc,   BorderLayout.CENTER);
        card.add(bottom,    BorderLayout.SOUTH);

        MouseAdapter hover = new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { card.setBackground(colorHover); }
            public void mouseExited(MouseEvent e)  { card.setBackground(colorBase); }
            public void mouseClicked(MouseEvent e) { accion.actionPerformed(null); }
        };
        card.addMouseListener(hover);
        lblTitulo.addMouseListener(hover);
        lblDesc.addMouseListener(hover);

        return card;
    }
}
