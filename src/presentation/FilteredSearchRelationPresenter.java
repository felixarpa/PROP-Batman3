package presentation;

import view.FilteredSearchRelationView;
import view.MyApp;

import java.util.ArrayList;

public class FilteredSearchRelationPresenter extends FilteredSearchPresenter {

    public FilteredSearchRelationPresenter(ArrayList<ArrayList<String>> result) {
        super(result);
        actualView = new FilteredSearchRelationView(this);

        for (int i = 0; i < 4; ++i) initialFill(i);
        MyApp.startScene(actualView.getContent());
    }


}