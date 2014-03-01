 /*
 * JFrameMDI.java
 *
 * Created on May 21, 2007, 12:03 HRS
 * Last Modified on July 20, 2007, 12:20 HRS
 *
 */
 
package inventorycontroller.display;

/**
 *
 * @author  brinto
 */
public class JFrameMDI extends javax.swing.JFrame {
    
    /** Creates new form JFrameMDI */
    public JFrameMDI() {
        initComponents();
        int h=java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        int w=java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        setBounds((w-760)/2, (h-590)/2, 760, 560);
        this.setMaximizedBounds(new java.awt.Rectangle(0, 0, w, h-30));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents() {
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem("Order Master");
        jMenuItem3 = new javax.swing.JMenuItem("Job Master");
        jMenuItem4 = new javax.swing.JMenuItem("Vendor Master");
        jMenuItem5 = new javax.swing.JMenuItem("Product Master");
        jMenuItem6 = new javax.swing.JMenuItem("Create Bill of Material");
        jMenuItem7 = new javax.swing.JMenuItem("View Bill of Materials");
        jMenuItem8 = new javax.swing.JMenuItem("Create Purchase Order");
        jMenuItem9 = new javax.swing.JMenuItem("View Purchase Orders");
        jMenuItem10 = new javax.swing.JMenuItem("Material Master");
        jMenuItem11 = new javax.swing.JMenuItem("Insert Goods Receipt Note");
        jMenuItem12 = new javax.swing.JMenuItem("Goods Requisition / Return");
        jMenuItem13 = new javax.swing.JMenuItem("Excess Requisition");
        jMenuItem23 = new javax.swing.JMenuItem("Cancel Goods Receipt Note");
        
        jMenuItem14 = new javax.swing.JMenuItem("Bill Of Material");
        jMenuItem15 = new javax.swing.JMenuItem("Purchase Requisition");
        jMenuItem16 = new javax.swing.JMenuItem("Purchase Order");
        jMenuItem17 = new javax.swing.JMenuItem("Goods Receipt Note");
        jMenuItem18 = new javax.swing.JMenuItem("Material Issue / Return Slip");
        jMenuItem19 = new javax.swing.JMenuItem("Stock Statement");
        jMenuItem20 = new javax.swing.JMenuItem("Job Statements");
        jMenuItem21 = new javax.swing.JMenuItem("Itemwise Ledger");
        jMenuItem22 = new javax.swing.JMenuItem("Jobwise Material Cost-Sheet");
        
        jInternalFrame2 = null;
        jInternalFrame3 = null;
        jInternalFrame4 = null;
        jInternalFrame5 = null;
        jInternalFrame6 = null;
        jInternalFrame7 = null;
        jInternalFrame8 = null;
        jInternalFrame9 = null;
        jInternalFrame10 = null;
        jInternalFrame11 = null;
        jInternalFrame12 = null;
        jInternalFrame13 = null;
        jInternalFrame14 = null;
        jInternalFrame15 = null;
        
        //* disabled items
        //jMenuItem7.setEnabled(false);
        
        dbInterface1=new inventorycontroller.function.DbInterface();
        try{
        	dbInterface1=new inventorycontroller.function.DbInterface("./db/db.mdb", "Microsoft Access Driver (*.mdb)");
        }
        catch(java.lang.ClassNotFoundException ex){
        	ex.printStackTrace();
        }
        catch(java.sql.SQLException ex){
        	ex.printStackTrace();
        }
        
        
		/*this.setIconImage(new javax.swing.ImageIcon(
			getClass().getResource("/inventorycontroller/display/resources/JavaCup16.png")).getImage()
		);*/
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("InventoryController");

        
        jMenuItem2.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		menuItemClicked((javax.swing.JMenuItem)evt.getSource());
        	}
        });
        jMenuItem3.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		menuItemClicked((javax.swing.JMenuItem)evt.getSource());
        	}
        });
        jMenuItem4.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		menuItemClicked((javax.swing.JMenuItem)evt.getSource());
        	}
        });
        jMenuItem5.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		menuItemClicked((javax.swing.JMenuItem)evt.getSource());
        	}
        });
        jMenuItem6.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		menuItemClicked((javax.swing.JMenuItem)evt.getSource());
        	}
        });
        jMenuItem7.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		menuItemClicked((javax.swing.JMenuItem)evt.getSource());
        	}
        });
        jMenuItem8.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		menuItemClicked((javax.swing.JMenuItem)evt.getSource());
        	}
        });
        jMenuItem9.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		menuItemClicked((javax.swing.JMenuItem)evt.getSource());
        	}
        });
        jMenuItem10.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		menuItemClicked((javax.swing.JMenuItem)evt.getSource());
        	}
        });
        jMenuItem11.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		menuItemClicked((javax.swing.JMenuItem)evt.getSource());
        	}
        });
        jMenuItem12.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		menuItemClicked((javax.swing.JMenuItem)evt.getSource());
        	}
        });
        jMenuItem13.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		menuItemClicked((javax.swing.JMenuItem)evt.getSource());
        	}
        });
        jMenuItem14.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		menuItemClicked((javax.swing.JMenuItem)evt.getSource());
        	}
        });
        jMenuItem15.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		menuItemClicked((javax.swing.JMenuItem)evt.getSource());
        	}
        });
        jMenuItem16.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		menuItemClicked((javax.swing.JMenuItem)evt.getSource());
        	}
        });
        jMenuItem17.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		menuItemClicked((javax.swing.JMenuItem)evt.getSource());
        	}
        });
        jMenuItem18.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		menuItemClicked((javax.swing.JMenuItem)evt.getSource());
        	}
        });
        jMenuItem19.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		menuItemClicked((javax.swing.JMenuItem)evt.getSource());
        	}
        });
        jMenuItem20.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		menuItemClicked((javax.swing.JMenuItem)evt.getSource());
        	}
        });
        jMenuItem21.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		menuItemClicked((javax.swing.JMenuItem)evt.getSource());
        	}
        });
        jMenuItem22.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		menuItemClicked((javax.swing.JMenuItem)evt.getSource());
        	}
        });
        jMenuItem23.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		menuItemClicked((javax.swing.JMenuItem)evt.getSource());
        	}
        });
        
        jMenu1.setText("Masters");
        jMenu1.add(jMenuItem2);
        jMenu1.add(jMenuItem3);
        jMenu1.add(jMenuItem4);
        jMenu1.add(jMenuItem5);
        jMenu1.add(jMenuItem10);
        
        jMenu2.setText("Bill of Material");
        jMenu2.add(jMenuItem6);
        jMenu2.add(jMenuItem7);
        
        jMenu3.setText("Purchase Order");
        jMenu3.add(jMenuItem8);
        jMenu3.add(jMenuItem9);
		
        jMenu4.setText("Material Transactions");
        jMenu4.add(jMenuItem11);
        jMenu4.add(jMenuItem23);
        jMenu4.add(jMenuItem12);
        jMenu4.add(jMenuItem13);
        
        jMenu5.setText("Reports");
        jMenu5.add(jMenuItem14);
        jMenu5.add(jMenuItem15);
        jMenu5.add(jMenuItem16);
        jMenu5.add(jMenuItem17);
        jMenu5.add(jMenuItem18);
        jMenu5.add(jMenuItem19);
        jMenu5.add(jMenuItem20);
        jMenu5.add(jMenuItem21);
        jMenu5.add(jMenuItem22);

        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);
        jMenuBar1.add(jMenu3);
        jMenuBar1.add(jMenu4);
        jMenuBar1.add(jMenu5);
        
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
        );
        
        addWindowListener(new java.awt.event.WindowAdapter(){
        	public void windowClosing(java.awt.event.WindowEvent evt) {
        		try{
	        		dbInterface1.close();
        		}
        		catch(java.sql.SQLException ex){
        		}
        	}
        });
        
        jDesktopPane1.setDesktopManager(new MDIDesktopManager(this, jMenuBar1));

        pack();
    //* ENDING: initComponents
    }
    
    /** called from report generator dialogs' generateReport() method. */
    public inventorycontroller.display.JInternalFrameReportViewer getReportViewer(){
    	return jInternalFrame14;
    }
    
    /** called from report generator dialogs' generateReport() method. */
    public javax.swing.JDesktopPane getDesktopPane(){
    	return jDesktopPane1;
    }
    
    public void menuItemClicked(javax.swing.JMenuItem jMenuItem) {
    	boolean isFrame=false;
    	javax.swing.JInternalFrame jif=null;
    	if(jMenuItem==jMenuItem2){ // order master
    		jInternalFrame2 = new inventorycontroller.display.JInternalFrameOrderMaster(dbInterface1);
    		jif=jInternalFrame2;
    		isFrame=true;
    	}
    	else if(jMenuItem==jMenuItem3){ // job master
    		jInternalFrame3 = new inventorycontroller.display.JInternalFrameJobMaster(dbInterface1);
    		jif=jInternalFrame3;
    		isFrame=true;
    	}
    	else if(jMenuItem==jMenuItem4){ // vendor master
    		jInternalFrame4 = new inventorycontroller.display.JInternalFrameVendorMaster(dbInterface1);
    		jif=jInternalFrame4;
    		isFrame=true;
    	}
    	else if(jMenuItem==jMenuItem5){ // product master
    		jInternalFrame5 = new inventorycontroller.display.JInternalFrameProductMaster(dbInterface1);
    		jif=jInternalFrame5;
    		isFrame=true;
    	}
    	else if(jMenuItem==jMenuItem6){ // create BOM
    		jInternalFrame6 = new inventorycontroller.display.JInternalFrameBillOfMaterial(dbInterface1);
    		jif=jInternalFrame6;
    		isFrame=true;
    	}
    	else if(jMenuItem==jMenuItem7){ // view BOM
    		jInternalFrame7 = new inventorycontroller.display.JInternalFrameShowBOM(dbInterface1);
    		jif=jInternalFrame7;
    		isFrame=true;
    	}
    	else if(jMenuItem==jMenuItem8){ // create PO
    		jInternalFrame8 = new inventorycontroller.display.JInternalFramePurchaseOrder(dbInterface1);
    		jif=jInternalFrame8;
    		isFrame=true;
    	}
    	else if(jMenuItem==jMenuItem9){ // view PO
    		jInternalFrame9 = new inventorycontroller.display.JInternalFrameShowPO(dbInterface1);
    		jif=jInternalFrame9;
    		isFrame=true;
    	}
    	else if(jMenuItem==jMenuItem10){ // material master
    		jInternalFrame10 = new inventorycontroller.display.JInternalFrameMaterialMaster(dbInterface1);
    		jif=jInternalFrame10;
    		isFrame=true;
    	}
    	else if(jMenuItem==jMenuItem11){ // insert GRN
    		jInternalFrame11 = new inventorycontroller.display.JInternalFrameGoodsReceiveNote(dbInterface1);
    		jif=jInternalFrame11;
    		isFrame=true;
    	}
    	else if(jMenuItem==jMenuItem12){ // material issue / return
    		jInternalFrame12 = new inventorycontroller.display.JInternalFrameGoodsIssue(dbInterface1);
    		jif=jInternalFrame12;
    		isFrame=true;
    	}
    	else if(jMenuItem==jMenuItem13){ // excess requisition
    		jInternalFrame13 = new inventorycontroller.display.JInternalFrameExcessRequisition(dbInterface1);
    		jif=jInternalFrame13;
    		isFrame=true;
    	}
    	else if(jMenuItem==jMenuItem23){ // cancel GRN
    		jInternalFrame15 = new inventorycontroller.display.JInternalFrameGRNCancellation(dbInterface1);
    		jif=jInternalFrame15;
    		isFrame=true;
    	}
    	//reports::
    	else if(jMenuItem==jMenuItem14){ // Bill Of Material
    		new inventorycontroller.display.JDialogReportBOM(this, true, dbInterface1).setVisible(true);
    	}
    	else if(jMenuItem==jMenuItem15){ // Purchase Requisition
    		new inventorycontroller.display.JDialogReportPurchaseRequisition(this, true, dbInterface1).setVisible(true);
    	}
    	else if(jMenuItem==jMenuItem16){ // Purchase Order
    		new inventorycontroller.display.JDialogReportPurchaseOrder(this, true, dbInterface1).setVisible(true);
    	}
    	else if(jMenuItem==jMenuItem17){ // Goods Receipt Note
    		new inventorycontroller.display.JDialogReportGRN(this, true, dbInterface1).setVisible(true);
    	}
    	else if(jMenuItem==jMenuItem18){ // Material Issue / Return Slip
    		new inventorycontroller.display.JDialogReportIssueReturn(this, true, dbInterface1).setVisible(true);
    	}
    	else if(jMenuItem==jMenuItem19){ // Stock Statement
    		new inventorycontroller.display.JDialogReportStockStatement(this, true, dbInterface1).setVisible(true);
    	}
    	else if(jMenuItem==jMenuItem20){ // Job Statements
    		new inventorycontroller.display.JDialogReportJobStatement(this, true, dbInterface1).setVisible(true);
    	}
    	else if(jMenuItem==jMenuItem21){ // Itemwise Ledger
    		new inventorycontroller.display.JDialogReportLedger(this, true, dbInterface1).setVisible(true);
    	}
    	else if(jMenuItem==jMenuItem22){ // Jobwise Material Cost-Sheet
    		new inventorycontroller.display.JDialogReportMaterialCostSheet(this, true, dbInterface1).setVisible(true);
    	}
    	
    	if(isFrame){
	    	jDesktopPane1.add(jif, new java.lang.Integer(2));
	    	try{
	        	jif.setSelected(true);
	    	}
	    	catch(java.lang.Exception ex){
	    	}
    	}
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	//try{
	            //	javax.swing.UIManager.setLookAndFeel("org.jdesktop.swingx.plaf.nimbus.NimbusLookAndFeel");
            	//}
            	//catch(java.lang.Exception ex){
            	//}
            	//finally{
	            	javax.swing.JFrame.setDefaultLookAndFeelDecorated(true);
	            	javax.swing.JDialog.setDefaultLookAndFeelDecorated(true);
	                new JFrameMDI().setVisible(true);
            	//}
            }
        });
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	System.out.println ("running");
            }
        });
    }
    
    //* Variables declaration - 
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private inventorycontroller.display.JInternalFrameOrderMaster jInternalFrame2;
    private inventorycontroller.display.JInternalFrameJobMaster jInternalFrame3;
    private inventorycontroller.display.JInternalFrameVendorMaster jInternalFrame4;
    private inventorycontroller.display.JInternalFrameProductMaster jInternalFrame5;
    private inventorycontroller.display.JInternalFrameBillOfMaterial jInternalFrame6;
    private inventorycontroller.display.JInternalFrameShowBOM jInternalFrame7;
    private inventorycontroller.display.JInternalFramePurchaseOrder jInternalFrame8;
    private inventorycontroller.display.JInternalFrameShowPO jInternalFrame9;
    private inventorycontroller.display.JInternalFrameMaterialMaster jInternalFrame10;
    private inventorycontroller.display.JInternalFrameGoodsReceiveNote jInternalFrame11;
    private inventorycontroller.display.JInternalFrameGoodsIssue jInternalFrame12;
    private inventorycontroller.display.JInternalFrameExcessRequisition jInternalFrame13;
    private inventorycontroller.display.JInternalFrameReportViewer jInternalFrame14;
    private inventorycontroller.display.JInternalFrameGRNCancellation jInternalFrame15;
    
    private inventorycontroller.function.DbInterface dbInterface1;
    //* End of variables declaration
    
}
