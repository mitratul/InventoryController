/*
 * JInternalFrameVendorMaster.java
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
public class JInternalFrameVendorMaster extends javax.swing.JInternalFrame {
    
    /** Creates new form JInternalFrameVendorMaster */
    public JInternalFrameVendorMaster(inventorycontroller.function.DbInterface dbi) {
        initComponents(dbi);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents(inventorycontroller.function.DbInterface dbi) {
    	dbInterface1=dbi;
    	vndList=null;
    	
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        //jLabel6 = new javax.swing.JLabel(); //deleted from frame
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
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        //jTextField5 = new javax.swing.JTextField(); //deleted from frame
        //jTextField6 = new javax.swing.JTextField(); //deleted from frame
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        
        jLabel1.setBackground(new java.awt.Color(127, 157, 185));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");
        jLabel1.setOpaque(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        jLabel2.setText("Vendor No.");

        jLabel3.setText("VAT No.");

        jLabel4.setText("Company Name");

        jLabel5.setText("Address");

        //jLabel6.setText("City");

        //jLabel7.setText("State");

        jLabel8.setText("PIN Code");

        jLabel9.setText("Phone No.");

        jLabel10.setText("Fax No.");

        jLabel11.setText("QA Rating");

        jLabel12.setText("Reliability Rating");

        jLabel13.setText("Contact Name");

        jLabel14.setText("Contact Phone No.");

        jLabel15.setText("Remarks (if any):");
        
        resetPerform();

        jEditorPane1.setPreferredSize(new java.awt.Dimension(80, 20));
        jScrollPane1.setViewportView(jEditorPane1);

        jButton2.setText("Register Vendor");
        jButton2.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		insertPerform();
        	}
        });

        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
        jTable1.setAutoCreateRowSorter(false);
        jScrollPane2.setViewportView(jTable1);

        jButton4.setText("Create Purchase Order");
        jButton4.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		openPoForm();
        	}
        });

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
                                    //.addComponent(jLabel7)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13))
                                .addGap(8, 12, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    //.addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(jTextField12, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))))
                        .addGap(16, 22, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    //.addComponent(jLabel6)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel14))
                                .addGap(8, 12, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    //.addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(jTextField13, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    //.addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    //.addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                //.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                //.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    //.addComponent(jLabel7)
                    //.addComponent(jLabel8)
                    //.addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    //.addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
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
        setTitle("VendorMaster");
        setVisible(true);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        getRootPane().setDefaultButton(jButton2);
    //* ENDING: initComponents
    }
    
    private void openPoForm(){
    	this.getDesktopPane().getRootPane().getJMenuBar().getMenu(2).getItem(0).doClick(4);
    	try{
    		this.setClosed(true);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    private void insertPerform(){
    	if(!verify()){
    		return;
    	}
    	java.lang.String val=new java.lang.String("");
    	val="'"+jTextField1.getText()+"', ";
    	val=val+"'"+jTextField3.getText()+"', ";
    	val=val+"'"+jTextField2.getText()+"' , ";
    	val=val+"'"+jTextField4.getText()+"', ";
    	val=val+"'"+/*jTextField5.getText()+*/" ', ";
    	val=val+"'"+jTextField7.getText()+"' , ";
    	val=val+"'"+/*jTextField6.getText()+*/" ', ";
    	val=val+"'"+jTextField8.getText()+" ', ";
    	val=val+"'"+jTextField9.getText()+" ', ";
    	val=val+"'"+jTextField12.getText()+" ', ";
    	val=val+"'"+jTextField13.getText()+" ', ";
    	val=val+"'"+jTextField10.getText()+" ', ";
    	val=val+"'"+jTextField11.getText()+" ', ";
    	val=val+"'"+jEditorPane1.getText()+" '";
    	
    	try{
    		dbInterface1.cmdInsert("vndMaster", val);
    	}
    	catch(java.sql.SQLException ex){
    		System.out.println (ex);
    	}
    	resetPerform();
    }
    
    private boolean verify(){
    	//* vendor no. cannot be empty
    	if(jTextField1.getText().length()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Vendor No. field cannot be empty\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField1.grabFocus();
			return false;
    	}
    	//* vendor no. cannot be duplicate.
    	String[][] tmp=null;
    	try{
    		tmp=dbInterface1.cmdSelect("vndMaster", "vndNo", "vndMaster.vndNo='"+this.jTextField1+"'");
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	if(tmp.length>0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Vendor No. field cannot be duplicate\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField1.grabFocus();
			jTextField1.setSelectionStart(0);
			jTextField1.setSelectionEnd(jTextField1.getText().length());
			return false;
    	}
    	
    	//* comp name cannot be empty
    	if(jTextField3.getText().length()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Company Name field cannot be empty\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField3.grabFocus();
			return false;
    	}
    	
    	//* Address cannot be empty
    	if(jTextField4.getText().length()==0){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Address field cannot be empty\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField4.grabFocus();
			return false;
    	}
    	
    	//* PIN should be numeric.
    	try{
    		Long.parseLong("0"+jTextField7.getText());
    	}
    	catch(java.lang.NumberFormatException ex){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"PIN Code field should be NUMERIC\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField7.grabFocus();
			jTextField7.setSelectionStart(0);
			jTextField7.setSelectionEnd(jTextField7.getText().length());
			return false;
    	}
    	
    	//* ph no should be numeric.
    	try{
    		Long.parseLong("0"+jTextField8.getText());
    	}
    	catch(java.lang.NumberFormatException ex){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Phone No. field should be NUMERIC\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField8.grabFocus();
			jTextField8.setSelectionStart(0);
			jTextField8.setSelectionEnd(jTextField8.getText().length());
			return false;
    	}
    	
    	//* fax no should be numeric.
    	try{
    		Long.parseLong("0"+jTextField9.getText());
    	}
    	catch(java.lang.NumberFormatException ex){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Fax No. field should be NUMERIC\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField9.grabFocus();
			jTextField9.setSelectionStart(0);
			jTextField9.setSelectionEnd(jTextField9.getText().length());
			return false;
    	}
    	
    	//* contact ph no should be numeric.
    	try{
    		Long.parseLong("0"+jTextField13.getText());
    	}
    	catch(java.lang.NumberFormatException ex){
    		javax.swing.JOptionPane.showInternalMessageDialog(this,
				"<html><center>\"Contact Phone No. field should be NUMERIC\"</center></html>",
				"InventoryController says...",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			jTextField13.grabFocus();
			jTextField13.setSelectionStart(0);
			jTextField13.setSelectionEnd(jTextField13.getText().length());
			return false;
    	}
    	return true;
    }
    
    private void resetPerform(){
    	resetVndTable();
    	jTextField1.setText("");
    	jTextField2.setText("");
    	jTextField3.setText("");
    	jTextField4.setText("");
    	//jTextField5.setText("");
    	//jTextField6.setText("");
    	jTextField7.setText("");
    	jTextField8.setText("");
    	jTextField9.setText("");
    	jTextField10.setText("");
    	jTextField11.setText("");
    	jTextField12.setText("");
    	jTextField13.setText("");
    	jEditorPane1.setText("");
    	
    	jTextField3.grabFocus();
    }
    
    private void resetVndTable(){
    	vndList=new inventorycontroller.function.VendorProcessor(dbInterface1).getVnds("1=1");
    	jTable1.setModel(new javax.swing.table.DefaultTableModel(
            vndList,
            new String [] {
                "Company Name", 
                "Vat No", 
                "Full Address", 
                "Phone No.", 
                "Fax No.", 
                "QA Rating", 
                "Reliability Rating", 
                "Contact Name", 
                "Contact No.", 
                "Remark", 
                "Vendor No."
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
    private javax.swing.JButton jButton4;
    private javax.swing.JEditorPane jEditorPane1;
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
    //private javax.swing.JLabel jLabel6;
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
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    //private javax.swing.JTextField jTextField5;
    //private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    
    private inventorycontroller.function.DbInterface dbInterface1;
    private java.lang.String[][] vndList;
    //* End of variables declaration
    
}
