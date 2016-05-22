package presentation;

import view.FilteredSearchResultView;
import view.MyApp;

import java.util.ArrayList;

public class FilteredSearchResultPresenter  extends BasePresenter {
    ArrayList<ArrayList<String>> result;

    FilteredSearchResultPresenter(ArrayList<ArrayList<String>> result) {
        this.result = result;
        actualView = new FilteredSearchResultView(this);
        MyApp.startScene(actualView.getContent());
    }




}
