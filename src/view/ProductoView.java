package view;

import controller.ProductoController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
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
        setSize(960, 560);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 247, 250));

        JPanel panelPrincipal = new JPanel(new BorderLayout(12, 0));
        panelPrincipal.setBackground(new Color(245, 247, 250));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));

        // ══ IZQUIERDA: Formulario + Botones ═══════════════════
        JPanel panelIzquierda = new JPanel(new BorderLayout(0, 10));
        panelIzquierda.setBackground(new Color(245, 247, 250));
        panelIzquierda.setPreferredSize(new Dimension(240, 0));

        JPanel panelForm = new JPanel();
        panelForm.setLayout(new BoxLayout(panelForm, BoxLayout.Y_AXIS));
        panelForm.setBackground(Color.WHITE);
        panelForm.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 220)),
                BorderFactory.createEmptyBorder(14, 14, 14, 14)));

        JLabel lblTitulo = new JLabel("Datos del Producto");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTitulo.setForeground(new Color(30, 60, 114));
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelForm.add(lblTitulo);
        panelForm.add(Box.createVerticalStrut(10));

        txtId = new JTextField();
        txtId.setVisible(false);

        txtNombre    = crearCampoConLabel(panelForm, "Nombre");
        txtCategoria = crearCampoConLabel(panelForm, "Categoría");
        txtPrecio    = crearCampoConLabel(panelForm, "Precio (S/.)");
        txtStock     = crearCampoConLabel(panelForm, "Stock");

        // Botones panel izquierdo
        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 6, 6));
        panelBotones.setBackground(Color.WHITE);
        panelBotones.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 220)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        btnRegistrar = crearBoton("Registrar", new Color(34, 139, 74));
        btnModificar = crearBoton("Modificar", new Color(25, 80, 170));
        btnEliminar  = crearBoton("Eliminar",  new Color(190, 40, 40));
        btnLimpiar   = crearBoton("Limpiar",   new Color(70, 70, 80));

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        panelIzquierda.add(panelForm, BorderLayout.CENTER);
        panelIzquierda.add(panelBotones, BorderLayout.SOUTH);
        panelPrincipal.add(panelIzquierda, BorderLayout.WEST);

        // ══ CENTRO: Buscador + Tabla ═══════════════════════════
        JPanel panelCentro = new JPanel(new BorderLayout(0, 10));
        panelCentro.setBackground(new Color(245, 247, 250));

        // ── BUSCADOR con botón visible ──
        JPanel panelBuscar = new JPanel(new BorderLayout(8, 0));
        panelBuscar.setBackground(new Color(245, 247, 250));

        JLabel lblBuscar = new JLabel("Buscar por nombre:  ");
        lblBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        txtBuscar = new JTextField();
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtBuscar.setPreferredSize(new Dimension(0, 32));
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 200)),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)));

        // Botón Buscar con ancho fijo para que sea visible en BorderLayout.EAST
        btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnBuscar.setBackground(new Color(90, 60, 170));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setOpaque(true);
        btnBuscar.setContentAreaFilled(true);
        btnBuscar.setBorderPainted(false);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setPreferredSize(new Dimension(90, 32)); // ancho fijo = siempre visible
        btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        panelBuscar.add(lblBuscar, BorderLayout.WEST);
        panelBuscar.add(txtBuscar, BorderLayout.CENTER);
        panelBuscar.add(btnBuscar, BorderLayout.EAST);

        // Tabla
        String[] cols = {"ID", "Nombre", "Categoría", "Precio (S/.)", "Stock"};
        modeloTabla = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tabla = new JTable(modeloTabla);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabla.setRowHeight(28);
        tabla.setShowGrid(false);
        tabla.setIntercellSpacing(new Dimension(0, 0));
        tabla.setSelectionBackground(new Color(195, 215, 255));
        tabla.setSelectionForeground(Color.BLACK);
        tabla.setFillsViewportHeight(true);

        // Header azul
        JTableHeader header = tabla.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setPreferredSize(new Dimension(0, 32));
        header.setReorderingAllowed(false);
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object v,
                                                           boolean sel, boolean foc, int row, int col) {
                super.getTableCellRendererComponent(t, v, sel, foc, row, col);
                setBackground(new Color(30, 60, 114));
                setForeground(Color.WHITE);
                setFont(new Font("Segoe UI", Font.BOLD, 13));
                setHorizontalAlignment(CENTER);
                setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(60, 90, 150)));
                setText(v != null ? v.toString() : "");
                return this;
            }
        });

        // Filas alternas
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object v,
                                                           boolean sel, boolean foc, int row, int col) {
                super.getTableCellRendererComponent(t, v, sel, foc, row, col);
                if (sel) {
                    setBackground(new Color(195, 215, 255));
                } else {
                    setBackground(row % 2 == 0 ? Color.WHITE : new Color(242, 245, 255));
                }
                setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                setForeground(new Color(40, 40, 60));
                return this;
            }
        });

        tabla.getColumnModel().getColumn(0).setPreferredWidth(45);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(130);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(70);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 215)));
        scroll.getViewport().setBackground(Color.WHITE);

        panelCentro.add(panelBuscar, BorderLayout.NORTH);
        panelCentro.add(scroll, BorderLayout.CENTER);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    private JTextField crearCampoConLabel(JPanel panel, String labelText) {
        JLabel lbl = new JLabel(labelText);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lbl.setForeground(new Color(80, 80, 110));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lbl);
        panel.add(Box.createVerticalStrut(3));

        JTextField campo = new JTextField();
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        campo.setPreferredSize(new Dimension(200, 30));
        campo.setAlignmentX(Component.LEFT_ALIGNMENT);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(190, 190, 210)),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)));
        panel.add(campo);
        panel.add(Box.createVerticalStrut(10));
        return campo;
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(0, 32));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

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
