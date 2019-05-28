package application;

import java.io.Serializable;

public class Word implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id = 0;
    private String wordTarget = "";

    private String wordMeaning = "";

    public Word(int id, String wordTarget, String wordMeaning) {
        super();
        this.id = id;
        this.wordTarget = wordTarget;
        this.wordMeaning = wordMeaning;
    }

    public Word() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWordTarget() {
        return this.wordTarget;
    }

    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    public String getWordMeaning() {
        return wordMeaning;
    }

    public void setWordMeaning(String wordMeaning) {
        this.wordMeaning = wordMeaning;
    }

    @Override
    public String toString() {
        return this.wordTarget;
    }
}
