package presentation;

import view.CategoryResultView;

import java.util.ArrayList;

/**
 * Created by Fanz0 on 19/05/2016.
 */
public class CategoryResultPresenter extends BasePresenter  {

    ArrayList<String> result;

    CategoryResultPresenter(int type) {
        actualView = new CategoryResultView(this);
        result = god.secondSearch(type);
    }

}
