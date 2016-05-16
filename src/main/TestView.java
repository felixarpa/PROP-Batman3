package main;

import presentation.Presenter;
import view.BaseView;
import view.MyApp;

import javax.swing.text.View;

public class TestView extends Presenter{

    private BaseView view;

    public TestView() {
        view = new BaseView(this);
        MyApp.startScene(view.getContent());
    }

}
