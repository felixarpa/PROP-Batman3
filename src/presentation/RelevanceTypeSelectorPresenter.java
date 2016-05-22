package presentation;

import util.ProjectConstants;
import view.MyApp;
import view.RelevanceTypeSelectorView;

import java.util.ArrayList;

public class RelevanceTypeSelectorPresenter extends BasePresenter  {
    ArrayList<String> result;

    RelevanceTypeSelectorPresenter(ArrayList<String> result) {
        this.result = result;
        actualView = new RelevanceTypeSelectorView(this);
        MyApp.startScene(actualView.getContent());
    }

    public void onClickEntityRelevance(int id) {
        String node = result.get(id);
        Thread thread = new Thread(()-> {
            ArrayList<ArrayList<String>> selected = domainController.firstSearch(node, ProjectConstants.NODE_RELEVANCE_RESULT);
            actualView.destroy();
            actualView = null;
            MyApp.changePresenter(new FilteredSearchResultPresenter(selected,ProjectConstants.NODE_RELEVANCE_RESULT));
        });
        thread.start();
    }

    public void onClickRelationshipRelevance(int id) {
        String node = result.get(id);
        Thread thread = new Thread(()-> {
            ArrayList<ArrayList<String>> selected = domainController.firstSearch(node, ProjectConstants.RELATION_RELEVANCE_RESULT);
            actualView.destroy();
            actualView = null;
            MyApp.changePresenter(new FilteredSearchResultPresenter(selected,ProjectConstants.RELATION_RELEVANCE_RESULT));
        });
        thread.start();
    }

}
