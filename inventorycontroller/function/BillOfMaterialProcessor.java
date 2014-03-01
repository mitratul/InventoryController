 /*
 * BillOfMaterialProcessor.java
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
public class BillOfMaterialProcessor {
	
	public static final String BILL_OF_MATERIAL_ID="BOM";
	
	public BillOfMaterialProcessor(inventorycontroller.function.DbInterface dbi){
		this.dbi=dbi;
	}
	    
    public java.lang.String getNewBomNo() {
    	java.lang.String[][] bomNos=null;
    	
    	try{
	    	bomNos=dbi.cmdSelect(
	    		"bomMaster", "bomNo", "1=1 order by bomNo");
    	}
    	catch(java.sql.SQLException ex){
    		System.out.println (ex);
    		return "";
    	}
    	
    	int bomNo=0;
    	String currentDate=inventorycontroller.function.DateProcessor
    	 .getCurrentDate(dbi);
    	String yr=inventorycontroller.util.DateUtil.getFinancialYear(currentDate);
    	
    	for (int i = 0; i<bomNos.length; i++){
    		if(
    			bomNos[i][0].startsWith(BILL_OF_MATERIAL_ID+"/") && 
    			bomNos[i][0].endsWith("/"+yr)
    		){
    			bomNo++;
    		}
    	}
    	java.lang.String fullBomNo=new java.lang.String(BILL_OF_MATERIAL_ID+"/");
    	for (int i = 7; i>=0; i--){	//if odrNo>10^7 then length will incr
	    	if((double)bomNo<java.lang.Math.pow((double)10, (double)i)){
	    		fullBomNo=fullBomNo+"0";
	    	}
	    	else{
	    		fullBomNo=fullBomNo+bomNo;
	    		break;
	    	}
    	}
    	fullBomNo=fullBomNo+"/"+yr;
    	
    	return fullBomNo;
    }
    
    public Object[][] getBomForBomReport(){
    	Object[][] res=null;
    	String[][] tmp=null;
    	try{
    		tmp=dbi.cmdSelect(
    			"bomMaster", 
    			
    			"bomMaster.bomNo, "+
    			"bomMaster.rqnDate, "+
    			"bomMaster.jbNo", 
    			
    			"1=1"
    		);
    	}
    	catch(Exception ex){
    		System.out.println (ex);
    	}
    	res=new Object[tmp.length][4];
    	for (int i = 0; i<res.length; i++){
    		res[i][0]=new Boolean(false);
    		for (int j = 0; j<3; j++){
    			res[i][j+1]=tmp[i][j];
    		}
    	}
    	inventorycontroller.util.DateUtil du=null;
    	for (int i = 0; i<res.length; i++){
    		res[i][2]=du.getDisplayFormat(res[i][2].toString());
    	}
    	return res;
    }
    
    public String[][] getBomForShowBom(){
    	String[][] bomList=null;
    	try{
    		bomList=dbi.cmdSelect(
    			"bomMaster, jbMaster, prdMaster", 
    			
    			"bomMaster.bomNo, "+
    			"bomMaster.rqnDate, "+
    			"bomMaster.bomStatus, "+
    			"jbMaster.jbNo, "+
    			"prdMaster.prdName, "+
    			"jbMaster.quantity, "+
    			"bomMaster.bomRemark", 
    			
    			"jbMaster.jbNo=bomMaster.jbNo and "+
    			"prdMaster.prdId=jbMaster.prdNo"
    		);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return new String[0][0];
    	}
    	inventorycontroller.util.DateUtil du=null;
    	for (int i = 0; i<bomList.length; i++){
    		bomList[i][1]=du.getDisplayFormat(bomList[i][1]);
    	}
    	return bomList;
    }
    
    public String[][] getBomDetForShowBom(String bomNo){
    	String[][] res=null;
    	try{
    		res=dbi.cmdSelect(
    			"bomDesc, matMaster", 
    			
    			"bomDesc.matNo, "+
    			"matMaster.matName, "+
    			"bomDesc.matReqd, "+
    			"bomDesc.purRqtn, "+
    			"matMaster.matUnit", 
    			
    			"bomDesc.bomNo='"+bomNo+"' and "+
    			"matMaster.matId=bomDesc.matNo"
    		);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return new String[0][0];
    	}
    	return res;
    }
    
    /** called from PurchaseOrserProcessor.preparePO(). */
    public java.lang.String[][] getBomQty(java.lang.String bomNo){
    	java.lang.String[][] res=null;
		try{
			res=dbi.cmdSelect(
				"bomDesc, matMaster", 
				"MatMaster.matName, bomDesc.purReqd", 
				"bomDesc.bomNo='"+bomNo+"' and bomDesc.matNo=matMaster.matId"
			);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		return res;
    }
    
	/*private java.lang.String[][] getBOMs(java.lang.String whereClause) {
		java.lang.String[][] res=null;
		try{
			res=dbi.cmdSelect("bomMaster", "*", whereClause);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		return res;
	}*/
	
	public java.lang.Object[][] prepareBOM(java.lang.String[][] components, int qty){
		inventorycontroller.function.MaterialProcessor mp=
    	 new inventorycontroller.function.MaterialProcessor(dbi);
    	inventorycontroller.function.PurchaseOrderProcessor pop=
    	 new inventorycontroller.function.PurchaseOrderProcessor(dbi);
    	 
    	java.lang.String[][] tmp2=components;
    	
    	java.lang.Object[][] res=new java.lang.Object[tmp2.length][11];
    	// res row format: slNo, select, matID, matName, reqdQty, stockQty,
    	//                 toDeliverQty, toOrderQty, allotedQty, purReqdQty, remarks
    	
    	for (int i = 0; i<res.length; i++){
    		res[i][0]=(i+1)+"";
    		res[i][1]=new Boolean(true);
    		res[i][2]=mp.getMatId(tmp2[i][0]);
    		res[i][3]=tmp2[i][0];
    		res[i][4]=(java.lang.Double.parseDouble(tmp2[i][1])*qty)+"";
    		
    		String matId=mp.getMatId(tmp2[i][0]);
    		
    		double mst=java.lang.Double.parseDouble(mp.getStock(matId));
    		res[i][5]=mst+"";
    		
    		double dlvr=pop.getPendingQty(matId);
    		res[i][6]=dlvr+"";
    		
    		double odr=this.getPendingQty(matId);
    		res[i][7]=odr+"";
    		
    		double alc=mp.getAllocatedQuantity(matId);
    		res[i][8]=alc+"";
    		
    		double mrq=java.lang.Double.parseDouble(res[i][4].toString())-mst-dlvr-odr+alc;
    		if(mrq<(double)0){
    			mrq=0;
    		}
    		res[i][9]=mrq+"";
    		
    		res[i][10]="";
    	}
    	return res;
	}
	
	/** called from JInternalFrameBillOfMaterial.bomTableEdited(). */
	public Object[] calculateBOM(Object[] rowData, int col){
		Object[] res=new Object[rowData.length];
		for (int i = 0; i<10; i++){
			res[i]=rowData[i];
		}
		double reqd=0;
		double purchase=0;
		try{
			reqd=Double.parseDouble(res[4].toString());
			purchase=Double.parseDouble(res[9].toString());
		}
		catch(NumberFormatException ex){
			res[col]="0";
			res[9]="0";
			return res;
		}
		
		if(col==9){
			return res;
		}
		
		double mst=Double.parseDouble(res[5].toString());
		double dlvr=Double.parseDouble(res[6].toString());
		double odr=Double.parseDouble(res[7].toString());
		double alc=Double.parseDouble(res[8].toString());
		
		double mrq=Double.parseDouble(res[4].toString())-mst-dlvr-odr+alc;
		if(mrq<0){
			mrq=0;
		}
		res[9]=mrq+"";
		
		
		return res;
	}
	
	/** called from prepareBOM() */
	public double getPendingQty(String matId){
		String[][] tmp=null;
		try{
			tmp=dbi.cmdSelect(
				"bomMaster, bomDesc",
				
				"bomDesc.purReqd",
				
				"bomMaster.bomNo=bomDesc.bomNo and "+
				"bomMaster.bomStatus='pending' and "+
				"bomDesc.matNo='"+matId+"'"
			);
		}
		catch(Exception ex){
			System.out.println (ex);
		}
		double res=0;
		for(String[] tmp1: tmp){
			res+=Double.parseDouble(tmp1[0]);
		}
		return res;
	}
	
	public java.lang.Object[][] getBomForPoMaster(){
		java.lang.String[][] tmp1=null;
		try{
			tmp1=dbi.cmdSelect(
				"odrMaster, jbMaster, bomMaster", 
				"odrMaster.custName, jbMaster.jbNo, bomMaster.rqnDate, bomMaster.bomNo", 
				"odrMaster.odrNo=jbMaster.odrNo and "+
				"jbMaster.jbNo=bomMaster.jbNo and "+
				"bomMaster.bomStatus='pending'"
			);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		for (int i = 0; i<tmp1.length; i++){
			tmp1[i][2]=inventorycontroller.util.DateUtil.getDisplayFormat(tmp1[i][2]);
		}
    	
    	java.lang.Object[][]res=new java.lang.Object[tmp1.length][4];
    	
    	for (int i = 0; i<res.length; i++){
    		
    		for (int j = 0; j<tmp1[i].length; j++){
    			res[i][j]=new java.lang.String(tmp1[i][j]);
    		}
    	}
    	return res;
	}
	
	public java.lang.Object[][] getBomDescForPoMaster(java.lang.String bomNo){
		java.lang.Object[][] res=null;
		java.lang.String[][] tmp1=null;
		try{
			tmp1=dbi.cmdSelect(
				"bomDesc, matMaster", 
				"matMaster.matName, bomDesc.matReqd, bomDesc.purReqd, bomDesc.remark", 
				"bomDesc.bomNo='"+bomNo+"' and bomDesc.matNo=matMaster.matId");
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		if(tmp1==null){
			return null;
		}
		res=new java.lang.Object[tmp1.length][5];
		for (int i = 0; i<tmp1.length; i++){
			res[i]=new java.lang.Object[5];
			res[i][0]=new java.lang.Boolean(false);
			res[i][1]=tmp1[i][0];
			res[i][2]=tmp1[i][1];
			res[i][3]=tmp1[i][2];
			res[i][4]=tmp1[i][3];
		}
		return res;
	}
	
	public String[][] getPurchaseReq(String bomNo){
		java.lang.String[][] res=null;
		try{
			res=dbi.cmdSelect(
				"bomDesc, matMaster", 
				"matMaster.matName, bomDesc.purReqd", 
				"bomDesc.bomNo='"+bomNo+"' and bomDesc.matNo=matMaster.matId");
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		return res;
	}
	
	public double getRequiredQty(String bomNo, String matNo){
		java.lang.String[][] tmp=null;
		try{
			tmp=dbi.cmdSelect(
				"bomDesc", 
				"bomDesc.matReqd", 
				"bomDesc.bomNo='"+bomNo+"' and bomDesc.matNo='"+matNo+"'");
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		double res=Double.parseDouble(tmp[0][0]);
		
		return res;
	}
	
	public void resetPurchaseReq(String bomNo, String[] matNo){
		for (int i = 0; i<matNo.length; i++){
	    	try{
				dbi.cmdUpdate(
					"bomDesc", 
					"purReqd='0'", 
					"bomNo='"+bomNo+"' and matNo='"+matNo[i]+"'");
			}
			catch(java.lang.Exception ex){
				System.out.println (ex);
			}
		}
	}
	
	public boolean isComplete(String bomNo){
		java.lang.String[][] tmp1=null;
		try{
			tmp1=dbi.cmdSelect(
				"bomDesc", 
				"bomDesc.purReqd", 
				"bomDesc.bomNo='"+bomNo+"' and  bomDesc.purReqd not like '0%'");
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		for (int i = 0; i<tmp1.length; i++)
			System.out.println (tmp1[i][0]);
		if(tmp1.length>0){
			return false;
		}
		else{
			return true;
		}
	}
	
	public void setBOMStatus(java.lang.String bomNo, java.lang.String status){
		if(status.compareTo("pending")!=0 && status.compareTo("complete")!=0){
			System.out.println ("invalid status: "+status);
			return;
		}
		try{
			dbi.cmdUpdate("bomMaster", "bomStatus='"+status+"'", "bomNo='"+bomNo+"'");
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
	}
	
	
	public static void main(String[] args){
		try{
			inventorycontroller.function.DbInterface dbi=
			 new inventorycontroller.function.DbInterface(
			 	"./db/db.mdb", "Microsoft Access Driver (*.mdb)"
			 );
			System.out.println (new BillOfMaterialProcessor(dbi).isComplete("bom/002/07-08"));
			dbi.close();
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		
	}
	
    
    //* Variables declaration - 
	private inventorycontroller.function.DbInterface dbi;
    //* End of variables declaration
}