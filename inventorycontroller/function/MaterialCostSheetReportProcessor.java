/*
 * MaterialCostSheetReportProcessor.java
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
public class MaterialCostSheetReportProcessor {
	
	public static final short REPORT_WIDTH=130;
	private static final int[] colWidth=new int[]{32, 25, 11, 14, 12, 15, 10, 11};
	
	private int linesPerPage=67;
	
	//* opening, closing date is in raw format.
	public MaterialCostSheetReportProcessor(inventorycontroller.function.DbInterface dbi, 
	 String[] jbNos){
	 	
	 	this.linesPerPage=this.getLinesPerPage();
	 	this.printProcess=null;
	 	this.lineCount=0;
		this.dbi=dbi;
		this.jbNos=jbNos;
	 	this.reportTotal=new double[4]; //* {qty, amt, vat, ed}
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
		fileName="./temp/MCS_Report#1.txt";
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
		return new int[]{0, 0, 1, 1, 1, 1, 1, 1};
	}
	
	public String[] getColumnNames(){
		return new String[]{
			"Item Code\nDescription", 
			"Voucher No. of Date", 
			"General Qty.\nExcisable Qty.", 
			"General Amt.\nExcisable Amt.", 
			"Total Quantity", 
			"Total Amount", 
			"VAT / CST", 
			"Excise Duty"
		};
		
	}
	
	/**
	 * methods to print to file.
	 */
	 
	public void generate(){

		for (int i = 0; i<jbNos.length; i++){
			this.lineCount=0;
		 	
			printReportHeader();
			printMCSHeader(jbNos[i]);
			printMCSDataHeader();
			printMCSData(jbNos[i]);
			printReportFooter(jbNos[i]);
		}
		
	}
	
	private void printReportHeader(){
		String[] reportHead=new String[9];
		//reportHead[0]="<<~h~>>";
		reportHead[0]="                                   ============================================================";
		reportHead[1]="                                   #                  * MATERIAL COST SHEET *                 #";
		reportHead[2]="                                   #                    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯                   #";
		reportHead[3]="                                   #                                                          #";
		reportHead[4]="                                   #         SREE CHAND ELECTRICAL INDUSTRIES PVT. LTD.       #";
		reportHead[5]="                                   ============================================================";
		reportHead[6]="";
		reportHead[7]="";
		reportHead[8]="";
		//reportHead[0]="<<~~h~>>";
		
		this.printLines(reportHead);
		
	}
	
	private void printMCSHeader(String jbNo){
		String[][] jbDet=null;
		try{
			jbDet=dbi.cmdSelect(
				"jbMaster, bomMaster, prdMaster", 
				
				"jbMaster.jbStDate, "+
				"jbMaster.jbEndDate, "+
				"jbMaster.prdNo, "+
				"prdMaster.prdName, "+
				"jbMaster.quantity, "+
				"bomMaster.bomNo, "+
				"bomMaster.rqnDate", 
				
				"jbMaster.jbNo='"+jbNo+"' and "+
				"bomMaster.jbNo=jbMaster.jbNo and "+
				"prdMaster.prdId=jbMaster.prdNo"
			);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		jbDet[0][0]=inventorycontroller.util.DateUtil.getDisplayFormat(jbDet[0][0]);
		jbDet[0][1]=inventorycontroller.util.DateUtil.getDisplayFormat(jbDet[0][1]);
		jbDet[0][6]=inventorycontroller.util.DateUtil.getDisplayFormat(jbDet[0][6]);
		this.printLines(new String[]{
			"JOB NO.          "+jbNo, 
			"START DATE       "+jbDet[0][0]+"        END DATE  "+jbDet[0][1], 
			"JOB DESCRIPTION  "+jbDet[0][2]+": "+jbDet[0][3]+"        QUANTITY  "+jbDet[0][4], 
			"", 
			"B. O. M. NO.     "+jbDet[0][5]+"    OF DATE "+jbDet[0][6], 
			"", 
			""
		});
		
	}
	
	private void printMCSDataHeader(){
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
	
	private void printMCSData(String jbNo){
	 	for (int i = 0; i<4; i++){ //* initialise to zero, for each ledger.
	 		this.reportTotal[i]=0;
	 	}
	 	getOpeningStock(jbNo);
	 	
	 	String fyr=inventorycontroller.util.DateUtil
		 .getFinancialYear(
		 	inventorycontroller.function.DateProcessor.getCurrentDate(dbi)
		 );
		String[][] rqn=null;
		try{
			rqn=dbi.cmdSelect(
				
				"gdsJbTrnDesc, gdsJbTrnMaster, matMaster",

				"gdsJbTrnDesc.matNo, "+
				"matMaster.matName, "+
				"matMaster.matUnit, "+
				"gdsJbTrnDesc.trnNo, "+
				"gdsJbTrnMaster.trnDate, "+
				"gdsJbTrnDesc.qtyGeneral, "+
				"gdsJbTrnDesc.qtyExcisable, "+
				"gdsJbTrnDesc.amtGeneral, "+
				"gdsJbTrnDesc.amtExcisable, "+
				"gdsJbTrnDesc.amtVatGeneral, "+
				"gdsJbTrnDesc.amtVatExcisable, "+
				"gdsJbTrnDesc.amtED", 
				
				"gdsJbTrnMaster.jbNo='"+jbNo+"' and "+
				"gdsJbTrnDesc.trnNo=gdsJbTrnMaster.trnNo and "+
				"gdsJbTrnDesc.TSid like '%"+fyr+"' and "+ 
				"matMaster.matId=gdsJbTrnDesc.matNo order by gdsJbTrnDesc.TSid"
			);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	 	inventorycontroller.util.DateUtil du=null;
	 	inventorycontroller.util.DoubleUtil dbu=null;
	 	
	 	//* opening balances: get & print.
	 	String vod=inventorycontroller.function.DateProcessor.getCurrentDate(dbi);
	 	vod="01-04-"+vod.substring(0, 4);
		String tQty=dbu.getDisplayValue(this.reportTotal[0], 3);
		String tAmt=dbu.getDisplayValue(this.reportTotal[1], 2);
		String tVat=dbu.getDisplayValue(this.reportTotal[2], 2);
		String tEd=dbu.getDisplayValue(this.reportTotal[3], 2);
		
		String[] lines=getLines(new String[]{
			"O P E N I N G    B A L A N C E", vod, "", "", tQty, tAmt, tVat, tEd
		});
		printLines(lines);
		printLines(new String[]{"", ""});
		
		String mat="";
		String gQty="";
		String eQty="";
		String gAmt="";
		String eAmt="";
		double qty=0;
		double amt=0;
		double vat=0;
		double ed=0;
	 	/** 
	 	 *rqn format: {matno, matname, matunit, voucherNo, date, 
	 	 *             genqty, exqty, genamt, examt, genvat, exvat, ed}
	 	 */
	 	
	 	for (int i = 0; i<rqn.length; i++){
	 		mat=rqn[i][0]+"\n"+rqn[i][1]+" (unit: "+rqn[i][2]+")";
	 		vod=rqn[i][3]+" of Date "+du.getDisplayFormat(rqn[i][4]);
	 		gQty=dbu.getDisplayValue(rqn[i][5], 3);
	 		eQty=dbu.getDisplayValue(rqn[i][6], 3);
	 		gAmt=dbu.getDisplayValue(rqn[i][7], 2);
	 		eAmt=dbu.getDisplayValue(rqn[i][8], 2);
	 		
	 		qty=Double.parseDouble(rqn[i][5])+Double.parseDouble(rqn[i][6]);
	 		amt=Double.parseDouble(rqn[i][7])+Double.parseDouble(rqn[i][8]);
	 		vat=Double.parseDouble(rqn[i][9])+Double.parseDouble(rqn[i][10]);
	 		ed=Double.parseDouble(rqn[i][11]);
	 		
	 		tQty=dbu.getDisplayValue(qty, 3);
	 		tAmt=dbu.getDisplayValue(amt, 2);
	 		tVat=dbu.getDisplayValue(vat, 2);
	 		tEd=dbu.getDisplayValue(ed, 2);
	 		
	 		int ve=1;
	 		if(rqn[i][3].startsWith(inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_RETURN_ID)){
	 			ve=-1;
	 			gQty="-"+gQty;
	 			eQty="-"+eQty;
	 			gAmt="-"+gAmt;
	 			eAmt="-"+eAmt;
	 			tQty="-"+tQty;
	 			tAmt="-"+tAmt;
	 			tVat="-"+tVat;
	 			tEd="-"+tEd;
	 		}
	 		
	 		//* calculate totals.
	 		this.reportTotal[0]+=ve*qty;
	 		this.reportTotal[1]+=ve*amt;
	 		this.reportTotal[2]+=ve*vat;
	 		this.reportTotal[3]+=ve*ed;
	 		
	 		lines=this.getLines(new String[]{
	 			mat, vod, gQty+"\n"+eQty, gAmt+"\n"+eAmt, tQty, tAmt, tVat, tEd
	 		});
	 		printLines(lines);
	 		printLines(new String[]{""});
	 	}
	 	
	 	//* write closing balance.
	 	vod=inventorycontroller.function.DateProcessor.getCurrentDate(dbi);
	 	vod=du.getDisplayFormat(vod);
		tQty=dbu.getDisplayValue(this.reportTotal[0], 3);
		tAmt=dbu.getDisplayValue(this.reportTotal[1], 2);
		tVat=dbu.getDisplayValue(this.reportTotal[2], 2);
		tEd=dbu.getDisplayValue(this.reportTotal[3], 2);

	 	lines=getLines(new String[]{
			"C L O S I N G    B A L A N C E", vod, "", "", tQty, tAmt, tVat, tEd
		});
		printLines(new String[]{""});
		printLines(lines);
		printLines(new String[]{getHorizLine("_")});
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
				"gdsJbTrnDesc.qtyGeneral, "+
				"gdsJbTrnDesc.qtyExcisable, "+
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
		double qty=0;
		double amt=0;
		double vat=0;
		double ed=0;
		for (int i = 0; i<rqn.length; i++){
			qty=Double.parseDouble(rqn[i][1])+Double.parseDouble(rqn[i][2]);
			amt=Double.parseDouble(rqn[i][3])+Double.parseDouble(rqn[i][4]);
			vat=Double.parseDouble(rqn[i][5])+Double.parseDouble(rqn[i][6]);
			ed=Double.parseDouble(rqn[i][7]);
			
			if(rqn[i][0].startsWith(inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_RETURN_ID)){
				this.reportTotal[0]-=qty;
				this.reportTotal[1]-=amt;
				this.reportTotal[2]-=vat;
				this.reportTotal[3]-=ed;
			}
			else {
				this.reportTotal[0]+=qty;
				this.reportTotal[1]+=amt;
				this.reportTotal[2]+=vat;
				this.reportTotal[3]+=ed;
			}
		}
	}
	
	private void printReportFooter(String jbNo){
		String[] tmp=getRemarks(jbNo);
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
	
	private String[] getRemarks(String jbNo){
		String[][] rem=null;
		try{
			rem=this.dbi.cmdSelect(
				"jbMaster", 
				"jbMaster.jbRemark", 
				"jbMaster.jbNo='"+jbNo+"'"
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
	
	//* need data header on top of each page other than the 1st.
	private void printLines(String[] lines){
		try{
			java.io.FileWriter fw=null;
			java.io.PrintWriter pw=null;
			
			for (int i = 0; i<lines.length; i++){
				
				if(this.lineCount%this.linesPerPage==0 && this.lineCount!=0){
					this.printMCSDataHeader(); //* header @ top of page.
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
	
		

	
	
	
	
	private Process printProcess;
	private int lineCount;
	private String[] jbNos;
	private double[] reportTotal;
	private inventorycontroller.function.DbInterface dbi;
	private java.io.File reportFile;
}
