/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Interface.EmpleadosDao;
import Entidad.Empleado;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




public class EmpleadosJDBC implements EmpleadosDao{

    public EmpleadosJDBC() {
    }
    final String spreadsheetId = "1pDdsEtVUmzR-Q7FwYteo6FGMJKKXJu1BamtGHsRqs-k";

    public List<Empleado>read() throws GeneralSecurityException, IOException {
        Sheets service = SheetsQuickstart.getService();

        //OBTENER NUMERO DE FILAS DEL EXCEL
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, Empleado.HOJA + "!M1:M1")
                .execute();
        
        List<List<Object>> datos = response.getValues();

        int numero_filas = 0;
        //ASGINAR EL NUMERO MAXIMO DE FILAS  PARA LUEGO REALIZAR CONSULTAS
        for (List fila : datos) {
            numero_filas = Integer.parseInt("" + fila.get(0));
        }
        System.out.println(numero_filas);
        final String range = Empleado.HOJA + "!A2" + ":"+Empleado.COLUMNA + numero_filas; //OBTENER TODOS LOS DATOS SEGUN EL MAXIMO DE FILAS
        response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();

        List<List<Object>> values = response.getValues();
        List<Empleado> empleados = new ArrayList<Empleado>();
        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
        } else {
            for (List row : values) {
                empleados.add(new Empleado(row.get(0).toString(), row.get(1).toString(),
                        row.get(2).toString(), row.get(3).toString(), row.get(4).toString(),
                        row.get(5).toString(), row.get(6).toString(), row.get(7).toString(),
                        row.get(8).toString(),Integer.parseInt(row.get(9).toString())));
            }
        }
        return empleados;
    }
    public int insert(Empleado emp){
        try {
            Sheets service = SheetsQuickstart.getService();
            ValueRange datos = new ValueRange()
                    .setValues(Arrays.<List<Object>>asList(
                            Arrays.<Object>asList(emp.getRegistro())
                    ));
            AppendValuesResponse appendResult = service.spreadsheets().values()
                    .append(spreadsheetId,Empleado.HOJA, datos)
                    .setValueInputOption("USER_ENTERED")
                    .setInsertDataOption("INSERT_ROWS")
                    .setIncludeValuesInResponse(true)
                    .execute();
            return 1;
        }catch (Exception ex){
            return 0;
        }
    }
    public int update(Empleado emp){
        try{
            Sheets service = SheetsQuickstart.getService();
            ValueRange body = new ValueRange()
                    .setValues(Arrays.<List<Object>>asList(
                            Arrays.<Object>asList(emp.getActualizar())
                    ));
            UpdateValuesResponse result = service.spreadsheets().values()
                    .update(spreadsheetId,Empleado.HOJA+"!A"+emp.getIndice()+":I"+emp.getIndice(),body)
                    .setValueInputOption("RAW")
                    .execute();

            return 1;
        }catch (Exception ex){
            return 0;
        }
    }

    public int delete(Empleado emp){
        try{
            Sheets service = SheetsQuickstart.getService();
            DeleteDimensionRequest deleteRequest = new DeleteDimensionRequest()
                    .setRange(
                            new DimensionRange()
                            .setSheetId(0)
                            .setDimension("ROWS")
                            .setStartIndex(Integer.parseInt(emp.getIndice())-1)
                            .setEndIndex(Integer.parseInt(emp.getIndice()))
                    );
            List<Request> requests = new ArrayList<Request>();
            requests.add(new Request().setDeleteDimension(deleteRequest));

            BatchUpdateSpreadsheetRequest body = new BatchUpdateSpreadsheetRequest()
                    .setRequests(requests);
            service.spreadsheets().batchUpdate(spreadsheetId,body).execute();

            return 1;
        }catch (Exception ex){
            return 0;
        }
    }




}
