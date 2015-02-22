package org.zezutom.algorithms.java.graph;

import org.junit.Test;
import org.zezutom.util.java.TestUtil;

import static org.junit.Assert.assertEquals;

public class MinCutCalculatorTest {

    private MinCutCalculator minCutCalc = new MinCutCalculator(true);

    @Test
    public void min_cut_of_a_small_graph() {
        MinCutCalculator.Graph graph = new MinCutCalculator.Graph();
        graph.addVertices(1, 2, 3, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 1);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 1);
        graph.addEdge(3, 2);
        graph.addEdge(3, 4);
        graph.addEdge(4, 2);
        graph.addEdge(4, 3);
        assertEquals(2, minCutCalc.calculateMinCut(graph));
    }

    @Test
    public void min_cut_of_a_larger_graph() {
        String graphDefinition = new TestUtil().getAbsolutePath("kargerMinCut.txt");
        assertEquals(17, minCutCalc.calculateMinGraph(graphDefinition));
    }
}
