package application;

import radixTree.RadixTree;
import java.util.ArrayList;
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

	public void lockUp(String wordTarget ) {
		
	}

	public static void main(String[] args) {
		Word word1 = new Word(1, "asdfhajsdf");
		Word word2 = new Word(2, "asadfddfffdd");
		Word word3 = new Word(3, "adsgffdd");
		Word word4 = new Word(4, "kahdhjdg");
		Word word5 = new Word(5, "tjjdj");
		Word word6 = new Word(6, "adtuueue");

	
	}

}
