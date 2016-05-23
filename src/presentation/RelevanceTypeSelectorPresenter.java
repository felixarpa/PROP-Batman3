package presentation;

import util.ProjectConstants;
import view.MyApp;
import view.RelevanceTypeSelectorView;

import java.util.ArrayList;

public class RelevanceTypeSelectorPresenter extends BasePresenter  {
    private ArrayList<String> result;

    private int index;

    public RelevanceTypeSelectorPresenter(ArrayList<String> result) {
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
        int max = index + RelevanceTypeSelectorView.numToShow;
        if (max > result.size()) max = result.size();
        int nextindex = index + RelevanceTypeSelectorView.numToShow;
        for (; index < max; ++index) {
            ((RelevanceTypeSelectorView) actualView).setContent(index, result.get(index));
        }

        for (; max < nextindex; ++max) {
            ((RelevanceTypeSelectorView) actualView).setContent(max, " \t \t ");
        }
    }

    public void showLess() {
        int min = index - 10;

        for (; index >= 0; --index) {
            ((RelevanceTypeSelectorView) actualView).setContent(index%10, result.get(index));
        }
    }

}
