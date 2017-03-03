import java.util.ArrayList;
import java.util.List;

import processing.core.PImage;

/***
 * A class to represent a set of answers from a page
 */
public class AnswerSheet {
	private PImage image;
	private List<String> answers;

	public AnswerSheet(PImage image) {
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

}
