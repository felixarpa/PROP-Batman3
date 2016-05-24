package presentation;

import view.FilteredSearchResultView;
import view.MyApp;

import java.util.ArrayList;

public class FilteredSearchResultPresenter  extends BasePresenter {

    ArrayList<ArrayList<String>> result;
    int index, actualType;

    public FilteredSearchResultPresenter(ArrayList<ArrayList<String>> result) {
        this.result = result;
        actualView = new FilteredSearchResultView(this);
        for (int i = 0; i < 4; ++i) initialFill(i);
        MyApp.startScene(actualView.getContent());
    }

    public void setType(int type) {
        index = 0;
        actualType = type;
    }

    private void initialFill(int i) {
        int max = FilteredSearchResultView.numToShow;
        if (max > result.get(i).size()) max = result.get(i).size();
        int nextIndex = FilteredSearchResultView.numToShow;

        for (int index = 0; index < max; ++index) {
            ((FilteredSearchResultView) actualView).setContent(index, result.get(i).get(index), i);
        }

        for (;max < nextIndex; ++max) {
            ((FilteredSearchResultView) actualView).setContent(max, "\t \t \t \t",i);
        }
    }

    public void showMore() {
        int max = index + FilteredSearchResultView.numToShow;
        if (max > result.get(actualType).size()) max = result.get(actualType).size();
        int nextIndex = index + FilteredSearchResultView.numToShow;

        for (; index < max; ++index) {
            ((FilteredSearchResultView) actualView).setContent(index, result.get(actualType).get(index), 0);
        }

        for (;max < nextIndex; ++max) {
            ((FilteredSearchResultView) actualView).setContent(max, "\t \t \t \t",0);
        }
    }

    public void showLess() {

        for (; index >= 0; --index) {
            ((FilteredSearchResultView) actualView).setContent(index, result.get(actualType).get(index),0);
        }

    }

}
