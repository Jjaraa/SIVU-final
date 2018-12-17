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
 *
 * @author Oscar
 * Esta clase gestiona la ventana de cambiar contraseña
 */
public class MenuCambiarContraseña extends Ventana {

    private JLabel textoUsuario, textoContraseña, textoNuevaContraseña, textoEncabezado;
    private JPasswordField contraseñAntigua, nuevaContraseña;
    private JTextField nombreUsuario;
    private JButton botonAceptar, botonCancelar;
    private final SIVU sivu;
    private String nombreAdministrador;

     /**
     * El constructor inicializa la ventana del menú cambiar contraseña y recibe el objeto SIVU y el nombre del administrador.
     *
     * @param sivu
     * @param nombreAdministrador
     */
    public MenuCambiarContraseña(SIVU sivu, String nombreAdministrador) {
        super("SIVU", 500, 400);
        this.sivu = sivu;
        this.nombreAdministrador = nombreAdministrador;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarTextoVentana();
        generarCamposTexto();
        generarBotones();
        repaint();
    }

    private void generarTextoVentana() {
        super.generarJLabel(this.textoUsuario, "Nombre de usuario:", 20, 100, 150, 20);
        super.generarJLabel(this.textoContraseña, "Contraseña antigua:", 20, 150, 150, 20);
        super.generarJLabel(this.textoNuevaContraseña, "Nueva contraseña:", 20, 200, 150, 20);
        super.generarJLabelEncabezado(this.textoEncabezado, "Cambiar contraseña:", 20, 20, 250, 20);
    }

    private void generarCamposTexto() {
        this.nombreUsuario = super.generarJTextField(200, 100, 150, 20);
        this.add(this.nombreUsuario);
        this.contraseñAntigua = super.generarJPasswordField(200, 150, 150, 20);
        this.add(this.contraseñAntigua);
        this.nuevaContraseña = super.generarJPasswordField(200, 200, 150, 20);
        this.add(this.nuevaContraseña);
    }

    private void generarBotones() {
        this.botonAceptar = super.generarBoton("Aceptar", 300, 300, 150, 20);
        this.add(this.botonAceptar);
        this.botonAceptar.addActionListener(this);
        this.botonCancelar = super.generarBoton("Cancelar", 50, 300, 150, 20);
        this.add(this.botonCancelar);
        this.botonCancelar.addActionListener(this);
    }

    private void cambiarContraseñaAdmin(String contraseñaAntigua, String contraseñaNueva) {

        Admin admin = this.sivu.getAdministrador();

        boolean condicion = admin.cambiarContraseña(nombreAdministrador, contraseñaAntigua, contraseñaNueva);
        if (condicion == true) {
            JOptionPane.showMessageDialog(this, "La contraseña ha sido cambiada correctamente");
            MenuAdministrador menu = new MenuAdministrador(this.sivu, this.nombreAdministrador);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Datos erroneos");
        }
    }

    private void cambiarContraseñaVendedor(String contraseñaAntigua, String contraseñaNueva) {
        String nombreUsuario = this.nombreUsuario.getText();
        ArrayList<Vendedor> vendedores = this.sivu.getVendedores();
        int contador = 1;
        for (int i = 0; i < vendedores.size(); i++) {
            if (vendedores.get(i).getNombre().equals(nombreUsuario)) {
                boolean condicion = vendedores.get(i).cambiarContraseña(nombreUsuario, contraseñaAntigua, contraseñaNueva);
                if (condicion == true) {
                    JOptionPane.showMessageDialog(this, "La contraseña ha sido cambiada correctamente");
                    MenuAdministrador menu = new MenuAdministrador(this.sivu, this.nombreAdministrador);
                    this.dispose();

                } else {
                    contador++;
                }

            }
        }
        if (contador == vendedores.size()) {
            JOptionPane.showMessageDialog(this, "Datos erróneos");
        }

    }

    private void cambiarContraseña() {
        if (this.contraseñAntigua.getText().length() == 0 || this.nuevaContraseña.getText().length() == 0 || this.nombreUsuario.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Recuerde completar los campos");
        } else {
            String contraseñaAntigua = this.contraseñAntigua.getText();
            String contraseñaNueva = this.nuevaContraseña.getText();
            if (this.nombreUsuario.equals(nombreAdministrador)) {
                cambiarContraseñaAdmin(contraseñaAntigua, contraseñaNueva);
            } else {
                cambiarContraseñaVendedor(contraseñaAntigua, contraseñaNueva);
            }
        }

    }
/**
     * Este método se encarga de gestionar los eventos de la ventana cambiar contraseña
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.botonAceptar) {
            cambiarContraseña();
        }
        if (e.getSource() == this.botonCancelar) {
            MenuAdministrador menu = new MenuAdministrador(this.sivu, this.nombreAdministrador);
        }
    }
}
