/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.networkflow.networkflowalgorithm;

import java.io.IOException;

/**
 *
 * @author ADMIN
 */
public class NetworkFlowAlgorithm {
    public static void main(String[] args) throws IOException {
        FlowNetwork network = new FlowNetwork(0);
        network.readFromFile(""); // Filename will be prompted
        int maxFlow = network.maxFlow(0, network.getNumNodes() - 1);
        System.out.println("Maximum Flow: " + maxFlow);
    }
}