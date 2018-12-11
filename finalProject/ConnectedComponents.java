package finalProject;
public class ConnectedComponents {

    private boolean visited[];
    private int[] id;
    private int numComponents;

    public ConnectedComponents(Graph g) {
        visited = new boolean[g.numVertices()];
        id = new int[g.numVertices()];
        for (int v = 0; v < g.numVertices(); v++) {
            if (! visited[v]) {
                dfs(g,v);
                numComponents++;
            }
        }
    }

    private void dfs(Graph g, int v) {
        visited[v] = true;
        id[v] = numComponents;
        for (int w : g.adj(v)) {
            if (! visited[w]) {
                dfs(g,w);
            }
        }
    }

    public int count() {
        return numComponents;
    }

    public int id(int v) {
        return id[v];
    }

    public static void main(String[] args) {

        Graph g = new Graph(13);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 5);
        g.addEdge(0, 6);
        g.addEdge(3, 4);
        g.addEdge(3, 5);
        g.addEdge(4, 5);
        g.addEdge(4, 6);
        g.addEdge(7, 8);
        g.addEdge(9, 10);
        g.addEdge(9, 11);
        g.addEdge(9, 12);
        g.addEdge(11, 12);

            System.out.println(g);

        ConnectedComponents cc = new ConnectedComponents(g);

        System.out.println("  v  | id[v]");
        System.out.println("-----|------");
        for (int v = 0; v < g.numVertices(); v++) {
            System.out.println((v < 10 ? "  " : " ") + v + " " + " |  " + cc.id(v));
        }
    }
}
