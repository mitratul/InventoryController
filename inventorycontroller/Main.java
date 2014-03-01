/*
 * Main.java
 *
 * Created on May 21, 2007, 12:01 HRS
 * Last Modified on August 08, 2007, 00:20 HRS
 *
 */

package inventorycontroller; 

/**
 *
 * @author brinto
 */
public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	javax.swing.JFrame.setDefaultLookAndFeelDecorated(true);
            	javax.swing.JDialog.setDefaultLookAndFeelDecorated(true);
            	
            	//* check validity of product. in invalid then exits from here.
            	inventorycontroller.util.Validate.checkValidity();
            	
            	//* if valid then start.
    			new inventorycontroller.display.JDialogTimeCheck((javax.swing.JFrame)null, true)
    			 .setVisible(true);
    			String curDate="";
    			
    			try{
	    			inventorycontroller.function.DbInterface dbInterface1=
	    			 new inventorycontroller.function.DbInterface(
	    			 	"./db/db.mdb", "Microsoft Access Driver (*.mdb)"
	    			 );
	    			curDate=inventorycontroller.function.DateProcessor
	    			 .getCurrentDate(dbInterface1);
	    			dbInterface1.close();
    			}
    			catch(Exception ex){
    				ex.printStackTrace();
    				return;
    			}
				
				inventorycontroller.util.Validate.checkExpiryDate(curDate);
				
                new inventorycontroller.display.JFrameMDI().setVisible(true);
            }
        });
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	System.out.println ("running");
            }
        });
    }
    
}
