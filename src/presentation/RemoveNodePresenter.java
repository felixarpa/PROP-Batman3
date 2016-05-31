package presentation;


import com.sun.org.apache.regexp.internal.RE;
import domain.DomainController;
import javafx.application.Platform;
import view.MyApp;
import view.RemoveNodeView;

import java.util.ArrayList;

public class RemoveNodePresenter extends MainAdminPresenter {



    public RemoveNodePresenter() {
        actualView = new RemoveNodeView(this, DomainController.allNames());
        MyApp.startScene(actualView.getContent());
    }

    public void clickRemoveNodeButton() {
        String nombre = ((RemoveNodeView)actualView).getSearchText();
        ((RemoveNodeView)actualView).startProgress();

         Thread thread = new Thread(() -> {
            ArrayList<String> result = domainController.searchingANode(nombre);
            if (result == null) {

                Platform.runLater(()-> {
                    ((RemoveNodeView)actualView).showError("No results found");
                } );
                ((RemoveNodeView)actualView).stopProgress();
                return;
            }
            actualView.destroy();
            actualView = null;
            MyApp.changePresenter(new RemoveNodeSelectorPresenter(result));
        });
        thread.start();
    }
}
