/*
 * JInternalFrameExcessRequisition.java
 *
 * Created on September 3, 2007, 20:11 HRS
 * Last Modified on September 25, 2007, 14:51 HRS
 *
 */

package inventorycontroller.display;

/**
 *
 * @author  brinto
 */
public class JInternalFrameExcessRequisition extends javax.swing.JInternalFrame {
    
    /** Creates new form JInternalFrameExcessRequisition */
    public JInternalFrameExcessRequisition(inventorycontroller.function.DbInterface dbi) {
        initComponents(dbi);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents(inventorycontroller.function.DbInterface dbi) {
    	dbInterface1=dbi;
    	jobList=null;
    	trnDet=null;
    	
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JLabel();
        jSpinner1 = new inventorycontroller.display.RDateSpinner(inventorycontroller.display.RDateSpinner.DD_MM_YYYY);
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jButton4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setTitle("Excess Requisition");
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        jLabel2.setText("Job No.");

        jLabel3.setText("Requisition No.");

        jLabel4.setText("Requisition Date");

        jLabel5.setText("Remarks");

        jTextField1.setText("jTextField1");

        jTextField2.setText("jTextField2");

        jLabel6.setText("Select Job:");

        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setAutoCreateRowSorter(false);
        jScrollPane2.setViewportView(jTable1);

        jButton1.setText("Save Excess Requisition");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertPerform();
            }
        });

        jScrollPane1.setViewportView(jTextPane1);

        jButton4.setText("Select Job");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jobSelected();
            }
        });

        jTable2.setAutoCreateRowSorter(false);
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane3.setViewportView(jTable2);

        jLabel7.setText("Requisition Description:");

        jButton5.setText("Print Last Excess Requisition Slip");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generatePrintPreview();
            }
        });
        ///
		///
        
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener(){
        	public void stateChanged(javax.swing.event.ChangeEvent evt){
        		String date=inventorycontroller.util.DateUtil.getRawFormat(
        			(java.util.Date)jSpinner1.getValue()
        		);
		        jTextField2.setText(
		    		new inventorycontroller.function.GoodsIssueReturnProcessor(dbInterface1)
		    		 .getNewTrnNo(
		    		 	inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_EXCESS_REQUISITION, 
		    		 	date
		    		 )
		    	);
        	}
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
            	.addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSpinner1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)))
                            .addComponent(jLabel5))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)))
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, 220, 320, 320)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, 220, 320, 320)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetPerform();
            }
        });

        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
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

        jLabel1.setBackground(new java.awt.Color(127, 157, 185));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");
        jLabel1.setOpaque(true);

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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        this.getRootPane().setDefaultButton(jButton1);
    //* ENDING: initComponents.
    }

    private void generatePrintPreview() {
		//this.getDesktopPane().getRootPane().getJMenuBar().getMenu(4).getItem(4).doClick(4);
		String[][] grcNo=null;
    	String grcid="";
		String yr=inventorycontroller.util.DateUtil.getFinancialYear(
			inventorycontroller.function.DateProcessor.getCurrentDate(this.dbInterface1)
		);
		grcid=inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_EXCESS_REQUISITION_ID;
    	try{
    		grcNo=this.dbInterface1.cmdSelect(
    			"gdsJbTrnDesc", 
    			
    			"gdsJbTrnDesc.TSid, "+
    			"gdsJbTrnDesc.trnNo", 
    			
    			"gdsJbTrnDesc.trnNo like '"+grcid+"%' and "+
    			"gdsJbTrnDesc.TSid like '%"+yr+"' order by gdsJbTrnDesc.TSid"
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
    	
    	inventorycontroller.function.GoodsIssueReturnReportProcessor grp=
    	 new inventorycontroller.function.GoodsIssueReturnReportProcessor(
    	 	dbInterface1, 
    	 	new String[]{grcNo[grcNo.length-1][1]}, 
    	 	grcid
    	 );
    	grp.generate();
    	grp.initPrint(this.getDesktopPane());
    }

    /** called from actionListener of jButton4 */
    private void jobSelected() {
    	int r=jTable1.getSelectedRow();
    	if(r<0){
    		return;
    	}
    	String jbNo=jTable1.getModel().getValueAt(r, 2).toString();
    	jTextField1.setText(jbNo);
    	
    	fillTrnTable();
    	
    }
    
    /** called from jobSelected() */
    private void fillTrnTable(){
    	
		trnDet=new inventorycontroller.function.GoodsIssueReturnProcessor(dbInterface1)
		 .prepareExcessRequisition(jTextField1.getText());
    	
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            trnDet,
            new String [] {
                "Sl No.", 
                "Material ID", 
                "Material", 
                "Required Qty", 
                "Issue", 
                "Issued (Excisable)", 
                "Issued (General)"
            }
        ) {
            public Class getColumnClass(int columnIndex) {
                return java.lang.String.class;
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex==4;
            }
        });
        jTable2.getModel().addTableModelListener(new javax.swing.event.TableModelListener(){
        	public void tableChanged(javax.swing.event.TableModelEvent evt){
        		jTable2.getModel().removeTableModelListener(this);
        		trnTableEdited(evt);
        		jTable2.getModel().addTableModelListener(this);
        	}
        });
    }
    
    /** called from TableModelListener of jTable2. */
    private void trnTableEdited(javax.swing.event.TableModelEvent evt){
    	int r=evt.getFirstRow();
    	javax.swing.table.TableModel m=(javax.swing.table.TableModel)evt.getSource();
    	double stk=Double.parseDouble(
    		new inventorycontroller.function.MaterialProcessor(dbInterface1).getStock(
    			m.getValueAt(r, 1).toString() // matId
    		)
    	);
    	String ul=stk+"";
    	int trnType=inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_EXCESS_REQUISITION;
    	String[] tmp1=new inventorycontroller.function.GoodsIssueReturnProcessor(dbInterface1)
    	 .calculateQtyDivision(
    	 	jTextField1.getText(), //jobNo
    	 	m.getValueAt(r, 1).toString(), //matNo
    	 	m.getValueAt(r, 4).toString(), //qty
    	 	ul, //upperLimit
    	 	trnType //type
    	 );
    	for (int i = 0; i<3; i++){
    		m.setValueAt(tmp1[i], r, i+4);
    	}
    	
    }

    private void insertPerform() {
    	if(!verify()){
    		return;
    	}
    	String val="";
    	val=val+   "'"+jTextField2.getText(); // trnNo
    	val=val+"', '"+jTextField1.getText(); // jbNo
    	val=val+"', '"+inventorycontroller.util.DateUtil.getRawFormat(
			(java.util.Date)jSpinner1.getValue()
		); // trnDate
    	val=val+"', '"+jTextPane1.getText()+" '"; // remark
    	try{
    		dbInterface1.cmdInsert("gdsJbTrnMaster", val);
    		//System.out.println (val);
    		insertTrn();
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	resetPerform();
    }
    
    private boolean verify(){
    	//* jobNo cannot be empty.
    	if(jTextField1.getText().length()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"No job is selected. Select one job first\"</center></html>",
				"InventoryController says...",
			javax.swing.JOptionPane.ERROR_MESSAGE);
			jTable1.grabFocus();
			jTable1.changeSelection(0, 0, false, false);
			jTable1.changeSelection(0, 0, false, true);
			return false;
    	}
    	
    	//* date cannot be in future.
    	String dtEr=inventorycontroller.util.DateUtil.getRawFormat(
    		(java.util.Date)this.jSpinner1.getValue()
    	);
    	String dtCur=inventorycontroller.function.DateProcessor
    	 .getCurrentDate(this.dbInterface1);
    	if(dtEr.compareTo(dtCur)>0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Requisition Date yet not came\"</center></html>",
				"InventoryController says...",
			javax.swing.JOptionPane.ERROR_MESSAGE);
			jSpinner1.grabFocus();
			return false;
    	}
    	
    	//* requisition table should not be empty.
    	javax.swing.table.TableModel tm=jTable2.getModel();
    	if(tm.getRowCount()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Excess Requisition cannot be done for the job.<br>"+
				"Allocated Material not finished yet\"</center></html>",
				"InventoryController says...",
			javax.swing.JOptionPane.ERROR_MESSAGE);
			jTable1.grabFocus();
			jTable1.changeSelection(0, 0, false, false);
			jTable1.changeSelection(0, 0, false, true);
			return false;
    	}
    	
    	//* at least one material should be issued.
    	boolean isIssued=false;
    	for (int i = 0; i<tm.getRowCount(); i++){
    		if(Double.parseDouble(tm.getValueAt(i, 4).toString())>0){
    			isIssued=true;
    			break;
    		}
    	}
    	if(!isIssued){
			javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"At least one material should be issued / returned\"</center></html>",
				"InventoryController says...",
			javax.swing.JOptionPane.ERROR_MESSAGE);
			jTable2.grabFocus();
			jTable2.changeSelection(0, 4, false, false);
			jTable2.changeSelection(0, 4, false, true);
			return false;
    	}
    	
    	return true;
    }
    
    /** called from insertPerform(). */
    private void insertTrn() throws Exception {
    	String trnNo=jTextField2.getText();
    	String jobNo=jTextField1.getText();
    	java.util.Vector<Integer> v=new java.util.Vector<Integer>();
    	javax.swing.table.TableModel tm=
    	 (javax.swing.table.TableModel)jTable2.getModel();
    	for (int i = 0; i<tm.getRowCount(); i++){
    		if(Double.parseDouble(tm.getValueAt(i, 4).toString())>0){
    			v.add(i);
    		}
    	}
    	String[][] trnDet=new String[v.size()][9];
    	int r=0;
    	String[] amts=null;
		inventorycontroller.function.MaterialProcessor mp=
    	 new inventorycontroller.function.MaterialProcessor(dbInterface1);
		inventorycontroller.function.GoodsIssueReturnProcessor girp=
    	 new inventorycontroller.function.GoodsIssueReturnProcessor(dbInterface1);
    	double tmp=0;
    	for (int i = 0; i<v.size(); i++){
    		trnDet[i][0]=new String(trnNo); //trnNo
    		r=v.elementAt(i);
    		trnDet[i][1]=new String(jTable2.getValueAt(r, 1).toString()); //matNo
    		trnDet[i][2]=new String(jTable2.getValueAt(r, 6).toString()); //qtyGen
    		trnDet[i][3]=new String(jTable2.getValueAt(r, 5).toString()); //qtyEx
    		
    		amts=mp.getAmounts(trnDet[i][1]);
			//amts format: {stkGen, stkEx, vatGen, vatEx, ed, amtGen, amtEx}
			
			tmp=Double.parseDouble(trnDet[i][2])*
			 Double.parseDouble(amts[5])/Double.parseDouble(amts[0]);
			tmp=Double.parseDouble(amts[0])==0? 0: tmp;
			trnDet[i][4]=new String(tmp+""); //amtGen
			
			tmp=Double.parseDouble(trnDet[i][3])*
			 Double.parseDouble(amts[6])/Double.parseDouble(amts[1]);
			tmp=Double.parseDouble(amts[1])==0? 0: tmp;
			trnDet[i][5]=new String(tmp+""); //amtEx
			
			tmp=Double.parseDouble(trnDet[i][2])*
			 Double.parseDouble(amts[2])/Double.parseDouble(amts[0]);
			tmp=Double.parseDouble(amts[0])==0? 0: tmp;
			trnDet[i][6]=new String(tmp+""); //matVatGen
			
			tmp=Double.parseDouble(trnDet[i][3])*
			 Double.parseDouble(amts[3])/Double.parseDouble(amts[1]);
			tmp=Double.parseDouble(amts[1])==0? 0: tmp;
			trnDet[i][7]=new String(tmp+"");//matVatEx
			
			tmp=Double.parseDouble(trnDet[i][3])*
			 Double.parseDouble(amts[4])/Double.parseDouble(amts[1]);
			tmp=Double.parseDouble(amts[1])==0? 0: tmp;
			trnDet[i][8]=new String(tmp+""); //amtEd
    	}
    	
		insertTrnDesc(trnDet);
		updateMatStock(trnDet);
    }
    
    /** called from insertPerform(). */
    private void insertTrnDesc(String[][] trnDet) throws Exception {
    	String[] vals=new String[trnDet.length];
    	inventorycontroller.function.TimeStampProcessor tsp=null;
    	String date=inventorycontroller.util.DateUtil.getRawFormat(
			(java.util.Date)jSpinner1.getValue()
		);
    	String tsid="";
    	for (int i = 0; i<trnDet.length; i++){
    		vals[i]=new String("");
    		vals[i]=vals[i]+"'"+trnDet[i][0];
    		for (int j = 1; j<9; j++){
    			vals[i]=vals[i]+"', '"+trnDet[i][j];
    		}
    		tsid=tsp.getNewTSid(date, dbInterface1);
    		vals[i]=vals[i]+"', '"+tsid+"'"; // TSid
	    	dbInterface1.cmdInsert("gdsJbTrnDesc", vals[i]);
	    	tsp.updateTSid(tsid, date, dbInterface1);
    	}
    }
    
    /** called from insertPerform(). */
    private void updateMatStock(String[][] trnDet) throws Exception {
		String[] amts=null;
		double[] newAmts=new double[7];
		double tmp=0;
		String date=inventorycontroller.function.DateProcessor
    	 .getCurrentDate(dbInterface1);
		inventorycontroller.function.MaterialProcessor mp=
    	 new inventorycontroller.function.MaterialProcessor(dbInterface1);
    	
		int type=-1; // reduction from inventory
		
		String val=new String("");
		for (int i = 0; i<trnDet.length; i++){
			amts=mp.getAmounts(trnDet[i][1]);
			//amts format: {stkGen, stkEx, vatGen, vatEx, ed, amtGen, amtEx}
			
			newAmts[0]=Double.parseDouble(amts[0])+(Double.parseDouble(trnDet[i][2])*type);
			newAmts[1]=Double.parseDouble(amts[1])+(Double.parseDouble(trnDet[i][3])*type);
			newAmts[2]=Double.parseDouble(amts[2])+(Double.parseDouble(trnDet[i][6])*type);
			newAmts[3]=Double.parseDouble(amts[3])+(Double.parseDouble(trnDet[i][7])*type);
			newAmts[4]=Double.parseDouble(amts[4])+(Double.parseDouble(trnDet[i][8])*type);
			newAmts[5]=Double.parseDouble(amts[5])+(Double.parseDouble(trnDet[i][4])*type);
			newAmts[6]=Double.parseDouble(amts[6])+(Double.parseDouble(trnDet[i][5])*type);
			
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
			
			dbInterface1.cmdUpdate("matMaster", val, "matId='"+trnDet[i][1]+"'");
		}
    	
    }

    private void resetPerform() {
    	jTextField1.setText("");
    	jTextField2.setText(
    		new inventorycontroller.function.GoodsIssueReturnProcessor(dbInterface1)
    		 .getNewTrnNo(
    		 	inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_EXCESS_REQUISITION, 
    		 	inventorycontroller.function.DateProcessor.getCurrentDate(this.dbInterface1)
    		 )
    	);
    	jSpinner1.setValue(
    		inventorycontroller.util.DateUtil.getDateValue(
    			inventorycontroller.function.DateProcessor.getCurrentDate(dbInterface1)
    		)
    	);
    	jTextPane1.setText("");
    	
    	resetJobList();
    	resetRequisitionDet();
    }
    
    private void resetJobList(){
    	jobList=new inventorycontroller.function.JobProcessor(dbInterface1)
    	 .getJobsForExcessRequisition();
    	
		jTable1.setModel(new javax.swing.table.DefaultTableModel(
            jobList,
            new String [] {
                "Customer Name", "Order No.", "Job No.", "Product", "Quantity"
            }
        ) {
            public Class getColumnClass(int columnIndex) {
                return java.lang.String.class;
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
    }
    
    private void resetRequisitionDet(){
    	jTable2.setModel(new javax.swing.table.DefaultTableModel(
            null,
            new String [] {
                "Sl No.", 
                "Material ID", 
                "Material", 
                "Required Qty", 
                "Issue", 
                "Issued (Excisable)", 
                "Issued (General)"
            }
        ));
    }

    private void exitPerform() {
    	try{
    		this.setClosed(true);
    	}
    	catch(java.lang.Exception ex){
    		ex.printStackTrace(); 
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private inventorycontroller.display.RDateSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel jTextField1;
    private javax.swing.JLabel jTextField2;
    private javax.swing.JTextPane jTextPane1;
    
    private inventorycontroller.function.DbInterface dbInterface1;
    String[][] jobList;
    Object[][] trnDet;
    // End of variables declaration
    
}
