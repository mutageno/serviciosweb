/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package leerxmlgroovyslurper

import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.*

/**
 *
 * @author jose maria
 */
class LeerXmlXPath {
    def leer(){
        def xpath = '''
   /nutrition/food[vitamins/* > 0]/name
''' // Selecciona elementos de tipo food que contienen vitaminas
        def builder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        def doc = builder.parse(LeerXmlXPath.class.getResourceAsStream('/recursos/food.xml'))
        def expr = XPathFactory.newInstance().newXPath().compile(xpath)
        def nodes = expr.evaluate(doc, XPathConstants.NODESET)
        def list = []
        nodes.each{list+= it.textContent}
        assert ["Beef Frankfurter, Quarter Pound"] == list
        'Hecho'
    }
}

