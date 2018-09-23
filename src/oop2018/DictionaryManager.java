package oop2018;

import java.io.IOException;
import java.util.Scanner;

public class DictionaryManager {
	private Dictionary dictionary;

	public DictionaryManager(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	public Dictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	public void insertFromCommandline() {
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		int wordsCount = keyboard.nextInt();
		for (int i = 0; i < wordsCount; i++) {
			Word tempWord = new Word(keyboard.nextLine(), keyboard.nextLine());
			this.getDictionary().addNewWorld(tempWord);
		}
	}

	public void showAllWord() {
		for (int i = 0; i < this.dictionary.getLength(); i++) {
			System.out.println(i + "\t" + this.dictionary.getDictionary().get(i).getWordTarget() + "\t"+ this.dictionary.getDictionary().get(i).getWordExplanain());

		}
	}

}
