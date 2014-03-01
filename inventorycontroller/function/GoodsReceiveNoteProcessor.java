 /*
 * GoodsReceiveNoteProcessor.java
 *
 * Created on July 21, 2007, 11:20 HRS
 * Last Modified on August 21, 2007, 11:20 HRS
 *
 */

package inventorycontroller.function;

/**
 *
 * @author  brinto
 */
public class GoodsReceiveNoteProcessor {
	
	public static final String GRN_ID="GRN";
	public static final String GRN_CANCELLATION_ID="GRC";
	
	public GoodsReceiveNoteProcessor(inventorycontroller.function.DbInterface dbi){
		this.dbi=dbi;
	}
	    
    public java.lang.String getNewGrnNo(String rawDate) {
    	java.lang.String[][] grnNos=null;
    	
    	java.lang.String fullGrnNo=new java.lang.String(GRN_ID+"/"+rawDate.substring(4)+"/");
    	try{
	    	grnNos=dbi.cmdSelect(
	    		"grnMaster", 
	    		"grnNo", 
	    		"grnMaster.grnNo like '"+fullGrnNo+"%' order by grnNo"
	    	);
    	}
    	catch(java.sql.SQLException ex){
    		System.out.println (ex);
    		return "";
    	}
    	
    	int grnNo=1;
    	String currentDate=rawDate;
    	String yr=inventorycontroller.util.DateUtil.getFinancialYear(currentDate);
    	
    	for (int i = 0; i<grnNos.length; i++){
    		if(
    			grnNos[i][0].endsWith("/"+yr)
    		){
    			grnNo++;
    		}
    	}
    	for (int i = 3; i>=0; i--){	//if odrNo>10^3 then length will incr
	    	if((double)grnNo<java.lang.Math.pow((double)10, (double)i)){
	    		fullGrnNo=fullGrnNo+"0";
	    	}
	    	else{
	    		fullGrnNo=fullGrnNo+grnNo;
	    		break;
	    	}
    	}
    	fullGrnNo=fullGrnNo+"/"+yr;
    	
    	return fullGrnNo;
    }
    
