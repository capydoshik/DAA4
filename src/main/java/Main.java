import graph.utils.GraphLoader;
import graph.scc.TarjanSCC;
import graph.topo.TopoSort;
import graph.dagsp.DAGShortestPath;
import graph.dagsp.DAGLongestPath;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        var data = GraphLoader.load("src/main/resources/tasks.json");
        var adj = GraphLoader.buildAdj(data);
        var adjW = GraphLoader.buildWeightedAdj(data);

        TarjanSCC scc = new TarjanSCC(data.n, adj);
        var sccs = scc.getSCCs();
        System.out.println("SCCs: " + sccs);

        var topo = TopoSort.kahn(adj, data.n);
        System.out.println("Topo order: " + topo);

        var distS = DAGShortestPath.shortestPath(adjW, data.source, data.n, topo);
        System.out.println("Shortest from " + data.source + ": " + Arrays.toString(distS));

        var distL = DAGLongestPath.longestPath(adjW, data.source, data.n, topo);
        System.out.println("Longest from " + data.source + ": " + Arrays.toString(distL));
    }
}
