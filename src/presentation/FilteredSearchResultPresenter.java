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
        int max = index + 10;
        if (max > result.size()) max = result.size();

        for (; index < max; ++index) {
            ((FilteredSearchResultView) actualView).setContent(index % 10, result.get(0).get(index), 0);
        }

        for (;max < index+10; ++max) {
            ((FilteredSearchResultView) actualView).setContent(max, "\t \t \t",0);
        }
    }

    public void showLess() {
        int min = index-10;

        for (; index >= 0; --index) {
            ((RelevanceTypeSelectorView) actualView).setContent(index%10, result.get(0).get(index));
        }
    }

}
