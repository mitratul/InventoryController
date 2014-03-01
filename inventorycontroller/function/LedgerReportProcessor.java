/*
 * LedgerReportProcessor.java
 *
 * Created on October 05, 2007, 21:55 HRS
 * Last Modified on October 06, 2007, 09:16 HRS
 *
 */

package inventorycontroller.function;

/**
 *
 * @author  brinto
 */
public class LedgerReportProcessor {
	
	public static final short REPORT_WIDTH=196;
	private static final int[] colWidth=new int[]{12, 25, 25, 15, 12, 17, 15, 12, 17, 16, 12, 18};
	
	private int linesPerPage=67;
	
	//* opening, closing date is in raw format.
	public LedgerReportProcessor(inventorycontroller.function.DbInterface dbi, 
	 String openingDate, String closingDate, boolean isExcisable, String[] matNos){
	 	
	 	this.isExcisable=isExcisable;
	 	this.linesPerPage=this.getLinesPerPage();
	 	this.printProcess=null;
	 	this.lineCount=0;
	 	this.openingDate=openingDate;
	 	this.closingDate=closingDate;
		this.dbi=dbi;
		this.matNos=matNos;
	 	this.reportTotal=new double[2]; //* {qty, amt}
		this.reportFile=allocateReportFile();
		this.TSidSorter=new java.util.TreeSet<String>();
		this.map=new java.util.Vector<String>();
		this.tranDet=new java.util.Vector<java.util.Vector<String>>();

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
		fileName="./temp/Ledger_Report#1.txt";
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
		return new int[]{0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1};
	}
	
	public String[] getColumnNames(){
		return new String[]{
			"Date", 
			"Particulars", 
			"Voucher No.", 
			"Quantity (units)", 
			"Rate (Rs/unit)", 
			"Amount (Rs)", 
			"Quantity (units)", 
			"Rate (Rs/unit)", 
			"Amount (Rs)", 
			"Quantity (units)", 
			"Rate (Rs/unit)", 
			"Amount (Rs)"
		};
		
	}
	
	/**
	 * methods to print to file.
	 */
	 
	public void generate(){

		for (int i = 0; i<matNos.length; i++){
			this.TSidSorter.clear();
			this.map.clear();
			this.tranDet.clear();
			this.lineCount=0;
		 	
			printReportHeader();
			printLedgerHeader(matNos[i]);
			printLedgerDataHeader();
			printLedgerData(matNos[i]);
			printReportFooter();
		}
		
	}
	
	private void printReportHeader(){
		String[] reportHead=new String[9];
		//reportHead[0]="<<~h~>>";
		reportHead[0]="                                                                    ============================================================";
		if(this.isExcisable){
		reportHead[1]="                                                                    #                   * EXCISABLE LEDGER *                   #";
		reportHead[2]="                                                                    #                     ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯                     #";
		}
		else{
		reportHead[1]="                                                                    #                    * GENERAL LEDGER *                    #";
		reportHead[2]="                                                                    #                      ¯¯¯¯¯¯¯¯¯¯¯¯¯¯                      #";
		}
		reportHead[3]="                                                                    #                                                          #";
		reportHead[4]="                                                                    #         SREE CHAND ELECTRICAL INDUSTRIES PVT. LTD.       #";
		reportHead[5]="                                                                    ============================================================";
		reportHead[6]="";
		reportHead[7]="";
		reportHead[8]="";
		//reportHead[0]="<<~~h~>>";
		
		this.printLines(reportHead);
		
	}
	
	private void printLedgerHeader(String matNo){
		String odate =inventorycontroller.util.DateUtil.getDisplayFormat(this.openingDate);
		String cdate =inventorycontroller.util.DateUtil.getDisplayFormat(this.closingDate);
		Object matDet[][]=new inventorycontroller.function.MaterialProcessor(this.dbi)
		 .getMatNamesForPo("matMaster.matId='"+matNo+"'");
		
		this.printLines(new String[]{
			"MATERIAL NO.     "+matNo, 
			"MATERIAL NAME    "+matDet[0][1].toString(), 
			"MATERIAL UNIT    "+matDet[0][3].toString(), 
			"", 
			"LEDGER FROM DATE "+odate+"    TO DATE "+cdate, 
			"", 
			""
		});
		
	}
	
