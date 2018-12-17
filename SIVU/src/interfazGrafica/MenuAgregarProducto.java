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
 * Esta clase se encarga de la gestionar la ventana de agregar productos
 */
public class MenuAgregarProducto extends Ventana {

    private JTextField nombreProducto;
    private JFormattedTextField precioProducto;
    private JComboBox categorias, subcategoria;
    private JButton botonAceptar, botonCancelar, botonAñadirCategoria, botonAñadirSubcategoria;
    private JLabel textoEncabezado, textoNombreProducto, textoPrecio;
    private final SIVU sivu;
    private String nombre;
          /**
     * El constructor inicializa la ventana del menú Agregar producto y recibe el objeto SIVU y el nombre del administrador.
     *
     * @param sivu
     * @param nombre
     */
    public MenuAgregarProducto(SIVU sivu, String nombre) {
        super("SIVU", 500, 400);
        this.sivu = sivu;
        this.nombre=nombre;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarCategorias();
        generarBotones();
        generarCamposTexto();
        generarLabels();
        repaint();
    }

    private void generarCategorias() {
        this.categorias = new JComboBox();
        ArrayList<Categoria> categoria = this.sivu.getCategorias();
        this.categorias.addItem("Elije una categoría");
        for (int i = 0; i < categoria.size(); i++) {
            this.categorias.addItem(categoria.get(i).getNombre());
        }
        this.categorias.setBounds(20, 60, 120, 20);
        this.add(this.categorias);
    }

    private void generarSubCategorias(String categoriaElegida) {
        
        ArrayList<Categoria> categoria = this.sivu.getCategorias();
        this.subcategoria = new JComboBox();
        this.subcategoria.addItem("Elije una subcategoría");
        for (int i = 0; i < categoria.size(); i++) {
            if (categoria.get(i).getNombre().equals(categoriaElegida)) {
                ArrayList<SubCategoria> subcategoria = categoria.get(i).getSubCategorias();
                for (int j = 0; j < subcategoria.size(); j++) {
                    this.subcategoria.addItem(subcategoria.get(i).getNombre());
                    this.add(this.categorias);
                }
            }
        }
        this.subcategoria.setBounds(150, 60, 120, 20);
        this.add(this.subcategoria);
    }

    private void generarBotones() {
        this.botonAceptar = super.generarBoton("Aceptar", 300, 300, 150, 20);
        this.add(this.botonAceptar);
        this.botonAceptar.addActionListener(this);
        this.botonAñadirCategoria = super.generarBoton("Agregar categoria", 30, 100, 200, 20);
        this.add(this.botonAñadirCategoria);
        this.botonAñadirCategoria.addActionListener(this);
        this.botonAñadirSubcategoria = super.generarBoton("Agregar subcategoria", 255, 100, 200, 20);
        this.add(this.botonAñadirSubcategoria);
        this.botonAñadirSubcategoria.addActionListener(this);
        this.botonCancelar = super.generarBoton("Cancelar", 50, 300, 150, 20);
        this.add(this.botonCancelar);
        this.botonCancelar.addActionListener(this);
    }

    private void generarCamposTexto() {
        this.nombreProducto = super.generarJTextField(200, 150, 150, 20);
        this.add(this.nombreProducto);
        InternationalFormatter formato = super.generarFormato(1);
        this.precioProducto = super.generarJFormattedTextField(formato, 200, 200, 150, 20);
        this.add(this.precioProducto);
    }

    private void generarLabels() {
        super.generarJLabelEncabezado(this.textoEncabezado, "Agregar producto", 20, 10, 250, 40);
        super.generarJLabel(this.textoNombreProducto, "Nombre producto:", 20, 150, 150, 20);
        super.generarJLabel(this.textoPrecio, "Precio unitario:", 20, 200, 150, 20);
    }
    private void generarSubcategorias(){
   
            int indice = this.categorias.getSelectedIndex();
            if (indice != 0) {
                Object categorias = this.categorias.getItemAt(indice);
                String categoria = categorias.toString();
                generarSubCategorias(categoria);

            }
    
    }
    private void añadirCategoria(){
    String nombreCategoria;

            nombreCategoria = JOptionPane.showInputDialog("Ingrese el nombre de su nueva Categoria");
            if (nombreCategoria == null || nombreCategoria.equals("")) {
            } else {
                this.sivu.agregarCategoria(nombreCategoria);
            }
    
    }
    private void añadirSubCategoria(){
     int indice = this.categorias.getSelectedIndex();
            if (indice != 0) {
             String nombrecategoria= this.categorias.getItemAt(indice).toString();
            String nombreSubcategoria;

            nombreSubcategoria = JOptionPane.showInputDialog("Ingrese el nombre de su nueva subcategoria");
            if (nombreSubcategoria == null || nombreSubcategoria.equals("")) {
                
            } else {
             this.sivu.agregarSubCategoria(this.sivu.buscarCategoria(nombrecategoria), nombreSubcategoria);
            }
        }
            
            }
    private void agregarProducto(){
        int indiceCategoria= this.categorias.getSelectedIndex();
        int indiceSubcategoria= this.subcategoria.getSelectedIndex();
    if(this.nombreProducto.getText().length()==0 || this.precioProducto.getText().length()==0 || indiceCategoria==0 || indiceSubcategoria==0 ){
    
    }
    else{
    String nombreCategoria= this.categorias.getSelectedItem().toString();
    String nombreSubcategoria= this.subcategoria.getSelectedItem().toString();
    int precioProducto= Integer.parseInt(this.precioProducto.getText());
    String nombreProducto= this.nombreProducto.getText();
    boolean condicion= this.sivu.añadirNuevoProducto(nombreProducto, nombreCategoria, nombreSubcategoria, precioProducto);
    if(condicion==true){
         JOptionPane.showMessageDialog(this,"Se ha añadido correctamente el producto");
         MenuAdministrador menu= new MenuAdministrador(this.sivu,this.nombre);
         this.dispose();
    }
    else{
     JOptionPane.showMessageDialog(this,"Hubo un error, intentelo nuevamente");
    }
    
    }
    
    }

       /**
     * Este método gestiona los eventos de la ventana agregar producto
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        generarSubcategorias();
        if (e.getSource() == this.botonAñadirCategoria) {
            añadirCategoria();
        }
       if(e.getSource()==this.botonAñadirSubcategoria){
       añadirSubCategoria();
       }
            if(e.getSource()==this.botonCancelar){
            MenuAdministrador menu= new MenuAdministrador(this.sivu,this.nombre);
            this.dispose();
            }
          if(e.getSource()==this.botonAceptar){
          
          }
        }

    }

