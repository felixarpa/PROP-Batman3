package comparators;

import domain.graph.Node;

import java.util.Comparator;

public class NodeVectorComparator implements Comparator<Node[]> {
    @Override
    public int compare(Node[] o1, Node[] o2) {
        if (o1.length != o2.length) {
            return o1.length-o2.length;
        }
        for (int i = 0; i < o1.length; ++i) {
            int comp = o1[i].getId().compareTo(o2[i].getId());
            if (comp != 0) return comp;
        }
        return 0;
    }
}
