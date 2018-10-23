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
	
	public Word(int id, String wordTarget) {
		super();
		this.id = id;
		this.wordTarget = wordTarget;
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
		return wordTarget;
	}

	public void setWordTarget(String wordTarget) {
		this.wordTarget = wordTarget;
	}

	
	
	
}
