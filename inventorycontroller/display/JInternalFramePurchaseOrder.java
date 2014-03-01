/*
 * JInternalFramePurchaseOrder.java
 *
 * Created on July 17, 2007, 21:11 HRS
 * Last Modified on August 18, 2007, 09:45 HRS
 *
 */
 
package inventorycontroller.display;
 

/**
 *
 * @author  brinto
 */
public class JInternalFramePurchaseOrder extends javax.swing.JInternalFrame {
    
    /** Creates new form JInternalFramePOMaster */
    public JInternalFramePurchaseOrder(inventorycontroller.function.DbInterface dbi) {
        initComponents(dbi);
    }
    
    /** This method is called from within the constructor to initialize the form. */
    private void initComponents(inventorycontroller.function.DbInterface dbi) {
    	dbInterface1=dbi;
    	poAmount="0";
    	poType=-1;
    	resetPerforming=false;
	    selectedBomList=null;
	    bomList=null;
	    bomDet=null;
	    itmList=null;
	    poDet=null;
	    vndList=null;
	    poDetType=new java.lang.Class[] {
	    	java.lang.Short.class,
	    	java.lang.String.class,
	    	java.lang.Long.class,
	    	java.lang.Double.class,
	    	java.lang.Double.class,
	    	java.lang.Double.class,
	    	java.lang.Double.class,
	    	java.lang.Double.class,
	    	java.lang.Double.class,
	    	java.lang.Double.class,
	    	java.lang.Double.class,
	    	java.lang.Double.class,
	    	java.lang.Double.class,
	    	java.lang.Double.class,
	    	java.lang.Double.class,
	    	java.lang.Double.class,
	    	java.lang.Double.class,
	    	java.lang.Double.class,
	    };
	    poDetEditableForBM=new boolean[18];
	    poDetEditableForLP=new boolean[18];
	    for (int i = 0; i<18; i++){
		    poDetEditableForBM[i]=true;
		    poDetEditableForLP[i]=true;
	    }
	    poDetEditableForBM[0]=false;
	    poDetEditableForBM[1]=false;
	    poDetEditableForBM[2]=false;
	    poDetEditableForBM[4]=false;
	    poDetEditableForBM[17]=false;
	    poDetEditableForLP[0]=false;
	    poDetEditableForLP[1]=false;
	    poDetEditableForLP[4]=false;
	    poDetEditableForLP[17]=false;
	    poDetEditable=null;
	    addedBom=new java.util.Vector<String>(); 
	    addedBomDet=new java.util.Vector<java.util.Vector<String>>(); 
	    
    	
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JComboBox();
        jSpinner1 = new javax.swing.JSpinner();
        jTextField1 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jSpinner2 = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jLabel8 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jTable4 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jXDatePicker1 = new inventorycontroller.display.RDateSpinner(inventorycontroller.display.RDateSpinner.DD_MM_YYYY);
        jXDatePicker2 = new inventorycontroller.display.RDateSpinner(inventorycontroller.display.RDateSpinner.DD_MM_YYYY);

        setTitle("Purchase Order");
        
        jTextField2.setMaximumRowCount(8);
        
        jLabel1.setBackground(new java.awt.Color(127, 157, 185));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");
        jLabel1.setOpaque(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        jRadioButton1.setText("Purchase against BOM");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		poTypeChanged((javax.swing.JRadioButton)evt.getSource());
        	}
        });

        jRadioButton2.setText("Local Purchase");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		poTypeChanged((javax.swing.JRadioButton)evt.getSource());
        	}
        });
        
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);

        jLabel2.setText("PO No.");

        jLabel3.setText("PO Date");

        jLabel4.setText("Vendor Name");

        jLabel5.setText("Quotation No.");

        jLabel6.setText("Quotation Date");

        jLabel7.setText("BOM List:");

        jScrollPane2.setViewportView(jEditorPane1);

        jLabel8.setText("Remarks (if any):");

        jButton6.setText("Generate Purchase Requisition");
        jButton6.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		generatePurchaseRequition();
        	}
        });

        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jXDatePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(jXDatePicker2, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jXDatePicker1, 20, 20, 20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField2, 20, 20, 20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jXDatePicker2, 20, 20, 20)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
	                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
	                            .addComponent(jLabel8)
	                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                            .addComponent(jScrollPane2))
	                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
	                            .addComponent(jLabel7)
	                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 16, 32)
                        .addComponent(jButton6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jTabbedPane1.addTab("Purchase Order Detail", jPanel3);

        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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
            		bomSelected(selectedRow);
            	}
        	}
		});
        jTable1.setAutoResizeMode(jTable2.AUTO_RESIZE_OFF);
        jTable1.setAutoCreateRowSorter(false);
        jScrollPane3.setViewportView(jTable1);

        jLabel9.setText("BOM List:");

        jLabel10.setText("BOM Details:");

		jTable2.setAutoResizeMode(jTable2.AUTO_RESIZE_OFF);
        jTable2.setAutoCreateRowSorter(false);
        jScrollPane4.setViewportView(jTable2);

        jButton5.setText("Add Selected Item/s");
        jButton5.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		selectBom();
        	}
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addComponent(jLabel9))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
                .addContainerGap())
        );
        jTabbedPane1.addTab("BOM Selection", jPanel5);

        jTable4.setAutoCreateRowSorter(false);
        jTable4.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane6.setViewportView(jTable4);

        jLabel12.setText("Material List:");

        jButton7.setText("<html><center>Add Selected<br>Material/s</center></html>");
        jButton7.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		itemSelected();
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
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                    .addComponent(jLabel12))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton7))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jTabbedPane1.addTab("Material Selection", jPanel4);
        
        jTable3.setAutoCreateRowSorter(false);
        jTable3.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane5.setViewportView(jTable3);

        jButton1.setText("Save this Purchase Order");
        jButton1.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		insertPerform();
        	}
        });
        
        jButton4.setText("Generate Print Preview");
        jButton4.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		showPrintView();
        	}
        });

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel11.setForeground(new java.awt.Color(255, 102, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        jLabel11.setOpaque(true);

        resetPerform();
        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                    	.addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, 220, 320, 320)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, 220, 320, 320)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton1))
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
        pack();
        setVisible(true);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        getRootPane().setDefaultButton(jButton1);
    //* ENDING: initComponent()
    }
    
    /** This method is called from actionPerformed of radioButton1-2 */
    private void poTypeChanged(javax.swing.JRadioButton jrb){
    	if(jrb==jRadioButton1){
    		if(!resetPerforming && poType==inventorycontroller.function.PurchaseOrderProcessor.AGAINST_BOM){
    			return;
    		}
    		poType=inventorycontroller.function.PurchaseOrderProcessor.AGAINST_BOM;
	    	jTabbedPane1.setEnabledAt(1, true);
	    	jTabbedPane1.setEnabledAt(2, false);
	    	poDetEditable=poDetEditableForBM;
	    	jTextField1.setText(
	    		new inventorycontroller.function.PurchaseOrderProcessor(dbInterface1)
	    		 .getNewPoNo(poType)
	    	);
	    	
	    	resetPoTable();
	    	resetBomTable();
	    	resetBomDetails();
    	}
    	else if(jrb==jRadioButton2){
    		if(!resetPerforming && poType==inventorycontroller.function.PurchaseOrderProcessor.LOCAL_PURCHASE){
    			return;
    		}
    		poType=inventorycontroller.function.PurchaseOrderProcessor.LOCAL_PURCHASE;
        	jTabbedPane1.setEnabledAt(1, false);
        	jTabbedPane1.setEnabledAt(2, true);
        	resetSelectedBom();
        	poDetEditable=poDetEditableForLP;
	    	jTextField1.setText(
	    		new inventorycontroller.function.PurchaseOrderProcessor(dbInterface1)
	    		 .getNewPoNo(poType)
	    	);
	    	resetPoTable();
	    	resetItmTable(); 
	        jTable4.getColumnModel().getColumn(0).setMinWidth(48);
	        jTable4.getColumnModel().getColumn(0).setPreferredWidth(48);
	        jTable4.getColumnModel().getColumn(0).setMaxWidth(60);    		
    	}
    }
    
    private void generatePurchaseRequition(){
    	this.getDesktopPane().getRootPane().getJMenuBar().getMenu(4).getItem(1).doClick(4);
    }
    
    private void showPrintView(){
    	this.getDesktopPane().getRootPane().getJMenuBar().getMenu(4).getItem(2).doClick(4);
    }
    
    /** called from ListSelectionListener of BOM List. */
    private void bomSelected(int selectedRow){
    	bomDet=new inventorycontroller.function.BillOfMaterialProcessor(dbInterface1)
    	 .getBomDescForPoMaster(jTable1.getModel().getValueAt(selectedRow, 3).toString());
    	
    	adjustAddedQty(); //* adjusts the item qty. which are already added.
    	
    	jTable2.setModel(new javax.swing.table.DefaultTableModel(
            bomDet,
            new String [] {
                "Select", "Material", "Reqd. Quantity", "Purchase Requirement", "Remark"
            }
        ) {
            Class[] types = new Class [] {
            	java.lang.Boolean.class, 
            	java.lang.String.class, 
                java.lang.Long.class, 
                java.lang.Long.class,
                java.lang.String.class
            };
            
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex==0 ? true : false ;
            }
        });
    }
    
    /** called from selectBom() & bomSelected. */
    private void adjustAddedQty(){
    	String tmp1=jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 3).toString();
    	
    	if(!addedBom.contains(tmp1)){
    		return;
    	}
    	int k=addedBom.indexOf(tmp1);
    	java.util.Vector<String> tmp2=addedBomDet.elementAt(k);
    	for (int i = 0; i<bomDet.length; i++){
    		if(tmp2.contains(bomDet[i][1].toString())){
    			bomDet[i][3]=new Long(0);
    		}
    	}
    	
    }
    
    /** called from BOM selection button's actionListener. */
    private void selectBom(){
    	//* count selected items
    	int c=0;
    	for (int i = 0; i<jTable2.getRowCount(); i++){
    		if((Boolean)jTable2.getModel().getValueAt(i, 0)){
    			c++;
    		}
    	}
    	if(c==0){
    		return; //* if no item selected then do nothing
    	}
    	
    	addBOM(); //* adds bomItemDetails to vector addedBom & addedBomDet
    	
    	//* get the new selected items
    	String[][] tmp3=new String[c][2];
    	for (int i = 0, j=0; i<jTable2.getRowCount(); i++){
    		if((Boolean)jTable2.getModel().getValueAt(i, 0)){
    			tmp3[j]=new String[2];
    			tmp3[j][0]=jTable2.getModel().getValueAt(i, 1).toString();
    			tmp3[j++][1]=jTable2.getModel().getValueAt(i, 3).toString();
    		}
    	}
    	
    	//* get the previously selected items
    	String[][] tmp2=new String[jTable3.getModel()==null? 0: jTable3.getModel().getRowCount()][9];
    	for (int i = 0; i<tmp2.length; i++){
    		tmp2[i]=new String[9];
    		tmp2[i][0]=jTable3.getModel().getValueAt(i, 1).toString();
    		tmp2[i][1]=jTable3.getModel().getValueAt(i, 2).toString();
    		tmp2[i][2]=jTable3.getModel().getValueAt(i, 3).toString();
    		tmp2[i][3]=jTable3.getModel().getValueAt(i, 5).toString();
    		tmp2[i][4]=jTable3.getModel().getValueAt(i, 7).toString();
    		tmp2[i][5]=jTable3.getModel().getValueAt(i, 9).toString();
    		tmp2[i][6]=jTable3.getModel().getValueAt(i, 11).toString();
    		tmp2[i][7]=jTable3.getModel().getValueAt(i, 13).toString();
    		tmp2[i][8]=jTable3.getModel().getValueAt(i, 15).toString();
    	}
    	fillPoDet(tmp2, tmp3); //* reconstruct PO table
    	
    	//* get selected BOM
    	selectedBomList=addedBom.toArray(); //addBOM() should be called before <--this statement
    	fillBomList();
    	
    	adjustAddedQty(); //* adjusts the item qty. which are already added.
    	
    	jTable2.setModel(new javax.swing.table.DefaultTableModel(
            bomDet,
            new String [] {
                "Select", "Material", "Reqd. Quantity", "Purchase Requirement", "Remark"
            }
        ) {
            Class[] types = new Class [] {
            	java.lang.Boolean.class, 
                java.lang.String.class, 
                java.lang.Long.class, 
                java.lang.Long.class,
                java.lang.String.class
            };
            
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex==0 ? true : false ;
            }
        });
    	//jTabbedPane1.setSelectedIndex(0);
    	
    }
    
    /** called from selectBom(). */
    private void addBOM(){
    	String tmp=jTable1.getModel()
    	 .getValueAt(jTable1.getSelectedRow(), 3).toString();
    	java.util.Vector<String> tmp1=new java.util.Vector<String>();
    	
    	for (int i = 0; i<jTable2.getRowCount(); i++){
    		if((Boolean)jTable2.getModel().getValueAt(i, 0)){
    			tmp1.add(new String(jTable2.getModel().getValueAt(i, 1).toString()));
    		}
    	}
    	if(addedBom.contains(tmp)){
    		int k=addedBom.indexOf(tmp);
    		for(String str: addedBomDet.remove(k)){
    			tmp1.add(new String(str));
    		}
    		addedBomDet.add(k, tmp1);
    	}
    	else{
    		addedBom.add(tmp);
    		addedBomDet.add(tmp1);
    	}
    }
    
    /** called from selectBom(). */
    private void fillBomList(){
    	jList1.setModel(new javax.swing.AbstractListModel() {
            Object[] strings = selectedBomList;
            public int getSize() { return selectedBomList.length; }
            public Object getElementAt(int i) { return selectedBomList[i]; }
        });
    }
    
    /** called from selectBom() & itemSelected(). */
    private void fillPoDet(String[][] oldItmList, String[][] newItmList){
    	poDet=new inventorycontroller.function.PurchaseOrderProcessor(dbInterface1)
    	 .preparePO(oldItmList, newItmList);
    	
    	jTable3.setModel(new javax.swing.table.DefaultTableModel(
            poDet,
            new String [] {
                "Sl No.", "Item", "Qty.", "Rate", "Amount", 
                "Discount (Rs.)", "Discount (%)", 
                "Surcharge (Rs.)", "Surcharge (%)", 
                "Excise Duty (Rs.)", "Excise Duty (%)", 
                "VAT (Rs.)", "VAT (%)", "CST (Rs.)", "CST (%)", 
                "Freight / Insurance (Rs.)", "Freight / Insurance (%)", 
                "Net Amount"
            }
        ) {
            public Class getColumnClass(int columnIndex) {
                //return poDetType[columnIndex];
                return java.lang.String.class;
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return poDetEditable[columnIndex];
            }
        });
        jTable3.getModel().addTableModelListener(new javax.swing.event.TableModelListener(){
        	public void tableChanged(javax.swing.event.TableModelEvent evt){
        		jTable3.getModel().removeTableModelListener(this);
        		poTableEdited(evt);
        		jTable3.getModel().addTableModelListener(this);
        	}
        });
        for (int i = 0; i<oldItmList.length; i++){
        	jTable3.getModel().setValueAt(new String(oldItmList[i][2]), i, 3);
        	jTable3.getModel().setValueAt(new String(oldItmList[i][3]), i, 5);
        	jTable3.getModel().setValueAt(new String(oldItmList[i][4]), i, 7);
        	jTable3.getModel().setValueAt(new String(oldItmList[i][5]), i, 9);
        	jTable3.getModel().setValueAt(new String(oldItmList[i][6]), i, 11);
        	jTable3.getModel().setValueAt(new String(oldItmList[i][7]), i, 13);
        	jTable3.getModel().setValueAt(new String(oldItmList[i][8]), i, 15);
        	
        }
    }
    
    /** called from actionListener of add material button. */
    private void itemSelected(){
    	//* count selected items
    	int c=0;
    	for (int i = 0; i<jTable4.getRowCount(); i++){
    		if((Boolean)jTable4.getModel().getValueAt(i, 0)){
    			c++;
    		}
    	}
    	if(c==0){
    		return; //* if no item selected then do nothing
    	}
    	
    	//* get the new selected items
    	String[][] tmp1=new String[c][2];
    	for (int i = 0, j=0; i<jTable4.getRowCount(); i++){
    		if((Boolean)jTable4.getModel().getValueAt(i, 0)){
    			tmp1[j]=new String[2];
    			tmp1[j][0]=jTable4.getModel().getValueAt(i, 1).toString();
    			tmp1[j++][1]="0";
    		}
    	}
    	
    	//* get the previously selected items
    	String[][] tmp2=new String[jTable3.getModel()==null? 0: jTable3.getModel().getRowCount()][9];
    	for (int i = 0; i<tmp2.length; i++){
    		tmp2[i]=new String[9];
    		tmp2[i][0]=jTable3.getModel().getValueAt(i, 1).toString();
    		tmp2[i][1]=jTable3.getModel().getValueAt(i, 2).toString();
    		tmp2[i][2]=jTable3.getModel().getValueAt(i, 3).toString();
    		tmp2[i][3]=jTable3.getModel().getValueAt(i, 5).toString();
    		tmp2[i][4]=jTable3.getModel().getValueAt(i, 7).toString();
    		tmp2[i][5]=jTable3.getModel().getValueAt(i, 9).toString();
    		tmp2[i][6]=jTable3.getModel().getValueAt(i, 11).toString();
    		tmp2[i][7]=jTable3.getModel().getValueAt(i, 13).toString();
    		tmp2[i][8]=jTable3.getModel().getValueAt(i, 15).toString();
    	}
    	fillPoDet(tmp2, tmp1); //* reconstruct PO table
    	jTabbedPane1.setSelectedIndex(0);
    }
    
    /** this TableModelListener is added from within fillPoDet. */
    private void poTableEdited(javax.swing.event.TableModelEvent evt){
    	String[] tmp0=new String[18];
    	int r=evt.getFirstRow();
    	int c=evt.getColumn();
    	javax.swing.table.TableModel m=(javax.swing.table.TableModel)evt.getSource();
    	for (int i = 0; i<18; i++){
    		tmp0[i]=m.getValueAt(r, i).toString();
    	}
    	
    	Object[] tmp1=new inventorycontroller.function.PurchaseOrderProcessor(dbInterface1)
    	 .calculateNetAmt(tmp0, c);
    	for (int i = 0; i<18; i++){
    		m.setValueAt(tmp1[i], r, i);
    	}
    	updatePoTotal();
    }
    
    /** called from poTableEdited(). */
    private void updatePoTotal(){
    	double res=0;
    	for (int i = 0; i<jTable3.getRowCount(); i++){
    		res+=Double.parseDouble(jTable3.getModel().getValueAt(i, 17).toString());
    	}
    	poAmount=res+"";
        jLabel11.setText("Purchase Order Total:    Rs. "+poAmount);
    }
    
    /** called from actionListener of save PO button. */
    private void insertPerform(){
    	if(!verify()){
    		return;
    	}
    	String val=new String("");
    	val=val+"'"+jTextField1.getText()+"', "; // poNo
    	val=val+"'"+
    		inventorycontroller.util.DateUtil.getRawFormat(
    			(java.util.Date)jXDatePicker1.getValue()
    		)
    		+"', "; // poDate
    	val=val+"'"+vndList[jTextField2.getSelectedIndex()-1]+"', "; // vndNo
    	val=val+"'"+jTextField3.getText()+"', "; // qtnNo
    	val=val+"'"+
    		inventorycontroller.util.DateUtil.getRawFormat(
    			(java.util.Date)jXDatePicker2.getValue()
    		)
    		+"', "; // qtnDate
    	val=val+"'"+poAmount+"', "; // poTotal
    	val=val+"'pending', "; // poStatus
    	val=val+"'"+jEditorPane1.getText()+"' "; // remark
    	
    	try{
    		dbInterface1.cmdInsert("poMaster", val);
    		insertPoBomDesc();
    		insertPoDesc();
	    	updateBOM();
    	}
    	catch(java.sql.SQLException ex){
    		System.out.println (ex);
    	}
    	resetPerform();
    }
    
    /** called from insertPerform(). */
    private boolean verify(){
    	//* vendor name cannot be empty
    	if(jTextField2.getSelectedIndex()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"No Vendor is selected. Select one Vendor first\"</center></html>",
				"InventoryController says...",
			javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField2.grabFocus();
			return false;
    	}
    	
    	//* qtn no cannot be empty
    	if(jTextField3.getText().length()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Quotation No. field cannot be left empty\"</center></html>",
				"InventoryController says...",
			javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField3.grabFocus();
			return false;
    	}
    	
    	//* poTable should contain at least one material.
    	if(jTable3.getModel().getRowCount()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"No Material selected for Purchase Order\"</center></html>",
				"InventoryController says...",
			javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField3.grabFocus();
			return false;
    	}
    	
    	//* material order qty should be >0
    	javax.swing.table.TableModel tm=jTable3.getModel();
    	for (int i = 0; i<tm.getRowCount(); i++){
    		if(Double.parseDouble(tm.getValueAt(i, 2).toString())<=0){
	    		javax.swing.JOptionPane.showInternalMessageDialog(this,
					"<html><center>\"Ordered Material quantity should be more than ZERO\"</center></html>",
					"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
				jTable3.grabFocus();
				jTable3.changeSelection(i, 2, false, false);
				jTable3.changeSelection(i, 2, false, true);
				return false;
    		}
    	}
    	//* material order rate should be >0
    	for (int i = 0; i<tm.getRowCount(); i++){
    		if(Double.parseDouble(tm.getValueAt(i, 3).toString())<=0){
	    		javax.swing.JOptionPane.showInternalMessageDialog(this,
					"<html><center>\"Ordered Material rate should be more than ZERO\"</center></html>",
					"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
				jTable3.grabFocus();
				jTable3.changeSelection(i, 3, false, false);
				jTable3.changeSelection(i, 3, false, true);
				return false;
    		}
    	}
    	return true;
    }
    
    /** called from insertPerform(). */
    private void insertPoBomDesc() throws java.sql.SQLException {
    	int count=0;
    	for(java.util.Vector<String> c1: addedBomDet){
    		count+=c1.size();
    	}
    	String val[]=new String[count];
    	String poNo=jTextField1.getText();
    	inventorycontroller.function.MaterialProcessor mp=
    	 new inventorycontroller.function.MaterialProcessor(dbInterface1);
    	int i=0;
    	for (String bomNo: addedBom){
    		for(String matNo: addedBomDet.elementAt(addedBom.indexOf(bomNo))){
    			val[i++]=new String(
    				"'"+poNo+"', '"+bomNo+"', '"+mp.getMatId(matNo)+"'"
    			);
    		}
    	}
    	dbInterface1.cmdInsert("poBomDesc", val);
    }
    
    /** called from insertPerform(). */
    private void insertPoDesc() throws java.sql.SQLException {
    	String poNo=jTextField1.getText();
    	String val[]=new String[jTable3.getRowCount()];
    	inventorycontroller.function.MaterialProcessor mp=
    	 new inventorycontroller.function.MaterialProcessor(dbInterface1);
    	final int[] indices=new int[] {2, 3, 6, 8, 10, 12, 14, 16};
    	for (int i = 0; i<val.length; i++){
    		val[i]=new String(
    			"'"+poNo+"', '"+mp.getMatId(jTable3.getModel().getValueAt(i, 1).toString())
    		);
    		for(int j: indices){
    			val[i]=val[i]+"', '"+jTable3.getModel().getValueAt(i, j).toString();
    		}
    		val[i]=val[i]+"'";
    	}
    	dbInterface1.cmdInsert("poDesc", val);
    }
    
    /** called from insertPerform(). */
    private void updateBOM() throws java.sql.SQLException {
    	String[] val=null;
    	java.util.Vector<String> tmp=null;
		inventorycontroller.function.BillOfMaterialProcessor bp=
		 new inventorycontroller.function.BillOfMaterialProcessor(dbInterface1);
		inventorycontroller.function.MaterialProcessor mp=
		 new inventorycontroller.function.MaterialProcessor(dbInterface1);
    	
    	int i=0;
    	for(String bomNo: addedBom){
    		tmp=addedBomDet.elementAt(addedBom.indexOf(bomNo));
    		val=new String[tmp.size()];
    		i=0;
    		for(String matName: tmp){
    			val[i++]=new String(mp.getMatId(matName));
    		}
    		bp.resetPurchaseReq(bomNo, val);
    		if(bp.isComplete(bomNo)){
    			bp.setBOMStatus(bomNo, "complete");
    		}
    	}
    }
    
    private void resetPerform(){
    	resetPerforming=true;
	    addedBom=new java.util.Vector<String>(); 
	    addedBomDet=new java.util.Vector<java.util.Vector<String>>(); 
    	jRadioButton1.setSelected(true);
    	fillVendorList();
    	jTextField2.setSelectedIndex(0);
    	jTextField3.setText("");
    	jXDatePicker1.setValue(inventorycontroller.util.DateUtil.getDateValue(
    			inventorycontroller.function.DateProcessor.getCurrentDate(dbInterface1)
    		)
    	);
    	jXDatePicker2.setValue(inventorycontroller.util.DateUtil.getDateValue(
    			inventorycontroller.function.DateProcessor.getCurrentDate(dbInterface1)
    		)
    	);
    	jEditorPane1.setText("");
    	poAmount="0.0";
        jLabel11.setText("Purchase Order Total:    Rs. "+poAmount);
    	resetSelectedBom();
    	jTabbedPane1.setSelectedIndex(0);
    	jRadioButton1.doClick(4);
    	
    	jTextField1.grabFocus();
    	resetPerforming=false;
    }
    
    private void fillVendorList(){
    	String[][] tmp=new inventorycontroller.function.VendorProcessor(dbInterface1)
    	 .getVndNameId("1=1 order by vndMaster.vndCmpName");
    	vndList=new String[tmp.length];
    	jTextField2.removeAllItems();
    	jTextField2.addItem("");
    	for (int i = 0; i<tmp.length; i++){
    		vndList[i]=new String(tmp[i][0]);
    		jTextField2.addItem(new String(tmp[i][1]+" ("+tmp[i][0]+")"));
    	}
    }
    
    private void resetSelectedBom(){
    	jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = null;
            public int getSize() { return 0; }
            public Object getElementAt(int i) { return null; }
        });
	    addedBom=new java.util.Vector<String>(); 
	    addedBomDet=new java.util.Vector<java.util.Vector<String>>(); 
        
    }
    
    private void resetPoTable(){
    	poDet=null;
    	jTable3.setModel(new javax.swing.table.DefaultTableModel(
            poDet,
            new String [] {
                "Sl No.", "Item", "Qty.", "Rate", "Amount", 
                "Discount (Rs.)", "Discount (%)", 
                "Surcharge (Rs.)", "Surcharge (%)", 
                "Excise Duty (Rs.)", "Excise Duty (%)", 
                "VAT (Rs.)", "VAT (%)", "CST (Rs.)", "CST (%)", 
                "Freight / Insurance (Rs.)", "Freight / Insurance (%)", 
                "Net Amount"
            }
        ));
    }
    
    private void resetBomTable(){
    	bomList=new inventorycontroller.function.BillOfMaterialProcessor(dbInterface1)
    	 .getBomForPoMaster();
    	
    	jTable1.setModel(new javax.swing.table.DefaultTableModel(
            bomList,
            new String [] {
                "Customer Name", "Job No.", "BOM Requisition Date", "BOM No."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, 
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
        
    }
    
    private void resetBomDetails(){
    	jTable2.setModel(new javax.swing.table.DefaultTableModel(
            null,
            new String [] {
                "Select", "Material", "Reqd. Quantity", "Purchase Requirement", "Remark"
            }
        ));
        
    }
    
    private void resetItmTable(){
    	itmList=new inventorycontroller.function.MaterialProcessor(dbInterface1)
    	 .getMatNamesForPo("1=1");
    	
    	jTable4.setModel(new javax.swing.table.DefaultTableModel(
            itmList,
            new String [] {
                "Select", "Material Description", "Material ID", "Unit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, 
                java.lang.String.class, 
                java.lang.String.class, 
                java.lang.String.class
            };
            
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex==0? true: false;
            }
        });
        jTable4.getColumnModel().getColumn(0).setPreferredWidth(48);
        jTable4.getColumnModel().getColumn(1).setPreferredWidth(220);
        jTable4.getColumnModel().getColumn(2).setPreferredWidth(160);
        jTable4.getColumnModel().getColumn(3).setPreferredWidth(48);
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
    
    
    // Variables declaration -
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.ButtonGroup buttonGroup1;
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
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JLabel jTextField1;
    private javax.swing.JComboBox jTextField2;
    private javax.swing.JTextField jTextField3;
    private inventorycontroller.display.RDateSpinner jXDatePicker1;
    private inventorycontroller.display.RDateSpinner jXDatePicker2;
    
    private inventorycontroller.function.DbInterface dbInterface1;
    private String poAmount;
    private int poType;
    private boolean resetPerforming;
    private Object[] selectedBomList;
    private Object[][] bomList;
    private Object[][] bomDet;
    private Object[][] itmList;
    private Object[][] poDet;
    private String[] vndList;
    private Class[] poDetType;
    private boolean[] poDetEditableForBM;
    private boolean[] poDetEditableForLP;
    private boolean[] poDetEditable;
    private java.util.Vector<String> addedBom; 
    private java.util.Vector<java.util.Vector<String>> addedBomDet;
    // End of variables declaration
    
}

