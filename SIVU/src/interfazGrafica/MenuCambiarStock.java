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
 * Esta clase se encarga de gestionar la ventana del menú de cambiar stock a un producto
 */
public class MenuCambiarStock extends Ventana {

    private JComboBox categorias;
    private JLabel textoStockMinimoProducto, textoCodigoProducto, textoStockProducto, textoEncabezado;
    private JTextField codigoProducto;
    private JFormattedTextField stockProducto, stockMinimoProducto;
    private JButton botonAceptar, botonCancelar, botonBuscarProducto;
    private final SIVU sivu;
    private String nombreAdministrador;
   /**
     * El constructor inicializa la ventana del menú cambiar stock a un producto y recibe el objeto SIVU y el nombre del administrador.
     *
     * @param sivu
     * @param nombreAdministrador
     */
    public MenuCambiarStock(SIVU sivu, String nombreAdministrador) {
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
        this.stockProducto = super.generarJFormattedTextField(formato, 250, 200, 150, 20);
        this.add(this.stockProducto);
        this.stockMinimoProducto = super.generarJFormattedTextField(formato, 250, 250, 150, 20);
        this.add(this.stockProducto);
        this.codigoProducto = super.generarJTextField(250, 150, 150, 20);
        this.add(this.codigoProducto);

    }

    private void generarLabels() {
        super.generarJLabelEncabezado(this.textoEncabezado, "Cambiar stock a producto", 20, 20, 300, 40);
        super.generarJLabel(this.textoStockProducto, "Nuevo stock producto:", 20, 200, 200, 20);
        super.generarJLabel(this.textoCodigoProducto, "Código producto:", 20, 150, 200, 20);
        super.generarJLabel(this.textoStockMinimoProducto, "Stock minimo producto:", 20, 250, 200, 20);
    }

    private void generarBotones() {
        this.botonAceptar = super.generarBoton("Aceptar", 280, 300, 150, 20);
        this.add(this.botonAceptar);
        this.botonAceptar.addActionListener(this);
        this.botonCancelar = super.generarBoton("Cancelar", 50, 300, 150, 20);
        this.add(this.botonCancelar);
        this.botonCancelar.addActionListener(this);
        this.botonBuscarProducto = super.generarBoton("Buscar producto", 150, 100, 200, 20);
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

    private void cambiarStock(ArrayList<Producto> productos, String codigoProducto, int stockProducto) {
        int contador = 1;
        for (int i = 0; i < productos.size(); i++) {
            if (codigoProducto.equals(productos.get(i).getCodigo())) {
                productos.get(i).setStock(stockProducto);
                JOptionPane.showMessageDialog(this, "Stock actual cambiado correctamente");
                MenuAdministrador menu = new MenuAdministrador(this.sivu, this.nombreAdministrador);
                this.dispose();
            }
            contador++;
        }
        if (contador == productos.size()) {
            JOptionPane.showMessageDialog(this, "Código erroneo, intentelo nuevamente");
        }
    }

    private void cambiarAmbosStock(ArrayList<Producto> productos, int stockProducto, String codigoProducto) {

        int stockMinimo = Integer.parseInt(this.stockMinimoProducto.getText());
        if (stockMinimo >= stockProducto) {
            JOptionPane.showMessageDialog(this, "Stock mínimo mayor o igual a stock");

            int contador = 1;
            for (int i = 0; i < productos.size(); i++) {
                if (codigoProducto.equals(productos.get(i).getCodigo())) {
                    productos.get(i).setStock(stockProducto);
                    productos.get(i).setStockCritico(stockMinimo);
                    JOptionPane.showMessageDialog(this, "Stock actual y stock mínimo cambiado correctamente");
                    MenuAdministrador menu = new MenuAdministrador(this.sivu, this.nombreAdministrador);
                    this.dispose();
                }
                contador++;
            }
            if (contador == productos.size()) {
                JOptionPane.showMessageDialog(this, "Código erroneo, intentelo nuevamente");
            }
        }

    }

    private void cambiarStocks() {
        if (this.codigoProducto.getText().length() == 0 || this.stockProducto.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Ingrese todos los datos");
        } else {
            ArrayList<Producto> productos = this.sivu.getInventarioTotal();
            String codigoProducto = this.codigoProducto.getText();
            int stockProducto = Integer.parseInt(this.stockProducto.getText());
            if (this.stockMinimoProducto.getText().length() != 0) {
                cambiarAmbosStock(productos, stockProducto, codigoProducto);
            } else {
                cambiarStock(productos, codigoProducto, stockProducto);
            }

        }
    }
   /**
     * Este método gestiona los eventos de la ventana Cambiar stock
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
            cambiarStocks();
        }
    }
}
