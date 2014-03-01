 /*
 * GoodsIssueReturnProcessor.java
 *
 * Created on August 29, 2007, 09:45 HRS
 * Last Modified on August 29, 2007, 09:45 HRS
 *
 */

package inventorycontroller.function;

/**
 *
 * @author  brinto
 */
public class GoodsIssueReturnProcessor {
	
	public static final int GOODS_REQUISITION=0;
	public static final int GOODS_RETURN=1;
	public static final int GOODS_EXCESS_REQUISITION=2;
	
	public static final String GOODS_REQUISITION_ID="RQN";
	public static final String GOODS_RETURN_ID="RET";
	public static final String GOODS_EXCESS_REQUISITION_ID="ERN";
	
	public GoodsIssueReturnProcessor(inventorycontroller.function.DbInterface dbi){
		this.dbi=dbi;
	}
	    
    public java.lang.String getNewTrnNo(int transType, String rawDate) {
    	String type=GOODS_REQUISITION_ID;
    	if(transType==GOODS_RETURN){
    		type=GOODS_RETURN_ID;
    	}
    	else if(transType==GOODS_EXCESS_REQUISITION){
    		type=GOODS_EXCESS_REQUISITION_ID;
    	}
    	
    	java.lang.String[][] trnNos=null;
    	
    	java.lang.String fullTrnNo=new java.lang.String(type+"/"+rawDate.substring(4)+"/");
    	try{
	    	trnNos=dbi.cmdSelect(
	    		"gdsJbTrnMaster", "trnNo", "trnNo like '"+fullTrnNo+"%' order by trnNo");
    	}
    	catch(java.sql.SQLException ex){
    		System.out.println (ex);
    		return "";
    	}
    	
    	int trnNo=1;
    	String currentDate=rawDate;
    	String yr=inventorycontroller.util.DateUtil.getFinancialYear(currentDate);
    	
    	for (int i = 0; i<trnNos.length; i++){
    		if(
    			trnNos[i][0].startsWith(type+"/") && 
    			trnNos[i][0].endsWith("/"+yr)
    		){
    			trnNo++;
    		}
    	}
    	for (int i = 3; i>=0; i--){	//if odrNo>10^3 then length will incr
	    	if((double)trnNo<java.lang.Math.pow((double)10, (double)i)){
	    		fullTrnNo=fullTrnNo+"0";
	    	}
	    	else{
	    		fullTrnNo=fullTrnNo+trnNo;
	    		break;
	    	}
    	}
    	fullTrnNo=fullTrnNo+"/"+yr;
    	
    	return fullTrnNo;
    }
    
    public String[][] prepareMaterialStatus(String bomNo){
    	String[][] res=null;
    	String[][] tmp=null;
    	try{
    		tmp=dbi.cmdSelect(
    			"bomDesc",
    			"bomDesc.matNo",
    			"bomNo='"+bomNo+"'"
    		);
    	}
    	catch(Exception ex){
    		System.out.println (ex);
    	}
    	res=new String[tmp.length][5];
		inventorycontroller.function.MaterialProcessor mp=
    	 new inventorycontroller.function.MaterialProcessor(dbi);
    	inventorycontroller.function.PurchaseOrderProcessor pop=
    	 new inventorycontroller.function.PurchaseOrderProcessor(dbi);
		inventorycontroller.function.BillOfMaterialProcessor bomp=
    	 new inventorycontroller.function.BillOfMaterialProcessor(dbi);
    	for (int i = 0; i<res.length; i++){
    		String[] tmp1=mp.getMatNames("matMaster.matId='"+tmp[i][0]+"'");
    		res[i][0]=tmp1[0];
    		res[i][1]=mp.getStock(tmp[i][0]);
    		res[i][2]=bomp.getPendingQty(tmp[i][0])+"";
    		res[i][3]=pop.getPendingQty(tmp[i][0])+"";
    		res[i][4]=mp.getAllocatedQuantity(tmp[i][0])+"";
    	}
    	return res;
    }
    
