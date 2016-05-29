package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import presentation.RemoveEdgePresenter;
import view.auxiliarViews.Config;
import view.auxiliarViews.ImageButton;
import view.auxiliarViews.Predictor;

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
        button.setOnMousePressed(
                event -> button.press()
        );
        button.setOnMouseReleased(
                event -> {
                    button.release();
//                    ((RemoveEdgePresenter) presenter).removeEdge();
                }
        );
    }

}
