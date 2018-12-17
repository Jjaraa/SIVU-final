
package gestor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import logico.Admin;
import logico.Vendedor;


public class UserFile {
   
    /**
     * El constructor no instancia nada, ya que la clase no posee atributos.
     */
    public UserFile(){
    
    }
    
    
    /**
     * Método para crear el archivo del administrador, con usuario y contraseña por defecto.
     */
    public void crearAdmin(){
        try{
            File archivo = new File("datos/Users/admin.txt");
            if(archivo.exists()==false){
                FileWriter fw = new FileWriter(archivo); 
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("admin;root");
                bw.close();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"   Error al crear el archivo");
        }
    }
    
    /**
     * Método para crear el archivo de vendedores, se creará un archivo vacío.
     */
    public void crearVendedor(){
        try{
            File archivo = new File("datos/Users/vendedor.txt");
            if(archivo.exists()==false){
                FileWriter fw = new FileWriter(archivo); 
                BufferedWriter bw = new BufferedWriter(fw);                
                bw.close();
            }else{
                JOptionPane.showMessageDialog(null, "El fichero ya existe");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"   Error al crear el archivo");
        }
    }
    
    /**
     * Método para agregar los vendedores al archivo de vendedores, se necesitara un usuario, una contraseña y el nombre real del vendedor.
     * @param usuario
     * @param contraseña
     * @param nombre 
     */
    public void agregarVendedor(String usuario, String contraseña, String nombre){
        try{
            File archivo = new File("datos/Users/vendedor.txt");
            if(archivo.exists()){
                FileWriter fw = new FileWriter(archivo,true); 
                BufferedWriter bw = new BufferedWriter(fw);                
                bw.newLine();
                bw.write(usuario+";"+contraseña+";"+nombre);
                bw.close();
            }else{
                JOptionPane.showMessageDialog(null, "El fichero no existe");               
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"   Error al guardar los datos");
        }
    }
    
    /**
     * Método para guardar en un archivo llamado vendedor.txt el ArrayList de vendedores en el directorio Users.
     * @param vendedores 
     */
    public void guardarVendedores(ArrayList<Vendedor> vendedores){
        ArrayList<String[]> valoresVendedores = transformarInversaVendedor(vendedores);
         try{
            File archivo = new File("datos/Users/vendedor.txt");
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw);
            for(String[] item:valoresVendedores){
                bw.write(item[0]+";"+item[1]+";"+item[2]);
                bw.newLine();
            }
            bw.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"   Error al guardar los datos");
        }
    }
    
    /**
     * Método para guardar en un archivo llamado admin.txt el objeto de tipo Admin en el directorio Users.
     * @param administrador 
     */
    public void guardarAdmin(Admin administrador){
        String[] valoresAdmin = transformarInversaVendedor(administrador);
        try{
            File archivo = new File("datos/Users/admin.txt");
            if(archivo.exists()==false){
                FileWriter fw = new FileWriter(archivo); 
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(valoresAdmin[0]+";"+valoresAdmin[1]);
                bw.close();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"   Error al crear el archivo");
        }
    }
    
    /**
     * Método para leer el archivo admin.txt en el directorio Users.
     * @return Admin
     */
    public Admin leerAdmin(){
        String cadena=null;
        Admin admin = null;
        try{            
            FileReader f = new FileReader("datos/Users/admin.txt");
            BufferedReader br = new BufferedReader(f);
            cadena = br.readLine();
            admin = transformarAdmin(cadena);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"   Error al leer los datos leerAdmin");
        }
        return admin;
        
    }
    
    /**
     * Método para leer el archivo vendedor.txt en el directorio Users.
     * @return ArrayList<Vendedor>
     */
    public ArrayList<Vendedor> leerVendedor(){
        ArrayList<String> cadena = new ArrayList<>();
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        String linea = null;
        try{            
            FileReader f = new FileReader("datos/Users/vendedor.txt");
            BufferedReader br = new BufferedReader(f);
            while((linea = br.readLine())!=null){
                cadena.add(linea);
            }
            vendedores = transformarVendedor(cadena);            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"   Error al leer los datos leerVendedor");
        }
        return vendedores;
        
    }
    
    private Admin transformarAdmin(String cadena){
        String[] cadenas = cadena.split(";");
        Admin admin = new Admin(cadenas[0],cadenas[1]);
        return admin;
    }
    
    
    private ArrayList<Vendedor> transformarVendedor(ArrayList<String> cadenas){
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        try{
            
        String[] cadena = null;
        for(String item:cadenas){
            cadena = item.split(";");          
            Vendedor vendedor = new Vendedor(cadena[0],cadena[1],cadena[2]);
            vendedores.add(vendedor);
        }}catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"Problema con el array");
        }
        return vendedores;
    } 
    
    private ArrayList<String[]> transformarInversaVendedor(ArrayList<Vendedor> vendedores){
        ArrayList<String[]> valoresVendedores = new ArrayList<>();
        for(Vendedor item: vendedores){
            String[] array = new String[3];
            array[0] = item.getNombreUsuario();
            array[1] = item.getContraseña();
            array[2] = item.getNombre();
            valoresVendedores.add(array);
        }
        return valoresVendedores;
    }
    
    private String[] transformarInversaVendedor(Admin administrador){
        String[] valoresAdmin = new String[2];
        valoresAdmin[0] = administrador.getNombreUsuario();
        valoresAdmin[1] = administrador.getContraseña();
        return valoresAdmin;
    }
    
    
}
