package presentation;

import util.ProjectConstants;
import view.CategoryResultView;
import view.Config;
import view.MyApp;
import view.RelationshipRelevanceResultView;

import java.util.ArrayList;
import java.util.StringJoiner;

public class RelationshipRelevanceResultPresenter extends BasePresenter  {
    private ArrayList<String> nodeSrc;
    private ArrayList<String> nodeDst;
    private ArrayList<Double> relevance;
    private int index;
    private int lastSelected;
    private int type1;
    private int type2;

    public RelationshipRelevanceResultPresenter(int type1, int type2) {
        transform(domainController.thirdSearch(type1, type2));
        index = 0;
        this.type1 = type1;
        this.type2 = type2;
        actualView = new RelationshipRelevanceResultView(this);
        show();
        MyApp.startScene(actualView.getContent());
    }

    public void onClick(int index) {
        lastSelected = index;
        ((RelationshipRelevanceResultView)actualView).askSimilarOp();
    }

    private void transform(ArrayList<String> dataFormat) {
        int i = 0;
        nodeSrc = new ArrayList<>();
        nodeDst = new ArrayList<>();
        relevance = new ArrayList<>();
        while (i < dataFormat.size()) {
            int size = (Integer.parseInt(dataFormat.get(i)) << 1);
            double rel = Double.parseDouble(dataFormat.get(i+1));
            i += 2;
            size += i;
            for (; i < size; i += 2) {
                nodeSrc.add(dataFormat.get(i));
                nodeDst.add(dataFormat.get(i+1));
                relevance.add(rel);
            }
        }
    }

    //op < 0 -> menos relevante
    //op == 0 -> igual de relevante
    //op > 0 -> más relevante
    public void onAcceptClick(int op) {
        ArrayList<String> nextResult = domainController.searchSimilarRelationRelevance(nodeSrc.get(lastSelected), nodeDst.get(lastSelected), op);
        actualView.destroy();
        actualView = null;
        //MyApp.changePresenter(new SimilarRelationRelevancePresenter(nextResult));
    }

    public void reorder(int typeOfOrder, boolean ascending) {
        transform(domainController.reorderSearch3(typeOfOrder, ascending));
        index = 0;
        show();
    }

    public void showMore() {
        if (index + Config.LISTS_SIZE <= nodeSrc.size()) {
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

    private void show() {
        int max = index+Config.LISTS_SIZE;
        if (max > nodeSrc.size()) max = nodeSrc.size();
        max -= index;
        for (int i = 0; i < max; ++i) {
            ((RelationshipRelevanceResultView) actualView).setContent(index+i, nodeSrc.get(index+i), nodeDst.get(index+1), relevance.get(index+1));
        }
        max += index;
        for (;max < index+Config.LISTS_SIZE; ++max) {
            ((RelationshipRelevanceResultView) actualView).setContent(max, null, null, 0);
        }
    }

    public String getType(int i) {
        if (i == 1) return transformType(type1);
        else return transformType(type2);
    }

    private String transformType(int i) {
        switch(i) {
            case ProjectConstants.AUTHOR_TYPE:
                return "AUTHOR";
            case ProjectConstants.CONFERENCE_TYPE:
                return "CONFERENCE";
            case ProjectConstants.PAPER_TYPE:
                return "PAPER";
            case ProjectConstants.TERM_TYPE:
                return "TERM";
        }
        return "Te has equivocado";
    }
}
