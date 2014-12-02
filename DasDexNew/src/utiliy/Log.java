package utiliy;

import org.apache.log4j.Logger;

public class Log {
	// Intialize log4j class
	private static Logger Log = Logger.getLogger(Log.class.getName());
	
	// This method will print TestCase name 
	public static void startTestCase(String sTestCaseName){
		Log.info("********************************************************************************************************************");
		Log.info("********************************************************************************************************************");
		Log.info("**********************************            sTestCaseName                *****************************************");
		Log.info("********************************************************************************************************************");
		Log.info("********************************************************************************************************************");
	}
	
	//This method will print end test case
	public static void endTestCase(){
		Log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		Log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX----------- End TestCasee------------------  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		Log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	}
	
	//this method will print information in log file
	public static void info(String message){
		Log.info(message);
		}
	
	//This method print warn message
	public static void warn(String message){
		Log.warn(message);
	}
	
	//This method will print error message
	public static void error(String message){
		Log.error(message);
		
	}
	
	//This method will print fatel message
	public static void fatel(String message){
		Log.fatal(message);
	}
	
	// This message will print debug message
	public static void debug(String message){
		Log.debug(message);
	}
}