	private void printLedgerDataHeader(){
		//* initialise.
		String[] colNames=getColumnNames();
		
		//* convert to lines, printable in grn report.
		String[] lines=getLines(colNames);
		String[] dataHeaderBlock=new String[lines.length+2];
		
		dataHeaderBlock[0]=
			"                                                              "+
			"|              R E C E I P T                "+
			"|                I S S U E                  "+
			"|               B A L A N C E                 ";
		for (int i = 0; i<lines.length; i++){
			dataHeaderBlock[i+1]=lines[i];
		}
		dataHeaderBlock[lines.length+1]=getHorizLine("-");
		
		//* print header lines.
		printLines(dataHeaderBlock);
		
		
	}
	
	private void printLedgerData(String matNo){
	 	for (int i = 0; i<2; i++){ //* initialise to zero, for each ledger.
	 		this.reportTotal[i]=0;
	 	}
	 	getOpeningStock(matNo);
	 	
	 	/** 
	 	 *tranDet format: {voucherNo, qty, amt, date, particulars}
	 	 */
	 	inventorycontroller.util.DateUtil du=null;
	 	inventorycontroller.util.DoubleUtil dbu=null;
	 	
	 	String bQty=dbu.getDisplayValue(this.reportTotal[0], 3);
	 	String bRate=getRate(this.reportTotal[0], this.reportTotal[1]);
	 	String bAmt=dbu.getDisplayValue(this.reportTotal[1], 2);
	 	
	 	String lines[]=this.getLines(new String[]{
	 		du.getDisplayFormat(this.openingDate), 
	 		"OPENING  BALANCE", 
	 		"",
	 		 
	 		"", "", "", 
	 		
	 		"", "", "", 
	 		
	 		bQty, bRate, bAmt
	 	});
	 	
	 	this.printLines(lines);
	 	this.printLines(new String[]{""});
	 	
	 	String date="";
	 	String particular="";
	 	String voucher="";
	 	String rQty="";
	 	String rRate="";
	 	String rAmt="";
	 	String iQty="";
	 	String iRate="";
	 	String iAmt="";
	 	
	 	int ptr=0;
	 	int length=this.TSidSorter.size();
	 	
	 	for (int i = 0; i<length; i++){
	 		ptr=this.map.indexOf(this.TSidSorter.pollFirst()); //* get the sorted order of trn
	 		/** 
		 	 *tranDet row format: {voucherNo, qty, amt, date, particulars}
		 	 */
	 		date=du.getDisplayFormat(this.tranDet.elementAt(ptr).elementAt(3));
	 		particular=this.tranDet.elementAt(ptr).elementAt(4);
	 		voucher=this.tranDet.elementAt(ptr).elementAt(0);
	 		
	 		if(
	 			voucher.startsWith(inventorycontroller.function.GoodsReceiveNoteProcessor.GRN_ID) ||
	 			voucher.startsWith(inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_RETURN_ID)
	 		  ){ //* +ve entry in receipt side.
	 		  	rQty=dbu.getDisplayValue(this.tranDet.elementAt(ptr).elementAt(1), 3);
	 		  	rRate=getRate(
	 		  		Double.parseDouble(this.tranDet.elementAt(ptr).elementAt(1)), 
	 		  		Double.parseDouble(this.tranDet.elementAt(ptr).elementAt(2))
	 		  	);
	 		  	rAmt=dbu.getDisplayValue(this.tranDet.elementAt(ptr).elementAt(2), 2);
	 		  	iQty="";
	 		  	iRate="";
	 		  	iAmt="";
	 		  	//if rate == 0 ie. qty &/ amt 0 then dont print.
	 		  	if(rRate.length()==0){
	 		  		continue;
	 		  	}
	 		  	
	 		  	//* now update balance
	 		  	this.reportTotal[0]+=Double.parseDouble(this.tranDet.elementAt(ptr).elementAt(1));
	 		  	this.reportTotal[1]+=Double.parseDouble(this.tranDet.elementAt(ptr).elementAt(2));
	 		  	
	 		  	//* show balance.
  			 	bQty=dbu.getDisplayValue(this.reportTotal[0], 3);
			 	bRate=getRate(this.reportTotal[0], this.reportTotal[1]);
			 	bAmt=dbu.getDisplayValue(this.reportTotal[1], 2);
	 		}
	 		else if(
	 			voucher.startsWith(inventorycontroller.function.GoodsReceiveNoteProcessor.GRN_CANCELLATION_ID)
	 		       ){ //* -ve entry in receipt side.
	 			rQty="-"+dbu.getDisplayValue(this.tranDet.elementAt(ptr).elementAt(1), 3);
	 		  	rRate=getRate(
	 		  		Double.parseDouble(this.tranDet.elementAt(ptr).elementAt(1)), 
	 		  		Double.parseDouble(this.tranDet.elementAt(ptr).elementAt(2))
	 		  	);
	 		  	rAmt="-"+dbu.getDisplayValue(this.tranDet.elementAt(ptr).elementAt(2), 2);
	 		  	iQty="";
	 		  	iRate="";
	 		  	iAmt="";
	 		  	
	 		  	//* now update balance
	 		  	this.reportTotal[0]-=Double.parseDouble(this.tranDet.elementAt(ptr).elementAt(1));
	 		  	this.reportTotal[1]-=Double.parseDouble(this.tranDet.elementAt(ptr).elementAt(2));
	 		  	
  			 	//* show balance.
  			 	bQty=dbu.getDisplayValue(this.reportTotal[0], 3);
			 	bRate=getRate(this.reportTotal[0], this.reportTotal[1]);
			 	bAmt=dbu.getDisplayValue(this.reportTotal[1], 2);
	 		}
	 		else if(
	 			voucher.startsWith(inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_REQUISITION_ID) ||
	 			voucher.startsWith(inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_EXCESS_REQUISITION_ID)
	 		       ){ //* +ve entry in issue side.
	 			iQty=dbu.getDisplayValue(this.tranDet.elementAt(ptr).elementAt(1), 3);
	 		  	iRate=getRate(
	 		  		Double.parseDouble(this.tranDet.elementAt(ptr).elementAt(1)), 
	 		  		Double.parseDouble(this.tranDet.elementAt(ptr).elementAt(2))
	 		  	);
	 		  	iAmt=dbu.getDisplayValue(this.tranDet.elementAt(ptr).elementAt(2), 2);
	 		  	rQty="";
	 		  	rRate="";
	 		  	rAmt="";
	 		  	//if rate == 0 ie. qty &/ amt 0 then dont print.
	 		  	if(iRate.length()==0){
	 		  		continue;
	 		  	}
	 		  	
	 		  	//* now update balance
	 		  	this.reportTotal[0]-=Double.parseDouble(this.tranDet.elementAt(ptr).elementAt(1));
	 		  	this.reportTotal[1]-=Double.parseDouble(this.tranDet.elementAt(ptr).elementAt(2));
	 		  	
  			 	//* show balance.
  			 	bQty=dbu.getDisplayValue(this.reportTotal[0], 3);
			 	bRate=getRate(this.reportTotal[0], this.reportTotal[1]);
			 	bAmt=dbu.getDisplayValue(this.reportTotal[1], 2);
	 		}
	 		
	 		//* now print this record in file.
	 		lines=getLines(new String[]{
	 			date, particular, voucher, 
	 			rQty, rRate, rAmt, 
	 			iQty, iRate, iAmt, 
	 			bQty, bRate, bAmt
	 		});
	 		
	 		printLines(lines);
	 		printLines(new String[]{""});
	 	} //* one record written to file.
	 	//* print closing balance:
	 	lines=this.getLines(new String[]{
	 		du.getDisplayFormat(this.closingDate), 
	 		"CLOSING  BALANCE", 
	 		"",
	 		 
	 		"", "", "", 
	 		
	 		"", "", "", 
	 		
	 		bQty, bRate, bAmt
	 	});
	 	
	 	this.printLines(lines);
	 	this.printLines(new String[]{""});
	 	
	 	
	 	//* print horiz line.
	 	printLines(new String[]{getHorizLine("-")});
	}
	
