 /*
 * JobProcessor.java
 *
 * Created on July 06, 2007, 19:20 HRS
 * Last Modified on July 25, 2007, 17:20 HRS
 *
 */

package inventorycontroller.function;

/**
 *
 * @author  brinto
 */
public class JobProcessor {
	
	public static final String JOB_ID="JOB";
	
	public JobProcessor(inventorycontroller.function.DbInterface dbi){
		this.dbi=dbi;
	}
	
    public java.lang.String getNewJobNo() {
    	java.lang.String[][] jbNos=null;
    	
    	try{
	    	jbNos=dbi.cmdSelect(
	    		"jbMaster", "jbNo", "1=1 order by jbNo");
    	}
    	catch(java.sql.SQLException ex){
    		System.out.println (ex);
    		return "";
    	}
    	
    	int jbNo=0;
    	String currentDate=inventorycontroller.function.DateProcessor
    	 .getCurrentDate(dbi);
    	String yr=inventorycontroller.util.DateUtil.getFinancialYear(currentDate);
    	
    	for (int i = 0; i<jbNos.length; i++){
    		if(
    			jbNos[i][0].startsWith(JOB_ID+"/") && 
    			jbNos[i][0].endsWith("/"+yr)
    		){
    			jbNo++;
    		}
    	}
    	java.lang.String fullJbNo=new java.lang.String(JOB_ID+"/");
    	for (int i = 7; i>=0; i--){	//if jbNo>10^7 then length will incr
	    	if((double)jbNo<java.lang.Math.pow((double)10, (double)i)){
	    		fullJbNo=fullJbNo+"0";
	    	}
	    	else{
	    		fullJbNo=fullJbNo+jbNo;
	    		break;
	    	}
    	}fullJbNo=fullJbNo+"/"+yr;
    	
    	return fullJbNo;
    }
    
	public java.lang.String[][] getPendingJobs(){
		return getJobs("jbStatus='pending'");
	}
	
	public java.lang.String[][] getProgressingJobs(){
		return getJobs("jbStatus='progressing'");
	}
	
	public java.lang.String[][] getFinishedJobs(){
		return getJobs("jbStatus='finished'");
	}
	
