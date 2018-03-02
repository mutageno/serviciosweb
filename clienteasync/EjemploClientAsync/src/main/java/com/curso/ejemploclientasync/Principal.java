/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.ejemploclientasync;

import com.curso.ejemploserverasync.SumaResponse;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.Response;

/**
 *
 * @author usuario
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        llamadaSincrona(3, 3);
        llamadaAsincronaPolling(5, 5);

        Future<?> rFuturo = llamadaAsincronaCallback(10,10);
        System.out.println("El hilo principal está detenido");
        while(!rFuturo.isDone());
        System.out.println("Fin");
    }

    private static Future<?> llamadaAsincronaCallback(int uno, int dos) {
        //            AsyncHandler<SumaResponse> handler = new AsyncHandler<SumaResponse>() {
//                @Override
//                public void handleResponse(Response<SumaResponse> res) {
//                    try {
//                        System.out.println("El valor es " + res.get(1, TimeUnit.SECONDS).getReturn());
//                    } catch (InterruptedException | ExecutionException | TimeoutException ex) {
//                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            };
        com.curso.ejemploserverasync.Asincrono_Service service = new com.curso.ejemploserverasync.Asincrono_Service();
        com.curso.ejemploserverasync.Asincrono port = service.getAsincronoPort();
// TODO initialize WS operation arguments here
// TODO process result here
        System.out.println("Llamada asíncrona usando callback");
        return port.sumaAsync(uno, dos, res -> {
            try {
                System.out.println("Callback ejecutado");
                System.out.println("Resultado: " + res.get(1, TimeUnit.SECONDS).getReturn());
            } catch (InterruptedException | ExecutionException | TimeoutException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private static void llamadaAsincronaPolling(int uno, int dos) {
        try { // Call Web Service Operation
            com.curso.ejemploserverasync.Asincrono_Service service = new com.curso.ejemploserverasync.Asincrono_Service();
            com.curso.ejemploserverasync.Asincrono port = service.getAsincronoPort();
            // TODO initialize WS operation arguments here
            // TODO process result here
            System.out.println("Llamada asícrona usando polling");
            Response<SumaResponse> response = port.sumaAsync(uno, dos);
            SumaResponse s = response.get(1, TimeUnit.SECONDS);
            System.out.println("El hilo principal del programa está detenido");
            int resultado = s.getReturn();
            System.out.println("Resultado = " + resultado);
        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
            LOG.log(Level.SEVERE, "Ha ocurrido un error", ex);
        }
    }

    private static void llamadaSincrona(int uno, int dos) {
        try { // Call Web Service Operation
            com.curso.ejemploserverasync.Asincrono_Service service = new com.curso.ejemploserverasync.Asincrono_Service();
            com.curso.ejemploserverasync.Asincrono port = service.getAsincronoPort();
            // TODO initialize WS operation arguments here
            // TODO process result here
            System.out.println("Llamada síncrona");
            int resultado = port.suma(uno, dos);
            System.out.println("Resultado = " + resultado);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ha ocurrido un error", ex);
        }
    }
    private static final Logger LOG = Logger.getLogger(Principal.class.getName());

}
