package presentation;

import view.FilteredSearchEntityView;
import view.MyApp;

import java.util.ArrayList;

public class FilteredSearchEntityPresenter extends FilteredSearchPresenter {

    public FilteredSearchEntityPresenter(ArrayList<ArrayList<String>> result) {
      super(result);
      actualView = new FilteredSearchEntityView(this);

        for (int i = 0; i < 4; ++i) initialFill(i);
        MyApp.startScene(actualView.getContent());
    }



}
