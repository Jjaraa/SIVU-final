
package gestor;

import java.io.File;

/**
 *
 * @author Master
 */
public class DirectoryFile {
    
    private String direccion;
    
    /**
     * Constructor para asignarle una direccion especifica para crear directorios.
     * @param 
     */
    public DirectoryFile(String direccion){
        this.direccion = direccion;
    }
    
    /**
     * Constructor sin parametros. Solo para crear los directorios del sistema.
     */
    public DirectoryFile(){
        this.direccion = null;
    }
    
    /**
     *  Método para crear un directorio. Exista o no. Retornara true si no existía y retornara false si ya existía.
     * @return boolean
     */
    public boolean crearDirectorio(){
        File directorio = new File(this.direccion);
        return directorio.mkdir();
    }
    
    /**
     * El metodo que crea los directorios en el primer inicio del programa
     */
   
    public void crearDirectoriosSIVU(){
        crearDirectorioDatos();
        crearDirectorioInventario();
        crearDirectorioUsers();
        crearDirectorioVentas();
        crearDirectorioTotales();
        
    }
    
    /**
     *  Método para cambiar el atributo de direccion para crear directorios.
     * @param 
     */
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    
    /**
     * Método para retornar la direccion para crear directorios.
     * @return
     */
    public String getDireccion(){
        return this.direccion;
    }
    
    
    private boolean crearDirectorioDatos(){
        File directorio = new File("datos");
        return directorio.mkdir(); 
    }
    
      private boolean crearDirectorioInventario(){
        File directorio = new File("datos/Inventario");
        return directorio.mkdir();
    }
    
    private boolean crearDirectorioUsers(){
        File directorio = new File("datos/Users");
        return directorio.mkdir();
    }
    
    private boolean crearDirectorioVentas(){
        File directorio = new File("datos/Ventas");
        return directorio.mkdir();
    }
    
    private boolean crearDirectorioTotales(){
        File directorio = new File("datos/Ventas/Totales");
        return directorio.mkdir();
    }
    
    
}
    