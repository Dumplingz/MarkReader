import java.util.ArrayList;
import java.util.List;

import processing.core.PImage;

/***
 * A class to represent a set of answers from a page
 */
public class Sheet {
	private PImage image;
	private List<String> answers;

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

	public static void compareTwoSheets(Sheet answerSheet, Sheet studentSheet) {
		double percentCorrect = 0, percentWrong = 0;
		int correct = 0;
		int wrong = 0;
		String[] answerKey = convertArrayListToArray(answerSheet.getAnswers());
		String[] student = convertArrayListToArray(studentSheet.getAnswers());
		int totalQuestions = answerKey.length;
		for (int i = 0; i < answerKey.length; i++) {
			if (answerKey[i].equals(student[i]))
				correct++;
			else
				wrong++;
		}
		percentCorrect = (double) (correct / totalQuestions);
		percentWrong = (double) (wrong / totalQuestions);
		System.out.println("This sheet answered " + correct + " questions correctly and " + wrong
				+ " answers wrong out of " + totalQuestions + ". The sheet answered " + percentCorrect
				+ " of the questions correctly and " + percentWrong + "of the questions incorrectly.");
	}

	public static String[] convertArrayListToArray(ArrayList<String> arr) {
		String[] newArr = new String[arr.size()];
		for (int i = 0; i < newArr.length; i++) {
			newArr[i] = arr.get(i);
		}
		return newArr;
	}

}
