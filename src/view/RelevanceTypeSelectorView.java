package view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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

    private Pane separacionSuperioPane;

    private ArrayList<HBox> results;
    private ArrayList<Label> numbers;
    private ArrayList<Label> names;
    private ArrayList<Label> ids;
    private ArrayList<ImageButton> entityButtons;
    private ArrayList<ImageButton> relationshipButtons;

    private Pane separacionInferiorPane;

    private HBox pagingButtonsHbox;
    private ImageButton nextPageButton;
    private ImageButton prevPageButton;
    private Button next;
    private Button prev;

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
        initializeTitleLabels();

        separacionSuperioPane = new Pane();

        numbers = new ArrayList<>(Config.LISTS_SIZE);
        names = new ArrayList<>(Config.LISTS_SIZE);
        ids = new ArrayList<>(Config.LISTS_SIZE);
        entityButtons = new ArrayList<>(Config.LISTS_SIZE);
        relationshipButtons = new ArrayList<>(Config.LISTS_SIZE);
        initializeArrayLabel();

        separacionInferiorPane = new Pane();

        // TODO: Imagenes de next y prev
    }

    private void buildPanes() {
        titlesHBox.getChildren().addAll(
                nameLabel,
                idLabel
        );

        int i = 0;
        for (HBox line : results) {
            results.get(i).getChildren().addAll(
                    numbers.get(i),
                    names.get(i),
                    ids.get(i),
                    entityButtons.get(i),
                    relationshipButtons.get(i)
            );
            ++i;
        }

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


        // TODO: Botones next i prev
        //PRUEBA DE MIERDA QUE NO SE USARA Y LA VOY A QUITAR AHORA
        HBox hbox = new HBox();
        next = new Button("Next");
        prev = new Button("Prev");
        hbox.getChildren().add(next);
        hbox.getChildren().add(prev);
        contentVBox.getChildren().add(hbox);

    }

    private void setListeners() {

        for (int i = 0; i < Config.LISTS_SIZE; ++i) {
            final int index = i;
            entityButtons.get(i).setOnMousePressed(
                event -> entityButtons.get(index).changeButtonImage("../images/entityRelevanceButtonPressed.png")
            );
            entityButtons.get(i).setOnMouseReleased(
                event -> {
                    entityButtons.get(index).changeButtonImage("../images/entityRelevanceButton.png");
                    ((RelevanceTypeSelectorPresenter) presenter).onClickEntityRelevance(index);
                }
            );

            relationshipButtons.get(i).setOnMousePressed(
                event -> relationshipButtons.get(index).changeButtonImage("../images/relationshipRelevanceButtonPressed.png")
            );
            relationshipButtons.get(i).setOnMouseReleased(
                event -> {
                    relationshipButtons.get(index).changeButtonImage("../images/relationshipRelevanceButton.png");
                    ((RelevanceTypeSelectorPresenter) presenter).onClickRelationshipRelevance(index);
                }
            );
        }
        next.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((RelevanceTypeSelectorPresenter)presenter).showMore();
            }
        });

        prev.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((RelevanceTypeSelectorPresenter)presenter).showLess();
            }
        });
    }

    public void setContent(int index, String node) {
        int i = index++ % Config.LISTS_SIZE;
        String[] elements = node.split("\t");
        if (elements[0].equals("")) numbers.get(i).setText("");
        else numbers.get(i).setText(index + "");
        names.get(i).setText(elements[0]);
        ids.get(i).setText(elements[1]);

        if (elements[0].equals("")) {
            entityButtons.get(i).setDisable(true);
            entityButtons.get(i).setVisible(false);
        }
        else {
            entityButtons.get(i).setDisable(false);
            entityButtons.get(i).setVisible(true);
        }
        if (elements[0].equals("")) {
            relationshipButtons.get(i).setDisable(true);
            relationshipButtons.get(i).setVisible(false);
        }
        else {
            relationshipButtons.get(i).setDisable(false);
            relationshipButtons.get(i).setVisible(true);
        }

    }

    private void initializeArrayLabel() {
        Font font = Font.loadFont(this.getClass().getResource("../fonts/Nilland-Black.ttf").toExternalForm(), 18);

        for (int i = 0; i < 10; ++i) {
            numbers.add(new Label());
            numbers.get(i).setMinSize(50, 20);
            numbers.get(i).setMaxSize(50, 24);

            numbers.get(i).setFont(font);
            numbers.get(i).setTextFill(Paint.valueOf("white"));

            names.add(new Label());
            names.get(i).setMinSize(380, 20);
            names.get(i).setMaxSize(380, 24);
            names.get(i).setFont(font);
            names.get(i).setTextFill(Paint.valueOf("white"));

            ids.add(new Label());
            ids.get(i).setMinSize(75, 20);
            ids.get(i).setMaxSize(75, 24);
            ids.get(i).setFont(font);
            ids.get(i).setTextFill(Paint.valueOf("white"));

            entityButtons.add(new ImageButton("../images/entityRelevanceButton.png", 140, 24));
            relationshipButtons.add(new ImageButton("../images/relationshipRelevanceButton.png", 140, 24));
        }
    }

    private void initializeTitleLabels() {
        nameLabel.setTextFill(Config.LABEL_CLEAR_COLOR);
        nameLabel.setMinSize(380, 20);
        nameLabel.setMaxSize(380, 24);
        nameLabel.setFont(new Font("Arial bold", 24));

        idLabel.setTextFill(Config.LABEL_CLEAR_COLOR);
        idLabel.setMinSize(75, 20);
        idLabel.setMaxSize(75, 24);
        idLabel.setFont(new Font("Arial bold", 24));
    }
}

/*
 * TAMAÑO VENTANA DE DENTRO:
 * width = 900
 * height = 370
 */
