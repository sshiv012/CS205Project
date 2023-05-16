package com.ucr.edu.cs205.project1;

import java.util.ArrayList;

public class Operators {
    public Node shiftTileLeft(Node currNode, int position, int boardEdge) {
        // If the position is on the extreme left, return null
        if (position % boardEdge == 0)
            return null;
        else {
            Node childNode = new Node(new ArrayList<>(currNode.getState()), currNode);
            childNode.swapTiles(position, position - 1);
            return childNode;
        }
    }

    public Node shiftTileRight(Node currNode, int position, int boardEdge) {

        for (int i = boardEdge - 1; i < boardEdge * boardEdge; i = i + boardEdge) {
            if (position == i)
                return null;
        }

        Node childNode = new Node(new ArrayList<>(currNode.getState()), currNode);
        childNode.swapTiles(position, position + 1);
        return childNode;

    }

    public Node shiftTileUp(Node currNode, int position, int boardEdge) {
        // If the position is on the top edge, return null
        if (position < boardEdge)
            return null;
        else {
            Node childNode = new Node(new ArrayList<>(currNode.getState()), currNode);
            childNode.swapTiles(position, position - boardEdge);
            return childNode;
        }
    }

    public Node shiftTileDown(Node currNode, int position, int boardEdge) {
        // If the position is on the bottom edge, return null
        if (position >= boardEdge * (boardEdge - 1))
            return null;
        else {
            Node childNode = new Node(new ArrayList<>(currNode.getState()), currNode);
            childNode.swapTiles(position, position + boardEdge);
            return childNode;
        }
    }
}
