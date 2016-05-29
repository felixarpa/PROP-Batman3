package presentation;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import javafx.geometry.Insets;
import javafx.scene.control.ProgressIndicator;
import presentation.MainPresenter;
import view.AddFavoriteTermSearchView;
import view.MainView;
import view.MyApp;
import view.auxiliarViews.ImageButton;
import view.auxiliarViews.Predictor;

import java.util.ArrayList;

public class AddFavoriteTermSearchPresenter extends MainPresenter{
    
    public AddFavoriteTermSearchPresenter() {
        actualView = new AddFavoriteTermSearchView(this);
        MyApp.startScene(actualView.getContent());
    }

    @Override
    public void clickSearchButton() {
        Thread thread = new Thread(() ->{
            String nom = ((AddFavoriteTermSearchView)actualView).getSearchText();
            ArrayList<String> result = domainController.searchingATerm(nom);
            if (result != null) {
                actualView.destroy();
                actualView = null;
                MyApp.changePresenter(new AddTermSelectorPresenter(result));
            }
        });
        thread.start();
    }



}
