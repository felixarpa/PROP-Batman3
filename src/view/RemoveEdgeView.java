package view;

import presentation.RemoveEdgePresenter;

public class RemoveEdgeView extends MainAdminView {
    public RemoveEdgeView(RemoveEdgePresenter removeEdgePresenter) {
        presenter = removeEdgePresenter;
        removeEdgeButton.press();
    }
}
