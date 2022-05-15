/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

/**
 *
 * @author 51934
 */
public class Producto extends Padre{
    public static String HOJA = "PRODUCTOS";
    public static String COLUMNA = "J";
    public static String COLUMNA_SIN_INDICE = "I";
    public static int ID_HOJA = 1299612606;
    private String codigo;
    private String marca;
    private String descripcion;
    private String lote;
    private String cantidad;
    private String precio;
    private String fecha_compra;
    private String fecha_vencimiento;
    private String cod_proveedor;
    private String indice;

    public Producto() {
    }

    public Producto(String codigo, int indice) {
        this.codigo = codigo;
        this.indice =  String.valueOf(indice);
    }

    public Producto(String marca, String descripcion, String lote, String cantidad, String precio, String fecha_compra, String fecha_vencimiento, String cod_proveedor) {
       
        this.marca = marca;
        this.descripcion = descripcion;
        this.lote = lote;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fecha_compra = fecha_compra;
        this.fecha_vencimiento = fecha_vencimiento;
        this.cod_proveedor = cod_proveedor;
        this.codigo = Generar_Codigo(marca);
    }

    public Producto(String codigo, String marca, String descripcion, String lote, String cantidad, String precio, String fecha_compra, String fecha_vencimiento, String cod_proveedor, int indice) {
        this.codigo = codigo;
        this.marca = marca;
        this.descripcion = descripcion;
        this.lote = lote;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fecha_compra = fecha_compra;
        this.fecha_vencimiento = fecha_vencimiento;
        this.cod_proveedor = cod_proveedor;
        this.indice = String.valueOf(indice);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(String fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public String getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(String fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public String getCod_proveedor() {
        return cod_proveedor;
    }

    public void setCod_proveedor(String cod_proveedor) {
        this.cod_proveedor = cod_proveedor;
    }

    public String getIndice() {
        return indice;
    }
    
    
     @Override
    public String toString(){
        return "Producto ("  +codigo+" ,"+marca+ " , "+descripcion+" , "+cantidad+","+indice+")";
    }
    
    public String Generar_Codigo(String marca){

        return "P"+lote+marca.substring(0,3);
    }
    
    public String [] getRegistro(){
        System.out.println(codigo);
        return new String[]{codigo,marca,descripcion,lote,cantidad,precio,fecha_compra,fecha_vencimiento,cod_proveedor,"=FILA()"};
    }

    public String [] getActualizar(){
        System.out.println(codigo);
        return new String[]{codigo,marca,descripcion,lote,cantidad,precio,fecha_compra,fecha_vencimiento,cod_proveedor};
    }
    
}
