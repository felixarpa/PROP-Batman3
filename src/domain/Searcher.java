package domain;

import comparators.RelevanceComparator;
import domain.graph.*;
import exceptions.ProjectError;
import util.ProjectConstants;

import java.util.*;

import static java.lang.StrictMath.abs;

public abstract class Searcher {

    public static void search(Node node, TreeSet<Paper> resultPaper, TreeSet<Conference> resultConference,
                              TreeSet<Author> resultAuthor, TreeSet<Term> resultTerm) {
        resultPaper.clear();
        resultAuthor.clear();
        resultConference.clear();
        resultTerm.clear();
        if (node.asPaper() == null) {
            for (Node adjacent : node.getAdjacentNode()) {
                Paper paper = adjacent.asPaper();
                if (paper != null) {
                    resultPaper.add(paper);
                    for (Node paperAdjacent : adjacent.getAdjacentNode()) {
                        addToResult(paperAdjacent, resultConference, resultAuthor, resultTerm);
                    }
                }
            }
        } else {
            for (Node adjacent : node.getAdjacentNode()) {
                for (Node paperAdjacent : adjacent.getAdjacentNode()) {
                    if (paperAdjacent.asPaper() != null) {
                        resultPaper.add(paperAdjacent.asPaper());
                    }
                }
                addToResult(adjacent, resultConference, resultAuthor, resultTerm);
            }
        }
        if (node.asAuthor() != null) resultAuthor.remove(node.asAuthor());
        else if (node.asPaper() != null) resultPaper.remove(node.asPaper());
        else if (node.asConference() != null) resultConference.remove(node.asConference());
        else if (node.asTerm() != null) resultTerm.remove(node.asTerm());
        else throw new ProjectError("FATAL ERROR: Node " + node + " it's not a valid type");
    }

    private static void addToResult(Node node, TreeSet<Conference> resultConference, TreeSet<Author> resultAuthor, TreeSet<Term> resultTerm) {
        if (node.asConference() != null) {
            resultConference.add(node.asConference());
        } else if (node.asAuthor() != null) {
            resultAuthor.add(node.asAuthor());
        } else if (node.asTerm() != null) {
            resultTerm.add(node.asTerm());
        }
    }

    public static void search(Node node, TreeMap<Double, TreeSet<Paper>> resultPaper, TreeMap<Double, TreeSet<Conference>> resultConference,
                              TreeMap<Double, TreeSet<Author>> resultAuthor, TreeMap<Double, TreeSet<Term>> resultTerm) {
        resultPaper.clear();
        resultAuthor.clear();
        resultConference.clear();
        resultTerm.clear();

        if (node.asPaper() == null) {
            // 1st HOP
            for (Map.Entry<Node, Double> adjacent : node.getEdges()) {
                // adjacent: (node, weight)
                Paper paper = adjacent.getKey().asPaper();

                // ONLY ADJACENT PAPER
                if (paper != null) {

                    double weight = MugendoAlgorithm.calculateRelevance(paper, node);
                    TreeSet<Paper> paperTreeSet = resultPaper.get(weight);
                    if (paperTreeSet == null) {
                        paperTreeSet = new TreeSet<>();
                        resultPaper.put(weight, paperTreeSet);
                    }
                    paperTreeSet.add(paper);

                    // 2nd HOP
                    for (Node adjacentToPaper : paper.getAdjacentNode()) {
                        if (!adjacentToPaper.equal(node)) {
                            weight = MugendoAlgorithm.calculateRelevance(adjacentToPaper, node);
                            adjacentToNode(adjacentToPaper, resultConference, resultAuthor, resultTerm, weight);
                        }
                    }
                }
            }
        } else {
            // 1st HOP
            for (Node adjacent : node.getAdjacentNode()) {

                double weight = MugendoAlgorithm.calculateRelevance(adjacent, node);

                adjacentToNode(adjacent, resultConference, resultAuthor, resultTerm, weight);
            }
        }
    }

