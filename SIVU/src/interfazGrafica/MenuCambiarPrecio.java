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
import javax.swing.text.InternationalFormatter;
import logico.*;

/**
 * 
 * @author Oscar
 * Esta clase se encarga de gestionar la ventana de cambiar precio a un producto
 */
public class MenuCambiarPrecio extends Ventana {

    private JLabel textoCodigoProducto, textoPrecioProducto, textoEncabezado;
    private JTextField codigoProducto;
    private JFormattedTextField precioProducto;
    private JButton botonAceptar, botonCancelar, botonBuscarProducto;
    private final SIVU sivu;
    private String nombreAdministrador;
    /**
     * El constructor inicializa la ventana del menú cambiar precio a un producto  y recibe el objeto SIVU y el nombre del administrador.
     *
     * @param sivu
     * @param nombreAdministrador
     */
    public MenuCambiarPrecio(SIVU sivu, String nombreAdministrador) {
        super("SIVU", 500, 400);
        this.sivu = sivu;
        this.nombreAdministrador = nombreAdministrador;
        generarElementosVentana();

    }

    private void generarElementosVentana() {
        generarCamposTexto();
        generarLabels();
        generarBotones();
        repaint();
    }

    private void generarCamposTexto() {
        InternationalFormatter formato = super.generarFormato(1);
        this.precioProducto = super.generarJFormattedTextField(formato, 250, 250, 150, 20);
        this.add(this.precioProducto);
        this.codigoProducto = super.generarJTextField(250, 200, 150, 20);
        this.add(this.codigoProducto);

    }

    private void generarLabels() {

        super.generarJLabel(this.textoCodigoProducto, "Código producto:", 20, 200, 200, 20);
        super.generarJLabel(this.textoPrecioProducto, "Nuevo precio producto:", 20, 250, 200, 20);
    }

    private void generarBotones() {
        this.botonAceptar = super.generarBoton("Aceptar", 280, 300, 150, 20);
        this.add(this.botonAceptar);
        this.botonAceptar.addActionListener(this);
        this.botonCancelar = super.generarBoton("Cancelar", 50, 300, 150, 20);
        this.add(this.botonCancelar);
        this.botonCancelar.addActionListener(this);
        this.botonBuscarProducto = super.generarBoton("Buscar producto", 150, 150, 200, 20);
        this.add(this.botonBuscarProducto);
        this.botonBuscarProducto.addActionListener(this);
    }

    private void generarMostrarInventario() {
        ArrayList<Categoria> categorias = sivu.getCategorias();
        String[] nombreCategorias = new String[categorias.size()];
        for (int i = 0; i < categorias.size(); i++) {
            nombreCategorias[i] = categorias.get(i).getNombre();
        }
        Object opcion = JOptionPane.showInputDialog(null, "Selecciona una categoría a mostrar", "Elegir", JOptionPane.QUESTION_MESSAGE, null, nombreCategorias, nombreCategorias[0]);
        String opcionCategoria = opcion.toString();
        String[][] inventario = sivu.retornarCategoria(opcionCategoria);
        String[] nombresColumnas = {"Código", "Nombre", "Stock", "Stock crítico", "Precio unitario"};
        MenuTabla tabla = new MenuTabla(inventario, nombresColumnas);

    }

    private void cambiarPrecio() {
        ArrayList<Producto> productos = new ArrayList();
        if (this.codigoProducto.getText().length() == 0 || this.precioProducto.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Ingrese todos los datos");
        } else {
            try {
                productos = this.sivu.getInventarioTotal();
                String codigoProducto = this.codigoProducto.getText();
                int precioProducto = Integer.parseInt(this.precioProducto.getText());
                int contador = 1;
                for (int i = 0; i < productos.size(); i++) {
                    if (codigoProducto.equals(productos.get(i).getCodigo())) {
                        productos.get(i).setPrecio(precioProducto);
                        JOptionPane.showMessageDialog(this, "Precio cambiado correctamente");
                        MenuAdministrador menu = new MenuAdministrador(this.sivu, this.nombreAdministrador);
                        this.dispose();
                    }
                    contador++;
                }
                if (contador == productos.size()) {
                    JOptionPane.showMessageDialog(this, "Código erroneo, intentelo nuevamente");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Datos erróneos");
            }
        }

    }
  /**
     * Este método gestiona los eventos de la ventana cambiar precio
     *
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.botonBuscarProducto) {
            generarMostrarInventario();
        }
        if (e.getSource() == this.botonCancelar) {
            MenuAdministrador menu = new MenuAdministrador(this.sivu, this.nombreAdministrador);
            this.dispose();
        }
        if (e.getSource() == this.botonAceptar) {
            cambiarPrecio();

        }
    }
}
