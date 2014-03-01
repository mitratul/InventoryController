 /*
 * PurchaseOrderProcessor.java
 *
 * Created on July 26, 2007, 12:45 HRS
 * Last Modified on August 08, 2007, 00:45 HRS
 *
 */

package inventorycontroller.function;

/**
 *
 * @author  brinto
 */
public class PurchaseOrderProcessor {
	
	public static final int AGAINST_BOM=0;
	public static final int LOCAL_PURCHASE=1;
	
	public static final String PO_AGAINST_BOM_ID="BM";
	public static final String PO_LOCAL_PURCHASE_ID="LP";
	
	public PurchaseOrderProcessor(inventorycontroller.function.DbInterface dbi){
		this.dbi=dbi;
	}
	    
    public java.lang.String getNewPoNo(int poType) {
    	String type=PO_AGAINST_BOM_ID;
    	if(poType==LOCAL_PURCHASE){
    		type=PO_LOCAL_PURCHASE_ID;
    	}
    	
    	java.lang.String[][] poNos=null;
    	
    	try{
	    	poNos=dbi.cmdSelect(
	    		"poMaster", "poNo", "1=1 order by poNo");
    	}
    	catch(java.sql.SQLException ex){
    		System.out.println (ex);
    		return "";
    	}
    	
    	int poNo=1;
    	String currentDate=inventorycontroller.function.DateProcessor
    	 .getCurrentDate(dbi);
    	String yr=inventorycontroller.util.DateUtil.getFinancialYear(currentDate);
    	
    	for (int i = 0; i<poNos.length; i++){
    		if(
    			poNos[i][0].startsWith(type+"/") && 
    			poNos[i][0].endsWith("/"+yr)
    		   ){
    			poNo++;
    		}
    	}
    	java.lang.String fullPoNo=new java.lang.String(type+"/");
    	for (int i = 7; i>=0; i--){	//if odrNo>10^7 then length will incr
	    	if((double)poNo<java.lang.Math.pow((double)10, (double)i)){
	    		fullPoNo=fullPoNo+"0";
	    	}
	    	else{
	    		fullPoNo=fullPoNo+poNo;
	    		break;
	    	}
    	}
    	fullPoNo=fullPoNo+"/"+yr;
    	
    	return fullPoNo;
    }
    
