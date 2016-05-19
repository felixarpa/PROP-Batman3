package presentation;

import view.BaseView;
import view.MainView;
import view.MyApp;

public class MainPresenter extends BasePresenter {

    private MainView mainView;

     public MainPresenter() {
         mainView = new MainView(this);
         MyApp.startScene(mainView.getContent());
     }

    public void ClickSearchButton() {
       // String nombre = MainView.getSearchText();
        //MainView.hideSearchButton();
        //ArrayList<String> result = god.searchingANode(nombre);
    }

}
