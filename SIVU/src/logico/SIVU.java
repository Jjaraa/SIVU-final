package logico;

import gestor.DirectoryFile;
import gestor.InventarioFile;
import gestor.UserFile;
import gestor.VentaFile;
import java.util.ArrayList;
import java.util.Iterator;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Master
 */
public class SIVU {

    private ArrayList<Producto> productos;
    private ArrayList<Venta> ventas;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<Categoria> categorias;
    private Admin administrador;

    /**
     * El constructor solamente instancia los atributos de clase.
     */
    public SIVU() {
        this.productos = new ArrayList<Producto>();
        this.ventas = new ArrayList<Venta>();
        this.vendedores = new ArrayList<Vendedor>();
        this.categorias = new ArrayList<Categoria>();

    }

    /**
     * Retorna las ventas.
     *
     * @return ArrayList<Venta>
     */
    public ArrayList<Venta> getVentas() {
        generarVentas();
        return this.ventas;
    }

    /**
     * Retorna el inventario.
     *
     * @return ArrayList<Producto>
     */
    public ArrayList<Producto> getProductos() {
        return this.productos;
    }

    /**
     * Retorna la lista de vendedores.
     *
     * @return ArrayList<Vendedor>
     */
    public ArrayList<Vendedor> getVendedores() {
        return this.vendedores;
    }

    /**
     * Retorna la lista de categorias.
     *
     * @return ArrayList<Categoria>
     */
    public ArrayList<Categoria> getCategorias() {
        return this.categorias;
    }

    /**
     * Retorna el administrador de SIVU
     *
     * @return Admin
     */
    public Admin getAdministrador() {
        return this.administrador;
    }

    /**
     * Metodo para configurar los archivos en el primer inicio del programa.
     */
    public void generarPrimerInicio() {
        DirectoryFile directorio = new DirectoryFile();
        UserFile userFile = new UserFile();
        directorio.crearDirectoriosSIVU();
        userFile.crearAdmin();
    }

    /**
     * Método para cargar todos los archivos del programa.
     */
    public void cargarArchivos() {
        InventarioFile gestorInventario = new InventarioFile();
        UserFile gestorUser = new UserFile();
        VentaFile gestorVenta = new VentaFile();
        this.vendedores = gestorUser.leerVendedor();
        this.productos = gestorInventario.leerInventario();
        this.ventas = gestorVenta.leerVentasTotales();
        this.administrador = gestorUser.leerAdmin();
        crearCategorias(gestorInventario.leerCategorias());
        crearSubCategorias(gestorInventario.leerSubCategorias());
        separarProductos(gestorInventario.leerInventario());
        cargarVentaVendedores();

    }

    /**
     * Método que guarda todos los datos en sus respectivos archivos. Retorna
     * true cuando termina.
     *
     * @return boolean
     */
    public boolean terminarDia() {
        InventarioFile gestorInventario = new InventarioFile();
        UserFile gestorUser = new UserFile();
        VentaFile gestorVenta = new VentaFile();
        generarVentas();
        gestorInventario.guardarCategorias(this.categorias);
        gestorInventario.guardarInventario(this.productos);
        gestorUser.guardarAdmin(this.administrador);
        gestorUser.guardarVendedores(this.vendedores);
        gestorVenta.grabarVentasTotales(this.ventas);
        guardarVentaVenderores();
        gestorInventario.guardarSubCategorias(obtenerSubCategorias());
        return true;

    }

    /**
     * Retorna el vendedor que corresponda al nombre de usuario con que se
     * buscó. La busqueda se hace dentro de la lista de vendedores.
     *
     * @param nombreUsuario
     * @return Vendedor
     */
    public Vendedor buscarVendedor(String nombreUsuario) {
        Vendedor vendedor1 = new Vendedor(null, null, null);
        for (Vendedor item : this.vendedores) {
            if (nombreUsuario.equals(item.getNombreUsuario())) {
                vendedor1 = item;
            }
        }
        return vendedor1;

    }

    /**
     * Método que retorna todos los valores de cada producto de una categoria en
     * especifico. Incluye todos los productos de todas las subcategorias.
     *
     *
     * @param nombre
     */
    public void agregarCategoria(String nombre) {
        String ultimoCodigoString = "";
        int ultimoCodigo = Integer.parseInt(this.categorias.get(this.categorias.size() - 1).getCodigo());
        if (ultimoCodigo >= 10) {
            ultimoCodigoString = Integer.toString(ultimoCodigo);
        } else if (ultimoCodigo < 10) {
            ultimoCodigoString = "0" + Integer.toString(ultimoCodigo);
        }
        Categoria categoria = new Categoria(ultimoCodigoString, nombre);
        this.categorias.add(categoria);
    }

