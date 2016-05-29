package presentation;

import domain.DomainController;
import view.AddEdgeView;
import view.MyApp;

public class AddEdgePresenter extends MainAdminPresenter {

    public AddEdgePresenter() {
        actualView = new AddEdgeView(this, DomainController.allNames());
        MyApp.startScene(actualView.getContent());
    }
}
