package presentation;

import view.MyApp;
import view.RemoveNodeView;

/**
 * Created by carlos.roldan on 26/05/2016.
 */
public class RemoveNodePresenter extends MainAdminPresenter {

    public RemoveNodePresenter() {
        actualView = new RemoveNodeView(this);
        MyApp.startScene(actualView.getContent());
    }
}
