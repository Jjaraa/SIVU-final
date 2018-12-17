/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazGrafica;
import logico.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
/**
 *
 * @author Oscar
 * Esta clase gestiona el menú del usuario Administrador mediante una ventana
 */
public class MenuAdministrador extends Ventana {

    private JButton botonCrearVendedor, botonAgregarProducto, botonRemoverProducto,botonCambiarContraseña;
    private JButton botonMostrarInventario, botonEditarStockProducto, botonEditarPrecioProducto, botonEstadisticaVendedores, botonCerrarSesion;
    private JLabel textoEncabezado1, textoEncabezado2, textoSubtitulo;
    private SIVU sivu;
    private String nombreUsuario;
  /**
     * El constructor inicializa la ventana del menú administrador y recibe el objeto SIVU y el nombre del administrador.
     *
     * @param sivu
     * @param nombre
     */
    public MenuAdministrador(SIVU sivu, String nombre) {
        super("SIVU", 500, 400);
        this.sivu=sivu;
        this.nombreUsuario=nombre;
        inicializarVentana();
        

    }

    private void inicializarVentana() {
              generarBotonCrearVendedor();
        generarBotonAgregarProducto();
        generarBotonQuitarProducto();
        generarBotonMostrarInventario();
        generarBotonEditarStockProducto();
        generarBotonEditarPrecioProducto();
        generarBotonEstadisticaVendedores();
        generarBotonCerrarSesion();
        generarLabelEncabezado();
        generarLabelSubtitulo();
        repaint();

    }

 

    private void generarBotonCrearVendedor() {
        this.botonCrearVendedor = super.generarBoton("Crear usuario Vendedor", 30, 100, 300, 20);
        this.add(this.botonCrearVendedor);
        this.botonCrearVendedor.addActionListener(this);

    }

    private void generarBotonAgregarProducto() {
        this.botonAgregarProducto = super.generarBoton("Agregar nuevo producto", 30, 130, 300, 20);
        this.add(this.botonAgregarProducto);
        this.botonAgregarProducto.addActionListener(this);

    }

    private void generarBotonQuitarProducto() {
        this.botonRemoverProducto = super.generarBoton("Descontinuar producto", 30, 160, 300, 20);
        this.add(this.botonRemoverProducto);
        this.botonRemoverProducto.addActionListener(this);

    }

    private void generarBotonMostrarInventario() {
        this.botonMostrarInventario = super.generarBoton("Mostrar inventario", 30, 190, 300, 20);
        this.add(this.botonMostrarInventario);
        this.botonMostrarInventario.addActionListener(this);

    }

    private void generarBotonEditarStockProducto() {
        this.botonEditarStockProducto = super.generarBoton("Editar stock de producto", 30, 210, 300, 20);
        this.add(this.botonEditarStockProducto);
        this.botonEditarStockProducto.addActionListener(this);
    }

    private void generarBotonEditarPrecioProducto() {
        this.botonEditarPrecioProducto = super.generarBoton("Editar precio unitario de producto", 30, 240, 300, 20);
        this.add(this.botonEditarPrecioProducto);
        this.botonEditarPrecioProducto.addActionListener(this);

    }

    private void generarBotonCerrarSesion() {
        this.botonCerrarSesion = super.generarBoton("Cerrar Sesión", 300, 330, 150, 20);
        this.add(this.botonCerrarSesion);
        this.botonCerrarSesion.addActionListener(this);

    }

    private void generarBotonEstadisticaVendedores() {
        this.botonEstadisticaVendedores = super.generarBoton("Estadística vendedores", 30, 270, 300, 20);
        this.add(this.botonEstadisticaVendedores);
        this.botonEstadisticaVendedores.addActionListener(this);

    }
       private void generarBotonCambiarContraseña() {
        this.botonCambiarContraseña = super.generarBoton("Cambiar contraseña", 30, 300, 300, 20);
        this.add(this.botonCambiarContraseña);
        this.botonCambiarContraseña.addActionListener(this);

    }
    private void generarLabelEncabezado() {
        super.generarJLabelEncabezado(this.textoEncabezado1, "Bienvenidos al Sistema de inventario", 50, 20, 400, 20);
        super.generarJLabelEncabezado(this.textoEncabezado2, "y Ventas automatizado S.I.V.U", 50, 40, 400, 20);

    }

    private void generarLabelSubtitulo() {
        super.generarJLabel(this.textoSubtitulo, "Seleccione que acción desea realizar:", 50, 70, 400, 40);

    }
    private void generarEstadisticaVendedores(){
        String[] nombreVendedores= sivu.retornarVendedores();
     Object opcion= JOptionPane.showInputDialog(null,"Selecciona un Vendedor", "Elegir",JOptionPane.QUESTION_MESSAGE,null,nombreVendedores, nombreVendedores[0]);
      String opcionVendedor= opcion.toString();
      Vendedor vendedor= sivu.buscarVendedor(opcionVendedor);
      String[][] ventasVendedor= vendedor.retornarVentasTotales();
      String[] nombresColumnas={"Código","Cantidad","Valor","Nombre Vendedor"};
      MenuTabla tabla= new MenuTabla(ventasVendedor,nombresColumnas);
    
    }
    private void generarMostrarInventario(){
        ArrayList<Categoria> categorias= sivu.getCategorias();
        String[] nombreCategorias= new String[categorias.size()];
        for(int i=0; i<categorias.size(); i++){
        nombreCategorias[i]=categorias.get(i).getNombre();
        }    
     Object opcion= JOptionPane.showInputDialog(null,"Selecciona una categoría a mostrar", "Elegir",JOptionPane.QUESTION_MESSAGE,null,nombreCategorias, nombreCategorias[0]);
      String opcionCategoria= opcion.toString();
      String[][] inventario= sivu.retornarCategoria(opcionCategoria);
      String[] nombresColumnas={"Código","Nombre","Stock","Stock crítico","Precio unitario"};
      MenuTabla tabla= new MenuTabla(inventario,nombresColumnas);
     
    }
/**
 * Este método gestiona los eventos de la ventana del menú administrador
 * @param e
 */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.botonCerrarSesion){
        Bienvenida bienvenida= new Bienvenida();
        this.dispose();
        }
        if(e.getSource()==this.botonAgregarProducto){
        MenuAgregarProducto  menu= new MenuAgregarProducto(this.sivu,this.nombreUsuario);
        this.dispose();
        }
        if(e.getSource()==this.botonCrearVendedor){
        MenuCrearVendedor menu= new MenuCrearVendedor(this.sivu,this.nombreUsuario);
        this.dispose();
        }
        if(e.getSource()==this.botonEditarPrecioProducto){
        MenuCambiarPrecio menu= new MenuCambiarPrecio(this.sivu,this.nombreUsuario);
        this.dispose();
        }
        if(e.getSource()==this.botonEditarStockProducto){
        MenuCambiarStock menu= new MenuCambiarStock(this.sivu,this.nombreUsuario);
        this.dispose();
        
        }
        if(e.getSource()== this.botonRemoverProducto){
        MenuEliminarProducto menu= new MenuEliminarProducto(this.sivu,this.nombreUsuario);
        }
        if(e.getSource()==this.botonCambiarContraseña){
        MenuCambiarContraseña menu= new MenuCambiarContraseña(this.sivu,this.nombreUsuario);
  
        }
        if(e.getSource()==this.botonEstadisticaVendedores){
        generarEstadisticaVendedores();
        }
       if(e.getSource()==this.botonMostrarInventario){
       generarMostrarInventario();
       }
    }

}
