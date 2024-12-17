package main;



import properties.Properties;


// import com.mxgraph.layout.mxOrganicLayout;
// import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;


import com.mxgraph.layout.mxCircleLayout ;
import com.mxgraph.layout.mxCompactTreeLayout;


import com.mxgraph.swing.mxGraphComponent;
// import com.mxgraph.util.mxPoint;
import com.mxgraph.view.mxGraph;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;


import javax.swing.*;
import java.awt.*;

public class JSONGraphVisualizer extends JFrame {

    public JSONGraphVisualizer(JSONObject graphData) {
        setTitle("JSON Graph Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);

        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        JSONArray nodes = graphData.getJSONArray(Properties.nodesString);
        JSONArray edges = graphData.getJSONArray(Properties.edgesString);

        graph.getModel().beginUpdate();
        try {
            // Add vertices
            for (Object nodeObj : nodes) {
                JSONObject node = (JSONObject) nodeObj;
                Object vertex = graph.insertVertex(parent, null, node.getString(Properties.textString), 20, 20, 120, 40);
                graph.getModel().setValue(vertex, node.getString(Properties.idString));
            }

            // Add edges
            for (Object edgeObj : edges) {
                JSONObject edge = (JSONObject) edgeObj;
                Object source = findVertexById(graph, edge.getString(Properties.sourceString));
                Object target = findVertexById(graph, edge.getString(Properties.targetString));
                Object edgeObject = graph.insertEdge(parent, null, edge.getString(Properties.choiceTextString), source, target);
                
                // Set style for edges (including width)
                graph.setCellStyle("strokeWidth=2", new Object[]{edgeObject});
            }
        } finally {
            graph.getModel().endUpdate();
        }

        mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
        layout.execute(parent);

        // mxParallelEdgeLayout parallelEdgeLayout = new mxParallelEdgeLayout(graph);
        // parallelEdgeLayout.execute(parent);

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent, BorderLayout.CENTER);
        setVisible(true);
    }

    private Object findVertexById(mxGraph graph, String id) {
        for (Object vertex : graph.getChildVertices(graph.getDefaultParent())) {
            if (graph.getLabel(vertex).equals(id)) {
                return vertex;
            }
        }
        return null;
    }
}
