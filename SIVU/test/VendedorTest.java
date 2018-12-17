/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import logico.Producto;
import logico.SIVU;
import logico.Vendedor;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mauricio
 */
public class VendedorTest {

    /**
     * Test of vender method, of class Vendedor.
     */
    @Test
    public void testVender() {
        Vendedor vn = new Vendedor("userjonathan","jonathan02","nombre1");
        SIVU sivu = new SIVU();
        sivu.cargarArchivos();
        ArrayList<Producto> productos = sivu.getInventarioTotal();
        boolean estado = vn.vender("0101002", 10, productos);
        assertEquals(estado, true);
    }
}
