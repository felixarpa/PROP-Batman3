package presentation;

import domain.DomainController;
import view.MyApp;
import view.RemoveNodeView;

import java.util.ArrayList;

public class RemoveNodePresenter extends MainAdminPresenter {

    public RemoveNodePresenter() {
        actualView = new RemoveNodeView(this, DomainController.allNames());
        MyApp.startScene(actualView.getContent());
    }

    public void clickRemoveNodeButton() {
        System.out.println("Haz el presenter Juanmi (ﾉಠдಠ)ﾉ︵┻━┻");
        String nombre = ((RemoveNodeView)actualView).getSearchText();
        ((RemoveNodeView)actualView).startProgress();
        Thread thread = new Thread(() -> {
            ArrayList<String> result = domainController.searchingANode(nombre);
            if (result == null) {
                System.out.println("No results found");
                ((RemoveNodeView)actualView).stopProgress();
                return;
            }
            actualView.destroy();
            actualView = null;
            //MyApp.changePresenter();
        });
        thread.start();
    }
}
