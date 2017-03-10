
import processing.core.PApplet;
import processing.core.PImage;

/***
 * Class to perform image processing for optical mark reading
 * 
 */
public class OpticalMarkReader {
	public static final int STARTING_ROW = 460;
	public static final int STARTING_COL = 125;
	public static final int ENDING_ROW_FOR_BUBBLES = 37;
	public static final int LAST_QUESTION_ROW = 1380;
	public static final int BEGINNING_COL_NEXT_QUESTION = 282;
	public static final int ENDING_COL_FOR_BUBBLES = 190;
	public static final int PIXEL_SUBSTRACTION_TO_CORRECT_ROW = 3;
	public static final int LAST_QUESTION_IN_COL = 25;
	public static final String answers = "abcde";

	/***
	 * Method to do optical mark reading on page image. Return an AnswerSheet
	 * object representing the page answers.
	 * 
	 * @param image
	 * @return
	 */
	public Sheet processPageImage(PImage image, PApplet window) {
		processImage(image, window);
		Sheet AnswerSheet = new Sheet(image);
		processAnswerSheet(image, 5, AnswerSheet);
		return AnswerSheet;
	}

	public void processAnswerSheet(PImage image, int numBubbles, Sheet AnswerSheet) {
		for (int col = STARTING_COL; col < image.width; col += BEGINNING_COL_NEXT_QUESTION) {
			int questionNumber = 1;
			for (int row = STARTING_ROW; row < LAST_QUESTION_ROW; row += ENDING_ROW_FOR_BUBBLES) {
				if (questionNumber % LAST_QUESTION_IN_COL == 0) row -=  PIXEL_SUBSTRACTION_TO_CORRECT_ROW;
				questionNumber++;
				int number = determineBubble(row, col, row + ENDING_ROW_FOR_BUBBLES, col + ENDING_COL_FOR_BUBBLES, numBubbles, image);
				String answer = answers.substring(number - 1, number);
				AnswerSheet.getAnswers().add(answer);
			}
		}
	}

	public static void processImage(PImage image, PApplet window) {
		image.filter(PImage.GRAY);
		image.loadPixels();
		int[] pixels = translatePixelsArrayFromPImageToWorking(image.pixels);
		updatePImageWithPixels(image, pixels, window);
	}

	/***
	 * Take in image.pixels where color format is default PImage format
	 * 
	 * @param pixels
	 * @return pixel array where color format is in 0 to 255
	 */
	private static int[] translatePixelsArrayFromPImageToWorking(int[] pixels) {
		int[] out = new int[pixels.length];
		for (int i = 0; i < pixels.length; i++) {
			out[i] = pixels[i] & 255;
		}
		return out;
	}

	private static void updatePImageWithPixels(PImage img, int[] newpixels, PApplet window) {
		for (int i = 0; i < newpixels.length; i++) {
			int c = window.color(newpixels[i], newpixels[i], newpixels[i]);
			img.pixels[i] = c;
		}
		img.updatePixels();
	}

	public static int getPixelAt(int row, int col, PImage image) {
		image.loadPixels();
		int index = row * image.width + col;
		return image.pixels[index] & 255;
	}

	public int determineBubble(int startRow, int startCol, int endRow, int endCol, int numBubbles, PImage pixels) {
		int difference = endCol - startCol, stepWidth = difference / numBubbles, min = Integer.MAX_VALUE, minGroup = 1;
		
		for (int groups = 1; groups <= numBubbles; groups++) {
			int values = getSum(startRow, startCol + ((groups - 1) * stepWidth), endRow, startCol + (groups * stepWidth), pixels);
			if (values < min) {
				minGroup = groups;
				min = values;
			}
		}
		return minGroup;
	}

	private int getSum(int startRow, int startCol, int endRow, int endCol, PImage pixels) {
		int values = 0;
		for (int i = startRow; i < endRow; i++) {
			for (int j = startCol; j < endCol; j++) {
				values += getPixelAt(i, j, pixels);
			}
		}
		return values;
	}

}
