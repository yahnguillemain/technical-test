package com.lombardinternational.technicaltest.algo;

import com.lombardinternational.technicaltest.algo.utils.TreeGenerator;
import com.lombardinternational.technicaltest.algo.graph.GraphNode;
import com.lombardinternational.technicaltest.algo.graph.TreeVisitor;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.random.RandomGenerator;

import static org.assertj.core.api.Assertions.assertThat;

public class TreeVisitorTest {


    @Test
    public void testGetMinimumValueBasicTree1() {
        int i=0;
        GraphNode<Integer> root=new GraphNode<>(i++,125);
        root.addChild(new GraphNode<>(i++,10));
        root.addChild(new GraphNode<>(i++,15));
        TreeVisitor treeVisitor = new TreeVisitor();
        assertThat(treeVisitor.getMinValue(root)).isEqualTo(10);
    }
    @Test
    public void testGetMinimumValueBasicTree2() {
        int i=0;
        GraphNode<Integer> root=new GraphNode<>(i++,0);
        root.addChild(new GraphNode<>(i++,10));
        root.addChild(new GraphNode<>(i++,15));
        TreeVisitor treeVisitor = new TreeVisitor();
        assertThat(treeVisitor.getMinValue(root)).isEqualTo(0);
    }

    @Test
    public void testGetMinimumValue2LevelsTree() {
        int i=0;
        GraphNode<Integer> root=new GraphNode<>(i++,27);
        root.addChild(new GraphNode<>(i++,10).addChild(new GraphNode<>(i++,5)));
        root.addChild(new GraphNode<>(i++,15).addChild(new GraphNode<>(i++,2)));
        TreeVisitor treeVisitor = new TreeVisitor();
        assertThat(treeVisitor.getMinValue(root)).isEqualTo(2);
    }

        @Test
    public void testGetMinimumValueRandomTree() {
        TreeGenerator<Integer> generator = new TreeGenerator<>();
        GraphNode<Integer> root = generator.generateTree( integer -> RandomGenerator.getDefault().nextInt(0,1000000),1000);
        TreeVisitor treeVisitor = new TreeVisitor();
        assertThat(treeVisitor.getMinValue(root)).isEqualTo(generator.getMinValueGenerated(Comparator.comparingInt(Integer::intValue)));
        assertThat(treeVisitor.getMaxValue(root)).isEqualTo(generator.getMaxValueGenerated(Comparator.comparingInt(Integer::intValue)));
    }

}
