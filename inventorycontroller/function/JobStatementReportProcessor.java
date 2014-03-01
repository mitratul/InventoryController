/*
 * JobStatementReportProcessor.java
 *
 * Created on October 06, 2007, 16:55 HRS
 * Last Modified on October 07, 2007, 13:03 HRS
 *
 */

package inventorycontroller.function;

/**
 *
 * @author  brinto
 */
public class JobStatementReportProcessor {
	
	public static final short REPORT_WIDTH=120;
	private static final int[] colWidth=new int[]{33, 15, 15, 15, 15, 13, 14};
	
	private int linesPerPage=67;
	
	//* opening, closing date is in raw format.
	public JobStatementReportProcessor(inventorycontroller.function.DbInterface dbi, 
	 String[] jbNos, boolean isFinished){
	 	
	 	this.linesPerPage=this.getLinesPerPage();
	 	this.printProcess=null;
	 	this.lineCount=0;
		this.dbi=dbi;
		this.jbNos=jbNos;
		this.isFinished=isFinished;
	 	this.reportTotal=new double[3]; //* {amt, vat, ed}
	 	this.currentAmt=new double[2]; //* {currentYrIssue, currentYrReturn}
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
		fileName="./temp/JS_Report#1.txt";
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
	
	public int[] getAlignment(){
		return new int[]{0, 1, 1, 1, 1, 1, 1};
	}
	
	public String[] getColumnNames(){
		return new String[]{
			"Job No.\nDetails", 
			"Opening W.I.P.", 
			"Issue During Current Year (Rs)", 
			"Return During Current Year (Rs)", 
			"Total Amount (Rs)", 
			"VAT / CST (Rs)", 
			"Excise Duty (Rs)"
		};
		
	}
	
	/**
	 * methods to print to file.
	 */
	 
	public void generate(){
		
		this.lineCount=0;
		printReportHeader();
		printJSHeader();
		printJSDataHeader();

		for (int i = 0; i<jbNos.length; i++){
		 	
			printJSData(jbNos[i]);
		}

		printReportFooter();
		
	}
	
	private void printReportHeader(){
		String[] reportHead=new String[9];
		//reportHead[0]="<<~h~>>";
		reportHead[0]="                                   ============================================================";
		if(this.isFinished){
		reportHead[1]="                                   #                    * FINISHED GOODS *                    #";
		reportHead[2]="                                   #                      ¯¯¯¯¯¯¯¯¯¯¯¯¯¯                      #";
		}
		else {
		reportHead[1]="                                   #                   * WORKS IN PROGRESS *                  #";
		reportHead[2]="                                   #                     ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯                    #";
		}
		reportHead[3]="                                   #                                                          #";
		reportHead[4]="                                   #         SREE CHAND ELECTRICAL INDUSTRIES PVT. LTD.       #";
		reportHead[5]="                                   ============================================================";
		reportHead[6]="";
		reportHead[7]="";
		reportHead[8]="";
		//reportHead[0]="<<~~h~>>";
		
		this.printLines(reportHead);
		
	}
	
	private void printJSHeader(){
		String date=inventorycontroller.function.DateProcessor
		 .getCurrentDate(this.dbi);
		String yr=inventorycontroller.util.DateUtil.getFinancialYear(date);
		date=inventorycontroller.util.DateUtil.getDisplayFormat(date);

		this.printLines(new String[]{
			"FINANCIAL YEAR  "+yr, 
			"REPORT DATE     "+date, 
			"", 
			""
		});
		
	}
	
	private void printJSDataHeader(){
		//* initialise.
		String[] colNames=getColumnNames();
		
		//* convert to lines, printable in grn report.
		String[] lines=getLines(colNames);
		String[] dataHeaderBlock=new String[lines.length+1];
		
		for (int i = 0; i<lines.length; i++){
			dataHeaderBlock[i]=lines[i];
		}
		dataHeaderBlock[dataHeaderBlock.length-1]=getHorizLine("-");
		
		//* print header lines.
		printLines(dataHeaderBlock);
		
		
	}
	
	private void printJSData(String jbNo){
	 	for (int i = 0; i<reportTotal.length; i++){ //* initialise to zero, for each job.
	 		this.reportTotal[i]=0;
	 	}
	 	for (int i = 0; i<currentAmt.length; i++){ //* initialise to zero, for each job.
	 		this.currentAmt[i]=0;
	 	}
	 	String[][] jbDet=null;
	 	try{
	 		jbDet=dbi.cmdSelect(
	 			"jbMaster, prdMaster", 
				
				"jbMaster.jbStDate, "+
				"jbMaster.jbEndDate, "+
				"jbMaster.prdNo, "+
				"prdMaster.prdName, "+
				"jbMaster.quantity", 
				
				"jbMaster.jbNo='"+jbNo+"' and "+
				"prdMaster.prdId=jbMaster.prdNo"
	 		);
	 	}
		catch(Exception ex){
			ex.printStackTrace();
		}
		//* job description.
	 	String jd=jbNo+"\nProduct: "+jbDet[0][2]+" - "+jbDet[0][3]+
	 		", Qty: "+jbDet[0][4]+", Start date: "+
	 		inventorycontroller.util.DateUtil.getDisplayFormat(jbDet[0][0]);
	 	if(this.isFinished){
	 		jd=jd+", End date: "+inventorycontroller.util.DateUtil.getDisplayFormat(jbDet[0][1]);
	 	}
	 	
	 	getOpeningStock(jbNo);
	 	//* opening wip.
	 	String openAmt=inventorycontroller.util.DoubleUtil.getDisplayValue(this.reportTotal[0], 2);
	 	
	 	getClosingStock(jbNo);
	 	//* current yr issue / return.
	 	String curIssue=inventorycontroller.util.DoubleUtil.getDisplayValue(this.currentAmt[0], 2);
	 	String curReturn=inventorycontroller.util.DoubleUtil.getDisplayValue(this.currentAmt[1], 2);
	 	
	 	//* closing figures.
	 	String closeAmt=inventorycontroller.util.DoubleUtil.getDisplayValue(this.reportTotal[0], 2);
	 	String closeVat=inventorycontroller.util.DoubleUtil.getDisplayValue(this.reportTotal[1], 2);
	 	String closeEd=inventorycontroller.util.DoubleUtil.getDisplayValue(this.reportTotal[2], 2);
	 	
	 	
	 	String[] lines=getLines(new String[]{
	 		jd, openAmt, curIssue, curReturn, closeAmt, closeVat, closeEd
	 	});
	 	
	 	printLines(lines);
	 	printLines(new String[]{""});
	 	
	}
	
	private void getOpeningStock(String jbNo){
		String fyr=inventorycontroller.util.DateUtil
		 .getFinancialYear(
		 	inventorycontroller.function.DateProcessor.getCurrentDate(dbi)
		 );
		String[][] rqn=null;
		try{
			rqn=dbi.cmdSelect(
				"gdsJbTrnDesc, gdsJbTrnMaster",
				
				"gdsJbTrnDesc.trnNo, "+
				"gdsJbTrnDesc.amtGeneral, "+
				"gdsJbTrnDesc.amtExcisable, "+
				"gdsJbTrnDesc.amtVatGeneral, "+
				"gdsJbTrnDesc.amtVatExcisable, "+
				"gdsJbTrnDesc.amtED", 
				
				"gdsJbTrnMaster.jbNo='"+jbNo+"' and "+
				"gdsJbTrnDesc.trnNo=gdsJbTrnMaster.trnNo and "+
				"gdsJbTrnDesc.TSid not like '%"+fyr+"'"
			);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		double amt=0;
		double vat=0;
		double ed=0;
		for (int i = 0; i<rqn.length; i++){
			amt=Double.parseDouble(rqn[i][1])+Double.parseDouble(rqn[i][2]);
			vat=Double.parseDouble(rqn[i][3])+Double.parseDouble(rqn[i][4]);
			ed=Double.parseDouble(rqn[i][5]);
			
			if(rqn[i][0].startsWith(inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_RETURN_ID)){
				this.reportTotal[0]-=amt;
				this.reportTotal[1]-=vat;
				this.reportTotal[2]-=ed;
			}
			else {
				this.reportTotal[0]+=amt;
				this.reportTotal[1]+=vat;
				this.reportTotal[2]+=ed;
			}
		}
	}
	
	private void getClosingStock(String jbNo){
		String fyr=inventorycontroller.util.DateUtil
		 .getFinancialYear(
		 	inventorycontroller.function.DateProcessor.getCurrentDate(dbi)
		 );
		String[][] rqn=null;
		try{
			rqn=dbi.cmdSelect(
				"gdsJbTrnDesc, gdsJbTrnMaster",
				
				"gdsJbTrnDesc.trnNo, "+
				"gdsJbTrnDesc.amtGeneral, "+
				"gdsJbTrnDesc.amtExcisable, "+
				"gdsJbTrnDesc.amtVatGeneral, "+
				"gdsJbTrnDesc.amtVatExcisable, "+
				"gdsJbTrnDesc.amtED", 
				
				"gdsJbTrnMaster.jbNo='"+jbNo+"' and "+
				"gdsJbTrnDesc.trnNo=gdsJbTrnMaster.trnNo and "+
				"gdsJbTrnDesc.TSid like '%"+fyr+"'"
			);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		double amt=0;
		double vat=0;
		double ed=0;
		for (int i = 0; i<rqn.length; i++){
			amt=Double.parseDouble(rqn[i][1])+Double.parseDouble(rqn[i][2]);
			vat=Double.parseDouble(rqn[i][3])+Double.parseDouble(rqn[i][4]);
			ed=Double.parseDouble(rqn[i][5]);
			
			if(rqn[i][0].startsWith(inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_RETURN_ID)){
				this.reportTotal[0]-=amt;
				this.reportTotal[1]-=vat;
				this.reportTotal[2]-=ed;
				
				this.currentAmt[1]+=amt;
			}
			else {
				this.reportTotal[0]+=amt;
				this.reportTotal[1]+=vat;
				this.reportTotal[2]+=ed;
				
				this.currentAmt[0]+=amt;
			}
		}
	}
	
	private void printReportFooter(){
		printLines(new String[]{
			getHorizLine("_"), 
			"", 
			"Remarks:"
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
	
	//* need data header on top of each page other than the 1st.
	private void printLines(String[] lines){
		try{
			java.io.FileWriter fw=null;
			java.io.PrintWriter pw=null;
			
			for (int i = 0; i<lines.length; i++){
				
				if(this.lineCount%this.linesPerPage==0 && this.lineCount!=0){
					this.printJSDataHeader(); //* header @ top of page.
				}
				
				fw=new java.io.FileWriter(this.reportFile, true);
				pw=new java.io.PrintWriter(fw, true);
				
				pw.println(lines[i]);
				this.lineCount++;
				
				pw.close();
				fw.close();

			}
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void initPrint(javax.swing.JDesktopPane parent){
		
        int choice=javax.swing.JOptionPane.showInternalConfirmDialog(
        	parent,
            "<html><center>\"You need "+
            "15\" paper & your Printer in CONDENSED mode<br>"+
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
	
		

	
	
	
	private double[] currentAmt;
	private Process printProcess;
	private int lineCount;
	private String[] jbNos;
	private double[] reportTotal;
	private inventorycontroller.function.DbInterface dbi;
	private java.io.File reportFile;
	private boolean isFinished;

}