    /**
     * Método para agregar una subcategoria a una categoria.
     *
     * @param categoria
     * @param nombre
     */
    public void agregarSubCategoria(Categoria categoria, String nombre) {
        String ultimoCodigoString = "";
        int ultimoCodigo = Integer.parseInt(categoria.getSubCategorias().get(categoria.getSubCategorias().size()).getCodigo());
        if (ultimoCodigo >= 10) {
            ultimoCodigoString = Integer.toString(ultimoCodigo);
        } else if (ultimoCodigo < 10) {
            ultimoCodigoString = "0" + Integer.toString(ultimoCodigo);
        }
        SubCategoria sub = new SubCategoria(ultimoCodigoString, nombre, categoria);
        categoria.getSubCategorias().add(sub);
    }

    /**
     * Retorna los nombres de todos los vendedores.
     *
     * @return String[]
     */
    public String[] retornarVendedores() {
        int numeroVendedores = this.vendedores.size();
        String[] nombreVendedores = new String[numeroVendedores];
        int contador = 0;
        for (Vendedor item : this.vendedores) {
            nombreVendedores[contador] = item.getNombre();
            contador++;
        }
        return nombreVendedores;
    }
    
    /**
     *
     * @param nombreCategoria
     * @return
     */
    public String[][] retornarCategoria(String nombreCategoria) {
        ArrayList<Producto> productosTotales = sumarSubCategorias(nombreCategoria);
        String[][] valoresProductos = new String[productosTotales.size()][5];
        int contador = 0;
        for (Producto item : productosTotales) {
            valoresProductos[contador][0] = item.getCodigo();
            valoresProductos[contador][1] = item.getNombre();
            valoresProductos[contador][2] = Integer.toString(item.getStock());
            valoresProductos[contador][3] = Integer.toString(item.getStockCritico());
            valoresProductos[contador][4] = Integer.toString(item.getPrecio());
            contador++;
        }
        return valoresProductos;
    }

    private ArrayList<Producto> sumarSubCategorias(String nombreCategoria) {
        Categoria categoria = buscarCategoria(nombreCategoria);
        ArrayList<Producto> productosTotales = new ArrayList<>();
        for (SubCategoria item : categoria.getSubCategorias()) {
            for (Producto objeto : item.getProductosDeSubCategoria()) {
                productosTotales.add(objeto);
            }
        }
        return productosTotales;
    }

    /**
     *
     * @param nombreCategoria
     * @return
     */
    public Categoria buscarCategoria(String nombreCategoria) {
        Categoria categoriaBuscada = null;
        Iterator<Categoria> it = this.categorias.iterator();
        while (it.hasNext()) {
            Categoria cat = it.next();
            if (cat.getNombre().equals(nombreCategoria)) {
                categoriaBuscada = cat;
            }
        }
        return categoriaBuscada;
    }

    private void generarVentas() {
        ArrayList<Venta> ventasTotales = new ArrayList<>();
        for (Vendedor item : this.vendedores) {
            for (Venta objet : item.getVentasUsuario()) {
                ventasTotales.add(objet);
            }
        }
        this.ventas = ventasTotales;
    }

    private void guardarVentaVenderores() {
        VentaFile gestorUser = new VentaFile();
        for (Vendedor item : this.vendedores) {
            gestorUser.grabarVentaVendedor(item, this.ventas);
        }
    }

    private void cargarVentaVendedores() {
        VentaFile gestorVenta = new VentaFile();
        for (Vendedor item : this.vendedores) {
            item.setVentas(gestorVenta.leerVentaVendedor(item));
        }

    }

    private ArrayList<SubCategoria> obtenerSubCategorias() {
        ArrayList<SubCategoria> SubCategoriasTotales = new ArrayList<>();
        for (Categoria item : this.categorias) {
            for (SubCategoria objet : item.getSubCategorias()) {
                SubCategoriasTotales.add(objet);
            }
        }
        return SubCategoriasTotales;
    }

    /**
     * Método que se encarga de la creación de Categorias recibiendo un arreglo
     * de String directamente desde la lectura de archivos. Devolviendo el
     * arreglo de tipo Categoria.
     *
     * @param categorias
     */
    public void crearCategorias(ArrayList<String> categorias) {
        ArrayList<Categoria> categoriasFinales = new ArrayList<>();
        for (int i = 0; i < categorias.size(); i = +2) {
            String codigoCategoria = categorias.get(i);
            String nombreCategoria = categorias.get(i + 1);
            categoriasFinales.add(new Categoria(codigoCategoria, nombreCategoria));
        }
        this.categorias = categoriasFinales;
    }

