/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logico;

import java.util.ArrayList;
import java.util.Iterator;

public class Categoria {

    String codigo;
    String nombre;
    ArrayList<SubCategoria> subCategorias;

    /**
     * El constructor pide como parámetros el código de la categoría y el
     * nombre. El constructor además, instancia el ArrayList de subcategorias.
     *
     * @param codigo
     * @param nombre
     */
    public Categoria(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        subCategorias = new ArrayList<>();
    }

    /**
     * Método que se encarga de añadir un objecto producto proveniente de la
     * lectura de acrhivos a una subcategoria.
     *
     * @param producto
     * @param codigoSubCategoria
     */
    public void addProducto(Producto producto, String codigoSubCategoria) {
        Iterator<SubCategoria> it = this.subCategorias.iterator();
        while (it.hasNext()) {
            SubCategoria sub = it.next();
            if (sub.getCodigo().equals(codigoSubCategoria)) {
                sub.addProducto(producto);
            }
        }
    }

    /**
     * Método el cual se encarga de añadir un nuevo producto a una Sub Categoria
     * especifica. Retornara true si se añadio correctamente y retornara false
     * si esto no se realizó.
     *
     * @param nombreSubCategoria
     * @param nombreProducto
     * @param precio
     * @return boolean
     */
    public boolean addNewProducto(String nombreSubCategoria, String nombreProducto, int precio) {
        boolean estado = false;
        Iterator<SubCategoria> it = this.subCategorias.iterator();
        while (it.hasNext()) {
            SubCategoria sub = it.next();
            if (sub.getNombre().equals(nombreSubCategoria)) {
                sub.addNewProducto(nombreProducto, precio);
                estado = true;
            }
        }
        return estado;
    }

    /**
     * Método para agregar una nueva subcategoría dentro de la categoría.
     *
     * @param subCategoria
     */
    public void addSubCategoria(SubCategoria subCategoria) {
        this.subCategorias.add(subCategoria);
    }

    /**
     * Retorna el código de la categoría.
     *
     * @return int
     */
    public String getCodigo() {
        return this.codigo;
    }

    /**
     * Retorna el nombre de la categoría.
     *
     * @return String
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Retorna el arreglo de Sub Categorias.
     *
     * @return
     */
    public ArrayList<SubCategoria> getSubCategorias() {
        return this.subCategorias;
    }
}
