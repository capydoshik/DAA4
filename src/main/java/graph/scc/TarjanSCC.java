package graph.scc;
import java.util.*;

public class TarjanSCC {
    private int V, time;
    private List<List<Integer>> adj;
    private int[] low, disc;
    private boolean[] onStack;
    private Deque<Integer> stack;
    private List<List<Integer>> sccList;

    public TarjanSCC(int V, List<List<Integer>> adj) {
        this.V = V; this.adj = adj;
        low = new int[V]; disc = new int[V];
        onStack = new boolean[V];
        stack = new ArrayDeque<>();
        sccList = new ArrayList<>();
        Arrays.fill(disc, -1);
    }

    private void dfs(int u) {
        disc[u] = low[u] = ++time;
        stack.push(u); onStack[u] = true;
        for (int v : adj.get(u)) {
            if (disc[v] == -1) { dfs(v); low[u] = Math.min(low[u], low[v]); }
            else if (onStack[v]) low[u] = Math.min(low[u], disc[v]);
        }
        if (low[u] == disc[u]) {
            List<Integer> scc = new ArrayList<>();
            int v;
            do { v = stack.pop(); onStack[v] = false; scc.add(v); } while (v != u);
            sccList.add(scc);
        }
    }

    public List<List<Integer>> getSCCs() {
        for (int i = 0; i < V; i++) if (disc[i] == -1) dfs(i);
        return sccList;
    }
}
