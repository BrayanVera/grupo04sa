/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.herramientas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brayan
 */
public class Conexion {
    
        private static Conexion instance = null;

    private final String db_name = "db_agenda";
    private final String port = "5432";
    private final String host = "virtual.fcet.uagrm.edu.bo";
    private final String usuario = "agenda";
    private final String password = "agendaagenda";

    private Connection conn;
    private Conexion() {
    }
     private void contectar() {
        String urlDatabase = "jdbc:postgresql://" + host + ":" + port + "/" + db_name;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(urlDatabase, usuario, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Ocurrio un error : " + e.getMessage());
        }
    }
    public synchronized static Conexion getInstance() {

        instance = new Conexion();
        instance.contectar();
        return instance;
    }
    public Connection getConnection() {
        return conn;
    }

    public void cerrarConexion() {
        try {
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.instance = null;
        }
    }

    
    
}
