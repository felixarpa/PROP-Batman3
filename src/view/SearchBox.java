package view;

import domain.DomainController;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Dictionary;

public class SearchBox extends TextField {

    ArrayList<String> dictionary;
    ArrayList<String> actualResult;
    int previousSize;

    public SearchBox(ArrayList<String> arrayList) {
        dictionary = arrayList;
        previousSize = 0;
    }

    private void setListeners() {
        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (getText().length() - previousSize > 0) {

                }
                else if (getText().length() - previousSize < 0) {

                }
            }
        });
    }

    private void fillActualText(String s) {

    }



}
