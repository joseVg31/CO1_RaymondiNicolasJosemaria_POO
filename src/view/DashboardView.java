package view;

import javax.swing.*;
import java.awt.*;

public class DashboardView extends JFrame {

    private String usuarioActivo;

    public DashboardView(String usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
        initUI();
        setVisible(true);
    }

    private void initUI() {
        setTitle("ValleTech - Dashboard");
        setSize(600, 380);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(245, 247, 250));

        // ── Header ──────────────────────────────────────────
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(30, 60, 114));
        header.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));

        JLabel lblTitulo = new JLabel("ValleTech - Gestión de Productos");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lblTitulo.setForeground(Color.WHITE);
        header.add(lblTitulo, BorderLayout.WEST);

        JLabel lblUsuario = new JLabel("👤 " + usuarioActivo);
        lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblUsuario.setForeground(new Color(180, 210, 255));
        header.add(lblUsuario, BorderLayout.EAST);

        panelPrincipal.add(header, BorderLayout.NORTH);

        // ── Subtítulo ────────────────────────────────────────
        JLabel lblSub = new JLabel("Seleccione una opción del menú principal", SwingConstants.CENTER);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSub.setForeground(new Color(120, 120, 140));
        lblSub.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        panelPrincipal.add(lblSub, BorderLayout.CENTER);

        // ── Botones ──────────────────────────────────────────
        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 20, 0));
        panelBotones.setBackground(new Color(245, 247, 250));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 60, 50, 60));

        JButton btnProductos = crearBoton("Gestión de Productos", new Color(30, 60, 114));
        JButton btnReportes  = crearBoton("Reportes",             new Color(0, 120, 90));
        JButton btnSalir     = crearBoton("Cerrar Sesión",        new Color(170, 35, 35));

        btnProductos.addActionListener(e -> new ProductoView());

        btnReportes.addActionListener(e -> new ReporteView());

        btnSalir.addActionListener(e -> {
            int op = JOptionPane.showConfirmDialog(this,
                "¿Desea cerrar sesión?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (op == JOptionPane.YES_OPTION) {
                dispose();
                new LoginView();
            }
        });

        panelBotones.add(btnProductos);
        panelBotones.add(btnReportes);
        panelBotones.add(btnSalir);

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        add(panelPrincipal);
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton("<html><center>" + texto + "</center></html>");
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(150, 80));
        return btn;
    }
}
