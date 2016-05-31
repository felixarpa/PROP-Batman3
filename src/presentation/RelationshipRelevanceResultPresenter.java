package presentation;

import util.ProjectConstants;
import view.auxiliarViews.Config;
import view.MyApp;
import view.RelationshipRelevanceResultView;

import java.util.ArrayList;

public class RelationshipRelevanceResultPresenter extends ListPresenter  {

    protected ArrayList<String> nodeSrc;
    protected ArrayList<String> nodeDst;
    protected ArrayList<Double> relevance;
    private int lastSelected;
    private int type1;
    private int type2;

    protected RelationshipRelevanceResultPresenter() {

    }

    public RelationshipRelevanceResultPresenter(ArrayList<String> result, int type1, int type2) {
        transform(result);
        this.type1 = type1;
        this.type2 = type2;
        actualView = new RelationshipRelevanceResultView(this);
        show();
        MyApp.startScene(actualView.getContent());
    }

    public void onClick(int index) {
        lastSelected = index + this.index;
        ((RelationshipRelevanceResultView)actualView).askSimilarOp();
    }

    protected void transform(ArrayList<String> dataFormat) {
        int i = 0;
        nodeSrc = new ArrayList<>(dataFormat.size());
        nodeDst = new ArrayList<>(dataFormat.size());
        relevance = new ArrayList<>(dataFormat.size());
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
        MyApp.changePresenter(new SimilarRelationRelevancePresenter(nextResult, nodeSrc.get(lastSelected), nodeDst.get(lastSelected), relevance.get(lastSelected)));
    }

    public void reorder(int typeOfOrder, boolean ascending) {
        transform(domainController.reorderSearch3(typeOfOrder, ascending));
        index = 0;
        show();
    }

    @Override
    public void showMore() {
        if (index + Config.LISTS_SIZE <= nodeSrc.size()) {
            index += Config.LISTS_SIZE;
            show();
        }
    }

    @Override
    protected void show() {
        int max = index+Config.LISTS_SIZE;
        if (max > nodeSrc.size()) max = nodeSrc.size();
        max -= index;
        for (int i = 0; i < max; ++i) {
            ((RelationshipRelevanceResultView) actualView).setContent(index+i, nodeSrc.get(index+i), nodeDst.get(index+i), relevance.get(index+i));
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
