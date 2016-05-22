package presentation;

import util.ProjectConstants;
import view.MyApp;
import view.RelevanceTypeSelectorView;

import java.util.ArrayList;

public class RelevanceTypeSelectorPresenter extends BasePresenter  {
    ArrayList<String> result;
    int index;

    RelevanceTypeSelectorPresenter(ArrayList<String> result) {
        this.result = result;
        actualView = new RelevanceTypeSelectorView(this);
        index = 0;
        MyApp.startScene(actualView.getContent());

    }

    public void onClickEntityRelevance(int id) {
        String node = result.get(id);
        Thread thread = new Thread(()-> {
            ArrayList<ArrayList<String>> selected = domainController.firstSearch(node, ProjectConstants.NODE_RELEVANCE_RESULT);
            actualView.destroy();
            actualView = null;
            MyApp.changePresenter(new FilteredSearchResultPresenter(selected));
        });
        thread.start();
    }

    public void onClickRelationshipRelevance(int id) {
        String node = result.get(id);
        Thread thread = new Thread(()-> {
            ArrayList<ArrayList<String>> selected = domainController.firstSearch(node, ProjectConstants.RELATION_RELEVANCE_RESULT);

            actualView.destroy();
            actualView = null;
            MyApp.changePresenter(new FilteredRelevanceResultPresenter(selected));
        });
        thread.start();
    }

    public void showMore() {
        int max = index+10;
        if (max > result.size()) max = result.size();
        for (int i = index; i < max; ++i) {
            actualView.setContent(i-index, result.get(i));
        }

        for (;max < index+10; ++max) {
            actualView.setContent(max, "");
        }
    }

    public void showLess() {

    }

}
