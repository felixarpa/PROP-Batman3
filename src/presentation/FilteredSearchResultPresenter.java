package presentation;

import view.FilteredSearchResultView;
import view.MyApp;

import java.util.ArrayList;

public class FilteredSearchResultPresenter  extends BasePresenter {
    ArrayList<ArrayList<String>> result;
    int typeOfResult;

    FilteredSearchResultPresenter(ArrayList<ArrayList<String>> result, int typeOfResult) {
        this.result = result;
        this.typeOfResult = typeOfResult;
        actualView = new FilteredSearchResultView(this);
        MyApp.startScene(actualView.getContent());
    }




}
