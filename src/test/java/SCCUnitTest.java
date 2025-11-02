import org.junit.jupiter.api.Test;
import graph.scc.TarjanSCC;
import graph.utils.SimpleMetrics;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class SCCUnitTest {

    @Test
    void testSimpleCycle() {
        List<List<Integer>> adj = new ArrayList<>();
        adj.add(List.of(1));
        adj.add(List.of(2));
        adj.add(List.of(0));

        SimpleMetrics metrics = new SimpleMetrics();
        TarjanSCC scc = new TarjanSCC(3, adj, metrics);
        var result = scc.getSCCs();

        assertEquals(1, result.size());
        assertEquals(3, result.get(0).size());
        assertTrue(result.get(0).containsAll(List.of(0, 1, 2)));
    }

    @Test
    void testTwoComponents() {
        List<List<Integer>> adj = new ArrayList<>();
        adj.add(List.of(1));
        adj.add(List.of(2));
        adj.add(List.of(0));
        adj.add(List.of(4));
        adj.add(List.of());

        SimpleMetrics metrics = new SimpleMetrics();
        TarjanSCC scc = new TarjanSCC(5, adj, metrics);
        var result = scc.getSCCs();

        assertTrue(result.stream().anyMatch(s -> s.containsAll(List.of(0, 1, 2))));
        assertTrue(result.stream().anyMatch(s -> s.containsAll(List.of(3))));
        assertTrue(result.stream().anyMatch(s -> s.containsAll(List.of(4))));
    }

    @Test
    void testNoEdges() {
        List<List<Integer>> adj = List.of(
                List.of(),
                List.of(),
                List.of()
        );

        SimpleMetrics metrics = new SimpleMetrics();
        TarjanSCC scc = new TarjanSCC(3, adj, metrics);
        var result = scc.getSCCs();

        assertEquals(3, result.size());
        for (List<Integer> comp : result) {
            assertEquals(1, comp.size());
        }
    }
}