    public String[][] getGrnForGrnCancellation(){
    	String[][] res=null;
    	String[][] tmp1=null;
    	String[][] tmp2=null;
    	try{
    		tmp1=dbi.cmdSelect(
    			"grnMaster, poMaster, vndMaster", 
    			
    			"grnMaster.grnNo, "+
    			"grnMaster.poNo, "+
    			"grnMaster.grnDate, "+
    			"vndMaster.vndCmpName", 
    			
    			"grnMaster.grnNo like 'GRN%' and "+
    			"poMaster.poNo=grnMaster.poNo and "+
    			"vndMaster.vndNo=poMaster.vndNo order by grnMaster.grnNo"
    		);
    		tmp2=dbi.cmdSelect(
    			"grnMaster", 
    			"grnMaster.grnNo", 
    			"grnMaster.grnNo like 'GRC%'"
    		);
    		
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	
    	java.util.Vector<String> v=new java.util.Vector<String>();
    	for (int i = 0; i<tmp2.length; i++){
    		v.add(new String(tmp2[i][0]));
    	}
    	
    	res=new String[tmp1.length-tmp2.length][4];
    	for (int i = 0, j = 0; i<tmp1.length; i++){
    		if(!v.contains(tmp1[i][0].replaceFirst("GRN", "GRC"))){
    			res[j][0]=tmp1[i][0];
    			res[j][1]=tmp1[i][1];
    			res[j][2]=tmp1[i][2];
    			res[j++][3]=tmp1[i][3];
    		}
    	}
    	return res;
    }
    
    public Object[][] getGrnForGrnReport(){
    	Object[][] res=null;
    	String[][] tmp=null;
    	try{
    		tmp=dbi.cmdSelect(
    			"grnMaster, poMaster, vndMaster", 
    			
    			"grnMaster.grnNo, "+
    			"grnMaster.grnAmt, "+
    			"grnMaster.grnDate, "+
    			"grnMaster.poNo, "+
    			"poMaster.vndNo, "+
    			"vndMaster.vndCmpName", 
    			
    			"poMaster.poNo=grnMaster.poNo and "+
    			"vndMaster.vndNo=poMaster.vndNo order by grnMaster.grnNo"
    		);
    	}
    	catch(Exception ex){
    		System.out.println (ex);
    	}
    	res=new Object[tmp.length][7];
    	for (int i = 0; i<res.length; i++){
    		res[i][0]=new Boolean(false);
    		for (int j = 0; j<6; j++){
    			res[i][j+1]=tmp[i][j];
    		}
    	}
    	inventorycontroller.util.DateUtil du=null;
    	for (int i = 0; i<res.length; i++){
    		res[i][3]=du.getDisplayFormat(res[i][3].toString());
    	}
    	return res;
    }
    
    public Object[][] prepareGRN(String[][] poDet){
    	Object[][] res=null;
    	String[][] tmp1=poDet;
    	
    	res=new Object[tmp1.length][16];
    	inventorycontroller.function.MaterialProcessor mp=
    	 new inventorycontroller.function.MaterialProcessor(dbi);
    	String[] tmp2=null;
    	for (int i = 0; i<tmp1.length; i++){
    		double rem=Double.parseDouble(tmp1[i][2])-getDelivered(tmp1[i][0], tmp1[i][1]);
    		res[i][0]=(i+1)+"";
    		tmp2=mp.getMatNames("matId='"+tmp1[i][1]+"'");
    		res[i][1]=tmp2[0];
    		res[i][2]=rem+"";
    		res[i][3]=rem+"";
    		res[i][4]="0.0";
    		res[i][5]=rem+"";
    		double qty=Double.parseDouble(res[i][5].toString());
    		double amt=qty*Double.parseDouble(tmp1[i][3].toString());
    		res[i][6]=amt+"";
    		res[i][7]=amt/100*Double.parseDouble(tmp1[i][4])+"";
    		amt-=Double.parseDouble(res[i][7].toString());
    		res[i][8]=amt/100*Double.parseDouble(tmp1[i][5])+"";
    		amt+=Double.parseDouble(res[i][8].toString());
    		res[i][9]=amt/100*Double.parseDouble(tmp1[i][6])+"";
    		res[i][10]=false;
    		amt+=Double.parseDouble(res[i][9].toString());
    		res[i][11]=amt/100*Double.parseDouble(tmp1[i][7])+"";
    		amt+=Double.parseDouble(res[i][11].toString());
    		res[i][12]=amt/100*Double.parseDouble(tmp1[i][8])+"";
    		amt+=Double.parseDouble(res[i][12].toString());
    		res[i][13]=amt/100*Double.parseDouble(tmp1[i][9])+"";
    		amt+=Double.parseDouble(res[i][13].toString());
    		res[i][14]=amt+"";
    		res[i][15]=(qty==(double)0?0:Double.parseDouble(res[i][14].toString())/qty)+"";
    	}
    	return res;
    }
    
    /** called from JInternalFrameGRNCancellation.fillGrnStatus(). */
    public String[][] prepareGrnStatus(String grnNo){
    	String[][] res=null;
    	String[][] tmp=null;
    	try{
    		tmp=dbi.cmdSelect(
    			"grnDesc, matMaster", 
    			
    			"grnDesc.matNo, "+
    			"matMaster.matName, "+
    			"grnDesc.grnQty, "+
    			"grnDesc.grnEdAmt, "+
    			"matMaster.matStockGeneral, "+
    			"matMaster.matStockExcisable", 
    			
    			"grnDesc.grnNo='"+grnNo+"' and "+
    			"matMaster.matId=grnDesc.matNo"
    		);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	res=new String[tmp.length][7];
    	for (int i = 0; i<res.length; i++){
    		res[i][0]=(i+1)+""; // slNo
    		res[i][1]=tmp[i][0]+""; // mat id
    		res[i][2]=tmp[i][1]+""; // mat name
    		double tmp1=0;
    		
    		if(Double.parseDouble(tmp[i][3])==0){ // general
    			tmp1=Double.parseDouble(tmp[i][2])-Double.parseDouble(tmp[i][4]);
    			tmp1=tmp1<0?0:tmp1;
    			
	    		res[i][3]="0.0"; // received qty ex.
	    		res[i][4]=tmp[i][2]+""; // received qty gen.
	    		res[i][5]="0.0"; // used qty ex.
	    		res[i][6]=tmp1+""; // used qty gen.
    		}
    		else { //excisable
    			tmp1=Double.parseDouble(tmp[i][2])-Double.parseDouble(tmp[i][5]);
    			tmp1=tmp1<0?0:tmp1;
    			
	    		res[i][3]=tmp[i][2]+""; // received qty ex.
	    		res[i][4]="0.0"; // received qty gen.
	    		res[i][5]=tmp1+""; // used qty ex.
	    		res[i][6]="0.0"; // used qty gen.
    		}
    	}
    	return res;
    }
    
	public Object[] calculateNetAmt(Object[] rowData, int col, String[] poDet)throws Exception{
		if(col>5 && Double.parseDouble(rowData[5].toString())==(double)0){
			return returnZero(rowData);
		}
		Object[] res=new Object[16];
		// clone the array in res
		for (int i = 0; i<16; i++){
			res[i]=new String(rowData[i].toString());
		}
		res[10]=new Boolean(rowData[10].toString());
		// if non numeric char then make that zero
		checkNumberFormat(res, col);
		// convert the -ve qty to zero
		if((col==10?1.0:Double.parseDouble(res[col].toString()))<(double)0){
			res[col]=new Double(0.0);
		}
		// now cascading update::
		double amt=0;
		double t=0;
		switch(col){
			case 3: // supplied
			if(Double.parseDouble(res[3].toString())>Double.parseDouble(res[2].toString())){
				res[3]=new Double(0.0);
			}
			case 4: // rejected
			if(Double.parseDouble(res[4].toString())>Double.parseDouble(res[3].toString())){
				res[4]=new Double(0.0);
			}
			res[5]=new Double(
				Double.parseDouble(res[3].toString())-Double.parseDouble(res[4].toString())
			);
			if(Double.parseDouble(res[5].toString())==(double)0){
				return returnZero(res);
			}
			amt=Double.parseDouble(res[5].toString())*Double.parseDouble(poDet[3]);
			res[6]=amt;
			res[7]=amt*Double.parseDouble(poDet[4])/100;
			
			case 7: // discount
			amt=Double.parseDouble(res[6].toString())-
				Double.parseDouble(res[7].toString());
			res[8]=amt*Double.parseDouble(poDet[5])/100;
			
			case 8: // surcharge
			amt=Double.parseDouble(res[6].toString())-
				Double.parseDouble(res[7].toString())+
				Double.parseDouble(res[8].toString());
			res[9]=amt*Double.parseDouble(poDet[6])/100;
			
			case 9: // ED
			if(Double.parseDouble(res[9].toString())==(double)0){
				res[10]=new Boolean(false);
			}
			/*else{
				res[10]=new Boolean(true);
			}*/
			
			case 10: // ED included
			if(Double.parseDouble(res[9].toString())==(double)0){
				res[10]=new Boolean(false);
			}
			amt=Double.parseDouble(res[6].toString())-
				Double.parseDouble(res[7].toString())+
				Double.parseDouble(res[8].toString());
			if(!Boolean.parseBoolean(res[10].toString())){
				amt=Double.parseDouble(res[6].toString())-
					Double.parseDouble(res[7].toString())+
					Double.parseDouble(res[8].toString())+
					Double.parseDouble(res[9].toString());
			}
			res[11]=amt*Double.parseDouble(poDet[7])/100;
			res[12]=amt*Double.parseDouble(poDet[8])/100;
			
			case 11: // VAT
			if(Double.parseDouble(res[11].toString())>(double)0){
				res[12]=new Double(0.0);
				t=Double.parseDouble(res[11].toString());
			}
			
			case 12: // CST
			if(Double.parseDouble(res[12].toString())>(double)0){
				res[11]=new Double(0.0);
				t=Double.parseDouble(res[12].toString());
			}
			amt=Double.parseDouble(res[6].toString())-
				Double.parseDouble(res[7].toString())+
				Double.parseDouble(res[8].toString())+
				(Boolean.parseBoolean(res[10].toString())?(double)0:
				 Double.parseDouble(res[9].toString()))+
				t;
			res[13]=amt*Double.parseDouble(poDet[9])/100;
			
			case 13: // Freight
			amt=Double.parseDouble(res[6].toString())-
				Double.parseDouble(res[7].toString())+
				Double.parseDouble(res[8].toString())+
				(Boolean.parseBoolean(res[10].toString())?(double)0:
				 Double.parseDouble(res[9].toString()))+
				Double.parseDouble(res[11].toString())+
				Double.parseDouble(res[12].toString())+
				Double.parseDouble(res[13].toString());
			res[14]=amt;
			res[15]=amt/Double.parseDouble(res[5].toString());
			
			default:
		}
		
		return res;
	}
	
	private void checkNumberFormat(Object[] rowData, int col){
		if(col!=10){
			try{
				Double.parseDouble(rowData[col].toString());
			}
			catch(java.lang.NumberFormatException ex){
				rowData[col]=new Double(0.0);
			}
		}
	}
	
	private Object[] returnZero(Object[] rowData){
		Object[] zero=new Object[16];
		for (int i = 0; i<6; i++){
			zero[i]=rowData[i];
		}
		for (int i = 6; i<16; i++){
			zero[i]=new Double(0.0);
		}
		zero[10]=new Boolean(false);
		return zero;
	}
	
    private double getDelivered(String poNo, String matNo){
    	double delivered=(double)0;
    	String[] grnList=getGrnNos(poNo);
    	for(String grnNo: grnList){
    		
    		delivered+=getGrnQty("grnDesc.grnNo='"+grnNo+"' and grnDesc.matNo='"+matNo+"'");
    	}
    	return delivered;
    }
    
    private String[] getGrnNos(String poNo){
    	String[] res=null;
    	String[][] tmp=null;
    	String[][] tmp1=null;
    	try{
    		tmp=dbi.cmdSelect(
    			"grnMaster",
    			"grnMaster.grnNo",
    			"grnMaster.poNo='"+poNo+"' and grnMaster.grnNo like 'GRN%'"
    		);
    		tmp1=dbi.cmdSelect(
    			"grnMaster",
    			"grnMaster.grnNo",
    			"grnMaster.poNo='"+poNo+"' and grnMaster.grnNo like 'GRC%'"
    		);
    	}
    	catch(Exception ex){
    		System.out.println (ex);
    	}
    	
    	java.util.Vector<String> v=new java.util.Vector<String>();
    	
    	for (int i = 0; i<tmp.length; i++){
    		v.add(tmp[i][0]);
    	}
    	for (int i = 0; i<tmp1.length; i++){
    		v.remove(tmp1[i][0].replaceFirst("GRC", "GRN"));
    	}
    	
    	res=new String[v.size()];
    	for (int i = 0; i<res.length; i++){
    		res[i]=new String(v.elementAt(i));
    	}
    	return res;
    }
    
    private double getGrnQty(String condition){
    	double res=(double)0;
    	String[][] tmp=null;
    	try{
    		tmp=dbi.cmdSelect(
    			"grnDesc",
    			"grnDesc.grnQty",
    			condition
    		);
    	}
    	catch(Exception ex){
    		System.out.println (ex);
    	}
    	if(tmp.length==0){ // if no rec found, ie that mat qty in that grn is 0.
    		return res;
    	}
    	res=Double.parseDouble(tmp[0][0]);
    	return res;
    }
    
    /** called from JInternalFrameGRNCancellation.updateMaterialStock(). */
    public double[] getCancelledQty(String[] grnDet){
    	String[] tmp=grnDet; //cols: grnNo, matNo, grnQty, dsc, sch, ed, vat, cst, freight, amt, TSid
    	String grnNo=tmp[0];
    	String matId=tmp[1];
    	
    	double[] res=new double[7];
    	
		res[4]=Double.parseDouble(tmp[5]);
		
		if(res[4]>0){ // excisable
			res[1]=Double.parseDouble(tmp[2]);
			res[3]=Double.parseDouble(tmp[6])+Double.parseDouble(tmp[7]);
			res[6]=Double.parseDouble(tmp[9]);
			res[0]=0;
			res[2]=0;
			res[5]=0;
		}
		else if(res[4]==0){ // general
			res[0]=Double.parseDouble(tmp[2]);
			res[2]=Double.parseDouble(tmp[6])+Double.parseDouble(tmp[7]);
			res[5]=Double.parseDouble(tmp[9]);
			res[1]=0;
			res[3]=0;
			res[6]=0;
		}
		return res; //* format: genqty, exqty, genvat, exvat, ed, genamt, examt
    }
	
    
    //* Variables declaration - 
	private inventorycontroller.function.DbInterface dbi;
    //* End of variables declaration
}