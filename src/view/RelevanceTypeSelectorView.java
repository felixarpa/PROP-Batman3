package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import presentation.RelevanceTypeSelectorPresenter;

import java.util.ArrayList;

public class RelevanceTypeSelectorView extends BaseView {

    private VBox contentVBox;

    private HBox titlesHBox;
    private Label nameLabel;
    private Label idLabel;
    private Label labelLabel;

    private Pane separacionSuperioPane;

    private ArrayList<HBox> results;
    private ArrayList<Label> numbers;
    private ArrayList<Label> names;
    private ArrayList<Label> ids;
    private ArrayList<Label> labels;
    private ArrayList<ImageButton> entityButtons;
    private ArrayList<ImageButton> relationshipButtons;

    private Pane separacionInferiorPane;

    private HBox pagingButtonsHbox;
    private ImageButton nextPageButton;
    private ImageButton prevPageButton;

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
        contentVBox.setPadding(new Insets(10, 40, 10, 40));
        contentVBox.setSpacing(4);

        titlesHBox = new HBox();
        titlesHBox.setPadding(new Insets(0, 10, 0, 60));

        results = new ArrayList<>(Config.LISTS_SIZE);
        for (int i = 0 ; i < Config.LISTS_SIZE; ++i) {
            results.add(new HBox());
            results.get(i).setPadding(new Insets(0, 10, 0, 10));
        }
        pagingButtonsHbox = new HBox();
        pagingButtonsHbox.setPadding(new Insets(0, 185, 0, 185));
        pagingButtonsHbox.setSpacing(50);

    }

    private void initializeViews() {
        nameLabel = new Label("Name");
        idLabel = new Label("ID");
        labelLabel = new Label("Label");
        initializeTitleLabels();

        separacionSuperioPane = new Pane();

        numbers = new ArrayList<>(Config.LISTS_SIZE);
        names = new ArrayList<>(Config.LISTS_SIZE);
        ids = new ArrayList<>(Config.LISTS_SIZE);
        labels = new ArrayList<>(Config.LISTS_SIZE);
        initializeArrayLabel();
        entityButtons = new ArrayList<>(Config.LISTS_SIZE);
        relationshipButtons = new ArrayList<>(Config.LISTS_SIZE);

        separacionInferiorPane = new Pane();

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
            ++i;
        }

        separacionSuperioPane.setMinSize(770, 1);
        separacionSuperioPane.setMaxSize(770, 1);
        separacionSuperioPane.setStyle("-fx-background-color: #ffffff");

        separacionInferiorPane.setMinSize(770, 1);
        separacionInferiorPane.setMaxSize(770, 1);
        separacionInferiorPane.setStyle("-fx-background-color: #ffffff");

        contentVBox.getChildren().add(titlesHBox);
        contentVBox.getChildren().add(separacionSuperioPane);
        contentVBox.getChildren().addAll(results);
        contentVBox.getChildren().add(separacionInferiorPane);
        // TODO: Botones next i prev
    }

    private void setListeners() {
    }

    public void setContent(int index, String node) {
        int i = index++ % Config.LISTS_SIZE;
        String[] elements = node.split("\t");

        numbers.get(i).setText(index + "");
        names.get(i).setText(elements[0]);
        ids.get(i).setText(elements[1]);
        labels.get(i).setText(elements[2]);
    }

    private void initializeArrayLabel() {
        for (int i = 0; i < 10; ++i) {
            numbers.add(new Label());
            numbers.get(i).setMinSize(50, 20);
            numbers.get(i).setMaxSize(50, 24);
            numbers.get(i).setFont(new Font(18));
            numbers.get(i).setTextFill(Paint.valueOf("white"));

            names.add(new Label());
            names.get(i).setMinSize(350, 20);
            names.get(i).setMaxSize(350, 24);
            names.get(i).setFont(new Font(18));
            names.get(i).setTextFill(Paint.valueOf("white"));

            ids.add(new Label());
            ids.get(i).setMinSize(100, 20);
            ids.get(i).setMaxSize(100, 24);
            ids.get(i).setFont(new Font(18));
            ids.get(i).setTextFill(Paint.valueOf("white"));

            labels.add(new Label());
            labels.get(i).setMinSize(75, 20);
            labels.get(i).setMaxSize(75, 24);
            labels.get(i).setFont(new Font(18));
            labels.get(i).setTextFill(Paint.valueOf("white"));

        }
    }

    private void initializeTitleLabels() {
        nameLabel.setTextFill(Config.LABEL_CLEAR_COLOR);
        nameLabel.setMinSize(350, 20);
        nameLabel.setMaxSize(350, 24);
        nameLabel.setFont(new Font("Arial bold", 24));

        idLabel.setTextFill(Config.LABEL_CLEAR_COLOR);
        idLabel.setMinSize(100, 20);
        idLabel.setMaxSize(100, 24);
        idLabel.setFont(new Font("Arial bold", 24));

        labelLabel.setTextFill(Config.LABEL_CLEAR_COLOR);
        labelLabel.setMinSize(75, 20);
        labelLabel.setMaxSize(75, 24);
        labelLabel.setFont(new Font("Arial bold", 24));


    }
}

/*
 * TAMAÑO VENTANA DE DENTRO:
 * width = 900
 * height = 370
 */
