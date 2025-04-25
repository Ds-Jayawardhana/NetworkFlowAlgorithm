/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.networkflow.networkflowalgorithm;

/**
 *
 * @author ADMIN
 */
class Edge {
    private int from;
    private int to;
    private int capacity;
    private int flow;

    Edge(int from, int to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
    }

    int getTo() {
        return to;
    }

    int getCapacity() {
        return capacity;
    }

    int getFlow() {
        return flow;
    }

    void addFlow(int amount) {
        flow += amount;
    }
}
