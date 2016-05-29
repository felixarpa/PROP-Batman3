package presentation;

import view.FilteredSearchEntityView;
import view.MyApp;

import java.util.ArrayList;

public class FilteredSearchEntityPresenter extends FilteredSearchPresenter {

    public FilteredSearchEntityPresenter(ArrayList<ArrayList<String>> result, String node) {
      super(result,node);
      actualView = new FilteredSearchEntityView(this);

        for (int i = 0; i < 4; ++i) initialFill(i);
        MyApp.startScene(actualView.getContent());
    }


    @Override
    public void reorder(int typeOfOrder, boolean ascending) {
        result.set(actualType,domainController.reorderSearch(1,actualType,typeOfOrder,ascending));
        index = 0;
        show();
    }

}
