package view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import presentation.CategoryResultPresenter;
import presentation.RelationshipRelevanceResultPresenter;
import util.ProjectConstants;
import view.auxiliarViews.Config;
import view.auxiliarViews.ImageButton;

import java.util.ArrayList;

public class RelationshipRelevanceResultView extends BaseView {

    VBox contentVBox;
    PopUp popUp;

    Font font;
    Font titleFont;


    HBox topBar;

    Label firstType;
    Label secondType;
    Label relevance;

    private HBox titleHBox;
    private ArrayList<HBox> titleHBoxes;

    private Label firstNameWord;
    private Label secondNameWord;
    private Label relevanceWord;

    ArrayList<HBox> topBarContents;

    protected Pane separacionSuperioPane;

    ArrayList<HBox> contents;

    ArrayList<ArrayList<HBox>> minicontents;
    ArrayList<Label> index;
    ArrayList<ProgressBar> relevanceBar;
    ArrayList<ArrayList<Label>> nodes;

    HBox buttonBar;
    ImageButton prevButton;
    ImageButton nextButton;
    
    StackPane stackPane;

    private int ascendingFirstNames;
    private int ascendingSecondNames;
    private int ascendingRelevance;

    public RelationshipRelevanceResultView(RelationshipRelevanceResultPresenter relationshipRelevanceResultPresenter) {
        presenter = relationshipRelevanceResultPresenter;
        relationshipSearch.press();
        ascendingFirstNames = -1;
        ascendingSecondNames = -1;
        ascendingRelevance = 0;
        initializeFonts();
        initializePanes();
        initializeViews();
        buildPanes();
        setListeners();
        topBarPane.setCenter(stackPane);
        topBarPane.setTop(topBar);
    }

    private void initializeFonts() {
        font = Font.loadFont(this.getClass().getResource("../fonts/Nilland-Black.ttf").toExternalForm(), 14);
        titleFont = Font.loadFont(this.getClass().getResource("../fonts/Nilland-Black.ttf").toExternalForm(),18);
    }


    private void initializePanes() {
        stackPane = new StackPane();
        contentVBox = new VBox();
        topBar = new HBox();
        topBar.setMaxSize(900,110);
        topBar.setMinSize(900,110);
        topBar.setAlignment(Pos.CENTER);
        String actualNodeBG =  this.getClass().getResource("../images/blueTopBar.png").toExternalForm();
        topBar.setStyle(
                "-fx-background-image: url('" + actualNodeBG + "'); " +
                        "-fx-background-position: center center; " +
                        "-fx-background-repeat: stretch;" +
                        "-fx-background-size: 900 110;"
        );
        topBarContents = new ArrayList<>(3);
        for (int i = 0; i < 3; ++i) {
            HBox haux = new HBox();
            haux.setPadding(new Insets(0,50,0,50));
            haux.setAlignment(Pos.CENTER);
            switch (i) {
                case 0:
                    haux.setMaxWidth(250);
                    haux.setMinWidth(250);
                    break;
                case 1:
                    haux.setMaxWidth(220);
                    haux.setMinWidth(220);
                    break;
                case 2:
                    haux.setMaxWidth(280);
                    haux.setMinWidth(280);
                    break;
            }
            topBarContents.add(haux);
        }

        titleHBox = new HBox();
        titleHBox.setMinWidth(900);
        titleHBox.setMinWidth(900);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBoxes = new ArrayList<>(3);
        for (int i = 0; i < 3; ++i) {
            HBox haux = new HBox();
            haux.setPadding(new Insets(0,50,0,50));
            haux.setAlignment(Pos.CENTER);
            switch (i) {
                case 0:
                    haux.setMaxWidth(250);
                    haux.setMinWidth(250);
                    break;
                case 1:
                    haux.setMinSize(220, 20);
                    haux.setMaxSize(220, 24);
                    break;
                case 2:
                    haux.setMaxWidth(280);
                    haux.setMinWidth(280);
                    break;
            }
            titleHBoxes.add(haux);
        }


        contents = new ArrayList<>(Config.LISTS_SIZE);
        for (int i = 0; i < Config.LISTS_SIZE; ++i) {
            HBox haux = new HBox();
            haux.setAlignment(Pos.CENTER);
            contents.add(haux);
        }

        minicontents = new ArrayList<>(Config.LISTS_SIZE);
        for (int i = 0; i < Config.LISTS_SIZE; ++i) {
            ArrayList arrayaux = new ArrayList(3);
            for (int j = 0; j < 3; ++j) {
                HBox haux = new HBox();
//                haux.setPadding(new Insets(0,50,0,50));

                haux.setSpacing(10);
                switch (j) {
                    case 0:
                        haux.setMaxWidth(250);
                        haux.setMinWidth(250);
                        haux.setAlignment(Pos.CENTER_LEFT);
                        break;
                    case 1:
                        haux.setMaxWidth(220);
                        haux.setMinWidth(220);
                        haux.setAlignment(Pos.CENTER);
                        break;
                    case 2:
                        haux.setMaxWidth(280);
                        haux.setMinWidth(280);
                        haux.setAlignment(Pos.CENTER_LEFT);
                        break;
                }

                arrayaux.add(haux);
            }
            minicontents.add(arrayaux);
        }
        relevanceBar = new ArrayList<>(Config.LISTS_SIZE);
        for (int i = 0; i < Config.LISTS_SIZE; ++i) {
            ProgressBar p = new ProgressBar();
//            p.setMaxWidth(125);
//            p.setMinWidth(125);
            relevanceBar.add(p);
        }
        buttonBar = new HBox();
        buttonBar.setPadding(new Insets(50, 0, 0, 375));
        buttonBar.setSpacing(10);


    }

