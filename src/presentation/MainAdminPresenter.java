package presentation;

import view.MainAdminView;
import view.MyApp;

public abstract class MainAdminPresenter extends BasePresenter {


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
