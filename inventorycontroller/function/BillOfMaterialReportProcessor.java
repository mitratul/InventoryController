/*
 * BillOfMaterialReportProcessor.java
 *
 * Created on September 11, 2007, 11:05 HRS
 * Last Modified on September 11, 2007, 11:05 HRS
 *
 */

package inventorycontroller.function;

/**
 *
 * @author  brinto
 */
public class BillOfMaterialReportProcessor {
	
	public static final short REPORT_WIDTH=108;
	private static final int[] colWidth=new int[]{50, 18, 18, 22};
	
	private int linesPerPage=67;
	
	public BillOfMaterialReportProcessor(inventorycontroller.function.DbInterface dbi, String[] bomNos){
	 	
	 	this.linesPerPage=this.getLinesPerPage();
	 	this.printProcess=null;
	 	this.lineCount=0;
	 	this.bomNos=bomNos;
		this.dbi=dbi;
		this.bomTotal=0;
		this.reportFile=allocateReportFile();

	}
	
	private int getLinesPerPage(){
		java.util.Properties p=new java.util.Properties();
		String tmp="";
		int l=67;
		try{
			java.io.FileInputStream fis=
			 new java.io.FileInputStream(new java.io.File("./properties/PRINTER.PROPERTY"));
			p.load(fis);
			
			tmp=p.getProperty("linesPerPage");
			
			fis.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
			return l;
		}
		try{
			l=Integer.parseInt(tmp);
		}
		catch(NumberFormatException ex){
			return l;
		}
		return l;
	}
	
	private java.io.File allocateReportFile(){
		java.io.File f=null;
		String fileName;
		fileName="./temp/BOM_Report#1.txt";
		f=new java.io.File(fileName);
		
		//* erase previous content.
		try{
			java.io.FileWriter fw=new java.io.FileWriter(
				f, false
			);
			java.io.PrintWriter pw=new java.io.PrintWriter(fw, true);
			pw.close();
			fw.close();
		}
		catch(Exception ex){
			System.out.println ("cannot read "+fileName);
		}
		return f;
	}
	
	private String getHorizLine(String lineUnit){
		String hLine="";
		for (int i = 0; i<REPORT_WIDTH; i++){
			//hLine=hLine+"¯";
			hLine=hLine+lineUnit;
		}
		return hLine;
	}
	
	public String[] getColumnNames(){
		return new String[]{
			"Item Code\nDescription", 
			"Required Qty.\n(unit)", 
			"Rate (Rs./unit)", 
			"Amount (Rs.)"
		};
		
	}
	
	public int[] getAlignment(){
		return new int[]{0, 1, 1, 1};
	}
	
	/**
	 * methods to print to file.
	 */
	 
	public void generate(){
		
		for (int i = 0; i<bomNos.length; i++){
			this.lineCount=0;
			bomTotal=0;
			printReportHeader();
			printBomHeader(bomNos[i]);
			printBomDataHeader();
			printBomData(bomNos[i]);
			printBomTotal(bomNos[i]);
			printReportFooter(bomNos[i]);
		}
		
	}
	
	private void printReportHeader(){
		String[] reportHead=new String[9];
		//reportHead[0]="<<~h~>>";
		reportHead[0]="                        ============================================================";
		reportHead[1]="                        #                   * BILL OF MATERIAL *                   #";
		reportHead[2]="                        #                     ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯                     #";
		reportHead[3]="                        #                                                          #";
		reportHead[4]="                        #         SREE CHAND ELECTRICAL INDUSTRIES PVT. LTD.       #";
		reportHead[5]="                        ============================================================";
		reportHead[6]="";
		reportHead[7]="";
		reportHead[8]="";
		//reportHead[0]="<<~~h~>>";
		
		this.printLines(reportHead);
		
	}
	
