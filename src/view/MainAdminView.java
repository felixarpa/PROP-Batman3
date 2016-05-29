package view;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import presentation.MainAdminPresenter;
import view.auxiliarViews.ImageButton;

public abstract class MainAdminView extends BaseView {

        VBox contentVBox;
        private ImageButton addNodeButton;
        private ImageButton removeNodeButton;
        private ImageButton addEdgeButton;
        private ImageButton removeEdgeButton;
        private ImageButton lastPressed;

        public MainAdminView() {
            adminMode.press();
            initializePanes();
            initializeViews();
            buildPanes();
            setListeners();
        }

        private void initializePanes() {}
        private void initializeViews(){
            addNodeButton = new  ImageButton("addNodeButton", 220, 75);
            removeNodeButton = new ImageButton("removeNodeButton", 225, 75);
            addEdgeButton = new ImageButton("addEdgeButton", 225, 75);
            removeEdgeButton = new ImageButton("removeEdgeButton", 225, 75);
            lastPressed = new ImageButton("addNodeButton",225,75);
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