    public String[][] prepareRequisition(String bomNo){
    	String[][] res=null;
    	String[][] tmp=null;
    	String[][] tmp1=null;
    	String jobNo="";
    	try{
    		tmp1=dbi.cmdSelect(
    			"bomMaster",
    			"bomMaster.jbNo",
    			"bomMaster.bomNo='"+bomNo+"'"
    		);
    		
    		tmp=dbi.cmdSelect(
    			"bomDesc",
    			"bomDesc.matNo",
    			"bomNo='"+bomNo+"'"
    		);
    	}
    	catch(Exception ex){
    		System.out.println (ex);
    	}
    	jobNo=tmp1[0][0];
    	
    	res=new String[tmp.length][8];
		inventorycontroller.function.MaterialProcessor mp=
    	 new inventorycontroller.function.MaterialProcessor(dbi);
    	inventorycontroller.function.PurchaseOrderProcessor pop=
    	 new inventorycontroller.function.PurchaseOrderProcessor(dbi);
		inventorycontroller.function.BillOfMaterialProcessor bomp=
    	 new inventorycontroller.function.BillOfMaterialProcessor(dbi);
    	for (int i = 0; i<res.length; i++){
    		String[] tmp2=mp.getMatNames("matMaster.matId='"+tmp[i][0]+"'");
    		res[i][0]=(i+1)+""; // slNo
    		res[i][1]=tmp[i][0]; // matId
    		res[i][2]=tmp2[0]; // matName
    		res[i][3]=bomp.getRequiredQty(bomNo, tmp[i][0])+""; //reqdQty
    		res[i][4]=(Double.parseDouble(res[i][3])-
    		 this.getConsumedQuantity(tmp[i][0], jobNo))+""; //consumptionPending
    		double stk=Double.parseDouble(mp.getStock(tmp[i][0]));
    		res[i][5]=(Double.parseDouble(res[i][4])>stk? stk: res[i][4])+""; //issue
    		res[i][6]=mp.getExcisableIssueQuantity(tmp[i][0], res[i][5])+""; //issueExcisable
    		res[i][7]=(Double.parseDouble(res[i][5])-
    		 Double.parseDouble(res[i][6]))+""; //issueGeneral
    	}
    	return res;
    }
    
    public Object[][] prepareRqnForReportDialog(String type){
    	String[][] tmp=null;
    	if(type.compareTo(this.GOODS_RETURN_ID)==0){
	    	try{
	    		tmp=dbi.cmdSelect(
	    			"gdsJbTrnMaster", 
	    			"gdsJbTrnMaster.trnNo, gdsJbTrnMaster.trnDate, gdsJbTrnMaster.jbNo", 
	    			"gdsJbTrnMaster.trnNo like '"+this.GOODS_RETURN_ID+"%' order by gdsJbTrnMaster.trnNo"
	    		);
	    	}
	    	catch(Exception ex){
	    		ex.printStackTrace();
	    	}
    	}
    	else {
    		try{
	    		tmp=dbi.cmdSelect(
	    			"gdsJbTrnMaster", 
	    			"gdsJbTrnMaster.trnNo, gdsJbTrnMaster.trnDate, gdsJbTrnMaster.jbNo", 
	    			"gdsJbTrnMaster.trnNo not like '"+this.GOODS_RETURN_ID+"%' order by gdsJbTrnMaster.trnNo"
	    		);
	    	}
	    	catch(Exception ex){
	    		ex.printStackTrace();
	    	}
    	}
    	Object[][] res=new Object[tmp.length][4];
    	for (int i = 0; i<res.length; i++){
    		res[i][0]=new Boolean(false);
    		res[i][1]=tmp[i][0];
    		res[i][2]=inventorycontroller.util.DateUtil.getDisplayFormat(tmp[i][1]);
    		res[i][3]=tmp[i][2];
    	}
    	return res;
    }
    
