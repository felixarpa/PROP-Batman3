package view;

import javafx.scene.control.Button;

public class ImageButton extends Button {

    private int width, height;
    private String name, path;

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

    public ImageButton(String path, String name, int width, int height) {
        this.path = path;
        this.name = name;
        this.width = width;
        this.height = height;
        String image = getClass().getResource(path + "/" + name + ".png").toExternalForm();
        setMaxSize(width, height);
        setMinSize(width, height);
        setStyle(
                "-fx-background-image: url('" + image + "');" +
                        "-fx-background-size: " + width + " " + height + ";" +
                        "-fx-background-color: transparent"
        );
    }

    public void press() {
        String image = getClass().getResource(path + "/" + name + "Pressed.png").toExternalForm();
        setStyle(
                "-fx-background-image: url('" + image + "');" +
                        "-fx-background-size: " + width + " " + height + ";" +
                        "-fx-background-color: transparent"
        );
    }

    public void release() {
        String image = getClass().getResource(path + "/" + name + ".png").toExternalForm();
        setStyle(
                "-fx-background-image: url('" + image + "');" +
                        "-fx-background-size: " + width + " " + height + ";" +
                        "-fx-background-color: transparent"
        );
    }

    public void changeButtonImage(String path) {
        String image = getClass().getResource(path).toExternalForm();
        setStyle(
                "-fx-background-image: url('" + image + "');" +
                "-fx-background-size: " + width + " " + height + ";" +
                "-fx-background-color: transparent"
        );
    }

}
