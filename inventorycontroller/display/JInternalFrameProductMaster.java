/*
 * JInternalFrameProductMaster.java
 *
 * Created on July 16, 2007, 9:45 AM
 */

package inventorycontroller.display;

/**
 *
 * @author  brinto
 */
public class JInternalFrameProductMaster extends javax.swing.JInternalFrame {
    
    /** Creates new form JInternalFrameProductMaster */
    public JInternalFrameProductMaster(inventorycontroller.function.DbInterface dbi) {
        initComponents(dbi);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents(inventorycontroller.function.DbInterface dbi) {
    	dbInterface1=dbi;
    	inputDet=null;
	    outputDet=null;
	    prdList=null;
	    matList=null;
    	
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jXDatePicker1 = new inventorycontroller.display.RDateSpinner(inventorycontroller.display.RDateSpinner.DD_MM_YYYY);
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel7 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setTitle("Product Master");
        
        resetPerform();
        
        jLabel1.setBackground(new java.awt.Color(127, 157, 185));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");
        jLabel1.setOpaque(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        jLabel2.setText("Product Name");

        jLabel3.setText("Product ID");

        jLabel4.setText("Date of Include");

        jLabel5.setText("Remark:");

        jScrollPane1.setViewportView(jEditorPane1);

        jLabel6.setText("Required Materials:");

        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(jTable1);

        jButton4.setToolTipText("Add Material");
        jButton4.setIcon(new javax.swing.ImageIcon(
        	getClass().getResource("/inventorycontroller/display/resources/Back16.gif")));
        jButton4.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		materialAdded();
        	}
        });

