/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.networkflow.networkflowalgorithm;

/**
 *
 * @author ADMIN
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class FlowNetwork {
    private int numNodes;

    public int getNumNodes() {
        return numNodes;
    }
    private List<List<Edge>> graph;

    FlowNetwork(int numNodes) {
        this.numNodes = numNodes;
        graph = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            graph.add(new ArrayList<>());
        }
    }

    void addEdge(int from, int to, int capacity) {
        graph.get(from).add(new Edge(from, to, capacity));
        graph.get(to).add(new Edge(to, from, 0));
    }

    void readFromFile(String filename) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the file name (e.g., network.txt): ");
        filename = scanner.nextLine();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        numNodes = Integer.parseInt(reader.readLine());
        graph = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            graph.add(new ArrayList<>());
        }
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            int from = Integer.parseInt(parts[0]);
            int to = Integer.parseInt(parts[1]);
            int capacity = Integer.parseInt(parts[2]);
            addEdge(from, to, capacity);
        }
        reader.close();
    }

    int maxFlow(int source, int target) {
        int totalFlow = 0;
        while (true) {
            int[] path = findPath(source, target);
            if (path[target] == -1) break;
            int bottleneck = getBottleneck(source, target, path);
            updateFlow(source, target, path, bottleneck);
            totalFlow += bottleneck;
            System.out.println("Added flow: " + bottleneck);
        }
        return totalFlow;
    }

    private int[] findPath(int source, int target) {
        int[] parent = new int[numNodes];
        Arrays.fill(parent, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        parent[source] = source;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (Edge edge : graph.get(current)) {
                if (parent[edge.getTo()] == -1 && edge.getCapacity() - edge.getFlow() > 0) {
                    parent[edge.getTo()] = current;
                    if (edge.getTo() == target) return parent;
                    queue.add(edge.getTo());
                }
            }
        }
        return parent;
    }

    private int getBottleneck(int source, int target, int[] parent) {
        int bottleneck = Integer.MAX_VALUE;
        int current = target;
        while (current != source) {
            int prev = parent[current];
            for (Edge edge : graph.get(prev)) {
                if (edge.getTo() == current) {
                    bottleneck = Math.min(bottleneck, edge.getCapacity() - edge.getFlow());
                    break;
                }
            }
            current = prev;
        }
        return bottleneck == Integer.MAX_VALUE ? 0 : bottleneck;
    }

    private void updateFlow(int source, int target, int[] parent, int bottleneck) {
        int current = target;
        while (current != source) {
            int prev = parent[current];
            for (Edge edge : graph.get(prev)) {
                if (edge.getTo() == current) {
                    edge.addFlow(bottleneck);
                    break;
                }
            }
            for (Edge reverse : graph.get(current)) {
                if (reverse.getTo() == prev) {
                    reverse.addFlow(-bottleneck);
                    break;
                }
            }
            current = prev;
        }
    }
}