	private java.lang.String[][] getPOs(java.lang.String whereClause) {
		java.lang.String[][] res=null;
		try{
			res=dbi.cmdSelect("poMaster", "*", whereClause);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		return res;
	}
	
	/** called from JInternalFrameShowPO.poTypeChanged(). */
	public Object[][] getAllPO() {
		Object[][] res=null;
		try{
			res=dbi.cmdSelect(
				"poMaster, vndMaster", 
				
				"poMaster.poNo, "+
				"poMaster.poDate, "+
				"poMaster.poTotal, "+
				"vndMaster.vndCmpName, "+
				"poMaster.qtnNo, "+
				"poMaster.qtnDate", 
				
				"poMaster.vndNo=vndMaster.vndNo"
			);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		for (int i = 0; i<res.length; i++){
			res[i][1]=inventorycontroller.util.DateUtil.getDisplayFormat(res[i][1].toString());
			res[i][5]=inventorycontroller.util.DateUtil.getDisplayFormat(res[i][5].toString());
		}
		
		return res;
	}
	
	public Object[][] getPoForPoReport(){
    	Object[][] res=null;
    	String[][] tmp=null;
    	try{
    		tmp=dbi.cmdSelect(
    			"poMaster, vndMaster", 
				
				"poMaster.poNo, "+
				"poMaster.poDate, "+
				"poMaster.poStatus, "+
				"poMaster.poTotal, "+
				"vndMaster.vndCmpName", 
				
				/*"poMaster.poStatus='pending' and */"poMaster.vndNo=vndMaster.vndNo"
    		);
    	}
    	catch(Exception ex){
    		System.out.println (ex);
    	}
    	res=new Object[tmp.length][6];
    	for (int i = 0; i<res.length; i++){
    		res[i][0]=new Boolean(false);
    		for (int j = 0; j<5; j++){
    			res[i][j+1]=tmp[i][j];
    		}
    	}
    	inventorycontroller.util.DateUtil du=null;
    	for (int i = 0; i<res.length; i++){
    		res[i][2]=du.getDisplayFormat(res[i][2].toString());
    	}
    	return res;
		
	}
	
	/** called from JInternalFrameShowPO.poTypeChanged(). */
	public Object[][] getPendingPO() {
		Object[][] res=null;
		try{
			res=dbi.cmdSelect(
				"poMaster, vndMaster", 
				
				"poMaster.poNo, "+
				"poMaster.poDate, "+
				"poMaster.poTotal, "+
				"vndMaster.vndCmpName, "+
				"poMaster.qtnNo, "+
				"poMaster.qtnDate", 
				
				"poMaster.poStatus='pending' and poMaster.vndNo=vndMaster.vndNo"
			);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		for (int i = 0; i<res.length; i++){
			res[i][1]=inventorycontroller.util.DateUtil.getDisplayFormat(res[i][1].toString());
			res[i][5]=inventorycontroller.util.DateUtil.getDisplayFormat(res[i][5].toString());
		}
		
		return res;
	}
	
	/** called from JInternalFrameShowPO.poTypeChanged(). */
	public Object[][] getIncompletePO() {
		Object[][] res=null;
		try{
			res=dbi.cmdSelect(
				"poMaster, vndMaster", 
				
				"poMaster.poNo, "+
				"poMaster.poDate, "+
				"poMaster.poTotal, "+
				"vndMaster.vndCmpName, "+
				"poMaster.qtnNo, "+
				"poMaster.qtnDate", 
				
				"poMaster.poStatus='incomplete' and poMaster.vndNo=vndMaster.vndNo"
			);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		for (int i = 0; i<res.length; i++){
			res[i][1]=inventorycontroller.util.DateUtil.getDisplayFormat(res[i][1].toString());
			res[i][5]=inventorycontroller.util.DateUtil.getDisplayFormat(res[i][5].toString());
		}
		
		return res;
	}
	
	/** called from JInternalFrameShowPO.poTypeChanged(). */
	public Object[][] getCompletePO() {
		Object[][] res=null;
		try{
			res=dbi.cmdSelect(
				"poMaster, vndMaster", 
				
				"poMaster.poNo, "+
				"poMaster.poDate, "+
				"poMaster.poTotal, "+
				"vndMaster.vndCmpName, "+
				"poMaster.qtnNo, "+
				"poMaster.qtnDate", 
				
				"poMaster.poStatus='complete' and poMaster.vndNo=vndMaster.vndNo"
			);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		for (int i = 0; i<res.length; i++){
			res[i][1]=inventorycontroller.util.DateUtil.getDisplayFormat(res[i][1].toString());
			res[i][5]=inventorycontroller.util.DateUtil.getDisplayFormat(res[i][5].toString());
		}
		
		return res;
	}
	
	public java.lang.String[][] getNonCompletePO(){
		java.lang.String[][] res=null;
		try{
			/*res=dbi.cmdSelect(
				"poMaster, vndMaster", 
				"poMaster.poNo, poMaster.poDate, vndMaster.vndCmpName", 
				"(poMaster.poStatus='pending' or poMaster.poStatus='incomplete') and poMaster.vndNo=vndMaster.vndNo"
			);*/
			res=dbi.cmdSelect(
				"poMaster, vndMaster", 
				"poMaster.poNo, poMaster.poDate, vndMaster.vndCmpName", 
				"(not poMaster.poStatus='complete') and poMaster.vndNo=vndMaster.vndNo"
			);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		for (int i = 0; i<res.length; i++){
			res[i][1]=inventorycontroller.util.DateUtil.getDisplayFormat(res[i][1]);
		}
		return res;
	}
	
	/** called from BOMProcessor.prepareBOM()*/
	public double getPendingQty(String matId){
		String[][] tmp1=null;
		String[][] tmp2=null;
		try{
			tmp1=dbi.cmdSelect(
				"poMaster, poDesc",
				
				"poDesc.quantity",
				
				"(not poMaster.poStatus='complete') and "+
				"poDesc.poNo=poMaster.poNo and "+
				"poDesc.matNo='"+matId+"'"
			);
			tmp2=dbi.cmdSelect(
				"poMaster, grnMaster, grnDesc",
				
				"grnDesc.grnQty, grnMaster.grnNo",
				
				"(not poMaster.poStatus='complete') and "+
				"grnMaster.poNo=poMaster.poNo and "+
				"grnMaster.grnNo=grnDesc.grnNo and "+
				"grnDesc.matNo='"+matId+"'"
			);
		}
		catch(Exception ex){
			System.out.println (ex);
		}
		
		double res=0;
		for(String[] tmp: tmp1){
			res+=Double.parseDouble(tmp[0]);
		}
		for(String[] tmp: tmp2){
			if(tmp[1].startsWith("GRN")){
				res-=Double.parseDouble(tmp[0]);
			}
			else if(tmp[1].startsWith("GRC")){
				res+=Double.parseDouble(tmp[0]);
			}
		}
		return res;
	}
	
	/** called from JInternalFrameShowPO.showPoBomDet()
	 *              JInternalFrameGoodsReceiveNote....*/
	public String[] getBomList(String poNo){
		java.lang.String[] res=null;
		java.lang.String[][] tmp=null;
		java.util.Vector<String> tmp1=new java.util.Vector<String>();
		try{
			tmp=dbi.cmdSelect(
				"poBomDesc", 
				"poBomDesc.bomNo", 
				"poBomDesc.poNo='"+poNo+"'"
			);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		for(String[] tmp2: tmp){
			if(!tmp1.contains(tmp2[0])){
				tmp1.add(tmp2[0]);
			}
		}
		res=new String[tmp1.size()];
		for (int i = 0; i<res.length; i++)	{
			res[i]=new String(tmp1.remove(0));
		}
		return res;
	}
	
	/** called from JInternalFrameShowPO.bomSelected() */
	public String[][] getPoBomDet(String poNo, String bomNo){
		java.lang.String[][] res=null;
		try{
			res=dbi.cmdSelect(
				"poBomDesc, bomDesc, matMaster", 
				
				"matMaster.matName, bomDesc.purRqtn", 
				
				"poBomDesc.poNo='"+poNo+"' and "+
				"poBomDesc.bomNo='"+bomNo+"' and "+
				"bomDesc.bomNo=poBomDesc.bomNo and "+
				"poBomDesc.matNo=bomDesc.matNo and "+
				"poBomDesc.matNo=matMaster.matId"
			);
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		return res;
	}
	
	public java.lang.Object[][] preparePO(java.lang.String[][] oldItmList, java.lang.String[][] newItmList){
		java.lang.Object[][] res=null;
		java.util.Vector<java.lang.String> tmp0=new java.util.Vector<java.lang.String>();
		java.util.Vector<java.lang.Double> tmp1=new java.util.Vector<java.lang.Double>();
		
		for (int j = 0; j<oldItmList.length; j++){
			tmp0.add(oldItmList[j][0]);
			tmp1.add(java.lang.Double.parseDouble(oldItmList[j][1]));
		}
		
		for (int j = 0; j<newItmList.length; j++){
			if(!tmp0.contains(newItmList[j][0])){
				tmp0.add(newItmList[j][0]);
				tmp1.add(java.lang.Double.parseDouble(newItmList[j][1]));
			}
			else{
				int k=tmp0.indexOf(newItmList[j][0]);
				double amt=tmp1.elementAt(k);
				amt+=java.lang.Double.parseDouble(newItmList[j][1]);
				tmp1.remove(k);
				tmp1.add(k, amt);
			}
		}
		res=new java.lang.Object[tmp0.size()][18];
		for (int i = 0; i<res.length; i++){
			res[i]=new java.lang.Object[18];
			
			res[i][0]=new java.lang.Short((short)(i+1));
			res[i][1]=new java.lang.String(tmp0.elementAt(i));
			res[i][2]=new java.lang.String(tmp1.elementAt(i).toString());
			res[i][3]=new java.lang.Double(0);
			res[i][4]=new java.lang.Double(0);
			for (int j = 3; j<9; j++){
				res[i][2*j-1]=new java.lang.Double(0);
				res[i][2*j]=new java.lang.Double(0);
			}
			res[i][17]=new java.lang.Double(0);
		}
		
    	return res;
	}
	
	public Object[] calculateNetAmt(String[] rowData, int col){
		if(col>4 && Double.parseDouble(rowData[4])==(double)0){
			return returnZero(rowData);
		}
		Object[] res=new Object[18];
		for (int i = 0; i<18; i++){
			res[i]=new String(rowData[i]);
		}
		checkNumberFormat(res, col); // if non numeric char then make that zero
		
		if(Double.parseDouble(res[col].toString())<(double)0){ 
			res[col]=new Double(0.0);
		}
		double amt=0;
		switch(col){
			case 2: //qty
			case 3: //rate
			res[4]=new Double(Double.parseDouble(res[2].toString())*Double.parseDouble(res[3].toString()));
			case 4: //amount
			if(Double.parseDouble(res[4].toString())==0){
				return returnZero(res);
			}
			case 5: //discount(Rs.)
			case 6: //discount(%)
			if(col==6){
				res[5]=new Double(Double.parseDouble(res[6].toString())/100*Double.parseDouble(res[4].toString()));
			}
			else{
				res[6]=new Double(Double.parseDouble(res[5].toString())*100/Double.parseDouble(res[4].toString()));
			}
			case 7: //surcharge(rs.)
			case 8: //surcharge(%)
			amt=Double.parseDouble(res[4].toString())-Double.parseDouble(res[5].toString());
			if(col==8){
				res[7]=new Double(Double.parseDouble(res[8].toString())/100*amt);
			}
			else{
				res[8]=new Double(Double.parseDouble(res[7].toString())*100/amt);
			}
			case 9: //ED(rs)
			case 10: //ED(%)
			amt=Double.parseDouble(res[4].toString())-Double.parseDouble(res[5].toString())+Double.parseDouble(res[7].toString());
			if(col==10){
				res[9]=new Double(Double.parseDouble(res[10].toString())/100*amt);
			}
			else{
				res[10]=new Double(Double.parseDouble(res[9].toString())*100/amt);
			}
			case 11: //VAT(rs)
			case 12: // VAT(%)
			amt=Double.parseDouble(res[4].toString())-Double.parseDouble(res[5].toString())+Double.parseDouble(res[7].toString())
			 +Double.parseDouble(res[9].toString());
			if(col==12){
				res[11]=new Double(Double.parseDouble(res[12].toString())/100*amt);
			}
			else{
				res[12]=new Double(Double.parseDouble(res[11].toString())*100/amt);
			}
			if(Double.parseDouble(res[11].toString())!=0 && (col==11 || col==12)){
				res[13]=new Double(0);
				res[14]=new Double(0);
			}
			case 13: // CST(rs)
			case 14: // CST(%)
			amt=Double.parseDouble(res[4].toString())-Double.parseDouble(res[5].toString())+Double.parseDouble(res[7].toString())
			 +Double.parseDouble(res[9].toString());
			if(col==14){
				res[13]=new Double(Double.parseDouble(res[14].toString())/100*amt);
			}
			else{
				res[14]=new Double(Double.parseDouble(res[13].toString())*100/amt);
			}
			if(Double.parseDouble(res[13].toString())!=0 && (col==13 || col==14)){
				res[11]=new Double(0);
				res[12]=new Double(0);
			}
			case 15: // freight(rs)
			case 16: // freight(%)
			amt=Double.parseDouble(res[4].toString())-Double.parseDouble(res[5].toString())+Double.parseDouble(res[7].toString())
			 +Double.parseDouble(res[9].toString())+Double.parseDouble(res[11].toString())+Double.parseDouble(res[13].toString());
			if(col==16){
				res[15]=new Double(Double.parseDouble(res[16].toString())/100*amt);
			}
			else{
				res[16]=new Double(Double.parseDouble(res[15].toString())*100/amt);
			}
			case 17: // net amt
			amt=Double.parseDouble(res[4].toString())-Double.parseDouble(res[5].toString())+Double.parseDouble(res[7].toString())
			 +Double.parseDouble(res[9].toString())+Double.parseDouble(res[11].toString())+Double.parseDouble(res[13].toString())
			 +Double.parseDouble(res[15].toString());
			res[17]=new Double(amt);
			default:
		}
		
		return res;
	}
	
	private void checkNumberFormat(Object[] rowData, int col){
		try{
			Double.parseDouble(rowData[col].toString());
		}
		catch(java.lang.NumberFormatException ex){
			rowData[col]=new Double(0.0);
		}
	}
	
	private Object[] returnZero(Object[] rowData){
		Object[] zero=new Object[18];
		for (int i = 0; i<5; i++){
			zero[i]=rowData[i];
		}
		for (int i = 5; i<18; i++){
			zero[i]=new Double(0.0);
		}
		return zero;
	}
	
	public String[][] getPoDesc(String poNo){
    	String[][] res=null;
    	
    	try{
	    	res=dbi.cmdSelect(
	    		"poDesc", 
	    		"*", 
	    		"poDesc.poNo='"+poNo+"'"
	    	);
    	}
    	catch(java.sql.SQLException ex){
    		System.out.println (ex);
    	}
    	
    	return res;
	}
	
	/** called from JInternalFrameShowPO.showPoDet(). */
	public String[][] getPoDetForShowPO(String poNo){
		String[][] res=null;
    	String [][]tmp=null;
    	try{
	    	tmp=dbi.cmdSelect(
	    		"poDesc", 
	    		"*", 
	    		"poDesc.poNo='"+poNo+"'"
	    	);
    	}
    	catch(java.sql.SQLException ex){
    		System.out.println (ex);
    	}
    	
    	inventorycontroller.function.MaterialProcessor mp=
    	 new inventorycontroller.function.MaterialProcessor(dbi);
    	String[] tmp1=null;
    	double tmp2=0;
    	res=new String[tmp.length][19];
    	for (int i = 0; i<res.length; i++){
    		res[i][0]=(i+1)+""; //Sl No.
    		
    		res[i][1]=tmp[i][1]+""; //Material No.
    		
    		tmp1=mp.getMatNames("matMaster.matId='"+tmp[i][1]+"'"); 
    		res[i][2]=tmp1[0]; //Material Name
    		
    		res[i][3]=tmp[i][2]+""; //Quantity
    		
    		res[i][4]=tmp[i][3]+""; //Rate
    		tmp2=Double.parseDouble(res[i][4])*Double.parseDouble(res[i][3]);
    		
    		res[i][5]=tmp2+""; //Amount
    		
    		res[i][7]=tmp[i][4]+""; //Discount (%)
    		res[i][6]=(Double.parseDouble(res[i][7])*tmp2/100)+""; //Discount (Rs.)
    		tmp2-=Double.parseDouble(res[i][6]);
    		
    		res[i][9]=tmp[i][5]+""; //Surcharge (%)
    		res[i][8]=(Double.parseDouble(res[i][9])*tmp2/100)+""; //Surcharge (Rs.)
    		tmp2+=Double.parseDouble(res[i][8]);
    		
    		res[i][11]=tmp[i][6]+""; //Excise Duty (%)
    		res[i][10]=(Double.parseDouble(res[i][11])*tmp2/100)+""; //Excise Duty (Rs.)
    		tmp2+=Double.parseDouble(res[i][10]);
    		
    		res[i][13]=tmp[i][7]+""; //VAT (%)
    		res[i][12]=(Double.parseDouble(res[i][13])*tmp2/100)+""; //VAT (Rs.)
    		tmp2+=Double.parseDouble(res[i][12]);
    		
    		res[i][15]=tmp[i][8]+""; //CST (%)
    		res[i][14]=(Double.parseDouble(res[i][15])*tmp2/100)+""; //CST (Rs.)
    		tmp2+=Double.parseDouble(res[i][14]);
    		
    		res[i][17]=tmp[i][9]+""; //Freight / Insurance (%)
    		res[i][16]=(Double.parseDouble(res[i][17])*tmp2/100)+""; //Freight / Insurance (Rs.)
    		tmp2+=Double.parseDouble(res[i][16]);
    		
    		res[i][18]=tmp2+"";//Net Amount
    	}
    	return res;
	}
	
	public void setPoStatus(String status, String poNo){
		try{
			dbi.cmdUpdate(
				"poMaster",
				"poMaster.poStatus='"+status+"'",
				"poNo='"+poNo+"'"
			);
		}
		catch(Exception ex){
			System.out.println (ex);
		}
	}
	
	public boolean isPending(String poNo) {
		String[][] tmp=null;
		String[][] tmp1=null;
		try{
			tmp=dbi.cmdSelect(
				"grnMaster", 
				
				"grnMaster.grnNo", 
				
				"grnMaster.poNo='"+poNo+"' and "+
				"grnMaster.grnNo like 'GRN%'"
			);
			tmp1=dbi.cmdSelect(
				"grnMaster", 
				
				"grnMaster.grnNo", 
				
				"grnMaster.poNo='"+poNo+"' and "+
				"grnMaster.grnNo like 'GRC%'"
			);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		
		return tmp.length==tmp1.length;
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