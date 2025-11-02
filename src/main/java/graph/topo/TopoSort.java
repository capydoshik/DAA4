package graph.topo;

import graph.utils.Metrics;
import java.util.*;

public class TopoSort {

    public static List<Integer> kahn(List<List<Integer>> adj, int V, Metrics metrics) {
        if (metrics != null) metrics.start();

        int[] indeg = new int[V];
        for (List<Integer> list : adj)
            for (int v : list) indeg[v]++;

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < V; i++) {
            if (indeg[i] == 0) {
                q.add(i);
                if (metrics != null) metrics.increment("queue_pushes");
            }
        }

        List<Integer> order = new ArrayList<>();

        while (!q.isEmpty()) {
            int u = q.poll();
            if (metrics != null) metrics.increment("queue_pops");

            order.add(u);
            for (int v : adj.get(u)) {
                indeg[v]--;
                if (metrics != null) metrics.increment("indegree_updates");

                if (indeg[v] == 0) {
                    q.add(v);
                    if (metrics != null) metrics.increment("queue_pushes");
                }
            }
        }

        if (metrics != null) {
            metrics.stop();
            metrics.printSummary();
        }

        return order;
    }
}
