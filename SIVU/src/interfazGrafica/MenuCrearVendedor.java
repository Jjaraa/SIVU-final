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
import javax.swing.text.InternationalFormatter;

/**
 *
 * @author Oscar
 * Esta clase se encarga de gestionar la ventana de la creación de vendedores
 */
public class MenuCrearVendedor extends Ventana {

    private JTextField nombreUsuario;
    private JPasswordField contraseña, confirmarContraseña;
    private JButton botonAceptar, botonCancelar;
    private JLabel textoEncabezado, textoNombreUsuario, textoContraseña, textoConfirmarContraseña;
    private final SIVU sivu;
    private String nombreAdministrador;
   /**
     * El constructor inicializa la ventana del menú crear vendedor y recibe el objeto SIVU y el nombre del administrador.
     *
     * @param sivu
     * @param nombreAdministrador
     */
    public MenuCrearVendedor(SIVU sivu, String nombreAdministrador) {
        super("SIVU", 500, 400);
        this.sivu = sivu;
        this.nombreAdministrador = nombreAdministrador;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarBotones();
        generarCamposTexto();
        generarLabels();
        repaint();
    }

    private void generarBotones() {
        this.botonAceptar = super.generarBoton("Aceptar", 300, 300, 150, 20);
        this.add(this.botonAceptar);
        this.botonAceptar.addActionListener(this);

        this.botonCancelar = super.generarBoton("Cancelar", 50, 300, 150, 20);
        this.add(this.botonCancelar);
        this.botonCancelar.addActionListener(this);
    }

    private void generarCamposTexto() {
        this.nombreUsuario = super.generarJTextField(250, 100, 190, 20);
        this.add(this.nombreUsuario);
        this.contraseña = super.generarJPasswordField(250, 170, 190, 20);
        this.add(this.contraseña);
        this.confirmarContraseña = super.generarJPasswordField(250, 240, 190, 20);
        this.add(this.confirmarContraseña);
    }

    private void generarLabels() {
        super.generarJLabelEncabezado(this.textoEncabezado, "Crear Vendedor", 20, 20, 250, 40);
        super.generarJLabel(this.textoNombreUsuario, "Nombre de Usuario:", 20, 100, 200, 20);
        super.generarJLabel(this.textoContraseña, "Contraseña:", 20, 170, 200, 20);
        super.generarJLabel(this.textoConfirmarContraseña, "Confirmar contraseña:", 20, 240, 200, 20);
    }

    private void crearVendedor() {

        if (this.nombreUsuario.getText().length() == 0 || this.contraseña.getText().length() == 0 || this.confirmarContraseña.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Ingrese todos los datos");
        } else {
            if (this.contraseña.getText().equals(this.confirmarContraseña.getText())) {
                Admin admin = this.sivu.getAdministrador();
                ArrayList<Vendedor> vendedores = sivu.getVendedores();
                String nombreVendedor = this.nombreUsuario.getText();
                String contraseña = this.contraseña.getText();
                admin.crearUsuario(nombreVendedor, contraseña, nombreVendedor, vendedores);
                JOptionPane.showMessageDialog(this, "Vendedor registrado");
                 MenuAdministrador menu= new MenuAdministrador(this.sivu,this.nombreAdministrador);
                 this.dispose();
                 
            } else {
                JOptionPane.showMessageDialog(this, "Contraseñas no coinciden");

            }
        }
    }

       /**
     * Este método gestiona los eventos de la ventana crear vendedor
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.botonAceptar) {
            crearVendedor();
        }
        if (e.getSource() == this.botonCancelar) {
            MenuAdministrador menu = new MenuAdministrador(this.sivu, this.nombreAdministrador);
            this.dispose();
        }

    }
}
