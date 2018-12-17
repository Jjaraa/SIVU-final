package logico;


import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Master
 */
public class Vendedor {
    private String nombreUsuario;
    private String contraseña; 
    private String nombre;
    private ArrayList<Venta> ventasUsuario;
    
    /**
     * El constructor recibe como parametros el nombre de usuario del vendedor, su contraseña y su nombre real.
     * @param nombreUsuario
     * @param contraseña
     * @param nombre 
     */
    public Vendedor(String nombreUsuario, String contraseña, String nombre){
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.ventasUsuario = new ArrayList<>();
    }
    /**
     * Retorna el nombre de usuario del vendedor.
     * @return String
     */
    public String getNombreUsuario(){
        return this.nombreUsuario;
    }
    
    /**
     * Retorna la contraseña del vendedor.
     * @return String
     */
    public String getContraseña(){
        return this.contraseña;
    }
    
    /**
     * Retorna el ArrayList de ventas del vendedor.
     * @return ArrayList<Venta>
     */
    public ArrayList<Venta> getVentasUsuario(){
        return this.ventasUsuario;
    }
    
    /**
     * Retorna el nombre del vendedor.
     * @return String
     */
    public String getNombre(){
        return this.nombre;
    }
    
    /**
     * Método para cambiar el ArrayList de ventas del vendedor.
     * @param ventas 
     */
    public void setVentas(ArrayList<Venta> ventas){
        this.ventasUsuario = ventas;
    }
    
    /**
     * Método para cambiar la contraseña. Retorna verdadero si se realizó la operacion.
     * @param usuario
     * @param contraseñaAntigua
     * @param contraseñaNueva
     * @return 
     */
    public boolean cambiarContraseña(String usuario, String contraseñaAntigua, String contraseñaNueva){
        boolean confirmacion = false;
        if((usuario.equals(this.nombreUsuario))&&(contraseñaAntigua.equals(this.nombreUsuario))){
            this.contraseña = contraseñaNueva;
            confirmacion = true;
        }
        return confirmacion;
    }
    
    /**
     * Método para  vender un producto. Retorna true si la venta se hizo satisfactoriamente.
     * @param codigo
     * @param cantidad
     * @param productos
     * @return 
     */
    public boolean vender(String codigo, int cantidad, ArrayList<Producto> productos){
        int precioTotal;
        boolean realizado = false;
        for(Producto item:productos){
            if(codigo.equals(item.getCodigo())){
                item.sumarStock(-cantidad);
                precioTotal = item.getPrecio()*cantidad;
                codigo = item.getCodigo();
                Venta venta = new Venta(precioTotal, codigo, cantidad,this.nombre);
                this.ventasUsuario.add(venta);
                realizado = true;
                
                
            }
        }
        return realizado;
    }
    
    /**
     * Método para eliminar una venta. Retorna true si se realizó satisfactoriamente.
     * @param venta
     * @param productos
     * @return 
     */
    public boolean eliminarVenta(Venta venta, ArrayList<Producto> productos){
        boolean realizado = false;
        this.ventasUsuario.remove(venta);
        if(this.ventasUsuario.contains(venta)==false){
            String codigo = venta.getCodigo();
            Producto producto = buscarProducto(codigo, productos);
            producto.setStock(producto.getStock()+venta.getCantidad());
            realizado = true;
        }
        return realizado;
    }
       
    /**
     * Retorna los valores de una venta en especifico.
     * @param venta
     * @return 
     */
    public String[] retornarVentaActual(Venta venta){
        String[] valoresVenta = new String[4];
        valoresVenta[0] = (venta.getCodigo());
        valoresVenta[1] = Integer.toString(venta.getCantidad());
        valoresVenta[2] = Integer.toString(venta.getValor());
        valoresVenta[3] = venta.getVendedor();
        return valoresVenta;
    }
    
    /**
     * Retorna los valores de todas las ventas del vendedor.
     * @return 
     */
    public String[][] retornarVentasTotales(){
        String[][] valoresVentas = new String[this.ventasUsuario.size()][4];
        int contador = 0;
        for(Venta item:this.ventasUsuario){
            valoresVentas[contador][0] = (item.getCodigo());
            valoresVentas[contador][1] = Integer.toString(item.getCantidad());
            valoresVentas[contador][2] = Integer.toString(item.getValor());
            valoresVentas[contador][3] = item.getVendedor();
        }
        return valoresVentas;
    }
    
    /**
     * Método para buscar los productos en el inventario.
     * @param codigo
     * @param productos
     * @return 
     */
     public Producto buscarProducto(String codigo, ArrayList<Producto> productos){
        Producto producto = null;
         for(Producto item:productos){
            if(codigo.equals(item.getCodigo())){
                producto = item;
            }
        }
         return producto;
    }
    
  
    
    /**
     * Retorna la cantidad de ventas que se realizaron por el usuario en esa sesión.
     * @return int
     */ 
    public int totalVentas(){
        return this.ventasUsuario.size();    
    
    }
    
   
    /**
     * Retorna la cantidad de productos vendidos por el usuario en esa sesión.
     * @return int
     */
    public int cantidadVendida(){
        int cantidadVendida = 0;
        for(Venta item: this.ventasUsuario){
            cantidadVendida += item.getCantidad();
        }
        return cantidadVendida;
    }
    
    /**
     * Retorna el valor total que se vendio entre todos los productos vendidos por el usuario en esa sesión.
     * @return int
     */
    public int valorVendido(){
        int valorVendido = 0;
        for(Venta item: this.ventasUsuario){
            valorVendido += item.getValor();
        }
        return valorVendido;
    }
    
    
    /**
     * Retorna el porcentaje de productos vendidos por el usuario comparados con el total de los productos vendidos por todos los usuarios.
     * @param ventasTotales
     * @return double
     */
    public double porcentajeVendido(ArrayList<Venta> ventasTotales){
        int total = 0; 
        int totalUsuario = cantidadVendida();
        for(Venta item2: ventasTotales){
            total += item2.getCantidad();
        }
        double porcentaje = (totalUsuario*100)/total;
        return porcentaje; 
    }
    
    
    
    /**
     * Retorna el porcentaje de su valor total vendido entre todos los productos del usuario, comparado con el total vendido entre todos
     * @param ventasTotales
     * @return double
     */
    public double porcentajeValorVentas(ArrayList<Venta> ventasTotales){
        int totalUsuario = valorVendido();
        int total = 0;
        for(Venta item2: ventasTotales){
            total += item2.getValor();
        }
        double porcentajeValor = (totalUsuario*100)/total; 
        return porcentajeValor;
    }
    
   
        
    
}
