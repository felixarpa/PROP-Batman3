package presentation;

import view.FilteredSearchResultView;
import view.MyApp;
import view.RelevanceTypeSelectorView;

import java.util.ArrayList;

public class FilteredSearchResultPresenter  extends BasePresenter {

    ArrayList<ArrayList<String>> result;
    int index;

    public FilteredSearchResultPresenter(ArrayList<ArrayList<String>> result) {
        this.result = result;
        actualView = new FilteredSearchResultView(this);
        index = 0;
        showMore();
        MyApp.startScene(actualView.getContent());
    }

    public void showMore() {
        int max = index + FilteredSearchResultView.numToShow;
        if (max > result.get(0).size()) max = result.get(0).size();
        int nextindex = index + FilteredSearchResultView.numToShow;
        for (; index < max; ++index) {
            System.out.println(result.get(0).get(index));
            ((FilteredSearchResultView) actualView).setContent(index, result.get(0).get(index), 0);
        }

        for (;max < nextindex; ++max) {
            ((FilteredSearchResultView) actualView).setContent(max, "\t \t \t",0);
        }
    }

    public void showLess() {
        int min = index-FilteredSearchResultView.numToShow;

        for (; index >= 0; --index) {
            ((RelevanceTypeSelectorView) actualView).setContent(index, result.get(0).get(index));
        }
    }

}
