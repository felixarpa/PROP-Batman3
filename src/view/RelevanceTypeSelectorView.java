package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import presentation.RelevanceTypeSelectorPresenter;

import java.util.ArrayList;

public class RelevanceTypeSelectorView extends BaseView {

    private VBox contentVBox;

    private HBox titlesHBox;
    private Label nameLabel;
    private Label idLabel;
    private Label labelLabel;

    private ArrayList<HBox> results;
    private ArrayList<Label> numbers;
    private ArrayList<Label> names;
    private ArrayList<Label> ids;
    private ArrayList<Label> labels;
    private ArrayList<ImageButton> entityButtons;
    private ArrayList<ImageButton> relationshipButtons;

    private HBox pagingButtonsHbox;
    private ImageButton nextPageButton;
    private ImageButton prevPageButton;

    public final static int numToShow = 10;

    public RelevanceTypeSelectorView(RelevanceTypeSelectorPresenter relevanceTypeSelectorPresenter) {
        presenter = relevanceTypeSelectorPresenter;
        initializePanes();
        initializeViews();
        buildPanes();
        setListeners();
        topBarPane.setCenter(contentVBox);
    }

    private void initializePanes() {
        contentVBox = new VBox();
        contentVBox.setPadding(new Insets(18, 100, 17, 100));

        titlesHBox = new HBox();
        titlesHBox.setPadding(new Insets(4, 100, 4, 50));

        results = new ArrayList<>(numToShow);
        for (HBox res : results) {
            res.setPadding(new Insets(4, 5, 4, 5));
            res.setSpacing(5);
        }

        pagingButtonsHbox = new HBox();
        pagingButtonsHbox.setPadding(new Insets(4, 175, 4, 175));
        pagingButtonsHbox.setSpacing(50);

    }

    private void initializeViews() {
        nameLabel = new Label("Name");
        nameLabel.setTextFill(Config.LABEL_TEXT_COLOR);
        idLabel = new Label("ID");
        idLabel.setTextFill(Config.LABEL_TEXT_COLOR);
        labelLabel = new Label("Label");
        labelLabel.setTextFill(Config.LABEL_TEXT_COLOR);

        numbers = new ArrayList<>(numToShow);
        names = new ArrayList<>(numToShow);
        ids = new ArrayList<>(numToShow);
        labels = new ArrayList<>(numToShow);
        entityButtons = new ArrayList<>(numToShow);
        relationshipButtons = new ArrayList<>(numToShow);

        // TODO: Imagenes de next y prev
    }

    private void buildPanes() {
        titlesHBox.getChildren().addAll(
                nameLabel,
                idLabel,
                labelLabel
        );

        int i = 0;
        for (HBox line : results) {
            line.getChildren().addAll(
                    numbers.get(i),
                    names.get(i),
                    ids.get(i)
            );
        }

        // TODO: Botones next i prev
    }

    private void setListeners() {
    }

    public void setContent(int index, String node) {
        int i = index++ % 10;
        String[] elements = node.split("\t");

        numbers.get(i).setText(index + "");
        names.get(i).setText(elements[0]);
        ids.get(i).setText(elements[1]);
        labels.get(i).setText("");
        if (elements.length > 2) labels.get(i).setText(elements[2]);
    }
}

/*
     * TAMAÑO VENTANA DE DENTRO:
     * width = 900
     * height = 383
     */
