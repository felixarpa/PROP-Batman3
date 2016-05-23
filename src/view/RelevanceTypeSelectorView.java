package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
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

        numbers = initializeArrayLabel();
        names = initializeArrayLabel();
        ids = initializeArrayLabel();
        labels = initializeArrayLabel();
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
            ++i;
        }
        contentVBox.getChildren().addAll(results);
        // TODO: Botones next i prev
    }

    private void setListeners() {
    }

    public void setContent(int index, String node) {
        int i = index++ % 10;
        String[] elements = node.split("\t");

        numbers.get(i).setText(index%numToShow + "");
        names.get(i).setText(elements[0]);
        ids.get(i).setText(elements[1]);
        labels.get(i).setText(elements[2]);
    }

    private ArrayList<Label> initializeArrayLabel() {
        ArrayList<Label> arrayList = new ArrayList<>(numToShow);
        for (int j = 0; j < numToShow; ++j) {
                Label laux = new Label();
                laux.setFont(new Font(20));
                laux.setTextFill(Paint.valueOf("white"));
                laux.setFont(new Font("Comic Sans MS",15));
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