    public String[][] prepareExcessRequisition(String jobNo){
    	String[][] res=null;
    	String[][] tmp=null;
    	String[][] tmp1=null;
    	String[][] tmp3=null;
    	String bomNo="";
    	String jobStatus="";
    	try{
    		tmp3=dbi.cmdSelect(
    			"jbMaster", 
    			"jbMaster.jbStatus", 
    			"jbMaster.jbNo='"+jobNo+"'"
    		);
    		jobStatus=tmp3[0][0];
    		
    		tmp1=dbi.cmdSelect(
    			"bomMaster",
    			"bomMaster.bomNo",
    			"bomMaster.jbNo='"+jobNo+"'"
    		);
	    	bomNo=tmp1[0][0];
    		
    		tmp=dbi.cmdSelect(
    			"bomDesc",
    			"bomDesc.matNo",
    			"bomNo='"+bomNo+"'"
    		);
    	}
    	catch(Exception ex){
    		System.out.println (ex);
    	}
    	
    	res=new String[tmp.length][5];
		inventorycontroller.function.MaterialProcessor mp=
    	 new inventorycontroller.function.MaterialProcessor(dbi);
    	inventorycontroller.function.PurchaseOrderProcessor pop=
    	 new inventorycontroller.function.PurchaseOrderProcessor(dbi);
		inventorycontroller.function.BillOfMaterialProcessor bomp=
    	 new inventorycontroller.function.BillOfMaterialProcessor(dbi);
    	for (int i = 0; i<res.length; i++){
    		String[] tmp2=mp.getMatNames("matMaster.matId='"+tmp[i][0]+"'");
    		res[i][0]=(i+1)+""; // slNo
    		res[i][1]=tmp[i][0]; // matId
    		res[i][2]=tmp2[0]; // matName
    		res[i][3]=bomp.getRequiredQty(bomNo, tmp[i][0])+""; // reqdQty
    		res[i][4]=(Double.parseDouble(res[i][3])-
    		 this.getConsumedQuantity(tmp[i][0], jobNo))+""; // consumptionPending
    	}
    	int count=0;
    	for (int i = 0; i<res.length; i++){
    		if(jobStatus.compareTo("finished")==0 || Double.parseDouble(res[i][4])==0){
    			count++;
    		}
    	}
    	String[][] res1=new String[count][7];
    	for (int i = 0, j=0; i<res.length; i++){
     		if(jobStatus.compareTo("finished")==0 || Double.parseDouble(res[i][4])==0){
    			res1[j][0]=(j+1)+""; // slNo
	    		res1[j][1]=res[i][1]; // matId
	    		res1[j][2]=res[i][2]; // matName
	    		res1[j][3]=res[i][3]; // reqdQty
	    		res1[j][4]="0.0"; // issue
	    		res1[j][5]="0.0"; // issueExcisable
	    		res1[j++][6]="0.0"; // issueGeneral
    		}
    	}
    	return res1;
    }
    
    public String[][] prepareReturn(String bomNo){
    	String[][] res=null;
    	String[][] tmp=null;
    	String[][] tmp1=null;
    	String jobNo="";
    	try{
    		tmp1=dbi.cmdSelect(
    			"bomMaster",
    			"bomMaster.jbNo",
    			"bomMaster.bomNo='"+bomNo+"'"
    		);
    		
    		tmp=dbi.cmdSelect(
    			"bomDesc",
    			"bomDesc.matNo",
    			"bomNo='"+bomNo+"'"
    		);
    	}
    	catch(Exception ex){
    		System.out.println (ex);
    	}
    	jobNo=tmp1[0][0];
    	
    	res=new String[tmp.length][8];
		inventorycontroller.function.MaterialProcessor mp=
    	 new inventorycontroller.function.MaterialProcessor(dbi);
    	inventorycontroller.function.PurchaseOrderProcessor pop=
    	 new inventorycontroller.function.PurchaseOrderProcessor(dbi);
		inventorycontroller.function.BillOfMaterialProcessor bomp=
    	 new inventorycontroller.function.BillOfMaterialProcessor(dbi);
    	for (int i = 0; i<res.length; i++){
    		String[] tmp2=mp.getMatNames("matMaster.matId='"+tmp[i][0]+"'");
    		res[i][0]=(i+1)+""; // slNo
    		res[i][1]=tmp[i][0]; // matId
    		res[i][2]=tmp2[0]; // matName
    		res[i][3]=bomp.getRequiredQty(bomNo, tmp[i][0])+""; //reqdQty
    		res[i][4]=this.getConsumedQuantity(tmp[i][0], jobNo)+""; //consumed
    		res[i][5]="0"; //return
    		res[i][7]="0"; //returnGeneral
    		res[i][6]="0"; //returnExcisable
    	}
    	return res;
    }
    
    public String[] calculateQtyDivision(String jobNo, String matNo, 
     String qty, String uLimit, int trnType){
     	
    	double totalQty=0;
    	try{
    		totalQty=Double.parseDouble(qty);
    	}
    	catch(NumberFormatException ex){
    		return new String[]{"0", "0", "0"};
    	}
    	double limit=Double.parseDouble(uLimit);
    	totalQty=totalQty>limit? limit: totalQty;
    	totalQty=totalQty<0? 0: totalQty;
    	double ex=0;
    	double gen=0;
    	if(trnType==GOODS_REQUISITION || trnType==GOODS_EXCESS_REQUISITION){
    		ex=new inventorycontroller.function.MaterialProcessor(dbi)
    		 .getExcisableIssueQuantity(matNo, totalQty+"");
    		gen=totalQty-ex;
    	}
    	else if(trnType==GOODS_RETURN){
    		gen=new inventorycontroller.function.MaterialProcessor(dbi)
    		 .getGeneralReturnQuantity(matNo, totalQty+"", jobNo);
    		ex=totalQty-gen;
    	}
    	return new String[]{totalQty+"", ex+"", gen+""};
    }
    
