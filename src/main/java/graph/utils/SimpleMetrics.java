package graph.utils;

import java.util.*;

public class SimpleMetrics implements Metrics {
    private final Map<String, Long> counters = new HashMap<>();
    private long startTime;
    private long endTime;

    @Override
    public void start() {
        startTime = System.nanoTime();
    }

    @Override
    public void stop() {
        endTime = System.nanoTime();
    }

    @Override
    public void reset() {
        counters.clear();
        startTime = 0;
        endTime = 0;
    }

    @Override
    public void increment(String name) {
        counters.put(name, counters.getOrDefault(name, 0L) + 1);
    }

    @Override
    public void add(String name, long value) {
        counters.put(name, counters.getOrDefault(name, 0L) + value);
    }

    @Override
    public long get(String name) {
        return counters.getOrDefault(name, 0L);
    }

    @Override
    public long getElapsedTime() {
        return endTime - startTime;
    }

    @Override
    public void printSummary() {
        System.out.println("===== Metrics Summary =====");
        for (var entry : counters.entrySet()) {
            System.out.printf("%-20s : %d%n", entry.getKey(), entry.getValue());
        }
        System.out.printf("Execution time: %.3f ms%n", getElapsedTime() / 1_000_000.0);
        System.out.println("============================");
    }
}
