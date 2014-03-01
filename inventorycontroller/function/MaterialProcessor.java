 /*
 * MaterialProcessor.java
 *
 * Created on July 21, 2007, 11:20 HRS
 * Last Modified on August 23, 2007, 12:20 HRS
 *
 */

package inventorycontroller.function;

/**
 *
 * @author  brinto
 */
public class MaterialProcessor {
	
	public static final String MATERIAL_ID="MAT";
	
	public MaterialProcessor(inventorycontroller.function.DbInterface dbi){
		this.dbi=dbi;
	}
	    
    public java.lang.String getNewMatNo() {
    	java.lang.String[][] matNos=null;
    	
    	try{
	    	matNos=dbi.cmdSelect(
	    		"matMaster", "matId", "1=1 order by matId");
    	}
    	catch(java.sql.SQLException ex){
    		System.out.println (ex);
    		return "";
    	}
    	
    	int matNo=0;
    	String currentDate=inventorycontroller.function.DateProcessor
    	 .getCurrentDate(dbi);
    	String yr=inventorycontroller.util.DateUtil.getFinancialYear(currentDate);
    	
    	for (int i = 0; i<matNos.length; i++){
    		if(
    			matNos[i][0].startsWith(MATERIAL_ID+"/") &&
    			matNos[i][0].endsWith("/"+yr)
    		){
    			matNo++;
    		}
    	}
    	java.lang.String fullMatNo=new java.lang.String(MATERIAL_ID+"/");
    	for (int i = 7; i>=0; i--){	//if odrNo>10^7 then length will incr
	    	if((double)matNo<java.lang.Math.pow((double)10, (double)i)){
	    		fullMatNo=fullMatNo+"0";
	    	}
	    	else{
	    		fullMatNo=fullMatNo+matNo;
	    		break;
	    	}
    	}
    	fullMatNo=fullMatNo+"/"+yr;
    	
    	return fullMatNo;
    }
    
