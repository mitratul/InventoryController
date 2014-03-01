/*
 * JInternalFrameOrderMaster.java
 *
 * Created on June 12, 2007, 11:08 HRS
 * Last Modified on August 07, 2007, 11:20 HRS
 */

package inventorycontroller.display;

/**
 *
 * @author  brinto
 */
public class JInternalFrameOrderMaster extends javax.swing.JInternalFrame {
    
    /** Creates new form JInternalFrameOrderMaster */
    public JInternalFrameOrderMaster(inventorycontroller.function.DbInterface dbi) {
        initComponents(dbi);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents(inventorycontroller.function.DbInterface dbi) {
    	dbInterface1=dbi;
    	stringOrderDescription=new java.lang.String("");
		usrOrder=null;
		pndOrder=null;
		ncmOrder=null;
		cmpOrder=null;
		detOrder=null;    	
    	
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabelOdrNo = new javax.swing.JTextField();
        //jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jXDatePicker1 = new inventorycontroller.display.RDateSpinner(inventorycontroller.display.RDateSpinner.DD_MM_YYYY);
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jLabel1.setBackground(new java.awt.Color(127, 157, 185));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");
        jLabel1.setOpaque(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        jLabel2.setText("Order Sl. No.");

        jLabel3.setText("Customer Name");

        jLabel4.setText("Order Date");

        jLabel5.setText("Order Description:");

        jLabel6.setText("Remarks (if any):");

        jLabel8.setText("PO No.");

        resetPerform();

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(1);
        jScrollPane1.setViewportView(jTextArea1);

		jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(jTable1);

        jButton1.setText("Insert Order");
        jButton1.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		insertPerform();
        	}
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                                    .addComponent(jLabelOdrNo, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                    	.addComponent(jXDatePicker1, 90, 90, 90)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))))
                            .addComponent(jLabel6))
                        .addGap(22, 32, 48)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
            	.addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                	.addComponent(jLabel5)
                    .addComponent(jLabelOdrNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))                
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jXDatePicker1, 22, 22, 22)
                            .addComponent(jLabel8)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

		jTable3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		jTable3.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable3.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener(){
        	public void valueChanged(javax.swing.event.ListSelectionEvent e) {
        		//Ignore extra messages.
        		if (e.getValueIsAdjusting()) return;
        		javax.swing.ListSelectionModel lsm =(javax.swing.ListSelectionModel)e.getSource();
        		int selectedRow;
        		if (lsm.isSelectionEmpty()) { //no rows are selected
        			selectedRow=-1;
        		} 
        		else {
        			jTable4.clearSelection();
        			jTable5.clearSelection();
            		selectedRow = lsm.getMinSelectionIndex();
            		showDetail(jTable3.getModel().getValueAt(selectedRow, 4).toString());
            	}
        	}
		});
        jScrollPane4.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addContainerGap())
        );
        jTabbedPane1.addTab("View Pending Orders", jPanel4);

        jTable4.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		jTable4.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable4.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener(){
        	public void valueChanged(javax.swing.event.ListSelectionEvent e) {
        		//Ignore extra messages.
        		if (e.getValueIsAdjusting()) return;
        		javax.swing.ListSelectionModel lsm =(javax.swing.ListSelectionModel)e.getSource();
        		int selectedRow;
        		if (lsm.isSelectionEmpty()) { //no rows are selected
        			selectedRow=-1;
        		} 
        		else {
            		jTable3.clearSelection();
        			jTable5.clearSelection();
            		selectedRow = lsm.getMinSelectionIndex();
            		showDetail(jTable4.getModel().getValueAt(selectedRow, 4).toString());
            	}
        	}        	
		});
        jScrollPane5.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addContainerGap())
        );
        jTabbedPane1.addTab("View Incomplete Orders", jPanel5);
		
		jTable5.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		jTable5.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable5.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener(){
        	public void valueChanged(javax.swing.event.ListSelectionEvent e) {
        		//Ignore extra messages.
        		if (e.getValueIsAdjusting()) return;
        		javax.swing.ListSelectionModel lsm =(javax.swing.ListSelectionModel)e.getSource();
        		int selectedRow;
        		if (lsm.isSelectionEmpty()) { //no rows are selected
        			selectedRow=-1;
        		} 
        		else {
            		jTable4.clearSelection();
        			jTable3.clearSelection();
            		selectedRow = lsm.getMinSelectionIndex();
            		showDetail(jTable5.getModel().getValueAt(selectedRow, 4).toString());
            	}
        	}        	
		});
        jScrollPane6.setViewportView(jTable5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addContainerGap())
        );
        jTabbedPane1.addTab("View Complete Orders", jPanel6);

        jButton2.setText("Allocate Job");
        jButton2.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		openJobForm();
        	}
        });

        jLabel7.setText("Order Description:");

        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGap(22)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                .addGap(22)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, 0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        jButton3.setText("Reset");
        jButton3.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		resetPerform();
        	}
        });
        jButton3.setMaximumSize(new java.awt.Dimension(80, 24));
        jButton3.setMinimumSize(new java.awt.Dimension(80, 24));
        jButton3.setPreferredSize(new java.awt.Dimension(80, 24));

        jButton4.setText("Exit");
        jButton4.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		exitPerform();
        	}
        });
        jButton4.setMaximumSize(new java.awt.Dimension(80, 24));
        jButton4.setMinimumSize(new java.awt.Dimension(80, 24));
        jButton4.setPreferredSize(new java.awt.Dimension(80, 24));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        pack();
        setTitle("OrderMaster");
        setVisible(true);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        getRootPane().setDefaultButton(jButton1);
    }
    
    private void openJobForm(){
    	this.getDesktopPane().getRootPane().getJMenuBar().getMenu(0).getItem(1).doClick(4);
    	try{
    		this.setClosed(true);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    private void showDetail(java.lang.String odrNo){
    	detOrder=new inventorycontroller.function.OrderProcessor(dbInterface1)
    	 .getOdrDetailForOdrMaster(odrNo);
    	jTable2.setModel(new javax.swing.table.DefaultTableModel(
            detOrder,
            new String [] {
                "Product Type", " Ordered Qty.", "Alloted Qty.", "Finished Qty."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, 
                java.lang.Integer.class, 
                java.lang.Integer.class, 
                java.lang.Integer.class
            };
            
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
    //* ENDING: showDetail().
    }
    
    private void insertPerform(){
    	if(!verify()){
    		return;
    	}
    	java.lang.String val=new java.lang.String("");
    	val="'"+jLabelOdrNo.getText()+"', "; // odrNo
    	val=val+"'"+jTextField2.getText()+"', "; // custName
    	val=val+"'"+jTextField3.getText()+"', "; // custPONo
    	val=val+"'"+
    		inventorycontroller.util.DateUtil.getRawFormat(
    			(java.util.Date)jXDatePicker1.getValue()
    		)
    		+"', "; //odrDate
    	val=val+"'"+jTextArea1.getText()+" ',"; // odrRemark
    	val=val+"'pending'"; // odrStatus
    	
    	try{
    		dbInterface1.cmdInsert("odrMaster", val);
	    	insertDetail();
    	}
    	catch(java.sql.SQLException ex){
    		System.out.println (ex);
    	}
    	
    	resetPerform();
    }
    
    private boolean verify(){
    	//* order sl no cannot be blank
    	if(this.jLabelOdrNo.getText().length()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Order Sl. No. field cannot be left empty\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			this.jLabelOdrNo.grabFocus();
			return false;
    	}
    	
    	//* order sl no cannot be duplicate.
    	String[][] tmp=null;
    	try{
    		tmp=dbInterface1.cmdSelect("odrMaster", "odrNo", "odrMaster.odrNo='"+this.jLabelOdrNo+"'");
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	if(tmp.length>0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Order Sl. No. cannot be duplcate\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			this.jLabelOdrNo.setSelectionStart(0);
			this.jLabelOdrNo.setSelectionEnd(this.jLabelOdrNo.getText().length());
			this.jLabelOdrNo.grabFocus();
			return false;
    	}
    	
    	//* customer name cannot be blank
    	if(jTextField2.getText().length()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Customer Name field cannot be left empty\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField2.grabFocus();
			return false;
    	}
    	//* customer PO cannot be blank.
    	if(jTextField3.getText().length()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Customer PO No. field cannot be left empty\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField3.grabFocus();
			return false;
    	}
    	//* should select at least one product.
    	javax.swing.table.TableModel tm=jTable1.getModel();
    	int pc=0;
    	for (int i = 0; i<tm.getRowCount(); i++){
    		if((Boolean)tm.getValueAt(i, 1)){
    			pc++;
    		}
    	}
    	if(pc==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Select at least ONE product\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTable1.grabFocus();
			return false;
    	}
    	
    	//* selected product should ordered >0 qty.
    	for (int i = 0; i<tm.getRowCount(); i++){
    		if((Boolean)tm.getValueAt(i, 1) && Double.parseDouble(tm.getValueAt(i, 2).toString())<=0){
	    		javax.swing.JOptionPane.showInternalMessageDialog(this,
					"<html><center>\"Selected Product should have Qty. more than ZERO\"</center></html>",
					"InventoryController says...",
					javax.swing.JOptionPane.ERROR_MESSAGE);
				jTable1.changeSelection(i, 0, false, false);
				jTable1.changeSelection(i, 2, false, true);
				jTable1.grabFocus();
				return false;
    		}
    	}
    	return true;
    }
    
    private void insertDetail() throws java.sql.SQLException {
    	java.util.Vector<Integer> selected=new java.util.Vector<Integer>();
    	for (int i = 0; i<jTable1.getRowCount(); i++){
    		if(jTable1.getValueAt(i, 1).toString()=="true" && Integer.parseInt(jTable1.getValueAt(i, 2).toString())>0){
    			selected.add(i);
    		}
    	}
    	
    	inventorycontroller.function.ProductProcessor pp=
    	 new inventorycontroller.function.ProductProcessor(dbInterface1);
    	
    	String[] val=new String[selected.size()];
    	String odrNo=jLabelOdrNo.getText();
    	int j=0;
    	for (int i: selected){
    		val[j++]=new String(
    			"'"+odrNo+"', '"+
    			pp.getProductId(jTable1.getValueAt(i, 0).toString())+"', '"+
    			jTable1.getValueAt(i, 2).toString()+"'"
    		);
    	}
    	dbInterface1.cmdInsert("odrDesc", val);
    }
    
    private void resetPerform(){
    	//jLabelOdrNo.setText(new inventorycontroller.function.OrderProcessor(dbInterface1).getNewOdrNo());
    	jLabelOdrNo.setText("");
    	jTextField2.setText("");
    	jTextField3.setText("");
    	jXDatePicker1.setValue(inventorycontroller.util.DateUtil.getDateValue(
    			inventorycontroller.function.DateProcessor.getCurrentDate(dbInterface1)
    		)
    	);
    	jTextArea1.setText("");
    	stringOrderDescription="";
    	jLabelOdrNo.grabFocus();
    	resetDescTable();
    	resetOrderTables();
    	resetOrderDesc();
    }
    
    private void resetDescTable(){
    	java.lang.String[] prd=null;
    	prd=new inventorycontroller.function.ProductProcessor(dbInterface1)
    	 .getProductNames("1=1");
    	this.usrOrder=new java.lang.Object[prd.length][3];
    	for (int i = 0; i<prd.length; i++){
    		usrOrder[i]=new java.lang.Object[3];
    		usrOrder[i][0]=prd[i];
    		usrOrder[i][1]=new java.lang.Boolean(false);
    		usrOrder[i][2]=new java.lang.Integer(0);
    	}
    	jTable1.setModel(new javax.swing.table.DefaultTableModel(
            usrOrder,
            new String [] {
                "Product", "Select", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(160);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(48);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);
    //* ENDING: resetDescTable().
    }
    
    private void resetOrderTables(){
    	this.pndOrder=new inventorycontroller.function.OrderProcessor(this.dbInterface1)
    	 .getPendingOrders();
    	jTable3.setModel(new javax.swing.table.DefaultTableModel(
            this.pndOrder,
            new String [] {
                "Custmer Name", "Order Date", "Remark", "PO No.", "Order No."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, 
                java.lang.Object.class, 
                java.lang.String.class, 
                java.lang.String.class, 
                java.lang.String.class
            };
            
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        jTable3.setAutoResizeMode(jTable3.AUTO_RESIZE_OFF);
        jTable3.getColumnModel().getColumn(0).setPreferredWidth(120);
        jTable3.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable3.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTable3.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable3.getColumnModel().getColumn(4).setPreferredWidth(80);
        
        this.ncmOrder=new inventorycontroller.function.OrderProcessor(this.dbInterface1)
    	 .getIncompleteOrders();
    	jTable4.setModel(new javax.swing.table.DefaultTableModel(
            this.ncmOrder,
            new String [] {
                "Custmer Name", "Order Date", "Remark", "PO No.", "Order No."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, 
                java.lang.Object.class, 
                java.lang.String.class, 
                java.lang.String.class, 
                java.lang.String.class
            };
            
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        jTable4.setAutoResizeMode(jTable4.AUTO_RESIZE_OFF);
        jTable4.getColumnModel().getColumn(0).setPreferredWidth(120);
        jTable4.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable4.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTable4.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable4.getColumnModel().getColumn(4).setPreferredWidth(80);
        
        this.cmpOrder=new inventorycontroller.function.OrderProcessor(this.dbInterface1)
    	 .getCompleteOrders();
    	jTable5.setModel(new javax.swing.table.DefaultTableModel(
            this.cmpOrder,
            new String [] {
                "Custmer Name", "Order Date", "Remark", "PO No.", "Order No."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, 
                java.lang.Object.class, 
                java.lang.String.class, 
                java.lang.String.class, 
                java.lang.String.class
            };
            
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        jTable5.setAutoResizeMode(jTable5.AUTO_RESIZE_OFF);
        jTable5.getColumnModel().getColumn(0).setPreferredWidth(120);
        jTable5.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable5.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTable5.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable5.getColumnModel().getColumn(4).setPreferredWidth(80);
        
    //* ENDING: resetOrderTables().
    }
    
    private void resetOrderDesc(){
    	jTable2.setModel(new javax.swing.table.DefaultTableModel(
            null,
            new String [] {
                "Product Type", " Ordered Qty.", "Alloted Qty.", "Finished Qty."
            }
        ));
    //* ENDING: resetOrderDesc().
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField jLabelOdrNo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextArea jTextArea1;
    //private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private inventorycontroller.display.RDateSpinner jXDatePicker1;
    
    private inventorycontroller.function.DbInterface dbInterface1;
    private java.lang.String stringOrderDescription;
    private java.lang.Object[][] usrOrder;
    private java.lang.String[][] pndOrder;
    private java.lang.String[][] ncmOrder;
    private java.lang.String[][] cmpOrder;
    private java.lang.String[][] detOrder;
    //* End of variables declaration
    
}
