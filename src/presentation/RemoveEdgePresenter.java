package presentation;

import domain.DomainController;
import exceptions.ExistingEdge;
import exceptions.NonExistentEdge;
import exceptions.NonExistentEdgeNodes;
import exceptions.ProjectError;
import util.ProjectConstants;
import view.ManageEdgeView;
import view.MyApp;
import view.RemoveEdgeView;

public class RemoveEdgePresenter extends MainAdminPresenter {

    public RemoveEdgePresenter() {
        actualView = new RemoveEdgeView(this, DomainController.allNamesWithId());
        MyApp.startScene(actualView.getContent());
    }

    public void removeEdge() {
        int id1;
        int id2;
        int type1;
        int type2;
        try {
            id1 = Integer.parseInt(((RemoveEdgeView) actualView).getId1());
            id2 = Integer.parseInt(((RemoveEdgeView) actualView).getId2());
            type1 = Integer.parseInt(((RemoveEdgeView) actualView).getType1());
            type2 = Integer.parseInt(((RemoveEdgeView) actualView).getType2());
        }
        catch (Exception e) {
            ((ManageEdgeView)actualView).showErrorMessage("Please select nodes to remove an edge between them");
            return;
        }
        if (type1 != ProjectConstants.PAPER_TYPE && type2 != ProjectConstants.PAPER_TYPE) {
            ((ManageEdgeView)actualView).showErrorMessage("Can't remove an edge between two nodes not directly connected (one of them must be paper)");
            return;
        }
        try {
            adminController.deleteEdge(id1, type1, id2, type2);
            ((ManageEdgeView)actualView).showSuccessMessage("Edge removed successfully");
        } catch (NonExistentEdge existingEdge) {
            ((ManageEdgeView)actualView).showErrorMessage("No edge exists between the nodes");
        } catch (NonExistentEdgeNodes existingEdge) {
            throw new ProjectError(existingEdge.getMessage());
        }

    }


}
