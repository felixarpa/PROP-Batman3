package presentation;

import view.BaseView;
import view.MainView;
import view.MyApp;

public class MainPresenter extends BasePresenter {


     public MainPresenter() {
         actualView = new MainView(this);
         MyApp.startScene(actualView.getContent());
     }

    public void clickSearchButton() {
        String nombre = actualView.ge
        //MainView.hideSearchButton();
        //ArrayList<String> result = god.searchingANode(nombre);
    }

}
