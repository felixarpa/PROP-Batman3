package exceptions;

import domain.graph.Id;
import domain.graph.Node;

public class NonExistentNode extends Exception {
    private Node node;
    private Id id;

    public NonExistentNode(Node node) {
        this.node = node;
    }

    public NonExistentNode(Id id) {this.id = id;}

    @Override
    public String getMessage() {
        return "Attempted to remove a non-existent node:\n"+ (node == null ? id : node.getName()+" "+node.getId());
    }

    public Node getNode() {return node;}
}
