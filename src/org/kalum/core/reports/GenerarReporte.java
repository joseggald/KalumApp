package org.kalum.core.reports;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.kalum.core.db.Conexion;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.util.Map;

public class GenerarReporte {
    private static GenerarReporte instancia;
    public void mostrarReporte(String titulo, String nombreReporte, Map parametros){
        try{
            InputStream reporte = GenerarReporte.class.getResourceAsStream(nombreReporte);
            JasperReport reporteMaestro = (JasperReport) JRLoader.loadObject(reporte);
            JasperPrint print = JasperFillManager.fillReport(reporteMaestro, parametros,Conexion.getInstancia().getConexion());
            JasperViewer visor = new JasperViewer(print, false);
            visor.setTitle(titulo);
            visor.setIconImage(new ImageIcon(getClass().getResource("/org/kalum/resource/images/7.png")).getImage());
            visor.setVisible(true);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static GenerarReporte getInstancia(){
        if (instancia==null){
            instancia=new GenerarReporte();
        }
        return instancia;
    }
}
