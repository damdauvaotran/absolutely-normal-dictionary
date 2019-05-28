package application;

import radixTree.RadixTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Collections;

public class Dictionary {

    private RadixTree<Word> dictionary;

    public Dictionary(RadixTree<Word> dictionary) {
        this.dictionary = dictionary;
    }

    public Dictionary() {
        this.dictionary = new RadixTree<Word>();
    }

    public RadixTree<Word> getDictionary() {
        return dictionary;
    }

    public void setDictionary(RadixTree<Word> dictionary) {
        this.dictionary = dictionary;
    }

    public ArrayList<Word> lockUp(String wordTarget) {
        return (ArrayList<Word>) this.dictionary.getValuesWithPrefix(wordTarget);
    }

    public void add(Word word) {
        dictionary.put(word.getWordTarget(), word);
    }


    public void addAll(ArrayList<Word> wordsList) {
        for (Word w : wordsList) {
            this.add(w);
        }
    }

    public void deleteWord(String key, Word word){
        this.dictionary.remove(key, word);
    }
    public ArrayList<Word> iterate () {
        return this.lockUp("");
    }



    public static void main(String[] args) {
        Dictionary dict = new Dictionary();
        Word word1 = new Word(1, "a", "1");
        Word word2 = new Word(1, "af", "2");
        Word word3 = new Word(1, "df", "3");
        Word word4 = new Word(1, "aa", "4");
        Word word5 = new Word(1, "artr", "5");
        dict.add(word1);
        dict.add(word2);
        dict.add(word3);
        dict.add(word4);
        dict.add(word5);
        ArrayList<Word> a = dict.iterate();
        for (Word w : a) {
            System.out.println(w.toString());
        }
    }

}
