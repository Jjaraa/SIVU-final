/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sIstemaLogin;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Mauricio
 */
public class Login {

    private static Scanner sc;
    private static Scanner x;

    /**
     * Método que se encarga de verificar si el login ingresado es el correcto.
     * Retornando un boolean true si es así y una false si es incorrecto.
     *
     * @param usuario
     * @param contraseña
     * @return boolean
     */
    public boolean verificarLoginAdmin(String usuario, String contraseña) {

        String direccionArchivo = "datos/Users/admin.txt";
        boolean found = false;
        String tempUsername = "";
        String tempPassword = "";

        try {
            sc = new Scanner(new File(direccionArchivo));
            sc.useDelimiter("[;\n]");

            while (sc.hasNext() && !found) {
                tempUsername = sc.next();
                tempPassword = sc.next();

                if (tempUsername.trim().equals(usuario.trim()) && tempPassword.trim().equals(contraseña.trim())) {
                    found = true;
                }
            }
            sc.close();
            return found;
        } catch (Exception e) {
            return found;
        }
    }

    /**
     * Método que se encarga de verificar si el login de usaurio ingresado es el
     * correcto. Retornando un boolean true si es así y una false si es
     * incorrecto.
     *
     * @param usuario
     * @param contraseña
     * @return boolean
     */
    public boolean verificarLoginVendedor(String usuario, String contraseña) {

        String direccionArchivo = "datos/Users/vendedor.txt";
        boolean found = false;
        String tempUsername = "";
        String tempPassword = "";

        try {
            sc = new Scanner(new File(direccionArchivo));
            sc.useDelimiter("[;\n]");

            while (sc.hasNext() && !found) {
                tempUsername = sc.next();
                tempPassword = sc.next();

                if (tempUsername.trim().equals(usuario.trim()) && tempPassword.trim().equals(contraseña.trim())) {
                    found = true;
                }
            }
            sc.close();
            return found;
        } catch (Exception e) {
            return found;
        }
    }

    /**
     * Método que se encarga de la creación del usuario vendedor. Retornando un
     * boolean true si se realizó correctamente y una false si ocurrio algún
     * error.
     *
     * @param id
     * @param password
     * @return boolean
     * @throws FileNotFoundException
     */
    public boolean crearUsuarioVendedor(String id, String password) throws FileNotFoundException {
        Seguridad seg = new Seguridad();
        boolean creacionUsuario = false;
        String direccionArchivo = "datos/Users/vendedor.txt";

        boolean estado = comprobarExistenciaVendedor(id);

        if (estado = false) {
            String contraseñaSegura = seg.getMD5(password);
            try {
                FileWriter fw = new FileWriter(direccionArchivo, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);

                pw.println(id + ";" + contraseñaSegura);
                pw.flush();
                pw.close();
                creacionUsuario = true;
                return creacionUsuario;

            } catch (Exception e) {
                return creacionUsuario;
            }
        } else {
            return creacionUsuario;
        }

    }

    /**
     * Método que comprueba la existencia del usuaro, devolviendo un true si ya
     * existe y un false si aun no existe.
     *
     * @param user
     * @param destino
     * @return boolean
     * @throws FileNotFoundException
     */
    public static boolean comprobarExistenciaVendedor(String user) throws FileNotFoundException {
        String direccionArchivo = "datos/Users/vendedor.txt";
        String usuario = "";
        String contraseña = "";
        boolean estado = false;
        x = new Scanner(new File(direccionArchivo));
        x.useDelimiter("[;\n]");

        while (x.hasNext()) {
            usuario = x.next();
            contraseña = x.next();

            if (usuario.equals(user)) {
                estado = true;
            } else {
                estado = false;
            }
        }
        x.close();
        return estado;
    }

    /**
     * Método encargado de cambiar la contraseña de un usuario.
     *
     * @param idEditar
     * @param filepath
     * @param password
     */
    public void editarVendedor(String idEditar, String password) {
        String direccionArchivo = "datos/Users/vendedor.txt";
        Seguridad seg = new Seguridad();
        String tempFile = "temp.txt";
        File oldFile = new File(direccionArchivo);
        File newFile = new File(tempFile);

        String usuario = "";
        String contraseña = "";

        String contraseñaSegura = seg.getMD5(password);

        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            x = new Scanner(new File(direccionArchivo));
            x.useDelimiter("[;\n]");

            while (x.hasNext()) {
                usuario = x.next();
                contraseña = x.next();

                if (usuario.equals(idEditar)) {
                    pw.println(idEditar + ";" + contraseñaSegura);
                } else {
                    pw.println(usuario + ";" + contraseña);
                }
            }
            x.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File(direccionArchivo);
            newFile.renameTo(dump);
        } catch (Exception e) {

        }
    }

    /**
     * Este método se encarga de eliminar un usuario.
     *
     * @param filepath
     * @param removeTerm
     */
    public void eliminarVendedor(String removeTerm) {
        String direccionArchivo = "datos/Users/vendedor.txt";
        String tempFile = "temp.txt";
        File oldFile = new File(direccionArchivo);
        File newFile = new File(tempFile);

        String usuario = "";
        String contraseña = "";

        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            x = new Scanner(new File(direccionArchivo));
            x.useDelimiter("[;\n]");

            while (x.hasNext()) {

                usuario = x.next();
                contraseña = x.next();

                if (!usuario.equals(removeTerm)) {
                    pw.println(usuario + ";" + contraseña);
                }
            }
            x.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File(direccionArchivo);
            newFile.renameTo(dump);

        } catch (Exception e) {

        }
    }

    public void crearArchivoAdmin() throws IOException {
        String destino = System.getProperty("user.home");
        destino += File.separator + "SIVU";
        destino += File.separator + "Admin";
        File customDir = new File(destino);

        if (customDir.exists()) {
            System.out.println(customDir + " ya existe");
        } else if (customDir.mkdirs()) {
            System.out.println(customDir + " fue creado");
        } else {
            System.out.println(customDir + " no fue creado");
        }
        File file = new File(customDir, "admin.txt");
        file.createNewFile();
    }

    public void crearArchivoUsuario() throws IOException {
        String destino = System.getProperty("user.home");
        destino += File.separator + "SIVU";
        destino += File.separator + "Usuario";
        File customDir = new File(destino);

        if (customDir.exists()) {
            System.out.println(customDir + " ya existe");
        } else if (customDir.mkdirs()) {
            System.out.println(customDir + " fue creado");
        } else {
            System.out.println(customDir + " no fue creado");
        }
        File file = new File(customDir, "usuario.txt");
        file.createNewFile();
    }

}
