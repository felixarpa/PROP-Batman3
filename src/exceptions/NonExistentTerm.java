package exceptions;

public class NonExistentTerm extends Exception {

    private String term;

    public NonExistentTerm(String term) {
        this.term = term;
    }

    public String getTerm(){return term;}

    @Override
    public String getMessage() {
        return "Term "+term+" not found in data base";
    }
}

//HOLAAAAA