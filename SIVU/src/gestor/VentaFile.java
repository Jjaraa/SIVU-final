
package gestor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import logico.Vendedor;
import logico.Venta;


public class VentaFile {
    
    /**
     * El constructor no instancia nada, ya que la clase no posee atributos.
     */
    public VentaFile(){
     
    }
    
    /**
     * Método para guardar en un archivo llamado totales.txt el ArrayList de ventas en el directorio Totales.
     * @param ventas 
     */
    public void grabarVentasTotales(ArrayList<Venta> ventas){
        ArrayList<String[]> arrayVentas = transformarVentas(ventas);              
        try{
            File archivo = new File("datos/Ventas/Totales/totales.txt");
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw);
            for(String[] item:arrayVentas){
                bw.write(item[0]+";"+item[1]+";"+item[2]+";"+item[3]);
                bw.newLine();
            }
            bw.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"   Error al guardar los datos");
        }
    }
    
    /**
     * Método para guardar en un archivo llamado con el nombre de cada vendedor, los datos de ventas de cada uno.
     * @param vendedor
     * @param ventasTotales 
     */
    public void grabarVentaVendedor(Vendedor vendedor, ArrayList<Venta> ventasTotales){        
        ArrayList<Venta> ventasVendedor = generarVentasVendedor(vendedor, ventasTotales);
        ArrayList<String[]> arrayVentas = transformarVentas(ventasVendedor);
        try{
            File nombreVendedor = new File("datos/Ventas/"+vendedor.getNombre()+".txt");
            FileWriter fw = new FileWriter(nombreVendedor);
            BufferedWriter bw = new BufferedWriter(fw);
            for(String[] item:arrayVentas){
                bw.write(item[0]+";"+item[1]+";"+item[2]+";"+item[3]);
                bw.newLine();
            }
            bw.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"   Error al guardar los datos");
        }
    }
    
    /**
     * Método para leer el archivo totales.txt en el directorio Totales.
     * @return ArrayList<Venta>
     */
    public ArrayList<Venta> leerVentasTotales(){
        ArrayList<String> cadena = new ArrayList<>();
        ArrayList<Venta> ventas = new ArrayList<>();
        String linea = null;
        try{            
            FileReader f = new FileReader("datos/Ventas/Totales/totales.txt");
            BufferedReader br = new BufferedReader(f);
            while((linea = br.readLine())!=null){
                cadena.add(linea);
            }
            ventas = transformarInversaVenta(cadena);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"   Error al leer los datos ventastotales");
        }
        return ventas;
    }
    
    /**
     * Método para leer el archivo del vendedor específico en el directorio Ventas.
     * @param 
     * @return ArrayList<Venta>
     */
    public ArrayList<Venta> leerVentaVendedor(Vendedor vendedor){
        ArrayList<String> cadena = new ArrayList<>();
        ArrayList<Venta> ventas = new ArrayList<>();
        String linea = null;
        try{                                    
            File archivo = new File("datos/Ventas/"+vendedor.getNombre()+".txt");
            if(archivo.exists()){
                FileReader f = new FileReader("datos/Ventas/"+vendedor.getNombre()+".txt");            
                BufferedReader br = new BufferedReader(f);
                while((linea = br.readLine())!=null){
                    cadena.add(linea);
                }
                ventas = transformarInversaVenta(cadena);
            }else{
                JOptionPane.showMessageDialog(null, "El archivo no existe. Puede que el vendedor no tenga ventas registradas");
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"   Error al leer los datos ventavendedor");
        }
        return ventas;
    }
    
    
    private ArrayList<Venta> transformarInversaVenta(ArrayList<String> cadena){
        ArrayList<Venta> ventas = new ArrayList<>();
        String[] cadenas = new String[4];
        for(String item:cadena){
            cadenas = item.split(";");
            Venta venta = new Venta(Integer.parseInt(cadenas[2]),(cadenas[0]),Integer.parseInt(cadenas[1]),cadenas[3]);
            ventas.add(venta);
        }
        return ventas;
    }
    
    
    /*
    venta[0] representa el codigo del producto vendido            
    venta[1] representa la cantidad vendida
    venta[2] representa el valor total vendido
    venta[3] representa el nombre del vendedor que realizó la venta
    */
    private ArrayList<String[]> transformarVentas(ArrayList<Venta> ventas){
        ArrayList<String[]> arrayVentas = new ArrayList<>();
        for(Venta item:ventas){
            String[] filaVentas = new String[4];
            filaVentas[0] = (item.getCodigo());
            filaVentas[1] = Integer.toString(item.getCantidad());
            filaVentas[2] = Integer.toString(item.getValor());
            filaVentas[3] = item.getVendedor();
            arrayVentas.add(filaVentas);
        }
        return arrayVentas;
    }
    
    
    private ArrayList<Venta> generarVentasVendedor(Vendedor vendedor, ArrayList<Venta> ventasTotales){
        ArrayList<Venta> venta = new ArrayList<>();
        for(Venta item: ventasTotales){
            if(item.getVendedor().equals(vendedor.getNombre())){
                venta.add(item);
            }
        }
        return venta;
    }    
}
