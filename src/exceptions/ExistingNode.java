package exceptions;

import domain.graph.Node;

public class ExistingNode extends Exception {
    private Node node;

    public ExistingNode(Node node) {
        this.node = node;
    }

    @Override
    public String getMessage() {
        return "Attempted to add an existing node:\n"+node;
    }

    public Node getNode() {return node;}
}
