package view;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import presentation.MainAdminPresenter;

public class MainAdminView extends BaseView {

        VBox contentVBox;
        private ImageButton addNodeButton;
        private ImageButton removeNodeButton;
        private ImageButton addEdgeButton;
        private ImageButton removeEdgeButton;
        private ImageButton lastPressed;

        public MainAdminView(MainAdminPresenter mainAdminViewPresenter) {
            presenter = mainAdminViewPresenter;
            initializePanes();
            initializeViews();
            buildPanes();
            setListeners();
            topBarPane.setCenter(contentVBox);
        }

        private void initializePanes() {}
        private void initializeViews(){
            addNodeButton = new  ImageButton("../images", "addNodeButton", 220, 75);
            removeNodeButton = new ImageButton("../images", "removeNodeButton", 225, 75);
            addEdgeButton = new ImageButton("../images", "addEdgeButton", 225, 75);
            removeEdgeButton = new ImageButton("../images", "removeEdgeButton", 225, 75);
            lastPressed = new ImageButton("../images","addNodeButton",225,75);
        }
        private void buildPanes(){
            categoryButtons.getChildren().clear();
            categoryButtons.getChildren().add(addNodeButton);
            categoryButtons.getChildren().add(removeNodeButton);
            categoryButtons.getChildren().add(addEdgeButton);
            categoryButtons.getChildren().add(removeEdgeButton);
        }
        private void setListeners() {
            addNodeButton.setOnMousePressed(
                    event -> {
                        addNodeButton.press();
                        lastPressed.release();
                        lastPressed = addNodeButton;
                    }
            );

            addNodeButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ((MainAdminPresenter)presenter).clickAddNode();
                }
            });

            removeNodeButton.setOnMousePressed(
                    event -> {
                        removeNodeButton.press();
                        lastPressed.release();
                        lastPressed = removeNodeButton;
                    }
            );

            removeNodeButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ((MainAdminPresenter)presenter).clickRemoveNode();
                }
            });

            addEdgeButton.setOnMousePressed(
                    event -> {
                        addEdgeButton.press();
                        lastPressed.release();
                        lastPressed = addEdgeButton;
                    }
            );

            addEdgeButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ((MainAdminPresenter)presenter).clickAddEdge();
                }
            });

            removeEdgeButton.setOnMousePressed(
                    event -> {
                        removeEdgeButton.press();
                        lastPressed.release();
                        lastPressed = removeEdgeButton;
                    }
            );

            removeEdgeButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ((MainAdminPresenter)presenter).clickRemoveEdge();
                }
            });

        }
}

