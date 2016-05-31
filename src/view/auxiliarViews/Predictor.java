package view.auxiliarViews;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import sun.awt.image.ImageWatched;
import sun.awt.image.IntegerComponentRaster;

import javax.swing.text.LabelView;
import java.util.*;

public class Predictor extends VBox {

    private TextField textToPredict;
    private List<String> data;
    private ArrayList<String> nameList;
    private ArrayList<String> idList;
    private ArrayList<String> types;
    private ArrayList<Integer> actualndex;
    private VBox resultBox;
    private String actualWord;

    private int resultsToShow;
    private int lastLength;
    private int selected;
    private boolean typed;

    public Predictor(List<String> data, String split, int resultsToShow, Insets padding, String textToShow) {
        initialize(resultsToShow, padding, textToShow);
        nameList = new ArrayList<>(data.size());
        idList = new ArrayList<>(data.size());
        types = new ArrayList<>(data.size());
        actualndex = new ArrayList<>(resultsToShow);
        for (int i = 0; i < resultsToShow; ++i) actualndex.add(-1);
        for (String string : data) {
            String[] elements = string.split(split);
            nameList.add(elements[0]);
            idList.add(elements[1]);
            types.add(elements[2]);
        }
        textToPredict.setOnKeyReleased(event -> {
            if (!typed) {
                textToPredict.setStyle("-fx-text-fill: #000000;");
                typed = true;
            }
            if (event.getCode() == KeyCode.UP) {
                if (selected > 0) {
                    resultBox.getChildren().get(selected).setStyle("-fx-background-color: transparent;");
                    --selected;
                    resultBox.getChildren().get(selected).setStyle("-fx-background-color: #948598");
                }
            }
            else if (event.getCode() == KeyCode.DOWN) {
                if (selected < resultBox.getChildren().size()-1) {
                    if (selected >= 0) {
                        resultBox.getChildren().get(selected).setStyle("-fx-background-color: transparent;");
                    }
                    ++selected;
                    resultBox.getChildren().get(selected).setStyle("-fx-background-color: #948598");
                }
            }
            else if (event.getCode() == KeyCode.ENTER) {
                if (selected >= 0 && selected < resultBox.getChildren().size()) {
                    textToPredict.setText(((Label)((HBox)resultBox.getChildren().get(selected)).getChildren().get(0)).getText());

                    if (selected >= 0 && selected < resultBox.getChildren().size()) {
                        textToPredict.setText(((Label)((HBox)resultBox.getChildren().get(selected)).getChildren().get(0)).getText());
                        resultBox.getChildren().remove(0, resultBox.getChildren().size());
                    }
                }
            }
            else {
                actualWord = textToPredict.getText();
                if (actualWord.length() == 0) {
                    resultBox.getChildren().remove(0, resultBox.getChildren().size());
                } else if (actualWord.length() != lastLength) {
                    slowStep2();
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

    public Predictor(List<String> data, int resultsToShow, Insets padding, String textToShow) {
        this.data = data;
        initialize(resultsToShow, padding, textToShow);
        textToPredict.setOnKeyReleased(event -> {
            if (!typed) {
                textToPredict.setStyle("-fx-text-fill: #000000;");
                typed = true;
            }
            if (event.getCode() == KeyCode.UP) {
                if (selected > 0) {
                    resultBox.getChildren().get(selected).setStyle("-fx-background-color: transparent");
                    --selected;
                    resultBox.getChildren().get(selected).setStyle("-fx-background-color: #948598");
                }
            }
            else if (event.getCode() == KeyCode.DOWN) {
                if (selected < resultBox.getChildren().size()-1) {
                    if (selected >= 0) resultBox.getChildren().get(selected).setStyle("-fx-background-color: transparent");
                    ++selected;
                    resultBox.getChildren().get(selected).setStyle("-fx-background-color: #948598");
                }
            }
            else if (event.getCode() == KeyCode.ENTER) {
                if (selected >= 0 && selected < resultBox.getChildren().size()) {
                    HBox hBox = (HBox) resultBox.getChildren().get(selected);
                    textToPredict.setText(((Label)hBox.getChildren().get(0)).getText());
                    resultBox.getChildren().remove(0,resultBox.getChildren().size());
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

    private void initialize(int resultsToShow, Insets padding, String textToShow) {
        this.resultsToShow = resultsToShow;
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
    }

    private void slowStep() {
        TreeMap<Integer, LinkedList<Integer>> map = new TreeMap<>();
        resultBox.getChildren().remove(0,resultBox.getChildren().size());
        int i = 0;
        for (String word : data) {
            int index = word.indexOf(actualWord);
            if (index >= 0) {
                LinkedList<Integer> linkedList = map.get(index);
                if (linkedList == null) {
                    linkedList = new LinkedList<>();
                    map.put(index, linkedList);
                }
                linkedList.add(i);
            }
            ++i;
        }
        int count = 0;
        for (LinkedList<Integer> list : map.values()) {
            for (int string : list) {
                if (count < resultsToShow) {
                    int lol = count;
                    HBox hbox = new HBox();
                    Label label = new Label(data.get(string));
                    hbox.getChildren().add(label);
                    hbox.setOnMouseClicked(event -> {
                        HBox hBox = (HBox) resultBox.getChildren().get(lol);
                        textToPredict.setText(((Label)hBox.getChildren().get(0)).getText());
                        resultBox.getChildren().remove(0,resultBox.getChildren().size());
                    });
                    hbox.setOnMouseEntered(event -> {
                        if (selected >= 0 && selected < resultBox.getChildren().size()) resultBox.getChildren().get(selected).setStyle("-fx-background-color: transparent");
                        selected = lol;
                        resultBox.getChildren().get(selected).setStyle("-fx-background-color: #948598");
                    });
                    resultBox.getChildren().add(hbox);
                    ++count;
                }
                else return;
            }
        }
    }

    private void slowStep2() {
        TreeMap<Integer, LinkedList<Integer>> map = new TreeMap<>();
        resultBox.getChildren().remove(0,resultBox.getChildren().size());
        for (int i = 0; i < nameList.size(); ++i) {
            int index = nameList.get(i).indexOf(actualWord);
            if (index >= 0) {
                LinkedList<Integer> linkedList = map.get(index);
                if (linkedList == null) {
                    linkedList = new LinkedList<>();
                    map.put(index, linkedList);
                }
                linkedList.add(i);
            }
        }
        int count = 0;
        for (LinkedList<Integer> list : map.values()) {
            for (int index : list) {
                if (count < resultsToShow) {
                    HBox hbox = new HBox();
                    Label name = new Label(nameList.get(index));
                    name.setMaxWidth(getWidth()-40);
                    name.setMinWidth(getWidth()-40);
                    Label id = new Label(idList.get(index));
                    id.setMaxWidth(40);
                    id.setMinWidth(40);
                    hbox.getChildren().add(name);
                    hbox.getChildren().add(id);
                    int lol = count;
                    hbox.setOnMouseClicked(event -> {
                        HBox hBox = (HBox) resultBox.getChildren().get(lol);
                        textToPredict.setText(((Label)hBox.getChildren().get(0)).getText());
                        resultBox.getChildren().remove(0,resultBox.getChildren().size());
                    });
                    hbox.setOnMouseEntered(event -> {
                        if (selected >= 0 && selected < resultBox.getChildren().size()) resultBox.getChildren().get(selected).setStyle("-fx-background-color: transparent");
                        selected = lol;
                        resultBox.getChildren().get(selected).setStyle("-fx-background-color: #948598");
                    });

                    resultBox.getChildren().add(hbox);
                    actualndex.set(count, index);
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

    public String getNameSelected() {
        return (selected >= 0 ? nameList.get(actualndex.get(selected)) : "");
    }

    public String getIdSelected() {
        return (selected >= 0 ? idList.get(actualndex.get(selected)) : "");
    }

    public String getTypeSelected() {
        return (selected >= 0 ? types.get(actualndex.get(selected)) : "");
    }

    public String getText() {
        return textToPredict.getText();
    }

}
