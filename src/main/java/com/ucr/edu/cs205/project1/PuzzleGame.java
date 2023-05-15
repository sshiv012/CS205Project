package com.ucr.edu.cs205.project1;

import java.util.*;


public class PuzzleGame {
    private int boardSize;
    private int boardEdge;
    private List<Node> nodes = new ArrayList<>();
    private List<Integer> goalState = new ArrayList<>();

    private void generateGoalState() {
        for(int i = 1; i < boardSize; i++)
            goalState.add(i);
        goalState.add(0);
    }
    public PuzzleGame(int boardSize) {
        this.boardSize = boardSize;
        this.boardEdge = (int) Math.sqrt(boardSize);
        generateGoalState();
    }

    public int calculateMisplacedTilesCount(List<Integer> gameState) {
        int misplacedCount = 0;
        for(int i = 0; i < boardSize; i++) {
            if(gameState.get(i) == 0) {
                continue;
            }
            if(!gameState.get(i).equals(goalState.get(i))) {
                misplacedCount++;
            }
        }
        return misplacedCount;
    }
    public int calculateTotalManhattanDistance(List<Integer> gameState) {
        int totalDistance = 0;
        for (int i = 1; i < boardSize; i++) {
            int currentIndex = gameState.indexOf(i);
            int goalIndex = goalState.indexOf(i);

            if(currentIndex == goalIndex) {
                continue;
            }
            int currentRow = (currentIndex / boardEdge) + 1;
            int currentCol = (currentIndex % boardEdge) + 1;
            int goalRow = (goalIndex / boardEdge) + 1;
            int goalCol = (goalIndex % boardEdge) + 1;

            int tileDistance = Math.abs(currentRow - goalRow) + Math.abs(currentCol - goalCol);
            totalDistance += tileDistance;
        }
        return totalDistance;
    }

    public Boolean isGoalState(List<Integer> currState){
        return goalState.equals(currState);
    }

    public Node shiftTileLeft(Node currNode, int position){
        // If the position is on the extreme left, return null
        if(position%boardEdge==0)
            return null;
        else
        {
            Node childNode = new Node(new ArrayList<>(currNode.state), currNode);
            childNode.swapTiles(position, position-1);
            return childNode;
        }
    }

    public Node shiftTileRight(Node currNode, int position){
        // If the position is on the extreme right, return null
        if(position%(boardEdge-1)==0)
            return null;
        else
        {
            Node childNode = new Node(new ArrayList<>(currNode.state), currNode);
            childNode.swapTiles(position, position+1);
            return childNode;
        }
    }

    public Node shiftTileUp(Node currNode, int position){
        // If the position is on the top edge, return null
        if(position<boardEdge)
            return null;
        else{
            Node childNode = new Node(new ArrayList<>(currNode.state), currNode);
            childNode.swapTiles(position, position-boardEdge);
            return childNode;
        }
    }

    public Node shiftTileDown(Node currNode, int position){
        // If the position is on the bottom edge, return null
        if(position>=boardEdge*(boardEdge-1))
            return null;
        else{
            Node childNode = new Node(new ArrayList<>(currNode.state), currNode);
            childNode.swapTiles(position, position+boardEdge);
            return childNode;
        }
    }


    class Node{
        private List<Integer> state;
        private Node parentNode;
        private int depth;
        private int manhattanDistance;
        private int misplacedTilesCount;

        public Node(List<Integer> state, Node parentNode) {
            this.state = new ArrayList<>(state);
            if(parentNode == null) {
                this.parentNode = null;
                this.depth = 0;
            } else {
                this.parentNode = parentNode;
                this.depth = this.parentNode.depth + 1;
            }
            this.manhattanDistance=Integer.MIN_VALUE;
            this.misplacedTilesCount=Integer.MIN_VALUE;
        }

        public int getItem(int index) {
            return this.state.get(index);
        }

        public void swapTiles(int pos1, int pos2) {
            int temp = this.state.get(pos1);
            this.state.set(pos1, this.state.get(pos2));
            this.state.set(pos2, temp);
        }

        public int getIndexOf(int item) {
            return this.state.indexOf(item);
        }

        public int getMisplacedTilesCount(){
            if(this.misplacedTilesCount==Integer.MIN_VALUE)
                this.misplacedTilesCount= PuzzleGame.this.calculateMisplacedTilesCount(this.state);
            return this.misplacedTilesCount;
        }

        public int getManhattanDistance(){
            if(this.manhattanDistance==Integer.MIN_VALUE)
                this.manhattanDistance=PuzzleGame.this.calculateTotalManhattanDistance(this.state);
            return this.manhattanDistance;
        }

    }

}
