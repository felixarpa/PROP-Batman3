package comparators;

import domain.Relation;

import java.util.Comparator;

public class RelationIdComparator1 implements Comparator<Relation> {

    @Override
    public int compare(Relation o1, Relation o2) {
        int comp = o1.getNode1().getId().compareTo(o2.getNode1().getId());
        return (comp == 0 ? o1.getNode2().getId().compareTo(o2.getNode2().getId()) : comp);
    }

}
