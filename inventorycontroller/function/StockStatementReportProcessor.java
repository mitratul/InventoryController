/*
 * StockStatementReportProcessor.java
 *
 * Created on September 27, 2007, 11:55 HRS
 * Last Modified on September 29, 2007, 19:16 HRS
 *
 */

package inventorycontroller.function;

/**
 *
 * @author  brinto
 */
public class StockStatementReportProcessor {
	
	public static final short REPORT_WIDTH=120;
	private static final int[] colWidth=new int[]{8, 40, 21, 21, 15, 15};
	
	private int linesPerPage=67;
	
	//* closing date is in raw format.
	public StockStatementReportProcessor(inventorycontroller.function.DbInterface dbi, String closingDate){
	 	
	 	this.linesPerPage=this.getLinesPerPage();
	 	this.printProcess=null;
	 	this.lineCount=0;
	 	this.closingDate=closingDate;
		this.dbi=dbi;
		this.matNos=new inventorycontroller.function.MaterialProcessor(this.dbi)
		 .getMatIds("1=1");
	 	this.reportTotal=new double[4];
	 	for (int i = 0; i<4; i++){
	 		this.reportTotal[i]=0;
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
		fileName="./temp/Stock_Stmt_Report#1.txt";
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
		return new int[]{0, 0, 1, 1, 1, 1};
	}
	
	public String[] getColumnNames(){
		return new String[]{
			"Sl.No.", 
			"Item Code\nDescription", 
			"General Qty.(unit)\nAmount (Rs.)", 
			"Excisable Qty.(unit)\nAmount (Rs.)", 
			"VAT (Rs)", 
			"Excise Duty (Rs)"
		};
		
	}
	
	/**
	 * methods to print to file.
	 */
	 
	public void generate(){
		printReportHeader();
		printStockHeader();
		printStockDataHeader();

		for (int i = 0; i<matNos.length; i++){
			printStockData(matNos[i], i);
		}
		
		printReportTotal();
		printReportFooter();
		
	}
	
	private void printReportHeader(){
		String[] reportHead=new String[9];
		//reportHead[0]="<<~h~>>";
		reportHead[0]="                             ============================================================";
		reportHead[1]="                             #                    * STOCK STATEMENT *                   #";
		reportHead[2]="                             #                      ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯                     #";
		reportHead[3]="                             #                                                          #";
		reportHead[4]="                             #         SREE CHAND ELECTRICAL INDUSTRIES PVT. LTD.       #";
		reportHead[5]="                             ============================================================";
		reportHead[6]="";
		reportHead[7]="";
		reportHead[8]="";
		//reportHead[0]="<<~~h~>>";
		
		this.printLines(reportHead);
		
	}
	
	private void printStockHeader(){
		String yr=inventorycontroller.util.DateUtil.getFinancialYear(this.closingDate);
		String date =inventorycontroller.util.DateUtil.getDisplayFormat(this.closingDate);
		
		this.printLines(new String[]{
			"FINANCIAL YEAR    "+yr, 
			"CLOSING DATE      "+date, 
			"", 
			""
		});
		
	}
	
	private void printStockDataHeader(){
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
	
	private void printStockData(String matNo, int slno){
		//Runtime.getRuntime().gc();
		String[] currentStock=new inventorycontroller.function.MaterialProcessor(this.dbi)
		 .getAmounts(matNo);//* format: stkGen, StkEx, VATGen, VATEx, ED, AmtGen, AmtEx
		
		String closingTSid=inventorycontroller.function.TimeStampProcessor
		 .getClosingTSid(this.closingDate, this.dbi);
		String grn[][]=null;
		
		try{
			grn=dbi.cmdSelect(
				"grnDesc",
				
				"grnDesc.TSid, "+
				"grnDesc.grnNo, "+
				"grnDesc.grnQty, "+
				"grnDesc.grnEdAmt, "+
				"grnDesc.grnVatAmt, "+
				"grnDesc.grnCSTAmt, "+
				"grnDesc.grnMatAmt", 
				
				"grnDesc.TSid>'"+closingTSid+"' and "+
				"grnDesc.matNo='"+matNo+"'"
			);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		// grn Format:         tsid, grnno, grnqty, ed, vat, cst, netamt
		// closing stk format: stkGen, StkEx, VATGen, VATEx, ED, AmtGen, AmtEx
		int ve=0;
		for (int i = 0; i<grn.length; i++){
			ve=grn[i][1].startsWith(inventorycontroller.function.GoodsReceiveNoteProcessor.GRN_ID)? -1: 1;
			
			if(Double.parseDouble(grn[i][3])>0){
				currentStock[1]=Double.parseDouble(currentStock[1])
				               +ve*Double.parseDouble(grn[i][2])+"";
				currentStock[3]=Double.parseDouble(currentStock[3])
				               +ve*(Double.parseDouble(grn[i][4])+Double.parseDouble(grn[i][5]))+"";
				currentStock[4]=Double.parseDouble(currentStock[4])
				               +ve*Double.parseDouble(grn[i][3])+"";
				currentStock[6]=Double.parseDouble(currentStock[6])
				               +ve*Double.parseDouble(grn[i][6])+"";
			}
			else {
				currentStock[0]=Double.parseDouble(currentStock[0])
				               +ve*Double.parseDouble(grn[i][2])+"";
				currentStock[2]=Double.parseDouble(currentStock[2])
				               +ve*(Double.parseDouble(grn[i][4])+Double.parseDouble(grn[i][5]))+"";
				currentStock[5]=Double.parseDouble(currentStock[5])
				               +ve*Double.parseDouble(grn[i][6])+"";
			}
		}
		
		String rqn[][]=null;
		
		try{
			rqn=dbi.cmdSelect(
				
				"gdsJbTrnDesc",
				
				"gdsJbTrnDesc.trnNo, "+
				"gdsJbTrnDesc.qtyGeneral, "+
				"gdsJbTrnDesc.amtGeneral, "+
				"gdsJbTrnDesc.qtyExcisable, "+
				"gdsJbTrnDesc.amtExcisable, "+
				"gdsJbTrnDesc.amtVatGeneral, "+
				"gdsJbTrnDesc.amtVatExcisable, "+
				"gdsJbTrnDesc.amtED, "+
				"gdsJbTrnDesc.TSid", 
				
				"gdsJbTrnDesc.TSid>'"+closingTSid+"' and "+
				"gdsJbTrnDesc.matNo='"+matNo+"'"
			);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		// rqn Format:         trnno, genqty, genamt, exqty, examt, genvat, exvat, ed
		// closing stk format: stkGen, StkEx, VATGen, VATEx, ED, AmtGen, AmtEx
		for (int i = 0; i<rqn.length; i++){
			ve=rqn[i][0]
			 .startsWith(inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_RETURN_ID)
			 	? -1: 1;
			
			currentStock[0]=Double.parseDouble(currentStock[0])
			               +ve*Double.parseDouble(rqn[i][1])+"";
			currentStock[1]=Double.parseDouble(currentStock[1])
			               +ve*Double.parseDouble(rqn[i][3])+"";
			currentStock[2]=Double.parseDouble(currentStock[2])
			               +ve*Double.parseDouble(rqn[i][5])+"";
			currentStock[3]=Double.parseDouble(currentStock[3])
			               +ve*Double.parseDouble(rqn[i][6])+"";
			currentStock[4]=Double.parseDouble(currentStock[4])
			               +ve*Double.parseDouble(rqn[i][7])+"";
			currentStock[5]=Double.parseDouble(currentStock[5])
			               +ve*Double.parseDouble(rqn[i][2])+"";
			currentStock[6]=Double.parseDouble(currentStock[6])
			               +ve*Double.parseDouble(rqn[i][4])+"";
		}
		
		
		this.reportTotal[0]+=Double.parseDouble(currentStock[5]);
		
		this.reportTotal[1]+=Double.parseDouble(currentStock[6]);
		
		this.reportTotal[2]+=Double.parseDouble(currentStock[2])
		                    +Double.parseDouble(currentStock[3]);
		
		this.reportTotal[3]+=Double.parseDouble(currentStock[4]);
		
		
		Object[][] matnames=new inventorycontroller.function.MaterialProcessor(this.dbi)
		 .getMatNamesForPo("matMaster.matId='"+matNo+"'");
		
		inventorycontroller.util.DoubleUtil dbu=null;
		String genq=currentStock[0];
		String gena=currentStock[5];
		String exq=currentStock[1];
		String exa=currentStock[6];
		String vat=(Double.parseDouble(currentStock[2])+Double.parseDouble(currentStock[3]))+"";
		String ed=currentStock[4];
		
		genq=dbu.getDisplayValue(genq, 3);
		gena=dbu.getDisplayValue(gena, 2);
		exq=dbu.getDisplayValue(exq, 3);
		exa=dbu.getDisplayValue(exa, 2);
		vat=dbu.getDisplayValue(vat, 2);
		ed=dbu.getDisplayValue(ed, 2);
		
		String[] lines=getLines(new String[]{
			(slno+1)+"", 
			matNo+"\n"+matnames[0][1].toString(), 
			genq+" "+matnames[0][3].toString()+"\nRs. "+gena, 
			exq+" "+matnames[0][3].toString()+"\nRs. "+exa, 
			"Rs. "+vat, 
			"Rs. "+ed
		});
		
		printLines(lines);
		printLines(new String[]{""});
	}
	
	private void printReportTotal(){
		String gena=this.reportTotal[0]+"";
		String exa=this.reportTotal[1]+"";
		String vat=this.reportTotal[2]+"";
		String ed=this.reportTotal[3]+"";
		
		inventorycontroller.util.DoubleUtil dbu=null;
		
		gena=dbu.getDisplayValue(gena, 2);
		exa=dbu.getDisplayValue(exa, 2);
		vat=dbu.getDisplayValue(vat, 2);
		ed=dbu.getDisplayValue(ed, 2);
		
		String[] totals=getLines(new String[]{
			"", 
			"T O T A L    A M O U N T S  ::", 
			"Rs. "+gena, 
			"Rs. "+exa, 
			"Rs. "+vat, 
			"Rs. "+ed
		});
		String[] stkTotal=new String[4+totals.length];
		stkTotal[0]=getHorizLine("=");
		stkTotal[1]=getHorizLine("¯");
		for (int i = 0; i<totals.length; i++){
			stkTotal[2+i]=totals[i];
		}
		stkTotal[stkTotal.length-2]=getHorizLine("¯");
		stkTotal[stkTotal.length-1]=getHorizLine("=");
		
		printLines(stkTotal);
		
	}
	
	private void printReportFooter(){
		printLines(new String[]{
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
					this.printStockDataHeader(); //* header @ top of page.
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
	
	/*public static void main(String[] args){
		inventorycontroller.function.DbInterface dbInterface1=null;
        try{
        	dbInterface1=new inventorycontroller.function.DbInterface("./db/db.mdb", "Microsoft Access Driver (*.mdb)");
        }
        catch(Exception ex){
        	ex.printStackTrace();
        }
        StockStatementReportProcessor ssrp=new StockStatementReportProcessor(dbInterface1, "20071004");
        
        ssrp.generate();
	}*/
	
	
	
	private String closingDate;
	private Process printProcess;
	private int lineCount;
	private String[] matNos;
	private double[] reportTotal;
	private inventorycontroller.function.DbInterface dbi;
	private java.io.File reportFile;
}