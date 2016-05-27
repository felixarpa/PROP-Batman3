package view;

import presentation.RemoveNodePresenter;
import presentation.RemoveNodeSelectorPresenter;

import java.util.Collection;

public class RemoveNodeSelectorView extends MainAdminView {

    protected Collection<String> resultToPredict;

    public RemoveNodeSelectorView(RemoveNodeSelectorPresenter removeNodeSelectorPresenter, Collection<String> resultToPredict) {
        presenter = removeNodeSelectorPresenter;
        this.resultToPredict = resultToPredict;
        initializePanes();
        initializeViews();
        buildPanes();
        setListeners();
        topBarPane.setCenter(contentVBox);
    }

    private void initializePanes() {

    }

    private void initializeViews() {

    }

    private void buildPanes() {

    }

    private void setListeners() {

    }
}
