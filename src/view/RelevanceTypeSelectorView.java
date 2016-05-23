package view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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

    public final static int numToShow = 8;

    public RelevanceTypeSelectorView(RelevanceTypeSelectorPresenter relevanceTypeSelectorPresenter) {
        presenter = relevanceTypeSelectorPresenter;
        initializePanes();
        initializeViews();
        buildPanes();
        setListeners();
        topBarPane.setCenter(contentVBox);
    }
 //158 29
    private void initializePanes() {
        contentVBox = new VBox();
        contentVBox.setPadding(new Insets(18, 100, 17, 100));

        titlesHBox = new HBox();
        titlesHBox.setPadding(new Insets(4, 100, 4, 50));

        results = new ArrayList<>(numToShow);
        for (int i = 0 ; i < numToShow; ++i) {
            HBox haux = new HBox();
            haux.setPadding(new Insets(4, 5, 4, 5));
            haux.setSpacing(5);
            results.add(haux);
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

        numbers = initializeArrayLabel(10);
        names = initializeArrayLabel(200);
        ids = initializeArrayLabel(90);
        labels = initializeArrayLabel(50);
        entityButtons = new ArrayList<>(numToShow);
        for(int j = 0;j<numToShow;++j){
            ImageButton entityButton = new ImageButton("../images","entityRelevanceButton",158,29);
            entityButtons.add(entityButton);
        }
        relationshipButtons = new ArrayList<>(numToShow);
        for(int j = 0;j<numToShow;++j){
            ImageButton relationshipButton = new ImageButton("../images","relationshipRelevanceButton",158,29);
            relationshipButtons.add(relationshipButton);
        }

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
                    labels.get(i),
                    ids.get(i),
                    entityButtons.get(i),
                    relationshipButtons.get(i)
            );
            ++i;
        }
        contentVBox.getChildren().addAll(titlesHBox);
        contentVBox.getChildren().addAll(results);
        // TODO: Botones next i prev
    }

    private void setListeners() {
        for(int i = 0; i < numToShow; ++i){
            ImageButton button = entityButtons.get(i);
            int index = i;
            button.setOnMousePressed(
                    event -> button.press()
            );
            button.setOnMouseReleased(
                    event -> {
                        button.release();
                        ((RelevanceTypeSelectorPresenter)presenter).onClickEntityRelevance(index);
                    }
            );
            ImageButton button2 = relationshipButtons.get(i);
            int index2 = i;
            button2.setOnMousePressed(
                    event -> button2.press()
            );
            button2.setOnMouseReleased(
                    event -> {
                        button2.release();
                        ((RelevanceTypeSelectorPresenter)presenter).onClickRelationshipRelevance(index2);
                    }
            );

        }
    }

    public void setContent(int index, String node) {
        int i = index++ % numToShow;
        String[] elements = node.split("\t");

        numbers.get(i).setText(index+ "");
        names.get(i).setText(elements[0]);
        ids.get(i).setText(elements[1]);
        labels.get(i).setText(elements[3]);
    }

    private ArrayList<Label> initializeArrayLabel(int width) {
        ArrayList<Label> arrayList = new ArrayList<>(numToShow);
        for (int j = 0; j < numToShow; ++j) {
                Label laux = new Label();
                laux.setFont(new Font(20));
                laux.setTextFill(Paint.valueOf("white"));
                laux.setFont(new Font("Comic Sans MS",15));
                //laux.setMinWidth(width);
                //laux.setMaxWidth(width);
                arrayList.add(laux);
        }
        return arrayList;
    }
}

/*
     * TAMAÑO VENTANA DE DENTRO:
     * width = 900
     * height = 383
     */