    /**
     * Método encargado de la creación de las Sub Categorias. Recibiendo un
     * arreglo de String con los nombres y codigos de estas desde la lectura de
     * archivos. Recibe el Arreglo de tipo Categoria para asignarle a cada una
     * su subcategoria.
     *
     * @param subCategorias
     */
    //codigoCategoria;codigoSub;NombreSub
    public void crearSubCategorias(ArrayList<String> subCategorias) {
        for (int i = 0; i < subCategorias.size(); i = +3) {
            String codigoCategoria = (subCategorias.get(i));
            String nombreSubCategoria = subCategorias.get(i + 1);
            String codigoSubCategoria = (subCategorias.get(i + 2));

            Iterator<Categoria> it = this.categorias.iterator();
            while (it.hasNext()) {
                Categoria cat = it.next();
                if (cat.getCodigo().equals(codigoCategoria)) {
                    cat.addSubCategoria(new SubCategoria(codigoSubCategoria, nombreSubCategoria, cat));
                }
            }
        }
    }

    /**
     * Método encargado de separar los productos según sus codigos
     * correspondiente a Categoria.
     *
     * @param Productos
     */
    public void separarProductos(ArrayList<Producto> Productos) {
        for (int i = 0; i < Productos.size(); i++) {
            String categoria = "";
            String subCategoria = "";
            String codigo = Productos.get(i).getCodigo();
            char[] chars = codigo.toCharArray();
            categoria += chars[0];
            categoria += chars[1];
            subCategoria += chars[2];
            subCategoria += chars[3];
            Iterator<Categoria> it = this.categorias.iterator();
            while (it.hasNext()) {
                Categoria cat = it.next();
                if (cat.getCodigo().equals(categoria)) {
                    cat.addProducto(Productos.get(i), subCategoria);
                }
            }
        }
    }

    /**
     * Método para añadir un nuevo producto a la Categoría y Sub Categoría
     * deseada. Retornara true si se añadio correctamente y retornara false si
     * esto no se realizó.
     *
     * @param nombreCategoria
     * @param nombreSubCategoria
     * @param nombreProducto
     * @param precio
     * @return boolean
     */
    public boolean añadirNuevoProducto(String nombreCategoria, String nombreSubCategoria, String nombreProducto, int precio) {
        boolean estado = false;
        Iterator<Categoria> it = this.categorias.iterator();
        while (it.hasNext()) {
            Categoria cat = it.next();
            if (cat.getNombre().equals(nombreCategoria)) {
                estado = cat.addNewProducto(nombreSubCategoria, nombreProducto, precio);
            }
        }
        return estado;
    }

    /**
     * Método que retorna un arreglo de tipo Producto con todos los productos en
     * todas las Categorias y Subcategorias.
     *
     * @return
     */
    public ArrayList<Producto> getInventarioTotal() {
        ArrayList<Producto> productoTotal = new ArrayList<>();
        for(Categoria categoria : this.categorias){
            for(SubCategoria sub : categoria.getSubCategorias()){
                for(Producto pro : sub.getProductosDeSubCategoria()){
                    productoTotal.add(pro);
                }
            }
        }
        return productoTotal;
    }

    /**
     * Método el cual se encarga de entregar un arreglo con los nombres de todas
     * las categorias.
     *
     * @return ArrayList<String>
     */
    public ArrayList<String> getNombresCategorias() {
        ArrayList<String> nombreCategoria = new ArrayList<>();
        for (int i = 0; i < this.categorias.size(); i++) {
            nombreCategoria.add(this.categorias.get(i).getNombre());
        }
        return nombreCategoria;
    }

    /**
     * Método el cual se encarga de entregar un arreglo con los nombres de todas
     * las Sub Categorias.
     *
     * @param nombreCategoria
     * @return
     */
    public ArrayList<String> getNombreSubcategorias(String nombreCategoria) {
        ArrayList<String> nombreSubCategoria = new ArrayList<>();
        Iterator<Categoria> it = this.categorias.iterator();
        while (it.hasNext()) {
            Categoria cat = it.next();
            if (cat.getNombre().equals(nombreCategoria)) {
                for (int i = 0; i < cat.getSubCategorias().size(); i++) {
                    nombreSubCategoria.add(cat.getSubCategorias().get(i).getNombre());
                }
            }
        }
        return nombreSubCategoria;
    }
}
