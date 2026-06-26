package view;

import controller.ProductoController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ProductoView extends JFrame {

    private JTextField txtId, txtNombre, txtCategoria, txtPrecio, txtStock, txtBuscar;
    private JButton btnRegistrar, btnModificar, btnEliminar, btnBuscar, btnLimpiar;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public ProductoView() {
        initUI();
        new ProductoController(this);
        setVisible(true);
    }

    private void initUI() {
        setTitle("ValleTech - CRUD de Productos");
        setSize(950, 580);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBackground(new Color(245, 247, 250));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        // ── IZQUIERDA: Formulario ─────────────────────────────
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(Color.WHITE);
        panelForm.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 210)),
            "Datos del Producto"));
        panelForm.setPreferredSize(new Dimension(260, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(6, 10, 6, 10);
        gbc.weightx = 1.0;

        txtId        = crearCampo();  txtId.setEditable(false); txtId.setBackground(new Color(240,240,240));
        txtNombre    = crearCampo();
        txtCategoria = crearCampo();
        txtPrecio    = crearCampo();
        txtStock     = crearCampo();

        int row = 0;
        agregarFila(panelForm, gbc, "ID:",         txtId,        row++);
        agregarFila(panelForm, gbc, "Nombre:",     txtNombre,    row++);
        agregarFila(panelForm, gbc, "Categoría:",  txtCategoria, row++);
        agregarFila(panelForm, gbc, "Precio:",     txtPrecio,    row++);
        agregarFila(panelForm, gbc, "Stock:",      txtStock,     row++);

        // relleno para empujar todo arriba
        gbc.gridx = 0; gbc.gridy = row;
        gbc.weighty = 1.0;
        gbc.gridwidth = 2;
        panelForm.add(Box.createVerticalGlue(), gbc);

        panelPrincipal.add(panelForm, BorderLayout.WEST);

        // ── DERECHA: Botones ──────────────────────────────────
        JPanel panelBotones = new JPanel(new GridLayout(5, 1, 0, 10));
        panelBotones.setBackground(new Color(245, 247, 250));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 0));
        panelBotones.setPreferredSize(new Dimension(130, 0));

        btnRegistrar = crearBoton("Registrar",  new Color(0, 130, 80));
        btnModificar = crearBoton("Modificar",  new Color(25, 80, 170));
        btnEliminar  = crearBoton("Eliminar",   new Color(190, 40, 40));
        btnBuscar    = crearBoton("Buscar",     new Color(90, 60, 170));
        btnLimpiar   = crearBoton("Limpiar",    new Color(80, 80, 90));

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnLimpiar);

        panelPrincipal.add(panelBotones, BorderLayout.EAST);

        // ── CENTRO: Buscador + Tabla ──────────────────────────
        JPanel panelCentro = new JPanel(new BorderLayout(0, 8));
        panelCentro.setBackground(new Color(245, 247, 250));

        // Buscador
        JPanel panelBuscar = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelBuscar.setBackground(new Color(245, 247, 250));
        JLabel lblBuscar = new JLabel("Buscar por nombre:  ");
        lblBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtBuscar = new JTextField(22);
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180,180,200)),
            BorderFactory.createEmptyBorder(4,8,4,8)));
        panelBuscar.add(lblBuscar);
        panelBuscar.add(txtBuscar);

        // Tabla
        String[] cols = {"ID", "Nombre", "Categoría", "Precio", "Stock"};
        modeloTabla = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tabla = new JTable(modeloTabla);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabla.setRowHeight(26);
        tabla.setShowGrid(false);
        tabla.setIntercellSpacing(new Dimension(0, 0));
        tabla.setSelectionBackground(new Color(200, 220, 255));
        tabla.setSelectionForeground(Color.BLACK);

        // Header
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabla.getTableHeader().setBackground(new Color(30, 60, 114));
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.getTableHeader().setPreferredSize(new Dimension(0, 30));

        // Ancho columnas
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(190);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(120);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(60);

        // Filas alternas
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object v,
                    boolean sel, boolean foc, int row, int col) {
                super.getTableCellRendererComponent(t, v, sel, foc, row, col);
                if (!sel) setBackground(row % 2 == 0 ? Color.WHITE : new Color(240, 244, 255));
                setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
                return this;
            }
        });

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 210)));

        panelCentro.add(panelBuscar, BorderLayout.NORTH);
        panelCentro.add(scroll, BorderLayout.CENTER);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    private JTextField crearCampo() {
        JTextField f = new JTextField();
        f.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        f.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 200)),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        f.setPreferredSize(new Dimension(160, 30));
        return f;
    }

    private void agregarFila(JPanel p, GridBagConstraints gbc, String label, JTextField campo, int fila) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lbl.setForeground(new Color(50, 50, 80));
        gbc.gridx = 0; gbc.gridy = fila; gbc.gridwidth = 2; gbc.weighty = 0;
        p.add(lbl, gbc);
        gbc.gridy = fila; // mismo espacio, siguiente col
        // label encima del campo
        gbc.gridy = fila * 2;
        p.add(lbl, gbc);
        gbc.gridy = fila * 2 + 1;
        p.add(campo, gbc);
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
        txtId.setText(""); txtNombre.setText(""); txtCategoria.setText("");
        txtPrecio.setText(""); txtStock.setText(""); txtBuscar.setText("");
        tabla.clearSelection();
    }
}
