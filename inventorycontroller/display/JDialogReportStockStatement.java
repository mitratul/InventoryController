/*
 * JDialogReportStockStatement.java
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
public class JDialogReportStockStatement extends javax.swing.JDialog {
    
    /** Creates new form JDialogReportStockStatement */
    public JDialogReportStockStatement(inventorycontroller.display.JFrameMDI parent, boolean modal, 
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
    	
    	jLabelStatus = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jSpinner2 = new inventorycontroller.display.RDateSpinner(jSpinner2.DD_MM_YYYY);
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        
        jLabelStatus.setVisible(false);
        jLabelStatus.setForeground(new java.awt.Color(251, 82, 0));
        jLabelStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelStatus.setVisible(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Stock Statement");
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 157, 185)));
        jLabel1.setText("Select Financial Year: ");

        jLabel2.setText("Select Closing Date: ");
        
        // setting jSpinner1 model.
    	int iyr=java.util.Calendar.getInstance(java.util.TimeZone.getTimeZone("GMT+5:30")).
    	 get(java.util.Calendar.YEAR)%100;
    	int imn=java.util.Calendar.getInstance(java.util.TimeZone.getTimeZone("GMT+5:30")).
    	 get(java.util.Calendar.MONTH);
    	iyr=imn<3?iyr-1:iyr;	//if month less than april, then prev fin. yr
    	String yr=(iyr<10?"0":"")+iyr+(iyr<9?"-0":"-")+(iyr+1);
    	
    	jSpinner1.setModel(new javax.swing.SpinnerModel(){
    		public Object getNextValue(){
    			String yr=jSpinner1.getValue().toString();
    			String[] y=yr.split("-");
    			String newYr=(Integer.parseInt(y[0])+1)+"-"+(Integer.parseInt(y[1])+1);
    			return newYr;
    		}
    		public Object getPreviousValue(){
    			String yr=jSpinner1.getValue().toString();
    			String[] y=yr.split("-");
    			if(Integer.parseInt(y[0])<7){
    				return yr;
    			}
    			String newYr=(Integer.parseInt(y[0])-1)+"-"+(Integer.parseInt(y[1])-1);
    			return newYr;
    		}
    		public void addChangeListener(javax.swing.event.ChangeListener l){
    			jSpinner1.addChangeListener(l);
    		}
    		public void removeChangeListener(javax.swing.event.ChangeListener l){
    			jSpinner1.removeChangeListener(l);
    		}
    		public Object getValue(){
    			return ((javax.swing.JSpinner.DefaultEditor)jSpinner1.getEditor()).getTextField().getText();
    		}
    		public void setValue(Object val){
    			String sVal=val.toString();
    			String[] y=sVal.split("-");
    			String value=(Integer.parseInt(y[0])<10?"0":"")+Integer.parseInt(y[0])+(Integer.parseInt(y[1])<10?"-0":"-")+Integer.parseInt(y[1]);
    			((javax.swing.JSpinner.DefaultEditor)jSpinner1.getEditor()).getTextField().setEditable(true);
    			((javax.swing.JSpinner.DefaultEditor)jSpinner1.getEditor()).getTextField().setText(value);
    		}
    	});
    	jSpinner1.getModel().setValue(yr);
    	//done

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    //.addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    //.addComponent(jSpinner1, 120, 120, 120)
                    .addComponent(jSpinner2, 120, 120, 120))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelStatus)
                //.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                //    .addComponent(jLabel1)
                //    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                //.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
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
    	
    	String rawDate=inventorycontroller.util.DateUtil
    	 .getRawFormat((java.util.Date)jSpinner2.getValue());
    	if(!check(rawDate)){
    		return;
    	}
    	this.setVisible(false);
    	inventorycontroller.function.StockStatementReportProcessor rp=
    	 new inventorycontroller.function.StockStatementReportProcessor(dbInterface1, rawDate);
    	rp.generate();
    	rp.initPrint(this.parent.getDesktopPane());
    	this.dispose();
    }
    
    private boolean check(String rawDate){
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
    	
    	if(tmp[tmp.length-1][0].compareTo(rawDate)<0){
    		jLabelStatus.setText("Date not arrived");
    		jLabelStatus.setVisible(true);
    		jSpinner2.grabFocus();
    		pack();
    		this.setLocation();
    		return false;
    	}
    	
    	if(tmp[0][0].compareTo(rawDate)>0){
    		jLabelStatus.setText("Date out of range");
    		jLabelStatus.setVisible(true);
    		jSpinner2.grabFocus();
    		pack();
    		this.setLocation();
    		return false;
    	}
    	return true;
    }
    
    public void resetPerform(){
    	int iyr=java.util.Calendar.getInstance(java.util.TimeZone.getTimeZone("GMT+5:30")).
    	 get(java.util.Calendar.YEAR)%100;
    	int imn=java.util.Calendar.getInstance(java.util.TimeZone.getTimeZone("GMT+5:30")).
    	 get(java.util.Calendar.MONTH);
    	iyr=imn<3?iyr-1:iyr;	//if month less than april, then prev fin. yr
    	String yr=(iyr<10?"0":"")+iyr+(iyr<9?"-0":"-")+(iyr+1);
    	jSpinner1.getModel().setValue(yr);
    	
    	jSpinner2.setValue(new java.util.Date());
    	
    	this.jLabelStatus.setVisible(false);
    	this.pack();
    	this.setLocation();
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
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSpinner jSpinner1;
    private inventorycontroller.display.RDateSpinner jSpinner2;
    private inventorycontroller.display.JFrameMDI parent;
    private inventorycontroller.function.DbInterface dbInterface1;
    // End of variables declaration
    
}
