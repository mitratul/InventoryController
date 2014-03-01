/*
 * JDialogMaterialListForBOM.java
 *
 * Created on August 24, 2007, 13:28 HRS
 * Last Modified on August 27, 2007, 13:28 HRS
 *
 */

package inventorycontroller.display;

/**
 *
 * @author  brinto
 */
public class JDialogMaterialListForBOM extends javax.swing.JDialog {
    
    /** Creates new form JDialogMaterialListForBOM */
    public JDialogMaterialListForBOM(inventorycontroller.display.JInternalFrameBillOfMaterial parent, 
     inventorycontroller.function.DbInterface dbInterface, String prdName, String[] selectedMatIDs) {
        super((java.awt.Frame)null, true);
        initComponents(parent, dbInterface, prdName, selectedMatIDs);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents(inventorycontroller.display.JInternalFrameBillOfMaterial parent, 
     inventorycontroller.function.DbInterface dbInterface, String prdName, String[] selectedMatIDs) {
    	matList=null;
    	this.prdName=prdName;
    	this.parent=parent;
    	dbInterface1=dbInterface;
    	selected=selectedMatIDs;
    	
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        jLabel1.setText("Select Materials:");

		jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
		jTable1.setAutoCreateRowSorter(false);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setText("Select");
        jButton1.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
				selectPerform();
        	}
        });

        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		resetPerform();
        	}
        });

        jButton3.setText("Cancel");
        jButton3.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		exitPerform();
        	}
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, 80, 80, 80)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, 80, 80, 80)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, 80, 80, 80)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton3, 22, 22, 22)
                .addComponent(jButton2, 22, 22, 22)
                .addComponent(jButton1, 22, 22, 22))
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
                .addContainerGap())
        );
        
        
        getRootPane().setDefaultButton(jButton1);
        resetPerform();
        
        pack();
    	setLocation();
        int h=java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        int w=java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        setBounds((w-400)/2, (h-330)/2, 400, 300);
        setLocation((w-400)/2, (h-330)/2);
        setTitle("Select Material");
        setVisible(true);
    //* ENDING: initComponents
    }
    
    private void selectPerform(){
    	java.util.Vector<String> tmp=new java.util.Vector<String>();
    	for (int i = 0; i<jTable1.getModel().getRowCount(); i++){
    		if((Boolean)jTable1.getModel().getValueAt(i, 0)){
    			tmp.add(jTable1.getModel().getValueAt(i, 1).toString());
    		}
    	}
    	String[][] components=new String[tmp.size()][2];
    	String[][] oldDesc=null;
    	
    	if(this.prdName.length()>0){
	    	inventorycontroller.function.ProductProcessor pp=
	    	 new inventorycontroller.function.ProductProcessor(this.dbInterface1);
	    	oldDesc=pp.getDescription(pp.getProductId(this.prdName));
    	}
		else {
			oldDesc=new String[0][0];
		}
		
    	java.util.Vector<String> tmp1=new java.util.Vector<String>();
    	for (int i = 0; i<oldDesc.length; i++){
    		tmp1.add(oldDesc[i][0]);
    	}
    	for (int i = 0; i<components.length; i++){
    		components[i][0]=new String(tmp.elementAt(i));
    		if(tmp1.contains(components[i][0])){
    			components[i][1]=new String(oldDesc[tmp1.indexOf(components[i][0])][1]);
    		}
    		else {
    			components[i][1]=new String("0.0");
    		}
    		
    	}
    	parent.reflectSelection(components);
    	exitPerform();
    }
    
    private void resetPerform(){
    	matList=new inventorycontroller.function.MaterialProcessor(dbInterface1)
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
        select();
    }
    
    private void select(){
    	int count1=selected.length;
    	javax.swing.table.TableModel tm=jTable1.getModel();
    	int count2=tm.getRowCount();
    	for (int i = 0; i<count1; i++){
    		for (int j = 0; j<count2; j++){
    			if(tm.getValueAt(j, 2).toString().compareTo(selected[i])==0){
    				tm.setValueAt(true, j, 0);
    				break;
    			}
    		}
    	}
    }
    
    private void exitPerform(){
    	try{
    		this.dispose();
    	}
    	catch(java.lang.Exception ex){
    		System.out.println (ex);
    	}
    	
    }
    
    public void setLocation(){
        int h=java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        int w=java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        super.setLocation((w-this.getWidth())/2, (h-this.getHeight()-30)/2);
    }
    
    
    //* Variables declaration -
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private inventorycontroller.display.JInternalFrameBillOfMaterial parent;
    
    private inventorycontroller.function.DbInterface dbInterface1;
    private String prdName;
    private Object[][] matList;
    private String[] selected;
    //* End of variables declaration
    
}
