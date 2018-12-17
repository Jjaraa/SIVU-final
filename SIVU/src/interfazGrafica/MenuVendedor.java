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
     * @author Oscar
     * Esta clase está encargada de gestionar el menu del vendedor
     *
     */
public class MenuVendedor extends Ventana {

    private JButton botonVenta, botonInventario, botonEstadistica, botonTerminarDia, botonCerrarSesion;
    private JLabel textoEncabezado1, textoEncabezado2, textoSubtitulo,textoNombreVendedor;
    private final SIVU sivu;
    private String nombreVendedor;
            /**
     * El constructor inicializa la ventana del menú vendedor  y recibe el objeto SIVU y el nombre del vendedor.
     *
     * @param sivu
     * @param nombreVendedor
     */
    public MenuVendedor(SIVU sivu, String nombreVendedor) {
        super("SIVU", 500, 400);
        this.sivu=sivu;
        this.nombreVendedor=nombreVendedor;
        inicializarVentana();

    }

    private void inicializarVentana() {
        generarBotonCrearVenta();
        generarBotonInventario();
        generarBotonEstadistica();
        generarBotonTerminarDia();
        generarBotonCerrarSesion();
        generarLabelEncabezado();
        generarLabelSubtitulo();
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

    private void generarBotonTerminarDia() {
        this.botonTerminarDia = super.generarBoton("Terminar Día", 30, 240, 300, 20);
        this.add(this.botonTerminarDia);
        this.botonTerminarDia.addActionListener(this);

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
private void generarLabelNombreVendedor(){
    super.generarJLabel(this.textoNombreVendedor, this.nombreVendedor, 300, 300, 150, 20);
    
    }
    /**
     * Este método gestiona los eventos de la ventana del menú vendedor
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
 if(e.getSource()==this.botonVenta){
   MenuVenta menu= new MenuVenta(this.sivu,this.nombreVendedor);
   
   }
   if(e.getSource()==this.botonEstadistica){
   generarEstadisticaVendedor();
   }
   if(e.getSource()==this.botonInventario){
       mostrarInventario();
   }
   if(e.getSource()==this.botonCerrarSesion){
     JOptionPane.showMessageDialog(this,"Debe terminar el día primero");
   }
   if(e.getSource()==this.botonTerminarDia){
    boolean condicion=this.sivu.terminarDia();
    if(condicion==true){
     JOptionPane.showMessageDialog(this,"Dia terminado correctamente");
        MenuVendedorIniciarDia menu= new MenuVendedorIniciarDia(this.sivu,this.nombreVendedor);
    }
    else{
      JOptionPane.showMessageDialog(this,"Hubo un error");
    }
   this.dispose();
   }
    }

}

