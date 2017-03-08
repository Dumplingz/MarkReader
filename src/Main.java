import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Main {
	public static final String PDF_PATH = "/omrtest.pdf";
	public static OpticalMarkReader markReader = new OpticalMarkReader();

	public static void main(String[] args) {
		System.out.println("Welcome!  I will now auto-score your pdf!");
		System.out.println("Loading file..." + PDF_PATH);
		ArrayList<PImage> images = PDFHelper.getPImagesFromPdf(PDF_PATH);

		System.out.println("Scoring all pages...");
		scoreAllPages(images);

		System.out.println("Complete!");

		// Optional: add a saveResults() method to save answers to a csv file
	}

	/***
	 * Score all pages in list, using index 0 as the key.
	 * 
	 * NOTE: YOU MAY CHANGE THE RETURN TYPE SO YOU RETURN SOMETHING IF YOU'D
	 * LIKE
	 * 
	 * @param images
	 *            List of images corresponding to each page of original pdf
	 */
	private static void scoreAllPages(ArrayList<PImage> images) {
		ArrayList<Sheet> scoredSheets = new ArrayList<Sheet>();

		String[] keyInfoColNames = { "#correct", "#wrong", "%correct", "%wrong", "totalQuestions" };
		String[] itemAnalysisColNames = { "Problem#", "# of Students who got problem wrong",
				"% of Students who got problem wrong" };

		CSVData keyInfo = new CSVData("E:\\workspace\\MarkReader\\out\\answerKeyInfo", keyInfoColNames);
		CSVData itemAnalysis = new CSVData("E:\\workspace\\MarkReader\\out\\itemInfo", itemAnalysisColNames);

		PApplet helper = new PApplet();

		// Score the first page as the key
		Sheet key = markReader.processPageImage(images.get(0), helper);

		double[][] keyData = new double[images.size()][keyInfoColNames.length];
		double[][] itemData = new double[key.getAnswers().size()][itemAnalysisColNames.length];

		for (int i = 0; i < images.size(); i++) {
			PImage image = images.get(i);

			Sheet answer = markReader.processPageImage(image, helper);
			answer.compareToKey(key);
			keyData[i][0] = answer.getNumCorrect();
			keyData[i][1] = answer.getNumIncorrect();
			keyData[i][2] = answer.getPercentCorrect();
			keyData[i][3] = answer.getNumIncorrect();
			keyData[i][4] = answer.getAnswers().size();
			scoredSheets.add(answer);

			// do something with answers
		}

		keyInfo.setData(keyData);

		for (int i = 0; i < key.getAnswers().size(); i++) {
			itemData[i][0] = i + 1;
			int numIncorrect = 0;
			for (int j = 0; j < scoredSheets.size(); j++) {
				Sheet answer = scoredSheets.get(j);
				if (!answer.getAnswers().get(i).equals(key.getAnswers().get(i))) {
					numIncorrect++;
				}
			}
			itemData[i][1] = numIncorrect;
			itemData[i][2] = ((double) numIncorrect) / scoredSheets.size();
		}

		itemAnalysis.setData(itemData);

		keyInfo.saveToFile("E:\\workspace\\MarkReader\\out\\answerKeyInfo");
		itemAnalysis.saveToFile("E:\\workspace\\MarkReader\\out\\itemInfo");

	}

	public static String[] convertArrayListToArray(ArrayList<String> arr) {
		String[] newArr = new String[arr.size()];
		for (int i = 0; i < newArr.length; i++) {
			newArr[i] = arr.get(i);
		}
		return newArr;
	}
}