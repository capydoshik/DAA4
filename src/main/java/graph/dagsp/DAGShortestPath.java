package graph.dagsp;

import graph.utils.Metrics;
import java.util.*;

public class DAGShortestPath {

    public static double[] shortestPath(List<List<int[]>> g, int src, int V, List<Integer> topo, Metrics metrics) {
        if (metrics != null) metrics.start();

        double[] dist = new double[V];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[src] = 0;

        for (int u : topo) {
            if (dist[u] != Double.POSITIVE_INFINITY) {
                for (int[] e : g.get(u)) {
                    if (metrics != null) metrics.increment("relaxations");
                    int v = e[0];
                    double w = e[1];
                    if (dist[v] > dist[u] + w)
                        dist[v] = dist[u] + w;
                }
            }
        }

        if (metrics != null) {
            metrics.stop();
            metrics.printSummary();
        }

        return dist;
    }
}
