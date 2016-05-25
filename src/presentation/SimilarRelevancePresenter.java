package presentation;

import view.MyApp;
import view.SimilarRelevanceView;

import java.util.ArrayList;

public class SimilarRelevancePresenter extends BasePresenter  {

    private ArrayList<String> result;

    public SimilarRelevancePresenter(ArrayList<String> result) {
        this.result = result;
        actualView = new SimilarRelevanceView(this);
        MyApp.startScene(actualView.getContent());
    }
}
