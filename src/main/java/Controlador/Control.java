/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Datos.DatosJDBC;
import Entidad.Cliente;
import Entidad.Empleado;
import Entidad.Padre;
import Entidad.Ruta;
import Interface.DatosDao;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
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
public class Control implements MouseListener, KeyListener, ActionListener {

    frmPrincipal WindowPrincipal;
    frmEmpleado WindowEmpleado;
    frmCliente WindowCliente;
    DatosDao datos_excel;
    List<Padre> lista_datos = new ArrayList<Padre>();
    List<Padre> lista_rutas = new ArrayList<Padre>();
    Empleado emp_select = new Empleado();

    public Control() {
        WindowPrincipal = new frmPrincipal();
        WindowEmpleado = new frmEmpleado();
        WindowCliente = new frmCliente();
        WindowPrincipal.setLocationRelativeTo(null);

    }

    public void Cargar() {
        WindowPrincipal.setVisible(true);

        //ASIGNARLES EVENTOS
        WindowPrincipal.getBtnEmpleado().addMouseListener(this);
        WindowPrincipal.getBtnCliente().addMouseListener(this);
        WindowPrincipal.getBtnProducto().addMouseListener(this);
        WindowPrincipal.getBtnVentas().addMouseListener(this);
        WindowEmpleado.getTxtBusqueda().addKeyListener(this);
        WindowEmpleado.getTblDatos().addMouseListener(this);
        WindowEmpleado.getBtnRegistrar().addActionListener(this);
        WindowEmpleado.getBtnLimpiar().addActionListener(this);
        WindowPrincipal.getEscritorio().add(WindowEmpleado);
        WindowEmpleado.setVisible(true);
        cargando("EMPLEADO");
        cargar_cbx();

    }

    public void cargar_cbx() {
        datos_excel = new DatosJDBC();
        try {
            //AGREGAR LAS RUTAS DE EMPLEADO AL COMBOBOX
            lista_rutas = datos_excel.read("RUTA");
            for (Padre rutas : lista_rutas) {
                Ruta rt = (Ruta) rutas;
                WindowEmpleado.getCbxRuta().addItem(rt.getZona());
            }

            //CENTRAR DATOS COMBO BOX
            DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
            listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
            WindowEmpleado.getCbxRuta().setRenderer(listRenderer);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void cargando(String opcion) {
        datos_excel = new DatosJDBC();
        try {
            //EXTRAER UNA LISTA CON LOS DATOS DEL EXCEL
            lista_datos = datos_excel.read(opcion);
            //LLENAR A LA TABLA LOS DATOS
            completar_datos(lista_datos, opcion, false);
        } catch (Exception ex) {
            System.out.println("Error");
        }
    }

    private void cargar_ventanas(String opcion) {
        //LIMPIAR Y OCULTAR LAS VENTANAS
        JInternalFrame[] ventanas = {WindowEmpleado, WindowCliente};
        for (JInternalFrame vent : ventanas) {
            vent.setVisible(false);
            WindowPrincipal.getEscritorio().removeAll();
            WindowPrincipal.getEscritorio().updateUI();
            vent.dispose();
        }
        //ABRIR LA VENTANA SELECCIONADA
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
                cargando("EMPLEADO");
                cargar_cbx();
                limpiar("EMPLEADO");

            }
        } else if (e.getSource().equals(WindowPrincipal.getBtnCliente())) {
            if (!WindowCliente.isVisible()) {
                cargar_ventanas("CLIENTE");
                cargando("CLIENTE");
            }
        } else if (e.getSource().equals(WindowEmpleado.getTblDatos())) {
            //AL MOMENTO DE DAR CLICK A LA TABLA OBTENER EL DNI PARA LUEGO BUSCARLO
            String dni = (String) WindowEmpleado.getLlenar_tabla().getValueAt((WindowEmpleado.getTblDatos().getSelectedRow()), 2);
            click_tabla("EMPLEADO", dni);
            WindowEmpleado.getTxtCodgo().setEditable(false);
        }
    }

    private void click_tabla(String opcion, String buscar) {
        for (Padre dato : lista_datos) {
            if (opcion.equals("EMPLEADO")) {
                emp_select = (Empleado) dato.llenar_datos_texto();
                if (buscar.equals(emp_select.getDni())) {
                    //OBTENER Y MOSTRAR EN PANTALLA LOS DATOS DEL EMPLEADO SELECCIONADO
                    WindowEmpleado.getTxtCodgo().setText(emp_select.getCodigo());
                    WindowEmpleado.getTxtNombre().setText(emp_select.getNombre());
                    WindowEmpleado.getTxtApellido().setText(emp_select.getApellido());
                    WindowEmpleado.getTxtDni().setText(emp_select.getDni());
                    WindowEmpleado.getTxtTelefono().setText(emp_select.getTelefono());
                    WindowEmpleado.getTxtDireccion().setText(emp_select.getDireccion());
                    WindowEmpleado.getTxtUsuario().setText(emp_select.getUsuario());
                    WindowEmpleado.getTxtContra().setText(emp_select.getPassword());
                    WindowEmpleado.getTxtContra().setEchoChar('*');

                    //SELECCIONAR LA RUTA DEL EMPLEADO SELECCIONADO EN EL COMBOBOX
                    for (Padre rt : lista_rutas) {
                        Ruta ruta = (Ruta) rt;
                        if (ruta.getCodigo().equals(emp_select.getCod_ruta())) {
                            WindowEmpleado.getCbxRuta().setSelectedItem(ruta.getZona());
                        }
                    }

                    break;
                }
            }

        }
    }

