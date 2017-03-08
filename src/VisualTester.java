
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

public class VisualTester extends PApplet {
	ArrayList<PImage> images;
	PImage current_image;
	int currentImageIndex = 0;
	int w = 1200;
	int h = 900;
	OpticalMarkReader markReader;
	Sheet answerSheet;
	Sheet[] answerSheets;

	public void setup() {
		size(w, h);
		images = PDFHelper.getPImagesFromPdf("/omrtest.pdf");
		markReader = new OpticalMarkReader();
		answerSheets = new Sheet[images.size()];
		answerSheet = new Sheet(current_image);

	}

	public void draw() {

		background(255);
		if (images.size() > 0) {
			current_image = images.get(currentImageIndex);

			OpticalMarkReader.processImage(current_image, this);

			image(current_image, 0, 0); // display image i
			fill(0);
			text(mouseX + ", " + mouseY, 50, 50);
			fill(100);

			for (int c = 0; c < current_image.width - 120; c += 282) {

				// starting and ending index points for looping
				for (int r = 0; r < current_image.height - 460; r += 37) {
					rect(c + 122, r + 460, 1, 1);
					rect(c + 310, r + 497, 1, 1);
				}
				rect(122 + c, (current_image.height - 1), 8, 8);
				rect(310 + c, (current_image.height - 1), 8, 8);
			}
		}
	}

	public void mouseReleased() {
		currentImageIndex = (currentImageIndex + 1) % images.size();
		// increment current image
	}

	public void keyReleased() {
		markReader.processAnswerSheet(current_image, 5, answerSheet);
	}
}
