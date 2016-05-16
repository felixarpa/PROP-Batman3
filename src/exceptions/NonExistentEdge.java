package exceptions;

import domain.graph.Node;

public class NonExistentEdge extends Exception {
    private Node node1;
    private Node node2;

    public NonExistentEdge(Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    @Override
    public String getMessage() {
        return "Attempted to remove a non-existent Edge:\n"+node1+ " with\n"+node2;
    }

    public Node getNode2() {
        return node2;
    }

    public Node getNode1() {
        return node1;
    }
}
