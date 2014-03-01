/*
 * GrnReportProcessor.java
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
public class GrnReportProcessor {
	
	public static final short G_R_N_REPORT_WIDTH=200;
	private static final int[] colWidth=new int[]{8, 38, 15, 17, 18, 17, 17, 17, 17, 17, 19};
	
	private int linesPerPage=67;
	
	public GrnReportProcessor(inventorycontroller.function.DbInterface dbi, String[] grnNos){
	 	
	 	this.linesPerPage=this.getLinesPerPage();
	 	this.printProcess=null;
	 	this.lineCount=0;
	 	this.grnNos=grnNos;
		this.dbi=dbi;
		this.reportTotal=0;
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
		fileName="./temp/GRN_Report#1.txt";
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
		for (int i = 0; i<G_R_N_REPORT_WIDTH; i++){
			//hLine=hLine+"¯";
			hLine=hLine+lineUnit;
		}
		return hLine;
	}
	
	public int[] getAlignment(){
		return new int[]{0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1};
	}
	
	public String[] getColumnNames(){
		return new String[]{
			"Sl.No.", 
			"Item Code\nDescription", 
			"Supplied Qty.\n(unit)", 
			"Rate (Rs / unit)", 
			"Gross Amount (Rs)", 
			"Discount (Rs)", 
			"Surcharge (Rs)", 
			"Excise Duty (Rs)",
			"VAT / CST (Rs)", 
			"Insurance / Freight (Rs)", 
			"Net Amount (Rs)"
		};
		
	}
	
	/**
	 * methods to print to file.
	 */
	 
	public void generate(){
		printReportHeader();
		
		for (int i = 0; i<grnNos.length; i++){
			printGrnHeader(grnNos[i]);
			printGrnDataHeader();
			printGrnData(grnNos[i]);
			printGrnTotal(grnNos[i]);
		}
		
		printReportTotal();
		printReportFooter();
		
	}
	
	private void printReportHeader(){
		String[] reportHead=new String[9];
		//reportHead[0]="<<~h~>>";
		reportHead[0]="                                                                     ============================================================";
		reportHead[1]="                                                                     #                   * GOODS RECEIPT NOTE *                 #";
		reportHead[2]="                                                                     #                     ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯                   #";
		reportHead[3]="                                                                     #                                                          #";
		reportHead[4]="                                                                     #         SREE CHAND ELECTRICAL INDUSTRIES PVT. LTD.       #";
		reportHead[5]="                                                                     ============================================================";
		reportHead[6]="";
		reportHead[7]="";
		reportHead[8]="";
		//reportHead[0]="<<~~h~>>";
		
		this.printLines(reportHead);
		
	}
	
	private void printGrnHeader(String grnNo){
		String[][] tmp1=null;
		String[][] tmp2=null;
		try{
			tmp1=dbi.cmdSelect(
				"grnMaster, poMaster, vndMaster", 
				
				"grnMaster.grnDate, "+
				"grnMaster.poNo, "+
				"poMaster.poDate, "+
				"grnMaster.grnvndChallanNo, "+
				"grnMaster.grnvndChallanDate, "+
				"vndMaster.vndNo, "+
				"vndMaster.vndCmpName",
				
				"grnMaster.grnNo='"+grnNo+"' and "+
				"poMaster.poNo=grnMaster.poNo and "+
				"vndMaster.vndNo=poMaster.vndNo"
			);
			tmp2=dbi.cmdSelect(
				"poBomDesc, bomMaster", 
				"poBomDesc.bomNo, bomMaster.rqnDate",
				"poBomDesc.poNo='"+tmp1[0][1]+"' and bomMaster.bomNo=poBomDesc.bomNo"
			);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		//* bring date to display format.
		inventorycontroller.util.DateUtil du=null;
		for (int i = 0; i<tmp1.length; i++){
			tmp1[i][0]=du.getDisplayFormat(tmp1[i][0]);
			tmp1[i][2]=du.getDisplayFormat(tmp1[i][2]);
			tmp1[i][4]=du.getDisplayFormat(tmp1[i][4]);
		}
		for (int i = 0; i<tmp2.length; i++){
			tmp2[i][1]=du.getDisplayFormat(tmp2[i][1]);
		}
		
		//* fill tmp3 with unique BOM nos.
		java.util.Vector<String> tmp3=new java.util.Vector<String>();
		for (int i = 0; i<tmp2.length; i++){
			if(!tmp3.contains(tmp2[i][0]+" OF "+tmp2[i][1])){
				tmp3.add(tmp2[i][0]+" OF "+tmp2[i][1]);
			}
		}
		String[] grnHeaderLines=new String[7+tmp3.size()];
		grnHeaderLines[0]     ="G. R. N. NO. "+grnNo+" OF DATE "+tmp1[0][0];
		grnHeaderLines[1]     ="PURCHASE ORDER NO. "+tmp1[0][1]+" OF DATE "+tmp1[0][2];
		grnHeaderLines[2]     ="B. O. M. NOS. OF DATE:";
		int i = 0;
		for (; i<tmp3.size(); i++){
			grnHeaderLines[3+i] ="        "+tmp3.elementAt(i);
		}
		grnHeaderLines[3+i++] ="";
		grnHeaderLines[3+i++] ="CHALLAN NO. "+tmp1[0][3]+" OF DATE "+tmp1[0][4];
		grnHeaderLines[3+i++] ="SUPPLIER NO. "+tmp1[0][5]+"  ("+tmp1[0][6]+")";
		grnHeaderLines[3+i]   ="";
		
		//* write in file
		this.printLines(grnHeaderLines);
		
	}
	
	private void printGrnDataHeader(){
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
	
	private void printGrnData(String grnNo){
		String[][] tmp=null;
		try{
			tmp=dbi.cmdSelect(
				"grnMaster, grnDesc, poDesc, matMaster",
				
				"grnDesc.matNo, "+
				"matMaster.matName, "+
				"grnDesc.grnQty, "+
				"poDesc.matRate, "+
				"grnDesc.grnDscAmt, "+
				"grnDesc.grnSurchargeAmt, "+
				"grnDesc.grnEdAmt, "+
				"grnDesc.grnVatAmt, "+
				"grnDesc.grnCSTAmt, "+
				"grnDesc.grnFreightAmt, "+
				"matMaster.matUnit, "+
				"grnDesc.grnMatAmt", 
				
				"grnMaster.grnNo='"+grnNo+"' and "+
				"grnDesc.grnNo='"+grnNo+"' and "+
				"poDesc.poNo=grnMaster.poNo and "+
				"poDesc.matNo=grnDesc.matNo and "+
				"matMaster.matId=grnDesc.matNo"
			);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		inventorycontroller.util.DoubleUtil dbu=null;
		
		//* make grn report data table.
		String[][] grnData=new String[tmp.length][11];
		for (int i = 0; i<grnData.length; i++){
			double tmp1=0;
			grnData[i][0]=(i+1)+".";
			
			grnData[i][1]=tmp[i][0]+"\n"+tmp[i][1];
			
			grnData[i][2]=dbu.getDisplayValue(tmp[i][2], 3)
			 +"\n"+tmp[i][10];
			
			grnData[i][3]=dbu.getDisplayValue(tmp[i][3], 2);
			
			tmp1=Double.parseDouble(tmp[i][2])
				*Double.parseDouble(tmp[i][3]);
			String grossAmt=tmp1+"";
			grnData[i][4]=dbu.getDisplayValue(grossAmt, 2);
			
			grnData[i][5]=dbu.getDisplayValue(tmp[i][4], 2);
			
			grnData[i][6]=dbu.getDisplayValue(tmp[i][5], 2);
			
			grnData[i][7]=dbu.getDisplayValue(tmp[i][6], 2);
			
			String tax=(Double.parseDouble(tmp[i][7])+Double.parseDouble(tmp[i][8]))+"";
			
			grnData[i][8]=dbu.getDisplayValue(tax, 2);
			
			grnData[i][9]=dbu.getDisplayValue(tmp[i][9], 2);
			
			grnData[i][10]=dbu.getDisplayValue(tmp[i][11], 2);
		
			//* convert to printable lines
			String[] lines=this.getLines(grnData[i]);
			String[] linesWithGap=new String[lines.length+1];
			for (int j = 0; j<lines.length; j++){
				linesWithGap[j]=lines[j];
			}
			linesWithGap[lines.length]="";
			
			//* print lines.
			printLines(linesWithGap);
		}
		
		
	}
	
	private void printGrnTotal(String grnNo){
		String[][] tmp1=null;
		try{
			tmp1=dbi.cmdSelect(
				"grnMaster", 
				"grnMaster.grnAmt", 
				"grnMaster.grnNo='"+grnNo+"'"
			);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		//* update report grand total.
		this.reportTotal+=Double.parseDouble(tmp1[0][0])*(grnNo.startsWith("GRN")?1:-1);
		
		//* show grn total.
		String total=inventorycontroller.util.DoubleUtil.getDisplayValue(tmp1[0][0], 2);
		
		total=grnNo.startsWith("GRN")?total:"-"+total;
		int[] align=getAlignment();
		while(total.length()<colWidth[10]-2){
			if(align[10]==0){
				total=total+" ";
			}
			else {
				total=" "+total;
			}
		}
		
		String[] grnTotal=new String[4];
		grnTotal[0]=getHorizLine("¯");
		grnTotal[1]="                                                                            "+
		            "G.    R.    N.    T O T A L"+
		            "                                                                            :  "+
		            total;
		grnTotal[2]=getHorizLine("¯");
		grnTotal[3]="";
		
		//* print total to file.
		printLines(grnTotal);
	}
	
	private void printReportTotal(){
		String[] grandTotal=new String[5];
		String tmp=this.reportTotal+"";
		String total=inventorycontroller.util.DoubleUtil.getDisplayValue(tmp, 2);
		
		grandTotal[0]=getHorizLine("=");
		grandTotal[1]=getHorizLine("¯");
		grandTotal[2]="                                                                         "+
		            "    G R A N D    T O T A L     :     RS.  "+
		            total;
		grandTotal[3]=getHorizLine("¯");
		grandTotal[4]=getHorizLine("=");
		
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
	private String[] grnNos;
	private double reportTotal;
	private inventorycontroller.function.DbInterface dbi;
	private java.io.File reportFile;
}