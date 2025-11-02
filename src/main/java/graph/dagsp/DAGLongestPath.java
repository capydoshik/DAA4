package graph.dagsp;
import java.util.*;

public class DAGLongestPath {
    public static double[] longestPath(List<List<int[]>> g, int src, int V, List<Integer> topo) {
        double[] dist = new double[V];
        Arrays.fill(dist, Double.NEGATIVE_INFINITY);
        dist[src] = 0;
        for (int u : topo)
            if (dist[u] != Double.NEGATIVE_INFINITY)
                for (int[] e : g.get(u))
                    if (dist[e[0]] < dist[u] + e[1])
                        dist[e[0]] = dist[u] + e[1];
        return dist;
    }
}
