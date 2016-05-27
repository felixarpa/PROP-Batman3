package presentation;

import view.AddNodeView;
import view.MyApp;

public class AddNodePresenter extends MainAdminPresenter {

    public AddNodePresenter() {
        actualView = new AddNodeView(this);
        MyApp.startScene(actualView.getContent());
    }
    public void addNode(String name, int type, int label){

    }
}
