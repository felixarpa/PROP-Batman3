package presentation;

import presentation.MainPresenter;
import view.AddFavoriteTermSearchView;
import view.MainView;
import view.MyApp;

import java.util.ArrayList;

public class AddFavoriteTermSearchPresenter extends MainPresenter{
    
    public AddFavoriteTermSearchPresenter() {
        actualView = new AddFavoriteTermSearchView(this);
        MyApp.startScene(actualView.getContent());
    }

    @Override
    public void clickSearchButton() {
        String nom = ((AddFavoriteTermSearchView)actualView).getSearchText();
        ArrayList<String> result = domainController.searchingATerm(nom);
        if (result != null) {
            actualView.destroy();
            actualView = null;
            MyApp.changePresenter(new AddTermSelectorPresenter(result));
        }
    }
}
