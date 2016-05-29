package view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import presentation.FilteredSearchPresenter;
import util.ProjectConstants;
import view.auxiliarViews.Config;
import view.auxiliarViews.ImageButton;

import java.util.ArrayList;

/**
 * Created by mario.fernandez on 25/05/2016.
 */
public abstract class FilteredSearchView extends BaseView {

    //TODO: Padding de las HBOXES; añadir el nodo arriba del todo, añadir listeners, añadir botones de show more y show less, cambiar fuentes.

    protected VBox contentVBox;
    protected ArrayList<VBox> contents;
    protected HBox line;


    protected Label authorText;
    protected Label conferenceText;
    protected Label paperText;
    protected Label termText;

    protected ArrayList<ArrayList<Label>> number;
    protected ArrayList<ArrayList<Label>> name;
    protected ArrayList<ArrayList<Label>> id;
    protected ArrayList<ArrayList<HBox>> relevance;
    protected ArrayList<ArrayList<Label>> label;
    protected Font font;
    protected Font titleFont;

    protected HBox actualNodeBox;
    protected Label actualNodeName;
    protected Label actualNodeId;
    protected Label actualNodeRelevance;
    protected Label actualNodeLabel;

    private HBox separacionSuperioPane;

    private HBox titlesHBox;
    private Label nameLabel;
    private Label idLabel;
    private Label relevanceLabel;
    private Label labelLabel;

    protected ImageButton next;
    protected ImageButton prev;

    private HBox buttonsPane;

    public final static int numToShow = 3;

    public FilteredSearchView(FilteredSearchPresenter filteredSearchPresenter) {
        presenter = filteredSearchPresenter;
        initializeFonts();
        initializePanes();
        initializeViews();
        buildPanes();
        setListeners();
        topBarPane.setCenter(contentVBox);

    }

    private void initializeFonts() {
        font = Font.loadFont(this.getClass().getResource("../fonts/Nilland-Black.ttf").toExternalForm(), 14);
        titleFont = Font.loadFont(this.getClass().getResource("../fonts/Nilland-Black.ttf").toExternalForm(),18);
    }

    private void initializeTitleLabels() {
        nameLabel.setTextFill(Config.LABEL_CLEAR_COLOR);
        nameLabel.setMinSize(400, 20);
        nameLabel.setMaxSize(400, 24);
        nameLabel.setFont(new Font("Arial bold", 18));

        idLabel.setTextFill(Config.LABEL_CLEAR_COLOR);
        idLabel.setMinSize(100, 20);
        idLabel.setMaxSize(100, 24);
        idLabel.setFont(new Font("Arial bold", 18));

        relevanceLabel.setTextFill(Config.LABEL_CLEAR_COLOR);
        relevanceLabel.setMinSize(100, 20);
        relevanceLabel.setMaxSize(100, 24);
        relevanceLabel.setFont(new Font("Arial bold", 18));

        labelLabel.setTextFill(Config.LABEL_CLEAR_COLOR);
        labelLabel.setMinSize(75, 20);
        labelLabel.setMaxSize(75, 24);
        labelLabel.setFont(new Font("Arial bold", 18));
    }

    private void initializePanes() {

        contentVBox = new VBox();
        contents = new ArrayList<>(4);
        titlesHBox = new HBox();
        titlesHBox.setPadding(new Insets(0, 10, 0, 100));
        separacionSuperioPane = new HBox();
        separacionSuperioPane.setPadding(new Insets(0, 60, 0, 50));
        buttonsPane = new HBox();
        //HEY!
        buttonsPane.setPadding(new Insets(0, 0, 0, 345));
        buttonsPane.setSpacing(10);

    }

