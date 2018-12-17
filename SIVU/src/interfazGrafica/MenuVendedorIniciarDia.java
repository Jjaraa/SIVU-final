/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfazGrafica;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import logico.*;
    /**
     * @author Oscar
     * Este método se encarga de gestionar la ventana del menu vendedor antes de haber iniciado el día
     *
     *
     */
public class MenuVendedorIniciarDia extends Ventana {

    private JButton botonVenta, botonInventario, botonEstadistica, botonIniciarDia, botonCerrarSesion;
    private JLabel textoEncabezado1, textoEncabezado2, textoSubtitulo,textoNombreVendedor;
    private String nombreVendedor;
    private final SIVU sivu;
    /**
     * El constructor inicializa la ventana del menú vendedor en iniciación de dia y recibe el objeto SIVU y el nombre del vendedor.
     *
     * @param sivu
     * @param nombreVendedor
     */
    public MenuVendedorIniciarDia(SIVU sivu,String nombreVendedor) {
        super("SIVU", 500, 400);
        this.sivu=sivu;
        this.nombreVendedor=nombreVendedor;
        inicializarVentana();

    }

    private void inicializarVentana() {
        generarBotonCrearVenta();
        generarBotonInventario();
        generarBotonEstadistica();
        generarBotonIniciarDia();
        generarBotonCerrarSesion();
        generarLabelEncabezado();
        generarLabelSubtitulo();
        generarLabelNombreVendedor();
        repaint();

    }

    private void generarBotonCrearVenta() {
        this.botonVenta = super.generarBoton("Realizar venta", 30, 120, 300, 20);
        this.add(this.botonVenta);
        this.botonVenta.addActionListener(this);

    }

    private void generarBotonInventario() {
        this.botonInventario = super.generarBoton("Mostrar inventario", 30, 160, 300, 20);
        this.add(this.botonInventario);
        this.botonInventario.addActionListener(this);

    }

    private void generarBotonEstadistica() {
        this.botonEstadistica = super.generarBoton("Estadística", 30, 200, 300, 20);
        this.add(this.botonEstadistica);
        this.botonEstadistica.addActionListener(this);

    }

    private void generarBotonIniciarDia() {
        this.botonIniciarDia = super.generarBoton("Iniciar Día", 30, 240, 300, 20);
        this.add(this.botonIniciarDia);
        this.botonIniciarDia.addActionListener(this);

    }

    private void generarBotonCerrarSesion() {
        this.botonCerrarSesion = super.generarBoton("Cerrar Sesión", 300, 330, 150, 20);
        this.add(this.botonCerrarSesion);
        this.botonCerrarSesion.addActionListener(this);
    }

    private void generarLabelEncabezado() {
        super.generarJLabelEncabezado(this.textoEncabezado1, "Bienvenidos al Sistema de inventario", 50, 20, 400, 20);
        super.generarJLabelEncabezado(this.textoEncabezado2, "y Ventas automatizado S.I.V.U", 50, 40, 400, 20);

    }

    private void generarLabelSubtitulo() {
        super.generarJLabel(this.textoSubtitulo, "Seleccione que acción desea realizar:", 50, 70, 400, 40);

    }
    private void generarLabelNombreVendedor(){
    super.generarJLabel(this.textoNombreVendedor, this.nombreVendedor, 300, 300, 150, 20);
    
    }
private void generarEstadisticaVendedor(){
      Vendedor vendedor= sivu.buscarVendedor(this.nombreVendedor);
      String[][] ventasVendedor= vendedor.retornarVentasTotales();
      String[] nombresColumnas={"Código","Cantidad","Valor","Nombre Vendedor"};
      MenuTabla tabla= new MenuTabla(ventasVendedor,nombresColumnas);
}
private void mostrarInventario(){
  
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
     * Este método se encarga de gestionar los datos de la ventana
     *
     * @param e
     */

    @Override
    public void actionPerformed(ActionEvent e) {
   if(e.getSource()==this.botonVenta){
   JOptionPane.showMessageDialog(this, "Debe comenzar el dia primero");
   
   }
   if(e.getSource()==this.botonEstadistica){
   generarEstadisticaVendedor();
   }
   if(e.getSource()==this.botonInventario){
       mostrarInventario();
   }
   if(e.getSource()==this.botonCerrarSesion){
   Bienvenida bienvenida= new Bienvenida();
   }
   if(e.getSource()==this.botonIniciarDia){
   MenuVendedor menu= new MenuVendedor(this.sivu,this.nombreVendedor);
   this.dispose();
   }
    }

}