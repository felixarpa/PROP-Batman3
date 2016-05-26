package presentation;


import view.MyApp;
import view.SimilarRelevanceView;

import java.util.ArrayList;

public class SimilarRelevancePresenter extends CategoryResultPresenter  {

    public SimilarRelevancePresenter(ArrayList<String> result, String node) {
        this.result = result;
        actualView = new SimilarRelevanceView(this);
        ((SimilarRelevanceView)actualView).setNode(node);
        show();
        MyApp.startScene(actualView.getContent());
    }

}
