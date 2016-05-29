package presentation;

import exceptions.NonExistentNode;
import exceptions.ProjectError;
import javafx.application.Platform;
import util.ProjectConstants;
import view.auxiliarViews.Config;
import view.MyApp;
import view.RemoveNodeSelectorView;

import java.util.ArrayList;


public class RemoveNodeSelectorPresenter extends MainAdminPresenter{

    protected ArrayList<String> result;
    protected int index;


    public RemoveNodeSelectorPresenter(ArrayList<String> result) {
        this.result = result;
        actualView = new RemoveNodeSelectorView(this);
        index = 0;
        show();
        MyApp.startScene(actualView.getContent());
    }


    public void showMore() {
        if (index + Config.LISTS_SIZE <= result.size()) {
            index += Config.LISTS_SIZE;
            show();
        }

    }

    public void showLess() {
        if (index - Config.LISTS_SIZE >= 0) {
            index = index - Config.LISTS_SIZE;
            show();
        }
    }

    protected void show(){
        int max = index+Config.LISTS_SIZE;
        if (max > result.size()) max = result.size();
        for (int i = 0; i < max-index; ++i) {
            ((RemoveNodeSelectorView) actualView).setContent(index+i, result.get(index+i));
        }
        for (;max < index+Config.LISTS_SIZE; ++max) {
            ((RemoveNodeSelectorView) actualView).setContent(max, "\t \t \t \t");
        }
    }

    public void onClickRemoveNode(int index) {
        String[] elements = result.get(this.index+index).split("\t");
        int id = Integer.parseInt(elements[1]);
        int type;
        switch (elements[4]){
            case "domain.graph.Author":
                type = ProjectConstants.AUTHOR_TYPE;
                break;
            case "domain.graph.Conference":
                type = ProjectConstants.CONFERENCE_TYPE;
                break;
            case "domain.graph.Paper":
                type = ProjectConstants.PAPER_TYPE;
                break;
            case "domain.graph.Term":
                type = ProjectConstants.TERM_TYPE;
                break;
            default:
                throw new ProjectError("Invalid parameter index: " + elements[4]);

        }

        ((RemoveNodeSelectorView)actualView).startProgress(index);
        Thread thread = new Thread(() -> {
            try {
                adminController.deleteNode(id, type);
                result.remove(this.index+index);
            } catch (NonExistentNode nonExistentNode) {
                throw new ProjectError(nonExistentNode.getMessage());
            }
            Platform.runLater(() -> {
                ((RemoveNodeSelectorView)actualView).stopProgress(index);
                show();
            });
        });
        thread.start();

    }

}

