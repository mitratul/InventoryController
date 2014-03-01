 /*
 * OrderProcessor.java
 *
 * Created on July 06, 2007, 19:20 HRS
 * Last Modified on August 27, 2007, 16:20 HRS
 *
 */

package inventorycontroller.function;

/**
 *
 * @author  brinto
 */
public class OrderProcessor {
	
	public static final String ORDER_ID="ODR";
	
	public OrderProcessor(inventorycontroller.function.DbInterface dbi){
		this.dbi=dbi;
	}
	    
    public java.lang.String getNewOdrNo() {
    	java.lang.String[][] odrNos=null;
    	
    	try{
	    	odrNos=dbi.cmdSelect(
	    		"odrMaster", "odrNo", "1=1 order by odrNo");
    	}
    	catch(java.sql.SQLException ex){
    		System.out.println (ex);
    		return "";
    	}
    	
    	int odrNo=0;
    	String currentDate=inventorycontroller.function.DateProcessor
    	 .getCurrentDate(dbi);
    	String yr=inventorycontroller.util.DateUtil.getFinancialYear(currentDate);
    	
    	for (int i = 0; i<odrNos.length; i++){
    		if(
    			odrNos[i][0].startsWith(ORDER_ID+"/") && 
    			odrNos[i][0].endsWith("/"+yr)
    		){
    			odrNo++;
    		}
    	}
    	java.lang.String fullOdrNo=new java.lang.String(ORDER_ID+"/");
    	for (int i = 7; i>=0; i--){	//if odrNo>10^7 then length will incr
	    	if((double)odrNo<java.lang.Math.pow((double)10, (double)i)){
	    		fullOdrNo=fullOdrNo+"0";
	    	}
	    	else{
	    		fullOdrNo=fullOdrNo+odrNo;
	    		break;
	    	}
    	}
    	fullOdrNo=fullOdrNo+"/"+yr;
    	
    	return fullOdrNo;
    }
	
