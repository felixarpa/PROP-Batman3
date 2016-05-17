package view;

import com.sun.javafx.iio.gif.GIFDescriptor;
import javafx.animation.Animation;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import presentation.LoginPresenter;

import javax.swing.*;


public class LoginView {

    private BorderPane basePane;
    private VBox titleVBox;
    private VBox contentVBox;
    private VBox textFieldsVBox;
    private VBox buttonsVBox;

    private ImageView applicationLogo;

    private EditText usernameEditText;

    private HBox passwordHBox;
    private Label passwordLabel;
    private PasswordField passwordTextField;

    private Button loginButton;
    private Text registerText;
    private Button registerButton;

    private LoginPresenter loginPresenter;

    private Animation loadingIcon;

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
        basePane.setPadding(new Insets(50, 0, 100, 0));

        titleVBox = new VBox();

        contentVBox = new VBox();
        contentVBox.setPadding(new Insets(75, 0, 50, 0));

        textFieldsVBox = new VBox();
        textFieldsVBox.setSpacing(4);

        usernameEditText = new EditText("Username");

        passwordHBox = new HBox();
        passwordHBox.setSpacing(4);

        buttonsVBox = new VBox();
        buttonsVBox.setPadding(new Insets(20, 0, 50, 0));
        buttonsVBox.setSpacing(4);
    }

    private void initializeViews() {

        applicationLogo = new ImageView();
        Image image2 = new Image(this.getClass().getResourceAsStream("../images/login/titleLogo.png"));

        applicationLogo.setImage(image2);
        applicationLogo.setFitWidth(525);
        applicationLogo.setFitHeight(147);

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
        titleVBox.getChildren().add(applicationLogo);
        titleVBox.setAlignment(Pos.CENTER);

        passwordHBox.getChildren().add(passwordLabel);
        passwordHBox.getChildren().add(passwordTextField);
        passwordHBox.setAlignment(Pos.CENTER);

        textFieldsVBox.getChildren().add(usernameEditText.getBase());
        textFieldsVBox.getChildren().add(passwordHBox);
        textFieldsVBox.setAlignment(Pos.CENTER);

        buttonsVBox.getChildren().add(loginButton);
        buttonsVBox.getChildren().add(registerText);
        buttonsVBox.getChildren().add(registerButton);
        buttonsVBox.setAlignment(Pos.CENTER);

        contentVBox.getChildren().add(textFieldsVBox);
        contentVBox.getChildren().add(buttonsVBox);
        contentVBox.setAlignment(Pos.CENTER);

        String image = this.getClass().getResource("../images/background.jpg").toExternalForm();
        basePane.setStyle(
                "-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;" +
                "-fx-background-size: 1024 576"
        );
        basePane.setTop(titleVBox);
        basePane.setCenter(contentVBox);
    }

    public void displayErrorMessage(){
        System.out.println("Login Failure");
    }

    public void displaySuccessMessage(){
        System.out.println("Login Success");
    }

    private void setListeners(){

        loginButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MyApp.changeButtonImage(loginButton, "login/loginButtonPressed", "227", "50", getClass());
            }
        });

        loginButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MyApp.changeButtonImage(loginButton, "login/loginButton", "227", "50", getClass());
                loginPresenter.login();
            }
        });

        registerButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MyApp.changeButtonImage(registerButton, "login/registerButtonPressed", "148", "50", getClass());
            }
        });

        registerButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MyApp.changeButtonImage(registerButton, "login/registerButton", "148", "50", getClass());
                loginPresenter.register();
            }
        });
    }

    public String getUsername(){
        return usernameEditText.getText();
    }

    public String getPassword(){
        return passwordTextField.getText();
    }

    public Pane getContent() {
        return basePane;
    }

}

