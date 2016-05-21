package presentation;

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
}
