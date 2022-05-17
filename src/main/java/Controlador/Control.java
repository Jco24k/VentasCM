/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Datos.DatosJDBC;
import Entidad.Cliente;
import Entidad.Empleado;
import Entidad.Padre;
import Interface.DatosDao;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import test.frmCliente;
import test.frmPrincipal;
import test.frmEmpleado;

/**
 *
 * @author 51934
 */
public class Control implements MouseListener {

    frmPrincipal WindowPrincipal;
    frmEmpleado WindowEmpleado;
    frmCliente WindowCliente;
    DatosDao datos_excel;
    List<Padre> lista_datos = new ArrayList<Padre>();

    public Control() {
        WindowPrincipal = new frmPrincipal();
        WindowEmpleado = new frmEmpleado();
        WindowCliente = new frmCliente();
        WindowPrincipal.setLocationRelativeTo(null);

    }

    public void Cargar() {
        WindowPrincipal.setVisible(true);
        WindowPrincipal.getBtnEmpleado().addMouseListener(this);
        WindowPrincipal.getBtnCliente().addMouseListener(this);
        WindowPrincipal.getBtnProducto().addMouseListener(this);
        WindowPrincipal.getBtnVentas().addMouseListener(this);
        WindowPrincipal.getEscritorio().add(WindowEmpleado);
        WindowEmpleado.setVisible(true);
        cargando("EMPLEADO");

    }

    public void cargando(String opcion) {
        datos_excel = new DatosJDBC();
        try {
            lista_datos = datos_excel.read(opcion);
            completar_datos(lista_datos, opcion, false);
        } catch (Exception ex) {
            System.out.println("Error");
        }
    }

    private void cargar_ventanas(String opcion) {
        JInternalFrame[] ventanas = {WindowEmpleado, WindowCliente};
        for (JInternalFrame vent : ventanas) {
            vent.setVisible(false);
            WindowPrincipal.getEscritorio().removeAll();
            WindowPrincipal.getEscritorio().updateUI();
            vent.dispose();
        }
        switch (opcion) {
            case "EMPLEADO":
                WindowPrincipal.getEscritorio().add(WindowEmpleado);
                WindowEmpleado.setVisible(true);
                break;
            case "CLIENTE":
                WindowPrincipal.getEscritorio().add(WindowCliente);
                WindowCliente.setVisible(true);
                break;
            default:
                WindowPrincipal.getEscritorio().add(WindowEmpleado);
                WindowEmpleado.setVisible(true);
                break;
        }

    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(WindowPrincipal.getBtnEmpleado())) {
            if (!WindowEmpleado.isVisible()) {
                cargar_ventanas("EMPLEADO");

            }
        } else if (e.getSource().equals(WindowPrincipal.getBtnCliente())) {
            if (!WindowCliente.isVisible()) {
                cargar_ventanas("CLIENTE");

            }

        }
    }

    public void completar_datos(List<Padre> lista_datos, String opcion, boolean busqueda) {
        for (Padre dato : lista_datos) {
            if (!busqueda) {
                if (opcion.equals("EMPLEADO")) {
                    WindowEmpleado.getLlenar_tabla().addRow(dato.llenar_datos_tbl());
                } else if (opcion.equals("CLIENTE")) {
                    WindowCliente.getLlenar_tabla().addRow(dato.llenar_datos_tbl());
                }
            }

        }
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        if (opcion.equals("EMPLEADO")) {
            for (int i = 0; i < WindowEmpleado.getTblDatos().getColumnCount(); i++) {
                WindowEmpleado.getTblDatos().getColumnModel().getColumn(i).setCellRenderer(Alinear);
            }
        }else if(opcion.equals("CLIENTE")){
            for (int i = 0; i < WindowCliente.getTblDatos().getColumnCount(); i++) {
                WindowCliente.getTblDatos().getColumnModel().getColumn(i).setCellRenderer(Alinear);
            }
        }

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

}
