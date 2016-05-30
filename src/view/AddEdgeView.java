package view;

import presentation.AddEdgePresenter;
import view.auxiliarViews.ImageButton;

import java.util.Collection;

public class AddEdgeView extends ManageEdgeView {

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
        super.setListeners();

        button.setOnMouseReleased(
                event -> {
                    button.release();
//                    ((AddEdgePresenter) presenter).addEdge();
                }
        );
    }

}
