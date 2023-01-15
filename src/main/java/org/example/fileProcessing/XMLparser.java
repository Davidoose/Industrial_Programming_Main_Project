package org.example.fileProcessing;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class XMLparser {
    public static ArrayList<String> parseXML(String xmlFile) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(xmlFile));
        ArrayList<String> problems = new ArrayList<>();

        Element elem = doc.getDocumentElement();
        NodeList nodes = elem.getChildNodes();
        getElements(nodes, problems);
        return problems;
    }

    static void getElements(NodeList nodes, ArrayList<String> problems) {

        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i) instanceof Element) {
                if (((Element) nodes.item(i)).hasAttribute("problem")) {
                    problems.add(((Element) nodes.item(i)).getAttribute("problem"));
                }
                if (nodes.item(i).hasChildNodes()) {
                    getElements(nodes.item(i).getChildNodes(), problems);
                }
            }
        }
    }

    public static void writeXML(String xmlFile, ArrayList<String> results) throws ParserConfigurationException, TransformerException, FileNotFoundException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element elem = doc.createElement("xmlExpressionList");
        doc.appendChild(elem);
        for (int i = 1; i <= results.size(); i++) {
            Element res = doc.createElement("expression" + i);
            elem.appendChild(res);
            res.setAttribute("problem", results.get(i - 1));
        }
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(xmlFile)));
    }
}
