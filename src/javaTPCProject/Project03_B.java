package javaTPCProject;

import java.io.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Project03_B {

	public static void main(String[] args) {
		
		try {
			Workbook wb = new XSSFWorkbook();
			Sheet sheet = wb.createSheet("My Sample Excel");
			InputStream is = new FileInputStream("pic.jpg");//이미지
			byte[] bytes=IOUtils.toByteArray(is);//이미지를 바이트 배열로 저장
			int pictureIdx =wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);//이미지의 번지를 저장
			is.close();//스트림 닫음
			
			CreationHelper helper = wb.getCreationHelper();//엑셀 이미지를 그리는 앵커를 위해 필요르한 함수
			Drawing drawing = sheet.createDrawingPatriarch();
			ClientAnchor anchor = helper.createClientAnchor();
			anchor.setCol1(1);
			anchor.setRow1(2);
			anchor.setCol2(2);
			anchor.setRow2(3);
			
			Picture pict= drawing.createPicture(anchor, pictureIdx);
			Cell cell = sheet.createRow(2).createCell(1);
			int w=20*256;
			sheet.setColumnWidth(1, w);
			short h=120*20;
			cell.getRow().setHeight(h);
			
			FileOutputStream fileOut = new FileOutputStream("myFile.xlsx");
			wb.write(fileOut);
			fileOut.close();
			System.out.println("이미지 생성 성공");			
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}



