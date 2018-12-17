/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import logico.SIVU;
import logico.Vendedor;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mauricio
 */
public class SIVUTest {
    
    /**
     * Test of buscarVendedor method, of class SIVU.
     */
    @Test
    public void testBuscarVendedor() {
        Vendedor vendedor = new Vendedor("userjonathan","jonathan02","nombre1");
        SIVU sivu = new SIVU();
        sivu.cargarArchivos();
        Vendedor vn = sivu.buscarVendedor("userjonathan");
        assertEquals(vendedor.getNombre(), vn.getNombre());
    }

}
