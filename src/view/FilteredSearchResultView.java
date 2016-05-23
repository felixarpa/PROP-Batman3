package view;


import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import presentation.FilteredRelevanceResultPresenter;
import presentation.FilteredSearchResultPresenter;
import util.ProjectConstants;

import java.util.ArrayList;

public class FilteredSearchResultView extends BaseView {

    //TODO: Padding de las HBOXES; añadir el nodo arriba del todo, añadir listeners, añadir botones de show more y show less, cambiar fuentes.

    private VBox contentVBox;
    private ArrayList<VBox> contents;


    private Label authorText;
    private Label conferenceText;
    private Label paperText;
    private Label termText;

    private ArrayList<ArrayList<Label>> number;
    private ArrayList<ArrayList<Label>> name;
    private ArrayList<ArrayList<Label>> id;
    private ArrayList<ArrayList<Label>> relevance;
    private ArrayList<ArrayList<Label>> label;


    public final static int numToShow = 4;


    public FilteredSearchResultView(FilteredSearchResultPresenter filteredSearchResultPresenter) {
        presenter = filteredSearchResultPresenter;
        initializePanes();
        initializeViews();
        buildPanes();
        setListeners();
        topBarPane.setCenter(contentVBox);

    }

    private void initializePanes() {
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
                laux.setFont(new Font("Microsoft Sans Serif",15));
                aux.add(laux);
            }
            arrayList.add(aux);
        }
        return arrayList;
    }

    private void initializeViews() {
        authorText = new Label("RELATED AUTHORS");
        authorText.setTextFill(Paint.valueOf("white"));
        conferenceText = new Label("RELATED CONFERENCES");
        conferenceText.setTextFill(Paint.valueOf("white"));
        paperText = new Label("RELATED PAPERS");
        paperText.setTextFill(Paint.valueOf("white"));
        termText = new Label("RELATED TERMS");
        termText.setTextFill(Paint.valueOf("white"));
        number = initializeArrayLabel();
        name = initializeArrayLabel();
        id = initializeArrayLabel();
        relevance = initializeArrayLabel();
        label = initializeArrayLabel();
    }

    private void buildPanes() {
        for (int i = 0; i < 4; ++i) {
            VBox vaux = new VBox();
            HBox haux = new HBox();
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
                haux.getChildren().add(number.get(i).get(j));
                haux.getChildren().add(name.get(i).get(j));
                haux.getChildren().add(id.get(i).get(j));
                haux.getChildren().add(relevance.get(i).get(j));
                haux.getChildren().add(label.get(i).get(j));
                vaux.getChildren().add(haux);
            }

            contents.add(vaux);

        }
        contentVBox.getChildren().addAll(contents);
    }

    private void setListeners() {
    }

    public void setContent(int index, String node, int type) {
        String[] elements = node.split("\t");
        number.get(type).get(index%numToShow).setText(Integer.toString(index+1));
        name.get(type).get(index%numToShow).setText(elements[0]);
        id.get(type).get(index%numToShow).setText(elements[1]);
        relevance.get(type).get(index%numToShow).setText(elements[2]);
        label.get(type).get(index%numToShow).setText(elements[3]);
    }

}
