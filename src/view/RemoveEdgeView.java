package view;

import presentation.RemoveEdgePresenter;
import view.auxiliarViews.ImageButton;

import java.util.Collection;

public class RemoveEdgeView extends ManageEdgeView {

    public RemoveEdgeView(RemoveEdgePresenter presenter, Collection<String> resultToPredict) {
        super(presenter, resultToPredict);
        this.presenter = presenter;
        removeEdgeButton.press();

        initializePanes();
        initializeViews();
        buildPanes();
        setListeners();
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        button = new ImageButton("removeEdgeButton", 100, 50);
    }

    @Override
    protected void setListeners() {
        super.setListeners();

        button.setOnMouseReleased(
                event -> {
                    button.release();
//                    ((RemoveEdgePresenter) presenter).removeEdge();
                }
        );
    }

}
