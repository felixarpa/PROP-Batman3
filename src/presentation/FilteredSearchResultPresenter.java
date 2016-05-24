package presentation;

import view.Config;
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
        ((FilteredSearchResultView)actualView).changeType(type);
        show();
    }

    private void initialFill(int i) {
        int max = FilteredSearchResultView.numToShow;
        if (max > result.get(i).size()) max = result.get(i).size();
        int nextIndex = FilteredSearchResultView.numToShow;

        for (int index = 0; index < max; ++index) {
            ((FilteredSearchResultView) actualView).setContent(index, result.get(i).get(index), i,FilteredSearchResultView.numToShow);
        }

        for (;max < nextIndex; ++max) {
            ((FilteredSearchResultView) actualView).setContent(max, "\t \t \t \t",i,FilteredSearchResultView.numToShow);
        }
    }

    public void showMore() {
        if (index + Config.LISTS_SIZE <= result.size()) {
            index += Config.LISTS_SIZE;
            show();

        }

    }

    public void showLess() {
        if (index - Config.LISTS_SIZE >= 0) {
            index = index - Config.LISTS_SIZE;
            show();
        }


    }

    private void show() {
        int max = index+ Config.LISTS_SIZE;
        if (max> result.get(actualType).size()) max = result.get(actualType).size();
        for (int i = 0; i < max-index; ++i) {
            System.out.println(i);
            ((FilteredSearchResultView)actualView).setContent(index+i, result.get(actualType).get(index+i),actualType,Config.LISTS_SIZE);
        }
        for (;max < index+Config.LISTS_SIZE; ++max) {
            ((FilteredSearchResultView)actualView).setContent(max, "\t \t \t \t",actualType,Config.LISTS_SIZE);
        }
    }

}
