package graph.utils;

public interface Metrics {
    void start();
    void stop();
    void reset();

    void increment(String counterName);
    void add(String counterName, long value);

    long get(String counterName);
    long getElapsedTime();

    void printSummary();
}
