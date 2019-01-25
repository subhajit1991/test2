package excelLib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelLib {
		
	private static List<String> l=new ArrayList<String>();



	public static List<String> readExcel(String filePath,String fileName,String sheetName) throws IOException{
		
		 //Create an object of File class to open xlsx file

	    File file =    new File("C:\\Users\\SUBROY\\Documents\\Naukri Login.xlsx");
	    
	  //Create an object of FileInputStream class to read excel file

	    FileInputStream inputStream = new FileInputStream(file);

	    Workbook Workbook1 = null;
	    
	  //Find the file extension by splitting file name in substring  and getting only extension name

	    String fileExtensionName = fileName.substring(fileName.indexOf("."));

	    //Check condition if the file is xlsx file

	    if(fileExtensionName.equals(".xlsx")){

	    //If it is xlsx file then create object of XSSFWorkbook class

	    Workbook1 = new XSSFWorkbook(inputStream);
	    
	  //Check condition if the file is xls file
	    }
	    else if(fileExtensionName.equals(".xls")){

	        //If it is xls file then create object of XSSFWorkbook class

	     Workbook1 = new HSSFWorkbook(inputStream);

	    }

	    
	    
	  //Read sheet inside the workbook by its name

	    Sheet Login = Workbook1.getSheet("Login");

	    //Find number of rows in excel file

	    int rowCount = Login.getPhysicalNumberOfRows();
	    //Create a loop over all the rows of excel file to read it

	    for (int i = 1; i < rowCount; i++) {

	        Row row = Login.getRow(i);

	        //Create a loop to print cell values in a row
	      
	        for (int j = 0; j < 2; j++) {

	            //Print Excel data in console
	           	String s=row.getCell(j).getStringCellValue();
	        
	        	l.add(s);
	        	System.out.println(l);
	       
	        }

	        //System.out.println();

	    }
		return l;

	    

	    }

	    

	    //Main function is calling readExcel function to read data from excel file

	    public static void main(String...strings) throws IOException{

	    //Create an object of ReadGuru99ExcelFile class

	    	excelLib objExcelFile = new excelLib();

	    //Prepare the path of excel file

	    String filePath = System.getProperty("user.dir")+"\\src\\excelExportAndFileIO";

	    //Call read file method of the class to read data

	    objExcelFile.readExcel(filePath,"Naukri Login.xlsx","Login");
	}
}
