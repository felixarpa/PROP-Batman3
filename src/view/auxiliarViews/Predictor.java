package view.auxiliarViews;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
    private boolean typed;

    public Predictor(Collection<String> data, int resultsToShow, Insets padding, String textToShow) {
        this.resultsToShow = resultsToShow;
        this.data = data;
        typed = false;

        textToPredict = new TextField();
        textToPredict.setText(textToShow);
        textToPredict.setStyle("-fx-text-fill: #948598;");
        resultBox = new VBox();
        setPadding(padding);
        resultBox.setStyle("-fx-background-color: #FFFFFF;");
        lastLength = -1;
        selected = -1;
        getChildren().add(textToPredict);
        getChildren().add(resultBox);

        textToPredict.setOnKeyReleased(event -> {
            if (!typed) {
                textToPredict.setStyle("-fx-text-fill: #000000;");
                typed = true;
            }
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

        textToPredict.setOnMousePressed(event -> {
            if (!typed) {
                textToPredict.setText("");
                textToPredict.setStyle("-fx-text-fill: #000000;");
                typed = true;
            }
        });

    }

    private void slowStep() {
        TreeMap<Integer, LinkedList<String>> map = new TreeMap<>();
        resultBox.getChildren().remove(0,resultBox.getChildren().size());
        for (String word : data) {
            int index = word.indexOf(actualWord);
            if (index >= 0) {
                LinkedList<String> linkedList = map.get(index);
                if (linkedList == null) {
                    linkedList = new LinkedList<>();
                    map.put(index, linkedList);
                }
                linkedList.add(word);
            }
        }
        int count = 0;
        for (LinkedList<String> list : map.values()) {
            for (String string : list) {
                if (count < resultsToShow) {
                    resultBox.getChildren().add(new Text(string));
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
