/*
 * JDialogReportPurchaseRequisition.java
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
public class JDialogReportPurchaseRequisition extends javax.swing.JDialog {
    
    /** Creates new form JDialogReportGRN */
    public JDialogReportPurchaseRequisition(inventorycontroller.display.JFrameMDI parent, boolean modal, 
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
    	
    	poList=null;
    	selectedPoList=new java.util.Vector<String>();
    	
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Purchase Requisition");
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        jTable1.setAutoCreateRowSorter(false);
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Select Purchase Requisition:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                /*.addContainerGap()*/)
        );
        setResizable(false);
        getRootPane().setDefaultButton(jButton1);
        pack();
        setLocation();
        
        resetPerform();
    //* ENDING: initComponents.
    }
    
    private void generatePerform(){
    	//* get the selected grns.
    	javax.swing.table.TableModel tm=jTable1.getModel();
    	for (int i = 0; i<tm.getRowCount(); i++){
    		if((Boolean)tm.getValueAt(i, 0)){
    			selectedPoList.add(new String(tm.getValueAt(i, 1).toString()));
    		}
    	}
    	
    	//* generate & show Report.
    	this.setVisible(false);
    	createReport();
    	dispose();
    }
    
    private void createReport(){
    	String[] poNos=new String[selectedPoList.size()];
    	for (int i = 0; i<poNos.length; i++){
    		poNos[i]=selectedPoList.elementAt(i);
    	}
    	inventorycontroller.function.PurchaseRequisitionReportProcessor rp=
    	 new inventorycontroller.function.PurchaseRequisitionReportProcessor(dbInterface1, poNos);
    	rp.generate();
    	rp.initPrint(this.parent.getDesktopPane());
    	
    }
    
    private void resetPerform(){
    	fillPoTable();
    }
    
    private void fillPoTable(){
    	poList=new inventorycontroller.function.PurchaseOrderProcessor(dbInterface1)
    	 .getPoForPoReport();
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            poList,
            new String [] {
                "Select", "PO No.", "PO Date", "PO Status", "PO Total", "Vendor Name"
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
    
    private void exitPerform(){
    	dispose();
    }
    
    private void setLocation(){
        int h=java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        int w=java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        super.setLocation((w-this.getWidth())/2, (h-this.getHeight()-30)/2);
    }
    
        
    // Variables declaration -
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private inventorycontroller.display.JFrameMDI parent;
    private inventorycontroller.function.DbInterface dbInterface1;
    
    private Object[][] poList;
    private java.util.Vector<String> selectedPoList;
    // End of variables declaration
    
}
