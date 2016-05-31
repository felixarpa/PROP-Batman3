package presentation;

import domain.AdminController;
import exceptions.ExistingNode;
import exceptions.ProjectError;
import view.AddNodeView;
import view.MyApp;

public class AddNodePresenter extends MainAdminPresenter {

    public AddNodePresenter() {
        actualView = new AddNodeView(this);
        MyApp.startScene(actualView.getContent());
    }
    public void addNode(){
        String name = ((AddNodeView)actualView).getName();
        int label = ((AddNodeView)actualView).getLabel();
        int type = ((AddNodeView)actualView).getType();
        if (name.length() == 0 || type == -1) ((AddNodeView)actualView).showErrorMessage("You must fill all the fields.");
        else {
            try {
                adminController.addNewNode(name,label,type);
            } catch (ExistingNode existingNode) {
                throw new ProjectError("Lo has roto.\n" + existingNode.getMessage());
            }
            ((AddNodeView)actualView).showSuccessMessage("Node added successfully.");
        }

    }
}
