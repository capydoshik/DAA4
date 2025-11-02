package graph.utils;

import com.google.gson.*;
import java.io.FileReader;
import java.util.*;

public class GraphLoader {
    public static class Edge { public int u, v, w; }
    public static class GraphData {
        public boolean directed;
        public int n;
        public List<Edge> edges;
        public int source;
        public String weight_model;
    }

    public static GraphData load(String file) throws Exception {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, GraphData.class);
        }
    }

    public static List<List<Integer>> buildAdj(GraphData data) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < data.n; i++) adj.add(new ArrayList<>());
        for (Edge e : data.edges) adj.get(e.u).add(e.v);
        return adj;
    }

    public static List<List<int[]>> buildWeightedAdj(GraphData data) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < data.n; i++) adj.add(new ArrayList<>());
        for (Edge e : data.edges) adj.get(e.u).add(new int[]{e.v, e.w});
        return adj;
    }
}
