package presentation;

import domain.DomainController;
import exceptions.ExistingEdge;
import exceptions.NonExistentEdgeNodes;
import exceptions.ProjectError;
import util.ProjectConstants;
import view.AddEdgeView;
import view.ManageEdgeView;
import view.MyApp;

public class AddEdgePresenter extends MainAdminPresenter {

    public AddEdgePresenter() {
        actualView = new AddEdgeView(this, DomainController.allNamesWithId());
        MyApp.startScene(actualView.getContent());
    }

    public void addEdge() {
        int id1;
        int id2;
        int type1;
        int type2;
        try {
            id1 = Integer.parseInt(((AddEdgeView) actualView).getId1());
            id2 = Integer.parseInt(((AddEdgeView) actualView).getId2());
            type1 = Integer.parseInt(((AddEdgeView) actualView).getType1());
            type2 = Integer.parseInt(((AddEdgeView) actualView).getType2());
        }
        catch (Exception e) {
            ((ManageEdgeView)actualView).setErrorMessage("Please select nodes to add an edge between them");
            return;
        }
        if (type1 != ProjectConstants.PAPER_TYPE && type2 != ProjectConstants.PAPER_TYPE) {
            ((ManageEdgeView)actualView).setErrorMessage("Can't make an edge between two nodes not directly connected (one of them must be paper)");
            return;
        }
        try {
            adminController.addNewEdge(id1, type1, id2, type2);
            ((ManageEdgeView)actualView).setErrorMessage("Edge added successfully");
        } catch (ExistingEdge existingEdge) {
            ((ManageEdgeView)actualView).setErrorMessage("An edge already exists between the nodes");
        } catch (NonExistentEdgeNodes existingEdge) {
            throw new ProjectError(existingEdge.getMessage());
        }

    }
}
