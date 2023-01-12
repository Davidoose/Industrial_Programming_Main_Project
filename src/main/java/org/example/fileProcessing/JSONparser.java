package org.example.fileProcessing;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class JSONparser {
    public static ArrayList<String> parseJSON() {
        JSONParser jsonParser = new JSONParser();
        ArrayList<String> list = new ArrayList<>();
        try (FileReader reader = new FileReader("input.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray employeeList = (JSONArray) obj;
            Iterator<JSONObject> iterator = employeeList.iterator();
            int i = 1;
            while (iterator.hasNext()) {
                JSONObject exps = iterator.next();
                String exp = (String) exps.get("expression");
                i++;
                list.add(exp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ParseException e) {
            e.printStackTrace();

        }
        return list;
    }

    public static void writeJSON(ArrayList<String> list) {
        JSONObject obj = new JSONObject();
        for (int i = 0; i < list.size(); i++) {
            obj.put("expression" + i, list.get(i));
        }
        try (FileWriter file = new FileWriter("output.json")) {
            file.write(obj.toJSONString());
            file.write("\r\n");
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
