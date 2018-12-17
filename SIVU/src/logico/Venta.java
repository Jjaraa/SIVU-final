package logico;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Master
 */
public class Venta {

    private int valor;
    private String codigo;
    private int cantidad;
    private String vendedor;
    
    /**
     * El constructor recible como parametros el valor total de la venta, el código del producto  vendido, la cantidad vendida
     * y el nombre del vendedor que realizó la venta.
     * @param valor
     * @param codigo
     * @param cantidad
     * @param vendedor 
     */
    public Venta(int valor, String codigo, int cantidad, String vendedor) {
        this.valor = valor;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.vendedor = vendedor;
    }

    /**
     * Retorna el valor total de la venta.
     * @return int
     */
    public int getValor() {
        return this.valor;
    }
    
    /**
     * Retorna el código del producto vendido.
     * @return int
     */
    public String getCodigo() {
        return this.codigo;
    }

    /**
     * Retorna la cantidad vendida.
     * @return int
     */
    public int getCantidad() {
        return this.cantidad;
    }
    
    /**
     * Retorna el nombre del vendedor que realizó la venta.
     * @return String
     */
    public String getVendedor(){
        return this.vendedor;
    }
}
