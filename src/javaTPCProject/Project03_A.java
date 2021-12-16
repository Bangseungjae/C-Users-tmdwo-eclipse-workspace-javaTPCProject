package javaTPCProject;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.*;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import kr.inflearn.ExcelVO;

public class Project03_A {

	public static void main(String[] args) {
		String fileName = "bookList.xlsx";
		List<ExcelVO> data = new ArrayList<ExcelVO>();
		
		try(FileInputStream fis = new FileInputStream(fileName)) {
			XSSFWorkbook workbook = new XSSFWorkbook(fis); // °¡»óÀÇ ¿¢¼¿
			XSSFSheet  sheet = workbook.getSheetAt(0); // ¿¢¼¿ ½ÃÆ®ÇÏ³ª °¡Á®¿È
			java.util.Iterator<Row> rows=sheet.rowIterator(); // row¸¦ IteratorÇüÅÂ·Î °¡Á®¿È
			rows.next(); // Ã¹¹ø¤Š´Â Á¦¸ñÀÌ´Ï±î °É·¯³¿
			String[] imsi = new String[5];
			while(rows.hasNext()) {
				XSSFRow row = (XSSFRow) rows.next();
				java.util.Iterator<Cell> cells= row.cellIterator();
				int i=0;
				while(cells.hasNext()) {
					XSSFCell cell = (XSSFCell) cells.next();
					imsi[i]=cell.toString();
					i++;
				}
				// ¹­°í(VO) -> ´ã°í(List)
				ExcelVO vo = new ExcelVO(imsi[0], imsi[1], imsi[2], imsi[3], imsi[4]);
				data.add(vo);
			}
			showExcelData(data);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void showExcelData(List<ExcelVO> data) {
		for(ExcelVO vo : data) {
			System.out.println(vo);
		}
	}

}
