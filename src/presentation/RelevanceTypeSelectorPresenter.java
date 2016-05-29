package presentation;

import util.ProjectConstants;
import view.auxiliarViews.Config;
import view.MyApp;
import view.RelevanceTypeSelectorView;

import java.util.ArrayList;

public class RelevanceTypeSelectorPresenter extends ListPresenter  {

    public RelevanceTypeSelectorPresenter(ArrayList<String> result) {
        super(result);
        actualView = new RelevanceTypeSelectorView(this);
        show();
        MyApp.startScene(actualView.getContent());
    }

    public void onClickEntityRelevance(int id) {
        String node = result.get(id);
        Thread thread = new Thread(()-> {
            ArrayList<ArrayList<String>> selected = domainController.firstSearch(node, ProjectConstants.NODE_RELEVANCE_RESULT);
            actualView.destroy();
            actualView = null;
            MyApp.changePresenter(new FilteredSearchEntityPresenter(selected,node));
        });
        thread.start();
    }

    public void onClickRelationshipRelevance(int id) {
        String node = result.get(id);
        Thread thread = new Thread(()-> {
            ArrayList<ArrayList<String>> selected = domainController.firstSearch(node, ProjectConstants.RELATION_RELEVANCE_RESULT);
            actualView.destroy();
            actualView = null;
            MyApp.changePresenter(new FilteredSearchRelationPresenter(selected,node));
        });
        thread.start();
    }

    @Override
    protected void show() {
        int max = index+Config.LISTS_SIZE;
        if (max > result.size()) max = result.size();
        for (int i = 0; i < max-index; ++i) {
            ((RelevanceTypeSelectorView) actualView).setContent(index+i, result.get(index+i));
        }
        for (;max < index+Config.LISTS_SIZE; ++max) {
            ((RelevanceTypeSelectorView) actualView).setContent(max, "\t \t \t \t");
        }
    }



}
