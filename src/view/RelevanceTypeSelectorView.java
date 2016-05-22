package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import presentation.MainPresenter;
import presentation.RelevanceTypeSelectorPresenter;

import java.util.ArrayList;

public class RelevanceTypeSelectorView extends BaseView {

    private VBox contentVBox;

    private HBox titlesVBox;
    private ArrayList<HBox> results;
    private ArrayList<Label> numbers;
    private ArrayList<Label> names;
    private ArrayList<Label> labels;
    private ArrayList<Label> ids;
    private ArrayList<ImageButton> entityButtons;
    private ArrayList<ImageButton> relationshipButtons;


    public RelevanceTypeSelectorView(RelevanceTypeSelectorPresenter relevanceTypeSelectorPresenter) {
        presenter = relevanceTypeSelectorPresenter;
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

    private void setListeners() {
    }

    public void setContent(int index, String node) {
    }
}
