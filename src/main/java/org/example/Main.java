package org.example;

import org.example.fileProcessing.JSONparser;
import org.example.fileProcessing.XMLparser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import static org.example.expProcessing.ProccesExp.solvingExpression;
import static org.example.expProcessing.ProccesExpByLib.solvingExpressionByLib;
import static org.example.fileProcessing.ArchivingFiles.archiveFilesZip;
import static org.example.fileProcessing.ArchivingFiles.reArchiveFilesZip;
import static org.example.fileProcessing.JSONparser.writeJSON;
import static org.example.fileProcessing.XMLparser.writeXML;
import static org.example.fileProcessing.fileEncryption.decryptFile;
import static org.example.fileProcessing.fileEncryption.encryptFile;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException, GeneralSecurityException {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> listXML = new ArrayList<>();
        listXML = XMLparser.parseXML("input.xml");
        //testing parseJSON
        list = JSONparser.parseJSON();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        ArrayList<String> ans = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            ans.add(String.valueOf(solvingExpression(list.get(i))));
        }
        ArrayList<String> ansXML = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            ansXML.add(String.valueOf(solvingExpressionByLib(listXML.get(i))));
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(ans.get(i));
        }
        writeXML("output.xml", ansXML);
        writeJSON(ans);

        archiveFilesZip("output.json", "archive.zip");
        reArchiveFilesZip("archive.zip");

        encryptFile("output.xml", "encrypt.axx", "E)H@McQeThWmZq4t");
        decryptFile("encrypt.axx", "decrypt.xml", "E)H@McQeThWmZq4t");
    }
}