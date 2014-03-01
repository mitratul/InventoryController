/*
 * PurchaseRequisitionReportProcessor.java
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
public class PurchaseRequisitionReportProcessor {
	
	private static final short REPORT_WIDTH=108;
	private static final int[] colWidth=new int[]{40, 18, 50};
	
	private int linesPerPage=67;
	
	public PurchaseRequisitionReportProcessor(inventorycontroller.function.DbInterface dbi, String[] poNos){
	 	
	 	this.linesPerPage=this.getLinesPerPage();
	 	this.poDet=null;
	 	this.bomList=null;
	 	this.type="PR";
	 	this.printProcess=null;
	 	this.lineCount=0;
	 	this.poNos=poNos;
		this.dbi=dbi;
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
	
	public PurchaseRequisitionReportProcessor(inventorycontroller.function.DbInterface dbi,
	 String poNo, String[] bomList, String[][] poDet){
	 	this.poDet=poDet;
	 	this.bomList=bomList;
	 	this.type="PT";
	 	this.printProcess=null;
	 	this.lineCount=0;
	 	this.poNos=new String[]{poNo};
		this.dbi=dbi;
		this.reportFile=allocateReportFile();

	}
	
	/**
	 * report format specification.
	 */
	 
	private java.io.File allocateReportFile(){
		java.io.File f=null;
		String fileName;
		fileName="./temp/PR_Report#1.txt";
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
	
	public String[] getColumnNames(){
		return new String[]{
			"Item Code\nDescription", 
			"Required Quantity\n(unit)", 
			""
		};
		
	}
	
	public int[] getAlignment(){
		return new int[]{0, 1, 1};
	}
	
	/**
	 * methods to print to file.
	 */
	 
	public void generate(){
		
		for (int i = 0; i<poNos.length; i++){
			this.lineCount=0;
			printReportHeader();
			printPrHeader(poNos[i]);
			printPrDataHeader();
			printPrData(poNos[i]);
			printPrTotal(poNos[i]);
			printReportFooter();
		}
		
	}
	
	private void printReportHeader(){
		String[] reportHead=new String[7];
		//reportHead[0]="<<~h~>>";
		reportHead[0]="                        ============================================================";
		reportHead[1]="                        #                 * PURCHASE REQUISITION *                 #";
		reportHead[2]="                        #                   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯                   #";
		reportHead[3]="                        #                                                          #";
		reportHead[4]="                        #         SREE CHAND ELECTRICAL INDUSTRIES PVT. LTD.       #";
		reportHead[5]="                        ============================================================";
		reportHead[6]="";
		//reportHead[0]="<<~~h~>>";
		
		this.printLines(reportHead);
		
	}
	
	private void printPrHeader(String poNo){
		String prNo=this.type+poNo.substring(2);
		
		//* bring date to display format.
		String date="";
		if(this.type.compareTo("PR")==0){
			String[][] tmp1=null;
			String[][] tmp2=null;
			try{
				tmp1=dbi.cmdSelect(
					"poMaster", 
					"poMaster.poDate", 
					"poMaster.poNo='"+poNo+"'"
				);
				tmp2=dbi.cmdSelect(
					"poBomDesc, bomMaster", 
					
					"poBomDesc.bomNo, "+
					"bomMaster.rqnDate", 
					
					"poBomDesc.poNo='"+poNo+"' and "+
					"bomMaster.bomNo=poBomDesc.bomNo"
				);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			java.util.Vector<String> v=new java.util.Vector<String>();
			for (int i = 0; i<tmp2.length; i++){
				tmp2[i][1]=inventorycontroller.util.DateUtil.getDisplayFormat(tmp2[i][1]);
				if(!v.contains(tmp2[i][0]+" OF DATE "+tmp2[i][1])){
					v.add(tmp2[i][0]+" OF DATE "+tmp2[i][1]);
				}
			}
			this.bomList=new String[v.size()];
			for (int i = 0; i<v.size(); i++){
				this.bomList[i]=v.elementAt(i);
			}
			
			date=tmp1[0][0];
		}
		else{
			String[][] tmp3=null;
			for (int i = 0; i<this.bomList.length; i++){
				try{
					tmp3=dbi.cmdSelect(
						"bomMaster", 
						"bomMaster.rqnDate", 
						"bomMaster.bomNo='"+this.bomList[i]+"'"
					);
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				tmp3[0][0]=inventorycontroller.util.DateUtil.getDisplayFormat(tmp3[0][0]);
				
				this.bomList[i]=this.bomList[i]+" OF DATE "+tmp3[0][0];
			}
			
			date=inventorycontroller.function.DateProcessor.getCurrentDate(dbi);
		}
		date=inventorycontroller.util.DateUtil.getDisplayFormat(date);
		
		String[] poHeaderLines=new String[5+this.bomList.length];
		poHeaderLines[0]="PURCHASE REQUISITION NO.  "+prNo;
		poHeaderLines[1]="PURCHASE REQUISITION DATE "+date;
		poHeaderLines[2]="B. O. M. NOS. WITH DATE:";
		for (int i = 0; i<this.bomList.length; i++){
			poHeaderLines[3+i]="        "+this.bomList[i];
		}
		poHeaderLines[poHeaderLines.length-2]="";
		poHeaderLines[poHeaderLines.length-1]="";
		
		//* write in file
		this.printLines(poHeaderLines);
		
		
	}
	
	private void printPrDataHeader(){
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
	
	private void printPrData(String poNo){
		if(this.type.compareTo("PR")==0){
			try{
				this.poDet=dbi.cmdSelect(
					"poDesc, matMaster",
					
					"poDesc.matNo, "+
					"matMaster.matName, "+
					"poDesc.quantity, "+
					"matMaster.matUnit", 
					
					"poDesc.poNo='"+poNo+"' and "+
					"matMaster.matId=poDesc.matNo"
				);
			}
			catch(Exception ex){
				this.poDet=new String[0][0];
				ex.printStackTrace();
			}
		}
		inventorycontroller.util.DoubleUtil dbu=null;
		//* make bom report data table.
		String[][] poData=new String[this.poDet.length][3];
		for (int i = 0; i<poData.length; i++){
			
			//* mat no, name.
			poData[i][0]=this.poDet[i][0]+"\n"+this.poDet[i][1];
			
			//* qty
			poData[i][1]=dbu.getDisplayValue(this.poDet[i][2], 3)
			 +"\n"+poDet[i][3];
			
			poData[i][2]=" ";
			
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
	
	private void printPrTotal(String poNo){
		
		//* print total to file.
		printLines(new String[]{this.getHorizLine("-")});
	}
	
	private void printReportFooter(){
		
		printLines(new String[]{
			"", 
			"REMARKS:"
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
	
	/**
	 * general methods.
	 */
	
	private String getHorizLine(String lineUnit){
		String hLine="";
		for (int i = 0; i<REPORT_WIDTH; i++){
			//hLine=hLine+"¯";
			hLine=hLine+lineUnit;
		}
		return hLine;
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
			
	String[][] poDet;
	private String[] bomList;
	private String type;
	private Process printProcess;
	private int lineCount;
	private String[] poNos;
	private inventorycontroller.function.DbInterface dbi;
	private java.io.File reportFile;
}