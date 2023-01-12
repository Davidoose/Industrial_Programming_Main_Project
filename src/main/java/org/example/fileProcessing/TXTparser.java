package org.example.fileProcessing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TXTparser {
    public static ArrayList<String> parseTXT(String txtFile) throws IOException {
        ArrayList<String> res = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(txtFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String nextLine;
        while ((nextLine = reader.readLine()) != null) {
            res.add(nextLine);
        }
        return res;
    }
}

