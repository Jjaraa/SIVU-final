/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfazGrafica;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
/**
 * 
 * @author Oscar
 * Esta clase se encarga de gestionar los datos mediante una tabla
 */
public class MenuTabla extends JFrame{
    private String[][] datos;
    private String[] nombreColumnas;
          /**
     * El constructor inicializa la tabla con los datos entregados por parámetro
     *
     * @param datos
     * @param nombreColumnas
     */
    public MenuTabla(String[][] datos, String[] nombreColumnas){
    super("SIVU");
     this.datos=datos;
     this.nombreColumnas= nombreColumnas;
     
    generarTabla();
   
    super.setLocationRelativeTo(null);
        super.setResizable(false);
        this.pack();
        this.setVisible(true);
    
    }
   public boolean isCellEditable (int row, int column)
   {
       return false;
   }
   public void generarTabla() {
        String[] nombreColumnas = {"N°", "Nombre", "Posición", "Salud"};
        DefaultTableModel dtm = new DefaultTableModel(this.datos, this.nombreColumnas){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        
        };
        final JTable tabla = new JTable(dtm);

        tabla.setPreferredScrollableViewportSize(new Dimension(500, 200));
        JScrollPane scrollPane = new JScrollPane(tabla);
      
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
               cerrarVentana();
            }
        });
    }
   private void cerrarVentana(){
   this.dispose();}
}
