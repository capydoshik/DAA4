import org.junit.jupiter.api.Test;
import graph.dagsp.DAGShortestPath;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class DAGSPUnitTest {
    @Test
    void testShortestPath() {
        List<List<int[]>> g = new ArrayList<>();
        for (int i = 0; i < 4; i++) g.add(new ArrayList<>());
        g.get(0).add(new int[]{1, 2});
        g.get(0).add(new int[]{2, 4});
        g.get(1).add(new int[]{2, 1});
        g.get(2).add(new int[]{3, 3});

        List<Integer> topo = List.of(0, 1, 2, 3);
        double[] dist = DAGShortestPath.shortestPath(g, 0, 4, topo);

        assertEquals(0.0, dist[0]);
        assertEquals(2.0, dist[1]);
        assertEquals(3.0, dist[2]);
        assertEquals(6.0, dist[3]);
    }
}
