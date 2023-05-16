package com.lombardinternational.technicaltest.algo.graph;

import java.util.LinkedList;
import java.util.List;

public class GraphNode<T> {

    private final int id;
    private T value;
    private List<GraphNode<T>> children = new LinkedList<>();

    public GraphNode(int id, T value) {
        this.id = id;
        this.value = value;
    }

    public GraphNode<T> addChild(GraphNode<T> child) {
        children.add(child);
        return this;
    }

    public T getValue() {
        return value;
    }

    public List<GraphNode<T>> children() {
        return children;
    }

    public int getId() {
        return id;
    }

}
