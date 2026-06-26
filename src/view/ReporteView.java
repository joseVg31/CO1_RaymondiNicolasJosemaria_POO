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
        setSize(400, 280);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(240, 242, 248));

        // ── Header ──────────────────────────────────────────
        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER));
        header.setBackground(new Color(0, 105, 75));
        header.setBorder(BorderFactory.createEmptyBorder(14, 0, 14, 0));

        JLabel lblTitulo = new JLabel("Módulo de Reportes");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTitulo.setForeground(Color.WHITE);
        header.add(lblTitulo);
        panelPrincipal.add(header, BorderLayout.NORTH);

        // ── Descripción ──────────────────────────────────────
        JLabel lblDesc = new JLabel(
            "<html><center>Genere reportes de los productos<br>almacenados en la base de datos.</center></html>",
            SwingConstants.CENTER);
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblDesc.setForeground(new Color(80, 90, 110));
        lblDesc.setBorder(BorderFactory.createEmptyBorder(24, 20, 16, 20));
        panelPrincipal.add(lblDesc, BorderLayout.CENTER);

        // ── Botones ──────────────────────────────────────────
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        panelBotones.setBackground(new Color(240, 242, 248));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 30, 36, 30));

        btnVerReporte   = crearBoton("Ver Reporte",   new Color(0, 105, 75));
        btnExportarPDF  = crearBoton("Exportar PDF",  new Color(160, 35, 35));

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
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(130, 38));
        return btn;
    }

    public JButton getBtnVerReporte()  { return btnVerReporte; }
    public JButton getBtnExportarPDF() { return btnExportarPDF; }

    public void mostrarMensaje(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
