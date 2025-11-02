package graph.topo;
import java.util.*;

public class TopoSort {
    public static List<Integer> kahn(List<List<Integer>> adj, int V) {
        int[] indeg = new int[V];
        for (List<Integer> l : adj) for (int v : l) indeg[v]++;
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < V; i++) if (indeg[i] == 0) q.add(i);
        List<Integer> order = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll(); order.add(u);
            for (int v : adj.get(u)) if (--indeg[v] == 0) q.add(v);
        }
        return order;
    }
}
