/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Entidad.Cliente;
import Entidad.Empleado;
import Entidad.Padre;
import Entidad.Producto;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.DeleteDimensionRequest;
import com.google.api.services.sheets.v4.model.DimensionRange;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Interface.DatosDao;

/**
 *
 * @author 51934
 */
public class DatosJDBC implements DatosDao {

    public DatosJDBC() {
    }
    final String spreadsheetId = "1pDdsEtVUmzR-Q7FwYteo6FGMJKKXJu1BamtGHsRqs-k";

    public List<Padre> read(String tipo) throws GeneralSecurityException, IOException {


        Sheets service = SheetsQuickstart.getService();
        //OBTENEMOS LA HOJA DE CALCULO A MODIFICAR
        String HOJA = Obtener_hoja(tipo);

        //OBTENEMOS LA COLUMNA MAXIMA DE LA COLUMNA
        String COLUMNA = Obtener_columna(tipo);

        //OBTENER NUMERO DE FILAS DEL EXCEL
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, HOJA + "!M1:M1")
                .execute();

        List<List<Object>> datos = response.getValues();

        int numero_filas = 0;
        //ASGINAR EL NUMERO MAXIMO DE FILAS  PARA LUEGO REALIZAR CONSULTAS
        for (List fila : datos) {
            numero_filas = Integer.parseInt("" + fila.get(0));
        }
        System.out.println(numero_filas);
        final String range = HOJA + "!A2" + ":" + COLUMNA + numero_filas; //OBTENER TODOS LOS DATOS SEGUN EL MAXIMO DE FILAS
        response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();

