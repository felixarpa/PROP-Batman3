package presentation;

import view.FilteredSearchResultView;
import view.MyApp;

import java.util.ArrayList;

public class FilteredSearchResultPresenter  extends BasePresenter {

    ArrayList<ArrayList<String>> result;
    ArrayList<Integer> index;

    public FilteredSearchResultPresenter(ArrayList<ArrayList<String>> result) {
        this.result = result;
        actualView = new FilteredSearchResultView(this);
        index = new ArrayList<>(4);
        for (int i = 0; i < 4; ++i) index.add(0);
        for (int i = 0; i < 4; ++i) showMore(i);
        MyApp.startScene(actualView.getContent());
    }

    public void showMore(int i) {
        int max = index.get(i) + FilteredSearchResultView.numToShow;
        if (max > result.get(i).size()) max = result.get(i).size();
        int nextindex = index.get(i) + FilteredSearchResultView.numToShow;

        for (; index.get(i) < max; index.set(i, index.get(i)+1)) {
            ((FilteredSearchResultView) actualView).setContent(index.get(i), result.get(i).get(index.get(i)), i);
        }

        for (;max < nextindex; ++max) {
            ((FilteredSearchResultView) actualView).setContent(max, "\t \t \t \t",i);
        }
    }

    public void showLess(int i) {
        int min = index.get(i)-FilteredSearchResultView.numToShow;

        for (; index.get(i) >= 0; index.set(i, index.get(i)-1)) {
            ((FilteredSearchResultView) actualView).setContent(index.get(i), result.get(i).get(index.get(i)),i);
        }
    }

}
