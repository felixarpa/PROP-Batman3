package presentation;

//import view.CategoryResultView;
import java.util.ArrayList;

public class CategoryResultPresenter extends BasePresenter  {

    ArrayList<String> result;

    CategoryResultPresenter(int type) {
//        actualView = new CategoryResultView(this);
        result = domainController.secondSearch(type);
    }

}
