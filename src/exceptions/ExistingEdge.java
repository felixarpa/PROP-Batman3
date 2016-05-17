package exceptions;

import domain.graph.Node;

public class ExistingEdge extends Exception {
    private Node node1;
    private Node node2;
    private Double replaced;

    public ExistingEdge(Node node1, Node node2, double replaced) {
        this.node1 = node1;
        this.node2 = node2;
        this.replaced = replaced;
    }

    @Override
    public String getMessage() {
        return "Attempted to add an existing Edge:\n"+node1+ " with\n"+node2+"\nweight "+replaced;
    }

    public Node getNode2() {
        return node2;
    }

    public Node getNode1() {
        return node1;
    }

    public Double getReplaced() {
        return replaced;
    }
}
