package presentation;

import view.MyApp;
import view.RemoveEdgeView;

/**
 * Created by carlos.roldan on 26/05/2016.
 */
public class RemoveEdgePresenter extends MainAdminPresenter {

    public RemoveEdgePresenter() {
        actualView = new RemoveEdgeView(this);
        MyApp.startScene(actualView.getContent());
    }

}
