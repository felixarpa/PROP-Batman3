package presentation;

import util.ProjectConstants;
import view.Config;
import view.MyApp;
import view.RelevanceTypeSelectorView;

import java.util.ArrayList;
import java.util.Stack;

public class RelevanceTypeSelectorPresenter extends BasePresenter  {

    private ArrayList<String> result;
    private Stack<ArrayList<SearchString>> resultStack;
    private int lastSearchLenght;
    private int index;

    public RelevanceTypeSelectorPresenter(ArrayList<String> result) {
        resultStack = new Stack<>();
        ArrayList<SearchString> firstResult = new ArrayList<>();
        for (String string : result) {
            
        }
        this.result = result;
        actualView = new RelevanceTypeSelectorView(this);
        index = 0;
        showMore();
        MyApp.startScene(actualView.getContent());
    }

    public void onKeyEntered(String newString) {
        if (lastSearchLenght > newString.length()) {
            ArrayList<SearchString> lastResult = resultStack.peek();

            ArrayList<SearchString> newResult = new ArrayList<>(lastResult.size());

            for (SearchString s : lastResult) {
                int i = s.getLastIndex();
                int newI = s.getString().indexOf(newString, i);
                newResult.add(new SearchString(s.getString(), newI));
            }

            resultStack.push(newResult);
        }
        else if (lastSearchLenght < newString.length()) {
            resultStack.pop();
        }

        lastSearchLenght = newString.length();
    }

    public void onClickEntityRelevance(int id) {
        String node = result.get(id);
        Thread thread = new Thread(()-> {
            ArrayList<ArrayList<String>> selected = domainController.firstSearch(node, ProjectConstants.NODE_RELEVANCE_RESULT);
            actualView.destroy();
            actualView = null;
            MyApp.changePresenter(new FilteredSearchResultPresenter(selected));
        });
        thread.start();
    }

    public void onClickRelationshipRelevance(int id) {
        String node = result.get(id);
        Thread thread = new Thread(()-> {
            ArrayList<ArrayList<String>> selected = domainController.firstSearch(node, ProjectConstants.RELATION_RELEVANCE_RESULT);
            actualView.destroy();
            actualView = null;
            MyApp.changePresenter(new FilteredRelevanceResultPresenter(selected));
        });
        thread.start();
    }

    public void showMore() {
        int max = index + Config.LISTS_SIZE;
        if (max > result.size()) max = result.size();
        for (; index < max; ++index) {
            ((RelevanceTypeSelectorView) actualView).setContent(index, result.get(index));
        }
    }

    public void showLess() {
        int min = index - 10;

        for (; index >= 0; --index) {
            ((RelevanceTypeSelectorView) actualView).setContent(index % 10, result.get(index));
        }
    }

}
