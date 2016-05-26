package presentation;

import javafx.application.Platform;
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
        ((MainView)actualView).startProgress();
        Thread thread = new Thread(() -> {
            ArrayList<String> result = domainController.searchingANode(nombre);
            if (result == null) {
                System.out.println("No results found");
                ((MainView)actualView).stopProgress();
                return;
            }
            actualView.destroy();
            actualView = null;
            MyApp.changePresenter(new RelevanceTypeSelectorPresenter(result));
        });
        thread.start();
    }

}
