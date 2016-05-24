package presentation;

public class SearchString {

    private int lastIndex;
    private String string;

    public SearchString(String string, int i) {
        lastIndex = i;
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public int getLastIndex() {
        return lastIndex;
    }

}
