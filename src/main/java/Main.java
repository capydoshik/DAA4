import graph.utils.GraphLoader;
import graph.utils.SimpleMetrics;
import graph.scc.TarjanSCC;
import graph.topo.TopoSort;
import graph.dagsp.DAGShortestPath;
import graph.dagsp.DAGLongestPath;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        var data = GraphLoader.load("src/main/resources/data/tasks.json");
        var adj = GraphLoader.buildAdj(data);
        var adjW = GraphLoader.buildWeightedAdj(data);

        SimpleMetrics metrics = new SimpleMetrics();

        System.out.println("========= STRONGLY CONNECTED COMPONENTS (Tarjan) =========");
        TarjanSCC scc = new TarjanSCC(data.n, adj, metrics);
        var sccs = scc.getSCCs();
        System.out.println("SCCs found: " + sccs.size());
        System.out.println("Components: " + sccs);
        System.out.println();

        System.out.println("========= TOPOLOGICAL SORT (Kahn) =========");
        var topo = TopoSort.kahn(adj, data.n, metrics);
        System.out.println("Topological order: " + topo);
        System.out.println();

        System.out.println("========= DAG SHORTEST PATH =========");
        var distS = DAGShortestPath.shortestPath(adjW, data.source, data.n, topo, metrics);
        System.out.println("Shortest distances from " + data.source + ": " + Arrays.toString(distS));
        System.out.println();

        System.out.println("========= DAG LONGEST PATH =========");
        var distL = DAGLongestPath.longestPath(adjW, data.source, data.n, topo, metrics);
        System.out.println("Longest distances from " + data.source + ": " + Arrays.toString(distL));
        System.out.println();
    }
}
