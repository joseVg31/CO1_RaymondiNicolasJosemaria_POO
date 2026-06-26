package controller;

import model.Producto;
import model.ProductoDAO;
import view.ReporteView;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReporteController {

    private ReporteView vista;
    private ProductoDAO dao;

    public ReporteController(ReporteView vista) {
        this.vista = vista;
        this.dao = new ProductoDAO();
        initListeners();
    }

    private void initListeners() {
        vista.getBtnVerReporte().addActionListener(e -> verReporte());
        vista.getBtnExportarPDF().addActionListener(e -> exportarPDF());
    }

    public void verReporte() {
        try {
            List<Producto> lista = dao.listar();

            JRBeanCollectionDataSource dataSource =
                    new JRBeanCollectionDataSource(lista);

            String ruta = "src/reports/ReporteProductos.jrxml";

            JasperReport reporte =
                    JasperCompileManager.compileReport(ruta);

            Map<String, Object> parametros = new HashMap<>();

            JasperPrint print =
                    JasperFillManager.fillReport(reporte, parametros, dataSource);

            JasperViewer.viewReport(print, false);

        } catch (Exception e) {
            vista.mostrarMensaje("Error al generar reporte: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void exportarPDF() {
        try {
            List<Producto> lista = dao.listar();

            JRBeanCollectionDataSource dataSource =
                    new JRBeanCollectionDataSource(lista);

            String ruta = "src/reports/ReporteProductos.jrxml";

            JasperReport reports =
                    JasperCompileManager.compileReport(ruta);

            Map<String, Object> parametros = new HashMap<>();

            JasperPrint print =
                    JasperFillManager.fillReport(reports, parametros, dataSource);

            JasperExportManager.exportReportToPdfFile(
                    print,
                    "ReporteProductos.pdf"
            );

            vista.mostrarMensaje("PDF exportado correctamente.");

        } catch (Exception e) {
            vista.mostrarMensaje("Error al exportar PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }
}