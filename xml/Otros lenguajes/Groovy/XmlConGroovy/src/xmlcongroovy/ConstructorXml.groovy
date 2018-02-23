/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package xmlcongroovy

import groovy.xml.MarkupBuilder

/**
 *
 * @author jose maria
 */
class ConstructorXml {
    def construirXml(){
        def builder = new MarkupBuilder()
        builder.autores{
            autor(nombre:'Bashar AbdulJawad'){
                libro(titulo:'Groovy and grails recipes',edicion:1)
            }
            autor(nombre:'Graeme Rocher'){
                libro(titulo:'The Definitiva Guide to Grails',edicion:2)
            }
        }
    }
}