	private java.lang.String[][] getJobs(java.lang.String whereClause) {
		java.lang.String[][] res=null;
		try{
			res=dbi.cmdSelect(
				"jbMaster, odrMaster, prdMaster", 
				
			 	"odrMaster.custName, "+
			 	"prdMaster.prdName, "+
			 	"jbMaster.quantity, "+
			 	"jbMaster.jbStDate, "+
			 	"jbMaster.jbEndDate, "+
			 	"jbMaster.jbRemark, "+
			 	"odrMaster.odrNo, "+
			 	"jbMaster.jbNo", 
			 	 
			 	"jbMaster.odrNo=odrMaster.odrNo and "+
			 	"jbMaster.prdNo=prdMaster.prdId and "+whereClause
			);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		for (int i = 0; i<res.length; i++){
			res[i][3]=inventorycontroller.util.DateUtil.getDisplayFormat(res[i][3]);
			res[i][4]=inventorycontroller.util.DateUtil.getDisplayFormat(res[i][4]);
		}
		return res;
	}
	
	public java.lang.String[][] getJobsForBOM() {
		java.lang.String[][] tmp1=getJobs("jbStatus='pending'");
		java.lang.String[][] res=new java.lang.String[tmp1.length][6];
		
		for (int i = 0; i<res.length; i++){
			res[i]=new java.lang.String[6];
			
			for (int j = 0; j<4; j++){
				res[i][j]=tmp1[i][j];
			}
			res[i][4]=tmp1[i][5];
			res[i][5]=tmp1[i][7];
		}
		return res;
	}
	
	public java.lang.String[][] getJobsForIssue(){
		java.lang.String[][] res=null;
		try{
			res=dbi.cmdSelect(
				"jbMaster, odrMaster, prdMaster, bomMaster",
				 
			 	"odrMaster.custName, "+
			 	 "jbMaster.jbNo, "+
			 	 "bomMaster.bomNo, "+
			 	 "prdMaster.prdName, "+
			 	 "jbMaster.quantity", 
			 	 
			 	"jbMaster.jbStatus='progressing' and "+
			 	 "jbMaster.odrNo=odrMaster.odrNo and "+
			 	 "jbMaster.prdNo=prdMaster.prdId and "+
			 	 "jbMaster.jbNo=bomMaster.jbNo"
			);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		return res;
	}
	
	public java.lang.String[][] getJobsForExcessRequisition(){
		java.lang.String[][] res=null;
		try{
			res=dbi.cmdSelect(
				"jbMaster, odrMaster, prdMaster, bomMaster",
				 
			 	"odrMaster.custName, "+
			 	 "odrMaster.custPONo, "+
			 	 "jbMaster.jbNo, "+
			 	 "prdMaster.prdName, "+
			 	 "jbMaster.quantity", 
			 	 
			 	" not jbMaster.jbStatus='pending' and "+
			 	 "jbMaster.odrNo=odrMaster.odrNo and "+
			 	 "jbMaster.prdNo=prdMaster.prdId and "+
			 	 "jbMaster.jbNo=bomMaster.jbNo"
			);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		return res;
	}
	
	public Object[][] getJobsForMaterialCostSheet(String sDate, String eDate){
		
		String[][] tmp=null;
		try{
			tmp=dbi.cmdSelect(
				"jbMaster, odrMaster",
				 
			 	"jbMaster.jbNo, "+
			 	"jbMaster.jbEndDate, "+
			 	"odrMaster.custName, "+
			 	"odrMaster.custPONo", 
			 	 
			 	"jbMaster.jbStatus='finished' and "+
			 	"jbMaster.jbEndDate>='"+sDate+"' and "+
			 	"jbMaster.jbEndDate<='"+eDate+"' and "+
			 	"odrMaster.odrNo=jbMaster.odrNo"
			);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		
		Object[][] res=new Object[tmp.length][5];
		for (int i = 0; i<res.length; i++){
			res[i][0]=new Boolean(false);
			for (int j = 0; j<4; j++){
				res[i][j+1]=tmp[i][j];
			}
			res[i][2]=inventorycontroller.util.DateUtil.getDisplayFormat(res[i][2].toString());
		}
		return res;
	}
	
	public Object[][] getWIP(){
		
		String[][] tmp=null;
		try{
			tmp=dbi.cmdSelect(
				"jbMaster, odrMaster",
				 
			 	"jbMaster.jbNo, "+
			 	"jbMaster.jbStDate, "+
			 	"odrMaster.custName, "+
			 	"odrMaster.custPONo", 
			 	 
			 	"jbMaster.jbStatus='progressing' and "+
			 	"odrMaster.odrNo=jbMaster.odrNo"
			);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		
		Object[][] res=new Object[tmp.length][5];
		for (int i = 0; i<res.length; i++){
			res[i][0]=new Boolean(false);
			for (int j = 0; j<4; j++){
				res[i][j+1]=tmp[i][j];
			}
			res[i][2]=inventorycontroller.util.DateUtil.getDisplayFormat(res[i][2].toString());
		}
		return res;
	}
	
	public java.lang.String[][] getJobsForReturn(){
		java.lang.String[][] res=null;
		java.lang.String[][] tmp=null;
		java.util.Vector<String> v=new java.util.Vector<String>();
		java.util.Vector<Integer> v1=new java.util.Vector<Integer>();
    	try{
			tmp=dbi.cmdSelect(
				"jbMaster, odrMaster, prdMaster, bomMaster, gdsJbTrnMaster",
				 
			 	"odrMaster.custName, "+
			 	 "jbMaster.jbNo, "+
			 	 "bomMaster.bomNo, "+
			 	 "prdMaster.prdName, "+
			 	 "jbMaster.quantity", 
			 	 
			 	" jbMaster.jbNo=gdsJbTrnMaster.jbNo and "+
			 	 "jbMaster.jbStatus='progressing' and "+
			 	 "jbMaster.odrNo=odrMaster.odrNo and "+
			 	 "jbMaster.prdNo=prdMaster.prdId and "+
			 	 "jbMaster.jbNo=bomMaster.jbNo"
			);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		for (int i = 0; i<tmp.length; i++){
			if(v.contains(tmp[i][1])){
				continue;
			}
			v.add(tmp[i][1]);
			v1.add(i);
		}
		res=new String[v1.size()][5];
		for (int i = 0; i<res.length; i++){
			res[i]=tmp[v1.elementAt(i)];
		}
		return res;
	}
	
	public String[][] getOdrDetail(String odrNo){
		inventorycontroller.function.OrderProcessor op
		 =new inventorycontroller.function.OrderProcessor(dbi);
    	java.lang.String[][] tmpQty=op.getQuantity(odrNo);
    	java.lang.String[][] tmpPnd=op.getPendingQuantity(odrNo);
    	
    	String[][] res=new java.lang.String[tmpQty.length][3];
    	for (int i = 0; i<res.length; i++){
    		res[i]=new java.lang.String[3];
    		res[i][0]=new java.lang.String(tmpQty[i][0]);
    		res[i][1]=new java.lang.String(tmpQty[i][1]);
    		for (int j = 0; j<tmpPnd.length; j++){
    			if(tmpQty[i][0].compareTo(tmpPnd[j][0])==0){
    				res[i][2]=new java.lang.String(tmpPnd[j][1]);
    			}
    		}
    	}
		return res;
	}
	
	public void setJobStatus(java.lang.String jbNo, java.lang.String status){
		if(status.compareTo("pending")!=0 && status.compareTo("progressing")!=0 && status.compareTo("finished")!=0){
			System.out.println ("invalid status: "+status);
			return;
		}
		try{
			dbi.cmdUpdate("jbMaster", "jbStatus='"+status+"'", "jbNo='"+jbNo+"'");
		}
		catch(java.lang.Exception ex){
			ex.printStackTrace();
		}
		
		if(status.compareToIgnoreCase("finished")!=0){
			return;
		}
		
		//* if finished then give end date.
		String date=inventorycontroller.function.DateProcessor.getCurrentDate(dbi);
		try{
			dbi.cmdUpdate(
				"jbMaster", 
				"jbMaster.jbEndDate='"+date+"'", 
				"jbMaster.jbNo='"+jbNo+"'"
			);
		}
		catch(java.lang.Exception ex){
			ex.printStackTrace();
		}
		
		//* check whether this job finishes the order.
		String[][] tmp=null;
		try{
			tmp=dbi.cmdSelect(
				"jbMaster j1, jbMaster j2", 
				
				"j2.odrNo, j2.jbStatus", 
				
				"j1.jbNo='"+jbNo+"' and "+
				"j2.odrNo=j1.odrNo"
			);
		}
		catch(java.lang.Exception ex){
			ex.printStackTrace();
		}
		int i =0;
		for (i = 0; i<tmp.length; i++){
			if(tmp[i][1].compareToIgnoreCase("finished")!=0){
				break;
			}
		}
		if(i<tmp.length){ // not all the jobs are finished.
			return;
		}
		// complete the order.
		new inventorycontroller.function.OrderProcessor(dbi)
		 .setOrderStatus(tmp[0][0], "complete");
		
		
	}
	
	
	public static void main(String[] args){
	}
	
    
    //* Variables declaration - 
	private inventorycontroller.function.DbInterface dbi;
    //* End of variables declaration
}