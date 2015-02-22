package org.zezutom.algorithms.java.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Karger's algorithm - a randomized contraction algorithm for finding
 * a minimum cut in an undirected graph.
 * (http://en.wikipedia.org/wiki/Karger%27s_algorithm)
 *
 * This is a "naive" implementation as a new graph is created from
 * the old one in each iteration.
 *
 *  Run-time complexity: O(n2)
 */
public class MinCutCalculator {

    private boolean verbose;

    public MinCutCalculator() {}

    public MinCutCalculator(boolean verbose) {
        this.verbose = verbose;
    }

    static class Pair<A, B> {
        A fst;
        B snd;

        Pair(A fst, B snd) {
            this.fst = fst;
            this.snd = snd;
        }
    }
    static class Graph {

        List<Set<Integer>> vertices = new ArrayList<>();

        List<Pair<Integer, Integer>> edges = new ArrayList<>();

        Graph() {}

        Graph(Graph graph) {
            for (Set<Integer> vertex : graph.vertices) {
                Set<Integer> newVertex = new HashSet<>();
                for (Integer v : vertex) newVertex.add(v);
                this.vertices.add(newVertex);
            }

            for (Pair<Integer, Integer> edge : graph.edges) {
                Pair<Integer, Integer> newEdge = new Pair<>(edge.fst, edge.snd);
                this.edges.add(newEdge);
            }
        }

        void addVertices(int ... vertices) {
            for (int vertex : vertices) addVertex(vertex);
        }

        void addVertex(int vertex) {
            Set<Integer> set = new HashSet<>();
            set.add(vertex);
            vertices.add(set);
        }

        void addEdge(int from, int to) {
            edges.add(new Pair<>(from, to));
        }
    }

    /**
     * Calculates the minimum cut using Karger's algorithm
     *
     * @param graphTxt  graph textual definition as an absolute file path
     * @return  the minimum cut in the graph as a result of a large(r) number of iterations
     */
    public int calculateMinGraph(String graphTxt) {
        return calculateMinCut(loadGraph(graphTxt));
    }

    /**
     * Calculates the minimum cut using Karger's algorithm
     *
     * @param graph
     * @return  the minimum cut in the graph as a result of a large(r) number of iterations
     */
    public int calculateMinCut(Graph graph) {
        int iter = 100, printStep = 10;

        if (verbose) System.out.println(String.format("Searching for min cut through %d loops", iter));

        Random rand = new Random();
        int minCut = contractVerticesAndCountCuts(graph, rand);
        if (verbose) {
            System.out.println(String.format("Found %d as a suitable candidate\nSearching further...", minCut));
        }
        for (int i = 1; i <= iter; i++) {
            rand.setSeed(i);
            if (verbose & (i % printStep == 0))
                System.out.println(String.format("Loop %d\tmin cut: %d", i, minCut));
            int cuts = contractVerticesAndCountCuts(graph, rand);
            if (minCut > cuts) {
                minCut = cuts;
                if (verbose)
                    System.out.println(String.format("Loop %d found a new minimum of %d cuts", i, minCut));
            }
        }
        return minCut;
    }

    private int countEdges(Graph graph) {
        int count = 0;
        final Set<Integer> head = graph.vertices.get(0);
        for (Pair<Integer, Integer> edge : graph.edges) {
            // Only count the edges between two standalone vertices (X -- n edges -- Y)
            // Vertices which have already been contracted do not count!
            if (head.contains(edge.fst) & !(head.contains(edge.snd))) count++;
        }
        return count;
    }

    private int indexOf(Graph graph, int vertex) {
        for (int i = 0; i < graph.vertices.size(); i++) {
            if (graph.vertices.get(i).contains(vertex))
                return i;
        }
        throw new RuntimeException(String.format("No such vertex %d!", vertex));
    }

    private int contractVerticesAndCountCuts(Graph graph, Random rand) {
        if (graph.vertices.size() <= 2) return countEdges(graph);

        // Preserve the original graph by creating a new one
        Graph newGraph = new Graph(graph);
        List<Set<Integer>> vertices = newGraph.vertices;
        List<Pair<Integer, Integer>> edges = newGraph.edges;

        // Take a random edge (v1, v2)
        Pair<Integer, Integer> edge = edges.get(rand.nextInt(edges.size()));
        int v1_index = indexOf(newGraph, edge.fst), v2_index = indexOf(newGraph, edge.snd);

        // Contract v2 into v1
        Set<Integer> contractedVertex = vertices.get(v1_index);
        contractedVertex.addAll(vertices.get(v2_index));

        // Remove the contracted vertex
        vertices.remove(v2_index);

        // Filter out any loops
        Iterator<Pair<Integer, Integer>> edgesIterator = edges.iterator();
        while (edgesIterator.hasNext()) {
            Pair<Integer, Integer> e = edgesIterator.next();
            if (contractedVertex.contains(e.fst) && contractedVertex.contains(e.snd))
                edgesIterator.remove();
        }

        // iterate
        return contractVerticesAndCountCuts(newGraph, rand);
    }

    /**
     * Parses the graph definition and turns it into the actual Graph object
     *
     * @param graphTxt  graph textual definition as an absolute file path
     * @return          a new Graph instance
     */
    private Graph loadGraph(String graphTxt) {
        try(BufferedReader br = new BufferedReader(new FileReader(graphTxt))) {
            Graph graph = new Graph();
            while (br.ready()) {
                List<Integer> vertices = new ArrayList<>();
                for (String v : br.readLine().split("[^0-9]+")) {
                    vertices.add(Integer.valueOf(v));
                }
                final Integer vertex = vertices.get(0);
                for (Integer target : vertices.subList(1, vertices.size())) {
                    graph.addEdge(vertex, target);
                }
                graph.addVertex(vertex);
            }
            return graph;
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read from " + graphTxt);
        }
    }
}