	private java.lang.String[][] getOrders(java.lang.String whereClause) {
		java.lang.String[][] res=null;
		try{
			res=dbi.cmdSelect("odrMaster", "custName, odrDate, odrRemark, custPONo, odrNo", whereClause);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		for (int i = 0; i<res.length; i++){
			res[i][1]=inventorycontroller.util.DateUtil
			 .getDisplayFormat(res[i][1]); //date
		}
		return res;
	}
    
	public java.lang.String[][] getPendingOrders(){
		return getOrders("odrStatus='pending'");
	}
	
	public java.lang.String[][] getIncompleteOrders(){
		return getOrders("odrStatus='incomplete'");
	}
	
	public java.lang.String[][] getCompleteOrders(){
		return getOrders("odrStatus='complete'");
	}
	
	public java.lang.String[][] getNonCompleteOrders(){
		//java.lang.String[][] tmp=getOrders("odrStatus='pending' or odrStatus='incomplete'");
		java.lang.String[][] tmp=getOrders("not odrStatus='complete'");
		int count=0;
		boolean[] isAllocationComplete=new boolean[tmp.length];
		java.lang.String[][] tmp1=null;
		for (int i = 0; i<tmp.length; i++){
			tmp1=getPendingQuantity(tmp[i][4]);
			isAllocationComplete[i]=true;
			for (int j = 0; j<tmp1.length; j++){
				if(Double.parseDouble(tmp1[j][1])>0){
					count++;
					isAllocationComplete[i]=false;
					break;
				}
			}
		}
		java.lang.String[][] res=new java.lang.String[count][4];
		int j=0;
		for (int i = 0; i<tmp.length; i++){
			if(!isAllocationComplete[i]){
				res[j][0]=tmp[i][0];
				res[j][1]=tmp[i][1];
				res[j][2]=tmp[i][2];
				res[j++][3]=tmp[i][4];
			}
		}
		return res;
	}
	
	/** returns qty of order denoted by ordNo. */
	public java.lang.String[][] getQuantity(java.lang.String odrNo){
		java.lang.String[][] res=null;
		try{
			res=dbi.cmdSelect(
				"odrDesc, prdMaster", 
				"prdMaster.prdName, odrDesc.quantity", 
				"odrDesc.odrNo='"+odrNo+"' and odrDesc.prdNo=prdMaster.prdId");
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		return res;
	}
	
	public java.lang.String[][] getPendingQuantity(java.lang.String odrNo){
		java.lang.String[][] res=null;
		java.lang.String[][] jobs=null;
		try{
			res=dbi.cmdSelect(
				"odrDesc, prdMaster", 
				"prdMaster.prdName, odrDesc.quantity", 
				"odrDesc.odrNo='"+odrNo+"' and odrDesc.prdNo=prdMaster.prdId"
			);
			java.lang.String[][] tmp=dbi.cmdSelect("odrMaster", "odrStatus", "odrNo='"+odrNo+"'");
			if(tmp[0][0].compareTo("pending")==0){
				return res;
			}
			if(tmp[0][0].compareTo("complete")==0){
				for (int i = 0; i<res.length; i++){
					res[i][1]=0+"";
				}
				return res;
			}
			jobs=dbi.cmdSelect(
				"jbMaster, prdMaster", 
				"prdMaster.prdName, jbMaster.quantity", 
				"jbMaster.odrNo='"+odrNo+"' and jbMaster.prdNo=prdMaster.prdId"
			);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		for (int i = 0; i<jobs.length; i++){
			for (int j=0; j<res.length; j++){
				if(jobs[i][0].compareTo(res[j][0])==0){
					res[j][1]=Integer.parseInt(res[j][1])-Integer.parseInt(jobs[i][1])+"";
				}
			}
		}
		return res;
	}
	
	public java.lang.String[][] getAllotedQuantity(java.lang.String odrNo){
		java.lang.String[][] res=null;
		java.lang.String[][] jobs=null;
		try{
			res=dbi.cmdSelect(
				"odrDesc, prdMaster", 
				"prdMaster.prdName, odrDesc.quantity", 
				"odrDesc.odrNo='"+odrNo+"' and odrDesc.prdNo=prdMaster.prdId"
			);
			java.lang.String[][] tmp=dbi.cmdSelect("odrMaster", "odrStatus", "odrNo='"+odrNo+"'");
			if(tmp[0][0].compareTo("complete")==0){
				return res;
			}
			for (int i = 0; i<res.length; i++){
				res[i][1]=0+"";
			}
			if(tmp[0][0].compareTo("pending")==0){
				return res;
			}
			jobs=dbi.cmdSelect(
				"jbMaster, prdMaster", 
				"prdMaster.prdName, jbMaster.quantity", 
				"jbMaster.odrNo='"+odrNo+"' and jbMaster.prdNo=prdMaster.prdId"
			);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		for (int i = 0; i<jobs.length; i++){
			for (int j=0; j<res.length; j++){
				if(jobs[i][0].compareTo(res[j][0])==0){
					res[j][1]=Integer.parseInt(res[j][1])+Integer.parseInt(jobs[i][1])+"";
				}
			}
		}
		return res;
	}
	
	public java.lang.String[][] getFinishedQuantity(java.lang.String odrNo){
		java.lang.String[][] res=null;
		java.lang.String[][] jobs=null;
		try{
			res=dbi.cmdSelect(
				"odrDesc, prdMaster", 
				"prdMaster.prdName, odrDesc.quantity", 
				"odrDesc.odrNo='"+odrNo+"' and odrDesc.prdNo=prdMaster.prdId"
			);
			java.lang.String[][] tmp=dbi.cmdSelect("odrMaster", "odrStatus", "odrNo='"+odrNo+"'");
			if(tmp[0][0].compareTo("complete")==0){
				return res;
			}
			for (int i = 0; i<res.length; i++){
				res[i][1]=0+"";
			}
			if(tmp[0][0].compareTo("pending")==0){
				return res;
			}
			jobs=dbi.cmdSelect(
				"jbMaster, prdMaster", 
				"prdMaster.prdName, jbMaster.quantity", 
				"jbMaster.odrNo='"+odrNo+"' and jbMaster.jbStatus='finished' and jbMaster.prdNo=prdMaster.prdId"
			);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		for (int i = 0; i<jobs.length; i++){
			for (int j=0; j<res.length; j++){
				if(jobs[i][0].compareTo(res[j][0])==0){
					res[j][1]=Integer.parseInt(res[j][1])+Integer.parseInt(jobs[i][1])+"";
				}
			}
		}
		return res;
	}
	
	public String[][] getOdrDetailForOdrMaster(String odrNo){
    	java.lang.String[][] tmpQty=getQuantity(odrNo);
    	java.lang.String[][] tmpFin=getFinishedQuantity(odrNo);
    	java.lang.String[][] tmpAlt=getAllotedQuantity(odrNo);
    	String[][] res=new java.lang.String[tmpQty.length][4];
    	for (int i = 0; i<res.length; i++){
    		res[i]=new java.lang.String[4];
    		res[i][0]=new java.lang.String(tmpQty[i][0]);
    		res[i][1]=new java.lang.String(tmpQty[i][1]);
    		for (int j = 0; j<tmpAlt.length; j++){
    			if(tmpQty[i][0].compareTo(tmpAlt[j][0])==0){
    				res[i][2]=new java.lang.String(tmpAlt[j][1]);
    			}
    		}
    		for (int j = 0; j<tmpAlt.length; j++){
    			if(tmpQty[i][0].compareTo(tmpFin[j][0])==0){
    				res[i][3]=new java.lang.String(tmpFin[j][1]);
    			}
    		}
    	}
		return res;
	}
	
	public void setOrderStatus(java.lang.String odrNo, java.lang.String status){
		if(status.compareTo("pending")!=0 && status.compareTo("incomplete")!=0 && status.compareTo("complete")!=0){
			System.out.println ("invalid status: "+status);
			return;
		}
		try{
			dbi.cmdUpdate("odrMaster", "odrStatus='"+status+"'", "odrNo='"+odrNo+"'");
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
	}
	
	
    
    //* Variables declaration - 
	private inventorycontroller.function.DbInterface dbi;
    //* End of variables declaration
}