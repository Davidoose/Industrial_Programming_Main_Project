package org.example;

import org.example.fileProcessing.JSONparser;
import org.example.fileProcessing.XMLparser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.example.fileProcessing.JSONparser.writeJSON;
import static org.example.fileProcessing.XMLparser.writeXML;


public class Main {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, FileNotFoundException {
        try {
            XMLparser.parseXML("input.xml");
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        ArrayList<String> list = new ArrayList<>();
        for (int i = 12; i < 17; i++) {
            list.add(String.valueOf(i));
        }
        writeXML("output.xml", list);

        //testing parseJSON

        JSONparser.parseJSON();
        writeJSON(list);


    }
}