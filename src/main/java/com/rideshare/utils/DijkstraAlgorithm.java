package com.rideshare.utils;

import java.util.*;

public class DijkstraAlgorithm {

    public static Map<String, Integer> dijkstra(Map<String, Map<String, Integer>> graph, String start) {
        Map<String, Integer> distances = new HashMap<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(pair -> pair.distance));
        Set<String> visited = new HashSet<>();

        // Initialize distances to Infinity
        graph.keySet().forEach(node -> distances.put(node, Integer.MAX_VALUE));
        distances.put(start, 0);

        // Priority Queue with custom Pair class
        pq.offer(new Pair(start, 0));

        while (!pq.isEmpty()) {
            Pair currentPair = pq.poll();
            String current = currentPair.node;

            if (!visited.add(current)) continue;

            for (Map.Entry<String, Integer> neighbor : graph.getOrDefault(current, new HashMap<>()).entrySet()) {
                int newDist = distances.get(current) + neighbor.getValue();
                if (newDist < distances.get(neighbor.getKey())) {
                    distances.put(neighbor.getKey(), newDist);
                    pq.offer(new Pair(neighbor.getKey(), newDist));
                }
            }
        }
        return distances;
    }

    // Custom class to store node and its distance
    static class Pair {
        String node;
        int distance;

        Pair(String node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }
}
