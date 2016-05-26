package presentation;

import view.MainAdminView;
import view.MyApp;

public class MainAdminPresenter extends BasePresenter {

    public MainAdminPresenter() {
        actualView = new MainAdminView(this);
        MyApp.startScene(actualView.getContent());
    }

    public void clickAddNode() {
        actualView.destroy();
        actualView = null;
        MyApp.changePresenter(new AddNodePresenter());
    }

    public void clickRemoveNode() {
        actualView.destroy();
        actualView = null;
        MyApp.changePresenter(new RemoveNodePresenter());
    }

    public void clickAddEdge() {
        actualView.destroy();
        actualView = null;
        MyApp.changePresenter(new AddEdgePresenter());
    }

    public void clickRemoveEdge() {
        actualView.destroy();
        actualView = null;
        MyApp.changePresenter(new RemoveEdgePresenter());
    }
}
