import org.junit.jupiter.api.Test;
import graph.topo.TopoSort;
import graph.utils.SimpleMetrics;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class TopoUnitTest {

    @Test
    void testSimpleDAG() {
        List<List<Integer>> adj = List.of(
                List.of(1),
                List.of(2),
                List.of()
        );

        SimpleMetrics metrics = new SimpleMetrics();
        List<Integer> order = TopoSort.kahn(adj, 3, metrics);

        assertEquals(3, order.size());

        assertTrue(order.indexOf(0) < order.indexOf(1));
        assertTrue(order.indexOf(1) < order.indexOf(2));
    }

    @Test
    void testBranchingDAG() {
        List<List<Integer>> adj = List.of(
                List.of(1, 2),
                List.of(3),
                List.of(3),
                List.of()
        );

        SimpleMetrics metrics = new SimpleMetrics();
        List<Integer> order = TopoSort.kahn(adj, 4, metrics);

        assertTrue(order.indexOf(0) < order.indexOf(1));
        assertTrue(order.indexOf(0) < order.indexOf(2));
        assertTrue(order.indexOf(1) < order.indexOf(3));
        assertTrue(order.indexOf(2) < order.indexOf(3));
    }

    @Test
    void testIndependentNodes() {
        List<List<Integer>> adj = List.of(
                List.of(),
                List.of(),
                List.of()
        );

        SimpleMetrics metrics = new SimpleMetrics();
        List<Integer> order = TopoSort.kahn(adj, 3, metrics);

        assertEquals(Set.of(0,1,2), new HashSet<>(order));
        assertEquals(3, order.size());
    }
}
