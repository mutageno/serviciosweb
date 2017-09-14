/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cache;

/**
 *
 * @author Chema
 * @param <K>
 * @param <V>
 */
public interface Cache<K,V> {
    V get(K clave);
    void put(K clave, V valor);
}