    private void initializeViews(){

        popUp = new PopUp("relations");
        prevButton = new ImageButton("prevButton", 60, 30);
        nextButton = new ImageButton("nextButton", 60, 30);

        firstType = new Label(((RelationshipRelevanceResultPresenter)presenter).getType(1));
        firstType.setFont(titleFont);
        firstType.setTextFill(Paint.valueOf("white"));
        firstType.setTextAlignment(TextAlignment.CENTER);
        secondType = new Label(((RelationshipRelevanceResultPresenter)presenter).getType(2));
        secondType.setFont(titleFont);
        secondType.setTextFill(Paint.valueOf("white"));
        secondType.setTextAlignment(TextAlignment.CENTER);
        relevance = new Label("RELEVANCE");
        relevance.setFont(titleFont);
        relevance.setTextFill(Paint.valueOf("white"));

        separacionSuperioPane = new Pane();


        firstNameWord = new Label("First Name");
        firstNameWord.setFont(titleFont);
        firstNameWord.setTextFill(Paint.valueOf("white"));
        firstNameWord.setTextAlignment(TextAlignment.CENTER);
        secondNameWord = new Label("Second Name");
        secondNameWord.setFont(titleFont);
        secondNameWord.setTextFill(Paint.valueOf("white"));
        secondNameWord.setTextAlignment(TextAlignment.CENTER);
        relevanceWord = new Label("Relevance");
        relevanceWord.setFont(titleFont);
        relevanceWord.setTextFill(Paint.valueOf("white"));

        index = new ArrayList<>(Config.LISTS_SIZE);
        for (int i = 0; i < Config.LISTS_SIZE; ++i) {
            Label laux = new Label();
            laux.setFont(titleFont);
            laux.setTextFill(Paint.valueOf("white"));
           laux.setMaxWidth(60);
            laux.setMinWidth(60);
            index.add(laux);
        }
        nodes = new ArrayList<>(2);
        for (int i = 0; i < 2; ++i) {
            ArrayList<Label> arrayaux = new ArrayList<>(Config.LISTS_SIZE);
            for (int j = 0; j < Config.LISTS_SIZE; ++j) {
                Label laux = new Label();
                laux.setFont(titleFont);
                laux.setTextFill(Paint.valueOf("white"));
                if (i == 0) {
//                    laux.setMaxWidth(250);
//                    laux.setMinWidth(250);
                    laux.setTextAlignment(TextAlignment.RIGHT);
                }
                arrayaux.add(laux);
            }
            nodes.add(arrayaux);
        }
    }

    private void buildPanes(){


        for (int i = 0; i < 3; ++i) {
            switch (i) {
                case 0:
                    topBarContents.get(i).getChildren().add(firstType);
                    break;
                case 1:
                    topBarContents.get(i).getChildren().add(relevance);
                    break;
                case 2:
                    topBarContents.get(i).getChildren().add(secondType);

            }
        }

        topBar.getChildren().addAll(topBarContents);

        for (int i = 0; i < 3; ++i) {
            switch (i) {
                case 0:
                    titleHBoxes.get(i).getChildren().add(firstNameWord);
                    break;
                case 1:
                    titleHBoxes.get(i).getChildren().add(relevanceWord);
                    break;
                case 2:
                    titleHBoxes.get(i).getChildren().add(secondNameWord);

            }
        }

        titleHBox.getChildren().addAll(titleHBoxes);

        separacionSuperioPane.setMinSize(800, 1);
        separacionSuperioPane.setMaxSize(800, 1);
        separacionSuperioPane.setStyle("-fx-background-color: #ffffff");

        for (int i = 0; i < Config.LISTS_SIZE; ++i) {
            minicontents.get(i).get(0).getChildren().addAll(
                    index.get(i),
                    nodes.get(0).get(i)
            );
            minicontents.get(i).get(1).getChildren().add(relevanceBar.get(i));
            minicontents.get(i).get(2).getChildren().add(nodes.get(1).get(i));
            contents.get(i).getChildren().addAll(minicontents.get(i));
        }
        buttonBar.getChildren().addAll(
                prevButton,
                nextButton
        );
        contentVBox.getChildren().add(titleHBox);
        contentVBox.getChildren().add(separacionSuperioPane);
        contentVBox.getChildren().addAll(contents);
        contentVBox.getChildren().add(buttonBar);



        stackPane.getChildren().add(contentVBox);

    }