    private static void adjacentToNode(Node adjacentToNode, TreeMap<Double, TreeSet<Conference>> resultConference,
                                       TreeMap<Double, TreeSet<Author>> resultAuthor, TreeMap<Double, TreeSet<Term>> resultTerm,
                                       double weight) {


        if (adjacentToNode.asConference() != null) {
            TreeSet<Conference> nodeTreeSet = resultConference.get(weight);
            if (nodeTreeSet == null) {
                nodeTreeSet = new TreeSet<>();
                resultConference.put(weight, nodeTreeSet);
            }
            nodeTreeSet.add(adjacentToNode.asConference());
        } else if (adjacentToNode.asAuthor() != null) {
            TreeSet<Author> nodeTreeSet = resultAuthor.get(weight);
            if (nodeTreeSet == null) {
                nodeTreeSet = new TreeSet<>();
                resultAuthor.put(weight, nodeTreeSet);
            }
            nodeTreeSet.add(adjacentToNode.asAuthor());
        } else if (adjacentToNode.asTerm() != null) {
            TreeSet<Term> nodeTreeSet = resultTerm.get(weight);
            if (nodeTreeSet == null) {
                nodeTreeSet = new TreeSet<>();
                resultTerm.put(weight, nodeTreeSet);
            }
            nodeTreeSet.add(adjacentToNode.asTerm());
        }
    }

    public static TreeSet<Node> similarRelevance(Node author, int op) {
        TreeSet<Node> result = new TreeSet<>(new RelevanceComparator());
        for (Node node : Graph.getInstance().allNodes()) {
            if (!author.equal(node) && author.getId().getType() == node.getId().getType()) {
                if (op < 0 && node.getRelevance() < author.getRelevance() ||
                        op == 0 && abs(node.getRelevance() - author.getRelevance()) <= 0.2 * author.getRelevance() ||
                        op > 0 && node.getRelevance() > author.getRelevance()) {
                    result.add(node);
                }
            }
        }
        return result;
    }

    public static LinkedList<Relation> similarRelationRelevance(Node src, Node dst, int op, TreeSet<Relation> searcSet) {
        double weightToCompare = src.getWeight(dst);
        /*SortedMap<Double, TreeSet<Node[]>> result;
        if (op < 0) {
            result = searcSet.headMap(weightToCompare, false);
        } else if (op == 0) {
            result = searcSet.subMap(weightToCompare - 0.2, true, weightToCompare + 0.2, true);
            TreeSet<Node[]> treeSet = result.get(weightToCompare);
            Node[] pair = new Node[2];
            pair[0] = src;
            pair[1] = dst;
            treeSet.remove(pair);
        } else {
            result = searcSet.tailMap(weightToCompare, false);
        }
        return result;*/
        LinkedList<Relation> result = new LinkedList<>();
        Relation actual = new Relation(src, dst, 0);
        for (Relation relation : searcSet) {
            //System.out.println(relation);
            if (!relation.equals(actual)) {
                if ((op < 0 && relation.getRelevance() < weightToCompare) ||
                        (op == 0 && (abs(relation.getRelevance() - weightToCompare) <= 0.2)) ||
                        (op > 0 && relation.getRelevance() > weightToCompare)) {
                    //System.out.println("picked");
                    result.add(relation);
                }
            }
        }
        return result;
    }

    public static TreeSet<Node> allTypeNodes(int type) {
        TreeSet<Node> result = new TreeSet<>(new RelevanceComparator());
        for (Node node : Graph.getInstance().allNodes()) {
            if (node.getId().getType() == type) {
                result.add(node);
            }
        }
        return result;
    }

    public static TreeSet<Relation> allRelevanceTypeNodes(int typesrc, int typedst) {
        TreeSet<Relation> result = new TreeSet<>();
        for (Node node : Graph.getInstance().allNodes()) {
            if (node.getId().getType() == typesrc) {
                LinkedList<Node> copy = new LinkedList<>(node.getAdjacentNode());
                for (Node adjacentNode : copy) {
                    if (typedst != ProjectConstants.PAPER_TYPE && adjacentNode.asPaper() != null) {
                        for (Node adjacentPaper : adjacentNode.getAdjacentNode()) {
                            if (!node.equal(adjacentPaper) && adjacentPaper.getId().getType() == typedst) {
                                addToResult(node, adjacentPaper, result);
                            }
                        }
                    } else if (adjacentNode.getId().getType() == typedst) {
                        addToResult(node, adjacentNode, result);
                    }
                }
            }
        }
        return result;
    }

    private static void addToResult(Node src, Node dst, TreeSet<Relation> result) {
        double weight = MugendoAlgorithm.calculateRelevance(src, dst);
        result.add(new Relation(src, dst, weight));
    }
}
