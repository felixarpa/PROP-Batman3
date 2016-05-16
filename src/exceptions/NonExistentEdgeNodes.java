package exceptions;

import domain.graph.Node;

public class NonExistentEdgeNodes extends Exception {

    private Node node1;
    private Node node2;

    public NonExistentEdgeNodes(Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    @Override
    public String getMessage() {
        return "Attempted to add or remove an edge with a non-existent node/s:\n"+
                (node1 == null ? "":node1+"\n")+
                (node2 == null ? "":node2.toString());
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }
}
