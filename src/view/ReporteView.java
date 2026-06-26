package view;

import controller.ReporteController;

import javax.swing.*;
import java.awt.*;

public class ReporteView extends JFrame {

    private JButton btnVerReporte;
    private JButton btnExportarPDF;

    public ReporteView() {
        initUI();
        new ReporteController(this);
        setVisible(true);
    }

    private void initUI() {
        setTitle("ValleTech - Reportes");
        setSize(420, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(245, 247, 250));

        // ── Header ──────────────────────────────────────────
        JPanel header = new JPanel();
        header.setBackground(new Color(0, 120, 100));
        header.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        JLabel lblTitulo = new JLabel("📊  Módulo de Reportes");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setForeground(Color.WHITE);
        header.add(lblTitulo);

        panelPrincipal.add(header, BorderLayout.NORTH);

        // ── Descripción ───────────────────────────────────────
        JLabel lblDesc = new JLabel(
            "<html><center>Genere reportes de los productos<br>almacenados en la base de datos.</center></html>",
            SwingConstants.CENTER);
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblDesc.setForeground(new Color(80, 80, 100));
        lblDesc.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        panelPrincipal.add(lblDesc, BorderLayout.CENTER);

        // ── Botones ────────────────────────────────────────
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 20, 0));
        panelBotones.setBackground(new Color(245, 247, 250));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 40, 40, 40));

        btnVerReporte = crearBoton("👁️ Ver Reporte", new Color(0, 120, 100));
        btnExportarPDF = crearBoton("📄 Exportar PDF", new Color(160, 40, 40));

        panelBotones.add(btnVerReporte);
        panelBotones.add(btnExportarPDF);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(160, 46));
        return btn;
    }

    // ── Getters ───────────────────────────────────────────────
    public JButton getBtnVerReporte()   { return btnVerReporte; }
    public JButton getBtnExportarPDF()  { return btnExportarPDF; }

    // ── Helpers ───────────────────────────────────────────────
    public void mostrarMensaje(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
