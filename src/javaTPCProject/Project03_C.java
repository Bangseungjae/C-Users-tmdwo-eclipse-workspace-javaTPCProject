package javaTPCProject;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellType;

public class Project03_C {

	public static void main(String[] args) {
		String fileName = "cellDataType1.xlsx";
		try(FileInputStream fis = new FileInputStream(fileName)) {
			XSSFWorkbook workbook =new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.rowIterator();
			while(rows.hasNext()) {
				XSSFRow row = (XSSFRow) rows.next();
				Iterator<Cell> cells= row.cellIterator();
				while(cells.hasNext()) {
					XSSFCell cell= (XSSFCell) cells.next();
					if(cell.getCellType()==Cell.CELL_TYPE_STRING) { 
						System.out.println("["+cell.getRowIndex()+","+cell.getColumnIndex()+"] = STRING, Value= "+cell.getRichStringCellValue().toString());
					}else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC) {
						System.out.println("["+cell.getRowIndex()+","+cell.getColumnIndex()+"] = NUMERIC, Value= "+cell.getNumericCellValue());
					}else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
						System.out.println("["+cell.getRowIndex()+","+cell.getColumnIndex()+"] = BOOLEAN, Value= "+cell.getBooleanCellValue());
					}else if(cell.getCellType()== Cell.CELL_TYPE_BLANK) {
						System.out.println("["+cell.getRowIndex()+","+cell.getColumnIndex()+"] = BLANK CELL ");
					}
				}
			}
			
	}catch(Exception e) {
		e.printStackTrace();
		}
	}
	

}
