package domain;

import domain.graph.*;

public abstract class PageRank {


    public static void execute() {
        for (int i = 0; i < 10; ++i) {
            for (Node node : Graph.getInstance().allNodesId()) {
                node.calculateRelevance(); // 0.85 amazing magic number! :D
            }
        }
    }
}