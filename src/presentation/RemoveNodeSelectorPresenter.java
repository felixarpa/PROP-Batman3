package presentation;

import domain.DomainController;
import view.MyApp;
import view.RemoveNodeSelectorView;


public class RemoveNodeSelectorPresenter extends MainAdminPresenter{

    public RemoveNodeSelectorPresenter() {
        actualView = new RemoveNodeSelectorView(this, DomainController.allNames());
        MyApp.startScene(actualView.getContent());
    }

}

