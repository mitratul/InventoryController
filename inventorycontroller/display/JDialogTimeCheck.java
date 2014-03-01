/*
 * JDialogTimeCheck.java
 *
 * Created on September 3, 2007, 11:54 AM
 */

package inventorycontroller.display;

/**
 *
 * @author  brinto
 */
public class JDialogTimeCheck extends javax.swing.JDialog {
    
    /** Creates new form JDialogTimeCheck */
    public JDialogTimeCheck(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents() {
    	try{
        	dbInterface1=new inventorycontroller.function.DbInterface("./db/db.mdb", "Microsoft Access Driver (*.mdb)");
        }
        catch(java.lang.ClassNotFoundException ex){
        	ex.printStackTrace();
        	
        }
        catch(java.sql.SQLException ex){
        	ex.printStackTrace();
        	
        }
    	
    	
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSpinner1 = new inventorycontroller.display.RDateSpinner(jSpinner1.DD_MM_YYYY);
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        this.addWindowListener(new java.awt.event.WindowAdapter(){
        	public void windowClosing(java.awt.event.WindowEvent evt){
        		System.exit(0);
        	}
        });
        setTitle("TimeCheck@InventoryController");
        //setAlwaysOnTop(true);
        setResizable(false);
        jPanel1.setOpaque(false);
        jPanel2.setOpaque(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("To Start Working, Enter Current Date Properly (DD-MM-YYYY)");

        jLabel2.setForeground(new java.awt.Color(251, 82, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setVisible(false);
        jLabel2.setText("Already Closed for that Date");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
            	.addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSpinner1, 160, 160, 160)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinner1, 22, 22, 22)
                .addContainerGap())
        );

        jButton1.setText("Start Working");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startPerform(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, 220, 220, 220)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, 24, 24, 24)
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
                .addContainerGap())
        );
        pack();
        setLocation();
        this.getRootPane().setDefaultButton(jButton1);
    //* ENDING: initComponents.
    }

    private void startPerform(java.awt.event.ActionEvent evt) {

    	if(verified()){
    		if(newYear()){
    			backUp();
    		}
    		if(newDay()){
    			saveTime();
    		}
    		try{
    			dbInterface1.close();
    		}
    		catch(Exception ex){
    			ex.printStackTrace();
    		}
        	this.dispose();
    	}
    	else{
    		jLabel2.setVisible(true);
    		jSpinner1.grabFocus();
    		pack();
    		setLocation();
    	}
    }
    
    private boolean verified(){
    	String dateStr=inventorycontroller.util.DateUtil.getRawFormat(
    		(java.util.Date)jSpinner1.getValue()
    	);
    	String[][] tmp=null;
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
    	
    	if(tmp.length==0){ //if 1st day of the yr then true.
	    	return true;
    	}
    	
    	// if dateStr, ie. current date >=, ie. same or after last stored date
    	//   then return true, ie. verify
    	return tmp[tmp.length-1][0].compareTo(dateStr)<=0;
    }
    
    private boolean newYear(){
    	String dateStr=inventorycontroller.util.DateUtil.getRawFormat(
    		(java.util.Date)jSpinner1.getValue()
    	);
    	
    	String[][] tmp=null;
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
    	
    	if(tmp.length==0){
    		return true;
    	}
    	String currentYr=dateStr.substring(0, 4);
    	if(Integer.parseInt(dateStr.substring(4, 6))<4){
    		currentYr=Integer.parseInt(currentYr)-1+"";
    	}
    	String dbYr=tmp[tmp.length-1][0].substring(0, 4);
    	if(Integer.parseInt(tmp[tmp.length-1][0].substring(4, 6))<4){
    		dbYr=Integer.parseInt(dbYr)-1+"";
    	}
    	
    	return dbYr.compareTo(currentYr)<0;
    }
    
    private boolean newDay(){
    	String dateStr=inventorycontroller.util.DateUtil.getRawFormat(
    		(java.util.Date)jSpinner1.getValue()
    	);
    	String[][] tmp=null;
    	try{
    		tmp=dbInterface1.cmdSelect(
    			"dateTSMap", 
    			"dateTSMap.date", 
    			"dateTSMap.date='"+dateStr+"'"
    		);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	
    	return tmp.length==0;
    	
    	
    }
    
    private void backUp(){
    	
    }
    
    private void saveTime(){
    	String dateStr=inventorycontroller.util.DateUtil.getRawFormat(
    		(java.util.Date)jSpinner1.getValue()
    	);
    	String tsid=inventorycontroller.function.TimeStampProcessor
    	 .getNewTSid(dateStr, dbInterface1);
    	try{
    		dbInterface1.cmdInsert(
    			"dateTSMap",
    			"'"+dateStr+"', '"+tsid+"', '_'"
    		);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
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
            	javax.swing.JFrame.setDefaultLookAndFeelDecorated(true);
            	javax.swing.JDialog.setDefaultLookAndFeelDecorated(true);
                new JDialogTimeCheck(null, true).setVisible(true);
        		System.exit(0);
            }
        });
    }
    
    // Variables declaration -
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private inventorycontroller.display.RDateSpinner jSpinner1;
    
    private inventorycontroller.function.DbInterface dbInterface1;
    // End of variables declaration
    
}
/*
 *
    	
    	int bomNo=0;
    	int iyr=java.util.Calendar.getInstance(java.util.TimeZone.getTimeZone("GMT+5:30")).
    	 get(java.util.Calendar.YEAR)%100;
    	int imn=java.util.Calendar.getInstance(java.util.TimeZone.getTimeZone("GMT+5:30")).
    	 get(java.util.Calendar.MONTH);
    	
    	iyr=imn<3?iyr-1:iyr;	//if month less than april, then prev fin. yr
    	String yr=(iyr<10?"0":"")+iyr+(iyr<9?"-0":"-")+(iyr+1);
    	
    	for (int i = 0; i<bomNos.length; i++){
    		if(bomNos[i][0].startsWith(Bill_OF_MATERIAL_ID+"/"+yr+"/")){
    			bomNo++;
    		}
    	}
    	java.lang.String fullBomNo=new java.lang.String(Bill_OF_MATERIAL_ID+"/"+yr+"/");
    	for (int i = 7; i>=0; i--){	//if odrNo>10^7 then length will incr
	    	if((double)bomNo<java.lang.Math.pow((double)10, (double)i)){
	    		fullBomNo=fullBomNo+"0";
	    	}
	    	else{
	    		fullBomNo=fullBomNo+bomNo;
	    		break;
	    	}
    	}
    	return fullBomNo;*/