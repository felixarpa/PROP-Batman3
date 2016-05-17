package view;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ImageButton extends Pane {

    private Button button;

    public ImageButton(String path, int width, int height, Class c) {
        String image = c.getResource(path).toExternalForm();
        button = new Button();
        button.setMaxSize(width, height);
        button.setMaxSize(width, height);
        button.setStyle(
                "-fx-background-image: url('" + image + "');" +
                "-fx-background-size: " + width + " " + height + ";" +
                "-fx-background-color: transparent"
        );
    }

    public Button getButton() {
        return button;
    }

    public void setOnClickListener(EventHandler<MouseEvent> action) {
        button.setOnMouseClicked(action);
    }

    public void changeButtonImage(String path, int width, int height, Class c) {
        String image = c.getResource(path).toExternalForm();
        button.setMaxSize(width, height);
        button.setMaxSize(width, height);
        button.setStyle(
                "-fx-background-image: url('" + image + "');" +
                "-fx-background-size: " + width + " " + height + ";" +
                "-fx-background-color: transparent"
        );
    }

}
