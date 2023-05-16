package com.ucr.edu.cs205.project1;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final List<Integer> state;

    private final Node parentNode;

    private final int depth;


    public Node(List<Integer> state, Node parentNode) {
        this.state = new ArrayList<>(state);
        if (parentNode == null) {
            this.parentNode = null;
            this.depth = 0;
        } else {
            this.parentNode = parentNode;
            this.depth = this.parentNode.depth + 1;
        }
    }

    public Node getParentNode() {
        return parentNode;
    }

    public int getDepth() {
        return depth;
    }

    public List<Integer> getState() {
        return this.state;
    }

    public void swapTiles(int pos1, int pos2) {
        int temp = this.state.get(pos1);
        this.state.set(pos1, this.state.get(pos2));
        this.state.set(pos2, temp);
    }

}
