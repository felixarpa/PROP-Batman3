package presentation;

import view.*;
import view.auxiliarViews.Config;
import view.auxiliarViews.ListView;

import java.util.ArrayList;

public class CategoryResultPresenter extends ListPresenter {

    private int lastSelected;

    protected CategoryResultPresenter() {

    }

    public CategoryResultPresenter(int type) {
        super(domainController.secondSearch(type));
        actualView = new CategoryResultView(this);
        ((CategoryResultView) actualView).setType(type);
        show();
        MyApp.startScene(actualView.getContent());
    }

    public void onClick(int index) {
        lastSelected = index + this.index;
        ((CategoryResultView)actualView).askSimilarOp(index);
    }

    //op < 0 -> menos relevante
    //op == 0 -> igual de relevante
    //op > 0 -> más relevante
    public void onAcceptClick(int op) {
        ArrayList<String> nextResult = domainController.searchSimilarRelevance(result.get(lastSelected), op);
        actualView.destroy();
        actualView = null;
        MyApp.changePresenter(new SimilarRelevancePresenter(nextResult, result.get(lastSelected)));
    }

    public void reorder(int typeOfOrder, boolean ascending) {
        result = domainController.reorderSearch(2, -1, typeOfOrder, ascending);
        index = 0;
        show();
    }

    @Override
    protected void show() {
        int max = index+ Config.LISTS_SIZE;
        if (max > result.size()) max = result.size();
        max -= index;
        for (int i = 0; i < max; ++i) {
            ((ListView) actualView).setContent(index+i, result.get(index+i));
        }
        max += index;
        for (;max < index+Config.LISTS_SIZE; ++max) {
            ((ListView) actualView).setContent(max, "\t \t \t \t");
        }
    }

}
