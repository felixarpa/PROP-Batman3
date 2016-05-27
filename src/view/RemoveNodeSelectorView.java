package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import presentation.ListPresenter;
import presentation.RelevanceTypeSelectorPresenter;
import presentation.RemoveNodeSelectorPresenter;

import java.util.ArrayList;
import java.util.Collection;

public class RemoveNodeSelectorView extends MainAdminView {


    protected HBox titlesHBox;
    protected Label nameLabel;
    protected Label idLabel;

    protected Pane separacionSuperioPane;

    protected ArrayList<HBox> results;
    protected ArrayList<Label> numbers;
    protected ArrayList<Label> names;
    protected ArrayList<Label> ids;
    private ArrayList<ImageButton> deleteButtons;

    protected Pane separacionInferiorPane;

    protected HBox pagingButtonsHbox;
    protected ImageButton nextPageButton;
    protected ImageButton prevPageButton;

    public RemoveNodeSelectorView(RemoveNodeSelectorPresenter removeNodeSelectorPresenter) {
        presenter = removeNodeSelectorPresenter;
        initializePanes();
        initializeViews();
        completePanes();
        setListeners();
        topBarPane.setCenter(contentVBox);
    }

    protected void initializePanes(){
        contentVBox = new VBox();
        contentVBox.setPadding(new Insets(5, 40, 5, 40));
        contentVBox.setSpacing(4);

        titlesHBox = new HBox();
        titlesHBox.setPadding(new Insets(0, 10, 0, 60));

        results = new ArrayList<>(Config.LISTS_SIZE);
        for (int i = 0 ; i < Config.LISTS_SIZE; ++i) {
            results.add(new HBox());
            results.get(i).setPadding(new Insets(0, 10, 0, 10));
        }
        pagingButtonsHbox = new HBox();
        pagingButtonsHbox.setPadding(new Insets(0, 0, 0, 345));
        pagingButtonsHbox.setSpacing(10);
    }

    protected void initializeViews() {
        nameLabel = new Label("Name");
        idLabel = new Label("ID");
        initializeTitleLabels();

        separacionSuperioPane = new Pane();

        numbers = new ArrayList<>(Config.LISTS_SIZE);
        names = new ArrayList<>(Config.LISTS_SIZE);
        ids = new ArrayList<>(Config.LISTS_SIZE);
        initializeArrayLabel();

        separacionInferiorPane = new Pane();

        prevPageButton = new ImageButton("../images/", "prevButton", 60, 30);
        nextPageButton = new ImageButton("../images/", "nextButton", 60, 30);

            deleteButtons = new ArrayList<>(Config.LISTS_SIZE);

            for (int i = 0; i < 10; ++i) {
                deleteButtons.add(new ImageButton("../images", "entityRelevanceButton", 140, 24));
            }
        }

        protected void setListeners() {

            for (int i = 0; i < Config.LISTS_SIZE; ++i) {
                final int index = i;

                deleteButtons.get(i).setOnMousePressed(
                        event -> deleteButtons.get(index).press()
                );
                deleteButtons.get(i).setOnMouseReleased(
                        event -> {
                            deleteButtons.get(index).release();
                            ((RemoveNodeSelectorPresenter) presenter).onClickRemoveNode(index);
                        }
                );
            }
            prevPageButton.setOnMousePressed(event -> prevPageButton.press());
            prevPageButton.setOnMouseReleased(
                    event -> {
                        prevPageButton.release();
                        ((RemoveNodeSelectorPresenter) presenter).showLess();
                    }
            );

            nextPageButton.setOnMousePressed(event -> nextPageButton.press());
            nextPageButton.setOnMouseReleased(
                    event -> {
                        nextPageButton.release();
                        ((RemoveNodeSelectorPresenter) presenter).showMore();
                    }
            );
        }


    protected void buildPanes(){
        titlesHBox.getChildren().addAll(
                nameLabel,
                idLabel
        );

        pagingButtonsHbox.getChildren().add(prevPageButton);
        pagingButtonsHbox.getChildren().add(nextPageButton);

        separacionSuperioPane.setMinSize(800, 1);
        separacionSuperioPane.setMaxSize(800, 1);
        separacionSuperioPane.setStyle("-fx-background-color: #ffffff");

        separacionInferiorPane.setMinSize(800, 1);
        separacionInferiorPane.setMaxSize(800, 1);
        separacionInferiorPane.setStyle("-fx-background-color: #ffffff");

        contentVBox.getChildren().add(titlesHBox);
        contentVBox.getChildren().add(separacionSuperioPane);
        contentVBox.getChildren().addAll(results);
        contentVBox.getChildren().add(separacionInferiorPane);
        contentVBox.getChildren().add(pagingButtonsHbox);
    }

    protected void completePanes() {
            buildLine();
            buildPanes();
    }


    protected void buildLine() {
            int i = 0;
            for (HBox line : results) {
                line.getChildren().addAll(
                        numbers.get(i),
                        names.get(i),
                        ids.get(i),
                        deleteButtons.get(i)
                );
                ++i;
            }
    }

    protected void initializeArrayLabel() {
        Font font = Font.loadFont(this.getClass().getResource("../fonts/Nilland-Black.ttf").toExternalForm(), 20);

        for (int i = 0; i < Config.LISTS_SIZE; ++i) {
            numbers.add(new Label());
            numbers.get(i).setMinSize(50, 24);
            numbers.get(i).setMaxSize(50, 24);
            numbers.get(i).setFont(font);
            numbers.get(i).setTextFill(Paint.valueOf("white"));

            names.add(new Label());
            names.get(i).setMinSize(380, 24);
            names.get(i).setMaxSize(380, 24);
            names.get(i).setFont(font);
            names.get(i).setTextFill(Paint.valueOf("white"));

            ids.add(new Label());
            ids.get(i).setMinSize(75, 24);
            ids.get(i).setMaxSize(75, 24);
            ids.get(i).setFont(font);
            ids.get(i).setTextFill(Paint.valueOf("white"));
        }
    }

    protected void initializeTitleLabels() {
        nameLabel.setTextFill(Config.LABEL_CLEAR_COLOR);
        nameLabel.setMinSize(380, 20);
        nameLabel.setMaxSize(380, 24);
        nameLabel.setFont(new Font("Arial bold", 24));

        idLabel.setTextFill(Config.LABEL_CLEAR_COLOR);
        idLabel.setMinSize(75, 20);
        idLabel.setMaxSize(75, 24);
        idLabel.setFont(new Font("Arial bold", 24));
    }

    public void setContent(int index, String node) {
        int i = index++ % Config.LISTS_SIZE;
        String[] elements = node.split("\t");
        names.get(i).setText(elements[0]);
        ids.get(i).setText(elements[1]);

        if (elements[0].equals("")) numbers.get(i).setText("");
        else numbers.get(i).setText(index + "");


        if (elements[0].equals("")) {
            deleteButtons.get(i).setDisable(true);

            deleteButtons.get(i).setVisible(false);
        }
        else {
            deleteButtons.get(i).setDisable(false);

            deleteButtons.get(i).setVisible(true);
        }
    }
    }

