package domain;

import domain.graph.Node;

public class Relation implements Comparable<Relation> {

    private double relevance;
    private Node node1;
    private Node node2;

    public Relation(Node node1, Node node2, double relevance) {
        this.node1 = node1;
        this.node2 = node2;
        this.relevance = relevance;
    }

    public double getRelevance() {
        return relevance;
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }

    @Override
    public int compareTo(Relation o) {
        if (relevance < o.relevance) {
            return -1;
        }
        else if (relevance > o.relevance) {
            return 1;
        }
        else {
            int comp = node1.getId().compareTo(o.getNode1().getId());
            return (comp == 0 ? node2.getId().compareTo(o.getNode2().getId()) : comp);
        }
    }

    @Override
    public boolean equals(Object obj) {
        Relation rel = (Relation)obj;
        return (node1.equal(rel.node1) ? node2.equal(rel.node2) : false);
    }

    @Override
    public String toString() {
        return node1 + " " + node2 + " " +relevance;
    }
}