	public java.lang.String[][] getMaterials(java.lang.String whereClause) {
		java.lang.String[][] res=null;
		try{
			res=dbi.cmdSelect(
				"matMaster", 
				"matName, matId, recDate, matStockGeneral, matStockExcisable, matUnit, matRemark", 
				whereClause
			);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		for (int i = 0; i<res.length; i++){
			res[i][2]=inventorycontroller.util.DateUtil.getDisplayFormat(res[i][2]);
		}
		return res;
	}
	
	/** returns name, id for purchase order material selection. 
	 *  JDialogLedgerReport.resetMatList()*/
	public java.lang.Object[][] getMatNameIds(java.lang.String condition){
		java.lang.Object[][] res=null;
		java.lang.String[][] tmp=null;
		try{
			tmp=dbi.cmdSelect("matMaster", "matName, matId", condition);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		res=new java.lang.Object[tmp.length][3];
		for (int i = 0; i<res.length; i++){
			res[i]=new java.lang.Object[3];
			res[i][0]=new Boolean(false);
			res[i][1]=tmp[i][0];
			res[i][2]=tmp[i][1];
		}
		return res;
	}
	
	/** returns name, id, unit for purchase order material selection. 
	 *  LedgerReportProcessor.printLedgerHeader() for name & unit*/
	public java.lang.Object[][] getMatNamesForPo(java.lang.String condition){
		java.lang.Object[][] res=null;
		java.lang.String[][] tmp=null;
		try{
			tmp=dbi.cmdSelect("matMaster", "matName, matId, matUnit", condition);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		res=new java.lang.Object[tmp.length][4];
		for (int i = 0; i<res.length; i++){
			res[i][0]=new Boolean(false);
			res[i][1]=tmp[i][0];
			res[i][2]=tmp[i][1];
			res[i][3]=tmp[i][2];
		}
		return res;
	}
	
	/** returns name for product master. */
	public java.lang.String[] getMatNames(java.lang.String condition){
		java.lang.String[] res=null;
		java.lang.String[][] tmp=null;
		try{
			tmp=dbi.cmdSelect("matMaster", "matName", condition);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		res=new java.lang.String[tmp.length];
		for (int i = 0; i<res.length; i++){
			res[i]=tmp[i][0];
		}
		return res;
	}
	
	public java.lang.String[] getMatIds(java.lang.String condition){
		java.lang.String[] res=null;
		java.lang.String[][] tmp=null;
		try{
			tmp=dbi.cmdSelect("matMaster", "matId", condition);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		res=new java.lang.String[tmp.length];
		for (int i = 0; i<res.length; i++){
			res[i]=tmp[i][0];
		}
		return res;
	}
	
	public String getMatId(String matName){
		String[] res=getMatIds("matMaster.matName='"+matName+"'");
		return res[0];
	}
	
	
	/** called from BOMProcessor.prepareBOM(), 
	 * GoodsIssueReturnProcessor.prepareMaterialStatus() */
	public java.lang.String getStock(java.lang.String matNo){
		java.lang.String[][] tmp=null;
		try{
			tmp=dbi.cmdSelect(
				"matMaster", 
				"matStockGeneral, matStockExcisable", 
				"matId='"+matNo+"'"
			);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		return (Double.parseDouble(tmp[0][0])+Double.parseDouble(tmp[0][1]))+"";
	}
	
	/** called from JInternalFrameGoodsIssue.insertTrn() 
	 *         StockStatementReportProcessor.printStockData() */
	public String[] getAmounts(String matNo){
		String[] res=null;
		String[][] tmp=null;
		try{
			tmp=dbi.cmdSelect(
				"matMaster",
				
				"matMaster.matStockGeneral, "+
				"matMaster.matStockExcisable, "+
				"matMaster.matVATGeneral, "+
				"matMaster.matVATExcisable, "+
				"matMaster.matED, "+
				"matMaster.matAmtGeneral, "+
				"matMaster.matAmtExcisable", 
				
				"matMaster.matId='"+matNo+"'"
			);
		}
		catch(Exception ex){
			System.out.println (ex);
		}
		res=new String[7];
		for (int i = 0; i<7; i++){
			res[i]=new String(tmp[0][i]);
		}
		return res;
	}
	
	/** update material stock with GRN entry. */
	public void addMaterialStock(String matId, double qty, double ed, 
	 double vat, double amt, String date){
		String[][] tmp=null;
		try{
			tmp=dbi.cmdSelect(
				"matMaster", 
				"*",
				"matId='"+matId+"'");
		}
		catch(Exception ex){
			System.out.println (ex);
		}
		double matQty=qty;
		double matAmtED=ed;
		double matAmtVAT=vat;
		double matAmt=amt;
		String setValues=new String("");
		if(matAmtED>0){
			matQty+=Double.parseDouble(tmp[0][4]);
			matAmt+=Double.parseDouble(tmp[0][9]);
			matAmtVAT+=Double.parseDouble(tmp[0][6]);	
			matAmtED+=Double.parseDouble(tmp[0][7]);
			setValues=
				"matMaster.matStockExcisable='"+matQty+
				"', matMaster.matAmtExcisable='"+matAmt+
				"', matMaster.matVATExcisable='"+matAmtVAT+
				"', matMaster.matED='"+matAmtED;
		}
		else {
			matQty+=Double.parseDouble(tmp[0][3]);
			matAmt+=Double.parseDouble(tmp[0][8]);
			matAmtVAT+=Double.parseDouble(tmp[0][5]);
			setValues=
				"matMaster.matStockGeneral='"+matQty+
				"', matMaster.matAmtGeneral='"+matAmt+
				"', matMaster.matVATGeneral='"+matAmtVAT;
		}
		setValues=setValues+"', matMaster.recDate='"+date+"'";
		try{
			dbi.cmdUpdate("matMaster", setValues, "matMaster.matId='"+matId+"'");
		}
		catch(Exception ex){
			System.out.println (ex);
		}
		
	}
	
	/** called from bomProcessor.prepareBOM(), 
	 * GoodsIssueReturnProcessor.prepareMaterialStatus()
	 */
	public double getAllocatedQuantity(String matNo){
		double res=0;
    	String[][] tmp=null;
    	try{
    		tmp=dbi.cmdSelect(
    			"bomMaster, bomDesc, jbMaster", 
    			
    			"bomDesc.matReqd", 
    			
    			"jbMaster.jbStatus='progressing' and "+
    			"bomMaster.jbNo=jbMaster.jbNo and "+
    			"bomMaster.bomNo=bomDesc.bomNo and "+
    			"bomDesc.matNo='"+matNo+"'"
    		);
    	}
    	catch(Exception ex){
    		System.out.println (ex);
    	}
    	for (int i = 0; i<tmp.length; i++){
    		res+=Double.parseDouble(tmp[i][0]);
    	}
    	res-=new inventorycontroller.function.GoodsIssueReturnProcessor(dbi)
    	 .getConsumedQuantity(matNo);
    	
    	return res;
	}
	
	/**called from GoodsIssueReturnProcessor.calculateQtyDivision(), 
	 *                                      .prepareRequisition().
	 */
	public double getExcisableIssueQuantity(String matNo, String totalQty) {
		double res=0;
		double qty=Double.parseDouble(totalQty);
		double exQty=0;
		String[][] tmp=null;
		try{
			tmp=dbi.cmdSelect(
				"matMaster",
				"matMaster.matStockExcisable",
				"matMaster.matId='"+matNo+"'"
			);
		}
		catch(Exception ex){
			System.out.println (ex);
		}
		exQty=Double.parseDouble(tmp[0][0]);
		
		res=exQty>qty? qty: exQty;
		
		return res;
	}
	
	/**called from GoodsIssueReturnProcessor.calculateQtyDivision() */
	public double getGeneralReturnQuantity(String matNo, String totalQty, String jobNo){
		double res=0;
		double qty=Double.parseDouble(totalQty);
		double genQty=0;
		String[][] tmp=null;
		try{
			tmp=dbi.cmdSelect(
    			"gdsJbTrnMaster, gdsJbTrnDesc", 
    			
    			"gdsJbTrnDesc.trnNo, gdsJbTrnDesc.qtyGeneral", 
    			
    			"gdsJbTrnMaster.jbNo='"+jobNo+"' and "+
    			"gdsJbTrnDesc.trnNo=gdsJbTrnMaster.trnNo and "+
    			"gdsJbTrnDesc.matNo='"+matNo+"'"
    		);
		}
		catch(Exception ex){
			System.out.println (ex);
		}
		
		for (int i = 0; i<tmp.length; i++){
    		genQty+=Double.parseDouble(tmp[i][1]);
		}
		res=genQty>qty? qty: genQty;
		
		return res;
	}
	
	
	public static void main(String[] args){
		java.lang.String[][] res=null;
		try{
			inventorycontroller.function.DbInterface dbi=
			 new inventorycontroller.function.DbInterface(
			 	"./db/db.mdb", "Microsoft Access Driver (*.mdb)"
			 );
			res=new MaterialProcessor(dbi).getMaterials("1=1");
			dbi.close();
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		if(res==null){
			return;
		}
		for (int i = 0; i<res.length; i++){
			for (int j = 0; j<res[i].length; j++){
				System.out.println (res[i][j]);
			}
			System.out.println ("");
		}
	}
	
    
    //* Variables declaration - 
	private inventorycontroller.function.DbInterface dbi;
    //* End of variables declaration
}