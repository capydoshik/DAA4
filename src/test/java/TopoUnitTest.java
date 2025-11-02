import org.junit.jupiter.api.Test;
import graph.topo.TopoSort;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class TopoUnitTest {

    @Test
    void testSimpleDAG() {
        // DAG: 0 -> 1 -> 2
        List<List<Integer>> adj = List.of(
                List.of(1),
                List.of(2),
                List.of()
        );

        List<Integer> order = TopoSort.kahn(adj, 3);

        // Must contain all nodes
        assertEquals(3, order.size());

        // Ensure valid order: 0 before 1, 1 before 2
        assertTrue(order.indexOf(0) < order.indexOf(1));
        assertTrue(order.indexOf(1) < order.indexOf(2));
    }

    @Test
    void testBranchingDAG() {
        // DAG: 0 -> 1, 0 -> 2, 1 -> 3, 2 -> 3
        List<List<Integer>> adj = List.of(
                List.of(1, 2),
                List.of(3),
                List.of(3),
                List.of()
        );

        List<Integer> order = TopoSort.kahn(adj, 4);

        // 0 must come before 1 and 2
        assertTrue(order.indexOf(0) < order.indexOf(1));
        assertTrue(order.indexOf(0) < order.indexOf(2));
        // 1 and 2 before 3
        assertTrue(order.indexOf(1) < order.indexOf(3));
        assertTrue(order.indexOf(2) < order.indexOf(3));
    }
}
