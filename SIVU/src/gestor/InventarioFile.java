
package gestor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import logico.Producto;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import logico.Categoria;
import logico.SubCategoria;


public class InventarioFile {
    
    /**
     * El constructor no instancia nada, ya que no existen atributos para esta clase.
     */
    public InventarioFile(){
    
    }
    
    /**
     * Método para guardar en un archivo llamado inventario.txt el ArrayList de productos en el directorio del inventario.
     * @param 
     */    
    public void guardarInventario(ArrayList<Producto> inventario){
        ArrayList<String[]> productos = transformarInventario(inventario);
        try{
            File archivo = new File("datos/Inventario/inventario.txt");
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw);
            for(String[] item:productos){
                bw.write(item[0]+";"+item[1]+";"+item[2]+";"+item[3]+";"+item[4]);
                bw.newLine();
            }
            bw.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"   Error al guardar los datos");
        }
    }
    
    /**
     * Método para guardar en un archivo llamado categorias.txt el ArrayList de categorias en el directorio del inventario.
     * @param 
     */
    public void guardarCategorias(ArrayList<Categoria> categorias){
        ArrayList<String[]> stringsCategorias = transformarCategorias(categorias);
        try{
            File archivo = new File("datos/Inventario/categorias.txt");
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw);
            for(String[] item:stringsCategorias){
                bw.write(item[0]+";"+item[1]);
                bw.newLine();
            }
            bw.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"   Error al guardar los datos");
        }
    }
    
    /**
     * Método para guardar en un archivo llamado subcategorias.txt el ArrayList de todas las subcategorias en el directorio del inventario.
     * @param 
     */
    public void guardarSubCategorias(ArrayList<SubCategoria> subCategorias){
         ArrayList<String[]> stringsSubCategorias = transformarSubCategorias(subCategorias);
        try{
            File archivo = new File("datos/Inventario/subcategorias.txt");
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw);
            for(String[] item:stringsSubCategorias){
                bw.write(item[0]+";"+item[1]+";"+item[2]);
                bw.newLine();
            }
            bw.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"   Error al guardar los datos");
        }
    }
    
    /**
     * Método para leer el archivo inventario.txt en el directorio del inventario.
     * @return 
     */
    public ArrayList<Producto> leerInventario(){
        ArrayList<String> cadena = new ArrayList<>();
        ArrayList<Producto> inventario = new ArrayList<>();
        String linea = null;
        try{            
            FileReader f = new FileReader("datos/Inventario/inventario.txt");
            BufferedReader br = new BufferedReader(f);
            while((linea = br.readLine())!=null){
                cadena.add(linea);
            }
            inventario = transformarInversaInventario(cadena);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"   Error al leer los datos leerInventario");
        }
        return inventario;
    }
    
    /**
     * Método para leer el archivo categorias.txt en el directorio del inventario.
     * @return 
     */
    public ArrayList<String> leerCategorias(){
        ArrayList<String> cadena = new ArrayList<>();
        ArrayList<String> categorias = new ArrayList<>();
        String linea = null;
        try{            
            FileReader f = new FileReader("datos/Inventario/categorias.txt");
            BufferedReader br = new BufferedReader(f);
            while((linea = br.readLine())!=null){
                cadena.add(linea);
            }
            categorias = transformarInvesaCategorias(cadena);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"   Error al leer los datos leerCategorias");
        }
        return categorias;
    }
    
    /**
     * Método para leer el archivo subcategorias.txt en el directorio del inventario.
     * @return 
     */
    public ArrayList<String> leerSubCategorias(){
        ArrayList<String> cadena = new ArrayList<>();
        ArrayList<String> subCategorias = new ArrayList<>();
        String linea = null;
        try{            
            FileReader f = new FileReader("datos/Inventario/subcategorias.txt");
            BufferedReader br = new BufferedReader(f);
            while((linea = br.readLine())!=null){
                cadena.add(linea);
            }
            subCategorias = transformarInversaSubCategorias(cadena);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"   Error al leer los datos leerSubCategorias");
        }
        return subCategorias;
    }
    
    
    
    /*
    producto[0] representa el codigo del producto
    producto[1] representa el nombre del producto
    producto[2] representa el stock del producto
    producto[3] representa el stock crítico del producto
    producto[4] representa el precio de venta del producto
    */    
    private ArrayList<String[]> transformarInventario(ArrayList<Producto> inventario){
        ArrayList<String[]> productos = new ArrayList<>();
        for(Producto item: inventario){
                String[] producto = new String[5];
                producto[0] = (item.getCodigo());
                producto[1] = item.getNombre();
                producto[2] = Integer.toString(item.getStock());
                producto[3] = Integer.toString(item.getStockCritico());
                producto[4] = Integer.toString(item.getPrecio());
                productos.add(producto);
            }
        return productos;
    }
    
    
    private ArrayList<Producto> transformarInversaInventario(ArrayList<String> cadena){
        ArrayList<Producto> inventario = new ArrayList<>();
        String[] cadenas = new String[5];
        for(String item: cadena){
            cadenas = item.split(";");
            Producto producto = new Producto(cadenas[1],Integer.parseInt(cadenas[4]),Integer.parseInt(cadenas[2]),(cadenas[0]),Integer.parseInt(cadenas[3]));
            inventario.add(producto);
        }
        return inventario;
        
    }
    
    /*
    categoria1[0] representa el código de la categoria
    categoria1[1] representa el nombre de la categoria
    */
    private ArrayList<String[]> transformarCategorias(ArrayList<Categoria> categorias){
        ArrayList<String[]> stringsCategorias = new ArrayList<>();
        for(Categoria item:categorias){
            String[] categoria1 = new String[2];
            categoria1[0] = (item.getCodigo());
            categoria1[1] = item.getNombre();
            stringsCategorias.add(categoria1);
            
        }
        return stringsCategorias;
    }
    
    private ArrayList<String> transformarInvesaCategorias(ArrayList<String> cadena){
        ArrayList<String> categorias = new ArrayList<>();
        String[] cadenas = new String[2];
        for(String item: cadena){
            cadenas = item.split(";");
            categorias.add(cadenas[0]);
            categorias.add(cadenas[1]);
        }
        return categorias;
    }
    
    /*
    subCategoria1[0] representa el código de la subcategoria
    subCategoria1[1] representa el nombre de la subcategoria
    subCategoria1[2] representa el código de la categoria a la que pertecene
    */
    private ArrayList<String[]> transformarSubCategorias(ArrayList<SubCategoria> subCategorias){
        ArrayList<String[]> stringsSubCategorias = new ArrayList<>();
        for(SubCategoria item:subCategorias){
            String[] subCategoria1 = new String[3];
            subCategoria1[0] = (item.getCodigo());
            subCategoria1[1] = item.getNombre();
            subCategoria1[2] = (item.getCategoria().getCodigo());
            stringsSubCategorias.add(subCategoria1);
        }
        return stringsSubCategorias;
    }
    
    
    private ArrayList<String> transformarInversaSubCategorias(ArrayList<String> cadena){
        ArrayList<String> subCategorias = new ArrayList<>();
        String[] cadenas = new String[3];
        for(String item: cadena){
            cadenas = item.split(";");
            subCategorias.add(cadenas[0]);
            subCategorias.add(cadenas[1]);
            subCategorias.add(cadenas[2]);
        }
        return subCategorias;
    }
}
