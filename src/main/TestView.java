package main;

import presentation.Presenter;
import view.BaseView;
import view.MyApp;

public class TestView extends Presenter{

    private BaseView view;

    public TestView() {
        view = new BaseView(this);
        MyApp.startScene(BaseView.getContent());
    }

}
