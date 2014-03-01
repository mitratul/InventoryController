 /*
 * VendorProcessor.java
 *
 * Created on July 14, 2007, 10:20 HRS
 * Last Modified on July 14, 2007, 10:20 HRS
 *
 */

package inventorycontroller.function;

/**
 *
 * @author  brinto
 */
public class VendorProcessor {
	
	public static final String VENDOR_ID="VND";
	
	public VendorProcessor(inventorycontroller.function.DbInterface dbi){
		this.dbi=dbi;
	}
	
    public java.lang.String getNewVndNo() {
    	java.lang.String[][] vndNos=null;
    	
    	try{
	    	vndNos=dbi.cmdSelect(
	    		"vndMaster", "vndNo", "1=1 order by vndNo");
    	}
    	catch(java.sql.SQLException ex){
    		System.out.println (ex);
    		return "";
    	}
    	
    	int vndNo=0;
    	String currentDate=inventorycontroller.function.DateProcessor
    	 .getCurrentDate(dbi);
    	String yr=inventorycontroller.util.DateUtil.getFinancialYear(currentDate);
    	
    	for (int i = 0; i<vndNos.length; i++){
    		if(
    			vndNos[i][0].startsWith(VENDOR_ID+"/") && 
    			vndNos[i][0].endsWith("/"+yr)
    		   ){
    			vndNo++;
    		}
    	}
    	java.lang.String fullVndNo=new java.lang.String(VENDOR_ID+"/");
    	for (int i = 7; i>=0; i--){	//if odrNo>10^7 then length will incr
	    	if((double)vndNo<java.lang.Math.pow((double)10, (double)i)){
	    		fullVndNo=fullVndNo+"0";
	    	}
	    	else{
	    		fullVndNo=fullVndNo+vndNo;
	    		break;
	    	}
    	}
    	fullVndNo=fullVndNo+"/"+yr;
    	
    	return fullVndNo;
    }
    
	public java.lang.String[][] getVnds(java.lang.String whereClause) {
		java.lang.String[][] res=null;
		java.lang.String[][] tmp1=null;
		try{
			tmp1=dbi.cmdSelect("vndMaster", 
			 "vndCmpName, "+
			 "vndVatNo, "+
			 "vndAddress, "+
			 "vndPin, "+
			 "vndPhone, "+
			 "vndFax, "+
			 "vndQARating, "+
			 "vndReliabilityRating, "+
			 "vndCtName, "+
			 "vndCtPhone, "+
			 "vndRemark, "+
			 "vndNo", whereClause);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		
		res=new java.lang.String[tmp1.length][11];
		for (int i = 0; i<res.length; i++){
			res[i]=new java.lang.String[11];
			for (int j = 0; j<3; j++){
				res[i][j]=new java.lang.String(tmp1[i][j]);
			}
			res[i][2]=res[i][2]+", PIN: "+tmp1[i][3];
			for (int j = 3; j<11; j++){
				res[i][j]=new java.lang.String(tmp1[i][j+1]);
			}
		}
		return res;
	}
	
	public String[][] getVndNameId(String whereClause){
		java.lang.String[][] res=null;
		try{
			res=dbi.cmdSelect(
				"vndMaster", 
				"vndMaster.vndNo, vndMaster.vndCmpName",
				whereClause
			);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		return res;
	}
	
	public String getVndNo(String vndName){
		java.lang.String res=null;
		java.lang.String[][] tmp=null;
		try{
			tmp=dbi.cmdSelect(
				"vndMaster", 
				"vndMaster.vndNo",
				"vndMaster.vndCmpName='"+vndName+"'"
			);
			res=new String(tmp[0][0]);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		return res;
	}
	
	
	public static void main(String[] args){
		java.lang.String[][] res=null;
		try{
		 	res=new VendorProcessor(
		 	 new inventorycontroller.function.DbInterface("./db/db.mdb", "Microsoft Access Driver (*.mdb)"))
		 	 .getVnds("1=1");
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		for (int i = 0; i<res.length; i++){
			for (int j = 0; j<11; j++){
				System.out.println (res[i][j]);
			}
			System.out.println ("");
		}
	}
	
    
    //* Variables declaration - 
	private inventorycontroller.function.DbInterface dbi;
    //* End of variables declaration
}