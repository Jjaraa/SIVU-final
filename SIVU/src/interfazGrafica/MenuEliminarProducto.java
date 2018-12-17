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
 * Esta clase se encarga de gestionar la ventana de eliminar productos
 */
public class MenuEliminarProducto extends Ventana {

    private JPasswordField contraseña;
    private JTextField codigoProducto;
    private JButton botonBorrarProducto, botonCancelar, botonBuscarProducto;
    private JLabel textoEncabezado, textoNombreProducto, textoCodigoProducto, textoContraseña;
    private final SIVU sivu;
    private String nombreUsuario;
        /**
     * El constructor inicializa la ventana del menú eliminar producto  y recibe el objeto SIVU y el nombre del administrador.
     *
     * @param sivu
     * @param nombreUsuario
     */
    public MenuEliminarProducto(SIVU sivu, String nombreUsuario) {
        super("SIVU", 500, 400);
        this.sivu = sivu;
        this.nombreUsuario = nombreUsuario;
        generarElementosVentana();

    }

    private void generarElementosVentana() {
        generarBotones();
        generarCamposTexto();
        generarLabels();
        repaint();
    }

    private void generarBotones() {
        this.botonBorrarProducto = super.generarBoton("Eliminar producto", 250, 320, 200, 20);
        this.add(this.botonBorrarProducto);
        this.botonBorrarProducto.addActionListener(this);
        this.botonBuscarProducto = super.generarBoton("Buscar producto", 100, 120, 250, 20);
        this.add(this.botonBuscarProducto);
        this.botonBuscarProducto.addActionListener(this);
        this.botonCancelar = super.generarBoton("Cancelar", 50, 320, 150, 20);
        this.add(this.botonCancelar);
        this.botonCancelar.addActionListener(this);

    }

    private void generarCamposTexto() {
        InternationalFormatter formato = super.generarFormato(1);
        this.contraseña = super.generarJPasswordField(250, 230, 150, 20);
        this.add(this.contraseña);
        this.codigoProducto = super.generarJTextField(250, 180, 150, 20);
        this.add(codigoProducto);
    }

    private void generarLabels() {
        super.generarJLabelEncabezado(this.textoEncabezado, "Eliminar producto", 20, 20, 250, 20);
        super.generarJLabel(this.textoCodigoProducto, "Codigo producto:", 20, 180, 200, 20);
        super.generarJLabel(this.textoContraseña, "Contraseña Administrador:", 20, 230, 200, 20);
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

    private void borrarProducto() {
        if (this.codigoProducto.getText().length() == 0 || this.contraseña.getText().length() == 0) {

        } else {
            try {
                int contador = 1;
                String codigoProducto = this.codigoProducto.getText();
                String contraseña = this.contraseña.getText();
                Admin admin = sivu.getAdministrador();
                if (admin.getContraseña().equals(contraseña)) {
                    ArrayList<Producto> productos = sivu.getInventarioTotal();
                    for (int i = 0; i < productos.size(); i++) {
                        if (productos.get(i).getCodigo().equals(codigoProducto)) {
                            boolean condicion = admin.eliminarProducto(productos.get(i), productos);
                            if (condicion == true) {
                                JOptionPane.showMessageDialog(this, "Producto borrado exitosamente");
                                MenuAdministrador menu = new MenuAdministrador(this.sivu, this.nombreUsuario);
                                this.dispose();
                            } else {
                                JOptionPane.showMessageDialog(this, "Hubo un error, intentelo nuevamente");
                            }
                        } else {
                            contador++;
                        }
                    }
                    if (contador == productos.size()) {
                        JOptionPane.showMessageDialog(this, "Producto no encontrado");

                    } else {
                        JOptionPane.showMessageDialog(this, "Contraseña incorrecta");
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Datos incorrectos");
            }
        }
    }
   /**
     * Este método se encarga de gestionar los eventos de la ventana eliminar producto
     *
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.botonBorrarProducto) {
         borrarProducto();
        }
        if (e.getSource() == this.botonBuscarProducto) {
            generarMostrarInventario();
        }
        if (e.getSource() == this.botonCancelar) {
            MenuAdministrador menu = new MenuAdministrador(this.sivu, this.nombreUsuario);
            this.dispose();
        }
    }
}
