package view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import presentation.RelationshipRelevanceResultPresenter;
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
    Label relevanceWord;

    ArrayList<HBox> contents;
    ArrayList<Label> index;
    ArrayList<ProgressBar> relevanceBar;
    ArrayList<ArrayList<Label>> nodes;

    HBox buttonBar;
    ImageButton prevButton;
    ImageButton nextButton;
    
    StackPane stackPane;

    public RelationshipRelevanceResultView(RelationshipRelevanceResultPresenter relationshipRelevanceResultPresenter) {
        presenter = relationshipRelevanceResultPresenter;
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
       // popUp.setPadding(new Insets(100,100,100,100));
        contentVBox = new VBox();
        topBar = new HBox();
        topBar.setMaxSize(900,110);
        topBar.setMinSize(900,110);
        //topBar.setPadding(new Insets(4,0,4,100));
        topBar.setAlignment(Pos.CENTER);
        topBar.setSpacing(10);
        String actualNodeBG =  this.getClass().getResource("../images/blueTopBar.png").toExternalForm();
        topBar.setStyle(
                "-fx-background-image: url('" + actualNodeBG + "'); " +
                        "-fx-background-position: center center; " +
                        "-fx-background-repeat: stretch;" +
                        "-fx-background-size: 900 110;"
        );
        contents = new ArrayList<>(Config.LISTS_SIZE);
        for (int i = 0; i < Config.LISTS_SIZE; ++i) {
            HBox haux = new HBox();
            haux.setPadding(new Insets(0,50,0,50));
            haux.setSpacing(10);
            contents.add(haux);
        }
        relevanceBar = new ArrayList<>(Config.LISTS_SIZE);
        for (int i = 0; i < Config.LISTS_SIZE; ++i) {
            ProgressBar p = new ProgressBar();
            p.setMaxWidth(125);
            p.setMinWidth(125);
            relevanceBar.add(p);
        }
        buttonBar = new HBox();
        buttonBar.setPadding(new Insets(0, 0, 0, 345));
        buttonBar.setSpacing(10);


    }

    private void initializeViews(){

        popUp = new PopUp("relations");
        prevButton = new ImageButton("prevButton", 60, 30);
        nextButton = new ImageButton("nextButton", 60, 30);

        firstType = new Label(((RelationshipRelevanceResultPresenter)presenter).getType(1));
        firstType.setMinWidth(250);
        firstType.setMaxWidth(250);
        firstType.setFont(titleFont);
        firstType.setTextFill(Paint.valueOf("white"));
        firstType.setTextAlignment(TextAlignment.CENTER);
        secondType = new Label(((RelationshipRelevanceResultPresenter)presenter).getType(2));
        secondType.setMinWidth(295);
        secondType.setMaxWidth(295);
        secondType.setFont(titleFont);
        secondType.setTextFill(Paint.valueOf("white"));
        secondType.setTextAlignment(TextAlignment.CENTER);
        relevanceWord = new Label("RELEVANCE");
        relevanceWord.setMinWidth(125);
        relevanceWord.setMaxWidth(125);
        relevanceWord.setFont(titleFont);
        relevanceWord.setTextFill(Paint.valueOf("white"));
        index = new ArrayList<>(Config.LISTS_SIZE);
        for (int i = 0; i < Config.LISTS_SIZE; ++i) {
            Label laux = new Label();
            laux.setFont(titleFont);
            laux.setTextFill(Paint.valueOf("white"));
            laux.setMaxWidth(40);
            laux.setMinWidth(40);
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
                    laux.setMaxWidth(250);
                    laux.setMinWidth(250);
                    laux.setTextAlignment(TextAlignment.RIGHT);
                }
                arrayaux.add(laux);
            }
            nodes.add(arrayaux);
        }
    }

    private void buildPanes(){
        topBar.getChildren().addAll(
                firstType,
                relevanceWord,
                secondType
        );
        for (int i = 0; i < Config.LISTS_SIZE; ++i) {
            contents.get(i).getChildren().addAll(
                    index.get(i),
                    nodes.get(0).get(i),
                    relevanceBar.get(i),
                    nodes.get(1).get(i)
            );
        }
        buttonBar.getChildren().addAll(
                prevButton,
                nextButton
        );
        HBox haux = new HBox();
        haux.setMaxHeight(50);
        haux.setMinHeight(50);
        contentVBox.getChildren().add(haux);
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
            relevanceBar.get(index%Config.LISTS_SIZE).setProgress(relevance);
            nodes.get(1).get(index%Config.LISTS_SIZE).setText(elements2[0]);
        }
        else {

            this.index.get(index % Config.LISTS_SIZE).setText("");
            nodes.get(0).get(index % Config.LISTS_SIZE).setText("");
            relevanceBar.get(index % Config.LISTS_SIZE).setDisable(true);
            nodes.get(1).get(index % Config.LISTS_SIZE).setText("");
        }

    }
}