    private void initializeViews() {
        authorText = new Label("RELATED AUTHORS");
        authorText.setTextFill(Paint.valueOf("white"));
        authorText.setFont(titleFont);
        conferenceText = new Label("RELATED CONFERENCES");
        conferenceText.setTextFill(Paint.valueOf("white"));
        conferenceText.setFont(titleFont);
        paperText = new Label("RELATED PAPERS");
        paperText.setTextFill(Paint.valueOf("white"));
        paperText.setFont(titleFont);
        termText = new Label("RELATED TERMS");
        termText.setTextFill(Paint.valueOf("white"));
        termText.setFont(titleFont);
        number = initializeArrayLabel();
        name = initializeArrayLabel();
        id = initializeArrayLabel();
        relevance = initializeRelevanceArray();
        label = initializeArrayLabel();
        nameLabel = new Label("Name");
        idLabel = new Label("ID");
        relevanceLabel = new Label("Relevance");
        labelLabel = new Label("Label");
        initializeTitleLabels();
        prev = new ImageButton("../images/", "prevButton", 60, 30);
        next = new ImageButton("../images/", "nextButton", 60, 30);
    }

    private void buildPanes() {
        for (int i = 0; i < 4; ++i) {
            VBox vaux = new VBox();

            HBox haux = new HBox();
            haux.setPadding(new Insets(0,0,0,50));

            switch (i) {
                case ProjectConstants.AUTHOR_TYPE:
                    haux.getChildren().add(authorText);
                    break;
                case ProjectConstants.CONFERENCE_TYPE:
                    haux.getChildren().add(conferenceText);
                    break;
                case ProjectConstants.PAPER_TYPE:
                    haux.getChildren().add(paperText);
                    break;
                case ProjectConstants.TERM_TYPE:
                    haux.getChildren().add(termText);
                    break;
            }
            vaux.getChildren().add(haux);
            for (int j = 0; j < numToShow; ++j) {
                haux = new HBox();
                haux.setPadding(new Insets(4,0,4,50));
                haux.getChildren().add(number.get(i).get(j));
                haux.getChildren().add(name.get(i).get(j));
                haux.getChildren().add(label.get(i).get(j));
                haux.getChildren().add(id.get(i).get(j));
                haux.getChildren().add(relevance.get(i).get(j));

                vaux.getChildren().add(haux);

            }

            if(i<3){
                HBox aux = new HBox();
                line = new HBox();
                line.setAlignment(Pos.CENTER);
                line.setMinSize(800,1);
                line.setMaxSize(800,1);
                line.setStyle("-fx-background-color: #ffffff;");
                aux.getChildren().add(line);
                aux.setMinSize(900,1);
                aux.setMinSize(900,1);
                aux.setPadding(new Insets(0,0,0,50));

                vaux.getChildren().add(aux);
            }
            contents.add(vaux);


        }
        contentVBox.getChildren().addAll(contents);
        titlesHBox.getChildren().addAll(
                nameLabel,
                labelLabel,
                idLabel,
                relevanceLabel

        );
        HBox haux = new HBox();
        haux.setMaxSize(830,1);
        haux.setMinSize(830,1);
        haux.setStyle("-fx-background-color: #ffffff");
        buttonsPane.getChildren().addAll(
                prev,
                next
        );
        separacionSuperioPane.getChildren().add(haux);
    }

