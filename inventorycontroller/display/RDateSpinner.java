/*
 * RDateSpinner.java
 *
 * Created on May 21, 2007, 2:09 HRS
 * Last Modified on May 21, 2007, 15:09 HRS
 */

package inventorycontroller.display;

/**
 *
 * @author brinto
 */
public class RDateSpinner extends javax.swing.JSpinner {
	
	public static final String MM_DD_YYYY="MM-dd-yyyy";
	public static final String DD_MM_YYYY="dd-MM-yyyy";
    
    /** Creates a new instance of RDateSpinner */
    public RDateSpinner() {
        this(MM_DD_YYYY, 1, 0, null);
    }
    
    
    public RDateSpinner(java.lang.String format) {
        this(format, 1, 0, null);
    }
    
    
    public RDateSpinner(java.lang.String format, int firstDate, int lastDate) {
        this(format, firstDate, lastDate, null);
    }
    
    
    public RDateSpinner(java.lang.String format, int firstDate, int lastDate,
     java.util.Date value) {
        super();
        
        if(format==null) {
        	dateFormat=new java.lang.String(MM_DD_YYYY);
        }
        else {
        	dateFormat=new java.lang.String(format);
        }
        
        if(value==null) {
        	curDate=new java.util.Date();
        }
        else {
        	curDate=new java.util.Date(value.getTime());
        }
        
        if(firstDate>lastDate) {
        	setModel(new javax.swing.SpinnerDateModel());
        	this.setValue(curDate);
        	iniDate=null;
        	finDate=null;
        }
        else {
	        java.util.Calendar d=java.util.Calendar.getInstance();
			d.setTime(new java.util.Date(0));
	        d.add(java.util.Calendar.YEAR, firstDate);
	        iniDate=d.getTime();
	        d.setTime(new java.util.Date(0));
	        d.add(java.util.Calendar.YEAR, lastDate);
	        finDate=d.getTime();
	        
        	if(curDate.after(finDate) ||curDate.before(iniDate)) {
	        	int diff=finDate.compareTo(iniDate);
	        	d=java.util.Calendar.getInstance();
				d.setTime(new java.util.Date(diff));
				d.add(java.util.Calendar.YEAR, firstDate);
				curDate=d.getTime();
	        }

	        setModel(new javax.swing.SpinnerDateModel(
	        	curDate, 
	        	iniDate, 
	        	finDate, 
	        	java.util.Calendar.DAY_OF_WEEK));
	    }
                
        setEditor(new javax.swing.JSpinner.DateEditor(this, dateFormat));
    }
    
    
    public static void main(String[] args) {
    	javax.swing.JOptionPane.showMessageDialog(
    		null, 
    		new javax.swing.JPanel().add(
    			new RDateSpinner("dd-MM-yyyy")));
    }
    
    //* Variable Declarations -
    private java.lang.String dateFormat;
    private java.util.Date curDate;
    private java.util.Date iniDate;
    private java.util.Date finDate;
    //* Ending Variable Declaration.
}