	private void printBomHeader(String bomNo){
		String[][] tmp1=null;
		try{
			tmp1=dbi.cmdSelect(
				"bomMaster, jbMaster, odrMaster, prdMaster", 
				
				"bomMaster.rqnDate, "+
				"bomMaster.jbNo, "+
				"prdMaster.prdName, "+
				"jbMaster.quantity, "+
				"odrMaster.custPONo",
				
				"bomMaster.bomNo='"+bomNo+"' and "+
				"jbMaster.jbNo=bomMaster.jbNo and "+
				"odrMaster.odrNo=jbMaster.odrNo and "+
				"prdMaster.prdId=jbMaster.prdNo"
			);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		//* bring date to display format.
		tmp1[0][0]=inventorycontroller.util.DateUtil.getDisplayFormat(tmp1[0][0]);
		
		String[] bomHeaderLines=new String[5];
		bomHeaderLines[0]="B. O. M. NO.      "+bomNo+" OF DATE "+tmp1[0][0];
		bomHeaderLines[1]="JOB NO.           "+tmp1[0][1];
		bomHeaderLines[2]="DESCRIPTION       "+tmp1[0][2]+"    QUANTITY "+tmp1[0][3];
		bomHeaderLines[3]="CUSTOMER P.O. NO. "+tmp1[0][4];
		bomHeaderLines[4]="";
		
		//* write in file
		this.printLines(bomHeaderLines);
		
		
	}
	
	private void printBomDataHeader(){
		//* initialise.
		String[] colNames=getColumnNames();
		
		//* convert to lines, printable in grn report.
		String[] lines=getLines(colNames);
		String[] dataHeaderBlock=new String[lines.length+1];
		for (int i = 0; i<lines.length; i++){
			dataHeaderBlock[i]=lines[i];
		}
		dataHeaderBlock[lines.length]=getHorizLine("-");
		
		//* print header lines.
		printLines(dataHeaderBlock);
		
		
	}
	
	private void printBomData(String bomNo){
		String[][] tmp=null;
		try{
			tmp=dbi.cmdSelect(
				"bomDesc, matMaster",
				
				"bomDesc.matNo, "+
				"matMaster.matName, "+
				"bomDesc.matReqd, "+
				"matMaster.matStockGeneral, "+
				"matMaster.matStockExcisable, "+
				"matMaster.matAmtGeneral, "+
				"matMaster.matAmtExcisable, "+
				"matMaster.matUnit", 
				
				"bomDesc.bomNo='"+bomNo+"' and "+
				"matMaster.matId=bomDesc.matNo"
			);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		//* make bom report data table.
		String[][] bomData=new String[tmp.length][4];
		for (int i = 0; i<bomData.length; i++){
			String tmp1="0";
			String tmp2="0";
			inventorycontroller.util.DoubleUtil dbu=null;
			
			bomData[i][0]=tmp[i][0]+"\n"+tmp[i][1];
			
			bomData[i][1]=dbu.getDisplayValue(tmp[i][2], 3)
			 +"\n"+tmp[i][7];
			
			//* rate.
			tmp1=(Double.parseDouble(tmp[i][5])+Double.parseDouble(tmp[i][6])) //total amt
			    /(Double.parseDouble(tmp[i][3])+Double.parseDouble(tmp[i][4])) //total qty
			    +""; 
			if(new Double(tmp1).isInfinite() || new Double(tmp1).isNaN()){
				bomData[i][2]="________";
			}
			else{
				bomData[i][2]=dbu.getDisplayValue(tmp1, 2);
			}
			
			//* amt
			tmp2=Double.parseDouble(tmp1)*Double.parseDouble(tmp[i][2])+"";
			//* update bomTotal.
			bomTotal+=new Double(tmp2).doubleValue();
			
			if(new Double(tmp2).isInfinite() || new Double(tmp2).isNaN()){
				bomData[i][3]="________";
			}
			else{
				bomData[i][3]=dbu.getDisplayValue(tmp2, 2);
			}
		
			//* convert to printable lines
			String[] lines=this.getLines(bomData[i]);
			String[] linesWithGap=new String[lines.length+1];
			for (int j = 0; j<lines.length; j++){
				linesWithGap[j]=lines[j];
			}
			linesWithGap[lines.length]="";
			
			//* print lines.
			printLines(linesWithGap);
		}
		
		
	}
	
	private void printBomTotal(String bomNo){
		
		String totalAmt=""+bomTotal;
		if(new Double(this.bomTotal).isInfinite() || new Double(this.bomTotal).isNaN()){
			totalAmt="__________";
		}
		else {
			totalAmt=inventorycontroller.util.DoubleUtil.getDisplayValue(totalAmt, 2);
			
			totalAmt="RS. "+totalAmt;
		}
		
		int[] align=this.getAlignment();
		while(totalAmt.length()<this.colWidth[3]-2){
			if(align[3]==0){
				totalAmt=totalAmt+" ";
			}
			else {
				totalAmt=" "+totalAmt;
			}
		}
		
		String[] bomTotal=new String[4];
		bomTotal[0]=getHorizLine("¯");
		
		int remWidth=this.colWidth[0]+this.colWidth[1]+this.colWidth[2];
		bomTotal[1]="B.    O.    M.    T O T A L";
		int c=0;
		while(bomTotal[1].length()<remWidth){
			if(++c%2==1){
				bomTotal[1]=" "+bomTotal[1];
			}
			else{
				bomTotal[1]=bomTotal[1]+" ";
			}
		}
		bomTotal[1]=bomTotal[1]+" "+totalAmt;
		bomTotal[2]=getHorizLine("¯");
		bomTotal[3]="";
		
		//* print total to file.
		printLines(bomTotal);
	}
	
	private void printReportFooter(String bomNo){
		String[] tmp=getRemarks(bomNo);
		String[] rem=null;
		if(tmp.length>0){
			rem=new String[tmp.length+1];
			rem[0]="";
			rem[1]="Remarks: "+tmp[0];
		}
		else{
			rem=new String[tmp.length+2];
			rem[0]="";
			rem[1]="         ";
		}
		for (int i = 1; i<tmp.length; i++){
			rem[i+1]="         "+tmp[i];
		}
		printLines(rem);
		
		
		int remain=this.linesPerPage-(this.lineCount%this.linesPerPage);
		if(remain==this.linesPerPage){
			return;
		}
		String[] blankLines=new String[remain];
		for (int i = 0; i<remain; i++){
			blankLines[i]="";
		}
		printLines(blankLines);

	}
	
	private String[] getRemarks(String bomNo){
		String[][] rem=null;
		try{
			rem=this.dbi.cmdSelect(
				"bomMaster", 
				"bomMaster.bomRemark", 
				"bomMaster.bomNo='"+bomNo+"'"
			);
		}
		catch(Exception ex){
			return new String[0];
		}
		int w=this.REPORT_WIDTH-10;
		
		int l=rem[0][0].length()/w;
		l=rem[0][0].length()%w==0? l: ++l;
		String[] res=new String[l];
		for (int i = 0; i<l; i++){
			int ei=(i+1)*w;
			if(ei>rem[0][0].length()){
				ei=rem[0][0].length();
			}
			res[i]=new String(rem[0][0].substring(i*w, ei));
		}
		return res;
	}
	
	public String[] getLines(String[] colData){
		String[] lines=null;
		java.util.Vector<java.util.Vector<String>> lineBreaks
		 =new java.util.Vector<java.util.Vector<String>>(colWidth.length);
		
		//* calculate lines reqd.
		for (int i = 0; i<colWidth.length; i++){
			java.util.Vector<String> v=new java.util.Vector<String>();
			String s1=colData[i];
			java.util.StringTokenizer st=new java.util.StringTokenizer(s1, " \n", true);
			java.util.Vector<String> v1=new java.util.Vector<String>();
			while(st.hasMoreTokens()){
				v1.add(st.nextToken());
			}
			for (int j = 0; j<v1.size(); j++){
				String s3=v1.remove(j);
				if(s3.length()>colWidth[i]-2){
					v1.add(j, s3.substring(0, colWidth[i]-3)+"…");
					v1.add(j+1, s3.substring(colWidth[i]-2));
				}
				else {
					v1.add(j, s3);
				}
			}
			
			String token="";
			String s2="";
			int[] align=getAlignment();
			for (int j = 0; j<v1.size(); j++){
				token=v1.elementAt(j);
				if(token.compareTo(" ")!=0 && token.compareTo("\n")!=0){
					if(s2.length()+token.length()<colWidth[i]-2){
						s2=s2+token;
					}
					else if(s2.length()+token.length()==colWidth[i]-2){
						s2=s2+token;
						v.add(s2+"");
						s2="";
					}
					else {
						int sh=colWidth[i]-2-s2.length();
						for (int k = 0; k<sh; k++){
							if(align[i]==0){
								s2=s2+" ";
							}
							else {
								s2=" "+s2;
							}
						}
						while(s2.charAt(s2.length()-1)==' ' && align[i]!=0){
							s2=" "+s2.substring(0, s2.length()-1);
						}
						v.add(s2+"");
						s2=token;
					}
				}
				else if(token.compareTo("\n")==0){
					if(s2.length()>0){
						int sh=colWidth[i]-2-s2.length();
						for (int k = 0; k<sh; k++){
							if(align[i]==0){
								s2=s2+" ";
							}
							else {
								s2=" "+s2;
							}
						}
						while(s2.charAt(s2.length()-1)==' ' && align[i]!=0){
							s2=" "+s2.substring(0, s2.length()-1);
						}
						v.add(s2+"");
						s2="";
					}
				}
				else if(token.compareTo(" ")==0){
					if(s2.length()==0){
						//do nothing
					}
					else if(s2.length()+token.length()<colWidth[i]-2){
						s2=s2+token;
					}
					else if(s2.length()+token.length()==colWidth[i]-2){
						if(align[i]==0){
							s2=s2+" ";
						}
						else {
							s2=" "+s2;
						}
						while(s2.charAt(s2.length()-1)==' ' && align[i]!=0){
							s2=" "+s2.substring(0, s2.length()-1);
						}
						v.add(s2+"");
						s2="";
					}
				}
			}
			if(s2.length()>0){
				int sh=colWidth[i]-2-s2.length();
				for (int k = 0; k<sh; k++){
					if(align[i]==0){
						s2=s2+" ";
					}
					else {
						s2=" "+s2;
					}
				}
				v.add(s2+"");
			}
			
			lineBreaks.add(i, v);
		}// lineBreaks is populated.
		
		int max=0;
		for (int i = 0; i<lineBreaks.size(); i++){
			if(lineBreaks.elementAt(i).size()>max){
				max=lineBreaks.elementAt(i).size();
			}
		}
		
		for (int i = 0; i<lineBreaks.size(); i++){
			java.util.Vector<String> v2=lineBreaks.remove(i);
			int[] align=getAlignment();
			while(v2.size()<max){
				String space="";
				for (int j = 0; j<colWidth[i]-2; j++){
					space=space+" ";
				}
				if(align[i]==0){
					v2.add(space);
				}
				else {
					v2.add(0, space);
				}
			}
			lineBreaks.add(i, v2);
		}
		
		//* now make the lines.
		lines=new String[lineBreaks.elementAt(0).size()];
		for (int i = 0; i<lines.length; i++){
			lines[i]="";
		}
		for (int i = 0; i<lineBreaks.size(); i++){
			for (int j = 0; j<lineBreaks.elementAt(i).size(); j++){
				lines[j]=lines[j]+" "+lineBreaks.elementAt(i).elementAt(j)+" ";
			}
		}
		return lines;
	}
	
	private void printLines(String[] lines){
		try{
			java.io.FileWriter fw=new java.io.FileWriter(this.reportFile, true);
			java.io.PrintWriter pw=new java.io.PrintWriter(fw, true);
			for (int i = 0; i<lines.length; i++){
				pw.println(lines[i]);
				this.lineCount++;
			}
			pw.close();
			fw.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * methods to print from file to printer.
	 */

	
	public void initPrint(javax.swing.JDesktopPane parent){
		
        int choice=javax.swing.JOptionPane.showInternalConfirmDialog(
        	parent,
            "<html><center>\"You need "+
            "10\" paper & your Printer in CONDENSED mode<br>"+
            "to print this report.<br><br>"+
            "Prepare Printer & press OK to print the report\"<center>",
            "InventoryController says..",
            javax.swing.JOptionPane.OK_CANCEL_OPTION, 
            javax.swing.JOptionPane.INFORMATION_MESSAGE
        );
        if(choice!=javax.swing.JOptionPane.OK_OPTION){
        	return;
        }
        
        int status=print();
        
        if(status==0){
	        javax.swing.JOptionPane.showInternalMessageDialog(
	        	parent,
	            "<html><center>\"Print Successful\"<center>",
	            "InventoryController says..",
	            javax.swing.JOptionPane.WARNING_MESSAGE
	        );
        }
        else{
	        javax.swing.JOptionPane.showInternalMessageDialog(
	        	parent,
	            "<html><center>\"Print Failed\"<center>",
	            "InventoryController says..",
	            javax.swing.JOptionPane.WARNING_MESSAGE
	        );
        }
        
	}
	
	private int print(){
		
		String os=System.getenv("OS");
        String cmd="cmd";
        
        if(os==null){
        	cmd="command";
        }
        else if(os.indexOf("nt")<0 && os.indexOf("NT")<0){
        	cmd="command";
        }
        
		String printCommand=cmd+" /c type \""+this.reportFile.getAbsolutePath()+"\">prn";
		
		try{
			this.printProcess=Runtime.getRuntime().exec(printCommand);
			this.printProcess.waitFor();
		}
		catch(Exception ex){
			return 1;
		}
		
		int status=this.printProcess.exitValue();
		
		return status; 
		
	}
	
			
	
	private Process printProcess;
	private int lineCount;
	private String[] bomNos;
	private double bomTotal;
	private inventorycontroller.function.DbInterface dbi;
	private java.io.File reportFile;
}