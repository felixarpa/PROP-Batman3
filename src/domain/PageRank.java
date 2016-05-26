package domain;

import domain.graph.*;

public abstract class PageRank {

    private  static double min;
    private  static double max;

    public static void execute() {
        min = Double.MAX_VALUE;
        max = Double.MIN_VALUE;
        for (int i = 0; i < 10; ++i) {
            for (Node node : Graph.getInstance().allNodesId()) {
                node.calculateRelevance(); // 0.85 amazing magic number! :D
                if (i == 9) {
                    min = Double.min(min, node.getRelevance());
                    max = Double.max(max, node.getRelevance());
                }
            }
        }
    }

    public static double getMinRelevance() {
        return min;
    }

    public static double getMaxRelevance() {
        return max;
    }
}