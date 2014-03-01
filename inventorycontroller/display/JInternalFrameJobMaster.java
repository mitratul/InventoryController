/*
 * JInternalFrameJobMaster.java
 *
 * Created on July 04, 2007, 11:44 HRS
 * Last modified on July 12, 2007, 02:44 HRS
 *
 */

package inventorycontroller.display;

/**
 *
 * @author  brinto
 */
public class JInternalFrameJobMaster extends javax.swing.JInternalFrame {
    
    /** Creates new form JInternalFrameJobMaster */
    public JInternalFrameJobMaster(inventorycontroller.function.DbInterface dbi) {
        initComponents(dbi);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents(inventorycontroller.function.DbInterface dbi) {
    	dbInterface1=dbi;
    	selectedDet=null;
    	jobList=null;
    	odrDet=null;
    	odrList=null;
    	
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jEditorPane1 = new javax.swing.JEditorPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        jSpinner1 = new javax.swing.JSpinner();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTable1 = new javax.swing.JTable();
        jTable2 = new javax.swing.JTable();
        jTable3 = new javax.swing.JTable();
        jTable4 = new javax.swing.JTable();
        jLabelJobNo = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JLabel();
        jXDatePicker1 = new inventorycontroller.display.RDateSpinner(inventorycontroller.display.RDateSpinner.DD_MM_YYYY);
        jXDatePicker2 = new inventorycontroller.display.RDateSpinner(inventorycontroller.display.RDateSpinner.DD_MM_YYYY);
        
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pending Jobs", "Jobs in progress", "Finished Jobs" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		resetJobTable();
        	}
        });

        jLabel1.setBackground(new java.awt.Color(127, 157, 185));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");
        jLabel1.setOpaque(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener(){
        	public void valueChanged(javax.swing.event.ListSelectionEvent e) {
        		//Ignore extra messages.
        		if (e.getValueIsAdjusting()) return;
        		javax.swing.ListSelectionModel lsm =(javax.swing.ListSelectionModel)e.getSource();
        		int selectedRow;
        		if (lsm.isSelectionEmpty()) { //no rows are selected
        			selectedRow=-1;
        		} 
        		else {
            		selectedRow = lsm.getMinSelectionIndex();
            		prodSelected();
            	}
        	}
		});
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Order Details");

        jLabel3.setText("Job No.");

        jLabel4.setText("Order Sl. No.");

        jLabel5.setText("Description");

        resetPerform();

        jLabel6.setText("Quantity");

        jLabel7.setText("Job Start Date");

        jLabel8.setText("End Date");

        jScrollPane2.setViewportView(jEditorPane1);

        jLabel9.setText("Remarks (if any)");

        jButton1.setText("Allocate Job for the selected product");
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, 0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jSpinner1, 94, 94, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jXDatePicker1, 94, 94, Short.MAX_VALUE))
                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                            .addComponent(jLabelJobNo, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
	            .addContainerGap()
	            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                
	                .addGroup(jPanel3Layout.createSequentialGroup()
	                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(jLabel3)
	                        .addComponent(jLabelJobNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(jLabel4)
	                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(jLabel5)
	                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(jLabel6)
	                        .addComponent(jSpinner1, 22, 22, 22)
	                        .addComponent(jLabel7)
	                        .addComponent(jXDatePicker1, 22, 22, 22)))
	                .addGroup(jPanel3Layout.createSequentialGroup()
	                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                        .addComponent(jLabel2)
	                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                        .addComponent(jLabel9)
	                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(jButton1)
	                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jTabbedPane1.addTab("Allocate Job", jPanel3);
		
		jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		jTable2.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener(){
        	public void valueChanged(javax.swing.event.ListSelectionEvent e) {
        		//Ignore extra messages.
        		if (e.getValueIsAdjusting()) return;
        		javax.swing.ListSelectionModel lsm =(javax.swing.ListSelectionModel)e.getSource();
        		int selectedRow;
        		if (lsm.isSelectionEmpty()) { //no rows are selected
        			selectedRow=-1;
        		} 
        		else {
            		selectedRow = lsm.getMinSelectionIndex();
            		showOrderDetail(jTable2.getValueAt(selectedRow, 3).toString());
            	}
        	}
		});
        jScrollPane3.setViewportView(jTable2);

		jTable3.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane4.setViewportView(jTable3);

        jLabel10.setText("Orders (pending & incomplete):");

        jLabel11.setText("Order Details:");

        jButton5.setText("Select Order");
        jButton5.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		orderSelected(jTable2.getValueAt(jTable2.getSelectedRow(), 3).toString());
        	}
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 242, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                        .addGap(38, 38, 38)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, 22, 22, 22)))
                .addContainerGap())
        );
        jTabbedPane1.addTab("Select Order", jPanel4);

        jLabel12.setText("View ");

        jTable4.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
        jTable4.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(jTable4);

        jButton4.setText("Prepare Bill of Material");
        jButton4.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		openBomForm();
        	}
        });

        jButton6.setText("End Job");
        jButton6.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		endJob();
        	}
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4, 220, 320, 320)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, 220, 320, 320)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jComboBox1, 22, 22, 22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton6))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		resetPerform();
        	}
        });
        jButton2.setPreferredSize(new java.awt.Dimension(120, 22));

        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		exitPerform();
        	}
        });
        jButton3.setPreferredSize(new java.awt.Dimension(120, 22));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        setTitle("JobMaster");
        setVisible(true);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        getRootPane().setDefaultButton(jButton1);
    }
    
    private void openBomForm(){
    	this.getDesktopPane().getRootPane().getJMenuBar().getMenu(1).getItem(0).doClick(4);
    	try{
    		this.setClosed(true);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    void orderSelected(java.lang.String odrNo){
    	java.lang.String[][] tmpPnd=new inventorycontroller.function.OrderProcessor(dbInterface1)
    	 .getPendingQuantity(odrNo);
    	selectedDet=new java.lang.String[tmpPnd.length][2];
    	for (int i = 0; i<selectedDet.length; i++){
    		selectedDet[i]=new java.lang.String[2];
    		selectedDet[i][0]=new java.lang.String(tmpPnd[i][0]);
    		selectedDet[i][1]=new java.lang.String(tmpPnd[i][1]);
    	}
    	jTable1.setModel(new javax.swing.table.DefaultTableModel(
            selectedDet,
            new String [] {
                "Product", "Pending Qty."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        jTable1.changeSelection(0, 0, false, false);
    	jTable1.changeSelection(0, 0, false, true);
        
        jTextField2.setText(odrNo);
        
        jTabbedPane1.setSelectedIndex(0);
    }
    
    private void prodSelected(){
    	jTextField3.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
    	
    	int max=java.lang.Integer.parseInt(
    		jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString()
    	);
    	if(max==0){
    		jSpinner1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 0, 1));
    	}
    	else{
    		jSpinner1.setModel(new javax.swing.SpinnerNumberModel(max, 1, max, 1));
    	}
    	
    }
    
    private void showOrderDetail(java.lang.String odrNo){
    	odrDet=new inventorycontroller.function.JobProcessor(dbInterface1)
    	 .getOdrDetail(odrNo);
    	
    	jTable3.setModel(new javax.swing.table.DefaultTableModel(
            odrDet,
            new String [] {
                "Product", "Ordered Qty.", "Pending Qty."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
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
    	val="'"+jLabelJobNo.getText()+"', "; //job no.
    	val=val+"'"+jTextField2.getText()+"', "; // order no.
    	val=val+"'"+
    		inventorycontroller.util.DateUtil.getRawFormat(
    			(java.util.Date)jXDatePicker1.getValue()
    		)
    		+"', "; // st date
    	//val=val+"'"+jXDatePicker2.getValue().toString()+"', "; // end date
    	val=val+"'not ended', "; // end date
    	val=val+"'"+new inventorycontroller.function.ProductProcessor(dbInterface1)
    	 .getProductId(jTextField3.getText())+"', "; // product no.
    	val=val+"'"+jSpinner1.getValue().toString()+"', "; // quantity
    	val=val+"'pending', "; //status
    	val=val+"'"+jEditorPane1.getText()+" '"; // remark.
    	System.out.println (val);
    	try{
    		dbInterface1.cmdInsert("jbMaster", val);
    	}
    	catch(java.sql.SQLException ex){
    		System.out.println (ex);
    	}
    	new inventorycontroller.function.OrderProcessor(dbInterface1)
    	 .setOrderStatus(jTextField2.getText(), "incomplete");
    	resetPerform();
    }
    
    private boolean verify(){
    	//* jobNo cannot be empty
    	if(jLabelJobNo.getText().length()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Job No. field cannot be empty\"</center></html>",
				"InventoryController says...",
			javax.swing.JOptionPane.ERROR_MESSAGE);
			jLabelJobNo.grabFocus();
			return false;
    	}
    	//* jobNo cannot be duplicate.
    	String[][] tmp=null;
    	try{
    		tmp=dbInterface1.cmdSelect(
    			"jbMaster", "jbNo", "jbNo='"+jLabelJobNo.getText()+"'"
    		);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	if(tmp.length>0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Job No. field cannot be duplicate\"</center></html>",
				"InventoryController says...",
			javax.swing.JOptionPane.ERROR_MESSAGE);
			jLabelJobNo.grabFocus();
			jLabelJobNo.setSelectionStart(0);
			jLabelJobNo.setSelectionEnd(jLabelJobNo.getText().length());
			return false;
    	}
    	
    	
    	//* odrNo cannot be empty
    	if(jTextField2.getText().length()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"No Order is selected. Select one order first\"</center></html>",
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
    
    private void endJob(){
    	int s=jTable4.getSelectedRow();
    	if(s==-1){
    		/*javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"No PO selected.<br>Select one PO to update\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);*/
			return;
    	}
    	String jbNo=jTable4.getModel().getValueAt(s, 7).toString();
    	
    	//* confirm ending.
    	int i=javax.swing.JOptionPane.showInternalConfirmDialog(this,
			"<html><center>\"Are you sure you want to END this job?\"<br>Job No.: "+jbNo+"</center></html>",
			"InventoryController says...",
		javax.swing.JOptionPane.YES_NO_OPTION, 
		javax.swing.JOptionPane.QUESTION_MESSAGE);
		if(i!=javax.swing.JOptionPane.YES_OPTION){
			return;
		}
		
		//* if confirm end job.
    	new inventorycontroller.function.JobProcessor(dbInterface1)
    	 .setJobStatus(jbNo, "finished");
    	
    	resetPerform();
    }
    
    private void resetPerform(){
    	jLabelJobNo.setText("");
    	jTextField2.setText("");
    	jTextField3.setText("");
    	jSpinner1.setValue(0);
    	jXDatePicker1.setValue(inventorycontroller.util.DateUtil.getDateValue(
    			inventorycontroller.function.DateProcessor.getCurrentDate(dbInterface1)
    		)
    	);
    	jXDatePicker2.setValue(inventorycontroller.util.DateUtil.getDateValue(
    			inventorycontroller.function.DateProcessor.getCurrentDate(dbInterface1)
    		)
    	);
    	jEditorPane1.setText("");
    	jTextField2.grabFocus();
    	jComboBox1.setSelectedIndex(0);
    	resetSelectedOrderDetail();
    	resetJobTable();
    	resetOrderTable();
    	resetOrderDetail();
    }
    
    private void resetSelectedOrderDetail(){
		jTable1.setModel(new javax.swing.table.DefaultTableModel(
            null,
            new String [] {
                "Product", "Pending Qty."
            }
        ));    	
    }
    
    private void resetJobTable(){
    	if(jComboBox1.getSelectedIndex()==0){
    		jobList=new inventorycontroller.function.JobProcessor(dbInterface1).getPendingJobs();
    	}
    	else if(jComboBox1.getSelectedIndex()==1){
    		jobList=new inventorycontroller.function.JobProcessor(dbInterface1).getProgressingJobs();
    	}
    	else if(jComboBox1.getSelectedIndex()==2){
    		jobList=new inventorycontroller.function.JobProcessor(dbInterface1).getFinishedJobs();
    	}
    	
    	
    	jTable4.setModel(new javax.swing.table.DefaultTableModel(
            jobList,
            new String [] {
                "Customer Name", "Job Description", "Quantity", "Start Date", "End Date", "Remark", "Order No.", "Job No"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
    }
    
    private void resetOrderTable(){
    	odrList=new inventorycontroller.function.OrderProcessor(dbInterface1)
    	 .getNonCompleteOrders();
    	
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            odrList,
            new String [] {
                "Customer Name", "Order Date", "Remark", "Order Sl. No."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
    }
    
    private void resetOrderDetail(){
    	jTable3.setModel(new javax.swing.table.DefaultTableModel(
            null,
            new String [] {
                "Product", "Ordered Qty.", "Pending Qty."
            }
        ));
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
    private javax.swing.JComboBox jComboBox1;
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jLabelJobNo;
    private javax.swing.JLabel jTextField2;
    private javax.swing.JLabel jTextField3;
    private inventorycontroller.display.RDateSpinner jXDatePicker1;
    private inventorycontroller.display.RDateSpinner jXDatePicker2;
    
    private inventorycontroller.function.DbInterface dbInterface1;
    private java.lang.String[][] selectedDet;
    private java.lang.String[][] jobList;
    private java.lang.String[][] odrDet;
    private java.lang.String[][] odrList;
    //* End of variables declaration
    
}
