/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import cache.Cache;
import cache.MemoriaCache;
import java.util.Date;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author usuario
 */
@WebService(serviceName = "CacheWebService")
public class CacheWebService {

 private final Cache<String, Date> cache = new MemoriaCache();

    /**
     * This is a sample web service operation
     * @param txt
     * @return 
     */
    @WebMethod(operationName = "cache")
    public String mensajeEnCache(@WebParam(name = "mensaje") String txt) {
        Date valor;
        if ((valor = cache.get(txt)) == null) {
            valor = new Date();
            cache.put(txt, valor);
        }
        return "Mensaje " + txt + " recibido a las " + valor;
    }
}
