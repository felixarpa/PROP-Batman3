package presentation;

import view.CategoryResultView;
import view.MyApp;

import java.util.ArrayList;

public class CategoryResultPresenter extends BasePresenter  {

    private ArrayList<String> result;

    public CategoryResultPresenter(int type) {
        result = domainController.secondSearch(type);
        actualView = new CategoryResultView(this);
        MyApp.startScene(actualView.getContent());
    }



}
