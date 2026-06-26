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
        setSize(650, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(240, 242, 248));

        // ── Header ──────────────────────────────────────────
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(22, 48, 95));
        header.setBorder(BorderFactory.createEmptyBorder(16, 24, 16, 24));

        JPanel headerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        headerLeft.setBackground(new Color(22, 48, 95));

        JLabel lblLogo = new JLabel("VT");
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblLogo.setForeground(new Color(22, 48, 95));
        lblLogo.setBackground(new Color(100, 160, 255));
        lblLogo.setOpaque(true);
        lblLogo.setPreferredSize(new Dimension(36, 36));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        JLabel lblTitulo = new JLabel("  ValleTech");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);

        headerLeft.add(lblLogo);
        headerLeft.add(lblTitulo);
        header.add(headerLeft, BorderLayout.WEST);

        JPanel headerRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
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
        JPanel panelBienvenida = new JPanel(new BorderLayout());
        panelBienvenida.setBackground(new Color(240, 242, 248));
        panelBienvenida.setBorder(BorderFactory.createEmptyBorder(30, 40, 10, 40));

        JLabel lblBienvenida = new JLabel("Bienvenido, " + usuarioActivo);
        lblBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblBienvenida.setForeground(new Color(22, 48, 95));

        JLabel lblSub = new JLabel("¿Qué deseas hacer hoy?");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSub.setForeground(new Color(120, 130, 155));

        JPanel textos = new JPanel();
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        textos.setBackground(new Color(240, 242, 248));
        textos.add(lblBienvenida);
        textos.add(Box.createVerticalStrut(4));
        textos.add(lblSub);

        panelBienvenida.add(textos, BorderLayout.WEST);
        panelPrincipal.add(panelBienvenida, BorderLayout.CENTER);

        // ── Tarjetas ─────────────────────────────────────────
        JPanel panelCards = new JPanel(new GridLayout(1, 3, 16, 0));
        panelCards.setBackground(new Color(240, 242, 248));
        panelCards.setBorder(BorderFactory.createEmptyBorder(16, 40, 40, 40));

        panelCards.add(crearCard(
            "Gestión de Productos",
            "Registrar, modificar y eliminar productos",
            new Color(22, 48, 95),
            new Color(40, 80, 150),
            e -> new ProductoView()
        ));

        panelCards.add(crearCard(
            "Reportes",
            "Ver y exportar reportes en PDF",
            new Color(0, 105, 75),
            new Color(0, 140, 100),
            e -> new ReporteView()
        ));

        panelCards.add(crearCard(
            "Cerrar Sesión",
            "Salir del sistema de gestión",
            new Color(150, 30, 30),
            new Color(190, 45, 45),
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
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(colorBase);
        card.setBorder(BorderFactory.createEmptyBorder(20, 18, 20, 18));
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitulo.setForeground(Color.WHITE);

        JLabel lblDesc = new JLabel("<html><p style='width:120px'>" + desc + "</p></html>");
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblDesc.setForeground(new Color(200, 220, 255));
        lblDesc.setBorder(BorderFactory.createEmptyBorder(6, 0, 12, 0));

        JButton btn = new JButton("Abrir →");
        btn.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(255, 255, 255, 40));
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(90, 28));
        btn.addActionListener(accion);

        // Hover en card
        card.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { card.setBackground(colorHover); }
            public void mouseClicked(MouseEvent e) { accion.actionPerformed(null); }
            public void mouseExited(MouseEvent e)  { card.setBackground(colorBase); }
        });

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        bottom.setBackground(colorBase);
        bottom.add(btn);

        card.add(lblTitulo, BorderLayout.NORTH);
        card.add(lblDesc,   BorderLayout.CENTER);
        card.add(bottom,    BorderLayout.SOUTH);

        // Hover en bottom panel
        bottom.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { card.setBackground(colorHover); bottom.setBackground(colorHover); }
            public void mouseExited(MouseEvent e)  { card.setBackground(colorBase);  bottom.setBackground(colorBase); }
        });

        return card;
    }
}
