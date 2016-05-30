package presentation;

import domain.DomainController;
import view.MyApp;
import view.RemoveEdgeView;

public class RemoveEdgePresenter extends MainAdminPresenter {

    public RemoveEdgePresenter() {
        actualView = new RemoveEdgeView(this, DomainController.allNames());
        MyApp.startScene(actualView.getContent());
    }



}
