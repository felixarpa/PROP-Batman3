package presentation;

import view.Config;
import view.FilteredSearchView;
import view.MyApp;

import java.util.ArrayList;

public abstract class FilteredSearchPresenter extends BasePresenter{
    ArrayList<ArrayList<String>> result;
    int index, actualType;

    public FilteredSearchPresenter(ArrayList<ArrayList<String>> result) {
        this.result = result;

    }

    public void setType(int type) {
        index = 0;
        actualType = type;
        ((FilteredSearchView)actualView).changeType(type);
        show();
    }

    protected void initialFill(int i) {
        int max = FilteredSearchView.numToShow;
        if (max > result.get(i).size()) max = result.get(i).size();
        int nextIndex = FilteredSearchView.numToShow;

        for (int index = 0; index < max; ++index) {
            ((FilteredSearchView) actualView).setContent(index, result.get(i).get(index), i,FilteredSearchView.numToShow);
        }

        for (;max < nextIndex; ++max) {
            ((FilteredSearchView) actualView).setContent(max, "\t \t \t \t",i,FilteredSearchView.numToShow);
        }
    }

    public void showMore() {
        if (index + Config.LISTS_SIZE <= result.get(actualType).size()) {
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
            ((FilteredSearchView)actualView).setContent(index+i, result.get(actualType).get(index+i),actualType,Config.LISTS_SIZE);
        }
        for (;max < index+Config.LISTS_SIZE; ++max) {
            ((FilteredSearchView)actualView).setContent(max, "\t \t \t \t",actualType,Config.LISTS_SIZE);
        }
    }

}
