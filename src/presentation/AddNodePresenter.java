package presentation;

import view.AddNodeView;
import view.MyApp;

/**
 * Created by carlos.roldan on 26/05/2016.
 */
public class AddNodePresenter extends MainAdminPresenter {

    public AddNodePresenter() {
        actualView = new AddNodeView(this);
        MyApp.startScene(actualView.getContent());
    }

}
