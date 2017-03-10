import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/***
 * A class to read/write numerical CSV files and allow easy access
 * 
 * @author efraisse694
 *
 */

public class CSVData {
	private double[][] data;
	private String[] columnNames;
	private String filePathToCSV;

	public CSVData(String filepath, String[] columnNames) {
		this.columnNames = columnNames;
		this.filePathToCSV = filepath;
	}
	
	/***
	 * returns the CSVFile in the format we want it to be in
	 */
	public String toString() {
		String out = "";
		for (int i = 0; i < columnNames.length; i++) {
			out += columnNames[i];
			if (i != columnNames.length - 1) {
				out += ", ";
			}
		}
		out += System.getProperty("line.separator");
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				out += data[i][j];
				if (j != data[0].length - 1) {
					out += ", ";
				}
			}
			out += System.getProperty("line.separator");
		}
		return out;
	}

	/***
	 * Saves a file to the file system
	 * 
	 * @param filename
	 * the file you want to save
	 */
	public void saveToFile(String filename) {
		File outFile = new File(filename);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
			writer.write(this.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getFilePathToCSV() {
		return filePathToCSV;
	}

	public void setFilePathToCSV(String filePathToCSV) {
		this.filePathToCSV = filePathToCSV;
	}

	public double[][] getData() {
		return data;
	}

	public void setData(double[][] data) {
		this.data = data;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}
}
