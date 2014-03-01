/*
 * PurchaseOrderReportProcessor.java
 *
 * Created on October 01, 2007, 11:05 HRS
 * Last Modified on October 01, 2007, 18:05 HRS
 *
 */

package inventorycontroller.function;

/**
 *
 * @author  brinto
 */
public class PurchaseOrderReportProcessor {
	
	private static final short REPORT_WIDTH=200;
	private static final int[] colWidth=new int[]{8, 38, 16, 17, 18, 17, 17, 17, 17, 17, 18};
	
	private int linesPerPage=67;
	
	public PurchaseOrderReportProcessor(inventorycontroller.function.DbInterface dbi, String[] poNos){
	 	
	 	this.linesPerPage=this.getLinesPerPage();
	 	this.printProcess=null;
	 	this.lineCount=0;
	 	this.poNos=poNos;
		this.dbi=dbi;
		this.reportFile=allocateReportFile();
		
	}
	
	/**
	 * report format specification.
	 */
	
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
		fileName="./temp/PO_Report#1.txt";
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
			"Sl.No.", 
			"Item Code\nDescription", 
			"Quantity\n(unit)", 
			"Rate (Rs./unit)", 
			"Gross Amount (Rs.)", 
			"Discount (Rs.)\n(%)", 
			"Surcharge (Rs.)\n(%)", 
			"Excise Duty (Rs.)\n(%)", 
			"Tax (Rs.)\n(%)", 
			"Freight / Insurance (Rs.)\n(%)", 
			"Net Amount (Rs.)"
		};
		
	}
	
	public int[] getAlignment(){
		return new int[]{0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1};
	}
	
	/**
	 * methods to print to file.
	 */
	 
	public void generate(){
		
		for (int i = 0; i<poNos.length; i++){
			this.lineCount=0;
			printReportHeader();
			printPoHeader(poNos[i]);
			printPoDataHeader();
			printPoData(poNos[i]);
			printPoTotal(poNos[i]);
			printReportFooter(poNos[i]);
		}
		
	}
	
	private void printReportHeader(){
		String[] reportHead=new String[7];
		//reportHead[0]="<<~h~>>";
		reportHead[0]="                                                                     ============================================================";
		reportHead[1]="                                                                     #                    * PURCHASE ORDER *                    #";
		reportHead[2]="                                                                     #                      ¯¯¯¯¯¯¯¯¯¯¯¯¯¯                      #";
		reportHead[3]="                                                                     #                                                          #";
		reportHead[4]="                                                                     #         SREE CHAND ELECTRICAL INDUSTRIES PVT. LTD.       #";
		reportHead[5]="                                                                     ============================================================";
		reportHead[6]="";
		//reportHead[0]="<<~~h~>>";
		
		this.printLines(reportHead);
		
	}
	
	private void printPoHeader(String poNo){
		String[][] tmp1=null;
		try{
			tmp1=dbi.cmdSelect(
				"poMaster, vndMaster", 
				
				"poMaster.poDate, "+
				"vndMaster.vndCmpName, "+
				"poMaster.qtnNo, "+
				"poMaster.qtnDate, "+
				"vndMaster.vndAddress, "+
				"vndMaster.vndPin", 
				
				"poMaster.poNo='"+poNo+"' and "+
				"vndMaster.vndNo=poMaster.vndNo"
			);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		//* bring date to display format.
		inventorycontroller.util.DateUtil du=null;
		tmp1[0][0]=du.getDisplayFormat(tmp1[0][0]);
		tmp1[0][3]=du.getDisplayFormat(tmp1[0][3]);
		
		String[] poHeaderLines=new String[6];
		poHeaderLines[0]="MESSERS            "+tmp1[0][1]+".";
		poHeaderLines[1]="                   "+tmp1[0][4]+", PIN - "+tmp1[0][5]+".";
		poHeaderLines[2]="QUOTATION REF. NO. "+tmp1[0][2];
		poHeaderLines[3]="                   OF DATE "+tmp1[0][3];
		poHeaderLines[4]="Dear Sirs,";
		poHeaderLines[5]="        Please supply us with the following, quoting the above ORDER NO. in your Challan.";
		
		int cc=132-poHeaderLines[2].length();
		for (int i = 0; i<cc; i++){
			poHeaderLines[2]=poHeaderLines[2]+" ";
		}
		poHeaderLines[2]=poHeaderLines[2]+"PURCHASE ORDER NO. "+poNo;
		
		cc=132-poHeaderLines[3].length();
		for (int i = 0; i<cc; i++){
			poHeaderLines[3]=poHeaderLines[3]+" ";
		}
		poHeaderLines[3]=poHeaderLines[3]+"OF DATE            "+tmp1[0][0];
		
		//* write in file
		this.printLines(poHeaderLines);
		
		
	}
	
	private void printPoDataHeader(){
		//* initialise.
		String[] colNames=getColumnNames();
		
		//* convert to lines, printable in grn report.
		String[] lines=getLines(colNames);
		String[] dataHeaderBlock=new String[lines.length+2];
		dataHeaderBlock[0]=getHorizLine("_");
		for (int i = 0; i<lines.length; i++){
			dataHeaderBlock[i+1]=lines[i];
		}
		dataHeaderBlock[dataHeaderBlock.length-1]=getHorizLine("-");
		
		//* print header lines.
		printLines(dataHeaderBlock);
		
		
	}
	
	private void printPoData(String poNo){
		String[][] tmp=null;
		try{
			tmp=dbi.cmdSelect(
				"poDesc, matMaster",
				
				"poDesc.matNo, "+
				"matMaster.matName, "+
				"poDesc.quantity, "+
				"poDesc.matRate, "+
				"poDesc.discount, "+
				"poDesc.surcharge, "+
				"poDesc.exDuty, "+
				"poDesc.vat, "+
				"poDesc.cst, "+
				"poDesc.freight, "+ 
				"matMaster.matUnit", 
				
				"poDesc.poNo='"+poNo+"' and "+
				"matMaster.matId=poDesc.matNo"
			);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		//* make bom report data table.
		String[][] poData=new String[tmp.length][11];
		for (int i = 0; i<poData.length; i++){
			String tmp1="0";
			double tmp2=0;
			inventorycontroller.util.DoubleUtil dbu=null;
			
			poData[i][0]=(i+1)+".";
			
			//* mat no, name.
			poData[i][1]=tmp[i][0]+"\n"+tmp[i][1];
			
			//* qty
			poData[i][2]=dbu.getDisplayValue(tmp[i][2], 3)
			 +"\n"+tmp[i][10];
			
			//* rate.
			poData[i][3]=dbu.getDisplayValue(tmp[i][3], 2);
			
			//* gross amt.
			tmp2=Double.parseDouble(tmp[i][2])*Double.parseDouble(tmp[i][3]);
			tmp1=tmp2+"";
			poData[i][4]=dbu.getDisplayValue(tmp1, 2);
			
			//* discount.
			tmp1=tmp2*Double.parseDouble(tmp[i][4])/100+"";
			tmp2-=Double.parseDouble(tmp1);
			
			poData[i][5]=dbu.getDisplayValue(tmp1, 2)
			 +"\n"+dbu.getDisplayValue(tmp[i][4], 2)
			 +"%";
			
			
			//* scharge.
			tmp1=tmp2*Double.parseDouble(tmp[i][5])/100+"";
			tmp2+=Double.parseDouble(tmp1);
			
			poData[i][6]=dbu.getDisplayValue(tmp1, 2)
			 +"\n"+dbu.getDisplayValue(tmp[i][5], 2)
			 +"%";
			
			//* ed.
			tmp1=tmp2*Double.parseDouble(tmp[i][6])/100+"";
			tmp2+=Double.parseDouble(tmp1);
			
			poData[i][7]=dbu.getDisplayValue(tmp1, 2)
			 +"\n"+dbu.getDisplayValue(tmp[i][6], 2)
			 +"%";
			
			//* vat/cst.
			String tax=Double.parseDouble(tmp[i][7])+Double.parseDouble(tmp[i][8])+"";
			String tt=Double.parseDouble(tmp[i][7])==0? "CST": "VAT";
			tmp1=tmp2*Double.parseDouble(tax)/100+"";
			tmp2+=Double.parseDouble(tmp1);
			
			poData[i][8]=dbu.getDisplayValue(tmp1, 2)
			 +"\n"+dbu.getDisplayValue(tax, 2)
			 +"% "+tt;
			
			//* frt.
			tmp1=tmp2*Double.parseDouble(tmp[i][9])/100+"";
			tmp2+=Double.parseDouble(tmp1);
			
			poData[i][9]=dbu.getDisplayValue(tmp1, 2)
			 +"\n"+dbu.getDisplayValue(tmp[i][9], 2)
			 +"%";
			 
			//* net amt.
			tmp1=tmp2+"";
			poData[i][10]=dbu.getDisplayValue(tmp1, 2);
		
			//* convert to printable lines
			String[] lines=this.getLines(poData[i]);
			String[] linesWithGap=new String[lines.length+1];
			for (int j = 0; j<lines.length; j++){
				linesWithGap[j]=lines[j];
			}
			linesWithGap[lines.length]="";
			
			//* print lines.
			printLines(linesWithGap);
		}
		
		
	}
	
	private void printPoTotal(String poNo){
		String[][] tmp=null;
		try{
			tmp=dbi.cmdSelect(
				"poMaster", 
				"poMaster.poTotal", 
				"poMaster.poNo='"+poNo+"'"
			);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		String totalAmt=inventorycontroller.util.DoubleUtil.getDisplayValue(tmp[0][0], 2);
		
		int[] align=this.getAlignment();
		while(totalAmt.length()<this.colWidth[10]-1){
			if(align[10]==0){
				totalAmt=totalAmt+" ";
			}
			else {
				totalAmt=" "+totalAmt;
			}
		}
		
		String[] poTotal=new String[3];
		poTotal[0]=getHorizLine("¯");
		
		int remWidth=this.REPORT_WIDTH-this.colWidth[10]-3;
		poTotal[1]="P U R C H A S E    O R D E R    T O T A L";
		int c=0;
		while(poTotal[1].length()<remWidth){
			if(++c%2==1){
				poTotal[1]=" "+poTotal[1];
			}
			else{
				poTotal[1]=poTotal[1]+" ";
			}
		}
		poTotal[1]=poTotal[1]+"RS."+totalAmt;
		poTotal[2]=getHorizLine("¯");
		
		//* print total to file.
		printLines(poTotal);
	}
	
	private void printReportFooter(String poNo){
		printLines(new String[]{
			"PAYMENT TERMS:                                                                                     DELIVERY TERMS:", 
		});
		String[] tmp=getRemarks(poNo);
		String[] rem=null;
		if(tmp.length>0){
			rem=new String[tmp.length+1];
			rem[0]="";
			rem[1]="REMARKS      : "+tmp[0];
		}
		else{
			rem=new String[tmp.length+2];
			rem[0]="";
			rem[1]="REMARKS      : ";
		}
		for (int i = 1; i<tmp.length; i++){
			rem[i+1]="               "+tmp[i];
		}
		printLines(rem);
		
		java.util.Properties p=new java.util.Properties();
		String vatno="";
		String cstno="";
		String eccno="";
		String range="";
		String div="";
		String comm="";
		try{
			java.io.FileInputStream fis=
			 new java.io.FileInputStream(new java.io.File("./properties/COMPANY.PROPERTY"));
			p.load(fis);
			vatno=p.getProperty("VAT_No");
			cstno=p.getProperty("CST_No");
			eccno=p.getProperty("ECC_No");
			range=p.getProperty("Range");
			div=p.getProperty("Division");
			comm=p.getProperty("Commissionerate");
			
			fis.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		printLines(new String[]{
			/** BLANK Space b4 auth. sig.*/"                                                                                                                                                                            "
			 +"AUTHORISED SIGNATURE", 
			"OUR TAX REGISTRATION DETAILS:", 
			"    V.A.T. NO. "+vatno, 
			"    C.S.T. NO. "+cstno, 
			"    E.C.C. NO. "+eccno+"    RANGE "+range+"    DIVISION "+div+"    COMMISSIONERATE "+comm
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
	
	private String[] getRemarks(String poNo){
		String[][] rem=null;
		try{
			rem=this.dbi.cmdSelect(
				"poMaster", 
				"poMaster.remark", 
				"poMaster.poNo='"+poNo+"'"
			);
		}
		catch(Exception ex){
			return new String[0];
		}
		int w=80;
		
		int l=rem[0][0].length()/w;
		l=rem[0][0].length()%w==0? l: ++l;
		System.out.println (rem[0][0].length());
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

			
	private Process printProcess;
	private int lineCount;
	private String[] poNos;
	private inventorycontroller.function.DbInterface dbi;
	private java.io.File reportFile;
}