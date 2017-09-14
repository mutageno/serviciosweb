/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cache;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Chema
 */
public class MemoriaCache implements Cache<String, Date> {

    private final Map<String, Date> m = new HashMap<>();

    @Override
    public Date get(String clave) {
        return m.get(clave);
    }

    @Override    
    public void put(String clave, Date valor) {
        m.put(clave, valor);
    }
}
