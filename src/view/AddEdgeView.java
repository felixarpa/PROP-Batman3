package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import presentation.AddEdgePresenter;
import view.auxiliarViews.Config;
import view.auxiliarViews.ImageButton;
import view.auxiliarViews.Predictor;

import java.util.Collection;

public class AddEdgeView extends ManageEdgeView {

    private HBox predictorHBox;

    private Predictor node1;
    private Predictor node2;

    private Label errorLabel;

    private ImageButton createButton;

    private Collection<String> resultToPredict;

    public AddEdgeView(AddEdgePresenter presenter, Collection<String> resultToPredict) {
        super(presenter, resultToPredict);
        this.presenter = presenter;
        addEdgeButton.press();

        initializePanes();
        initializeViews();
        buildPanes();
        setListeners();
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        button = new ImageButton("addEdgeButton", 100, 50);
    }

    @Override
    protected void setListeners() {
        button.setOnMousePressed(
                event -> button.press()
        );
        button.setOnMouseReleased(
                event -> {
                    button.release();
//                    ((AddEdgePresenter) presenter).addEdge();
                }
        );
    }

}
