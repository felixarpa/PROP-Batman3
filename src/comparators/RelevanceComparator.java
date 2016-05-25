package comparators;

import domain.graph.Node;

import java.util.Comparator;

public class RelevanceComparator implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        if (o1.getRelevance() < o2.getRelevance()) {
            return 1;
        }
        else if (o1.getRelevance() > o2.getRelevance()) {
            return -1;
        }
        else {
            return o1.getId().compareTo(o2.getId());
        }
    }

}