        jButton5.setToolTipText("Remove Material");
		jButton5.setIcon(new javax.swing.ImageIcon(
			getClass().getResource("/inventorycontroller/display/resources/Forward16.gif")));
		jButton5.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		materialRemoved();
        	}
        });
        
        jScrollPane3.setViewportView(jList1);

        jLabel7.setText("Material List:");

        jButton6.setToolTipText("New Material");
		jButton6.setIcon(new javax.swing.ImageIcon(
			getClass().getResource("/inventorycontroller/display/resources/New16.gif")));
		jButton6.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		materialInserted();
        	}
        });

        jButton1.setText("Insert / Update Product Details");
		jButton1.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		insertPerform();
        	}
        });

        jLabel8.setText("Existing Product List:");

        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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
            		showDetail(jTable2.getModel().getValueAt(selectedRow, 0).toString());
            	}
        	}        	
		});
		jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		jTable2.setAutoCreateRowSorter(false);
		jTable2.setAutoResizeMode(jTable2.AUTO_RESIZE_OFF);
        jScrollPane4.setViewportView(jTable2);

		jTable3.setAutoResizeMode(jTable3.AUTO_RESIZE_OFF);
        jScrollPane5.setViewportView(jTable3);

        jLabel9.setText("Required Maretials:");

        jButton7.setText("Change Preset Materials for selected Product");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                            .addComponent(jTextField2)
                                            .addComponent(jXDatePicker1)))
                                    .addComponent(jScrollPane1))
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jButton6, 0, 0, Short.MAX_VALUE)
                                                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, Short.MAX_VALUE)
                                                    .addComponent(jButton5, 0, 0, Short.MAX_VALUE)))
                                            .addComponent(jLabel6))
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)))
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel8))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jXDatePicker1, 20, 20, 20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5)
                                .addGap(22, 22, 22)
                                .addComponent(jButton6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addGap(23, 23, 23)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, 0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
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
        setVisible(true);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        getRootPane().setDefaultButton(jButton1);
        
    }
    
    private void materialAdded(){
    	if(jList1.getSelectedIndex()==-1){
    		return;
    	}
    	java.lang.String mat=jList1.getSelectedValue().toString();
    	int i=0;
    	for (; i<jTable1.getRowCount(); i++){
    		if(jTable1.getValueAt(i, 0).toString().compareTo(mat)==0){
    			break;
    		}
    	}
    	if(i<jTable1.getRowCount()){
			jTable1.changeSelection(i, 0, false, false);
			jTable1.changeSelection(i, 0, false, true);
			return;
    	}
    	javax.swing.table.DefaultTableModel model=
    	 (javax.swing.table.DefaultTableModel)jTable1.getModel();
    	model.addRow(new java.lang.Object[]
    	 {mat, new java.lang.Double(1.0)});
		jTable1.changeSelection(i, 0, false, false);
		jTable1.changeSelection(i, 0, false, true);
    }
    
    private void materialRemoved(){
    	int i=jTable1.getSelectedRow();
    	if(i==-1){
    		return;
    	}
    	javax.swing.table.DefaultTableModel model=
    	 (javax.swing.table.DefaultTableModel)jTable1.getModel();
    	model.removeRow(i);
    }
    
    private void materialInserted(){
    	int yesno=javax.swing.JOptionPane.showInternalConfirmDialog(this,
			"<html><center>\"Inserting Material from here will result ZERO OPENING STOCK<br>"+
			 "& \"PIECE\" as unit of the material.<br>Are you sure you want to continue?\"</center></html>",
			"InventoryController says...",
			javax.swing.JOptionPane.YES_NO_OPTION, 
			javax.swing.JOptionPane.WARNING_MESSAGE);
		
		if(yesno!=javax.swing.JOptionPane.YES_OPTION){
			return;
		}
		
    	Object input=javax.swing.JOptionPane.showInternalInputDialog(this,
			"<html><center>\"Enter Material Name:\"</center></html>",
			"InventoryController says...",
			javax.swing.JOptionPane.QUESTION_MESSAGE);
		if(input==null){
			return;
		}
		String matName=input.toString();
		
		if(!insertMaterial(matName)){
			javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"ERROR! Cannot insert the material\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			return;
		}
		resetMatList();
    	
    	javax.swing.table.DefaultTableModel model=
    	 (javax.swing.table.DefaultTableModel)jTable1.getModel();
    	
    	model.addRow(new java.lang.Object[]
    	 {matName, new java.lang.Double(1.0)});
    	
		jTable1.changeSelection(model.getRowCount()-1, 0, false, false);
		jTable1.changeSelection(model.getRowCount()-1, 0, false, true);
    }
    
    /** called from materialInserted() */
    private boolean insertMaterial(String matName){
    	if(matName.length()==0){
    		return false;
    	}
    	String date=inventorycontroller.function.DateProcessor
    	 .getCurrentDate(dbInterface1);
    	String matno=new inventorycontroller.function.MaterialProcessor(dbInterface1)
    	 .getNewMatNo();
    	
    	java.lang.String val=new java.lang.String("");
    	val=val+"'"+matno+"', "; // matNo
    	val=val+"'"+matName+"', "; // matName
    	val=val+"'"+date+"', "; //recDate
    	val=val+"'0', "; // matStockGeneral
    	val=val+"'0', "; // matStockExcisable
    	val=val+"'0', "; // matVATGeneral
    	val=val+"'0', "; // matVATExcisable
    	val=val+"'0', "; // matED
    	val=val+"'0', "; // matAmtGeneral
    	val=val+"'0', "; // matAmtExcisable
    	val=val+"'unit', "; // matUnit
    	val=val+"'_'"; // matRemark
    	try{
    		dbInterface1.cmdInsert("matMaster", val);
    		return true;
    	}
    	catch(java.sql.SQLException ex){
    		ex.printStackTrace();
    		return false;
    	}
    }
    
    /** to display reqd. materials for selected product. */
    private void showDetail(java.lang.String prdNo){
    	prdList=new inventorycontroller.function.ProductProcessor(dbInterface1)
    	 .getDescription(prdNo);
    	jTable3.setModel(new javax.swing.table.DefaultTableModel(
            prdList,
            new String [] {
                "Material", "Quantity"
            }
        ) {
            public Class getColumnClass(int columnIndex) {
                return java.lang.String.class;
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        jTable3.getColumnModel().getColumn(0).setPreferredWidth(220);
    //* ENDING: showDetail().
    }

    private void insertPerform(){
    	if(!verify()){
    		return;
    	}
    	java.lang.String val=new java.lang.String("");
    	val="'"+jTextField2.getText()+"', "; // prdId
    	val=val+"'"+jTextField1.getText()+"', "; // prdName
    	val=val+"'"+
    		inventorycontroller.util.DateUtil.getRawFormat(
    			(java.util.Date)jXDatePicker1.getValue()
    		)
    		+"', "; // insDate
    	val=val+"'"+jEditorPane1.getText()+" '"; // prdRemark
    	try{
    		dbInterface1.cmdInsert("prdMaster", val);
    		insertDesc();
    	}
    	catch(java.sql.SQLException ex){
    		System.out.println (ex);
    	}
    	resetPerform();
    }
    
    private boolean verify(){
    	//* product name cannot be empty
    	if(jTextField1.getText().length()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Product Name field cannot be empty\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField1.grabFocus();
			return false;
    	}
    	
    	//* product no cannot be empty
    	if(jTextField2.getText().length()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Product No. field cannot be empty\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField2.grabFocus();
			return false;
    	}
    	//* product no cannot be duplicate.
    	String[][] tmp=null;
    	try{
    		tmp=dbInterface1.cmdSelect(
    			"matMaster", "matMaster.matId", "matMaster.matId='"+jTextField2.getText()+"'"
    		);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	if(tmp.length>0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Material No. field cannot be duplicate\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField2.grabFocus();
			jTextField2.setSelectionStart(0);
			jTextField2.setSelectionEnd(jTextField2.getText().length());
			return false;
    	}
    	
    	//* mateial qty cannot be -ve.
    	javax.swing.table.TableModel tm=jTable1.getModel();
    	double qty=0;
    	for (int i = 0; i<tm.getRowCount(); i++){
    		qty=Double.parseDouble(tm.getValueAt(i, 1).toString());
    		if(qty<=0){
	    		javax.swing.JOptionPane.showInternalMessageDialog(this,
					"<html><center>\"Material Quantity should be NON-ZERO POSITIVE number\"</center></html>",
					"InventoryController says...",
					javax.swing.JOptionPane.ERROR_MESSAGE);
				jTable1.grabFocus();
				jTable1.changeSelection(i, 1, false, false);
				jTable1.changeSelection(i, 1, false, true);
				return false;
    		}
    	}
    	
    	//* warn if prd desc is not included.
    	if(jTable1.getModel().getRowCount()==0){
    		int i=javax.swing.JOptionPane.showInternalConfirmDialog(this,
				"<html><center>\"Are you sure you don't want to insert required materials?\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.YES_NO_OPTION, 
				javax.swing.JOptionPane.WARNING_MESSAGE);
			if(i!=javax.swing.JOptionPane.YES_OPTION){
				jList1.setSelectedIndex(0);
				jList1.grabFocus();
				return false;
			}
    	}
    	return true;
    }
    
    private void insertDesc()throws java.sql.SQLException {
    	java.util.Vector<Integer> selected=new java.util.Vector<Integer>();
    	for (int i = 0; i<jTable1.getRowCount(); i++){
    		if((Double)jTable1.getModel().getValueAt(i, 1)>(double)0){
    			selected.add(i);
    		}
    	}
    	inventorycontroller.function.MaterialProcessor mp=
    	 new inventorycontroller.function.MaterialProcessor(dbInterface1);
    	String prdNo=jTextField2.getText();
    	java.lang.String[] val=new java.lang.String[selected.size()];
    	int i=0;
    	for (int j: selected){
    		val[i++]=new java.lang.String(
    			"'"+prdNo+
    			"', '"+mp.getMatId(jTable1.getValueAt(j, 0).toString())+
    			"', '"+jTable1.getValueAt(j, 1).toString()+"'");
    	}
    	dbInterface1.cmdInsert("prdDesc", val);
    }
    
    private void resetPerform(){
    	jTextField1.setText("");
    	jTextField2.setText("");
    	jXDatePicker1.setValue(inventorycontroller.util.DateUtil.getDateValue(
    			inventorycontroller.function.DateProcessor.getCurrentDate(dbInterface1)
    		)
    	);
    	jEditorPane1.setText("");
    	resetMatList();
    	resetDescTable();
    	resetProductTable();
    	resetProductDesc();
    	
    	jTextField1.grabFocus();
    }
    
    /** to display  material list. */
    private void resetMatList(){
    	matList=new inventorycontroller.function.MaterialProcessor(this.dbInterface1)
    	 .getMatNames("1=1");
    	
    	jList1.setModel(new javax.swing.AbstractListModel() {
            java.lang.String[] strings = matList;
            public int getSize() { return strings.length; }
            public java.lang.Object getElementAt(int i) { return strings[i]; }
        });
        
    //* ENDING: resetMatList().
    }
    
    /** to reset reqd. material input table. */
    private void resetDescTable(){
    	jTable1.setModel(new javax.swing.table.DefaultTableModel(
            null,
            new String [] {
                "Material", "Quantity"
            }
        ) {
        	Class[] type={java.lang.String.class, java.lang.Double.class};
        	
        	boolean[] canEdit={false, true};
        	
            public Class getColumnClass(int columnIndex) {
                return type[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    //* ENDING: resetDescTable().
    }
    
    /** to show all the existing products. */
    private void resetProductTable(){
    	prdList=new inventorycontroller.function.ProductProcessor(this.dbInterface1)
    	 .getProducts();
		jTable2.setModel(new javax.swing.table.DefaultTableModel(
            prdList,
            new String [] {
                "Product ID", "Product Name", "Remark"
            }
        ) {
            public Class getColumnClass(int columnIndex) {
                return java.lang.String.class;
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(120);
        jTable2.getColumnModel().getColumn(1).setPreferredWidth(160);
        jTable2.getColumnModel().getColumn(2).setPreferredWidth(120);
        
    //* ENDING: resetOrderTables().
    }
    
    /** to reset product details table. */
    private void resetProductDesc(){
    	jTable3.setModel(new javax.swing.table.DefaultTableModel(
            null,
            new String [] {
                "Material", "Quantity"
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
    
    
    // Variables declaration -
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
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
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private inventorycontroller.display.RDateSpinner jXDatePicker1;
    
    private inventorycontroller.function.DbInterface dbInterface1;
    private java.lang.String[][] inputDet;
    private java.lang.String[][] outputDet;
    private java.lang.String[][] prdList;
    private java.lang.String[] matList;
    // End of variables declaration
    
}