        List<List<Object>> values = response.getValues();
        List<Padre> dato = new ArrayList<Padre>();
        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
        } else {
            for (List row : values) {
                dato.add(tipo.equalsIgnoreCase("EMPLEADO")
                ? new Empleado(row.get(0).toString(), row.get(1).toString(),
                        row.get(2).toString(), row.get(3).toString(), row.get(4).toString(),
                        row.get(5).toString(), row.get(6).toString(), row.get(7).toString(),
                        row.get(8).toString(), Integer.parseInt(row.get(9).toString()))
                : ( tipo.equalsIgnoreCase("PRODUCTO") ? new Producto(row.get(0).toString(), row.get(1).toString(),
                row.get(2).toString(), row.get(3).toString(), row.get(4).toString(),
                row.get(5).toString(), row.get(6).toString(), row.get(7).toString(),
                row.get(8).toString(), Integer.parseInt(row.get(9).toString())): 
                
                new Cliente(row.get(0).toString(), row.get(1).toString(),
                row.get(2).toString(), row.get(3).toString(), row.get(4).toString(),
                row.get(5).toString(), Integer.parseInt(row.get(6).toString()))));
            }
        }
        return dato;
    }

    public int insert(Padre valor, String tipo) {
        try {
            Sheets service = SheetsQuickstart.getService();
            ValueRange datos = new ValueRange()
                    .setValues(Arrays.<List<Object>>asList(
                            Arrays.<Object>asList(
                                    getRegistro(valor, tipo)
                            )
                    ));
            AppendValuesResponse appendResult = service.spreadsheets().values()
                    .append(spreadsheetId,
                            Obtener_hoja(tipo),
                             datos)
                    .setValueInputOption("USER_ENTERED")
                    .setInsertDataOption("INSERT_ROWS")
                    .setIncludeValuesInResponse(true)
                    .execute();
            return 1;
        } catch (Exception ex) {
            return 0;
        }
    }

    public int update(Padre valor, String tipo) {
        try {
            Sheets service = SheetsQuickstart.getService();
            ValueRange body = new ValueRange()
                    .setValues(Arrays.<List<Object>>asList(
                            Arrays.<Object>asList(
                                    getActualizar(valor, tipo)
                            )
                    ));
            
            UpdateValuesResponse result = service.spreadsheets().values()
                    .update(spreadsheetId, Obtener_hoja(tipo) + Obtener_rango(valor, tipo),body)
                    .setValueInputOption("RAW")
                    .execute();

            return 1;
        } catch (Exception ex) {
            System.out.println(ex);
            return 0;
        }
    }

    public int delete(Padre valor,String tipo) {
        try {
            Sheets service = SheetsQuickstart.getService();
            DeleteDimensionRequest deleteRequest = new DeleteDimensionRequest()
                    .setRange(
                            new DimensionRange()
                                    .setSheetId(Obtener_id_hoja(tipo))
                                    .setDimension("ROWS")
                                    .setStartIndex(Obtener_indice(valor, tipo) - 1)
                                    .setEndIndex( Obtener_indice(valor, tipo))
                    );
            List<Request> requests = new ArrayList<Request>();
            requests.add(new Request().setDeleteDimension(deleteRequest));

            BatchUpdateSpreadsheetRequest body = new BatchUpdateSpreadsheetRequest()
                    .setRequests(requests);
            service.spreadsheets().batchUpdate(spreadsheetId, body).execute();

            return 1;
        } catch (Exception ex) {
            return 0;
        }
    }

    private String Obtener_hoja(String tipo) {
        switch (tipo) {
            case "EMPLEADO": return Empleado.HOJA;
            case "PRODUCTO": return Producto.HOJA;
            case "CLIENTE":  return Cliente.HOJA;
            default:         return "PEPITO14";
        }
    }

    private String Obtener_columna( String tipo) {
        switch (tipo) {
            case "EMPLEADO": return Empleado.COLUMNA;
            case "PRODUCTO": return Producto.COLUMNA;
            case "CLIENTE":  return Cliente.COLUMNA;
            default:         return "PEPITO14";
        }
    }

    private int Obtener_indice(Padre valor,String tipo){
        String indice =  "";
        switch (tipo) {
            case "EMPLEADO": indice = ((Empleado) valor).getIndice(); break;
            case "PRODUCTO": indice =  ((Producto) valor).getIndice(); break;
            case "CLIENTE":  indice =  ((Cliente) valor).getIndice(); break;
            default: indice = "0"; break;
        }
                        
        return Integer.parseInt(indice);
        
    }
    
    private String [] getRegistro(Padre valor,String tipo){

        switch (tipo) {
            case "EMPLEADO": return ((Empleado) valor).getRegistro();
            case "PRODUCTO": return ((Producto) valor).getRegistro(); 
            case "CLIENTE":  return ((Cliente) valor).getRegistro();
            default: return new String[]{""};
        } 
    }
    
    private String [] getActualizar(Padre valor,String tipo){

        switch (tipo) {
            case "EMPLEADO": return ((Empleado) valor).getActualizar();
            case "PRODUCTO": return ((Producto) valor).getActualizar();
            case "CLIENTE":  return ((Cliente) valor).getActualizar();
            default: return new String[]{""};
        } 
    }
    
    private String Obtener_columna_antes_indice(String tipo){
        switch (tipo) {
            case "EMPLEADO": return Empleado.COLUMNA_SIN_INDICE;
            case "PRODUCTO": return Producto.COLUMNA_SIN_INDICE;
            case "CLIENTE":  return Cliente.COLUMNA_SIN_INDICE;
            default:         return "PEPITO14";
        }
    }
    
    private String Obtener_rango(Padre valor ,String tipo){
        String indice = String.valueOf(Obtener_indice(valor, tipo));
        return "!A"+indice+":"+Obtener_columna_antes_indice(tipo)+indice;
    }
    
    private int Obtener_id_hoja(String tipo){
        switch (tipo) {
            case "EMPLEADO": return Empleado.ID_HOJA;
            case "PRODUCTO": return Producto.ID_HOJA;
            case "CLIENTE":  return Cliente.ID_HOJA;
            default:         return 0;
        }
    }

}
