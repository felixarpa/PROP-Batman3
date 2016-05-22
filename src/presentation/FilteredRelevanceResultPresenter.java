package presentation;

import view.FilteredRelevanceResultView;
import view.MyApp;

import java.util.ArrayList;

public class FilteredRelevanceResultPresenter extends BasePresenter {
    ArrayList<ArrayList<String>> result;

    public FilteredRelevanceResultPresenter(ArrayList<ArrayList<String>> result) {
        this.result = result;
        actualView = new FilteredRelevanceResultView(this);
        MyApp.startScene(actualView.getContent());
    }
}