	private String getRate(double qty, double amt){
		if(inventorycontroller.util.DoubleUtil.getDisplayValue(qty, 2).compareTo("0.0")==0){
			return "";
		}
		if(inventorycontroller.util.DoubleUtil.getDisplayValue(amt, 2).compareTo("0.0")==0){
			return "";
		}
		double rate=amt/qty;
		return inventorycontroller.util.DoubleUtil.getDisplayValue(rate, 2);
		
	}
	
	private void getOpeningStock(String matNo){
		Runtime.getRuntime().gc();
		String[] currentStock=new inventorycontroller.function.MaterialProcessor(this.dbi)
		 .getAmounts(matNo);//* format: stkGen, StkEx, VATGen, VATEx, ED, AmtGen, AmtEx
		
		String openingTSid=inventorycontroller.function.TimeStampProcessor
		 .getOpeningTSid(this.openingDate, this.dbi);
		String grn[][]=null;
		
		String grnCond="(not grnDesc.TSid<'"+openingTSid+"') and "+
				    "grnDesc.matNo='"+matNo+"' and ";
		grnCond=this.isExcisable? 
			(grnCond+"grnDesc.grnEdAmt>'0.00'"): 
			(grnCond+"( not grnDesc.grnEdAmt>'0.00')");
		
		try{
			grn=dbi.cmdSelect(
				"grnDesc, grnMaster, poMaster",
				
				"grnDesc.TSid, "+
				"grnDesc.grnNo, "+
				"grnDesc.grnQty, "+
				"grnDesc.grnMatAmt, "+
				"grnMaster.grnDate, "+
				"poMaster.vndNo", 
				
				grnCond+" and "+
				"grnMaster.grnNo=grnDesc.grnNo and "+
				"poMaster.poNo=grnMaster.poNo"
			);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		// grn Format:         tsid, grnno, grnqty, netamt, date, particular
		// closing stk format: stkGen, StkEx, VATGen, VATEx, ED, AmtGen, AmtEx
		int ve=0;
		java.util.Vector<String> v=null;
		for (int i = 0; i<grn.length; i++){
			
			if(this.closingDate.compareTo(grn[i][4])>=0){ //* if within ledger date range.
				this.TSidSorter.add(grn[i][0]);
				this.map.add(grn[i][0]);
				v=new java.util.Vector<String>();
				v.add(grn[i][1]);
				v.add(grn[i][2]);
				v.add(grn[i][3]);
				v.add(grn[i][4]);
				v.add(grn[i][5]);
				this.tranDet.add(v);
			}
			
			ve=grn[i][1].startsWith(inventorycontroller.function.GoodsReceiveNoteProcessor.GRN_ID)
				? -1: 1;
			
			if(this.isExcisable){
				currentStock[1]=Double.parseDouble(currentStock[1])
				               +ve*Double.parseDouble(grn[i][2])+"";
				currentStock[6]=Double.parseDouble(currentStock[6])
				               +ve*Double.parseDouble(grn[i][3])+"";
			}
			else {
				currentStock[0]=Double.parseDouble(currentStock[0])
				               +ve*Double.parseDouble(grn[i][2])+"";
				currentStock[5]=Double.parseDouble(currentStock[5])
				               +ve*Double.parseDouble(grn[i][3])+"";
			}
		}
		
		String rqn[][]=null;
		String trnFlds="";
		
		if(this.isExcisable){
			trnFlds="gdsJbTrnDesc.TSid, "+
					"gdsJbTrnDesc.trnNo, "+
					"gdsJbTrnDesc.qtyExcisable, "+
					"gdsJbTrnDesc.amtExcisable";
		}
		else {
			trnFlds="gdsJbTrnDesc.TSid, "+
					"gdsJbTrnDesc.trnNo, "+
					"gdsJbTrnDesc.qtyGeneral, "+
					"gdsJbTrnDesc.amtGeneral";
		}
		
		try{
			rqn=dbi.cmdSelect(
				
				"gdsJbTrnDesc, gdsJbTrnMaster",
				
				trnFlds+", "+
				"gdsJbTrnMaster.trnDate, "+
				"gdsJbTrnMaster.jbNo", 
				
				"(not gdsJbTrnDesc.TSid<'"+openingTSid+"') and "+
				"gdsJbTrnDesc.matNo='"+matNo+"' and "+
				"gdsJbTrnMaster.trnNo=gdsJbTrnDesc.trnNo"
			);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		// rqn Format:         TSid, trnno, genqty / exqty, genamt / examt, date, particular
		// closing stk format: stkGen, StkEx, VATGen, VATEx, ED, AmtGen, AmtEx
		for (int i = 0; i<rqn.length; i++){
			
			if(this.closingDate.compareTo(rqn[i][4])>=0){ //* if within ledger date range
				this.TSidSorter.add(rqn[i][0]);
				this.map.add(rqn[i][0]);
				v=new java.util.Vector<String>();
				v.add(rqn[i][1]);
				v.add(rqn[i][2]);
				v.add(rqn[i][3]);
				v.add(rqn[i][4]);
				v.add(rqn[i][5]);
				this.tranDet.add(v);
			}
			
			ve=rqn[i][1]
			 .startsWith(inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_RETURN_ID)
			 	? -1: 1;
			if(this.isExcisable){
				currentStock[1]=Double.parseDouble(currentStock[1])
				               +ve*Double.parseDouble(rqn[i][2])+"";
				currentStock[6]=Double.parseDouble(currentStock[6])
				               +ve*Double.parseDouble(rqn[i][3])+"";
			}
			else {
				currentStock[0]=Double.parseDouble(currentStock[0])
				               +ve*Double.parseDouble(rqn[i][2])+"";
				currentStock[5]=Double.parseDouble(currentStock[5])
				               +ve*Double.parseDouble(rqn[i][3])+"";
			}
		}
		
		if(this.isExcisable){
			this.reportTotal[0]+=Double.parseDouble(currentStock[1]);
			this.reportTotal[1]+=Double.parseDouble(currentStock[6]);
		}
		else {
			this.reportTotal[0]+=Double.parseDouble(currentStock[0]);
			this.reportTotal[1]+=Double.parseDouble(currentStock[5]);
		}
		
		
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
					this.printLedgerDataHeader(); //* header @ top of page.
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
	
	/*public static void main(String[] args){
		java.util.TreeSet<String> s=new java.util.TreeSet<String>();
		s.add("ts/000034/07-08");
		s.add("ts/000056/07-08");
		s.add("ts/000008/07-08");
		s.add("ts/000000/07-08");
		s.add("ts/000904/07-08");
		s.add("ts/000164/07-08");
		s.add("ts/000032/07-08");
		s.add("ts/000234/07-08");
		s.add("ts/000045/07-08");
		s.add("ts/000022/07-08");
		
		int l=s.size();
		for (int i = 0; i<l; i++){
			System.out.println (s.pollFirst());
		}
		

	}*/
	
	
	
	private String openingDate;
	private String closingDate;
	private boolean isExcisable;
	private Process printProcess;
	private int lineCount;
	private String[] matNos;
	private double[] reportTotal;
	private inventorycontroller.function.DbInterface dbi;
	private java.io.File reportFile;
	
	private java.util.TreeSet<String> TSidSorter;
	private java.util.Vector<String> map;
	private java.util.Vector<java.util.Vector<String>> tranDet;
}
