package main;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;


import java.util.*;


import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {


        // JSONObject book = JsonManipulator.readJsonObjectFromFile("example_copy");

        // JSONObject bookGraph = BuilderGraph.buildGraph(book);


        // System.out.println("book: " + book);
        // System.out.println("");
        // System.out.println("bookGraph: " + bookGraph);


        // JsonManipulator.writeJsonObjectToFile(bookGraph, "example_copy_graph");


        JSONObject graphData = JsonManipulator.readJsonObjectFromFile("example_copy_graph");
        SwingUtilities.invokeLater(() -> new JSONGraphVisualizer(graphData));


    }
}