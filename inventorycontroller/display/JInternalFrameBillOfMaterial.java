/*
 * JInternalFrameBillOfMaterial.java
 *
 * Created on June 08, 2007, 12:15 HRS
 * Last modified on August 27, 2007, 13:15 HRS
 *
 */

package inventorycontroller.display;

/**
 *
 * @author  brinto
 */
public class JInternalFrameBillOfMaterial extends javax.swing.JInternalFrame {
    
    /** Creates new form JInternalFrameBillOfMaterial */
    public JInternalFrameBillOfMaterial(inventorycontroller.function.DbInterface dbi) {
        initComponents(dbi);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents(inventorycontroller.function.DbInterface dbi) {
    	dbInterface1=dbi;
    	jobList=null;
    	bomDesc=null;
    	
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTable3 = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JLabel();
        jXDatePicker1 = new inventorycontroller.display.RDateSpinner(inventorycontroller.display.RDateSpinner.DD_MM_YYYY);
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JLabel();
        jEditorPane1 = new javax.swing.JEditorPane();

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        
        resetPerform();
        
        jLabel5.setText("Required Material Details:");

		jTable2.setAutoResizeMode(jTable2.AUTO_RESIZE_OFF);
		jTable2.setAutoCreateRowSorter(false);
        jScrollPane2.setViewportView(jTable2);

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        
        jLabel2.setText("Product");
        jLabel7.setText("BOM No.");
        jLabel9.setText("Remarks");
        jLabel8.setText("Quantity");
        jSpinner1.setFont(new java.awt.Font("Dialog", 0, 12));
        jTextField3.setFont(new java.awt.Font("Dialog", 0, 12));

        jScrollPane1.setViewportView(jEditorPane1);

        jLabel3.setText("Job No.");
        jLabel4.setText("Date of Requisition");

        jButton4.setText("Change preset Materials for the Product");
        jButton4.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		selectMaterial();
        	}
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                //.addComponent(jTextField3)
                                .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner1))
                                //.addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))))
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                            .addComponent(jXDatePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, 164-20, Short.MAX_VALUE)))
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jXDatePicker1, 20, 20, 20)
                    .addComponent(jLabel2)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel9))
                .addContainerGap())
        );
        jTabbedPane1.addTab("Bill of Material", jPanel3);
        
		jTable3.setAutoResizeMode(jTable2.AUTO_RESIZE_OFF);
		jTable3.setAutoCreateRowSorter(false);
        jScrollPane3.setViewportView(jTable3);

        jLabel6.setText("Pending Jobs:");

        jButton6.setText("<html><center>Select<br>Job</center></html>");
        jButton6.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		jobSelected();
        	}
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                .addContainerGap(0, Short.MAX_VALUE))
        );
        jTabbedPane1.addTab("Select Job", jPanel4);

        jButton1.setText("Save this Bill of Material");
        jButton1.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		insertPerform();
        	}
        });
        jButton1.setMaximumSize(new java.awt.Dimension(320, 24));
        jButton1.setMinimumSize(new java.awt.Dimension(80, 24));
        jButton1.setPreferredSize(new java.awt.Dimension(160, 24));

        jButton5.setText("Generate Printable Format");
        jButton5.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		openBomPrint();
        	}
        });
        jButton5.setMaximumSize(new java.awt.Dimension(320, 24));
        jButton5.setMinimumSize(new java.awt.Dimension(80, 24));
        jButton5.setPreferredSize(new java.awt.Dimension(160, 24));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		resetPerform();
        	}
        });
        jButton2.setMaximumSize(new java.awt.Dimension(120, 24));
        jButton2.setMinimumSize(new java.awt.Dimension(120, 24));
        jButton2.setPreferredSize(new java.awt.Dimension(120, 24));

        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		exitPerform();
        	}
        });
        jButton3.setMaximumSize(new java.awt.Dimension(120, 24));
        jButton3.setMinimumSize(new java.awt.Dimension(120, 24));
        jButton3.setPreferredSize(new java.awt.Dimension(120, 24));

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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
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
                .addComponent(jPanel1)
                .addGap(4, 16, 22)
                .addComponent(jPanel2)
                .addGap(4, 16, 22)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        pack();
        setTitle("Bill of Material (Form No. F/SCL/18)");
        setVisible(true);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        getRootPane().setDefaultButton(jButton1);
    }
    
    
    private void selectMaterial(){
    	String[] oldItems=null;
    	javax.swing.table.TableModel tm=jTable2.getModel();
    	if(tm!=null){
    		oldItems=new String[tm.getRowCount()];
    		for (int i = 0; i<oldItems.length; i++){
    			if((Boolean)tm.getValueAt(i, 1)){
    				oldItems[i]=new String(tm.getValueAt(i, 2).toString());
    			}
    			else {
    				oldItems[i]=new String("");
    			}
    		}
    	}
    	else {
    		oldItems=new String[0];
    	}
    	String prod=jTextField3.getText();
    	new inventorycontroller.display.JDialogMaterialListForBOM(
    		this, dbInterface1, prod, oldItems
    	);
    }
    
    /** this method is called from material selection dialog's 
     * selectPerform() method, to get the selection and update the BOM
     */
    void reflectSelection(String[][] components){
    	updateBOM(components);
    }
    
    private void updateBOM(String[][] components){
    	int qty=Integer.parseInt(jSpinner1.getText());
    	bomDesc=new inventorycontroller.function.BillOfMaterialProcessor(dbInterface1)
    	 .prepareBOM(components, qty);
    	 
    	jTable2.setModel(new javax.swing.table.DefaultTableModel(
            bomDesc,
            new String [] {
                "Sl No.", 
                "Select", 
                "Item No.", 
                "Item Description", 
                "Qty. Required", 
                "Stock in hand", 
                "Delivery pending", 
                "Order pending",
                "Allocated",
                "Purchase Requirement",
                "Remarks"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, true, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return columnIndex==1? java.lang.Boolean.class: java.lang.String.class;
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.getModel().addTableModelListener(new javax.swing.event.TableModelListener(){
        	public void tableChanged(javax.swing.event.TableModelEvent evt){
        		jTable2.getModel().removeTableModelListener(this);
        		bomTableEdited(evt);
        		jTable2.getModel().addTableModelListener(this);
        	}
        });
    }
    
    /** called from updateBOM(). */
    private void bomTableEdited(javax.swing.event.TableModelEvent evt){
    	// col 4, 9 should be double
    	int r=evt.getFirstRow();
    	int c=evt.getColumn();
    	Object[] tmp0=new Object[10];
    	if(c==1 || c==10){ //select column: boolean || remark col.
    		return;
    	}
    	javax.swing.table.TableModel m=(javax.swing.table.TableModel)evt.getSource();
    	for (int i = 0; i<10; i++){
    		tmp0[i]=m.getValueAt(r, i);
    	}
    	
    	Object[] tmp1=new inventorycontroller.function.BillOfMaterialProcessor(dbInterface1)
    	 .calculateBOM(tmp0, c);
    	for (int i = 0; i<10; i++){
    		m.setValueAt(tmp1[i], r, i);
    	}
    	
    }
    
    private void openBomPrint(){
    	this.getDesktopPane().getRootPane().getJMenuBar().getMenu(4).getItem(0).doClick(4);
    }
    
    private void jobSelected(){
    	int i=jTable3.getSelectedRow();
    	if(i<0){
    		return;
    	}
    	resetJobSelection();
    	jTextField3.setText(jTable3.getValueAt(i, 1).toString());
    	jSpinner1.setText(jTable3.getValueAt(i, 2).toString());
    	jTextField1.setText(jTable3.getValueAt(i, 5).toString());
    	showBOM(jTable3.getValueAt(i, 1).toString());
    	jTabbedPane1.setSelectedIndex(0);
    }
    
    /** showBOM() should be called after setting the qty in label jSpinner1.
     * it takes the qty from that label's getText() method
     */
    private void showBOM(java.lang.String prd){
    	inventorycontroller.function.ProductProcessor pp=
    	 new inventorycontroller.function.ProductProcessor(dbInterface1);
    	String[][] components=pp.getDescription(pp.getProductId(prd));
    	updateBOM(components);
    }
    
    private void insertPerform(){
    	if(!verify()){
    		return;
    	}
    	String val=new String("");
    	val=val+"'"+jTextField2.getText()+"', "; // bomNo
    	val=val+"'"+jTextField1.getText()+"', "; // jbNo
    	val=val+"'"+
    		inventorycontroller.util.DateUtil.getRawFormat(
    			(java.util.Date)jXDatePicker1.getValue()
    		)
    		+"', "; // rqnDate
    	val=val+"'pending', "; // bomStatus
    	val=val+"'"+jEditorPane1.getText()+" '"; // bomRemark
    	
    	try{
    		dbInterface1.cmdInsert("bomMaster", val);
    		insertDesc();
    		
    	}
    	catch(java.sql.SQLException ex){
    		System.out.println (ex);
    	}
    	
    	new inventorycontroller.function.JobProcessor(dbInterface1)
    	 .setJobStatus(jTextField1.getText(), "progressing");
    	boolean b=new inventorycontroller.function.BillOfMaterialProcessor(dbInterface1)
    	 .isComplete(jTextField2.getText());
    	if(b){
    		new inventorycontroller.function.BillOfMaterialProcessor(dbInterface1)
    		 .setBOMStatus(jTextField2.getText(), "complete");
    	}
    	resetPerform();
    }
    
    private boolean verify(){
    	//* jobNo cannot be empty
    	if(jTextField1.getText().length()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"No job is selected. Select one job first\"</center></html>",
				"InventoryController says...",
			javax.swing.JOptionPane.ERROR_MESSAGE);
			jTabbedPane1.setSelectedIndex(1);
			return false;
    	}
    	
    	// warn if the date is not current date.
    	String cDate=inventorycontroller.function.DateProcessor
    	 .getCurrentDate(dbInterface1);
    	String date=inventorycontroller.util.DateUtil
    	 .getRawFormat((java.util.Date)jXDatePicker1.getValue());
    	if(cDate.compareTo(date)<0){
    		int i=javax.swing.JOptionPane.showInternalConfirmDialog(this,
				"<html><center>\"Job Start date is not yet arrived.<br>Do you want to continue?\"</center></html>",
				"InventoryController says...",
			javax.swing.JOptionPane.YES_NO_OPTION, 
			javax.swing.JOptionPane.WARNING_MESSAGE);
			if(i!=javax.swing.JOptionPane.YES_OPTION){
				return false;
			}
    	}
    	else if(cDate.compareTo(date)>0){
    		int i=javax.swing.JOptionPane.showInternalConfirmDialog(this,
				"<html><center>\"Job Start date is already past.<br>Do you want to continue?\"</center></html>",
				"InventoryController says...",
			javax.swing.JOptionPane.YES_NO_OPTION, 
			javax.swing.JOptionPane.WARNING_MESSAGE);
			if(i!=javax.swing.JOptionPane.YES_OPTION){
				return false;
			}
    	}
    	return true;
    }
    
    private void insertDesc() throws java.sql.SQLException {
    	java.lang.String[] val=new java.lang.String[jTable2.getRowCount()];
    	String bomNo=jTextField2.getText();
    	
    	for (int i = 0; i<val.length; i++){
    		val[i]=new java.lang.String(
    			"'"+bomNo+"', '"+jTable2.getValueAt(i, 2).toString()
    		); //bomNo, matNo
    		val[i]=val[i]+"', '"+jTable2.getValueAt(i, 4).toString(); // matReqd
    		val[i]=val[i]+"', '"+jTable2.getValueAt(i, 5).toString(); // stock
    		val[i]=val[i]+"', '"+jTable2.getValueAt(i, 9).toString(); // purRqtn
    		val[i]=val[i]+"', '"+jTable2.getValueAt(i, 9).toString(); // purReqd
    		val[i]=val[i]+"', '"+jTable2.getValueAt(i, 10).toString()+" '"; // remark
    	}
   		dbInterface1.cmdInsert("bomDesc", val);
    }
    
    private void resetPerform(){
    	jTextField2.setText(
    		new inventorycontroller.function.BillOfMaterialProcessor(dbInterface1)
    		 .getNewBomNo()
    	);
    	jTextField1.setText("");
    	jXDatePicker1.setValue(
    		inventorycontroller.util.DateUtil.getDateValue(
    			inventorycontroller.function.DateProcessor.getCurrentDate(dbInterface1)
    		)
    	);
    	jEditorPane1.setText("");
    	resetJobTable();
    	resetBomDetail();
    	resetJobSelection();
    	
    	jTextField2.grabFocus();
    }
    
    private void resetBomDetail(){
    	jTable2.setModel(new javax.swing.table.DefaultTableModel(
            null,
            new String [] {
                "Sl No.", 
                "Select", 
                "Item No.", 
                "Item Description", 
                "Qty. Required", 
                "Stock in hand", 
                "Delivery pending", 
                "Order pending",
                "Allocated",
                "Purchase Requirement",
                "Remarks"
            }
        ));
    }
    
    private void resetJobTable(){
    	jobList=new inventorycontroller.function.JobProcessor(dbInterface1).getJobsForBOM();
    	/*for (int i = 0; i<jobList.length; i++){
    		System.out.println (jobList[i][5]);
    	}*/
    	jTable3.setModel(new javax.swing.table.DefaultTableModel(
            jobList,
            new String [] {
                "Customer Name", "Job Description", "Quantity", "Start Date", "Remark", "Job No."
            }
        ) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
    }
    
    private void resetJobSelection(){
		jTextField3.setText("");
    	jSpinner1.setText("0");
    }
    
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
    
    
    
    //* Variables declaration -
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel jTextField1;
    private javax.swing.JLabel jTextField2;
    private javax.swing.JLabel jTextField3;
    private inventorycontroller.display.RDateSpinner jXDatePicker1;
    
    private inventorycontroller.function.DbInterface dbInterface1;
    java.lang.String[][] jobList;
    java.lang.Object[][] bomDesc;
    //* End of variables declaration
    
}
