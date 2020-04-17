/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo4sa;

import java.sql.Connection;

import negocio.HiloCliente;
import negocio.herramientas.Conexion;

/**
 *
 * @author Brayan
 */
public class Grupo4sa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
         HiloCliente hiloCliente;
        Connection con = Conexion.getInstance().getConnection();
        if (con != null) {
            System.out.println(" ¡¡¡ Conexion exitosa !!!");;
            hiloCliente = new HiloCliente();
            hiloCliente.start();
        } else {
            System.out.println("Fallo en la Conexion");
        }
        
        
    }
    
}
