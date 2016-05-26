package presentation;

import view.CategoryResultView;
import view.Config;
import view.MyApp;
import view.SimilarRelevanceView;

import java.util.ArrayList;

public class SimilarRelevancePresenter extends ListPresenter  {

    public SimilarRelevancePresenter(ArrayList<String> result) {
        super(result);
        actualView = new SimilarRelevanceView(this);
        show();
        MyApp.startScene(actualView.getContent());
    }

    @Override
    protected void show() {
        int max = index+Config.LISTS_SIZE;
        if (max > result.size()) max = result.size();
        max -= index;
        for (int i = 0; i < max; ++i) {
            ((CategoryResultView) actualView).setContent(index+i, result.get(index+i));
        }
        max += index;
        for (;max < index+Config.LISTS_SIZE; ++max) {
            ((CategoryResultView) actualView).setContent(max, "\t \t \t \t");
        }
    }

}
