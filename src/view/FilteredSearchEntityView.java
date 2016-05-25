package view;


import presentation.FilteredSearchEntityPresenter;

public class FilteredSearchEntityView extends FilteredSearchView {
    public FilteredSearchEntityView(FilteredSearchEntityPresenter filteredSearchEntityPresenter) {
        super(filteredSearchEntityPresenter);
    }
    @Override
    public void setContent(int index, String node, int type, int listSize) {
        String[] elements = node.split("\t");
        if (!elements[0].equals("")) {
            number.get(type).get(index%listSize).setText(Integer.toString(index+1));
            number.get(type).get(index%listSize).setMinWidth(50);
            number.get(type).get(index%listSize).setMaxWidth(50);
            //number.get(type).get(index%listSize).setPadding(new Insets(0,0,0,0));
            id.get(type).get(index%listSize).setText("Id: " + elements[1]);
            id.get(type).get(index%listSize).setMinWidth(100);
            id.get(type).get(index%listSize).setMaxWidth(100);
            //id.get(type).get(index%listSize).setPadding(new Insets(0,50,0,0));
            relevance.get(type).get(index%listSize).setText("Relevance: " + elements[2]);
            relevance.get(type).get(index%listSize).setMinWidth(300);
            relevance.get(type).get(index%listSize).setMaxWidth(300);
            //relevance.get(type).get(index%listSize).setPadding(new Insets(0,50,0,0));

            label.get(type).get(index%listSize).setText("Label: " + elements[3]);
            label.get(type).get(index%listSize).setMinWidth(75);
            label.get(type).get(index%listSize).setMaxWidth(75);
            //label.get(type).get(index%listSize).setPadding(new Insets(0,50,0,0));
        }
        else {
            number.get(type).get(index%listSize).setText("");
            number.get(type).get(index%listSize).setMinWidth(10);
            number.get(type).get(index%listSize).setMaxWidth(10);
            //number.get(type).get(index%listSize).setPadding(new Insets(0,50,0,0));
            id.get(type).get(index%listSize).setText("");
            id.get(type).get(index%listSize).setMinWidth(100);
            id.get(type).get(index%listSize).setMaxWidth(100);
            //id.get(type).get(index%listSize).setPadding(new Insets(0,50,0,0));
            relevance.get(type).get(index%listSize).setText("");
            relevance.get(type).get(index%listSize).setMinWidth(300);
            relevance.get(type).get(index%listSize).setMaxWidth(300);
            //relevance.get(type).get(index%listSize).setPadding(new Insets(0,50,0,0));
            label.get(type).get(index%listSize).setText("");
            label.get(type).get(index%listSize).setMinWidth(75);
            label.get(type).get(index%listSize).setMaxWidth(75);
            //label.get(type).get(index%listSize).setPadding(new Insets(0,50,0,0));
        }
        name.get(type).get(index%listSize).setText(elements[0]);
        name.get(type).get(index%listSize).setMinWidth(400);
        name.get(type).get(index%listSize).setMaxWidth(400);
        //name.get(type).get(index%listSize).setPadding(new Insets(0,50,0,0));

    }

}
