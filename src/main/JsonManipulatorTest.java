package main;

import net.sf.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.io.File;




public class JsonManipulatorTest {

    @Test
    public void testReadJsonObjectFromFile() {
        String fileName = "testFile";
        JSONObject jsonObject = JsonManipulator.readJsonObjectFromFile(fileName);

        assertNotNull(jsonObject); // Vérifie si le JSONObject retourné n'est pas null
        assertFalse(jsonObject.isEmpty()); // Vérifie si le JSONObject n'est pas vide
        assertTrue(jsonObject.containsKey("sections"));

    // Récupère l'objet JSON des sections
         JSONObject sectionsObject = jsonObject.getJSONObject("sections");

    // Vérifie la structure de chaque section
    for (Object key : sectionsObject.keySet()) {
        JSONObject section = sectionsObject.getJSONObject((String) key);
        assertTrue(section.containsKey("text")); // Vérifie la présence de la clé "text"
        assertTrue(section.containsKey("end")); // Vérifie la présence de la clé "end"
        assertTrue(section.getBoolean("end") || section.containsKey("choices")); // Vérifie la présence de la clé "choices" si "end" est false

        // Vérifie que la clé "win" est présente et a la valeur false lorsque "end" est false
        if (!section.getBoolean("end")) {
            assertTrue(section.containsKey("win"));
            assertFalse(section.getBoolean("win"));
        }



    }
    }




    @Test
public void testWriteJsonObjectToFile() { 
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("key", "value");

    String outputFileName = "outputFile";
    String workingDirectory = System.getProperty("user.dir");
    File outputFile = new File(workingDirectory + File.separator + outputFileName + ".json");

    JsonManipulator.writeJsonObjectToFile(jsonObject, outputFile.getAbsolutePath());

    assertTrue(outputFile.exists());


    // Teste la lecture du fichier écrit
    String inputFileName = "testFile";
    JSONObject readJsonObject = JsonManipulator.readJsonObjectFromFile(inputFileName);

    // Vérifie que le JSONObject retourné n'est pas null
    assertNotNull(readJsonObject);

    // Vérifie que le JSONObject contient les clés "nodes" et "edges"
    assertTrue(readJsonObject.containsKey("nodes"));
    assertTrue(readJsonObject.containsKey("edges"));
}
}