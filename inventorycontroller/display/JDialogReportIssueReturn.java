/*
 * JDialogReportIssueReturn.java
 *
 * Created on September 4, 2007, 11:36 HRS
 * Last Modified on September 4, 2007, 14:36 HRS
 *
 */

package inventorycontroller.display;

/**
 *
 * @author  brinto
 */
public class JDialogReportIssueReturn extends javax.swing.JDialog {
    
    /** Creates new form JDialogReportIssueReturn */
    public JDialogReportIssueReturn(inventorycontroller.display.JFrameMDI parent, boolean modal, 
     inventorycontroller.function.DbInterface dbi) {
        super(parent, modal);
        initComponents(parent, dbi);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents(inventorycontroller.display.JFrameMDI parent, 
     inventorycontroller.function.DbInterface dbi) {
    	this.parent=parent;
    	dbInterface1=dbi;
    	
    	trnList=null;
    	selectedTrnList=new java.util.Vector<String>();
    	trnType="RET";
    	
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        //jLabel2 = new javax.swing.JLabel();
        //jSpinner1 = new inventorycontroller.display.RDateSpinner(jSpinner1.DD_MM_YYYY);
        //jLabel3 = new javax.swing.JLabel();
        //jSpinner2 = new inventorycontroller.display.RDateSpinner(jSpinner2.DD_MM_YYYY);
        buttonGroup1 = new javax.swing.ButtonGroup();
        //buttonGroup2 = new javax.swing.ButtonGroup();
        //jRadioButton1 = new javax.swing.JRadioButton();
        //jRadioButton2 = new javax.swing.JRadioButton();
        //jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        
        jTable1.setAutoCreateRowSorter(false);
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Select Requisition / Return :");

        //jLabel2.setText("Date From");

        //jLabel3.setText("To");

        //jRadioButton1.setText("All");

        //jRadioButton2.setText("Selected");
        
        //buttonGroup2.add(jRadioButton1);
        //buttonGroup2.add(jRadioButton2);

        //jTextField1.setText("jTextField1");

        jLabel4.setText("Generate Report for ");

        jRadioButton3.setText("Requisition");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		trnTypeChanged(evt);
        	}
        });

        jRadioButton4.setText("Return to Store");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		trnTypeChanged(evt);
        	}
        });
        
        buttonGroup1.add(jRadioButton3);
        buttonGroup1.add(jRadioButton4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                    /*.addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE))*/
                    /*.addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, Short.MAX_VALUE))*/
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton4)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
            	.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                //.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                /*.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)*/
                /*.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))*/
                .addContainerGap())
        );

        //jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        jButton1.setText("Generate Report");
        jButton1.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		generatePerform();
        	}
        });

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
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, 140, 140, 140)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, 80, 80, 80)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, 80, 80, 80)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, 22, 22, 22)
                    .addComponent(jButton2, 22, 22, 22)
                    .addComponent(jButton3, 22, 22, 22))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                //.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                /*.addContainerGap()*/)
        );
        setResizable(false);
        getRootPane().setDefaultButton(jButton1);
        pack();
        setLocation();
        setTitle("Requisition / Return Slip");
        
        resetPerform();
    //* ENDING: initComponents.
    }
    
    private void trnTypeChanged(java.awt.event.ActionEvent evt){
    	if(evt.getSource()==this.jRadioButton3 && this.trnType.compareTo("RQN")==0){
    		return;
    	}
    	else if(evt.getSource()==this.jRadioButton4 && this.trnType.compareTo("RET")==0){
    		return;
    	}
    	else if(evt.getSource()==this.jRadioButton3 && this.trnType.compareTo("RET")==0){
    		this.trnType="RQN";
    	}
    	else if(evt.getSource()==this.jRadioButton4 && this.trnType.compareTo("RQN")==0){
    		this.trnType="RET";
    	}
    	fillTrnTable();
    }
    
    public void generatePerform(){
    	//* get the selected trns.
    	javax.swing.table.TableModel tm=jTable1.getModel();
    	for (int i = 0; i<tm.getRowCount(); i++){
    		if((Boolean)tm.getValueAt(i, 0)){
    			selectedTrnList.add(new String(tm.getValueAt(i, 1).toString()));
    		}
    	}
    	
    	//* generate & show Report.
    	this.setVisible(false);
    	createReport();
    	dispose();
    }
    
    private void createReport(){
    	String[] trnNos=new String[selectedTrnList.size()];
    	for (int i = 0; i<trnNos.length; i++){
    		trnNos[i]=selectedTrnList.elementAt(i);
    	}
    	inventorycontroller.function.GoodsIssueReturnReportProcessor grp=
    	 new inventorycontroller.function.GoodsIssueReturnReportProcessor(
    	 	dbInterface1, trnNos, this.trnType
    	 );
    	grp.generate();
    	grp.initPrint(this.parent.getDesktopPane());
    }
    
    public void resetPerform(){
    	//jSpinner1.setValue(new java.util.Date());
    	//jSpinner2.setValue(new java.util.Date());
    	//jRadioButton1.doClick(4);
    	jRadioButton3.doClick(4);
    }
    
    public void fillTrnTable(){
    	trnList=new inventorycontroller.function.GoodsIssueReturnProcessor(dbInterface1)
    	 .prepareRqnForReportDialog(this.trnType);
    	
    	jTable1.setModel(new javax.swing.table.DefaultTableModel(
            trnList,
            new String [] {
                "Select", "Rqn./RTS No.", "Rqn./RTS Date", "Job No."
            }
        ) {
            public Class getColumnClass(int columnIndex) {
                return columnIndex==0? java.lang.Boolean.class: java.lang.String.class;
            } 

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex==0;
            }
        });
    	
    }
    
    public void exitPerform(){
    	dispose();
    }
    
    public void setLocation(){
        int h=java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        int w=java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        super.setLocation((w-this.getWidth())/2, (h-this.getHeight()-30)/2);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new JDialogReportIssueReturn(new javax.swing.JFrame(), true).setVisible(true);
                System.exit(0);
            }
        });
    }
    
    // Variables declaration -
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    //private javax.swing.JLabel jLabel2;
    //private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    //private javax.swing.JRadioButton jRadioButton1;
    //private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    //private inventorycontroller.display.RDateSpinner jSpinner1;
    //private inventorycontroller.display.RDateSpinner jSpinner2;
    private javax.swing.JTable jTable1;
    //private javax.swing.JTextField jTextField1;
    private inventorycontroller.display.JFrameMDI parent;
    private inventorycontroller.function.DbInterface dbInterface1;
    
    private String trnType;
    private Object[][] trnList;
    private java.util.Vector<String> selectedTrnList;
    // End of variables declaration
    
}
