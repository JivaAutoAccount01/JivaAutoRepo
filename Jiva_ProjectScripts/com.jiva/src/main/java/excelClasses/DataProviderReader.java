package excelClasses;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;

public class DataProviderReader {

	public String[][] getTableArray(String xlFilePath, String sheetName) throws Exception {
		String[][] tableArray=null; 
		Sheet sheet = null;
	//	XSSFSheet sheet = null;
		
		try {
		//	XSSFWorkbook workbook = new XSSFWorkbook(new File(xlFilePath));
			Workbook workbook =  Workbook.getWorkbook (new File(xlFilePath));
			sheet = workbook.getSheet(sheetName);
		}
		catch (Exception e) {
			throw new Exception("Can not find sheetName: '"+sheetName+"' in file '"+xlFilePath+"' ");
		}		
		
		int numberOfColumns = 7;
		//retrieve up to 200 rows and 7 columns of spreadsheet data
		tableArray=new String[200][numberOfColumns];		

		allDone:
		for (int i=0;i<200;i++) {		
			for (int j=0;j<numberOfColumns;j++) {

				String value = null;
				
				try {
					value =  sheet.getCell(j,i).getContents();					
				} catch (ArrayIndexOutOfBoundsException e) {
					break allDone; //exception shortly after last row in sheet, e.g. row 62
				}
				
				if (value != null) {
					tableArray[i][j]=value;
				}

			}
		}

		return(tableArray);
	}

}
