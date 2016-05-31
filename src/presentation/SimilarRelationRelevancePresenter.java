package presentation;

import view.MyApp;
import view.SimilarRelationRelevanceView;
import view.auxiliarViews.Config;

import java.util.ArrayList;

public class SimilarRelationRelevancePresenter extends RelationshipRelevanceResultPresenter {

    public SimilarRelationRelevancePresenter(ArrayList<String> result, String nodeSrc, String nodeDst, double relevance) {
        transform(result);
        actualView = new SimilarRelationRelevanceView(this);
        show();
        ((SimilarRelationRelevanceView)actualView).setNode(nodeSrc, nodeDst, relevance);
        MyApp.startScene(actualView.getContent());
    }

    @Override
    protected void show() {
        int max = index+ Config.LISTS_SIZE;
        if (max > nodeSrc.size()) max = nodeSrc.size();
        max -= index;
        for (int i = 0; i < max; ++i) {
            ((SimilarRelationRelevanceView) actualView).setContent(index+i, nodeSrc.get(index+i), nodeDst.get(index+i), relevance.get(index+i));
        }
        max += index;
        for (;max < index+Config.LISTS_SIZE; ++max) {
            ((SimilarRelationRelevanceView) actualView).setContent(max, "\t \t", "\t \t", 0);
        }
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
