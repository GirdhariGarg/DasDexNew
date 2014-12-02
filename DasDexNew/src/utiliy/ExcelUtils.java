package utiliy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	private static XSSFWorkbook Workbook;
	private static XSSFSheet Sheet;
	private static XSSFRow Row;
	private static XSSFCell Cell;
	
	// this method will set file path 
	public static void setFilePath(String path, String sheetname) throws IOException{
		try{
		// open excel file 
		FileInputStream fis = new FileInputStream(path);
		// open workbook and sheet
		Workbook = new XSSFWorkbook(fis);
		Sheet = Workbook.getSheet(sheetname);
		Log.info("Excel Sheet successful opened");
		 
		}catch(Exception e){
			Log.error("Class ExcelUtils|Method setFilePath| Exception desc" +e.getMessage());
		}
	}
	
	//this method will get data from excel sheet
	public static String getCellData(int RowNum, int ColNum){
		try{
		// getting cell value 
		Cell = Sheet.getRow(RowNum).getCell(ColNum);
		//convert it into string 
		String CellData = Cell.getStringCellValue();
		return CellData;
		}catch(Exception e){
			Log.info("Class EcxelUtils | Method getCellData | Exception desc" +e.getMessage());
			return "";
			}
	}

	@SuppressWarnings("static-access")
	public static void setCellData(String Result, int RowNum, int ColNum){
		try{
		Row = Sheet.getRow(RowNum);
		Cell  = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
		if(Cell == null){
			Cell = Row.createCell(ColNum);
			Cell.setCellValue(Result);
		}else{
			Cell.setCellValue(Result);
		}
		
	FileOutputStream fos = new FileOutputStream(Constant.TestData_path);
	Workbook.write(fos);
	fos.flush();
	fos.close();
		}catch(Exception e){
			Log.error("Class ExcelUtils | Method setCellData | Exception "+e.getMessage());
		}
	}
	
	public static int getRowContains(String sTestCaseName, int ColNum){
		int i;
		try{
		int rowcount = ExcelUtils.getRowUsed(ColNum);
		for( i = 0 ; i<=rowcount;i++){
			if(ExcelUtils.getCellData(i, ColNum).equalsIgnoreCase(sTestCaseName)){
				break;
				}
			}
		return i;
		}catch(Exception e){
			Log.error("Class ExcelUtils | Method getRowContains| Exception desc"+e.getMessage());
			throw (e);
		}
		
	}

	public static int getRowUsed(int ColNum) {
		try{
		int rowcount = Sheet.getLastRowNum();
		Log.info("Total no of rows in test case Column");
		return rowcount;
		}catch(Exception e){
			Log.error("Class ExcelUtils| Method getRowUsed| Exception desc"+e.getMessage());
			throw(e);
		}
		
	}
}
