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
public class Producto {
    private String nombre;
    private int precio;
    private int stock;
    private String codigo;
    private int stockCritico;
    
        /**
     * El constructor recibe como parámetros el nombre del producto, el precio, el stock, el código y el stock crítico.
     * @param nombre
     * @param precio
     * @param stock
     * @param codigo 
     */
    public Producto(String nombre, int precio, int stock, String codigo, int stockCritico){
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.codigo = codigo;
        this.stockCritico=stockCritico;
        
    }
    
    /**
     * El constructor recibe como parámetros el nombre del producto, el precio, el stock, el código y el stock crítico.
     * @param nombre
     * @param precio
     * @param stock
     * @param codigo 
     */
    public Producto(String nombre, int precio, int stock, String codigo){
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.codigo = codigo;
        this.stockCritico=20;
        
    }
    
    /**
     * El constructor no recibe parametros, y por ende, no instancia ningún atributo.
     */
    Producto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
        /**
     * Retorna el nombre del producto.
     * @return String
     */
    public String getNombre(){
        return this.nombre;
    }
    
    /**
     * Retorna el precio del producto.
     * @return int
     */
    public int getPrecio(){
        return this.precio;
    }
    
    /**
     * Retorna el stock del producto.
     * @return int
     */
    public int getStock(){
        return this.stock;
    }
    
    public boolean verificarStockCirtico(){
        if(this.stockCritico <= 20){
            return true;
        }
        return false;
    }
    
    /**
     * Retorna el código del producto.
     * @return String
     */
    public String getCodigo(){
        return this.codigo;
    }
    
    /**
     * Retorna el stock crítico del producto.
     * @return int
     */
    public int getStockCritico(){
        return this.stockCritico;
    }
    
    /**
     * Método para cambiar el stock crítico del producto.
     * @param stockCritico 
     */
    public void setStockCritico(int stockCritico){
        this.stockCritico = stockCritico;
    }
    
    /**
     * Método para cambiar el precio del producto.
     * @param precio 
     */
    public void setPrecio(int precio){
        this.precio = precio;
    }
    
    /**
     * Método para cambiar el stock del producto.
     * @param stock 
     */
    public void setStock(int stock){
        this.stock = stock;
    }
    
    /**
     * Método para sumarle el precio a un producto.
     * @param precio 
     */
    public void sumarPrecio(int precio){
        this.precio = this.precio + precio;
    }
    
    /**
     * Método para sumarle el stock a un producto.
     * @param suma 
     */
    public void sumarStock(int suma){
        this.stock = this.stock + suma;
    }
    

}
