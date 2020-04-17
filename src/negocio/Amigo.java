/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.ArrayList;


import dato.DatosAmigo;
import negocio.herramientas.Mensaje;

/**
 *
 * @author Brayan
 */
public class Amigo {
    
    String contenido;
    Mensaje mensaje;

    public Amigo(String contenido) {
        this.contenido = contenido;
    }

    public Mensaje analizar(Mensaje mensaje) {
        this.mensaje = mensaje;
        Mensaje myMensaje = null;
        myMensaje = new Mensaje(mensaje.getCorreo(), "Listar ", "\n" + obtenerDatos(contenido));

        return myMensaje; /// modificando para el envio del mensaje a gmail
    }

    private String obtenerDatos(String query) {
        ArrayList<DatosAmigo> lista = DatosAmigo.All(query);

//        String leftAlignFormat = " | %-4d | %-15s | %-15s |%n";
//
//        System.out.format(" +-------+---------------------+----------+%n");
//        System.out.format(" |   CI  |        NOMBRE      |  TELEFONO |%n");
//        System.out.format(" +-------+---------------------+----------+%n");
//        for (int i = 0; i < lista.size(); i++) {
//            DAmigo modelo = lista.get(i);
//            System.out.format(leftAlignFormat, modelo.getAmig_ci(), modelo.getAmig_nombre(), modelo.getAmig_telf());
//        }
//        return ("+----+--------+----------+%n");
        String result = "<h1>LISTADO DE AMIGOS</h1>"
                + "<table>"
                + "<tr>\n"
                + "<th>CI</th>\n"
                + "<th>NOMBRE</th>\n"
                + "<th>APELLIDO</th>\n"
                + "<th>TELEFONO</th>\n"
                + "<th>CELULAR</th>\n"
                + "<th>DIRECCION</th>\n"
                + "<th>FEC NACIMIENTO</th>\n"
                + "</tr>\n";
        for (int i = 0; i < lista.size(); i++) {
            DatosAmigo modelo = lista.get(i);
            result = result + "<tr>\n"
                    + "<td>" + Integer.toString(modelo.getAmig_ci()) + "</td>\n"
                    + "<td>" + modelo.getAmig_nombre() + "</td>\n"
                    + "<td>" + modelo.getAmig_appm() + "</td>\n"
                    + "<td>" + modelo.getAmig_telf() + "</td>\n"
                    + "<td>" + modelo.getAmig_cel() + "</td>\n"
                    + "<td>" + modelo.getAmig_dir() + "</td>\n"
                    + "<td>" + modelo.getAmig_fnac() + "</td>\n"
                    + "</tr>\n";
        }
        result = result + "</table>";
        return result;
    }

    public Mensaje generarMensajeError(int nroComandos) {
        String subject = "ERROR";
        String data = "";
        if (nroComandos == 0) {
            data = "El comando enviado no es correcto\n"
                    + "Si desea saber los comandos del sistema ingrese :\n"
                    + "\t{HELP}\n\n";
        } else {
            data = "El Caso de Uso solicitado es incorrecto\n"
                    + "Si desea saber los comandos del sistema ingrese :\n"
                    + "\t{HELP}\n\n";
        }
        Mensaje myMensaje = new Mensaje(mensaje.getCorreo(), subject, data);
        return myMensaje;
    }

    
}
