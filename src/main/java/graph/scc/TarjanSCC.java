package graph.scc;

import graph.utils.Metrics;
import java.util.*;

public class TarjanSCC {

    private final int V;
    private final List<List<Integer>> adj;
    private final int[] low, disc;
    private final boolean[] onStack;
    private final Deque<Integer> stack;
    private final List<List<Integer>> sccList;
    private int time = 0;
    private final Metrics metrics;

    public TarjanSCC(int V, List<List<Integer>> adj, Metrics metrics) {
        this.V = V;
        this.adj = adj;
        this.metrics = metrics;
        this.low = new int[V];
        this.disc = new int[V];
        this.onStack = new boolean[V];
        this.stack = new ArrayDeque<>();
        this.sccList = new ArrayList<>();
        Arrays.fill(disc, -1);
    }

    private void dfs(int u) {
        if (metrics != null) metrics.increment("dfs_calls");

        disc[u] = low[u] = ++time;
        stack.push(u);
        onStack[u] = true;

        for (int v : adj.get(u)) {
            if (metrics != null) metrics.increment("dfs_edges");

            if (disc[v] == -1) {
                dfs(v);
                low[u] = Math.min(low[u], low[v]);
            } else if (onStack[v]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }

        if (low[u] == disc[u]) {
            List<Integer> scc = new ArrayList<>();
            int node;
            do {
                node = stack.pop();
                onStack[node] = false;
                scc.add(node);
            } while (node != u);

            sccList.add(scc);
            if (metrics != null) metrics.increment("scc_found");
        }
    }

    public List<List<Integer>> getSCCs() {
        if (metrics != null) metrics.start();

        for (int i = 0; i < V; i++)
            if (disc[i] == -1)
                dfs(i);

        if (metrics != null) {
            metrics.stop();
            metrics.printSummary();
        }

        return sccList;
    }
}
