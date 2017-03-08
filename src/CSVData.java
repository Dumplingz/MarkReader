import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/***
 * A class to read/write numerical CSV files and allow easy access
 * @author efraisse694
 *
 */

public class CSVData {
	private double[][] data;
	private String[] columnNames;
	private double numRows;
	private String filePathToCSV;
	
	public CSVData(String filepath, String[] columnNames, int startRow) {
		this.filePathToCSV = filepath;

		String dataString = readFileAsString(filepath);
		String[] lines = dataString.split("\n");

		// number of data points
		int n = lines.length - startRow;
		this.numRows = n;
		int numColumns = columnNames.length;

		// create storage for column names
		this.columnNames = columnNames;

		// create storage for data
		this.data = new double[n][numColumns];
		
		for (int i = 0; i < lines.length - startRow; i++) {
			String line = lines[startRow + i];
			String[] coords = line.split(",");
			
			for (int j = 0; j < numColumns; j++) {			
				if (coords[j].endsWith("\n")) coords[j] = coords[j].substring(0, coords[j].length() - 1);
				double val = Double.parseDouble(coords[j]);
				data[i][j] = val;
			}
		}
	}

	public double getNumRows() {
		return numRows;
	}

	public void setNumRows(double numRows) {
		this.numRows = numRows;
	}

	public String getFilePathToCSV() {
		return filePathToCSV;
	}

	public void setFilePathToCSV(String filePathToCSV) {
		this.filePathToCSV = filePathToCSV;
	}

	private String readFileAsString(String filepath) {
		StringBuilder output = new StringBuilder();
		try (Scanner scanner = new Scanner(new File(filepath))) {
			
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				output.append(line + System.getProperty("line.separator"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output.toString();
	}
	
	public static CSVData readCSVFile(String filename, int numLinesToignore, String[] columnNames) {
		return null;
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
	
	/***
	 * Gets A row out of the CSVData file
	 * @param rowIndex the index of the row you want to get out of the CSVData file
	 * @return the row Index
	 */
	public double[] getRow(int rowIndex) {
		double[] row = new double[4];
		for (int i = 0; i < 4; i++) {
			row[i] = data[rowIndex][i];
		}
		return row;
	}
	
	/***
	 * Gets A column out of the CSVData file
	 * @param ColumnIndex the index of the column you want to get out of the CSVData file
	 * @return entire row
	 */
	public double[] getColumn(int columnIndex) {
		double[] array = new double[data.length];
		for (int i = 0; i < data.length; i++) {
			array[i] = data[i][columnIndex];
		}
		return array;
	}
			
	/***
	 * Overloaded method which takes a column name to return the column
	 * @param name Name/ type of data that a sensor is measuring inside the column
	 * @return entire column
	 */
	public double[] getColumn(String name) { //edit this it doesn't work
		double[] columnValues = new double[data.length];
		for (int i = 0; i < columnNames.length; i++) {
			if (columnNames[i].equals(name)) {
				System.out.println(name);
				int area = columnNames[i].indexOf(name);
				for (int j = 0; j < data.length; j++) {
					columnValues[j] = data[j][area];
				}
			}
		}
		return columnValues;
	}
	
	/***
	 * gets rows from a value to another
	 * @param startIndex row you are starting at
	 * @param endIndex row you are ending at
	 * @return a double array containing the desired rows
	 */
	public double[][] getRows(int startIndex, int endIndex) {
		return null;
	}
	

	/***
	 * Overloaded method which gets rows based on indices stored in an integer array
	 * @param rowIndexes integer array which contains all of the row indices you want to include
	 * @return double array containing all rows
	 */
	public double[][] getRows(int[] rowIndexes) {
		return null;
	}
		
	/***
	 * gets columns from a value to another
	 * @param startIndex column you are starting at
	 * @param endIndex column you are ending at
	 * @return double array containing the desired columns
	 */
	public double[][] getColumns(int startIndex, int endIndex) {
		double[][] array = new double[data.length][endIndex-startIndex+1];
		
		for (int j = startIndex; j < endIndex; j++) {
			for (int i = 0; i < data.length; i++) {
				array[i][j-startIndex] = data[i][j];
			}
		}
		return array;
	}
		
	/***
	 * Overloaded method which gets columns based on indices stored in a integer array
	 * @param colIndexes integer array which contains all of the column indices you want to include
	 * @return double array containing desired columns
	 */
	public double[][] getColumns(int[] colIndexes) {
		return null;
	}
	
	/***
	 * Overloaded method which gets columns based on a string of the column names
	 * @param colNames String array of column names
	 * @return double array with desired columns
	 */
	public double[][] getColumns(String[] colNames) {
		return null;
	}
	
	/***
	 * sets a specific value in the array to a new one
	 * @param rowIndex row you want to put your value in
	 * @param colIndex column you want to put your value in
	 * @param newValue new value you are putting into the array
	 * @return the new value | and maybe the old one
	 */
	public double[] setValue(int rowIndex, int colIndex, double newValue) {
		return null;
	}
	
	/***
	 * Sets an individual row to a specific set of values
	 * @param row you want to set
	 * @param colIndex column you want to put your value in
	 * @return the new values | and maybe the old ones
	 */
	public double[] setIndividualRow(int row, int colIndex) {
		return null;
	}
	
	/***
	 * Sets an individual column to a specific set of values
	 * @param column column you want to get
	 * @param rowIndex row you want to put your value in
	 * @return the new values | and maybe the old ones
	 */
	public double[] setIndividualColumn(int column, int rowIndex) {
		return null;
	}
	
	/***
	 * Get column names out of array
	 * @param columnNames array containing all of the column names
	 * @return the column names
	 */
	public String[] getColumnNames(String[] columnNames) {
		return this.columnNames;
	}

	/***
	 * Saves a file to the file system
	 * @param filename the file you want to save
	 */
	public void saveToFile(String data) {
		File outFile = new File(filePathToCSV);
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))){
			writer.write(data);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public void printRows(int start, int end) {
		for (int row = start; row < end; row++)	{
			for (int col = 0; col < 4; col++) {
				System.out.print(data[row][col] + "  ");
			}
			System.out.println();
		}
	}
}