    private void setListeners() {
        for (int i = 0; i < Config.LISTS_SIZE; ++i) {
            int lol = i;
            contents.get(i).setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ((RelationshipRelevanceResultPresenter)presenter).onClick(lol);
                }
            });

            prevButton.setOnMousePressed(event -> prevButton.press());
            prevButton.setOnMouseReleased(
                    event -> {
                        prevButton.release();
                        ((RelationshipRelevanceResultPresenter) presenter).showLess();
                    }
            );

            nextButton.setOnMousePressed(event -> nextButton.press());
            nextButton.setOnMouseReleased(
                    event -> {
                        nextButton.release();
                        ((RelationshipRelevanceResultPresenter) presenter).showMore();
                    }
            );
            popUp.setListeners(event -> {
                ((RelationshipRelevanceResultPresenter)presenter).onAcceptClick(popUp.getSelected());
            });

        }
        firstNameWord.setOnMouseReleased(
                event -> {
                    ascendingSecondNames = -1;
                    ascendingRelevance = -1;
                    ((RelationshipRelevanceResultPresenter) presenter).reorder(
                            ProjectConstants.NAME_ORDER1,
                            (ascendingFirstNames != 1)
                    );
                    ascendingFirstNames = (ascendingFirstNames != 1) ? 1 : 0;
                    setCorrectOrder();
                }
        );

        secondNameWord.setOnMouseReleased(
                event -> {
                    ascendingFirstNames = -1;
                    ascendingRelevance = -1;
                    ((RelationshipRelevanceResultPresenter) presenter).reorder(
                            ProjectConstants.NAME_ORDER2,
                            (ascendingSecondNames != 1)
                    );
                    ascendingSecondNames = (ascendingSecondNames != 1) ? 1 : 0;
                    setCorrectOrder();
                }
        );

        relevanceWord.setOnMouseReleased(
                event -> {
                    ascendingFirstNames = -1;
                    ascendingSecondNames = -1;
                    ((RelationshipRelevanceResultPresenter) presenter).reorder(
                            ProjectConstants.RELATION_RELEVANCE,
                            (ascendingRelevance != 1)
                    );
                    ascendingRelevance = (ascendingRelevance != 1) ? 1 : 0;
                    setCorrectOrder();
                }
        );

    }

    private void setCorrectOrder() {
        setCorrectOrder(ascendingFirstNames, firstNameWord, "First Name");
        setCorrectOrder(ascendingSecondNames, secondNameWord, "Second Name");
        setCorrectOrder(ascendingRelevance, relevanceWord, "Relevance");
    }

    private void setCorrectOrder(int orderStatus, Label label, String name) {
        switch (orderStatus) {
            case -1:
                label.setText(name + " ▬");
                break;

            case 0:
                label.setText(name + " ▼");
                break;

            case 1:
                label.setText(name + " ▲");
                break;
        }
    }

    public void askSimilarOp() {
        stackPane.getChildren().add(popUp);
        stackPane.getChildren().get(0).setDisable(true);

    }

    public void setContent(int index, String nodeSrc, String nodeDst, double relevance) {


        if (nodeSrc != null ) {
            String[] elements1 = nodeSrc.split("\t");
            String[] elements2 = nodeDst.split("\t");
            this.index.get(index%Config.LISTS_SIZE).setText(Integer.toString(index+1));
            nodes.get(0).get(index%Config.LISTS_SIZE).setText(elements1[0]);
            relevanceBar.get(index%Config.LISTS_SIZE).setDisable(false);
            relevanceBar.get(index % Config.LISTS_SIZE).setVisible(true);
            relevanceBar.get(index%Config.LISTS_SIZE).setProgress(relevance);
            nodes.get(1).get(index%Config.LISTS_SIZE).setText(elements2[0]);
        }
        else {

            this.index.get(index % Config.LISTS_SIZE).setText("");
            nodes.get(0).get(index % Config.LISTS_SIZE).setText("");
            relevanceBar.get(index % Config.LISTS_SIZE).setDisable(true);
            relevanceBar.get(index % Config.LISTS_SIZE).setVisible(false);
            nodes.get(1).get(index % Config.LISTS_SIZE).setText("");
        }

    }
}
