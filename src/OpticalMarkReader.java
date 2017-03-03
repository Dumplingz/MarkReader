import processing.core.PImage;

/***
 * Class to perform image processing for optical mark reading
 * 
 */
public class OpticalMarkReader {
	/***
	 * Method to do optical mark reading on page image. Return an AnswerSheet
	 * object representing the page answers.
	 * 
	 * @param image
	 * @return
	 */
	public AnswerSheet processPageImage(PImage image) {
		processImage(image);
		AnswerSheet sheet = new AnswerSheet(image);
		for (int row = 110; row < image.height; row++) {
			for (int col = 0; col < image.width; col++) {
				
			}
		}
		return null;
	}

	public static void processImage(PImage image) {
		image.filter(PImage.GRAY);
		for (int row = 0; row < image.height; row++) {
			for (int col = 0; col < image.width; col++) {
				int color = getPixelAt(row, col, image);

				  // System.out.println("row: " + row + " col: " + col + " color: " + color);

			
					image.set(row, col, 255);
			
					//image.set(row, col, 255);
				
			}
		}
		
		image.updatePixels();

	}

	public static int getPixelAt(int row, int col, PImage image) {
		image.loadPixels();

		int index = row * image.width + col;
	
		return image.pixels[index] & 255;
	}

	public int determineBubble(int r1, int c1, int r2, int c2, int numBubbles, PImage pixels) {
		int difference = c2 - c1;
		int stepWidth = difference / numBubbles;
		int extraRows = difference % numBubbles;
		if (extraRows != 0) {
			System.out.println("Cutting off " + extraRows + " rows because not a multiple of " + numBubbles + ".");
		}
		int min = Integer.MAX_VALUE;
		int minGroup = 1;
		for (int groups = 1; groups <= numBubbles; groups++) {
			int values = getSum(r1, c1 + ((groups - 1) * stepWidth), r2, c1 + (groups * stepWidth), pixels);
			if (values < min) {
				minGroup = groups;
				min = values;
			}
		}
		return minGroup;
	}

	private int getSum(int r1, int c1, int r2, int c2, PImage pixels) {
		int values = 0;
		for (int i = r1; i < r2; i++) {
			for (int j = c1; j < c2; j++) {
				values += getPixelAt(i, j, pixels);
			}
		}
		return values;
	}

}
