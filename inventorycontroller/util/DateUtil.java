/*
 * DateUtil.java
 *
 * Created on September 3, 2007, 11:54 AM
 */

package inventorycontroller.util;

/**
 *
 * @author  brinto
 */
public class DateUtil {
    
    
    public static String getRawFormat(java.util.Date d){
    	
    	String str=d.toString();
    	java.util.StringTokenizer st=new java.util.StringTokenizer(str, " ");
    	st.nextToken();
    	String mnthName=st.nextToken();
    	String day=st.nextToken();
    	st.nextToken();
    	st.nextToken();
    	String yr=st.nextToken();
    	String mnthNo="00";
    	
    	if(mnthName.compareTo("Jan")==0){
    		mnthNo="01";
    	}
    	else if(mnthName.compareTo("Feb")==0){
    		mnthNo="02";
    	}
    	else if(mnthName.compareTo("Mar")==0){
    		mnthNo="03";
    	}
    	else if(mnthName.compareTo("Apr")==0){
    		mnthNo="04";
    	}
    	else if(mnthName.compareTo("May")==0){
    		mnthNo="05";
    	}
    	else if(mnthName.compareTo("Jun")==0){
    		mnthNo="06";
    	}
    	else if(mnthName.compareTo("Jul")==0){
    		mnthNo="07";
    	}
    	else if(mnthName.compareTo("Aug")==0){
    		mnthNo="08";
    	}
    	else if(mnthName.compareTo("Sep")==0){
    		mnthNo="09";
    	}
    	else if(mnthName.compareTo("Oct")==0){
    		mnthNo="10";
    	}
    	else if(mnthName.compareTo("Nov")==0){
    		mnthNo="11";
    	}
    	else if(mnthName.compareTo("Dec")==0){
    		mnthNo="12";
    	}
    	
    	return yr+mnthNo+day;
    }
    
    public static String getDisplayFormat(String rawDate){
    	if(rawDate.length()!=8){
    		return rawDate;
    	}
    	String yr=rawDate.substring(0, 4);
    	String mnth=rawDate.substring(4, 6);
    	String day=rawDate.substring(6);
    	
    	return day+"-"+mnth+"-"+yr;
    }
    
    public static String getFinancialYear(String rawDate){
    	
    	int iyr=Integer.parseInt(rawDate.substring(2, 4));
    	int imn=Integer.parseInt(rawDate.substring(4, 6))-1;
    	
    	iyr=imn<3?iyr-1:iyr;	//if month less than april, then prev fin. yr
    	String yr=(iyr<10?"0":"")+iyr+(iyr<9?"-0":"-")+(iyr+1);
    	
    	return yr;
    }
    
    public static java.util.Date getDateValue(String rawDate){
    	int yr=Integer.parseInt(rawDate.substring(0, 4));
    	int mnth=Integer.parseInt(rawDate.substring(4, 6))-1; //as January=0 in Calender
    	int day=Integer.parseInt(rawDate.substring(6));
    	
    	long mlTime=((yr-1970)*12 + mnth);
    	java.util.Calendar c=new java.util.GregorianCalendar(yr, mnth, day);
    	
    	return c.getTime();
    }
    
    
    private java.util.Date date;
}