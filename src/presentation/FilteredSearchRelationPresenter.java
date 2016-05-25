package presentation;

import view.FilteredSearchRelationView;
import view.MyApp;

import java.util.ArrayList;

public class FilteredSearchRelationPresenter extends FilteredSearchPresenter {

    public FilteredSearchRelationPresenter(ArrayList<ArrayList<String>> result,String node) {
        super(result,node);
        processResult();
        actualView = new FilteredSearchRelationView(this);

        for (int i = 0; i < 4; ++i) initialFill(i);
        nodeRelevance = "";
        MyApp.startScene(actualView.getContent());
    }

    private void processResult() {
        ArrayList<ArrayList<String>> processed = new ArrayList<>(4);
        for (int k = 0; k < 4; ++k) {
            ArrayList<String> aux = new ArrayList<>();
            for (int i = 0; i < result.get(k).size(); ++i) {
                int auxnum = Integer.parseInt(result.get(k).get(i));
                ++i;
                String relevance = result.get(k).get(i);
                ++i;
                for (int j = 0; j < auxnum; ++j) {
                    aux.add(result.get(k).get(i+j)+ "\t" + relevance);
                }
                i += auxnum-1;
            }
            processed.add(aux);
        }
        result = processed;
    }

}