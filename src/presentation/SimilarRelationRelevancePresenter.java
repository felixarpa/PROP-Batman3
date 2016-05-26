package presentation;

import view.SimilarRelationRelevanceView;

import java.util.ArrayList;

public class SimilarRelationRelevancePresenter extends RelationshipRelevanceResultPresenter {

    public SimilarRelationRelevancePresenter(ArrayList<String> result) {
        transform(result);
        actualView = new SimilarRelationRelevanceView(this);
    }

}
