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
    private Bag<Integer>[] adj;

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
        adj = (Bag<Integer>[]) new Bag[numVertices];

        // construct all the individual (initially empty) bags
        for (int v = 0; v < numVertices; v++) {
            adj[v] = new BagArray<Integer>();
        }
    }

    public void addEdge(int v, int w) {
        numEdges++;
        adj[v].add(w);
        adj[w].add(v);
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
        Graph g = new Graph(6);
        g.addEdge(0, 2);
        g.addEdge(0, 1);
        g.addEdge(3, 5);
        g.addEdge(0, 5);
        g.addEdge(1, 2);
        g.addEdge(3, 4);
        g.addEdge(2, 3);
        g.addEdge(2, 4);

        System.out.println(g);

        if (args.length > 0) {
           System.out.println();
           Graph g2 = new Graph(args[0]);
           System.out.println(g2);
        }
    }

}
