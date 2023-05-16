package com.lombardinternational.technicaltest.algo.utils;

import com.lombardinternational.technicaltest.algo.graph.GraphNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class TreeGenerator<T> {

    private List<GraphNode<T>> allNodes;
    private GraphNode<T> root;

    public T getMaxValueGenerated(Comparator<T> comparator) {
        return allNodes.stream().map(GraphNode::getValue).max(comparator).get();
    }

    public T getMinValueGenerated(Comparator<T> comparator) {
        return allNodes.stream().map(GraphNode::getValue).min(comparator).get();
    }

    public GraphNode<T> generateTree(Function<Integer, T> valueGenerator, int nbNodes) {
        root = new GraphNode<>(0, valueGenerator.apply(0));
        allNodes=new ArrayList<>(nbNodes);
        allNodes.add(root);
        for (int i = 1; i < nbNodes; ++i) {
            GraphNode<T> node = new GraphNode<>(i, valueGenerator.apply(i));
            allNodes.get(new Random().nextInt(0, allNodes.size())).addChild(node);
            allNodes.add(node);
        }
        return root;
    }


}
