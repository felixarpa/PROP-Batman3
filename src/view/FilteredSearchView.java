package view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import presentation.FilteredSearchPresenter;
import util.ProjectConstants;

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
    protected ArrayList<ArrayList<Label>> relevance;
    protected ArrayList<ArrayList<Label>> label;
    protected Font font;
    protected Font titleFont;

    protected Button next;
    protected Button prev;

    public final static int numToShow = 4;


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

    private void initializePanes() {
        topBarPane.setTop(null);
        contentVBox = new VBox();
        contents = new ArrayList<>(4);


    }

    private ArrayList<ArrayList<Label>> initializeArrayLabel() {
        ArrayList<ArrayList<Label>> arrayList = new ArrayList<>(4);
        for (int i = 0; i < 4; ++i) {
            ArrayList<Label> aux = new ArrayList<>(numToShow);
            for (int j = 0; j < numToShow; ++j) {
                Label laux = new Label();
                laux.setFont(new Font(20));
                laux.setTextFill(Paint.valueOf("white"));
                laux.setFont(font);
                //laux.setFont(new Font("Microsoft Sans Serif",15));
                aux.add(laux);
            }
            arrayList.add(aux);
        }
        return arrayList;
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
        relevance = initializeArrayLabel();
        label = initializeArrayLabel();
        next = new Button("Next");
        prev = new Button("Prev");
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

        next.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((FilteredSearchPresenter)presenter).showMore();
            }
        });

        prev.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((FilteredSearchPresenter)presenter).showLess();
            }
        });
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
            relevance.get(type).add(laux.get(3));
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
        HBox hbox = new HBox();
        hbox.getChildren().add(prev);
        hbox.getChildren().add(next);
        contentVBox.getChildren().add(hbox);
    }

}
