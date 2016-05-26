package presentation;

import view.AddEdgeView;
import view.MyApp;

public class AddEdgePresenter extends MainAdminPresenter {

    public AddEdgePresenter() {
        actualView = new AddEdgeView(this);
        MyApp.startScene(actualView.getContent());
    }
}
