package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.*;

public class Predictor extends VBox {

    private TextField textToPredict;
    private Collection<String> data;
    private VBox resultBox;
    private String actualWord;

    private int resultsToShow;
    private int lastLength;
    private int selected;

    public Predictor(Collection<String> data, int resultsToShow, Insets padding) {
        this.resultsToShow = resultsToShow;
        this.data = data;

        textToPredict = new TextField();
        resultBox = new VBox();
        this.setPadding(padding);
        resultBox.setStyle("-fx-background-color: #FFFFFF;");
        lastLength = -1;
        selected = -1;
        getChildren().add(textToPredict);
        getChildren().add(resultBox);

        textToPredict.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.UP) {
                if (selected > 0) {
                    ((Text)resultBox.getChildren().get(selected)).setFill(Color.BLACK);
                    --selected;
                    ((Text)resultBox.getChildren().get(selected)).setFill(Color.GRAY);
                }
            }
            else if (event.getCode() == KeyCode.DOWN) {
                if (selected < resultBox.getChildren().size()-1) {
                    if (selected >= 0)((Text)resultBox.getChildren().get(selected)).setFill(Color.BLACK);
                    ++selected;
                    ((Text)resultBox.getChildren().get(selected)).setFill(Color.GRAY);
                }
            }
            else if (event.getCode() == KeyCode.ENTER) {
                if (selected >= 0 && selected < resultBox.getChildren().size()) {
                    textToPredict.setText(((Text)resultBox.getChildren().get(selected)).getText());

                    if (selected >= 0 && selected < resultBox.getChildren().size()) {
                        textToPredict.setText(((Text)resultBox.getChildren().get(selected)).getText());
                        resultBox.getChildren().remove(0,resultBox.getChildren().size());
                    }
                }
                selected = -1;
            }
            else {
                actualWord = textToPredict.getText();
                if (actualWord.length() == 0) {
                    resultBox.getChildren().remove(0, resultBox.getChildren().size());
                } else if (actualWord.length() != lastLength) {
                    slowStep();
                }
                lastLength = actualWord.length();
                selected = -1;
            }
        });

    }

    private void slowStep() {
        int count = 0;
        resultBox.getChildren().remove(0,resultBox.getChildren().size());
        for (String word : data) {
            if (word.contains(actualWord)) {
                if (count < resultsToShow) {
                    Text predicted = new Text(word);
                    resultBox.getChildren().add(predicted);
                    ++count;
                }
                else return;
            }
        }
    }

    public void setTextListener(EventHandler<ActionEvent> eventListener) {
        textToPredict.setOnAction(
                event -> {
                    if (selected == -1) eventListener.handle(null);
                }
        );
    }

    public String getText() {
        return textToPredict.getText();
    }

}