    public double getConsumedQuantity(String matNo){
    	double res=0;
    	String[][] tmp=null;
    	try{
    		tmp=dbi.cmdSelect(
    			"gdsJbTrnMaster, gdsJbTrnDesc, jbMaster", 
    			
    			"gdsJbTrnDesc.trnNo, gdsJbTrnDesc.qtyGeneral, gdsJbTrnDesc.qtyExcisable", 
    			
    			"jbMaster.jbStatus='progressing' and "+
    			"gdsJbTrnMaster.jbNo=jbMaster.jbNo and "+
    			"gdsJbTrnDesc.trnNo=gdsJbTrnMaster.trnNo and "+
    			"gdsJbTrnDesc.matNo='"+matNo+"'"
    		);
    	}
    	catch(Exception ex){
    		System.out.println (ex);
    	}
    	for (int i = 0; i<tmp.length; i++){
    		if(tmp[i][0].startsWith(this.GOODS_RETURN_ID)){
    			res-=Double.parseDouble(tmp[i][1])+Double.parseDouble(tmp[i][2]);
    		}
    		else {
    			res+=Double.parseDouble(tmp[i][1])+Double.parseDouble(tmp[i][2]);
    		}
    	}
    	return res;
    }
    
    /** called from prepareRequisition()
     *              prepareReturn() */
	public double getConsumedQuantity(String matNo, String jobNo){
    	double res=0;
    	String[][] tmp=null;
    	try{
    		tmp=dbi.cmdSelect(
    			"gdsJbTrnMaster, gdsJbTrnDesc", 
    			
    			"gdsJbTrnDesc.trnNo, gdsJbTrnDesc.qtyGeneral, gdsJbTrnDesc.qtyExcisable ", 
    			
    			"gdsJbTrnMaster.jbNo='"+jobNo+"' and "+
    			"gdsJbTrnDesc.trnNo=gdsJbTrnMaster.trnNo and "+
    			"gdsJbTrnDesc.matNo='"+matNo+"'"
    		);
    	}
    	catch(Exception ex){
    		System.out.println (ex);
    	}
    	for (int i = 0; i<tmp.length; i++){
    		if(tmp[i][0].startsWith(this.GOODS_RETURN_ID)){
    			res-=Double.parseDouble(tmp[i][1])+Double.parseDouble(tmp[i][2]);
    		}
    		else {
    			res+=Double.parseDouble(tmp[i][1])+Double.parseDouble(tmp[i][2]);
    		}
    	}
    	return res;
    }
    
    /** called from JInternalFrameGoodsIssue.insertTrn() */
	public String[] getConsumedAmounts(String matNo, String jobNo){
		String[] res=null;
		String[][] tmp=null;
		try{
			tmp=dbi.cmdSelect(
				"gdsJbTrnDesc, gdsJbTrnMaster",
				
				"gdsJbTrnDesc.qtyGeneral, "+
				"gdsJbTrnDesc.qtyExcisable, "+
				"gdsJbTrnDesc.amtVatGeneral, "+
				"gdsJbTrnDesc.amtVatExcisable, "+
				"gdsJbTrnDesc.amtED, "+
				"gdsJbTrnDesc.amtGeneral, "+
				"gdsJbTrnDesc.amtExcisable", 
				
				"gdsJbTrnMaster.jbNo='"+jobNo+"' and "+
				"gdsJbTrnMaster.trnNo=gdsJbTrnDesc.trnNo and "+
				"gdsJbTrnDesc.matNo='"+matNo+"'"
			);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		res=new String[7];
		for (int i = 0; i<7; i++){
			res[i]="0";
			for (int j = 0; j<tmp.length; j++){
				res[i]=Double.parseDouble(res[i])+Double.parseDouble(tmp[j][i])+"";
			}
		}
		return res;
	}
    
	public static void main(String[] args){
		String s="abc";
		java.util.Vector<java.lang.String> tmp0=new java.util.Vector<java.lang.String>();
		tmp0.add(new String("abc"));
		System.out.println (tmp0.contains(s));
	}
	
    
    //* Variables declaration - 
	private inventorycontroller.function.DbInterface dbi;
    //* End of variables declaration
}