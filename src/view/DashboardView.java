package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DashboardView extends JFrame {

    private String usuarioActivo;

    public DashboardView(String usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
        initUI();
        setVisible(true);
    }

    private void initUI() {
        setTitle("ValleTech - Dashboard");
        setSize(600, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(245, 247, 250));

        // ── Header ──────────────────────────────────────────
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(30, 60, 114));
        header.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        JLabel lblTitulo = new JLabel("ValleTech - Gestión de Productos");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        header.add(lblTitulo, BorderLayout.WEST);

        JLabel lblUsuario = new JLabel("👤 " + usuarioActivo);
        lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblUsuario.setForeground(new Color(200, 220, 255));
        header.add(lblUsuario, BorderLayout.EAST);

        panelPrincipal.add(header, BorderLayout.NORTH);

        // ── Bienvenida ───────────────────────────────────────
        JLabel lblBienvenida = new JLabel("Seleccione una opción del menú principal", SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblBienvenida.setForeground(new Color(100, 100, 120));
        lblBienvenida.setBorder(BorderFactory.createEmptyBorder(25, 0, 10, 0));
        panelPrincipal.add(lblBienvenida, BorderLayout.CENTER);

        // ── Botones del menú ──────────────────────────────────
        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 20, 0));
        panelBotones.setBackground(new Color(245, 247, 250));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 60, 60, 60));

        JButton btnProductos = crearBoton("📦  Gestión de\n Productos", new Color(30, 60, 114));
        JButton btnReportes  = crearBoton("📊  Reportes", new Color(0, 120, 100));
        JButton btnSalir     = crearBoton("🚪  Cerrar\n  Sesión", new Color(180, 40, 40));

        btnProductos.addActionListener((ActionEvent e) -> {
            new ProductoView();
        });

        btnReportes.addActionListener((ActionEvent e) -> {
            new ReporteView();
        });

        btnSalir.addActionListener((ActionEvent e) -> {
            int op = JOptionPane.showConfirmDialog(this,
                "¿Desea cerrar sesión?", "Confirmar",
                JOptionPane.YES_NO_OPTION);
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
        JButton btn = new JButton("<html><center>" + texto.replace("\n", "<br>") + "</center></html>");
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(150, 90));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return btn;
    }
}
