/*
 * JInternalFrameGoodsReceiveNote.java
 *
 * Created on August 4, 2007, 10:40 HRS
 * Last Modified on August 21, 2007, 13:45 HRS
 *
 */

package inventorycontroller.display;

/**
 *
 * @author  brinto
 */
public class JInternalFrameGoodsReceiveNote extends javax.swing.JInternalFrame {
    
    /** Creates new form JInternalFrameGoodsReceiveNote */
    public JInternalFrameGoodsReceiveNote(inventorycontroller.function.DbInterface dbi) {
        initComponents(dbi);
    }
    
    /** This method is called from within the constructor to initialize the form. */
    private void initComponents(inventorycontroller.function.DbInterface dbi) {
    	dbInterface1=dbi;
    	
    	jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jXDatePicker1=new inventorycontroller.display.RDateSpinner(inventorycontroller.display.RDateSpinner.DD_MM_YYYY);
        jXDatePicker2=new inventorycontroller.display.RDateSpinner(inventorycontroller.display.RDateSpinner.DD_MM_YYYY);
        
	    poList=null;
	    bomList=null;
	    grnDet=null;
	    poDet=null;
	    grnTotal=0.0;
        
        jLabel1.setBackground(new java.awt.Color(127, 157, 185));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");
        jLabel1.setOpaque(true);

        setTitle("Goods Receive Note");
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        jLabel2.setText("GRN Date");

        jLabel3.setText("PO No.");

        jLabel4.setText("Vendor No.");

        jLabel5.setText("Challan No.");

        jLabel6.setText("Challan Date");

        jLabel7.setText("Remarks");

        jScrollPane1.setViewportView(jTextPane1);

        jScrollPane2.setViewportView(jList1);

        jLabel8.setText("BOM List & BOM Date:");

        jLabel9.setText("GRN No.");

        jLabel12.setText("GRN Total");
        
        jXDatePicker2.addChangeListener(new javax.swing.event.ChangeListener(){
        	public void stateChanged(javax.swing.event.ChangeEvent evt){
        		String date=inventorycontroller.util.DateUtil.getRawFormat(
        			(java.util.Date)jXDatePicker2.getValue()
        		);
        		jTextField1.setText(
					new inventorycontroller.function.GoodsReceiveNoteProcessor(dbInterface1)
					 .getNewGrnNo(date)
				);
        	}
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(jXDatePicker2, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                    	.addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    		.addComponent(jLabel5)
                    		.addComponent(jLabel6)
                    		.addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                            .addComponent(jXDatePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                            .addComponent(jLabel13))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jXDatePicker2, 20, 20, 20)
                    .addComponent(jLabel6)
                    .addComponent(jXDatePicker1, 20, 20, 20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jTabbedPane1.addTab("Goods Receive Note", jPanel3);

        jLabel10.setText("Purchase Order List:");

        jTable1.setAutoCreateRowSorter(false);
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jTable1);

        jButton4.setText("<html><center>Select<br>Purchase Order</center></html>");
        jButton4.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		poSelected();
        	}
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                    .addComponent(jLabel10))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jTabbedPane1.addTab("Purchase Order Selection", jPanel4);

        jTable2.setAutoCreateRowSorter(false);
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(jTable2);

        jLabel11.setText("GRN Details:");

        jButton1.setText("Save Goods Receive Note");
        jButton1.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		insertPerform();
        	}
        });

        jButton5.setText("Print Last Goods Receive Note");
        jButton5.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		createPrintView();
        	}
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 320, Short.MAX_VALUE)
                        .addComponent(jButton1, 320, 320, 320)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, 320, 320, 320)
                        .addGap(0, 320, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		resetPerform();
        	}
        });
        jButton2.setMaximumSize(new java.awt.Dimension(120, 22));
        jButton2.setMinimumSize(new java.awt.Dimension(120, 22));
        jButton2.setPreferredSize(new java.awt.Dimension(120, 22));

        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		exitPerform();
        	}
        });
        jButton3.setMaximumSize(new java.awt.Dimension(120, 22));
        jButton3.setMinimumSize(new java.awt.Dimension(120, 22));
        jButton3.setPreferredSize(new java.awt.Dimension(120, 22));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
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
    
    /** called from actionPerformed of jButton4 */
    private void poSelected(){
    	String poNo=jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0).toString();
    	jTextField3.setText(poNo);
    	String vndName=jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 2).toString();
    	jTextField4.setText(
    		new inventorycontroller.function.VendorProcessor(dbInterface1)
    		 .getVndNo(vndName)+" ("+vndName+")"
    	);
    	fillBomList(poNo);
    	fillGrnDet(poNo);
    	
    	jTabbedPane1.setSelectedIndex(0);
    }
    
    /** called from poSelected */
    private void fillBomList(String poNo){
		bomList=new inventorycontroller.function.PurchaseOrderProcessor(dbInterface1)
    	 .getBomList(poNo);
    	jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = bomList;
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
    }
    
    /** called from poSelected */
    private void fillGrnDet(String poNo){
    	poDet=new inventorycontroller.function.PurchaseOrderProcessor(dbInterface1)
    	 .getPoDesc(poNo);
    	grnDet=new inventorycontroller.function.GoodsReceiveNoteProcessor(dbInterface1)
    	 .prepareGRN(poDet);
    	
    	jTable2.setModel(new javax.swing.table.DefaultTableModel(
            grnDet,
            new String [] {
                "Sl No.", "Item", 
                "Remaining Qty.", "Qty. Supplied", "Qty. Rejected", "Qty. Accepted", 
                "Gross Amount", 
                "Discount(Rs.)", "Surcharge(Rs.)", 
                "Excise Duty(Rs.)", "ED included", 
                "VAT(Rs.)", "CST(Rs.)", "Freight / Insurance(Rs.)", 
                "Net Amount", "Net Rate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, 
                java.lang.String.class, 
                java.lang.String.class, 
                java.lang.String.class, 
                java.lang.String.class, 
                java.lang.String.class, 
                java.lang.String.class, 
                java.lang.String.class, 
                java.lang.String.class, 
                java.lang.String.class, 
                java.lang.Boolean.class, 	
                java.lang.String.class, 
                java.lang.String.class, 
                java.lang.String.class, 
                java.lang.String.class, 
                java.lang.String.class, 
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, false, false, true,
                true, true, true, true, true, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.getModel().addTableModelListener(new javax.swing.event.TableModelListener(){
        	public void tableChanged(javax.swing.event.TableModelEvent evt){
        		jTable2.getModel().removeTableModelListener(this);
        		try{
        			grnTableEdited(evt);
        		}
        		catch(Exception ex){
        			System.out.println (ex);
        		}
        		finally{
        			jTable2.getModel().addTableModelListener(this);
        		}
        		
        	}
        });
    	updateGrnTotal();
    }
    
    /** this TableModelListener is added from within fillGrnDet. */
    private void grnTableEdited(javax.swing.event.TableModelEvent evt)throws Exception{
    	Object[] tmp0=new Object[16];
    	int r=evt.getFirstRow();
    	int c=evt.getColumn();
    	javax.swing.table.TableModel m=(javax.swing.table.TableModel)evt.getSource();
    	for (int i = 0; i<16; i++){
    		tmp0[i]=m.getValueAt(r, i);
    	}
    	
    	Object[] tmp1=new inventorycontroller.function.GoodsReceiveNoteProcessor(dbInterface1)
    	 .calculateNetAmt(tmp0, c, poDet[Integer.parseInt(tmp0[0].toString())-1]);
    	/*for (int i = 0; i<tmp1.length; i++){
    		System.out.println (tmp1[i].toString());
    	}*/
    	for (int i = 0; i<16; i++){
    		m.setValueAt(tmp1[i], r, i);
    	}
    	updateGrnTotal();
    }
    
    /** this method is called from grnTableEdited. */
    private void updateGrnTotal(){
    	int rc=jTable2.getModel().getRowCount();
    	grnTotal=0.0;
    	for (int i = 0; i<rc; i++){
    		grnTotal+=Double.parseDouble(jTable2.getModel().getValueAt(i, 14).toString());
    	}
    	jLabel13.setText("Rs. "+grnTotal);
    }
    
    /** called from actionPerformed of jButton5 */
    private void createPrintView(){
    	//this.getDesktopPane().getRootPane().getJMenuBar().getMenu(4).getItem(3).doClick(4);
    	String[][] grnNo=null;
    	String grnid=inventorycontroller.function.GoodsReceiveNoteProcessor.GRN_ID;
		String yr=inventorycontroller.util.DateUtil.getFinancialYear(
			inventorycontroller.function.DateProcessor.getCurrentDate(this.dbInterface1)
		);
    	try{
    		grnNo=this.dbInterface1.cmdSelect(
    			"grnDesc", 
    			
    			"grnDesc.TSid, "+
    			"grnDesc.grnNo", 
    			
    			"grnDesc.grnNo like '"+grnid+"%' and "+
    			"grnDesc.TSid like '%"+yr+"' order by grnDesc.TSid"
    		);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	if(grnNo.length==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Nothing to print\"</center></html>",
				"InventoryController says...",
			javax.swing.JOptionPane.ERROR_MESSAGE);
			return;
    	}
    	
    	inventorycontroller.function.GrnReportProcessor grp=
    	 new inventorycontroller.function.GrnReportProcessor(
    	 	dbInterface1, 
    	 	new String[]{grnNo[grnNo.length-1][1]}
    	 );
    	grp.generate();
    	grp.initPrint(this.getDesktopPane());
    }
    
    /** called from actionPerformed of jButton1 */
    private void insertPerform(){
    	if(!verify()){
    		return;
    	}
    	String val=new String("");
    	val=val+"'"+jTextField1.getText()+"', "; // grnNo
    	val=val+"'"+jTextField3.getText()+"', "; // poNo
    	val=val+"'"+
    		inventorycontroller.util.DateUtil.getRawFormat(
    			(java.util.Date)jXDatePicker2.getValue()
    		)
    		+"', "; // grnDate
    	val=val+"'"+jTextField5.getText()+"', "; // challanNo
    	val=val+"'"+
    		inventorycontroller.util.DateUtil.getRawFormat(
    			(java.util.Date)jXDatePicker1.getValue()
    		)
    		+"', "; // challanDate
    	val=val+"'billNo', "; // billNo
    	val=val+"'billDate', "; // billDate
    	val=val+"'"+grnTotal+"', "; // grnTotal
    	val=val+"'"+jTextPane1.getText()+" '"; // remark
    	
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
    	printRejNote();
    	resetPerform();
    }
    
    private void printRejNote(){
    	//* at least one material rejected qty should be >0
    	java.util.Vector<java.util.Vector<String>> rejList=
    	 new java.util.Vector<java.util.Vector<String>>(); //* {item, sup qty, rej qty}
    	java.util.Vector<String> v=null;
    	
    	javax.swing.table.TableModel tm=jTable2.getModel();
    	for (int i = 0; i<tm.getRowCount(); i++){
    		if(
    			Double.parseDouble(tm.getValueAt(i, 4).toString())>0
    		){
    			v=new java.util.Vector<String>();
    			v.add(tm.getValueAt(i, 1).toString());
    			v.add(tm.getValueAt(i, 3).toString());
    			v.add(tm.getValueAt(i, 4).toString());
    			rejList.add(v);
    		}
    	}
    	if(rejList.size()==0){
			return;
    	}
    	int i=javax.swing.JOptionPane.showInternalConfirmDialog(this,
			"<html><center>\"Do you want to print Rejection Note?\"</center></html>",
			"InventoryController says...",
		javax.swing.JOptionPane.YES_NO_OPTION, 
		javax.swing.JOptionPane.QUESTION_MESSAGE);
		
		if(i==javax.swing.JOptionPane.NO_OPTION){
			return;
		}
		
		String[] det=new String[3];
		det[0]=jTextField1.getText();
		det[1]=inventorycontroller.util.DateUtil.getDisplayFormat(
			inventorycontroller.util.DateUtil.getRawFormat(
    			(java.util.Date)jXDatePicker2.getValue()
    		)
    	);
		det[2]=jTextField3.getText();
		
		inventorycontroller.function.RejectionNoteReportProcessor rp=
		 new inventorycontroller.function.RejectionNoteReportProcessor(dbInterface1, det, rejList);
		rp.generate();
		rp.initPrint(this.getDesktopPane());
    }
    
    /** called from insertPerform(). */
    private boolean verify(){
    	//* poNo cannot be empty
    	if(jTextField3.getText().length()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"No Purchase Order is selected. Select one Purchase Order first\"</center></html>",
				"InventoryController says...",
			javax.swing.JOptionPane.ERROR_MESSAGE);
			jTabbedPane1.setSelectedIndex(1);
			return false;
    	}
    	
    	//* challan no cannot be empty
    	if(jTextField5.getText().length()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Challan No. field cannot be left empty\"</center></html>",
				"InventoryController says...",
			javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField5.grabFocus();
			return false;
    	}
    	
    	//* date cannot be in future
    	String dtGrn=inventorycontroller.util.DateUtil.getRawFormat(
    		(java.util.Date)this.jXDatePicker2.getValue()
    	);
    	String dtCh=inventorycontroller.util.DateUtil.getRawFormat(
    		(java.util.Date)this.jXDatePicker1.getValue()
    	);
    	String dtCur=inventorycontroller.function.DateProcessor
    	 .getCurrentDate(this.dbInterface1);
    	if(dtGrn.compareTo(dtCur)>0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"GRN Date yet not came\"</center></html>",
				"InventoryController says...",
			javax.swing.JOptionPane.ERROR_MESSAGE);
			jXDatePicker2.grabFocus();
			return false;
    	}
    	if(dtCh.compareTo(dtGrn)>0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Challan Date cannot be after GRN Date\"</center></html>",
				"InventoryController says...",
			javax.swing.JOptionPane.ERROR_MESSAGE);
			jXDatePicker1.grabFocus();
			return false;
    	}
    	
    	//* at least one material supplied qty should be >0
    	javax.swing.table.TableModel tm=jTable2.getModel();
    	boolean noSup=true;
    	for (int i = 0; i<tm.getRowCount(); i++){
    		if(
    			Double.parseDouble(tm.getValueAt(i, 3).toString())>0
    		){
    			noSup=false;
    			break;
    		}
    	}
    	if(noSup){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"At least one Material should be Supplied.\"</center></html>",
				"InventoryController says...",
			javax.swing.JOptionPane.ERROR_MESSAGE);
			jTable2.grabFocus();
			jTable2.changeSelection(0, 3, false, false);
			jTable2.changeSelection(0, 3, false, true);
			return false;
    	}
    	return true;
    }
    
    /** called from insertPerform(). */
    private void insertGrnDesc() throws java.sql.SQLException, java.lang.ClassNotFoundException {
    	String grnNo=jTextField1.getText();
    	String val[]=new String[jTable2.getModel().getRowCount()];
    	final int[] indices=new int[] {5, 7, 8, 9, 11, 12, 13, 14}; //cols: grnQty, dsc, sch, ed, vat,
    	                                                        //      cst, freight, netAmt
    	inventorycontroller.function.TimeStampProcessor tsp=null;
    	String date=inventorycontroller.util.DateUtil.getRawFormat(
			(java.util.Date)jXDatePicker2.getValue()
		);
    	String tsid="";
    	for (int i = 0; i<val.length; i++){
    		// if qty ==0 then ommit that row.
    		if(Double.parseDouble(jTable2.getModel().getValueAt(i, 5).toString())==0){
    			continue;
    		}
    		val[i]=new String(
    			"'"+grnNo+"', '"+
    			poDet[Integer.parseInt(jTable2.getModel().getValueAt(i, 0).toString())-1][1]
    		);
    		// 'grnNo', 'matNo
    		for(int j: indices){
    			val[i]=val[i]+"', '"+jTable2.getModel().getValueAt(i, j).toString();
    		}
    		tsid=tsp.getNewTSid(date, dbInterface1);
    		val[i]=val[i]+"', '"+tsid+"'"; // TSid
	    	dbInterface1.cmdInsert("grnDesc", val[i]);
	    	tsp.updateTSid(tsid, date, dbInterface1);
    	}
    }
    
    /** called from insertPerform(). */
    private void updatePO() {
    	boolean isComplete=true;
    	javax.swing.table.TableModel tm=jTable2.getModel();
    	int count=tm.getRowCount();
    	for (int i = 0; i<count; i++){
    		if(Double.parseDouble(tm.getValueAt(i, 2).toString())!=
    		 Double.parseDouble(tm.getValueAt(i, 5).toString())) {
		    	isComplete=false;
		    	break;
    		}
    	}
    	String poStatus=new String(isComplete?"complete":"incomplete");
    	new inventorycontroller.function.PurchaseOrderProcessor(dbInterface1)
    	 .setPoStatus(poStatus, jTextField3.getText());
    }
    
    /** called from insertPerform(). */
    private void updateMaterialStock() {
    	inventorycontroller.function.MaterialProcessor mp=
    	 new inventorycontroller.function.MaterialProcessor(dbInterface1);
    	javax.swing.table.TableModel tm=jTable2.getModel();
    	
    	int count=tm.getRowCount();
		double qty=0;
		double ed=0;
		double vat=0;
		double amt=0;
		String matId="";
    	for (int i = 0; i<count; i++){
			qty=Double.parseDouble(tm.getValueAt(i, 5).toString());
			ed=Double.parseDouble(tm.getValueAt(i, 9).toString());
			vat=Double.parseDouble(tm.getValueAt(i, 11).toString())+
			 Double.parseDouble(tm.getValueAt(i, 12).toString());
			amt=Double.parseDouble(tm.getValueAt(i, 14).toString());
			matId=mp.getMatId(tm.getValueAt(i, 1).toString());
    		
    		mp.addMaterialStock(
    			matId, 
    			qty, 
    			ed, 
    			vat, 
    			amt, 
    			inventorycontroller.util.DateUtil.getRawFormat(
    				(java.util.Date)jXDatePicker2.getValue()
    			)
    		);
    	}
    	
    }
    
    /** called from actionPerformed of jButton2 */
    private void resetPerform(){
    	jTextField1.setText(
    		new inventorycontroller.function.GoodsReceiveNoteProcessor(dbInterface1)
    		 .getNewGrnNo(
    		 	inventorycontroller.function.DateProcessor.getCurrentDate(dbInterface1)
    		 )
    	);
    	jXDatePicker2.setValue(inventorycontroller.util.DateUtil.getDateValue(
    			inventorycontroller.function.DateProcessor.getCurrentDate(dbInterface1)
    		)
    	);
    	jTextField3.setText("");
    	jTextField4.setText("");
    	jTextField5.setText("");
    	jTextPane1.setText("");
    	jLabel13.setText("Rs. 0.0");
    	jXDatePicker1.setValue(inventorycontroller.util.DateUtil.getDateValue(
    			inventorycontroller.function.DateProcessor.getCurrentDate(dbInterface1)
    		)
    	);
    	resetBomList();
    	resetGrnTable();
    	resetPoTable();
    }
    
    /** called from resetPerform */
    private void resetPoTable(){
    	poList=new inventorycontroller.function.PurchaseOrderProcessor(dbInterface1)
    	 .getNonCompletePO();
    	
    	jTable1.setModel(new javax.swing.table.DefaultTableModel(
            poList,
            new String [] {
                "PO No.", "PO Date", "Vendor Name"
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
    
    /** called from resetPerform */
    private void resetBomList(){
    	bomList=null;
    	jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = null;
            public int getSize() { return 0; }
            public Object getElementAt(int i) { return null; }
        });
    }
    
    /** called from resetPerform */
    private void resetGrnTable(){
    	poDet=null;
    	grnDet=null;
    	jTable2.setModel(new javax.swing.table.DefaultTableModel(
            null,
            new String [] {
                "Sl No.", "Item", 
                "Remaining Qty.", "Qty. Supplied", "Qty. Rejected", "Qty. Accepted", 
                "Gross Amount", 
                "Discount(Rs.)", "Surcharge(Rs.)", 
                "Excise Duty(Rs.)", "ED included", 
                "VAT(Rs.)", "CST(Rs.)", "Freight / Insurance(Rs.)", 
                "Net Amount", "Net Rate"
            }
        ));
    }
    
    /** called from actionPerformed of jButton3 */
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel jTextField1;
    private javax.swing.JLabel jTextField3;
    private javax.swing.JLabel jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextPane jTextPane1;
    private inventorycontroller.display.RDateSpinner jXDatePicker1;
    private inventorycontroller.display.RDateSpinner jXDatePicker2;
    
    private inventorycontroller.function.DbInterface dbInterface1;
    private Object[][] poList;
    private String[] bomList;
    private Object[][] grnDet;
    private String[][] poDet;
    private double grnTotal;
    // End of variables declaration
    
}
