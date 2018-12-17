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
public class Admin {

    private String nombreUsuario;
    private String contraseña;

    /**
     * El constructor pide como parametros el nombre y la contraseña del adminsitrador. 
     * @return 
     */
    public Admin(String nombre, String contraseña){
        this.nombreUsuario = nombre;
        this.contraseña = contraseña;
    }
    
    /**
     * Retorna el nombre de usuario del administrador.
     * @return String
     */
    public String getNombreUsuario(){
        return this.nombreUsuario;
    }
    
    /**
     * Retorna la contraseña del usuario administrador.
     * @return String
     */
    public String getContraseña(){
        return this.contraseña;
    }
    
    /**
     * Método para cambiar la contraseña. Retorna true si se realizó la operacion.
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
     * Método para crear un vendedor nuevo. Lo añadirá a un ArrayList de vendedores.
     * @param nombreUsuario
     * @param contraseña
     * @param nombre
     * @param vendedores 
     */
    public void crearUsuario(String nombreUsuario, String contraseña, String nombre, ArrayList<Vendedor> vendedores) {
        Vendedor usuario1 = new Vendedor(nombreUsuario, contraseña, nombre);
        vendedores.add(usuario1);
       
    }
    
    /**
     * Metodo para eliminar un producto. Retornará true si se realizó satisfactoriamente.
     * @param producto
     * @param productos
     * @return 
     */
    public boolean eliminarProducto(Producto producto, ArrayList<Producto> productos){
        boolean realizado = false;
        productos.remove(producto);
        if(productos.contains(producto)){
            realizado = true;
        }
        return realizado;
    }

    
    /**
     * Método para buscar un producto con su código dentro de un ArrayList de productos.
     * @param codigo
     * @param productos
     * @return Producto
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
      * Método para editar el stock de un producto.
      * @param codigo
      * @param valor
      * @param productos 
      */
    public void sumarStock(String codigo, int valor, ArrayList<Producto> productos){
        for(Producto item:productos){
            if(codigo.equals(item.getCodigo())){
                item.sumarStock(valor);
            }
        }
    }
    
    /**
     * Método para editar el precio de un producto.
     * @param codigo
     * @param valor
     * @param productos 
     */    
    public void sumarPrecio(String codigo, int valor, ArrayList<Producto> productos){
        for(Producto item:productos){
            if(codigo.equals(item.getCodigo())){
                item.sumarPrecio(valor);
            }
        }
    }
    
    
    
}