    private void setListeners() {
        authorText.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((FilteredSearchPresenter)presenter).setType(ProjectConstants.AUTHOR_TYPE);
            }
        });
        conferenceText.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((FilteredSearchPresenter)presenter).setType(ProjectConstants.CONFERENCE_TYPE);
            }
        });
        paperText.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((FilteredSearchPresenter)presenter).setType(ProjectConstants.PAPER_TYPE);
            }
        });
        termText.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((FilteredSearchPresenter)presenter).setType(ProjectConstants.TERM_TYPE);
            }
        });

        prev.setOnMousePressed(event -> prev.press());
        prev.setOnMouseReleased(
                event -> {
                    prev.release();
                    ((FilteredSearchPresenter) presenter).showLess();
                }
        );

        next.setOnMousePressed(event -> next.press());
        next.setOnMouseReleased(
                event -> {
                    next.release();
                    ((FilteredSearchPresenter) presenter).showMore();
                }
        );
    }

    protected abstract void initializeActualNode();

    private ArrayList<ArrayList<Label>> initializeArrayLabel() {
        ArrayList<ArrayList<Label>> arrayList = new ArrayList<>(4);
        for (int i = 0; i < 4; ++i) {
            ArrayList<Label> aux = new ArrayList<>(numToShow);
            for (int j = 0; j < numToShow; ++j) {
                Label laux = new Label();
                laux.setTextFill(Paint.valueOf("white"));
                laux.setFont(font);
                //laux.setFont(new Font("Microsoft Sans Serif",15));
                aux.add(laux);
            }
            arrayList.add(aux);
        }
        return arrayList;
    }

    protected  ArrayList<ArrayList<HBox>> initializeRelevanceArray() {
        ArrayList<ArrayList<HBox>> arrayList = new ArrayList<>(4);
        for (int i = 0; i < 4; ++i) {
            ArrayList<HBox> aux = new ArrayList<>(numToShow);
            for (int j = 0; j < numToShow; ++j) {
                HBox haux = new HBox();
                //laux.setFont(new Font("Microsoft Sans Serif",15));
                aux.add(haux);
            }
            arrayList.add(aux);
        }
        return arrayList;
    }

    public abstract void setContent(int index, String node, int type, int listSize);

    public void changeType(int type) {
        for (int i = 0; i < Config.LISTS_SIZE - numToShow; ++i) {
            ArrayList<Label> laux = new ArrayList<>(5);
            for (int j = 0; j < 5; ++j) {
                Label l = new Label();
                l.setFont(new Font(20));
                l.setTextFill(Paint.valueOf("white"));
                l.setFont(font);
                //l.setFont(new Font("Microsoft Sans Serif",15));
                laux.add(l);
            }
            number.get(type).add(laux.get(0));
            name.get(type).add(laux.get(1));
            id.get(type).add(laux.get(2));
            relevance.get(type).add(new HBox());
            label.get(type).add(laux.get(4));
        }
        contentVBox.getChildren().removeAll(contents);
        VBox vaux = new VBox();



        HBox haux = new HBox();
        haux.setPadding(new Insets(0,0,0,50));

        switch (type) {
            case ProjectConstants.AUTHOR_TYPE:
                haux.getChildren().add(authorText);
                authorText.setOnMouseReleased(null);
                break;
            case ProjectConstants.CONFERENCE_TYPE:
                haux.getChildren().add(conferenceText);
                conferenceText.setOnMouseReleased(null);
                break;
            case ProjectConstants.PAPER_TYPE:
                haux.getChildren().add(paperText);
                paperText.setOnMouseReleased(null);
                break;
            case ProjectConstants.TERM_TYPE:
                haux.getChildren().add(termText);
                termText.setOnMouseReleased(null);
                break;
        }
        vaux.getChildren().add(haux);
        haux = new HBox();
        haux.setMinSize(0,20);
        haux.setMaxSize(0,20);
        vaux.getChildren().add(haux);
        vaux.getChildren().add(titlesHBox);
        vaux.getChildren().add(separacionSuperioPane);
        for (int j = 0; j < Config.LISTS_SIZE; ++j) {
            haux = new HBox();
            haux.setPadding(new Insets(4,0,4,50));
            haux.getChildren().add(number.get(type).get(j));
            haux.getChildren().add(name.get(type).get(j));
            haux.getChildren().add(label.get(type).get(j));
            haux.getChildren().add(id.get(type).get(j));
            haux.getChildren().add(relevance.get(type).get(j));
            vaux.getChildren().add(haux);

        }
        contentVBox.getChildren().add(vaux);
        contentVBox.getChildren().add(buttonsPane);
    }



}
