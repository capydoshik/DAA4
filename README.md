# Smart City / Smart Campus Scheduling (Assignment 4)

**Course Goal:**  
Integrate two graph theory topics in one practical case — **Strongly Connected Components (SCC)** and **Shortest Paths in Directed Acyclic Graphs (DAGs)** — applied to smart city task scheduling.

---

## Project Overview

This project implements:
1. **SCC detection (Tarjan’s Algorithm)**  
   → Identifies cyclic dependencies in the task graph.
2. **Condensation Graph (DAG)**  
   → Each SCC becomes a single super-node.
3. **Topological Sort (Kahn’s Algorithm)**  
   → Provides an execution order of tasks/components.
4. **Shortest & Longest Paths in DAG**  
   → For task planning (minimum total duration or critical path).

---

## Project Structure

Algorithms Used

🔹 Strongly Connected Components (Tarjan)

Time Complexity: O(V + E)

Detects cycles and groups mutually dependent tasks.

🔹 Condensation Graph

Each SCC is replaced by a single node.

Produces a DAG.

🔹 Topological Sort (Kahn’s Algorithm)

Orders DAG nodes so that all dependencies are satisfied.

🔹 Shortest Path in DAG

Dynamic programming along topological order.

🔹 Longest Path in DAG

Inverse of shortest path (max-DP); used to find the critical path.

| Algorithm        | Metric      | Description                         |
| ---------------- | ----------- | ----------------------------------- |
| SCC (Tarjan)     | DFS calls   | Number of recursive DFS invocations |
| Topo Sort (Kahn) | Queue ops   | Push/pop operations                 |
| DAG-SP           | Relaxations | Edge relaxations performed          |
| DAG-LP           | DP updates  | Longest-path updates                |

## Analysis

**SCC Detection:** Linear in graph size; performs well even on dense graphs up to 50 nodes.

**Topological Sorting:** Dominated by indegree computation; stable performance.

**Shortest Paths:** DP-based, very efficient (O(V + E)).

**Longest Paths:** Reveals the “critical chain” — longest dependency route in task graph.

## Conclusions

**Use SCC + Condensation** to remove cycles before scheduling.

**Topological ordering** ensures correct dependency execution.

**Shortest path** = optimal plan (minimum total duration).

**Longest path** = critical path (bottleneck sequence).

Tarjan’s algorithm performs best for dynamic graphs with many cycles.