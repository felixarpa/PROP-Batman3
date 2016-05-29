package presentation;


import view.MyApp;
import view.SimilarRelevanceView;

import java.util.ArrayList;

public class SimilarRelevancePresenter extends CategoryResultPresenter  {

    public SimilarRelevancePresenter(ArrayList<String> result, String node, int type) {
        this.result = result;
        actualView = new SimilarRelevanceView(this);
        ((SimilarRelevanceView) actualView).setType(type);

        ((SimilarRelevanceView)actualView).setNode(node);
        show();
        MyApp.startScene(actualView.getContent());
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
