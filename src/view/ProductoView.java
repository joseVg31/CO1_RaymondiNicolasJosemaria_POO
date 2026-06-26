package view;

import controller.ProductoController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProductoView extends JFrame {

    // Campos del formulario
    private JTextField txtId, txtNombre, txtCategoria, txtPrecio, txtStock, txtBuscar;

    // Botones
    private JButton btnRegistrar, btnModificar, btnEliminar, btnBuscar, btnLimpiar;

    // Tabla
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public ProductoView() {
        initUI();
        new ProductoController(this);
        setVisible(true);
    }

    private void initUI() {
        setTitle("ValleTech - CRUD de Productos");
        setSize(900, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBackground(new Color(245, 247, 250));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // ── Formulario ────────────────────────────────────────
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(Color.WHITE);
        panelForm.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            "Datos del Producto"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 8, 5, 8);

        txtId = new JTextField(5);
        txtId.setEditable(false);
        txtId.setBackground(new Color(240, 240, 240));
        txtNombre    = new JTextField(20);
        txtCategoria = new JTextField(15);
        txtPrecio    = new JTextField(10);
        txtStock     = new JTextField(8);

        agregarCampo(panelForm, gbc, "ID:",        txtId,       0, 0);
        agregarCampo(panelForm, gbc, "Nombre:",    txtNombre,   0, 1);
        agregarCampo(panelForm, gbc, "Categoría:", txtCategoria,0, 2);
        agregarCampo(panelForm, gbc, "Precio:",    txtPrecio,   0, 3);
        agregarCampo(panelForm, gbc, "Stock:",     txtStock,    0, 4);

        panelPrincipal.add(panelForm, BorderLayout.WEST);

        // ── Panel de botones ──────────────────────────────────
        JPanel panelBotones = new JPanel(new GridLayout(5, 1, 0, 10));
        panelBotones.setBackground(new Color(245, 247, 250));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));

        btnRegistrar = crearBoton("➕ Registrar",  new Color(0, 120, 80));
        btnModificar = crearBoton("✏️ Modificar",   new Color(30, 80, 160));
        btnEliminar  = crearBoton("🗑️ Eliminar",    new Color(180, 40, 40));
        btnBuscar    = crearBoton("🔍 Buscar",      new Color(100, 80, 160));
        btnLimpiar   = crearBoton("🔄 Limpiar",     new Color(80, 80, 80));

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnLimpiar);

        panelPrincipal.add(panelBotones, BorderLayout.EAST);

        // ── Buscador ──────────────────────────────────────────
        JPanel panelBuscar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBuscar.setBackground(new Color(245, 247, 250));
        txtBuscar = new JTextField(25);
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panelBuscar.add(new JLabel("Buscar por nombre:"));
        panelBuscar.add(txtBuscar);

        // ── Tabla ─────────────────────────────────────────────
        String[] columnas = {"ID", "Nombre", "Categoría", "Precio", "Stock"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };

        tabla = new JTable(modeloTabla);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabla.setRowHeight(26);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabla.getTableHeader().setBackground(new Color(30, 60, 114));
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.setSelectionBackground(new Color(200, 220, 255));

        // Ancho columnas
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(120);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(60);

        JScrollPane scroll = new JScrollPane(tabla);

        JPanel panelCentro = new JPanel(new BorderLayout(0, 5));
        panelCentro.setBackground(new Color(245, 247, 250));
        panelCentro.add(panelBuscar, BorderLayout.NORTH);
        panelCentro.add(scroll, BorderLayout.CENTER);

        panelPrincipal.add(panelCentro, BorderLayout.CENTER);
        add(panelPrincipal);
    }

    private void agregarCampo(JPanel panel, GridBagConstraints gbc, String label,
                               JTextField campo, int col, int fila) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = col * 2;     gbc.gridy = fila;
        panel.add(lbl, gbc);

        campo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(4, 6, 4, 6)));
        gbc.gridx = col * 2 + 1; gbc.gridy = fila;
        panel.add(campo, gbc);
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(140, 36));
        return btn;
    }

    // ── Getters ───────────────────────────────────────────────
    public JTextField getTxtId()        { return txtId; }
    public JTextField getTxtNombre()    { return txtNombre; }
    public JTextField getTxtCategoria() { return txtCategoria; }
    public JTextField getTxtPrecio()    { return txtPrecio; }
    public JTextField getTxtStock()     { return txtStock; }
    public JTextField getTxtBuscar()    { return txtBuscar; }
    public JButton getBtnRegistrar()    { return btnRegistrar; }
    public JButton getBtnModificar()    { return btnModificar; }
    public JButton getBtnEliminar()     { return btnEliminar; }
    public JButton getBtnBuscar()       { return btnBuscar; }
    public JButton getBtnLimpiar()      { return btnLimpiar; }
    public JTable  getTabla()           { return tabla; }

    // ── Helpers ───────────────────────────────────────────────
    public void mostrarMensaje(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public int confirmar(String msg) {
        return JOptionPane.showConfirmDialog(this, msg, "Confirmar", JOptionPane.YES_NO_OPTION);
    }

    public void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtCategoria.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        txtBuscar.setText("");
        tabla.clearSelection();
    }
}
