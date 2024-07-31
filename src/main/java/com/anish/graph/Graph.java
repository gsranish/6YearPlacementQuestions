package com.anish.graph;

import java.util.*;

public class Graph {
    private Map<String, List<String>> adjList; // adjacency list to store the graph

    // Constructor to initialize the graph
    public Graph() {
        adjList = new HashMap<>(); // Using a HashMap to store adjacency lists for each vertex
    }

    // Method to add a vertex to the graph
    public void addVertex(String vertex) {
        // Check if the vertex already exists
        if (!adjList.containsKey(vertex)) {
            adjList.put(vertex, new ArrayList<>()); // Add a new vertex with an empty adjacency list
        }
    }

    // Method to add an edge between two vertices
    public void addEdge(String source, String destination) {
        // Add vertices if they don't exist
        addVertex(source);
        addVertex(destination);

        // Add an edge (undirected graph)
        adjList.get(source).add(destination); // Add destination to source's adjacency list
        adjList.get(destination).add(source); // Add source to destination's adjacency list
    }

    // Method to get the adjacency list of a vertex
    public List<String> getAdjacencyList(String vertex) {
        return adjList.get(vertex); // Return the adjacency list of the given vertex
    }

    // Method to print the graph
    public void printGraph() {
        for (String vertex : adjList.keySet()) {
            System.out.println("Vertex " + vertex + ": " + adjList.get(vertex));
        }
    }

    // Method to perform Depth-First Search (DFS)
    public void depthFirstSearch(String start) {
        Set<String> visited = new HashSet<>(); // Set to track visited vertices
        depthFirstSearchHelper(start, visited); // Start DFS from the given vertex
    }

    // Helper method for DFS
    private void depthFirstSearchHelper(String vertex, Set<String> visited) {
        visited.add(vertex); // Mark the current vertex as visited
        System.out.print(vertex + " "); // Print the vertex

        // Recursively visit all adjacent vertices
        for (String neighbor : adjList.get(vertex)) {
            if (!visited.contains(neighbor)) { // Check if the neighbor has not been visited
                depthFirstSearchHelper(neighbor, visited);
            }
        }
    }

    // Method to perform Breadth-First Search (BFS)
    public void breadthFirstSearch(String start) {
        Set<String> visited = new HashSet<>(); // Set to track visited vertices
        Queue<String> queue = new LinkedList<>(); // Queue for BFS

        visited.add(start); // Mark the start vertex as visited
        queue.add(start); // Add the start vertex to the queue

        while (!queue.isEmpty()) {
            String vertex = queue.poll(); // Dequeue a vertex from the queue
            System.out.print(vertex + " "); // Print the vertex

            // Visit all adjacent vertices
            for (String neighbor : adjList.get(vertex)) {
                if (!visited.contains(neighbor)) { // Check if the neighbor has not been visited
                    visited.add(neighbor); // Mark the neighbor as visited
                    queue.add(neighbor); // Add the neighbor to the queue
                }
            }
        }
    }
}

class GraphExample {
    
    public static void main(String[] args) {
        Graph graph = new Graph();

        // Add vertices
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        // Add edges
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");
        graph.addEdge("D", "E");

        // Print the graph
        System.out.println("Graph adjacency list: ");
        graph.printGraph();

        // Perform Depth-First Search
        System.out.println("\nDepth-First Search starting from vertex A: ");
        graph.depthFirstSearch("A");

        // Perform Breadth-First Search
        System.out.println("\n\nBreadth-First Search starting from vertex A:\n ");
        graph.breadthFirstSearch("A");
    }
}