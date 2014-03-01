/*
 * JInternalFrameMaterialMaster.java
 *
 * Created on June 02, 2007, 9:39 HRS
 * Last Modified on September 03, 2007, 16:00 HRS
 *
 */

package inventorycontroller.display;

/**
 *
 * @author  brinto
 */
public class JInternalFrameMaterialMaster extends javax.swing.JInternalFrame {
    
    /** Creates new form JInternalFrameVendorMaster */
    public JInternalFrameMaterialMaster(inventorycontroller.function.DbInterface dbi) {
        initComponents(dbi);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents(inventorycontroller.function.DbInterface dbi) {
    	dbInterface1=dbi;
    	matList=null;
    	
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        //jButton4 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        //jLabel7 = new javax.swing.JLabel(); //deleted from frame
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new inventorycontroller.display.RDateSpinner(inventorycontroller.display.RDateSpinner.DD_MM_YYYY);
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        //jTextField6 = new javax.swing.JTextField(); //deleted from frame
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        //jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        
        jLabel1.setBackground(new java.awt.Color(127, 157, 185));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");
        jLabel1.setOpaque(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        jLabel2.setText("Material No.");

        jLabel3.setText("Insertion Date");

        jLabel4.setText("Material Name");

        jLabel5.setText("General Quantity");

        jLabel6.setText("Material Unit");

        jLabel8.setText("Excisable Quantity");

        jLabel9.setText("VAT (general)");

        jLabel10.setText("VAT (excisable)");

        jLabel11.setText("General Amount");

        jLabel12.setText("Excisable Amount");
        
        jLabel13.setText("Opening Balance:");
        jLabel13.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel13.setForeground(new java.awt.Color(255, 102, 0));

        jLabel14.setText("Excise Duty Amount");

        jLabel15.setText("Remarks (if any):");
        
        resetPerform();

        jEditorPane1.setPreferredSize(new java.awt.Dimension(80, 20));
        jScrollPane1.setViewportView(jEditorPane1);

        jButton2.setText("Insert Material");
        jButton2.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		insertPerform();
        	}
        });

        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setAutoCreateRowSorter(false);
        jScrollPane2.setViewportView(jTable1);


        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13))
                                .addGap(8, 12, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    )))
                        .addGap(16, 22, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel14))
                                .addGap(8, 12, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(jTextField13, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    )
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		resetPerform();
        	}
        });
        jButton1.setPreferredSize(new java.awt.Dimension(80, 22));

        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		exitPerform();
        	}
        });
        jButton3.setPreferredSize(new java.awt.Dimension(80, 22));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, 120, 120, 120)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, 120, 120, 120)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, 22, 22, 22)
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        pack();
        setTitle("MaterialMaster");
        setVisible(true);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        getRootPane().setDefaultButton(jButton2);
    //* ENDING: initComponents
    }
    
    private void insertPerform(){
    	if(!verify()){
    		return;
    	}
    	java.lang.String val=new java.lang.String("");
    	val=val+"'"+jTextField1.getText()+"', "; // matNo
    	val=val+"'"+jTextField3.getText()+"', "; // matName
    	val=val+"'"+
    		inventorycontroller.util.DateUtil.getRawFormat(
    			(java.util.Date)jTextField2.getValue()
    		)
    		+"', "; //recDate
    	val=val+"'"+jTextField4.getText()+"', "; // matStockGeneral
    	val=val+"'"+jTextField7.getText()+"', "; // matStockExcisable
    	val=val+"'"+jTextField8.getText()+"', "; // matVATGeneral
    	val=val+"'"+jTextField9.getText()+"', "; // matVATExcisable
    	val=val+"'"+jTextField13.getText()+"', "; // matED
    	val=val+"'"+jTextField10.getText()+"', "; // matAmtGeneral
    	val=val+"'"+jTextField11.getText()+"', "; // matAmtExcisable
    	val=val+"'"+jTextField5.getText()+"', "; // matunit
    	val=val+"'"+jEditorPane1.getText()+" '"; // matRemark
    	try{
    		dbInterface1.cmdInsert("matMaster", val);
    	}
    	catch(java.sql.SQLException ex){
    		ex.printStackTrace();
    	}
    	resetPerform();
    }
    
    private boolean verify(){
    	//* mat no cannot be empty
    	if(jTextField1.getText().length()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Material Name field cannot be empty\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField1.grabFocus();
			return false;
    	}
    	//* mat no cannot be duplicate.
    	String[][] tmp=null;
    	try{
    		tmp=dbInterface1.cmdSelect(
    			"matMaster", "matMaster.matId", "matMaster.matId='"+jTextField1.getText()+"'"
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
			jTextField1.grabFocus();
			jTextField1.setSelectionStart(0);
			jTextField1.setSelectionEnd(jTextField1.getText().length());
			return false;
    	}
    	
    	//* mat name cannot be empty
    	if(jTextField3.getText().length()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Material Name field cannot be empty\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField3.grabFocus();
			return false;
    	}
    	
    	//* mat unit cannot be empty
    	if(jTextField5.getText().length()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Material Unit field cannot be empty\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField5.grabFocus();
			return false;
    	}
    	
    	//* gen qty cannot be empty
    	if(jTextField4.getText().length()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"General Quantity field cannot be empty\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField4.grabFocus();
			return false;
    	}
    	//* gen qty should be numeric.
    	double genQty=0;
    	try{
    		genQty=Double.parseDouble(jTextField4.getText());
    	}
    	catch(java.lang.NumberFormatException ex){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"General Quantity field should be NUMERIC\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField4.grabFocus();
			jTextField4.setSelectionStart(0);
			jTextField4.setSelectionEnd(jTextField4.getText().length());
			return false;
    	}
    	//* gen qty should be posivite.
    	if(genQty<0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"General Quantity field should not be NEGETIVE\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField4.grabFocus();
			jTextField4.setSelectionStart(0);
			jTextField4.setSelectionEnd(1);
			return false;
    	}
    	
    	//* ex qty cannot be empty
    	if(jTextField7.getText().length()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Excisable Quantity field cannot be empty\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField7.grabFocus();
			return false;
    	}
    	//* ex qty should be numeric.
    	double exQty=0;
    	try{
    		exQty=Double.parseDouble(jTextField7.getText());
    	}
    	catch(java.lang.NumberFormatException ex){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Excisable Quantity field should be NUMERIC\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField7.grabFocus();
			jTextField7.setSelectionStart(0);
			jTextField7.setSelectionEnd(jTextField7.getText().length());
			return false;
    	}
    	//* ex qty should be posivite.
    	if(exQty<0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Excisable Quantity field should not be NEGETIVE\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField7.grabFocus();
			jTextField7.setSelectionStart(0);
			jTextField7.setSelectionEnd(1);
			return false;
    	}
    	
    	//* gen vat cannot be empty
    	if(jTextField8.getText().length()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"VAT (general) field cannot be empty\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField8.grabFocus();
			return false;
    	}
    	//* gen vat should be numeric.
    	double genVat=0;
    	try{
    		genVat=Double.parseDouble(jTextField8.getText());
    	}
    	catch(java.lang.NumberFormatException ex){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"VAT (general) field should be NUMERIC\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField8.grabFocus();
			jTextField8.setSelectionStart(0);
			jTextField8.setSelectionEnd(jTextField8.getText().length());
			return false;
    	}
    	//* gen vat should be posivite.
    	if(genVat<0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"VAT (general) field should not be NEGETIVE\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField8.grabFocus();
			jTextField8.setSelectionStart(0);
			jTextField8.setSelectionEnd(1);
			return false;
    	}
    	
    	//* ex vat cannot be empty
    	if(jTextField9.getText().length()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"VAT (excisable) field cannot be empty\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField9.grabFocus();
			return false;
    	}
    	//* ex vat should be numeric.
    	double exVat=0;
    	try{
    		exVat=Double.parseDouble(jTextField9.getText());
    	}
    	catch(java.lang.NumberFormatException ex){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"VAT (excisable) field should be NUMERIC\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField9.grabFocus();
			jTextField9.setSelectionStart(0);
			jTextField9.setSelectionEnd(jTextField9.getText().length());
			return false;
    	}
    	//* ex vat should be posivite.
    	if(exVat<0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"VAT (excisable) field should not be NEGETIVE\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField9.grabFocus();
			jTextField9.setSelectionStart(0);
			jTextField9.setSelectionEnd(1);
			return false;
    	}
    	
    	//* gen amt cannot be empty
    	if(jTextField10.getText().length()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"General Amount field cannot be empty\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField10.grabFocus();
			return false;
    	}
    	//* gen amt should be numeric.
    	double genAmt=0;
    	try{
    		genAmt=Double.parseDouble(jTextField10.getText());
    	}
    	catch(java.lang.NumberFormatException ex){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"General Amount field should be NUMERIC\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField10.grabFocus();
			jTextField10.setSelectionStart(0);
			jTextField10.setSelectionEnd(jTextField10.getText().length());
			return false;
    	}
    	//* gen amt should be posivite.
    	if(genAmt<0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"General Amount field should not be NEGETIVE\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField10.grabFocus();
			jTextField10.setSelectionStart(0);
			jTextField10.setSelectionEnd(1);
			return false;
    	}
    	
    	//* ex amt cannot be empty
    	if(jTextField11.getText().length()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Excisable Amount field cannot be empty\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField11.grabFocus();
			return false;
    	}
    	//* ex amt should be numeric.
    	double exAmt=0;
    	try{
    		exAmt=Double.parseDouble(jTextField11.getText());
    	}
    	catch(java.lang.NumberFormatException ex){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Excisable Amount field should be NUMERIC\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField11.grabFocus();
			jTextField11.setSelectionStart(0);
			jTextField11.setSelectionEnd(jTextField11.getText().length());
			return false;
    	}
    	//* ex amt should be posivite.
    	if(exAmt<0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Excisable Amount field should not be NEGETIVE\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField11.grabFocus();
			jTextField11.setSelectionStart(0);
			jTextField11.setSelectionEnd(1);
			return false;
    	}
    	
    	//* ed cannot be empty
    	if(jTextField13.getText().length()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Excise Duty Amount field cannot be empty\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField13.grabFocus();
			return false;
    	}
    	//* ed should be numeric.
    	double ed=0;
    	try{
    		ed=Double.parseDouble(jTextField13.getText());
    	}
    	catch(java.lang.NumberFormatException ex){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Excise Duty Amount field should be NUMERIC\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField13.grabFocus();
			jTextField13.setSelectionStart(0);
			jTextField13.setSelectionEnd(jTextField13.getText().length());
			return false;
    	}
    	//* ed should be posivite.
    	if(ed<0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Excise Duty Amount field should not be NEGETIVE\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField13.grabFocus();
			jTextField13.setSelectionStart(0);
			jTextField13.setSelectionEnd(1);
			return false;
    	}
    	return true;
    }
    
    private void resetPerform(){
    	resetMaterialTable();
    	jTextField1.setText("");
    	jTextField2.setValue(inventorycontroller.util.DateUtil.getDateValue(
    			inventorycontroller.function.DateProcessor.getCurrentDate(dbInterface1)
    		)
    	);
    	jTextField3.setText("");
    	jTextField4.setText("0");
    	jTextField5.setText("");
    	jTextField7.setText("0");
    	jTextField8.setText("0");
    	jTextField9.setText("0");
    	jTextField10.setText("0");
    	jTextField11.setText("0");
    	jTextField13.setText("0");
    	jEditorPane1.setText("");
    	
    	jTextField1.grabFocus();
    }
    
    private void resetMaterialTable(){
    	matList=new inventorycontroller.function.MaterialProcessor(dbInterface1).getMaterials("1=1");
    	
    	jTable1.setModel(new javax.swing.table.DefaultTableModel(
            matList,
            new String [] {
                "Material Name", 
                "Material ID", 
                "Date", 
                "Stock (General)", 
                "Stock (Excisable)", 
                "Unit", 
                "Remark"
            }
        ) {
            public Class getColumnClass(int columnIndex) {
                return java.lang.String.class;
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(320);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(160);
        
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
    		getRootPane().setDefaultButton(jButton2);
    	}
    }
    
    
    
    // Variables declaration -
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    //private javax.swing.JButton jButton4;
    private javax.swing.JTextPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    //private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private inventorycontroller.display.RDateSpinner jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    //private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    
    private inventorycontroller.function.DbInterface dbInterface1;
    private java.lang.String[][] matList;
    //* End of variables declaration
    
}
