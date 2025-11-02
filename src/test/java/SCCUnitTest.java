import org.junit.jupiter.api.Test;
import graph.scc.TarjanSCC;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class SCCUnitTest {

    @Test
    void testSimpleCycle() {
        // Graph: 0 -> 1 -> 2 -> 0 (one SCC)
        List<List<Integer>> adj = List.of(
                List.of(1),
                List.of(2),
                List.of(0)
        );

        TarjanSCC scc = new TarjanSCC(3, adj);
        var result = scc.getSCCs();

        // Expect 1 strongly connected component with 3 vertices
        assertEquals(1, result.size());
        assertEquals(3, result.get(0).size());
    }

    @Test
    void testTwoComponents() {
        // Graph: (0->1->2->0) and (3->4)
        List<List<Integer>> adj = List.of(
                List.of(1),
                List.of(2),
                List.of(0),
                List.of(4),
                List.of()
        );

        TarjanSCC scc = new TarjanSCC(5, adj);
        var result = scc.getSCCs();

        // Expect 2 SCCs
        assertTrue(result.stream().anyMatch(s -> s.containsAll(List.of(0, 1, 2))));
        assertTrue(result.stream().anyMatch(s -> s.containsAll(List.of(3))));
    }
}
