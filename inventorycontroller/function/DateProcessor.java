/*
 * DateProcessor.java
 *
 * Created on July 25, 2007, 21:45 HRS
 * Last Modified on August 31, 2007, 00:26 HRS
 *
 */

package inventorycontroller.function;

/**
 *
 * @author  brinto
 */
public class DateProcessor {
	
	public static String getCurrentDate(
	 inventorycontroller.function.DbInterface dbInterface){
    	String[][] tmp=null;
    	try{
    		tmp=dbInterface.cmdSelect(
    			"dateTSMap", 
    			"dateTSMap.date", 
    			"1=1 order by dateTSMap.date"
    		);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	
    	return tmp[tmp.length-1][0];
	}
	
}