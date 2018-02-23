package com.curso.ejemploxpath.jdomxpath;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

/**
 * This class illustrates executing of different type of XPath expressions,
 * using JDOM 1.0 XPath API.
 */
public class JDomXPath {

    public void parseDocument(InputStream xmlDocument) throws JDOMException, IOException {

        // Create a SAXBuilder parser
        SAXBuilder saxBuilder = new SAXBuilder("org.apache.xerces.parsers.SAXParser");
        // Create a JDOM document object
        org.jdom.Document jdomDocument = saxBuilder.build(xmlDocument);

        // select level attribute in first article dated January-2004
        // in first journal
        org.jdom.Attribute levelNode = (org.jdom.Attribute) (XPath.selectSingleNode(jdomDocument,
                "/catalog//journal//article[@date='January-2004']/@level"));

        System.out.println(levelNode.getValue());

        // select title attribute in first article dated January-2004
        // in first journal
        org.jdom.Element titleNode = (org.jdom.Element) XPath.selectSingleNode(jdomDocument,
                "/catalog//journal//article[@date='January-2004']/title");

        System.out.println(titleNode.getText());

        // select title of all articles
        // in journal dated Java Technology
        java.util.List nodeList = XPath.selectNodes(jdomDocument,
                "/catalog/journal[@title='Java Technology']/article/title");

        Iterator iter = nodeList.iterator();

        while (iter.hasNext()) {
            org.jdom.Element element = (org.jdom.Element) iter.next();
            System.out.println(element.getText());

        }

        // Example of a xpath expression using namespace
        // Select level attribute in journal namespace
        // in first article in first journal in journal namespace
        XPath xpath = XPath.newInstance("/catalog/journal:journal/article/@journal:level");
        xpath.addNamespace("journal",
                "http://www.apress.com/catalog/journal");

        org.jdom.Attribute namespaceNode = (org.jdom.Attribute) xpath.selectSingleNode(jdomDocument);

        System.out.println(namespaceNode.getValue());

    }

    public static void main(String[] argv) {
        try {
            JDomXPath parser = new JDomXPath();
            URL url = new URL("http://localhost:8080/EsquemaServerMaven/Chapter4/catalog.xml");
            parser.parseDocument(url.openStream());
        } catch (JDOMException ex) {
            Logger.getLogger(JDomXPath.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(JDomXPath.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JDomXPath.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
