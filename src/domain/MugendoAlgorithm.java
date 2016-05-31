package domain;

import domain.graph.*;
import exceptions.NonExistentEdge;
import exceptions.ProjectError;

public abstract class MugendoAlgorithm {

    public static double calculateRelevance(Node src, Node dst) {
        Graph graph = Graph.getInstance();
        double weight = src.getWeight(dst);
        if (weight > 0.0) return weight; // Ya existe una conexion
        if (src.equal(dst)) throw new ProjectError("Attempted to compute the relevance between a node and itself"); // Mismo nodo
        if (src.asPaper() == null && dst.asPaper() == null) { // Ninguno de los dos es Paper: 2 hops
            weight = get2HopsRelevance(src, dst);
            try {
                graph.addSingleEdge(src, dst, weight);
            } catch (Exception graphException) {
                throw new ProjectError("In Mugendo algorithm, we got the next exception :\n"+graphException.getMessage());
            }
        }
        else if (src.asPaper() != null ^ dst.asPaper() != null) { // Nomes un paper: 1 hop
            weight = get1HopRelevance(src, dst);
            try {
                graph.setWeight(src, dst, weight);
            } catch (NonExistentEdge nonExistentEdge) {
                throw new ProjectError("In Mugendo algorithm, we got the next exception :\n"+nonExistentEdge.getMessage());
            }
        }
        else throw new ProjectError("Tried to compute relevance between two papers");
        return weight;
    }

    private static double get2HopsRelevance(Node src, Node dst) {
        double weight = 0.0;
        for (Node node : dst.getAdjacentNode()) {
            if (node.asPaper() != null && src.existsEdge(node)) {
                double middleToDst = calculateRelevance(node, dst);
                weight += calculateRelevance(src, node) * middleToDst;
            }
        }
        return weight;
    }

    private static double get1HopRelevance(Node src, Node dst) {
        double total = 0;
        for (Node node : dst.getAdjacentNode()) {
            if (node.getId().getType() == src.getId().getType()) {
                total += node.getRelevance();
            }
        }
        return (src.getRelevance() / total);
    }

}
