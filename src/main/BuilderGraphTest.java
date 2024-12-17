package main;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

public class BuilderGraphTest {

    @Test
    public void testBuildGraph() {
        // Création d'un exemple de JSON représentant des sections
        JSONObject sectionsJson = new JSONObject();

        // Section 1
        JSONObject section1 = new JSONObject();
        section1.put("text", "Section 1");
        section1.put("end", false);
        section1.put("win", false);
        JSONArray choices1 = new JSONArray();
        JSONObject choice1 = new JSONObject();
        choice1.put("text", "Choice 1");
        choice1.put("section", "2");
        choices1.add(choice1);
        section1.put("choices", choices1);
        sectionsJson.put("1", section1);

        // Section 2
        JSONObject section2 = new JSONObject();
        section2.put("text", "Section 2");
        section2.put("end", true);
        section2.put("win", true);
        sectionsJson.put("2", section2);

        // Appel de la méthode à tester
        JSONObject graphJson = BuilderGraph.buildGraph(sectionsJson);

        // Vérification du résultat
        assertNotNull(graphJson);
        assertTrue(graphJson.containsKey("nodes"));
        assertTrue(graphJson.containsKey("edges"));

        // Vérification des nœuds
        JSONArray nodesArray = graphJson.getJSONArray("nodes");
        assertNotNull(nodesArray);
        assertEquals(2, nodesArray.size());
        JSONObject node1 = nodesArray.getJSONObject(0);
        JSONObject node2 = nodesArray.getJSONObject(1);

        // Vérification du premier nœud
        assertEquals("1", node1.getString("id"));
        assertEquals("Section 1", node1.getString("text"));
        assertFalse(node1.getBoolean("end"));
        assertFalse(node1.getBoolean("win"));

        // Vérification du deuxième nœud
        assertEquals("2", node2.getString("id"));
        assertEquals("Section 2", node2.getString("text"));
        assertTrue(node2.getBoolean("end"));
        assertTrue(node2.getBoolean("win"));

        // Vérification des arêtes
        JSONArray edgesArray = graphJson.getJSONArray("edges");
        assertNotNull(edgesArray);
        assertEquals(1, edgesArray.size());
        JSONObject edge1 = edgesArray.getJSONObject(0);
        assertEquals("1", edge1.getString("source"));
        assertEquals("2", edge1.getString("target"));
        assertEquals("Choice 1", edge1.getString("choice_text"));
    }
}
