package presentation;

import view.BaseView;
import view.MainView;
import view.MyApp;

import java.util.ArrayList;

public class MainPresenter extends BasePresenter {


     public MainPresenter() {
         actualView = new MainView(this);
         MyApp.startScene(actualView.getContent());
     }

    public void clickSearchButton() {
        String nombre = ((MainView)actualView).getSearchText();
        ArrayList<String> result = god.searchingANode(nombre);
        actualView.destroy();
        actualView = null;
        System.out.println("Starting search");
        MyApp.changePresenter(new RelevanceTypeSelectorPresenter(result));
    }

}
