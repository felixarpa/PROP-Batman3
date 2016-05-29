package presentation;

import view.MyApp;
import view.RemoveEdgeView;

public class RemoveEdgePresenter extends MainAdminPresenter {

    public RemoveEdgePresenter() {
        actualView = new RemoveEdgeView(this);
        MyApp.startScene(actualView.getContent());
    }



}
