package com.curso.ejemploxpath.jdk5xpath;

import java.io.IOException;
import java.net.URL;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XPathEvaluator {

    public void evaluateDocument(String xmlDocument) {

        try {
            XPathFactory factory = XPathFactory.newInstance();
            XPath xPath = factory.newXPath();

            InputSource inputSource = new InputSource(new URL(xmlDocument).openStream());

            // Find the title of the first article dated January-2004,
            // but first compile the xpath expression
            XPathExpression xPathExpression = xPath.compile("/catalog/journal/article[@date='January-2004']/title");
            // This returns the title value
            String title = xPathExpression.evaluate(inputSource);
            // Print title
            System.out.println("Title: " + title);

            // create input source for XML document
            inputSource = new InputSource(new URL(xmlDocument).openStream());
            // Find publisher of first journal that is not in any namespace.
            // This time we are not compiling the XPath expression.
            // Return the publisher value as a string.
            String publisher = xPath.evaluate("/catalog/journal/@publisher",
                    inputSource);
            // Print publisher
            System.out.println("Publisher:" + publisher);

            // Find all titles
            String expression = "//title";
            // Reset XPath to its original configuration
            xPath.reset();
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(xmlDocument);
            // Evaluate xpath expression on a document object and
            // result as a node list.
            NodeList nodeList = (NodeList) xPath.evaluate(expression, document,
                    XPathConstants.NODESET);

            // Iterate over node list and print titles
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                System.out.println(element.getFirstChild().getNodeValue());

            }

            // This is an exmaple of using NamespaceContext
            NamespaceContext namespaceContext = new NamespaceContextImpl(
                    "journal", "http://www.apress.com/catalog/journal");
            xPath.setNamespaceContext(namespaceContext);
            // Create an input source
            inputSource = new InputSource(new URL(xmlDocument).openStream());
            // Find title of first article in first
            // journal, in journal namespace
            title = xPath.evaluate("/catalog/journal:journal/article/title",
                    inputSource);
            System.out.println("Title:" + title);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (XPathExpressionException e) {
            System.out.println(e.getMessage());
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] argv) {

        XPathEvaluator evaluator = new XPathEvaluator();
        evaluator.evaluateDocument("http://localhost:8080/EsquemaServerMaven/Chapter4/catalog.xml");
    }

    /**
     * This is a private class for NamespaceContext
     */
    private class NamespaceContextImpl implements NamespaceContext {

        public String uri;
        public String prefix;

        public NamespaceContextImpl() {
        }

        /**
         * Constrcutor
         *
         * @param prefix namespace prefix
         * @param uri namespace uri
         */
        public NamespaceContextImpl(String prefix, String uri) {
            this.uri = uri;
            this.prefix = prefix;
        }

        /**
         * @param prefix namespace prefix
         * @return namespace URI
         */
        public String getNamespaceURI(String prefix) {
            return uri;
        }

        /**
         * set uri
         *
         * @param uri namespace uri
         */
        public void setNamespaceURI(String uri) {
            this.uri = uri;
        }

        /**
         * @param uri namespace uri
         * @return namespace prefix
         */
        public String getPrefix(String uri) {
            return prefix;
        }

        /**
         * set prefix
         *
         * @param prefix namespace prefix
         */
        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        /**
         * One uri may have multiple prefixes. We will allow only one preifx per
         * uri.
         *
         * @return an iterator for all prefixes for a uri
         */
        public java.util.Iterator getPrefixes(String uri) {
            if (uri == null) {
                throw new IllegalArgumentException();
            }
            java.util.ArrayList<String> li = new java.util.ArrayList<String>();
            if (this.uri == null ? uri == null : this.uri.equals(uri)) {
                li.add(prefix);
            }
            return li.iterator();
        }
    }
}
