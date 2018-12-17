/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gestor.InventarioFile;
import gestor.UserFile;
import java.util.ArrayList;
import logico.Admin;
import logico.Producto;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Master
 */
public class GestorTest {
    
    public GestorTest() {
    }
    
    @Test
    public void leerAdminTest(){
        UserFile archivo = new UserFile();
        Admin administradorEsperado = new Admin("admin","root");
        Admin administradorObtenido = archivo.leerAdmin();
        assertEquals(administradorEsperado.getNombreUsuario(), administradorObtenido.getNombreUsuario());
    }
    
    @Test
    public void leerInventarioTest(){
        InventarioFile archivo = new InventarioFile();
        ArrayList<Producto> productos = new ArrayList<>();
        String[] codigosInventario= new String[6];
        codigosInventario[0] = "0101000";
        codigosInventario[1] = "0101001";
        codigosInventario[2] = "0101002";
        codigosInventario[3] = "0101003";
        codigosInventario[4] = "0101004";
        codigosInventario[5] = "0101005";
        productos = archivo.leerInventario();
        int contador = 0;
        int contadorEquivalencia = 0;
        for(Producto item:productos){
            if(codigosInventario[contador].equals(item.getCodigo())){
                contadorEquivalencia++;
            }
            contador++;
        }
        assertEquals(6, contadorEquivalencia);
        
    } 
   
}
