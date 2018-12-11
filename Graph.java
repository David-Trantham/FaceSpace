package finalProject;
/*
 * The following provides an adjacency list implementation of a graph. The main method
 * prints the adjacency lists for the graphs constructed.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Graph {

    private int numVertices;
    private int numEdges;
    private BagList<Integer>[] adj;

    // create an empty graph with V vertices
    public Graph(int numVertices) {
        initializeEmptyGraph(numVertices);
    }

    // create a graph from a file
    public Graph(String pathToFile) {

        File f = new File(pathToFile);
        Scanner scanner;
        try {
            scanner = new Scanner(f);

            // read number of vertices
            int numVertices = Integer.parseInt(scanner.nextLine());
            initializeEmptyGraph(numVertices);

            while (scanner.hasNextLine()) {     // read and add edges
                int v = scanner.nextInt();
                int w = scanner.nextInt();
                this.addEdge(v, w);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void initializeEmptyGraph(int numVertices) {
        // initialize instance variables
        this.numVertices = numVertices;
        this.numEdges = 0;

        // construct array of bags (using ugly cast due to generics in Bag)
        adj = (BagList<Integer>[]) new BagList[numVertices];

        // construct all the individual (initially empty) bags
        for (int v = 0; v < numVertices; v++) {
            adj[v] = new BagList<Integer>();
        }
    }

    public void addEdge(int v, int w) {
        numEdges++;
        adj[v].add(w);
        adj[w].add(v);
    }

    public void removeEdge(int v, int w) {
    	numEdges--;
    	adj[v].remove(w);
    	adj[w].remove(v);
    	
    }
    
    // produce something to iterate through vertices adjacent to v
    public Iterable<Integer> adj(int v) {
        return adj[v];  //remember bags are iterable
    }

    public int numVertices() {
        return this.numVertices;
    }

    public int numEdges() {
        return this.numEdges;
    }

    public int degree(int v) {
        return adj[v].size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");
        sb.append(numVertices + " vertices, " + numEdges + " edges " + NEWLINE);
        for (int v = 0; v < numVertices; v++) {
            sb.append(v + ": ");
            for (int w : adj[v]) {
                sb.append(w + " ");
            }
            sb.append(NEWLINE);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
       
    }

}