/*
 * JInternalFrameShowPO.java
 *
 * Created on August 08, 2007, 14:45 HRS
 * Last Modified on August 09, 2007, 12:45 HRS
 *
 */

package inventorycontroller.display;

/**
 *
 * @author  brinto
 */
public class JInternalFrameShowPO extends javax.swing.JInternalFrame {
	
	private static final String PO_ALL="All";
	private static final String PO_PENDING="Pending";
	private static final String PO_PARTLY_DELIVERED="Partly Delivered";
	private static final String PO_DELIVERED="Delivered";
    
    /** Creates new form JInternalFrameShowPO */
    public JInternalFrameShowPO(inventorycontroller.function.DbInterface dbi) {
        initComponents(dbi);
    }
    
    /** This method is called from within the constructor to initialize the form. */
    private void initComponents(inventorycontroller.function.DbInterface dbi) {
    	dbInterface1=dbi;
    	poList=null;
    	poDet=null;
    	bomList=null;
    	matList=null;
    	poType=PO_ALL;
    	selectedPoNo="";
    	
    	jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jList1 = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jTable1 = new javax.swing.JTable();
        jTable2 = new javax.swing.JTable();
        jTable3 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();

        setTitle("Purchase Order Viewer");
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        jTable1.setAutoCreateRowSorter(false);
        
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
        jTable1.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener(){
        	public void valueChanged(javax.swing.event.ListSelectionEvent e) {
        		//Ignore extra messages.
        		if (e.getValueIsAdjusting()) return;
        		javax.swing.ListSelectionModel lsm =(javax.swing.ListSelectionModel)e.getSource();
        		int selectedRow;
        		if (lsm.isSelectionEmpty()) { //no rows are selected
        			selectedRow=-1;
        			selectedPoNo="";
        		} 
        		else {
        			selectedRow = lsm.getMinSelectionIndex();
        			selectedPoNo=jTable1.getModel().getValueAt(selectedRow, 0).toString();
            		poSelected(jTable1.getModel().getValueAt(selectedRow, 0).toString());
            	}
        	}
		});
        jScrollPane1.setViewportView(jTable1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(
        	new String[] { PO_ALL, PO_PENDING, PO_PARTLY_DELIVERED, PO_DELIVERED })
        );
        jComboBox1.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		poTypeChanged();
        	}
        });

        jLabel4.setText("Purchase Orders");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, 22, 22, 22)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addContainerGap())
        );
        jTabbedPane1.addTab("Purchase Orders", jPanel3);

        jLabel5.setText("PO No.");

        jLabel6.setText("PO Date");

        jLabel7.setText("Vendor Name");

        jLabel8.setText("Quotation No.");

        jLabel9.setText("BOM No.");

        jTextField1.setText("jTextField1");

        jTextField2.setText("jTextField2");

        jTextField3.setText("jTextField3");

        jTextField4.setText("jTextField4");

        jTextField5.setText("jTextField5");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Enter Search Criteria");

        jLabel11.setText("PO Amount");

        jLabel12.setText("More than");

        jLabel13.setText("Less than");

        jButton6.setText("Search Purchase Order");
        jButton6.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		searchPO();
        	}
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, Short.MAX_VALUE))
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addContainerGap())
        );
        jTabbedPane1.addTab("Search Purchase Order", jPanel4);
        jTabbedPane1.setEnabledAt(1, false);

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener(){
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
        			bomSelected(jList1.getSelectedValue().toString());
            	}
        	}
		});
        jScrollPane2.setViewportView(jList1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addContainerGap())
        );
        jTabbedPane2.addTab("Associated BOM List", jPanel5);

        jTable2.setAutoCreateRowSorter(false);
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addContainerGap())
        );
        jTabbedPane2.addTab("Material List", jPanel6);

        jTable3.setAutoCreateRowSorter(false);
        jTable3.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane4.setViewportView(jTable3);

        jLabel2.setText("Purchase Order Description:");

        jButton1.setText("Create Goods Receive  Note");
        jButton1.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		createGRN();
        	}
        });

        jButton2.setText("Generate Printable Format");
        jButton2.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		createPrintView();
        	}
        });

        jButton5.setText("Modify Purchase Order");
        jButton5.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		editPO();
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setBackground(new java.awt.Color(127, 157, 185));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");
        jLabel1.setOpaque(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        jButton3.setText("Reset");
        jButton3.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		resetPerform();
        	}
        });
        jButton3.setMaximumSize(new java.awt.Dimension(120, 22));
        jButton3.setMinimumSize(new java.awt.Dimension(120, 22));
        jButton3.setPreferredSize(new java.awt.Dimension(120, 22));

        jButton4.setText("Exit");
        jButton4.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		exitPerform();
        	}
        });
        jButton4.setMaximumSize(new java.awt.Dimension(120, 22));
        jButton4.setMinimumSize(new java.awt.Dimension(120, 22));
        jButton4.setPreferredSize(new java.awt.Dimension(120, 22));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
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
    //* ENDING: initComponents
    }
    
    /** called from selectionChanged of table1.selectionModel */
    private void poSelected(String poNo){
    	showPoBomDet(poNo);
    	fillPoDet(poNo);
    	
    	resetMatList();
    }
    
    /** called from poSelected */
    private void showPoBomDet(String poNo){
    	bomList=new inventorycontroller.function.PurchaseOrderProcessor(dbInterface1)
    	 .getBomList(poNo);
    	jList1.setModel(new javax.swing.AbstractListModel() {
            Object[] strings = bomList;
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
    }
    
    /** called from poSelected */
    private void fillPoDet(String poNo){
    	poDet=new inventorycontroller.function.PurchaseOrderProcessor(dbInterface1)
    	 .getPoDetForShowPO(poNo);
    	jTable3.setModel(new javax.swing.table.DefaultTableModel(
            poDet,
            new String [] {
                "Sl No.", 
                "Material No.", 
                "Material Name", 
                "Quantity", 
                "Rate", 
                "Amount", 
                "Discount (Rs.)", 
                "Discount (%)", 
                "Surcharge (Rs.)", 
                "Surcharge (%)", 
                "Excise Duty (Rs.)", 
                "Excise Duty (%)", 
                "VAT (Rs.)", 
                "VAT (%)", 
                "CST (Rs.)", 
                "CST (%)", 
                "Freight / Insurance (Rs.)", 
                "Freight / Insurance (%)", 
                "Net Amount" 
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false,  true, false,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.getModel().addTableModelListener(new javax.swing.event.TableModelListener(){
        	public void tableChanged(javax.swing.event.TableModelEvent evt){
        		jTable3.getModel().removeTableModelListener(this);
        		poTableEdited(evt);
        		jTable3.getModel().addTableModelListener(this);
        	}
        });
    }
    
    /** this TableModelListener is added from within fillPoDet. */
    private void poTableEdited(javax.swing.event.TableModelEvent evt){
    	String[] tmp0=new String[18];
    	int r=evt.getFirstRow();
    	int c=evt.getColumn();
    	javax.swing.table.TableModel m=(javax.swing.table.TableModel)evt.getSource();
    	
    	// matNo is not sent to calculateNetAmt().
    	tmp0[0]=m.getValueAt(r, 0).toString();
    	for (int i = 1; i<18; i++){
    		tmp0[i]=m.getValueAt(r, i+1).toString();
    	}
    	
    	Object[] tmp1=new inventorycontroller.function.PurchaseOrderProcessor(dbInterface1)
    	 .calculateNetAmt(tmp0, c-1); // as calculateNetAmt() dont takes matId as an col. so c-1
    	 
    	// updated from rate col. onwards
    	for (int i = 3; i<18; i++){
    		m.setValueAt(tmp1[i], r, i+1);
    	}
    	
    	updatePoTotal();
    }
    
    /** called from poTableEdited(). */
    private void updatePoTotal(){
    	double res=0;
    	for (int i = 0; i<jTable3.getRowCount(); i++){
    		res+=Double.parseDouble(jTable3.getModel().getValueAt(i, 18).toString());
    	}
    	jTable1.getModel().setValueAt(res+"", jTable1.getSelectedRow(), 2);
    }
    
    /** called from selectionChanged of list1.selectionModel */
    private void bomSelected(String bomNo){
    	matList=new inventorycontroller.function.PurchaseOrderProcessor(dbInterface1)
    	 .getPoBomDet(
    	 	jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0).toString(), 
    	 	bomNo
    	 );
    	
    	jTable2.setModel(new javax.swing.table.DefaultTableModel(
            matList,
            new String [] {
                "Material Name", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }
    
    /** called from actionPerformed of jComboBox1 */
    private void poTypeChanged(){
    	poType=jComboBox1.getSelectedItem().toString();
    	
    	if(poType==PO_ALL){
    		poList=new inventorycontroller.function.PurchaseOrderProcessor(dbInterface1)
    		 .getAllPO();
    	}
    	else if(poType==PO_PENDING){
    		poList=new inventorycontroller.function.PurchaseOrderProcessor(dbInterface1)
    		 .getPendingPO();
    	}
    	else if(poType==PO_PARTLY_DELIVERED){
    		poList=new inventorycontroller.function.PurchaseOrderProcessor(dbInterface1)
    		 .getIncompletePO();
    	}
    	else if(poType==PO_DELIVERED){
    		poList=new inventorycontroller.function.PurchaseOrderProcessor(dbInterface1)
    		 .getCompletePO();
    	}
    	
    	fillPoTable(poList);
    	resetBomList();
        resetPoDet();
    	
    	resetMatList();
    	
    }
    
    private void fillPoTable(Object[][] data){
    	jTable1.setModel(new javax.swing.table.DefaultTableModel(
            data,
            new String [] {
                "PO No.", 
                "PO Date", 
                "PO Amount", 
                "Vendor Name", 
                "Quotation No.", 
                "Quotation Date"
            }
        ) {
        	boolean[] canEdit=new boolean[] {
        		false, false, false, true, true, true
        	};
        	
            public Class getColumnClass(int columnIndex) {
                return java.lang.String.class;
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }
    
    /** called from actionPerformed of jButton6 */
    private void searchPO(){
    	
    }
    
    /** called from actionPerformed of jButton1 */
    private void createGRN(){
    	this.getDesktopPane().getRootPane().getJMenuBar().getMenu(3).getItem(0).doClick(4);
    	try{
    		this.setClosed(true);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    /** called from actionPerformed of jButton2 */
    private void createPrintView(){
    	this.getDesktopPane().getRootPane().getJMenuBar().getMenu(4).getItem(2).doClick(4);
    }
    
    /** called from actionPerformed of jButton5 */
    private void editPO(){
    	if(jTable1.getSelectedRow()==-1){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"No PO selected.<br>Select one PO to update\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	if(!canEdit()){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Cannot update.<br>Corresponding GRN exists.\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.INFORMATION_MESSAGE);
    		return;
    	}
    	if(!verifyVendor()){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Cannot update.<br>Vendor doesn't exist.\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.WARNING_MESSAGE);
    		return;
    	}
    	if(updatePoMaster()){
    		if(updatePoDesc()){
	    		javax.swing.JOptionPane.showInternalMessageDialog(this,
					"<html><center>\"Purchase Order updated.\"</center></html>",
					"InventoryController says...",
					javax.swing.JOptionPane.INFORMATION_MESSAGE);
    		}
    	}
    }
    
    /** called from editPO() */
    private boolean canEdit() {
    	String tmp1=jComboBox1.getSelectedItem().toString();
    	
    	if(tmp1.compareTo(PO_DELIVERED)==0 || tmp1.compareTo(PO_PARTLY_DELIVERED)==0){
    		return false;
    	}
    	
    	if(tmp1.compareTo(PO_PENDING)==0){
    		return true;
    	}
    	
    	// now for PO_ALL
    	String[][] tmp=null;
    	try{
    		tmp=dbInterface1.cmdSelect(
    			"grnMaster",
    			"grnMaster.poNo", 
    			"grnMaster.poNo='"+selectedPoNo+"'"
    		);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return false;
    	}
    	if(tmp.length>0){
    		return false;
    	}
    	return true;
    }
    
    /** called from editPO() */
    private boolean verifyVendor(){
    	// verify validity of vendor name
    	String[][] tmp2=null;
    	try{
    		tmp2=dbInterface1.cmdSelect(
    			"vndMaster",
    			"vndMaster.vndNo", 
    			"vndMaster.vndCmpName='"+jTable1.getModel().
    			 getValueAt(jTable1.getSelectedRow(), 3)+"'"
    		);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return false;
    	}
    	if(tmp2.length==0){
    		return false;
    	}
    	return true;
    }
    
    /** called from editPO() */
    private boolean updatePoMaster() {
    	try{
    		String tmp[][]=dbInterface1.cmdSelect(
    			"vndMaster",
    			"vndMaster.vndNo", 
    			"vndMaster.vndCmpName='"+jTable1.getModel().
    			 getValueAt(jTable1.getSelectedRow(), 3)+"'"
    		);
    		dbInterface1.cmdUpdate(
    			"poMaster",
    			
    			"poMaster.poTotal='"+jTable1.getModel().
    			 getValueAt(jTable1.getSelectedRow(), 2)+"', "+
    			"poMaster.vndNo='"+tmp[0][0]+"', "+
    			"poMaster.qtnNo='"+jTable1.getModel().
    			 getValueAt(jTable1.getSelectedRow(), 4)+"', "+
    			"poMaster.qtnDate='"+jTable1.getModel().
    			 getValueAt(jTable1.getSelectedRow(), 5)+"'",
    			
    			"poMaster.poNo='"+selectedPoNo+"'"
    		);
    		return true;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return false;
    	}
    }
    
    /** called from editPO() */
    private boolean updatePoDesc() {
    	boolean f=true;
    	javax.swing.table.TableModel m=jTable3.getModel();
    	for (int i = 0; i<m.getRowCount(); i++){
    		String setValues=
    		 "   poDesc.matRate='"+m.getValueAt(i, 4)+
    		 "', poDesc.discount='"+m.getValueAt(i, 7)+
    		 "', poDesc.surcharge='"+m.getValueAt(i, 9)+
    		 "', poDesc.exDuty='"+m.getValueAt(i, 11)+
    		 "', poDesc.vat='"+m.getValueAt(i, 13)+
    		 "', poDesc.cst='"+m.getValueAt(i, 15)+
    		 "', poDesc.freight='"+m.getValueAt(i, 17)+"'";
    		 
    		String whereClause=
    		 "poDesc.poNo='"+selectedPoNo+"' and "+
    		 "poDesc.matNo='"+m.getValueAt(i, 1)+"'";
    		
    		try{
    		 	dbInterface1.cmdUpdate(
    		 		"poDesc", 
    		 		setValues, 
    		 		whereClause
    		 	);
    		}
    		catch(Exception ex){
    			f=false;
    			ex.printStackTrace();
    		}
    	}
    	return f;
    }
    
    /** called from actionPerformed of jButton3 */
    private void resetPerform(){
    	jComboBox1.setSelectedIndex(0);
    }
    
    /** called from poTypeChanged */
    private void resetBomList(){
    	jList1.setModel(new javax.swing.AbstractListModel() {
            Object[] strings = null;
            public int getSize() { return 0; }
            public Object getElementAt(int i) { return null; }
        });
    }
    
    /** called from poTypeChanged */
    private void resetPoDet(){
    	jTable3.setModel(new javax.swing.table.DefaultTableModel(
            null,
            new String [] {
                "Sl No.", "Material", "Quantity", "Rate", "Amount", "Discount (Rs.)", "Discount (%)", "Surcharge (Rs.)", "Surcharge (%)", "Excise Duty (Rs.)", "Excise Duty (%)", "VAT (Rs.)", "VAT (%)", "CST (Rs.)", "CST (%)", "Freight / Insurance (Rs.)", "Freight / Insurance (%)", "Net Amount"
            }
        ));
    }
    
    /** called from poTypeChanged() 
     *              poSelected()   */
    private void resetMatList(){
    	jTable2.setModel(new javax.swing.table.DefaultTableModel(
            null,
            new String [] {
                "Material Name", "Quantity"
            }
        ));
    }
    
    /** called from actionPerformed of jButton4 */
    private void exitPerform(){
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
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    
    private inventorycontroller.function.DbInterface dbInterface1;
    private String selectedPoNo;
    private String poType;
    private Object[][] poList;
    private Object[][] poDet;
    private Object[] bomList;
    private Object[][] matList;
    // End of variables declaration
    
}
