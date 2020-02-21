package provider;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataProvider {
	
	private Properties properties;
	private final String propertyFilePath= "C:\\Users\\SR068695\\test2\\Naukri.properties";

	
	public DataProvider(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}
	
	public String getDriverPath(){
		String driverPath = properties.getProperty("driverPath");
		if(driverPath!= null) return driverPath;
		else throw new RuntimeException("driverPath not specified in the Naukri.properties file.");		
	}
/*
	public long getImplicitlyWait() {		
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
		else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");		
	}
	*/
	
	public String getApplicationUrl() {
		String url = properties.getProperty("url");
		if(url != null) return url;
		else throw new RuntimeException("url not specified in the Naukri.properties file.");
	}
	
	public String getExcelName(){
		String excelName = properties.getProperty("excelName");
		return excelName;
		
	}
	
	public String getExcelPath() {
		String excelPath= properties.getProperty("excelPath");
		return excelPath;
	}
	
	public String getSheetName( ) {
		String sheetName= properties.getProperty("sheetName");
		return sheetName;
	}
	
	//public String getSheetName1( ) {
		//String sheetName1= properties.getProperty("sheetName1");
		//return sheetName1;
	//}
}

