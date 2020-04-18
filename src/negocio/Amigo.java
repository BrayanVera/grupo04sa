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

        return myMensaje; 
    }

    private String obtenerDatos(String query) {
        ArrayList<DatosAmigo> lista = DatosAmigo.All(query);

        String result = "<html>"
                + "<head>\n" +
                "<style>\n" +
                "table {\n" +
                "  font-family: arial, sans-serif;\n" +
                "  border-collapse: collapse;\n" +
                "  width: 100%;\n" +
                "}\n" +
                "\n" +
                "td, th {\n" +
                "  border: 1px solid #dddddd;\n" +
                "  text-align: left;\n" +
                "  padding: 8px;\n" +
                "}\n" +
                "\n" +
                "tr:nth-child(even) {\n" +
                "  background-color: #dddddd;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<h2>Lista de Amigos</h2>" +
                "<body>"
                + "<table>"
                + "<tr>\n"
                + "<th>Ci</th>\n"
                + "<th>Nombre Completo</th>\n"             
                + "<th>Telefono</th>\n"
                + "<th>Ccelular</th>\n"
                + "<th>Direccion</th>\n"
                + "<th>Fech. Nac.</th>\n"
                + "</tr>\n";
        for (int i = 0; i < lista.size(); i++) {
            DatosAmigo amigo = lista.get(i);
            result = result + "<tr>\n"
                    + "<td>" + Integer.toString(amigo.getAmig_ci()) + "</td>\n"
                    + "<td>" + amigo.getAmig_nombre() + " "+amigo.getAmig_appm() +"</td>\n"
                    + "<td>" + amigo.getAmig_telf() + "</td>\n"
                    + "<td>" + amigo.getAmig_cel() + "</td>\n"
                    + "<td>" + amigo.getAmig_dir() + "</td>\n"
                    + "<td>" + amigo.getAmig_fnac() + "</td>\n"
                    + "</tr>\n";
        }
        result = result + "</table>"
                + "</body>"
                +"</html>";
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
