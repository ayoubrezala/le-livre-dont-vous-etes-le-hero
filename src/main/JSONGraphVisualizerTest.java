package main;


import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

import org.junit.Test;

import javax.swing.*;

public class JSONGraphVisualizerTest {

    @Test
    public void testJSONGraphVisualizer() {
        // Création d'un exemple de graphe JSON à visualiser
        JSONObject graphData = new JSONObject();
        JSONArray nodesArray = new JSONArray();
        JSONObject node1 = new JSONObject();
        node1.put("id", "1");
        node1.put("text", "Node 1");
        nodesArray.add(node1);
        // Ajoutez d'autres nœuds au besoin

        JSONArray edgesArray = new JSONArray();
        JSONObject edge1 = new JSONObject();
        edge1.put("source", "1");
        edge1.put("target", "2");
        edge1.put("choice_text", "Edge 1");
        edgesArray.add(edge1);
        // Ajoutez d'autres arêtes au besoin

        graphData.put("nodes", nodesArray);
        graphData.put("edges", edgesArray);

        // Création d'une instance de JSONGraphVisualizer
        SwingUtilities.invokeLater(() -> {
            JSONGraphVisualizer visualizer = new JSONGraphVisualizer(graphData);
            visualizer.setVisible(true);
        });

        // Attente pour permettre à la fenêtre graphique de s'afficher (facultatif)
        try {
            Thread.sleep(5000); // Attend 5 secondes avant de terminer le test
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
