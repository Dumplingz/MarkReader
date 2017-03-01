import processing.core.PImage;

/***
 * Class to perform image processing for optical mark reading
 * 
 */
public class OpticalMarkReader {
	// find where the numbers are using polychrome filter strategy
	/***
	 * Method to do optical mark reading on page image. Return an AnswerSheet
	 * object representing the page answers.
	 * 
	 * @param image
	 * @return
	 */
	public AnswerSheet processPageImage(PImage image) {
		processImage(image);
		for (int row = 0; row < image.height; row++) {
			for (int col = 0; col < image.width; col++) {

			}
		}
		return null;
	}

	public void processImage(PImage image) {
		for (int row = 0; row < image.height; row++) {
			for (int col = 0; col < image.width; col++) {
				int color = getPixelAt(row, col, image);
				if (color > 255) {
					System.out.println("row: " + row + " col: " + col + " color: " + color);
				}
				if (color < 123) {
					image.set(row, col, 0);
				} else {
					image.set(row, col, 255);
				}
			}
		}

	}

	public int getPixelAt(int row, int col, PImage image) {
		image.loadPixels();

		int index = row * image.width + col;

		return image.pixels[index];
	}
}
