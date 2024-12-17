package main;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import properties.Properties;

import java.util.HashMap;
import java.util.Map;





public class BuilderGraph {
    
    /*
     * This function takes a book represented by a JSON, and returns a JSON representing the corresponding Graph
     * @param  sectionsJson : book represented by JSON
     * @returns JSONObject : book represented by a JSON Graph
     */
    public static JSONObject buildGraph(JSONObject sectionsJson) {
        JSONArray nodesArray = new JSONArray();
        JSONArray edgesArray = new JSONArray();
        Map<String, JSONObject> sectionNodes = new HashMap<>();

        // System.out.println("sectionsJson: "+sectionsJson);
        // System.out.println("");

        JSONObject sections = sectionsJson.getJSONObject(Properties.sectionsString);

        // Create nodes for each section
        for (Object key : sections.keySet()) {
            String sectionId = (String) key;
            // System.out.println("sectionId: "+sectionId);
            JSONObject section = sections.getJSONObject(sectionId);
            // System.out.println("section: "+ section);
            // System.out.println("");
            JSONObject node = new JSONObject();
            node.put("id", sectionId);
            node.put(Properties.textString, section.getString(Properties.textString));
            node.put(Properties.endString, section.getBoolean(Properties.endString));
            nodesArray.add(node);
            sectionNodes.put(sectionId, node);
        }

        for (Object key : sections.keySet()) {
            String sectionId = (String) key;
            JSONObject section = sections.getJSONObject(sectionId);
            if(!section.getBoolean(Properties.endString))
            {
                JSONArray choices = section.getJSONArray(Properties.choicesString);
                for (int i = 0; i < choices.size(); i++) {
                    JSONObject choice = choices.getJSONObject(i);
                    JSONObject edge = new JSONObject();
                    edge.put(Properties.sourceString, sectionId);
                    edge.put(Properties.targetString, choice.getString("section"));
                    edge.put("choice_text", choice.getString(Properties.textString));
                    edgesArray.add(edge);
                }
            }

        }

        JSONObject graphJson = new JSONObject();
        graphJson.put(Properties.nodesString, nodesArray);
        graphJson.put(Properties.edgesString, edgesArray);

        return graphJson;
    }


}
