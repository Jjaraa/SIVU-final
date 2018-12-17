/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logico;

import java.util.ArrayList;

/**
 *
 * @author Master
 */
public class SubCategoria {

    String codigo;
    String nombre;
    Categoria categoria;
    ArrayList<Producto> productosDeSubCategoria;

    /**
     * El constructor recibe como parámetros el código de la subcategoría, su
     * nombre, y la categoría a la que pertenece. El constructor además,
     * instancia el ArrayList de productos de la clase.
     *
     * @param codigo
     * @param nombre
     * @param categoria
     */
    public SubCategoria(String codigo, String nombre, Categoria categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.productosDeSubCategoria = new ArrayList<>();
    }

    /**
     * Retorna el código de la subcategoría.
     *
     * @return int
     */
    public String getCodigo() {
        return this.codigo;
    }

    /**
     * Retorna el nombre de la subcategoría.
     *
     * @return String
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Retorna la categoría a la cual pertenece la subcategoría.
     *
     * @return Categoria
     */
    public Categoria getCategoria() {
        return this.categoria;
    }

    /**
     * Método que se encarga de añadir un objecto producto proveniente de la
     * lectura de acrhivos.
     *
     * @param producto
     */
    public void addProducto(Producto producto) {
        this.productosDeSubCategoria.add(producto);
    }

    /**
     * Método que añade el producto a la subcategoria.
     *
     * @param nombreProducto
     * @param precio
     */
    public void addNewProducto(String nombreProducto, int precio) {
        String codigoProducto = formarCodigo();
        this.productosDeSubCategoria.add(new Producto(nombreProducto, precio, 0, codigoProducto));
    }

    /**
     * Método encargado de crear un código único para elm producto nuevo según
     * el último codigo de producto agregado.
     *
     * @return int
     */
    private String formarCodigo() {
        String codigoCategoria = this.categoria.getCodigo();
        String codigoSubCategoria = this.codigo;
        int codigoProducto = 1;
        for (int i = 0; i < this.productosDeSubCategoria.size(); i++) {
            String codigoFinal = "";
            String codigoTotal = this.productosDeSubCategoria.get(i).getCodigo();
            char[] chars = codigoTotal.toCharArray();
            codigoFinal += chars[4];
            codigoFinal += chars[5];
            codigoFinal += chars[6];
            int codigoFinalInt = Integer.parseInt(codigoFinal);
            if (codigoFinalInt > codigoProducto) {
                codigoProducto = codigoFinalInt;
            }
        }
        String codigoProductoString = "";
        if (codigoProducto >= 10) {
            codigoProductoString = "0" + Integer.toString(codigoProducto);
        } else if (codigoProducto >= 100) {
            codigoProductoString = Integer.toString(codigoProducto);
        } else {
            codigoProductoString = "0" + "0" + Integer.toString(codigoProducto);
        }
        String codigoFinal = codigoCategoria + codigoSubCategoria + codigoProductoString;
        return codigoFinal;
    }

    /**
     * Retorna el arreglo de productos de esta subcategoria.
     *
     * @return ArrayList<Producto>
     */
    public ArrayList<Producto> getProductosDeSubCategoria() {
        return this.productosDeSubCategoria;
    }
}
