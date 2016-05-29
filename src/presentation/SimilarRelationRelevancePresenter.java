package presentation;

import view.SimilarRelationRelevanceView;

import java.util.ArrayList;

public class SimilarRelationRelevancePresenter extends RelationshipRelevanceResultPresenter {

    public SimilarRelationRelevancePresenter(ArrayList<String> result, String nodeSrc, String nodeDst, double relevance) {
        transform(result);
        actualView = new SimilarRelationRelevanceView(this);
        ((SimilarRelationRelevanceView)actualView).setNode(nodeSrc, nodeDst, relevance);
    }

    @Override
    public void onClick(int index) {
    }
    @Override
    public void onAcceptClick(int op) {
    }
    @Override
    public void reorder(int typeOfOrder, boolean ascending) {
    }

}
