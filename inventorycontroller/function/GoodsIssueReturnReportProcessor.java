/*
 * GoodsIssueReturnReportProcessor.java
 *
 * Created on September 29, 2007, 18:55 HRS
 * Last Modified on September 30, 2007, 02:16 HRS
 *
 */

package inventorycontroller.function;

/**
 *
 * @author  brinto
 */
public class GoodsIssueReturnReportProcessor {
	
	public static final short REPORT_WIDTH=108;
	private static final int[] colWidth=new int[]{36, 21, 21, 15, 15};
	
	private int linesPerPage=67;
	
	public GoodsIssueReturnReportProcessor(inventorycontroller.function.DbInterface dbi, 
	 String[] trnNos, String trnType){
	 	
	 	this.linesPerPage=this.getLinesPerPage();
	 	this.printProcess=null;
	 	this.lineCount=0;
	 	this.trnType=trnType;
	 	this.trnNos=trnNos;
		this.dbi=dbi;
		this.reportTotal=new String[4];
		for (int i = 0; i<4; i++){
			this.reportTotal[i]="0";
		}
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
		boolean b=false;
		int i=0;
		String fileName;
		fileName="./temp/IR_Report#1.txt";
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
	
	/**
	 * methods giving general information about the report. report specific.
	 */
	
	public int[] getAlignment(){
		return new int[]{0, 1, 1, 1, 1};
	}
	
	public String[] getColumnNames(){
		return new String[]{
			"Item Code\nDescription", 
			"General Qty. (unit)\nAmount (Rs.)", 
			"Excisable Qty. (unit)\nAmount (Rs.)",
			"VAT / CST (Rs.)", 
			"Excise Duty (Rs.)"
		};
		
	}
	
	/**
	 * methods to print to file.
	 */
	 
	public void generate(){
		printReportHeader();
		
		for (int i = 0; i<trnNos.length; i++){
			printTrnHeader(trnNos[i]);
			printTrnDataHeader();
			printTrnData(trnNos[i]);
			printTrnTotal(trnNos[i]);
		}
		
		printReportTotal();
		printReportFooter();
		
	}
	
	private void printReportHeader(){
		String[] reportHead=new String[9];
		//reportHead[0]="<<~h~>>";
		reportHead[0]="                        ============================================================";
		if(this.trnType.compareTo(inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_RETURN_ID)!=0){
		reportHead[1]="                        #                   * REQUISITION SLIP *                   #";
		reportHead[2]="                        #                     ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯                     #";
		}
		else {
		reportHead[1]="                        #                     * RETURN  SLIP *                     #";
		reportHead[2]="                        #                       ¯¯¯¯¯¯¯¯¯¯¯¯                       #";
		}
		reportHead[3]="                        #                                                          #";
		reportHead[4]="                        #         SREE CHAND ELECTRICAL INDUSTRIES PVT. LTD.       #";
		reportHead[5]="                        ============================================================";
		reportHead[6]="";
		reportHead[7]="";
		reportHead[8]="";
		//reportHead[0]="<<~h~>>";
		
		this.printLines(reportHead);
		
	}
	
	private void printTrnHeader(String trnNo){
		String[][] tmp1=null;
		try{
			tmp1=dbi.cmdSelect(
				"gdsJbTrnMaster, bomMaster", 
				
				"gdsJbTrnMaster.jbNo, "+
				"bomMaster.bomNo, "+
				"bomMaster.rqnDate, "+
				"gdsJbTrnMaster.trnDate",
				
				"gdsJbTrnMaster.trnNo='"+trnNo+"' and "+
				"bomMaster.jbNo=gdsJbTrnMaster.jbNo"
			);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		//* bring date to display format.
		tmp1[0][2]=inventorycontroller.util.DateUtil.getDisplayFormat(tmp1[0][2]);
		tmp1[0][3]=inventorycontroller.util.DateUtil.getDisplayFormat(tmp1[0][3]);
		
		//* make display format.
		String[] grnHeaderLines=new String[5];
		grnHeaderLines[0]="JOB NO.          "+tmp1[0][0];
		grnHeaderLines[1]="B. O. M. NO.     "+tmp1[0][1]+" OF DATE "+tmp1[0][2];
		if(this.trnType.compareTo("RQN")==0){
		grnHeaderLines[2]="REQUISITION NO.  "+trnNo;
		grnHeaderLines[3]="REQUISITION DATE "+tmp1[0][3];
		}
		else{
		grnHeaderLines[2]="R. T. S. NO.     "+trnNo;
		grnHeaderLines[3]="R. T. S. DATE    "+tmp1[0][3];
		}
		grnHeaderLines[4]="";
		
		//* write in file
		this.printLines(grnHeaderLines);
		
		
	}
	
	private void printTrnDataHeader(){
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
	
	private void printTrnData(String trnNo){
		String[][] tmp=null;
		try{
			tmp=dbi.cmdSelect(
				"gdsJbTrnDesc, matMaster",
				
				"gdsJbTrnDesc.matNo, "+
				"matMaster.matName, "+
				"gdsJbTrnDesc.qtyGeneral, "+
				"gdsJbTrnDesc.amtGeneral, "+
				"gdsJbTrnDesc.qtyExcisable, "+
				"gdsJbTrnDesc.amtExcisable, "+
				"gdsJbTrnDesc.amtVatGeneral, "+
				"gdsJbTrnDesc.amtVatExcisable, "+
				"gdsJbTrnDesc.amtED, "+
				"matMaster.matUnit",  
				
				"gdsJbTrnDesc.trnNo='"+trnNo+"' and "+
				"matMaster.matId=gdsJbTrnDesc.matNo"
			);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		inventorycontroller.util.DoubleUtil dbu=null;
		
		//* make grn report data table.
		String[][] trnData=new String[tmp.length][5]; //* no. of col is report specific.
		for (int i = 0; i<trnData.length; i++){
			double tmp1=0;
			trnData[i][0]=tmp[i][0]+"\n"+tmp[i][1];
			
			trnData[i][1]=dbu.getDisplayValue(tmp[i][2], 3)
			 +" "+tmp[i][9]+"\n"+dbu.getDisplayValue(tmp[i][3], 2);
			
			trnData[i][2]=dbu.getDisplayValue(tmp[i][4], 3)
			 +" "+tmp[i][9]+"\n"+dbu.getDisplayValue(tmp[i][5], 2);
			
			tmp1=Double.parseDouble(tmp[i][6])
				+Double.parseDouble(tmp[i][7]);
			String vatAmt=tmp1+"";
			trnData[i][3]=dbu.getDisplayValue(vatAmt, 2);
			
			trnData[i][4]=dbu.getDisplayValue(tmp[i][8], 2);
			
			
		
			//* convert to printable lines
			String[] lines=this.getLines(trnData[i]);
			String[] linesWithGap=new String[lines.length+1];
			for (int j = 0; j<lines.length; j++){
				linesWithGap[j]=lines[j];
			}
			linesWithGap[lines.length]="";
			
			//* print lines.
			printLines(linesWithGap);
		}
		
		
	}
	
	private void printTrnTotal(String trnNo){
		String[][] tmp=null;
		try{
			tmp=dbi.cmdSelect(
				"gdsJbTrnDesc",
				
				"gdsJbTrnDesc.amtGeneral, "+
				"gdsJbTrnDesc.amtExcisable, "+
				"gdsJbTrnDesc.amtVatGeneral, "+
				"gdsJbTrnDesc.amtVatExcisable, "+
				"gdsJbTrnDesc.amtED", 
				
				"gdsJbTrnDesc.trnNo='"+trnNo+"'"
			);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		String[] tmp1=new String[4];
		for (int i = 0; i<4; i++){
			tmp1[i]="0";
		}
		
		for (int i = 0; i<tmp.length; i++){
			tmp1[0]=Double.parseDouble(tmp1[0])
			       +Double.parseDouble(tmp[i][0])+"";
			       
			tmp1[1]=Double.parseDouble(tmp1[1])
			       +Double.parseDouble(tmp[i][1])+"";
			       
			tmp1[2]=Double.parseDouble(tmp1[2])
			       +Double.parseDouble(tmp[i][2])
			       +Double.parseDouble(tmp[i][3])+"";
			       
			tmp1[3]=Double.parseDouble(tmp1[3])
			       +Double.parseDouble(tmp[i][4])+"";
		}
		
		//* update report grand total.
		for (int i = 0; i<4; i++){
			this.reportTotal[i]=Double.parseDouble(this.reportTotal[i])
			                   +Double.parseDouble(tmp1[i])+"";
		}
		
		//* show grn total.
		String[] total=new String[5];
		if(this.trnType.compareTo("RQN")==0){
			total[0]="    REQUISITION   SLIP   TOTAL   :";
		}
		else {
			total[0]="      RETURN   SLIP   TOTAL      :";
		}
		
		inventorycontroller.util.DoubleUtil dbu=null;
		for (int i = 0; i<4; i++){
			total[i+1]="Rs. "+dbu.getDisplayValue(tmp1[i], 2);
		}
		
		String[] totalLines=this.getLines(total);
		
		
		String[] trnTotal=new String[3+totalLines.length];
		trnTotal[0]=getHorizLine("¯");
		for (int i = 0; i<totalLines.length; i++){
			trnTotal[i+1]=totalLines[i];
		}
		trnTotal[1+totalLines.length]=getHorizLine("¯");
		trnTotal[2+totalLines.length]="";
		
		//* print total to file.
		printLines(trnTotal);
	}
	
	private void printReportTotal(){
		String[] grandTotal=new String[7];
		
		inventorycontroller.util.DoubleUtil dbu=null;
		for (int i = 0; i<4; i++){
			this.reportTotal[i]="RS. "+dbu.getDisplayValue(this.reportTotal[i], 2);
		}
		String[] amtLines=this.getLines(new String[]{
			"G  R  A  N  D     T  O  T  A  L", 
			this.reportTotal[0],
			this.reportTotal[1],
			this.reportTotal[2],
			this.reportTotal[3],
		});
			 
		
		grandTotal[0]=getHorizLine("=");
		grandTotal[1]=getHorizLine("¯");
		grandTotal[2]="";
		grandTotal[3]=amtLines[0];
		grandTotal[4]="";
		grandTotal[5]=getHorizLine("¯");
		grandTotal[6]=getHorizLine("=");
		
		//* print total to file.
		printLines(grandTotal);
	}
	
	private void printReportFooter(){
		
		printLines(new String[]{
			"", 
			"Remarks:", 
			"", 
			""
		});
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
	private String trnType;
	private String[] trnNos;
	private String[] reportTotal;
	private inventorycontroller.function.DbInterface dbi;
	private java.io.File reportFile;
}