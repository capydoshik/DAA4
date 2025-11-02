package graph.dagsp;

import graph.utils.Metrics;
import java.util.*;

public class DAGLongestPath {

    public static double[] longestPath(List<List<int[]>> g, int src, int V, List<Integer> topo, Metrics metrics) {
        if (metrics != null) metrics.start();

        double[] dist = new double[V];
        Arrays.fill(dist, Double.NEGATIVE_INFINITY);
        dist[src] = 0;

        for (int u : topo) {
            if (dist[u] != Double.NEGATIVE_INFINITY) {
                for (int[] e : g.get(u)) {
                    if (metrics != null) metrics.increment("relaxations");
                    int v = e[0];
                    double w = e[1];
                    if (dist[v] < dist[u] + w) {
                        dist[v] = dist[u] + w;
                    }
                }
            }
        }

        if (metrics != null) {
            metrics.stop();
            metrics.printSummary();
        }

        return dist;
    }

    public static Map<String, Object> longestPathWithParent(List<List<int[]>> g, int src, int V, List<Integer> topo, Metrics metrics) {
        if (metrics != null) metrics.start();

        double[] dist = new double[V];
        int[] parent = new int[V];
        Arrays.fill(dist, Double.NEGATIVE_INFINITY);
        Arrays.fill(parent, -1);
        dist[src] = 0;

        for (int u : topo) {
            if (dist[u] != Double.NEGATIVE_INFINITY) {
                for (int[] e : g.get(u)) {
                    if (metrics != null) metrics.increment("relaxations");
                    int v = e[0];
                    double w = e[1];
                    if (dist[v] < dist[u] + w) {
                        dist[v] = dist[u] + w;
                        parent[v] = u;
                    }
                }
            }
        }

        if (metrics != null) {
            metrics.stop();
            metrics.printSummary();
        }

        Map<String, Object> result = new HashMap<>();
        result.put("dist", dist);
        result.put("parent", parent);
        return result;
    }

    public static List<Integer> reconstructPath(int[] parent, int target) {
        List<Integer> path = new ArrayList<>();
        for (int v = target; v != -1; v = parent[v]) {
            path.add(v);
        }
        Collections.reverse(path);
        return path;
    }
}
