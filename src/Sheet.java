import java.util.ArrayList;
import java.util.List;

import processing.core.PImage;

/***
 * A class to represent a set of answers from a page
 */
public class Sheet {
	private PImage image;
	private List<String> answers;
	private int numCorrect;
	private int numIncorrect;
	private double percentCorrect;
	private double percentIncorrect;

	public Sheet(PImage image) {
		this.image = image;
		this.answers = new ArrayList<String>();
	}

	public PImage getImage() {
		return image;
	}

	public ArrayList<String> getAnswers() {
		return (ArrayList<String>) answers;
	}

	public void addAnswer(String answer) {
		answers.add(answer);
	}

	public void compareToKey(Sheet key) {
		ArrayList<String> answerKey = key.getAnswers();
		ArrayList<String> studentAnswers = this.getAnswers();

		int totalQuestions = answerKey.size();
		for (int i = 0; i < answerKey.size(); i++) {
			if (answerKey.get(i).equals(studentAnswers.get(i))) {
				numCorrect++;
			} else {
				numIncorrect++;
			}
		}
		percentCorrect = (((double) numCorrect) / totalQuestions);
		percentIncorrect = (((double) numIncorrect) / totalQuestions);
	}

	public int getNumCorrect() {
		return numCorrect;
	}

	public int getNumIncorrect() {
		return numIncorrect;
	}

	public double getPercentCorrect() {
		return percentCorrect;
	}

	public double getPercentIncorrect() {
		return percentIncorrect;
	}

}
