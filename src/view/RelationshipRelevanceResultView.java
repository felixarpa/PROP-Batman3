package view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import presentation.RelationshipRelevanceResultPresenter;

import java.util.ArrayList;

public class RelationshipRelevanceResultView extends BaseView {

    VBox contentVBox;

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
    

    public RelationshipRelevanceResultView(RelationshipRelevanceResultPresenter relationshipRelevanceResultPresenter) {
        presenter = relationshipRelevanceResultPresenter;
        initializePanes();
        initializeViews();
        buildPanes();
        setListeners();
        topBarPane.setCenter(contentVBox);
        topBarPane.setTop(topBar);
    }

    private void initializePanes() {
        contentVBox = new VBox();
        topBar = new HBox();
        topBar.setMaxSize(900,110);
        topBar.setMinSize(900,110);
        topBar.setPadding(new Insets(4,0,4,100));
        topBar.setAlignment(Pos.CENTER_LEFT);
        String actualNodeBG =  this.getClass().getResource("../images/blueTopBar.png").toExternalForm();
        topBar.setStyle(
                "-fx-background-image: url('" + actualNodeBG + "'); " +
                        "-fx-background-position: center center; " +
                        "-fx-background-repeat: stretch;" +
                        "-fx-background-size: 900 110;"
        );
        contents = new ArrayList<>(Config.LISTS_SIZE);
        for (int i = 0; i < Config.LISTS_SIZE; ++i) contents.add(new HBox());
        relevanceBar = new ArrayList<>(Config.LISTS_SIZE);
        for (int i = 0; i < Config.LISTS_SIZE; ++i) contents.add(new HBox());
        buttonBar = new HBox();
        prevButton = new ImageButton("../images/", "prevButton", 60, 30);
        nextButton = new ImageButton("../images/", "nextButton", 60, 30);

    }

    private void initializeViews(){
        firstType = new Label(((RelationshipRelevanceResultPresenter)presenter).getType(1));
        secondType = new Label(((RelationshipRelevanceResultPresenter)presenter).getType(2));
        relevanceWord = new Label("RELEVANCE");
        index = new ArrayList<>(Config.LISTS_SIZE);
        for (int i = 0; i < Config.LISTS_SIZE; ++i) index.add(new Label());
        nodes = new ArrayList<>(2);
        for (int i = 0; i < 2; ++i) {
            ArrayList<Label> arrayaux = new ArrayList<>(Config.LISTS_SIZE);
            for (int j = 0; j < Config.LISTS_SIZE; ++j) arrayaux.add(new Label());
            nodes.add(arrayaux);
        }
    }

    private void buildPanes(){
        topBar.getChildren().addAll(
                firstType,
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
        contentVBox.getChildren().addAll(contents);
        contentVBox.getChildren().add(buttonBar);

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
        }
    }

    public void askSimilarOp() {

    }

    public void setContent(int index, String nodeSrc, String nodeDst, double relevance) {
        String[] elements = node.split("\t");
        if (!elements[0].equals("")) {
            number.get(type).get(index%listSize).setText(Integer.toString(index+1));
            number.get(type).get(index%listSize).setMinWidth(50);
            number.get(type).get(index%listSize).setMaxWidth(50);
            //number.get(type).get(index%listSize).setPadding(new Insets(0,0,0,0));
            id.get(type).get(index%listSize).setText(elements[1]);
            id.get(type).get(index%listSize).setMinWidth(100);
            id.get(type).get(index%listSize).setMaxWidth(100);
            //id.get(type).get(index%listSize).setPadding(new Insets(0,50,0,0));
            Label laux = new Label();
            laux.setFont(font);
            laux.setTextFill(Paint.valueOf("white"));
            laux.setText(elements[2]);
            laux.setMinWidth(300);
            laux.setMaxWidth(300);
            relevance.get(type).get(index%listSize).getChildren().clear();
            relevance.get(type).get(index%listSize).getChildren().add(laux);
            //relevance.get(type).get(index%listSize).setPadding(new Insets(0,50,0,0));

            label.get(type).get(index%listSize).setText(elements[3]);
            label.get(type).get(index%listSize).setMinWidth(75);
            label.get(type).get(index%listSize).setMaxWidth(75);
            //label.get(type).get(index%listSize).setPadding(new Insets(0,50,0,0));
        }
        else {
            number.get(type).get(index%listSize).setText("");
            number.get(type).get(index%listSize).setMinWidth(10);
            number.get(type).get(index%listSize).setMaxWidth(10);
            //number.get(type).get(index%listSize).setPadding(new Insets(0,50,0,0));
            id.get(type).get(index%listSize).setText("");
            id.get(type).get(index%listSize).setMinWidth(100);
            id.get(type).get(index%listSize).setMaxWidth(100);
            //id.get(type).get(index%listSize).setPadding(new Insets(0,50,0,0));
            Label laux = new Label();
            laux.setText("");
            laux.setMinWidth(300);
            laux.setMaxWidth(300);
            relevance.get(type).get(index%listSize).getChildren().clear();
            relevance.get(type).get(index%listSize).getChildren().add(laux);
            //relevance.get(type).get(index%listSize).setPadding(new Insets(0,50,0,0));
            label.get(type).get(index%listSize).setText("");
            label.get(type).get(index%listSize).setMinWidth(75);
            label.get(type).get(index%listSize).setMaxWidth(75);
            //label.get(type).get(index%listSize).setPadding(new Insets(0,50,0,0));
        }
        name.get(type).get(index%listSize).setText(elements[0]);
        name.get(type).get(index%listSize).setMinWidth(400);
        name.get(type).get(index%listSize).setMaxWidth(400);
        //name.get(type).get(index%listSize).setPadding(new Insets(0,50,0,0));
    }
}
