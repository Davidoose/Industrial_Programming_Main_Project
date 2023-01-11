package org.example.fileProcessing;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLparser {
    public static void parseXML(String xmlFile) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(xmlFile));

        Element elem = doc.getDocumentElement();
        System.out.println(elem.getTagName());
        NodeList nodes = elem.getChildNodes();
        getElements(nodes);
    }

    static ArrayList<String> getElements(NodeList nodes) {
        ArrayList<String> problems = new ArrayList<>();

        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i) instanceof Element) {
                if (((Element) nodes.item(i)).hasAttribute("problem")) {
                    System.out.println(((Element) nodes.item(i)).getAttribute("problem"));
                    problems.add(((Element) nodes.item(i)).getAttribute("problem"));
                }
                if (nodes.item(i).hasChildNodes()) {
                    getElements(nodes.item(i).getChildNodes());
                }
            }
        }
        return problems;
    }
}
