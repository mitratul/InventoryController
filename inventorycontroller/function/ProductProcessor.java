 /*
 * ProductProcessor.java
 *
 * Created on July 07, 2007, 23:20 HRS
 * Last Modified on July 07, 2007, 23:20 HRS
 *
 */

package inventorycontroller.function;

/**
 *
 * @author  brinto
 */
public class ProductProcessor {
	public static final String PRODUCT_ID="PRD";
	
	public ProductProcessor(inventorycontroller.function.DbInterface dbi){
		this.dbi=dbi;
	}
	    
    public java.lang.String getNewPrdNo() {
    	java.lang.String[][] prdNos=null;
    	
    	try{
	    	prdNos=dbi.cmdSelect(
	    		"prdMaster", "prdId", "1=1 order by prdId");
    	}
    	catch(java.sql.SQLException ex){
    		System.out.println (ex);
    		return "";
    	}
    	
    	int prdNo=0;
    	String currentDate=inventorycontroller.function.DateProcessor
    	 .getCurrentDate(dbi);
    	String yr=inventorycontroller.util.DateUtil.getFinancialYear(currentDate);
    	
    	for (int i = 0; i<prdNos.length; i++){
    		if(
    			prdNos[i][0].startsWith(PRODUCT_ID+"/") && 
    			prdNos[i][0].endsWith("/"+yr)
    		){
    			prdNo++;
    		}
    	}
    	java.lang.String fullPrdNo=new java.lang.String(PRODUCT_ID+"/");
    	for (int i = 7; i>=0; i--){	//if odrNo>10^7 then length will incr
	    	if((double)prdNo<java.lang.Math.pow((double)10, (double)i)){
	    		fullPrdNo=fullPrdNo+"0";
	    	}
	    	else{
	    		fullPrdNo=fullPrdNo+prdNo;
	    		break;
	    	}
    	}
    	fullPrdNo=fullPrdNo+"/"+yr;
    	
    	return fullPrdNo;
    }
	
	public java.lang.String[][] getProducts() {
		java.lang.String[][] res=null;
		try{
			res=dbi.cmdSelect("prdMaster", "prdId, prdName, prdRemark", "1=1");
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		return res;

	}
	
	public java.lang.String[] getProductNames(java.lang.String condition) {
		java.lang.String[] res=null;
		java.lang.String[][] tmp=null;
		
		try{
			tmp=dbi.cmdSelect("prdMaster", "prdName", condition);
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
	
	public java.lang.String[] getProductIds(java.lang.String condition) {
		java.lang.String[] res=null;
		java.lang.String[][] tmp=null;
		
		try{
			tmp=dbi.cmdSelect("prdMaster", "prdId", condition);
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
	
	public String getProductId(String prdName){
		String[] res=getProductIds("prdName='"+prdName+"'");
		return res[0];
	}
	
	public java.lang.String[][] getDescription(java.lang.String prdId){
		java.lang.String[][] res=null;
		try{
			res=dbi.cmdSelect(
				"prdDesc, matMaster", 
				"matMaster.matName, prdDesc.quantity", 
				"prdDesc.prdNo='"+prdId+"' and prdDesc.matNo=matMaster.matId");
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		return res;
	}
	
	
	public static void main(String[] args){
		String[] res=null;
		try{
			inventorycontroller.function.DbInterface dbi=
			 new inventorycontroller.function.DbInterface(
			 	"./db/db.mdb", "Microsoft Access Driver (*.mdb)"
			 );
			res=new ProductProcessor(dbi).getProductNames("1=1");
			dbi.close();
		}
		catch(java.lang.Exception ex){
			System.out.println (ex);
		}
		if(res==null){
			return;
		}
		for (int i = 0; i<res.length; i++){
			System.out.println (res[i]);
		}
	}
	
    
    //* Variables declaration - 
	private inventorycontroller.function.DbInterface dbi;
    //* End of variables declaration
}