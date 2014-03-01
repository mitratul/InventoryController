/*
 * JInternalFrameGRNCancellation.java
 *
 * Created on September 21, 2007, 10:45 HRS
 * Last Modified on September 10, 2007, 09:45 HRS
 *
 */

package inventorycontroller.display;

/**
 *
 * @author  brinto
 */
public class JInternalFrameGRNCancellation extends javax.swing.JInternalFrame {
    
    /** Creates new form JInternalFrameGRNCancellation */
    public JInternalFrameGRNCancellation(inventorycontroller.function.DbInterface dbi) {
        initComponents(dbi);
    }
    
    /** This method is called from within the constructor to initialize the form. */
    private void initComponents(inventorycontroller.function.DbInterface dbi) {
    	dbInterface1=dbi;
    	existingGrnList=null;
    	cancelledGrnList=null;
    	grnStatus=null;
    	grnNo="";
    	grcNo="";
    	poNo="";
    	
    	jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setTitle("Goods Receipt Note Cancellation");
        jLabel1.setBackground(new java.awt.Color(127, 157, 185));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");
        jLabel1.setOpaque(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        
        jTable2.setAutoCreateRowSorter(false);
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.setAutoResizeMode(jTable2.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(jTable2);

        jLabel3.setText("GRN Status:");

        jButton1.setText("Cancel Goods Receipt Note");
        jButton1.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		insertPerform();
        	}
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel4.setForeground(new java.awt.Color(255, 102, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        jLabel4.setOpaque(true);

        jButton4.setText("Print Last GRN Cancellation Report");
        jButton4.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		generatePrintPreview();
        	}
        });

        jTable3.setAutoCreateRowSorter(false);
        jTable3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable3.setAutoResizeMode(jTable3.AUTO_RESIZE_OFF);
        jScrollPane3.setViewportView(jTable3);

        jButton5.setText("<html><center>Select<br>Goods Receipt Note</center></html>");
        jButton5.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		selectExistingGRN();
        	}
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addComponent(jButton5))
                .addContainerGap())
        );
        jTabbedPane1.addTab("Existing GRN List", jPanel3);

        /*jTable1.setAutoCreateRowSorter(false);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(jTable1);

        jButton6.setText("<html><center>Select<br>Cancelled Goods Receipt Note</center></html>");
        jButton6.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		selectCancelledGRN();
        	}
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                .addContainerGap())
        );
        jTabbedPane1.addTab("Cancelled GRN List", jPanel4);*/ // as cancelled GRN List is not shown

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                    	.addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, 220, 320, 320)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, 220, 320, 320)
                    	.addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addGap(16)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		resetPerform();
        	}
        });

        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		exitPerform();
        	}
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, 120, 120, 120)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, 120, 120, 120)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, 22, 22, 22)
                    .addComponent(jButton3, 22, 22, 22))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(0, java.lang.Short.MAX_VALUE)
                .addComponent(jPanel2)
                .addContainerGap(0, java.lang.Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, javax.swing.GroupLayout.DEFAULT_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1)
                .addGap(4, 16, 22)
                .addComponent(jPanel2)
                .addGap(4, 16, 22)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        
        resetPerform();
        
        pack();
        setVisible(true);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        getRootPane().setDefaultButton(jButton1);
    //* ENDING: initComponent()
    }
    
    /** called from actionListener of jButton4. */
    public void generatePrintPreview(){
    	//this.getDesktopPane().getRootPane().getJMenuBar().getMenu(4).getItem(3).doClick(4);
    	String[][] grcNo=null;
    	String grcid=inventorycontroller.function.GoodsReceiveNoteProcessor.GRN_CANCELLATION_ID;
		String yr=inventorycontroller.util.DateUtil.getFinancialYear(
			inventorycontroller.function.DateProcessor.getCurrentDate(this.dbInterface1)
		);
    	try{
    		grcNo=this.dbInterface1.cmdSelect(
    			"grnDesc", 
    			
    			"grnDesc.TSid, "+
    			"grnDesc.grnNo", 
    			
    			"grnDesc.grnNo like '"+grcid+"%' and "+
    			"grnDesc.TSid like '%"+yr+"' order by grnDesc.TSid"
    		);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	if(grcNo.length==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Nothing to print\"</center></html>",
				"InventoryController says...",
			javax.swing.JOptionPane.ERROR_MESSAGE);
			return;
    	}
    	
    	inventorycontroller.function.GrnReportProcessor grp=
    	 new inventorycontroller.function.GrnReportProcessor(
    	 	dbInterface1, 
    	 	new String[]{grcNo[grcNo.length-1][1]}
    	 );
    	grp.generate();
    	grp.initPrint(this.getDesktopPane());

    }
    
    /** called from actionListener of jButton5. */
    public void selectExistingGRN(){
    	int r=jTable3.getSelectedRow();
    	if(r<0){
    		return;
    	}
    	grnNo=jTable3.getModel().getValueAt(r, 0).toString();
    	poNo=jTable3.getModel().getValueAt(r, 1).toString();
    	
    	fillGrnStatus();
    }
    
    /** called from selectExistingGRN(). */
    private void fillGrnStatus(){
    	grnStatus=new inventorycontroller.function.GoodsReceiveNoteProcessor(dbInterface1)
    	 .prepareGrnStatus(this.grnNo);
    	
    	jTable2.setModel(new javax.swing.table.DefaultTableModel(
            grnStatus,
            new String [] {
                "Sl No.", 
                "Material ID", 
                "Material Name", 
                "Received Qty. (Excisable)", 
                "Received Qty. (General)", 
                "Used Qty. (Excisable)", 
                "Used Qty. (General)"
            }
        ){
        	public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        
        checkStatus();
    }
    
    /** called from fillGrnStatus(). */
    private void checkStatus(){
    	javax.swing.table.TableModel tm=jTable2.getModel();
    	int rc=tm.getRowCount();
    	
    	int i=0;
    	for (i = 0; i<rc; i++){
    		if(
    		 Double.parseDouble(tm.getValueAt(i, 5).toString())>0 || 
    		 Double.parseDouble(tm.getValueAt(i, 6).toString())>0
    		){
    			break;
    		}
    	}
    	if(i<rc){
    		this.grcNo="";
    		updateStatusMsg("Materials in use, cannot cancel GRN");
    	}
    	else{
    		this.grcNo=this.grnNo.replaceFirst(
    			"GRN", 
    			inventorycontroller.function.GoodsReceiveNoteProcessor.GRN_CANCELLATION_ID
    		);
    		updateStatusMsg(
    			"Cancel this GRN, Cancellation ID: "+this.grcNo
    		);
    	}
    }
    
    /** called from actionListener of jButton6. */
    private void selectCancelledGRN(){
    	
    }
    
    /** called from actionListener of jButton1. */
    private void insertPerform(){
    	if(!verify()){
    		return;
    	}
    	
    	String val=new String("");
    	String[][] tmp=null;
    	try{
    		tmp=dbInterface1.cmdSelect(
    			"grnMaster", 
    			"*", 
    			"grnMaster.grnNo='"+this.grnNo+"'"
    		);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	
    	val=val+"'"+this.grcNo+"', "; // grcNo
    	val=val+"'"+tmp[0][1]+"', "; // poNo
    	val=val+"'"+
    		inventorycontroller.function.DateProcessor
    		 .getCurrentDate(dbInterface1)
    		+"', "; // grcDate
    	val=val+"'"+tmp[0][3]+"', "; // challanNo
    	val=val+"'"+tmp[0][4]+"', "; // challanDate
    	val=val+"'"+tmp[0][5]+"', "; // billNo
    	val=val+"'"+tmp[0][6]+"', "; // billDate
    	val=val+"'"+tmp[0][7]+"', "; // grcAmt
    	val=val+"'"+tmp[0][8]+" '"; // remark
    	
    	try{
    		dbInterface1.cmdInsert("grnMaster", val);
    		insertGrnDesc();
	    	updatePO();
	    	updateMaterialStock();
    	}
    	catch(java.sql.SQLException ex){
    		ex.printStackTrace();
    	}
    	catch(java.lang.ClassNotFoundException ex){
    		ex.printStackTrace();
    	}
    	resetPerform();
    }
    
    private boolean verify(){
    	return this.grcNo.length()!=0;
    }
    
    /** called from insertPerform(). */
    private void insertGrnDesc() throws java.sql.SQLException, java.lang.ClassNotFoundException {
    	//cols: grnNo, matNo, grnQty, dsc, sch, ed, vat, cst, freight, TSid
    	
    	String[][] tmp=null;
    	try{
    		tmp=dbInterface1.cmdSelect(
    			"grnDesc", 
    			"*", 
    			"grnDesc.grnNo='"+this.grnNo+"'"
    		);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	
    	inventorycontroller.function.TimeStampProcessor tsp=null;
    	String date=inventorycontroller.function.DateProcessor
    	 .getCurrentDate(dbInterface1);
    	String tsid="";
    	String[] val=new String[tmp.length];
    	
    	for (int i = 0; i<val.length; i++){
    		// if qty ==0 then ommit that row.
    		val[i]=new String("'"+this.grcNo); // 'grnNo'
    		
    		for(int j = 1; j<10; j++){ // matNo, grnQty, dsc, sch, ed, vat, cst, freight, netamt
    			val[i]=val[i]+"', '"+tmp[i][j];
    		}
    		tsid=tsp.getNewTSid(date, dbInterface1);
    		val[i]=val[i]+"', '"+tsid+"'"; // TSid
	    	dbInterface1.cmdInsert("grnDesc", val[i]);
	    	tsp.updateTSid(tsid, date, dbInterface1);
    	}
    }
    
    /** called from insertPerform(). */
    private void updatePO() {
    	inventorycontroller.function.PurchaseOrderProcessor pp=
    	 new inventorycontroller.function.PurchaseOrderProcessor(dbInterface1);
    	 
    	boolean isPending=pp.isPending(this.poNo);
    	String poStatus=new String(isPending?"pending":"incomplete");
    	pp.setPoStatus(poStatus, this.poNo);
    }
    
    /** called from insertPerform(). */
    private void updateMaterialStock() throws java.sql.SQLException {
    	//cols: grnNo, matNo, grnQty, dsc, sch, ed, vat, cst, freight, netAmt, TSid
    	String[][] tmp=null;
    	String[] amts=null;
    	String val="";
		double[] newAmts=new double[7];
    	try{
    		tmp=dbInterface1.cmdSelect(
    			"grnDesc", 
    			"*", 
    			"grnDesc.grnNo='"+this.grnNo+"'"
    		);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	
    	String date=inventorycontroller.function.DateProcessor
    	 .getCurrentDate(dbInterface1);
    	 
    	inventorycontroller.function.MaterialProcessor mp=
    	 new inventorycontroller.function.MaterialProcessor(dbInterface1);
    	
    	String matId="";
    	double[] diff=null;
    	//tmp[i] format: {grnNo, matNo, grnQty, dsc, sch, ed, vat, cst, freight, amt, TSid}
    	for (int i = 0; i<tmp.length; i++){
    		matId=tmp[i][1];
    		diff=new inventorycontroller.function.GoodsReceiveNoteProcessor(dbInterface1)
    		 .getCancelledQty(tmp[i]);
    		amts=mp.getAmounts(matId);
			//amts format: {stkGen, stkEx, vatGen, vatEx, ed, amtGen, amtEx}
			//diff format: same with amts.
			
			newAmts[0]=Double.parseDouble(amts[0])-diff[0];
			newAmts[1]=Double.parseDouble(amts[1])-diff[1];
			newAmts[2]=Double.parseDouble(amts[2])-diff[2];
			newAmts[3]=Double.parseDouble(amts[3])-diff[3];
			newAmts[4]=Double.parseDouble(amts[4])-diff[4];
			newAmts[5]=Double.parseDouble(amts[5])-diff[5];
			newAmts[6]=Double.parseDouble(amts[6])-diff[6];
			
			val=new String("");
			val=val+   "recDate='"+date;
			val=val+"', matStockGeneral='"+newAmts[0];
			val=val+"', matStockExcisable='"+newAmts[1];
			val=val+"', matVATGeneral='"+newAmts[2];
			val=val+"', matVATExcisable='"+newAmts[3];
			val=val+"', matED='"+newAmts[4];
			val=val+"', matAmtGeneral='"+newAmts[5];
			val=val+"', matAmtExcisable='"+newAmts[6];
			val=val+"'";
			
			dbInterface1.cmdUpdate("matMaster", val, "matId='"+matId+"'");
			
			
    	}
    	
    }
    
    /** called from actionListener of jButton2. */
    private void resetPerform(){
    	
        updateStatusMsg("No GRN is selected");
        this.grnNo="";
        this.grcNo="";
        this.poNo="";
    	
    	resetExistingGrnList();
    	//resetCancelledGrnList(); //* as cancelled GRN List is not shown.
    	resetGrnStatus();
    }
    
    /** called from resetPerform()
     *              checkStatus(). */
    private void updateStatusMsg(String msg){
    	jLabel4.setText(msg);
    }
    
    /** called from resetPerform(). */
    private void resetExistingGrnList(){
    	existingGrnList=new inventorycontroller.function.GoodsReceiveNoteProcessor(dbInterface1)
    	 .getGrnForGrnCancellation();
    	
    	jTable3.setModel(new javax.swing.table.DefaultTableModel(
            existingGrnList,
            new String [] {
                "GRN No.", "PO No.", "GRN Date", "Vendor Name"
            }
        ){
        	public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
    }
    
    /** called from resetPerform(). */
    private void resetCancelledGrnList(){
    	/*
    	//cancelledGrnList=
    	
    	jTable1.setModel(new javax.swing.table.DefaultTableModel(
            cancelledGrnList,
            new String [] {
                "Cancelled GRN No.", "PO No.", "GRN Date", "Vendor Name"
            }
        ){
        	public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });*/ // as cancelled GRN List is not shown.
    }
    
    /** called from resetPerform(). */
    private void resetGrnStatus(){
    	jTable2.setModel(new javax.swing.table.DefaultTableModel(
            null,
            new String [] {
                "Sl No.", 
                "Material ID", 
                "Material Name", 
                "Received Qty. (Excisable)", 
                "Received Qty. (General)", 
                "Used Qty. (Excisable)", 
                "Used Qty. (General)"
            }
        ));
    }
    
    /** called from actionListener of jButton3. */
    private void exitPerform(){
    	try{
    		this.setClosed(true);
    	}
    	catch(java.lang.Exception ex){
    		System.out.println (ex);
    	}
    }
    
    
    /** overriden method of JInternalFrame */
    public void setIcon(boolean b){
    	try{
    		super.setIcon(b);
    	}
    	catch(java.lang.Exception ex){
    		System.out.println (ex);
    	}
    	if(!b){
    		getRootPane().setDefaultButton(jButton1);
    	}
    }
    
    
    // Variables declaration -
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    
    private inventorycontroller.function.DbInterface dbInterface1;
    private String grnNo;
    private String grcNo;
    private String poNo;
    private String[][] existingGrnList;
    private String[][] cancelledGrnList;
    private String[][] grnStatus;
    // End of variables declaration
    
}
