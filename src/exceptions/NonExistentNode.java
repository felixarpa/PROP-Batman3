package exceptions;

import domain.graph.Node;

public class NonExistentNode extends Exception {
    private Node node;

    public NonExistentNode(Node node) {
        this.node = node;
    }

    @Override
    public String getMessage() {
        return "Attempted to remove a non-existent node:\n"+node.getName()+" "+node.getId();
    }

    public Node getNode() {return node;}
}
