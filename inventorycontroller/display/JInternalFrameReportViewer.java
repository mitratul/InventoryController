/*
 * JInternalFrameReportViewer.java
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
public class JInternalFrameReportViewer extends javax.swing.JInternalFrame {
    
    /** Creates new form JInternalFramePrintPreview */
    public JInternalFrameReportViewer(String reportType) {
        initComponents(reportType);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents(String reportType) {
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Report Preview: "+reportType);
        jEditorPane1.setEditable(false);
        jEditorPane1.setContentType("text/plain");
        jEditorPane1.setFont(
    		new java.awt.Font(java.awt.Font.MONOSPACED, java.awt.Font.PLAIN, 10)
    	);
    	jEditorPane1.setMinimumSize(new java.awt.Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        jEditorPane1.setMaximumSize(new java.awt.Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        jEditorPane1.setMaximumSize(new java.awt.Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        jScrollPane1.setViewportView(jEditorPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            	.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            	.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
        );

        jToolBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

		jSeparator1.setUI( new javax.swing.plaf.basic.BasicSeparatorUI() {
			public void paint( java.awt.Graphics g, javax.swing.JComponent c ) {
			}
		});
    	jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar1.add(jSeparator1);
        
        jButton1.setText("Page Setup..");
        jToolBar1.add(jButton1);

        jButton2.setText("Print Content");
        jToolBar1.add(jButton2);

        jButton3.setText("Save Content as..");
        jToolBar1.add(jButton3);

        jButton4.setText("Exit");
        jToolBar1.add(jButton4);

        jSeparator2.setUI( new javax.swing.plaf.basic.BasicSeparatorUI() {
			public void paint( java.awt.Graphics g, javax.swing.JComponent c ) {
			}
		});
    	jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar1.add(jSeparator2);

        jLabel1.setBackground(new java.awt.Color(127, 157, 185));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");
        jLabel1.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, 24, 24, 24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        pack();
        setVisible(true);
        this.getRootPane().setDefaultButton(jButton2);
    //* ENDING: initComponents.
    }
    
    public javax.swing.JEditorPane getReportPane(){
    	return jEditorPane1;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration
    
}
