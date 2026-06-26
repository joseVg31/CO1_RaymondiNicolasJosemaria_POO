package controller;

import model.Producto;
import model.ProductoDAO;
import view.ProductoView;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProductoController {

    private ProductoView vista;
    private ProductoDAO dao;

    public ProductoController(ProductoView vista) {
        this.vista = vista;
        this.dao = new ProductoDAO();
        initListeners();
        cargarTabla(dao.listar());
    }

    private void initListeners() {
        vista.getBtnRegistrar().addActionListener(e -> registrar());
        vista.getBtnModificar().addActionListener(e -> modificar());
        vista.getBtnEliminar().addActionListener(e -> eliminar());
        vista.getBtnBuscar().addActionListener(e -> buscar());
        vista.getBtnLimpiar().addActionListener(e -> limpiar());

        // Al seleccionar fila → cargar campos
        vista.getTabla().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) cargarDesdeFila();
        });
    }

    private void registrar() {
        Producto p = obtenerDesdeCampos();
        if (p == null) return;

        if (dao.registrar(p)) {
            vista.mostrarMensaje("Producto registrado correctamente.");
            limpiar();
            cargarTabla(dao.listar());
        } else {
            vista.mostrarError("Error al registrar el producto.");
        }
    }

    private void modificar() {
        String idStr = vista.getTxtId().getText().trim();
        if (idStr.isEmpty()) {
            vista.mostrarError("Seleccione un producto de la tabla.");
            return;
        }

        Producto p = obtenerDesdeCampos();
        if (p == null) return;
        p.setId(Integer.parseInt(idStr));

        if (dao.modificar(p)) {
            vista.mostrarMensaje("Producto modificado correctamente.");
            limpiar();
            cargarTabla(dao.listar());
        } else {
            vista.mostrarError("Error al modificar el producto.");
        }
    }

    private void eliminar() {
        String idStr = vista.getTxtId().getText().trim();
        if (idStr.isEmpty()) {
            vista.mostrarError("Seleccione un producto de la tabla.");
            return;
        }

        int confirm = vista.confirmar("¿Desea eliminar el producto seleccionado?");
        if (confirm == 0) {
            if (dao.eliminar(Integer.parseInt(idStr))) {
                vista.mostrarMensaje("Producto eliminado correctamente.");
                limpiar();
                cargarTabla(dao.listar());
            } else {
                vista.mostrarError("Error al eliminar el producto.");
            }
        }
    }

    private void buscar() {
        String texto = vista.getTxtBuscar().getText().trim();
        List<Producto> lista = dao.buscar(texto);
        cargarTabla(lista);
    }

    private void limpiar() {
        vista.limpiarCampos();
        cargarTabla(dao.listar());
    }

    private void cargarDesdeFila() {
        int fila = vista.getTabla().getSelectedRow();
        if (fila < 0) return;
        DefaultTableModel modelo = (DefaultTableModel) vista.getTabla().getModel();
        vista.getTxtId().setText(modelo.getValueAt(fila, 0).toString());
        vista.getTxtNombre().setText(modelo.getValueAt(fila, 1).toString());
        vista.getTxtCategoria().setText(modelo.getValueAt(fila, 2).toString());
        vista.getTxtPrecio().setText(modelo.getValueAt(fila, 3).toString());
        vista.getTxtStock().setText(modelo.getValueAt(fila, 4).toString());
    }

    private void cargarTabla(List<Producto> lista) {
        DefaultTableModel modelo = (DefaultTableModel) vista.getTabla().getModel();
        modelo.setRowCount(0);
        for (Producto p : lista) {
            modelo.addRow(new Object[]{
                p.getId(), p.getNombre(), p.getCategoria(),
                String.format("%.2f", p.getPrecio()), p.getStock()
            });
        }
    }

    private Producto obtenerDesdeCampos() {
        String nombre = vista.getTxtNombre().getText().trim();
        String categoria = vista.getTxtCategoria().getText().trim();
        String precioStr = vista.getTxtPrecio().getText().trim();
        String stockStr = vista.getTxtStock().getText().trim();

        if (nombre.isEmpty() || categoria.isEmpty() || precioStr.isEmpty() || stockStr.isEmpty()) {
            vista.mostrarError("Complete todos los campos.");
            return null;
        }

        try {
            double precio = Double.parseDouble(precioStr);
            int stock = Integer.parseInt(stockStr);
            return new Producto(nombre, categoria, precio, stock);
        } catch (NumberFormatException e) {
            vista.mostrarError("Precio y Stock deben ser numéricos.");
            return null;
        }
    }
}
