package oop2018;

public class Word {
	private String word_target;
	private String word_explanain;

	public Word(String word_target, String word_explanain) {
		this.word_target = word_target;
		this.word_explanain = word_explanain;
	}

	public String getWordTarget() {
		return word_target;
	}

	public void setWordTarget(String word_target) {
		this.word_target = word_target;
	}

	public String getWordExplanain() {
		return word_explanain;
	}

	public void setWordExplanain(String word_explanain) {
		this.word_explanain = word_explanain;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (word_explanain == null) {
			if (other.word_explanain != null)
				return false;
		} else if (!word_explanain.equals(other.word_explanain))
			return false;
		if (word_target == null) {
			if (other.word_target != null)
				return false;
		} else if (!word_target.equals(other.word_target))
			return false;
		return true;
	}

}
