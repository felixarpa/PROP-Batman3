package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import presentation.LoginPresenter;


public class LoginView {

    private BorderPane basePane;
    private HBox titleHBox;
    private VBox contentVBox;
    private GridPane textFieldsGridPane;
    private VBox buttonsVBox;

    private ImageView applicationLogo;

    private Label usernameLabel;
    private static TextField usernameTextField;

    private Label passwordLabel;
    private static PasswordField passwordTextField;

    private HBox loginButtonHBox;
    private Button loginButton;
    private HBox registerTextHBox;
    private Text registerText;
    private HBox registerButtonHBox;
    private Button registerButton;

    private LoginPresenter loginPresenter;

    public LoginView(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
        initializePanes();
        initializeViews();
        buildPanes();
        setListeners();
    }

    public void destroy() {
        loginPresenter = null;
    }

    private void initializePanes(){
        basePane = new BorderPane();
        basePane.setPadding(new Insets(50, 300, 100, 250));

        titleHBox = new HBox();
        titleHBox.setPadding(new Insets(0, 150, 0, 0));

        contentVBox = new VBox();
        contentVBox.setPadding(new Insets(75, 100, 50, 150));

        textFieldsGridPane = new GridPane();
        textFieldsGridPane.setHgap(4);
        textFieldsGridPane.setVgap(4);

        buttonsVBox = new VBox();
        buttonsVBox.setPadding(new Insets(20, 0, 50, 0));
        buttonsVBox.setSpacing(4);

        loginButtonHBox = new HBox();
        loginButtonHBox.setPadding(new Insets(5, 0, 5, 10));
        registerTextHBox = new HBox();
        registerTextHBox.setPadding(new Insets(5, 190, 5, 0));
        registerButtonHBox = new HBox();
        registerButtonHBox.setPadding(new Insets(5, 0, 5, 45));
    }

    private void initializeViews() {
        applicationLogo = new ImageView();
        Image image2 = new Image(this.getClass().getResourceAsStream("../images/login/titleLogo.png"));
        applicationLogo.setImage(image2);
        applicationLogo.setFitWidth(525);
        applicationLogo.setFitHeight(147);

        usernameLabel = new Label("Username");
        usernameLabel.setTextFill(Config.LABEL_TEXT_COLOR);
        usernameTextField = new TextField();

        passwordLabel = new Label("Password");
        passwordLabel.setTextFill(Config.LABEL_TEXT_COLOR);
        passwordTextField = new PasswordField();

        loginButton = new Button();
        loginButton.setMaxSize(227,50);
        loginButton.setMinSize(227,50);
        String loginButtonImage = this.getClass().getResource("../images/login/loginButton.png").toExternalForm();
        loginButton.setStyle(
                "-fx-background-image: url('"+ loginButtonImage + "');" +
                "-fx-background-size: 227 50;" +
                "-fx-background-color: transparent");

        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0,0,0));
        registerText = new Text("Don't have an account yet?");
        registerText.setEffect(ds);
        registerText.setStyle(
                "-fx-fill: #d0b494;" +
                "-fx-font: 20px Caspian");


        registerButton = new Button();
        registerButton.setMaxSize(148,50);
        registerButton.setMinSize(148,50);
        String registerButtonImage = this.getClass().getResource("../images/login/registerButton.png").toExternalForm();
        registerButton.setStyle(
                "-fx-background-image: url('"+ registerButtonImage + "');" +
                "-fx-background-size: 148 50;" +
                "-fx-background-color: transparent");
        HBox.setHgrow(loginButton, Priority.ALWAYS);
        HBox.setHgrow(registerButton, Priority.ALWAYS);

    }

    private void buildPanes() {
        titleHBox.getChildren().add(applicationLogo);

        textFieldsGridPane.add(usernameLabel, 0, 0);
        textFieldsGridPane.add(usernameTextField, 1, 0);

        textFieldsGridPane.add(passwordLabel, 0, 1);
        textFieldsGridPane.add(passwordTextField, 1, 1);

        loginButtonHBox.getChildren().add(loginButton);
        registerTextHBox.getChildren().add(registerText);
        registerButtonHBox.getChildren().add(registerButton);

        registerTextHBox.setAlignment(Pos.CENTER);

        buttonsVBox.getChildren().add(loginButtonHBox);
        buttonsVBox.getChildren().add(registerTextHBox);
        buttonsVBox.getChildren().add(registerButtonHBox);

        contentVBox.getChildren().add(textFieldsGridPane);
        contentVBox.getChildren().add(buttonsVBox);

        String image = this.getClass().getResource("../images/background.jpg").toExternalForm();
        basePane.setStyle(
                "-fx-background-image: url('" + image + "'); " +
         "-fx-background-position: center center; " +
         "-fx-background-repeat: stretch;" +
         "-fx-background-size: 1024 576");
        basePane.setTop(titleHBox);
        basePane.setCenter(contentVBox);
    }

    public void displayErrorMessage(){
        System.out.println("Login Failure");
    }

    public void displaySuccessMessage(){
        System.out.println("Login Success");
    }

    private void setListeners(){
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loginPresenter.login();
            }
        });

        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loginPresenter.register();
            }
        });
    }

    public String getUsername(){
        return usernameTextField.getText();
    }

    public String getPassword(){
        return passwordTextField.getText();
    }

    public Pane getContent() {
        return basePane;
    }

}

