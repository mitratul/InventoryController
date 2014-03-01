 /*
 * MDIDesktopManager.java
 *
 * Created on May July 05, 2007, 19:20 HRS
 * Last Modified on July 15, 2007, 13:52 HRS
 *
 */

package inventorycontroller.display;

/**
 *
 * @author  brinto
 */
public class MDIDesktopManager extends javax.swing.DefaultDesktopManager {
	
	public MDIDesktopManager(javax.swing.JFrame mdiframe, javax.swing.JMenuBar jmb) {
		MDIFrame=mdiframe;
		jMenuBar=jmb;
    	maximizedJIF=null;
    	mdiTitle=new java.lang.String(MDIFrame.getTitle());
    	jSeparator=new javax.swing.JSeparator(javax.swing.JSeparator.VERTICAL);
    	jSeparator.setUI( new javax.swing.plaf.basic.BasicSeparatorUI() {
			public void paint( java.awt.Graphics g, javax.swing.JComponent c ) {
			}
		});
    	
    	vectorMaximizedJIF=new java.util.Vector<javax.swing.JInternalFrame>();
		
    	weToolBar1=new inventorycontroller.display.WindowEventToolBar(){
    		public void iconifyPerform() {
    			try{
	    			maximizedJIF.setIcon(true);
    			}
    			catch(java.lang.Exception ex){
					System.out.println (ex);
    			}
    		}
    		public void minimizePerform() {
    			try{
	    			maximizedJIF.setMaximum(false);
    			}
    			catch(java.lang.Exception ex){
					System.out.println (ex);
    			}
    		}
    		public void closePerform() {
    			try{
	    			maximizedJIF.setClosed(true);
    			}
    			catch(java.lang.Exception ex){
					System.out.println (ex);
    			}
    		}
    	};
        jMenuBar.add(jSeparator);
        jMenuBar.add(weToolBar1);
        weToolBar1.setVisible(false);
        jSeparator.setVisible(false);
	}
	
	
    private void setNorthEastPane(boolean b){
    	if(b){
	    	weToolBar1.setVisible(true);
	        jSeparator.setVisible(true);
    	}
    	else{
    		weToolBar1.setVisible(false);
	        jSeparator.setVisible(false);
    	}
    	setMDITitle(b);
    }
    
    private void setMDITitle(boolean b){
    	if(b){
    		MDIFrame.setTitle(maximizedJIF.getTitle().concat(" @ "+mdiTitle));
    	}
    	else {
    		MDIFrame.setTitle(mdiTitle);
    	}
    }
	
	public void maximizeFrame(javax.swing.JInternalFrame jif){
		if(maximizedJIF!=null){
			try{
    			maximizedJIF.setMaximum(false);
			}
			catch(java.lang.Exception ex){
				System.out.println (ex);
			}
		}
		((javax.swing.plaf.basic.BasicInternalFrameUI)jif.getUI())
			.getNorthPane().setPreferredSize(new java.awt.Dimension(jif.getWidth() , 0));
		maximizedJIF=jif;
		super.maximizeFrame(jif);
		setNorthEastPane(true);
	}
	
	public void minimizeFrame(javax.swing.JInternalFrame jif){
		((javax.swing.plaf.basic.BasicInternalFrameUI)jif.getUI())
			.getNorthPane().setPreferredSize(new java.awt.Dimension(jif.getWidth() , 23));
		maximizedJIF=null;
		setNorthEastPane(false);
		super.minimizeFrame(jif);
	}
	
	public void closeFrame(javax.swing.JInternalFrame jif){
		if(jif.equals(maximizedJIF)){
			maximizedJIF=null;
			setNorthEastPane(false);
		}
		super.closeFrame(jif);
	}
	
	public void iconifyFrame(javax.swing.JInternalFrame jif){
		if(jif.equals(maximizedJIF)){
			vectorMaximizedJIF.add(maximizedJIF);
			maximizedJIF=null;
			setNorthEastPane(false);
		}
		super.iconifyFrame(jif);
	}
	
	public void deiconifyFrame(javax.swing.JInternalFrame jif){
		super.deiconifyFrame(jif);
		if(vectorMaximizedJIF.contains(jif)){
			maximizedJIF=jif;
			vectorMaximizedJIF.remove(jif);
			try{
    			maximizedJIF.setMaximum(true);
			}
			catch(java.lang.Exception ex){
				System.out.println (ex);
			}
			setNorthEastPane(true);
		}
	}
	
	private javax.swing.JFrame MDIFrame;
	private java.lang.String mdiTitle;
	private javax.swing.JMenuBar jMenuBar;
	private javax.swing.JSeparator jSeparator;
	private javax.swing.JInternalFrame maximizedJIF;
	private java.util.Vector<javax.swing.JInternalFrame> vectorMaximizedJIF;
	private inventorycontroller.display.WindowEventToolBar weToolBar1;
}
