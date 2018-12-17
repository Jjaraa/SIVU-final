/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazGrafica;
import logico.*;
import sIstemaLogin.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Oscar
 * Esta clase se encarga de gestionar la ventana de login
 */
public class MenuLogin extends Ventana {

    private JPasswordField contraseña;
    private JTextField nombreUsuario;
    private JLabel textoContraseña, textoEncabezado, textoUsuario;
    private JButton botonAceptar, botonCancelar;
   private SIVU sivu;
      /**
     * El constructor inicializa la ventana del login y crea al objeto SIVU
     *
     */
    public MenuLogin() {
        super("SIVU", 500, 400);
        this.sivu= new SIVU();
        this.sivu.cargarArchivos();
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarCamposTexto();
        generarBotones();
        generarTexto();
        repaint();

    }

    private void generarCamposTexto() {
        this.nombreUsuario = super.generarJTextField(200, 120, 150, 20);
        this.add(this.nombreUsuario);
        this.contraseña = super.generarJPasswordField(200, 200, 150, 20);
        this.add(this.contraseña);
    }

    private void generarBotones() {
        this.botonAceptar = super.generarBoton("Aceptar", 300, 300, 150, 20);
        this.add(this.botonAceptar);
        this.botonAceptar.addActionListener(this);
        this.botonCancelar = super.generarBoton("Cancelar", 50, 300, 150, 20);
        this.add(this.botonCancelar);
        this.botonCancelar.addActionListener(this);
    }

    private void generarTexto() {
        super.generarJLabel(this.textoContraseña, "Contraseña:", 20, 200, 150, 20);
        super.generarJLabelEncabezado(this.textoEncabezado, "Inicio de sesión", 150, 20, 250, 20);
        super.generarJLabel(this.textoUsuario, "Usuario:", 20, 120, 150, 20);
    }

    private void verificarDatosVendedor() {
       String nombreVendedor= this.nombreUsuario.getText();
       String contraseña= this.contraseña.getText();
       Login login= new Login();
        boolean condicion = login.verificarLoginVendedor(nombreVendedor, contraseña);
        if (condicion == true) {
            JOptionPane.showMessageDialog(this, "Ha iniciado correctamente");
            MenuVendedorIniciarDia menu = new MenuVendedorIniciarDia(this.sivu,nombreVendedor);
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Datos erróneos, intentelo nuevamente");
        }

    }

    private void verificarDatosAdministrador() {
        String nombreUsuario= this.nombreUsuario.getText();
        String contraseña= this.contraseña.getText();
        Login login= new Login();
        boolean condicion =  login.verificarLoginAdmin(nombreUsuario, contraseña);
      
        if (condicion == true) {
             JOptionPane.showMessageDialog(this, "Ha iniciado correctamente");
            MenuAdministrador menu = new MenuAdministrador(this.sivu,nombreUsuario);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Datos erróneos, intentelo nuevamente");
        }

    }
/**
 * Este método gestiona los eventos de la ventana login
 * @param e
 */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.botonAceptar) {
            if (this.nombreUsuario.getText().length() == 0 || this.contraseña.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Ingrese todos los datos");
            } else {
                if (this.nombreUsuario.getText().equals("admin")) {
                    verificarDatosAdministrador();
                }
                else{
                verificarDatosVendedor();
                }
            }

        }
        if (e.getSource() == this.botonCancelar) {
            Bienvenida bienvenida = new Bienvenida();
        }
    }
}
