package br.com.cadastro.persistence.fileservice;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class FileXmlSerice {
    private Document document;

    public FileXmlSerice(String fileName) {
        InputStream xml = getClass().getClassLoader().getResourceAsStream(fileName);
        try {
            configure(xml);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

    }

    private void configure(InputStream xml) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder documentBuilder =  factory.newDocumentBuilder();
        this.document = documentBuilder.parse(xml);
    }

    public String findValue(String key){
        Element element = document.getDocumentElement();
        NodeList nodeList = element.getElementsByTagName("query");
        for (int i =0; i < nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) node;
                String attr = e.getAttribute("id");
                if(attr.equals(key)){
                    return e.getTextContent();
                }
            }
        }

        return null;
    }

}
