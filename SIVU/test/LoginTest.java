/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.junit.Test;
import static org.junit.Assert.*;
import sIstemaLogin.Login;

/**
 *
 * @author Mauricio
 */
public class LoginTest {

    /**
     * Test of comprobarExistenciaVendedor method, of class Login.
     */
    @Test
    public void testComprobarExistenciaVendedor() throws Exception {
        Login lg = new Login();
        boolean estado = lg.comprobarExistenciaVendedor("userfibonacci");
        assertEquals(estado, true);
    }

}