    public void completar_datos(List<Padre> lista_datos, String opcion, boolean busqueda) {

        //LIMPIAMOS LOS DEFAULT MODEL DE LA TABLA 
        WindowEmpleado.getLlenar_tabla().setRowCount(0);
        WindowCliente.getLlenar_tabla().setRowCount(0);

        for (Padre dato : lista_datos) {
            String[] datos_llenar = new String[]{};
            if (!busqueda) { //VERIFICAMOS SI LA OPCION BUSQUEDA ES VERDAD O NO
                datos_llenar = dato.llenar_datos_tbl(); //OBTIENEN UN ARREGLO CON LOS DATOS A MOSTRAR EN LA TABLA
            } else {
                String buscar_datos = "";
                //OBTENGO EL DATO DE LA BUSQUEDA
                if (opcion.equals("EMPLEADO")) {
                    buscar_datos = WindowEmpleado.getTxtBusqueda().getText();
                } else if (opcion.equals("CLIENTE")) {
                    buscar_datos = WindowCliente.getTxtBusqueda().getText();
                }
                //OBTENER LOS DATOS DE LA CONSULTA
                if (dato.llenar_datos_tbl()[0].substring(0, buscar_datos.length()).equalsIgnoreCase(buscar_datos)) {
                    datos_llenar = dato.llenar_datos_tbl();
                } else {
                    continue;
                }
            }
            if (opcion.equals("EMPLEADO")) {
                WindowEmpleado.getLlenar_tabla().addRow(datos_llenar);
            } else if (opcion.equals("CLIENTE")) {
                WindowCliente.getLlenar_tabla().addRow(datos_llenar);
            }
        }

        //CENTRAR DATOS DE LA TABLA
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        if (opcion.equals("EMPLEADO")) {
            for (int i = 0; i < WindowEmpleado.getTblDatos().getColumnCount(); i++) {
                WindowEmpleado.getTblDatos().getColumnModel().getColumn(i).setCellRenderer(Alinear);
            }
        } else if (opcion.equals("CLIENTE")) {
            for (int i = 0; i < WindowCliente.getTblDatos().getColumnCount(); i++) {
                WindowCliente.getTblDatos().getColumnModel().getColumn(i).setCellRenderer(Alinear);
            }
        }

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(WindowEmpleado.getBtnRegistrar())){
            validar_datos_registrar("EMPLEADO");
        }else if(e.getSource().equals(WindowEmpleado.getBtnLimpiar())){
            limpiar("EMPLEADO");
        }
    }
    public void limpiar(String opcion) {
        if (opcion.equals("EMPLEADO")) {
            WindowEmpleado.getTxtCodgo().setText("");
            WindowEmpleado.getTxtNombre().setText("");
            WindowEmpleado.getTxtApellido().setText("");
            WindowEmpleado.getTxtDni().setText("");
            WindowEmpleado.getTxtTelefono().setText("");
            WindowEmpleado.getTxtDireccion().setText("");
            WindowEmpleado.getTxtUsuario().setText("");
            WindowEmpleado.getTxtContra().setText("");
            WindowEmpleado.getTxtContra().setEchoChar((char)0);
            WindowEmpleado.getTxtBusqueda().setText("");
            WindowEmpleado.getCbxRuta().setSelectedIndex(0);
            

        }

    }

    private void validar_datos_registrar(String opcion) {
        if (opcion.equalsIgnoreCase("EMPLEADO")) {
            //VERIFICAR QUE TODOS LOS CAMPOS ESTEN COMPLETOS
            if (!WindowEmpleado.getTxtCodgo().getText().isEmpty() && !WindowEmpleado.getTxtNombre().getText().isEmpty()
                    && !WindowEmpleado.getTxtApellido().getText().isEmpty() && !WindowEmpleado.getTxtDni().getText().isEmpty()
                    && !WindowEmpleado.getTxtTelefono().getText().isEmpty() && !WindowEmpleado.getTxtDireccion().getText().isBlank()
                    && !WindowEmpleado.getTxtUsuario().getText().isEmpty() && !WindowEmpleado.getTxtContra().getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Datos validos");
            } else {
                JOptionPane.showMessageDialog(null, "Completar todos los campos");
            }
        }

    }

    public void keyReleased(KeyEvent e) {
        if (e.getSource().equals(WindowEmpleado.getTxtBusqueda())) {
            completar_datos(lista_datos, "EMPLEADO", true);
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

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }



}
