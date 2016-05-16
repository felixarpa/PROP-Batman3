package comparators;

import domain.Relation;

import java.util.Comparator;

public class RelationSingleRelevance2Comparator implements Comparator<Relation> {
    @Override
    public int compare(Relation o1, Relation o2) {
        if (o1.getNode2().getRelevance() < o2.getNode1().getRelevance())  {
            return -1;
        }
        else if (o1.getNode2().getRelevance() > o2.getNode1().getRelevance()) {
            return 1;
        }
        else {
            if (o1.getNode1().getRelevance() < o2.getNode2().getRelevance())  {
                return -1;
            }
            else if (o1.getNode1().getRelevance() > o2.getNode2().getRelevance()) {
                return 1;
            }
            else {
                int result = o1.getNode1().compareTo(o2.getNode2());
                return (result == 0 ? o1.getNode2().compareTo(o2.getNode1()) : result);
            }
        }
    }
}