 /*
 * TimeStampProcessor.java
 *
 * Created on September 21, 2007, 09:45 HRS
 * Last Modified on September 24, 2007, 09:45 HRS
 *
 */

package inventorycontroller.function;

/**
 *
 * @author  brinto
 */
public class TimeStampProcessor {
	
	public static String getNewTSid(String rawDate, 
	 inventorycontroller.function.DbInterface dbInterface){
    	String[][] tmp=null;
    	try{
    		tmp=dbInterface.cmdSelect(
    			"dateTSMap", 
    			"dateTSMap.date, dateTSMap.openingTSid, dateTSMap.closingTSid", 
    			"dateTSMap.date='"+rawDate+"'"
    		);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	
    	String fullTSid="TS/"+rawDate;
    	
    	if(tmp.length==0){ //if 1st TS of the day then initialise.
	    	fullTSid=fullTSid+"0000";
    	}
    	else{
    		long lastTSno=-1;
    		long currentTSno=-1;
    		String lastTSid="";
    		
    		if(tmp[tmp.length-1][2].compareTo("_")==0){//if no closing then return opening
    			return tmp[tmp.length-1][1];
    		}
    		else{//if closing present then start with next value.
    			lastTSid=tmp[tmp.length-1][2];
	    		lastTSno=Integer.parseInt(
	    			lastTSid.substring(lastTSid.indexOf("/")+9, lastTSid.lastIndexOf("/"))
	    		);
	    		currentTSno=lastTSno+1;
    		}
    		
    		for (int i = 3; i>=0; i--){	//if odrNo>10^11 then length will incr
		    	if((double)currentTSno<java.lang.Math.pow((double)10, (double)i)){
		    		fullTSid=fullTSid+"0";
		    	}
		    	else{
		    		fullTSid=fullTSid+currentTSno;
		    		break;
		    	}
	    	}
    	}
    	fullTSid=fullTSid+"/"+
    		inventorycontroller.util.DateUtil.getFinancialYear(rawDate);
    	
    	return fullTSid;
    }
    
    public static String getClosingTSid(String rawDate, 
	 inventorycontroller.function.DbInterface dbInterface){
    	String[][] tmp=null;
    	try{
    		tmp=dbInterface.cmdSelect(
    			"dateTSMap", 
    			"dateTSMap.openingTSid, dateTSMap.closingTSid", 
    			"dateTSMap.date<='"+rawDate+"' order by dateTSMap.date"
    		);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	if(tmp.length==0){
    		return "_";
    	}
    	
    	String res=tmp[tmp.length-1][1];
    	int os=1;
    	while(res.compareTo("_")==0){ //if no closing
    		os++;
    		if(os>tmp.length){
    			break;
    		}
    		res=tmp[tmp.length-os][1];
    	}
    	
    	if(res.compareTo("_")!=0) {
    		return res;
    	}
    	else {
    		return "TS/000000000000/"+inventorycontroller.util.DateUtil.getFinancialYear(rawDate);
    	}
    	
    }
    
    public static String getOpeningTSid(String rawDate, 
	 inventorycontroller.function.DbInterface dbInterface){
    	String[][] tmp=null;
    	try{
    		tmp=dbInterface.cmdSelect(
    			"dateTSMap", 
    			"dateTSMap.openingTSid", 
    			"dateTSMap.date>='"+rawDate+"'"
    		);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	if(tmp.length==0){
    		return "_";
    	}
    	return tmp[0][0];
    }
    
    public static void updateTSid(String tsid, String rawDate, 
	 inventorycontroller.function.DbInterface dbInterface){
	 	String[][] tmp=null;
    	try{
    		tmp=dbInterface.cmdSelect(
    			"dateTSMap", 
    			"dateTSMap.date", 
    			"dateTSMap.date='"+rawDate+"'"
    		);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	if(tmp.length==0){
	    	try{
	    		dbInterface.cmdInsert(
	    			"dateTSMap", 
	    			
	    			"'"+rawDate+"', "+
	    			"'TS/"+rawDate+"0000/"+inventorycontroller.util.DateUtil.getFinancialYear(rawDate)+"', "+
	    			"'"+tsid+"'"
	    		);
	    	}
	    	catch(Exception ex){
	    		ex.printStackTrace();
	    	}
    	}
    	else {
	    	try{
	    		dbInterface.cmdUpdate(
	    			"dateTSMap", 
	    			"dateTSMap.closingTSid='"+tsid+"'", 
	    			"dateTSMap.date='"+rawDate+"'"
	    		);
	    	}
	    	catch(Exception ex){
	    		ex.printStackTrace();
	    	}
    	}
	}
	
	public static void main(String[] args) throws Exception {
		inventorycontroller.function.DbInterface dbi=
		 new inventorycontroller.function.DbInterface("./db/db.mdb", "Microsoft Access Driver (*.mdb)");
		
		System.out.println (TimeStampProcessor.getNewTSid("20071001", dbi));
		dbi.close();
	}
	
}