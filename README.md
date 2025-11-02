Assignment 4 – Graph Algorithms and Metrics
Course: Design and Analysis of Algorithms

Ayazbaev Daniyar


Overview

This project implements and analyzes several key graph algorithms using Java and Maven.
It focuses on both algorithmic correctness and instrumentation through a shared Metrics interface,
which measures execution time and operation counts (e.g., DFS calls, relaxations, queue pushes).

Algorithms Implemented

Tarjan’s SCC – Strongly Connected Components detection

Kahn’s Topological Sort – DAG ordering using BFS-based approach

DAG Shortest Path – Dynamic programming using topological order

DAG Longest Path – Critical path analysis in DAGs

Each algorithm includes instrumentation for:

Operation counters (DFS visits, relaxations, queue operations)

Runtime measurement (System.nanoTime())


Project Structure

DAA4/
 ├── src/
 │    ├── main/
 │    │    ├── java/graph/
 │    │    │    ├── scc/TarjanSCC.java
 │    │    │    ├── topo/TopoSort.java
 │    │    │    ├── dagsp/DAGShortestPath.java
 │    │    │    ├── dagsp/DAGLongestPath.java
 │    │    │    └── utils/
 │    │    │         ├── GraphLoader.java
 │    │    │         ├── GraphGenerator.java
 │    │    │         ├── Metrics.java
 │    │    │         └── SimpleMetrics.java
 │    └── test/java/
 │         ├── SCCUnitTest.java
 │         ├── TopoUnitTest.java
 │         └── DAGSPUnitTest.java
 ├── data/
 │    ├── small1.json ... large3.json
 ├── pom.xml
 └── README.md


 Instrumentation (Metrics Interface)

 | Algorithm             | Metrics Tracked                                  |
| --------------------- | ------------------------------------------------ |
| **TarjanSCC**         | `dfs_calls`, `dfs_edges`, `scc_found`            |
| **TopoSort (Kahn)**   | `queue_pushes`, `queue_pops`, `indegree_updates` |
| **DAG Shortest Path** | `relaxations`                                    |
| **DAG Longest Path**  | `relaxations`                                    |


Sample Output

===== Metrics Summary =====
dfs_calls            : 8
dfs_edges            : 7
scc_found            : 3
Execution time: 0.094 ms
============================


Testing (JUnit 5)

| Test Class           | Focus            | Example Check                  |
| -------------------- | ---------------- | ------------------------------ |
| `SCCUnitTest.java`   | Tarjan’s SCC     | Detects SCC counts and groups  |
| `TopoUnitTest.java`  | Kahn’s Topo Sort | Validates ordering constraints |
| `DAGSPUnitTest.java` | Shortest Path    | Verifies distance correctness  |


Algorithm Overview

| Algorithm                   | Complexity | Description                              |
| --------------------------- | ---------- | ---------------------------------------- |
| **Tarjan’s SCC**            | O(V + E)   | Uses DFS + low-link values to find SCCs  |
| **Kahn’s Topological Sort** | O(V + E)   | BFS-based, removes nodes with indegree=0 |
| **DAG Shortest Path**       | O(V + E)   | Dynamic programming via topo order       |
| **DAG Longest Path**        | O(V + E)   | Finds critical path in DAG               |


Datasets Summary

| Category | File | Nodes | Edges | Type | Density | Notes |
|-----------|------|--------|--------|--------|----------|
| Small | small1.json | 6 | 5 | DAG | Sparse | Simple acyclic |
| Small | small2.json | 8 | 8 | Cyclic | Medium | 1 cycle |
| Small | small3.json | 10 | 11 | Mixed | Dense | Cross edges |
| Medium | medium1.json | 12 | 11 | Mixed | Sparse | Multiple SCCs |
| Medium | medium2.json | 15 | 15 | Cyclic | Dense | 5 SCCs |
| Medium | medium3.json | 18 | 16 | Mixed | Medium | Partial DAG |
| Large | large1.json | 25 | 15 | DAG | Sparse | Long chain |
| Large | large2.json | 30 | 30 | Cyclic | Dense | Multi-SCC |
| Large | large3.json | 45 | 12 | DAG | Sparse | Performance path |


small1.json

{
  "directed": true,
  "n": 6,
  "edges": [
    {"u": 0, "v": 1, "w": 2},
    {"u": 1, "v": 2, "w": 3},
    {"u": 2, "v": 3, "w": 4},
    {"u": 3, "v": 4, "w": 2},
    {"u": 4, "v": 5, "w": 1}
  ],
  "source": 0,
  "weight_model": "edge"
}


Execution (Main Program)

mvn compile exec:java -Dexec.mainClass="Main"


Example Output

========= STRONGLY CONNECTED COMPONENTS (Tarjan) =========
SCCs found: 3
Components: [[1,2,3],[0],[4,5,6,7]]

========= TOPOLOGICAL SORT (Kahn) =========
Topological order: [4,5,6,7,0,1,2,3]

========= DAG SHORTEST PATH =========
Shortest distances: [∞, ∞, ∞, ∞, 0.0, 2.0, 7.0, 8.0]

========= DAG LONGEST PATH =========
Longest distances: [NaN, NaN, NaN, NaN, 0.0, 2.0, 7.0, 8.0]


Reproducibility

Environment: Java 17, Maven 3.9+, JUnit 5

Dependencies:

com.google.code.gson:gson:2.10.1

org.junit.jupiter:junit-jupiter-api:5.9.3

org.junit.jupiter:junit-jupiter-engine:5.9.3



