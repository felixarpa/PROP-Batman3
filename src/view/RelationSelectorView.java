package view;

import javafx.scene.layout.VBox;
import presentation.RelationSelectorPresenter;

/**
 * Created by mario.fernandez on 26/05/2016.
 */
public class RelationSelectorView extends BaseView {

    VBox contentVBox;

    public RelationSelectorView(RelationSelectorPresenter relationSelectorPresenter){
        presenter = relationSelectorPresenter;
        initializePanes();
        initializeViews();
        buildPanes();
        setListeners();
        topBarPane.setCenter(contentVBox);
    }

    private void initializePanes() {

    }

    private void initializeViews() {

    }
    private void buildPanes() {

    }

    private void setListeners(){

    }
}
