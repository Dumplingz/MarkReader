import processing.core.PImage;

/***
 * A class to represent a set of answers from a page
 */
public class AnswerSheet {
	private PImage image;
	private String[] answers;

	public AnswerSheet(PImage image, String[] answers) {
		this.image = image;
		this.answers = answers;
	}

	public PImage getImage() {
		return image;
	}

	public String[] getAnswers() {
		return answers;
	}

}
