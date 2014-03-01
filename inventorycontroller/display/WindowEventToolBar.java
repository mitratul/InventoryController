 /*
 * WindowEventToolBar.java
 *
 * Created on May July 05, 2007, 19:20 HRS
 * Last Modified on July 07, 2007, 12:20 HRS
 *
 */

package inventorycontroller.display;

/**
 *
 * @author  brinto
 */
public class WindowEventToolBar extends javax.swing.JPanel {
	
	public WindowEventToolBar(){
		initComponents();
	}
	
	private void initComponents() {
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventorycontroller/display/resources/iconify.gif")));
        jButton1.setMaximumSize(new java.awt.Dimension(16, 16));
        jButton1.setMinimumSize(new java.awt.Dimension(16, 16));
        jButton1.setPreferredSize(new java.awt.Dimension(16, 16));
        jButton1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/inventorycontroller/display/resources/iconify-pressed.gif")));
        jButton1.setToolTipText("Minimize");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iconifyPerform();
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventorycontroller/display/resources/minimize.gif")));
        jButton2.setMaximumSize(new java.awt.Dimension(16, 16));
        jButton2.setMinimumSize(new java.awt.Dimension(16, 16));
        jButton2.setPreferredSize(new java.awt.Dimension(16, 16));
        jButton2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/inventorycontroller/display/resources/minimize-pressed.gif")));
        jButton2.setToolTipText("Restore");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimizePerform();
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventorycontroller/display/resources/close.gif")));
        jButton3.setMaximumSize(new java.awt.Dimension(16, 16));
        jButton3.setMinimumSize(new java.awt.Dimension(16, 16));
        jButton3.setPreferredSize(new java.awt.Dimension(16, 16));
        jButton3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/inventorycontroller/display/resources/close-pressed.gif")));
        jButton3.setToolTipText("Close");
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closePerform();
            }
        });
        
        setMinimumSize(new java.awt.Dimension(56, 20));
        setPreferredSize(new java.awt.Dimension(56, 20));
        setMaximumSize(new java.awt.Dimension(56, 20));
        this.setVisible(true);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        setLayout(layout);
		layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jButton1, 16, 16, 16)
                .addGap(2, 2, 2)
                .addComponent(jButton2, 16, 16, 16)
                .addGap(8, 8, 8)
                .addComponent(jButton3, 16, 16, 16)
                .addGap(4, 4, 4))
        );        
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, 16, 16, 16)
                    .addComponent(jButton2, 16, 16, 16)
                    .addComponent(jButton3, 16, 16, 16))
                .addGap(2, 2, 2))
        );
        this.setMinimumSize(new java.awt.Dimension(64, 22));
        this.setPreferredSize(new java.awt.Dimension(64, 20));
        this.setMaximumSize(new java.awt.Dimension(64, 20));
    }
    
    public void iconifyPerform() {
    }

    public void minimizePerform() {
    }

    public void closePerform() {
    }
    
    
    //* Variables declaration - 
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    //* End of variables declaration
}