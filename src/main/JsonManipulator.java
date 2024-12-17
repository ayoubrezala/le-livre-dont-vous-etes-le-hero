package main;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class JsonManipulator {



    private static String wd = "/home/benameu231/Documents/Software_Engineering/interface_seperated_deps/src/livres/";



    
    public static JSONObject readJsonObjectFromFile(String fileName) {
        try {
            // Specify the absolute path to your JSON file
            // String pwd = ;

            String filePath = wd + fileName + ".json";

            // Read the JSON file as a String
            String jsonString = FileUtils.readFileToString(new File(filePath), "UTF-8");

            // Parse the JSON String into a JSONObject
            JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(jsonString);

            // Now you can use the jsonObject as needed
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public static void writeJsonObjectToFile(JSONObject jsonObject, String fileName) {
        try {
            // Specify the directory where you want to save the file
            // String directoryPath = "/home/benameu231/Documents/interface_copy/src/livres/";
    
            // Create the directory if it doesn't exist
            File directory = new File(wd);
            if (!directory.exists()) {
                directory.mkdirs();
            }
    
            // Construct the file path
            String filePath = wd + fileName + ".json";
    
            // Write the JSON object to file
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(jsonObject.toString(4)); // Use 4-space indentation for better readability
                System.out.println("JSON object successfully written to file: " + filePath);
            } catch (IOException e) {
                System.err.println("Error writing JSON object to file: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Error creating directory: " + e.getMessage());
        }
    }
    
}
