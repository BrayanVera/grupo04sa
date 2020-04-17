/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


import negocio.servicios.ClientePop;
import negocio.servicios.ClienteSmtp;
import negocio.herramientas.Mensaje;
import negocio.Amigo;

/**
 *
 * @author Brayan
 */
public class HiloCliente {
    
        private Timer temporizador;
    private int milisegundosDeRetraso = 7000;

    public void start() {
        stop();
        temporizador = new Timer();
        planificar();
    }

    private void planificar() {
        if (temporizador != null) {
            Date timeToRun = new Date(System.currentTimeMillis() + milisegundosDeRetraso);
            temporizador.schedule(new TimerTask() {
                public void run() {
                    verificarBandeja();
                }
            }, timeToRun);
        }
    }

    /*Este metodo verificara cada rato el servidor
     */
    private synchronized void verificarBandeja() {
        System.out.println("verificado en " + System.currentTimeMillis());
        ClientePop pop = new ClientePop();
        Mensaje mensaje = pop.LeerCorreo();

        if (mensaje != null) {

            String contenidoMensaje = mensaje.getSubject();
            if (contenidoMensaje != "") {
                Amigo amigo = new Amigo(contenidoMensaje); 
                Mensaje result = amigo.analizar(mensaje); 
                
                //System.out.println(" Mensaje :" + mensaje.toString());
                //System.out.println(" A ENVIAR :" + result.toString());
                
                
                ClienteSmtp smtp = new ClienteSmtp();
                if (smtp.enviarCorreo(result)) {
                    System.out.println(" S : Se envio el mensaje");
                    //pop.eliminarCorreo(mensaje.getId());
                }
                
            }
            pop.eliminarCorreo(mensaje.getId());

        } else {
            System.out.println(" S : No hay mensajes nuevos");
        }
        planificar();
    }

    public void stop() {
        if (temporizador != null) {
            temporizador.cancel();
            temporizador = null;
        }
    }
    
}
