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
 * Esta clase se encarga de gestionar la venta del vendedor 
 */
public class MenuVenta extends Ventana {

    private JTextField nombreProducto, codigoProducto;
    private JFormattedTextField cantidadProductos;
    private JButton botonVender, botonCancelar, botonBuscarProductos;
    private JLabel textoEncabezado, textoNombreProducto, textoCodigo, textoCantidad;
    private final SIVU sivu;
    private String nombreVendedor;
    /**
     * El constructor inicializa la ventana del menú venta y recibe el objeto SIVU y el nombre del vendedor.
     *
     * @param sivu
     * @param nombreVendedor
     */
    public MenuVenta(SIVU sivu, String nombreVendedor) {
        super("SIVU", 500, 400);
        this.sivu = sivu;
        this.nombreVendedor = nombreVendedor;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarBotones();
        generarCamposTexto();
        generarLabels();

        repaint();

    }

    private void generarBotones() {
        this.botonVender = super.generarBoton("Vender", 300, 320, 150, 20);
        this.add(this.botonVender);
        this.botonVender.addActionListener(this);
        this.botonCancelar = super.generarBoton("Cancelar", 50, 320, 150, 20);
        this.add(this.botonCancelar);
        this.botonCancelar.addActionListener(this);
        this.botonBuscarProductos = super.generarBoton("Buscar productos", 100, 100, 200, 20);
        this.add(this.botonBuscarProductos);
        this.botonBuscarProductos.addActionListener(this);

    }

    private void generarCamposTexto() {
        InternationalFormatter formato = super.generarFormato(1);
        this.cantidadProductos = super.generarJFormattedTextField(formato, 250, 230, 150, 20);
        this.add(this.cantidadProductos);
        this.codigoProducto = super.generarJTextField(250, 180, 150, 20);
        this.add(codigoProducto);
    }

    private void generarLabels() {
        super.generarJLabelEncabezado(this.textoEncabezado, "Venta", 20, 20, 250, 20);
        super.generarJLabel(this.textoCodigo, "Codigo producto:", 20, 180, 200, 20);
        super.generarJLabel(this.textoCantidad, "Cantidad:", 20, 230, 200, 20);
    }

    /**
     * Este método gestiona los eventos de la ventana
     */
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

    private void generarVenta() {
        if (this.cantidadProductos.getText().length() == 0 || this.codigoProducto.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Ingrese todos los datos");
        } else {
            try {
                String codigoProducto = this.codigoProducto.getText();
                int cantidadProductos = Integer.parseInt(this.cantidadProductos.getText());
                Vendedor vendedor = sivu.buscarVendedor(this.nombreVendedor);
                ArrayList<Producto> productos = sivu.getProductos();
                boolean condicionVenta = vendedor.vender(codigoProducto, cantidadProductos, productos);
                if (condicionVenta == true) {
                    JOptionPane.showMessageDialog(this, "Venta realizada exitosamente");
                    MenuVendedor menu = new MenuVendedor(this.sivu, this.nombreVendedor);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Intentelo nuevamente, hubo un error");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Datos erroneos");
            }
        }
    }
    /**
     * Este método gestiona los eventos de la ventana
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.botonBuscarProductos) {
            generarMostrarInventario();

        }
        if (e.getSource() == this.botonVender) {
            generarVenta();

        }
        if (e.getSource() == this.botonCancelar) {
            MenuVendedor menu = new MenuVendedor(this.sivu, this.nombreVendedor);
            this.dispose();
        }
    }
}
