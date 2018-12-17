/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfazGrafica;
/**
 * 
 * @author Oscar
 * Esta clase se encarga de gestionar la ventana de bienvenida
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Bienvenida extends Ventana {
private JLabel textoBienvenida, textoSubtitulo;
private JButton botonInicioSesion, botonSalir;
 /**
     * El constructor inicializa la ventana con sus objetos
     *
     * 
     */
public Bienvenida(){
super("SIVU",500,400);
inicializarVentana();

}
/**
 * Este método llama a todos los métodos necesarios para inicializar la ventana.
 */
private void inicializarVentana(){
generarBotonIniciarSesion();
 generarLabelBienvenida();
 generarBotonSalir();
  generarLabelSubtitulo();
  repaint();
}
/**
 * Este método genera el boton de inicio de sesión de la ventana.
 */
private void generarBotonIniciarSesion(){
this.botonInicioSesion= super.generarBoton("Iniciar sesión", 170, 200, 150, 50);
this.add(this.botonInicioSesion);
this.botonInicioSesion.addActionListener(this);

}

private void generarBotonSalir(){
this.botonSalir=super.generarBoton("Salir", 330,330,100,20);
this.add(this.botonSalir);
this.botonSalir.addActionListener(this);
}

private void generarLabelBienvenida(){
super.generarJLabelEncabezado(this.textoBienvenida, "Bienvenidos a S.I.V.U", 135, 20, 300, 50);

}

private void generarLabelSubtitulo(){
super.generarJLabel(this.textoSubtitulo, "S.I.V.U(Sistema de Inventario y Ventas Automatizado)", 60, 100, 400, 50);

}
   /**
     * Este método gestiona los eventos de la ventana de ventas
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==this.botonInicioSesion){
       MenuLogin login= new MenuLogin();
       this.dispose();
       }
       if(e.getSource()==this.botonSalir){
       System.exit(0);
       }
    }
}
