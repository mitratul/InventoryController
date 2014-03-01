/*
 * JDialogReportLedger.java
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
public class JDialogReportLedger extends javax.swing.JDialog {
    
    /** Creates new form JDialogReportLedger */
    public JDialogReportLedger(inventorycontroller.display.JFrameMDI parent, boolean modal, 
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
    	
    	this.matList=null;
    	isExcisable=false;
    	sDate="";
    	eDate="";
    	selectedMatList=new java.util.Vector<String>();
    	
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabelStatus = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jSpinner1 = new inventorycontroller.display.RDateSpinner(jSpinner1.DD_MM_YYYY);
        jLabel5 = new javax.swing.JLabel();
        jSpinner2 = new inventorycontroller.display.RDateSpinner(jSpinner2.DD_MM_YYYY);
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        
        jLabelStatus.setText("");
        jLabelStatus.setVisible(false);
        jLabelStatus.setForeground(new java.awt.Color(251, 82, 0));
        jLabelStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelStatus.setVisible(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Itemwise Ledger");
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        jLabel1.setText("Ledger Type: ");

        jRadioButton1.setText("General ");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		isExcisable=false;
        	}
        });
        

        jRadioButton2.setText("Excisable");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		isExcisable=true;
        	}
        });
        
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);

        jLabel2.setText("Include Materials: ");

        jRadioButton3.setText("All");

        jRadioButton4.setText("Selected");
        
        buttonGroup2.add(jRadioButton3);
        buttonGroup2.add(jRadioButton4);

        jLabel3.setText("Select Materials:");

        jTable1.setAutoCreateRowSorter(false);
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(jTable1);

        jLabel4.setText("Date From ");

        jLabel5.setText("To");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    /*.addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton4))*/
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2))
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelStatus)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                /*.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)*/
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    
    public void generatePerform(){
    	sDate=inventorycontroller.util.DateUtil
    	 .getRawFormat((java.util.Date)jSpinner1.getValue());
    	eDate=inventorycontroller.util.DateUtil
    	 .getRawFormat((java.util.Date)jSpinner2.getValue());
    	
    	if(!check()){
    		return;
    	}
    	//* get the selected grns.
    	javax.swing.table.TableModel tm=jTable1.getModel();
    	for (int i = 0; i<tm.getRowCount(); i++){
    		if((Boolean)tm.getValueAt(i, 0)){
    			selectedMatList.add(new String(tm.getValueAt(i, 2).toString()));
    		}
    	}
    	
    	setVisible(false);
    	createReport();
    	dispose();
    }
    
    private boolean check(){
    	if(sDate.compareTo(eDate)>0){
    		jLabelStatus.setText("End Date less than Start Date");
    		jLabelStatus.setVisible(true);
    		jSpinner1.grabFocus();
    		pack();
    		this.setLocation();
    		return false;
    	}
    	
    	String tmp[][]=null;
    	try{
    		tmp=dbInterface1.cmdSelect(
    			"dateTSMap", 
    			"dateTSMap.date", 
    			"1=1 order by dateTSMap.date"
    		);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	
    	if(tmp[tmp.length-1][0].compareTo(sDate)<0){
    		jLabelStatus.setText("Start Date not arrived");
    		jLabelStatus.setVisible(true);
    		jSpinner1.grabFocus();
    		pack();
    		this.setLocation();
    		return false;
    	}
    	
    	if(tmp[0][0].compareTo(sDate)>0){
    		jLabelStatus.setText("Start Date out of range");
    		jLabelStatus.setVisible(true);
    		jSpinner1.grabFocus();
    		pack();
    		this.setLocation();
    		return false;
    	}
    	
    	if(tmp[tmp.length-1][0].compareTo(eDate)<0){
    		jLabelStatus.setText("End Date not arrived");
    		jLabelStatus.setVisible(true);
    		jSpinner2.grabFocus();
    		pack();
    		this.setLocation();
    		return false;
    	}
    	
    	if(tmp[0][0].compareTo(eDate)>0){
    		jLabelStatus.setText("End Date out of range");
    		jLabelStatus.setVisible(true);
    		jSpinner2.grabFocus();
    		pack();
    		this.setLocation();
    		return false;
    	}
    	return true;
    }
    
    private void createReport(){
    	
    	String[] matNos=new String[selectedMatList.size()];
    	for (int i = 0; i<matNos.length; i++){
    		matNos[i]=selectedMatList.elementAt(i);
    	}
    	inventorycontroller.function.LedgerReportProcessor rp=
    	 new inventorycontroller.function.LedgerReportProcessor(
    	 	dbInterface1, sDate, eDate, isExcisable, matNos
    	 );
    	rp.generate();
    	rp.initPrint(this.parent.getDesktopPane());
    	
    }
    
    public void resetPerform(){
    	jSpinner1.setValue(new java.util.Date());
    	jSpinner2.setValue(new java.util.Date());
    	jRadioButton1.doClick(4);
    	//jRadioButton3.doClick(4);
    	resetMatList();
		jLabelStatus.setVisible(false);
		pack();
		this.setLocation();
    }
    
    private void resetMatList(){
    	matList=new inventorycontroller.function.MaterialProcessor(this.dbInterface1)
    	 .getMatNameIds("1=1");
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            matList,
            new String [] {
                "Select", "Material Name", "Material ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(48);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(220);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(160);
    }
    
    public void exitPerform(){
    	dispose();
    }
    
    public void setLocation(){
        int h=java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        int w=java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        super.setLocation((w-this.getWidth())/2, (h-this.getHeight()-30)/2);
    }
    
    
    
    // Variables declaration -
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private inventorycontroller.display.RDateSpinner jSpinner1;
    private inventorycontroller.display.RDateSpinner jSpinner2;
    private javax.swing.JTable jTable1;
    private inventorycontroller.display.JFrameMDI parent;
    private inventorycontroller.function.DbInterface dbInterface1;
    
    private Object[][] matList;
    private boolean isExcisable;
    private String sDate;
    private String eDate;
    private java.util.Vector<String> selectedMatList;
    // End of variables declaration
    
}
