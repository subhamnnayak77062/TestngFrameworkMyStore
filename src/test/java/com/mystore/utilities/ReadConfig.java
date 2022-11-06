package com.mystore.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties properties;

	String path = "E:\\Software\\MyWorkspace\\MyStoreProject\\Configuration\\config.properties";
	
	public ReadConfig(){
		properties = new Properties();
	
		try{
			FileInputStream fis = new FileInputStream(path);
			properties.load(fis);
		}
		catch (Exception e) {
			e.getMessage();
		}
	}
	public String getBaseUrl() {
		
		String baseurlvalue = properties.getProperty("baseUrl");
		
		if(baseurlvalue!=null) 
			return baseurlvalue;
		
		else
			throw new RuntimeException("url is not specified in config file");
		
	}
	
public String getBrowser() {
		
		String browserValue = properties.getProperty("browser");
		
		if(browserValue!=null) 
			return browserValue;
		
		else
			throw new RuntimeException("browser is not specified in config file");
		
	}
}
