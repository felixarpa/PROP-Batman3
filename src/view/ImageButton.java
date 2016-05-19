package view;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ImageButton extends Button {

    private int width, height;

    public ImageButton(String path, int width, int height) {
        this.width = width;
        this.height = height;
        String image = getClass().getResource(path).toExternalForm();
        setMaxSize(width, height);
        setMinSize(width, height);
        setStyle(
                "-fx-background-image: url('" + image + "');" +
                "-fx-background-size: " + width + " " + height + ";" +
                "-fx-background-color: transparent"
        );
    }


    public void changeButtonImage(String path) {
        String image = getClass().getResource(path).toExternalForm();
        setMaxSize(width, height);
        setMaxSize(width, height);
        setStyle(
                "-fx-background-image: url('" + image + "');" +
                "-fx-background-size: " + width + " " + height + ";" +
                "-fx-background-color: transparent"
        );
    }

}
