/*
 * JInternalFrameGoodsIssue.java
 *
 * Created on August 10, 2007, 10:51 HRS
 * Last Modified on August 11, 2007, 11:25 HRS
 *
 */

package inventorycontroller.display;

/**
 *
 * @author  brinto
 */
public class JInternalFrameGoodsIssue extends javax.swing.JInternalFrame {
    
    /** Creates new form JInternalFrameGoodsIssue */
    public JInternalFrameGoodsIssue(inventorycontroller.function.DbInterface dbi) {
        initComponents(dbi);
    }
    
    /** This method is called from within the constructor to initialize the form. */
    private void initComponents(inventorycontroller.function.DbInterface dbi) {
    	dbInterface1=dbi;
    	resetPerforming=false;
    	trnType=-1;
    	jobList=null;
    	matStatus=null;
    	trnDet=null;
    	trnTableHeader=null;
    	rqnTableHeader=new String[]{
            	"Sl No.", 
                "Material ID", 
                "Material Name", 
                "Required Qty.", 
                "Consumption Pending", 
                "Issue", 
                "Issued (Excisable)", 
                "Issued (General)"
    	};
    	retTableHeader=new String[]{
            	"Sl No.", 
                "Material ID", 
                "Material Name", 
                "Required Qty.", 
                "Consumed Qty.", 
                "Return", 
                "Returned (Excisable)", 
                "Returned (General)"
    	};
    	
    	jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JLabel();
        jXDatePicker1 = new inventorycontroller.display.RDateSpinner(inventorycontroller.display.RDateSpinner.DD_MM_YYYY);
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        
        jLabel1.setBackground(new java.awt.Color(127, 157, 185));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");
        jLabel1.setOpaque(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        jLabel2.setText("Job No.");

        jLabel5.setText("Remark:");

        jScrollPane1.setViewportView(jTextPane1);

        jLabel6.setText("BOM No.");

        jLabel8.setText("Material Status:");

        jTable1.setAutoCreateRowSorter(false);
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(jTable1);
        
        jXDatePicker1.addChangeListener(new javax.swing.event.ChangeListener(){
        	public void stateChanged(javax.swing.event.ChangeEvent evt){
        		String date=inventorycontroller.util.DateUtil.getRawFormat(
        			(java.util.Date)jXDatePicker1.getValue()
        		);
        		jTextField2.setText(
					new inventorycontroller.function.GoodsIssueReturnProcessor(dbInterface1)
		    		 .getNewTrnNo(trnType, date)
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
	                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
	                    .addComponent(jRadioButton1)
	                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                    .addComponent(jRadioButton2))
	            	.addGroup(jPanel3Layout.createSequentialGroup()
		                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addComponent(jLabel4)
		                    .addComponent(jLabel3)
		                    .addComponent(jLabel2)
		                    .addComponent(jLabel5))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
		                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
		                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
		                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
		                .addGap(22, 22, 22)
		                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(jPanel3Layout.createSequentialGroup()
		                        .addComponent(jLabel6)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
		                    .addComponent(jLabel8)
		                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jXDatePicker1, 20, 20, 20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addContainerGap())
        );
        jTabbedPane1.addTab("Issue / Return to Store", jPanel3);

        jTable2.setAutoCreateRowSorter(false);
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jTable2);

        jLabel9.setText("Job List:");

        jButton4.setText("Select Job");
        jButton4.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		selectJob();
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
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
                    .addComponent(jLabel9))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jTabbedPane1.addTab("Job Selection", jPanel4);

        jRadioButton1.setText("Requisition");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		toggleIssueReturn((javax.swing.JRadioButton)evt.getSource());
        	}
        });
        
        jRadioButton2.setText("Return to Store");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		toggleIssueReturn((javax.swing.JRadioButton)evt.getSource());
        	}
        });
        
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        
        jTable3.setAutoCreateRowSorter(false);
        jTable3.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane4.setViewportView(jTable3);

        jButton1.setText("Issue Goods / Return Goods to Store");
        jButton1.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		insertPerform();
        	}
        });

        jButton5.setText("Print Last Requisition / Return Slip");
        jButton5.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		createPrintView();
        	}
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                    
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel1Layout.createSequentialGroup()
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
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
        setTitle("Goods Requisition / Return");
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        getRootPane().setDefaultButton(jButton1);
    //* ENDING: initComponent()
    }
    
    private void createPrintView(){
    	//this.getDesktopPane().getRootPane().getJMenuBar().getMenu(4).getItem(4).doClick(4);
    	String[][] grcNo=null;
    	String grcid="";
		String yr=inventorycontroller.util.DateUtil.getFinancialYear(
			inventorycontroller.function.DateProcessor.getCurrentDate(this.dbInterface1)
		);
		if(trnType==inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_REQUISITION){
			grcid=inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_REQUISITION_ID;
		}
		else if(trnType==inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_RETURN){
			grcid=inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_RETURN_ID;
		}
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
    
    /** called from actionLiisteners of radioButtons: issue / return. */
    private void toggleIssueReturn(javax.swing.JRadioButton jrb){
    	String date=inventorycontroller.util.DateUtil.getRawFormat(
    		(java.util.Date)jXDatePicker1.getValue()
    	);
    	if(jrb==jRadioButton1){
    		if(!resetPerforming && trnType==inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_REQUISITION){
    			return;
    		}
    		trnType=inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_REQUISITION;
	    	trnTableHeader=rqnTableHeader;
	    	jLabel3.setText("Requisition No.");
	    	jLabel4.setText("Requisition Date");
	    	jTextField1.setText("");
	    	jTextField2.setText(
	    		new inventorycontroller.function.GoodsIssueReturnProcessor(dbInterface1)
	    		 .getNewTrnNo(trnType, date)
	    	);
	    	jTextField4.setText("");
	    	resetMaterialStatus();
	    	resetTransactionDesc();
    		resetJobList();
    	
    	}
    	else if(jrb==jRadioButton2){
    		if(!resetPerforming && trnType==inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_RETURN){
    			return;
    		}
    		trnType=inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_RETURN;
        	trnTableHeader=retTableHeader;
	    	jLabel3.setText("RTS No.");
	    	jLabel4.setText("RTS Date");
	    	jTextField1.setText("");
	    	jTextField2.setText(
	    		new inventorycontroller.function.GoodsIssueReturnProcessor(dbInterface1)
	    		 .getNewTrnNo(trnType, date)
	    	);
	    	jTextField4.setText("");
	    	resetMaterialStatus();
	    	resetTransactionDesc();
    		resetJobList();
    	}
    	
    }
    
    /** called from actionLiisteners of jButton4: select job. */
    private void selectJob(){
    	int r=jTable2.getSelectedRow();
    	if(r<0){
    		return;
    	}
    	javax.swing.table.TableModel tm=jTable2.getModel();
    	jTextField1.setText(tm.getValueAt(r, 1).toString());
    	jTextField4.setText(tm.getValueAt(r, 2).toString());
    	setMaterialStatus();
    	fillTransactionDesc();
    	
    	jTabbedPane1.setSelectedIndex(0);
    }
    
    /** called from selectJob(). 
     * this method should be called after bomNo is set at jTextField4, 
     * because this method fetches bomNo from jTextField4. 
     */
    private void setMaterialStatus(){
    	matStatus=new inventorycontroller.function.GoodsIssueReturnProcessor(dbInterface1)
    	 .prepareMaterialStatus(jTextField4.getText());
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            matStatus,
            new String [] {
                "Material", 
                "Stock in hand", 
                "Order pending", 
                "Delivery pending", 
                "Allocated Qty."
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
    
    
    /** called from selectJob(). 
     * this method should be called after bomNo is set at jTextField4, 
     * because this method fetches bomNo from jTextField4. 
     */
    private void fillTransactionDesc(){
    	if(trnType==inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_REQUISITION){
			trnDet=new inventorycontroller.function.GoodsIssueReturnProcessor(dbInterface1)
			 .prepareRequisition(jTextField4.getText());
    	}
    	else if(trnType==inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_RETURN){
    		trnDet=new inventorycontroller.function.GoodsIssueReturnProcessor(dbInterface1)
    		 .prepareReturn(jTextField4.getText());
    	}
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            trnDet,
            trnTableHeader
        ) {
            public Class getColumnClass(int columnIndex) {
                return java.lang.String.class;
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex==5;
            }
        });
        jTable3.getModel().addTableModelListener(new javax.swing.event.TableModelListener(){
        	public void tableChanged(javax.swing.event.TableModelEvent evt){
        		jTable3.getModel().removeTableModelListener(this);
        		transactionTableEdited(evt);
        		jTable3.getModel().addTableModelListener(this);
        	}
        });
    }
    
    /** called from fillTransactionDesc(). */
    private void transactionTableEdited(javax.swing.event.TableModelEvent evt){
    	int r=evt.getFirstRow();
    	javax.swing.table.TableModel m=(javax.swing.table.TableModel)evt.getSource();
    	double stk=Double.parseDouble(
    		new inventorycontroller.function.MaterialProcessor(dbInterface1).getStock(
    			m.getValueAt(r, 1).toString()
    		)
    	);
    	String ul=m.getValueAt(r, 4).toString();
    	if(trnType==inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_REQUISITION){
    		ul=(Double.parseDouble(ul)>stk? stk: ul)+"";
    	}
    	String[] tmp1=new inventorycontroller.function.GoodsIssueReturnProcessor(dbInterface1)
    	 .calculateQtyDivision(
    	 	jTextField1.getText(), //jobNo
    	 	m.getValueAt(r, 1).toString(), //matNo
    	 	m.getValueAt(r, 5).toString(), //qty
    	 	ul, //upperLimit
    	 	trnType //type
    	 );
    	for (int i = 0; i<3; i++){
    		m.setValueAt(tmp1[i], r, i+5);
    	}
    	
    }
    
    /** called from actionLiisteners of jButton1: Issue Goods / Return Goods to Store. */
    private void insertPerform(){
    	if(!verify()){
    		return;
    	}
    	String val="";
    	val=val+   "'"+jTextField2.getText(); // trnNo
    	val=val+"', '"+jTextField1.getText(); // jbNo
    	val=val+"', '"+
    		inventorycontroller.util.DateUtil.getRawFormat(
    			(java.util.Date)jXDatePicker1.getValue()
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
			jTabbedPane1.setSelectedIndex(1);
			return false;
    	}
    	
    	//* date cannot be in future
    	String dtIr=inventorycontroller.util.DateUtil.getRawFormat(
    		(java.util.Date)this.jXDatePicker1.getValue()
    	);
    	String dtCur=inventorycontroller.function.DateProcessor
    	 .getCurrentDate(this.dbInterface1);
    	if(dtIr.compareTo(dtCur)>0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Requisition/Return Date yet not came\"</center></html>",
				"InventoryController says...",
			javax.swing.JOptionPane.ERROR_MESSAGE);
			jXDatePicker1.grabFocus();
			return false;
    	}
    	
    	//* at least one material should be issued.
    	javax.swing.table.TableModel tm=jTable3.getModel();
    	boolean isIssued=false;
    	for (int i = 0; i<tm.getRowCount(); i++){
    		if(Double.parseDouble(tm.getValueAt(i, 5).toString())>0){
    			isIssued=true;
    			break;
    		}
    	}
    	if(!isIssued){
			javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"At least one material should be issued / returned\"</center></html>",
				"InventoryController says...",
			javax.swing.JOptionPane.ERROR_MESSAGE);
			jTable3.grabFocus();
			jTable3.changeSelection(0, 5, false, false);
			jTable3.changeSelection(0, 5, false, true);
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
    	 (javax.swing.table.TableModel)jTable3.getModel();
    	for (int i = 0; i<tm.getRowCount(); i++){
    		if(Double.parseDouble(tm.getValueAt(i, 5).toString())>0){
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
    		trnDet[i][1]=new String(jTable3.getValueAt(r, 1).toString()); //matNo
    		trnDet[i][2]=new String(jTable3.getValueAt(r, 7).toString()); //qtyGen
    		trnDet[i][3]=new String(jTable3.getValueAt(r, 6).toString()); //qtyEx
    		if(trnType==inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_REQUISITION){
    			amts=mp.getAmounts(trnDet[i][1]);
    		}
    		else if(trnType==inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_RETURN){
    			amts=girp.getConsumedAmounts(trnDet[i][1], jobNo);
    		}
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
    	/*String date=inventorycontroller.function.DateProcessor
    	 .getCurrentDate(dbInterface1);*/
    	String date=inventorycontroller.util.DateUtil.getRawFormat(
			(java.util.Date)jXDatePicker1.getValue()
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
		String date=inventorycontroller.util.DateUtil.getRawFormat(
			(java.util.Date)jXDatePicker1.getValue()
		);
		inventorycontroller.function.MaterialProcessor mp=
    	 new inventorycontroller.function.MaterialProcessor(dbInterface1);
    	
		int type=0;
		if(trnType==inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_REQUISITION){
			type=-1; // reduction from inventory
		}
		else if(trnType==inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_RETURN){
			type=1; // addition to inventory
		}
		
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
    
    /** called from actionLiisteners of jButton2: reset. */
    private void resetPerform(){
    	resetPerforming=true;
    		
    	jTextField1.setText("");
    	jTextField4.setText("");
    	jTextPane1.setText("");
    	jXDatePicker1.setValue(
    		inventorycontroller.util.DateUtil.getDateValue(
    			inventorycontroller.function.DateProcessor.getCurrentDate(dbInterface1)
    		)
    	);
    	jRadioButton1.doClick(4);
    	
    	jXDatePicker1.grabFocus();
    	jTabbedPane1.setSelectedIndex(0);
    	
    	resetPerforming=false;
    }
    
    /** called from resetPerform. */
    private void resetJobList(){
    	if(trnType==inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_REQUISITION){
	    	jobList=new inventorycontroller.function.JobProcessor(dbInterface1)
	    	 .getJobsForIssue();
    	}
    	else if(trnType==inventorycontroller.function.GoodsIssueReturnProcessor.GOODS_RETURN){
	    	jobList=new inventorycontroller.function.JobProcessor(dbInterface1)
	    	 .getJobsForReturn();
    	}
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            jobList,
            new String [] {
                "Customer Name", "Job No.", "BOM No.", "Job Description", "Quantity"
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
    
    /** called from resetPerform. */
    private void resetMaterialStatus(){
    	jTable1.setModel(new javax.swing.table.DefaultTableModel(
            null,
            new String [] {
                "Material", 
                "Stock in hand", 
                "Order pending", 
                "Delivery pending", 
                "Allocated Qty."
            }
        ));
    }
    
    /** called from resetPerform. */
    private void resetTransactionDesc(){
    	jTable3.setModel(new javax.swing.table.DefaultTableModel(
            null,
            trnTableHeader
        ));
		
    }
    
    /** called from actionLiisteners of jButton1: exit. */
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    //private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    //private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel jTextField1;
    private javax.swing.JLabel jTextField2;
    private javax.swing.JLabel jTextField4;
    private javax.swing.JTextPane jTextPane1;
    private inventorycontroller.display.RDateSpinner jXDatePicker1;
    
    private inventorycontroller.function.DbInterface dbInterface1;
    private boolean resetPerforming;
    private int trnType;
    private String[] rqnTableHeader;
    private String[] retTableHeader;
    private String[] trnTableHeader;
    private String[][] jobList;
    private String[][] matStatus;
    private String[][] trnDet;
    // End of variables declaration
    
}
