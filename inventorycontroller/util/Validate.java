
package inventorycontroller.util;


public class Validate {
	
	public static void checkValidity(){
		int i=1;
		try{
			Process p= Runtime.getRuntime().exec("reg query HKCR\\MS /v valid");
			p.waitFor();
			i=p.exitValue();
		}
		catch(Exception ex){
		}
		if(i==0){
			return;
		}
		javax.swing.JOptionPane.showMessageDialog(
			(java.awt.Component)null, 
			"<html><center>This is an Invalid copy of Management Systems ControlInventory<br>"+
			"Contact Management Systems to Validate this copy.<center><html>", 
			"InventoryController says..", 
			javax.swing.JOptionPane.ERROR_MESSAGE
		); 
		System.exit(1);
	}
	
	public static void checkExpiryDate(String curDate){
		int i=1;
		String line="";
		try{
			Process p= Runtime.getRuntime().exec("reg query HKCR\\MS /v valid");
			p.waitFor();
			java.io.BufferedReader br=new java.io.BufferedReader(
				new java.io.InputStreamReader(p.getInputStream())
			);
			
			while(true){
				line=br.readLine();
				if(line==null){
					break;
				}
				if(line.indexOf("valid")>0){
					break;
				}
			}
			
			i=p.exitValue();
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.exit(1);
			return;
		}
		if(i==1){
			System.exit(1);
			return;
		}
		String value="";
		java.util.StringTokenizer st=new java.util.StringTokenizer(line, " \t");
		while(st.hasMoreTokens()){
			value=st.nextToken();
		}
		
		if(value.compareTo(curDate)>0){
			return;
		}
		else{
			javax.swing.JOptionPane.showMessageDialog(
				(java.awt.Component)null, 
				"<html><center>This is an Expired Trial copy of Management Systems ControlInventory<br>"+
				"Contact Management Systems to Validate this copy.<center><html>", 
				"InventoryController says..", 
				javax.swing.JOptionPane.ERROR_MESSAGE
			);
			System.exit(1);
		}
		
	}
	
	public static void main(String[] args){
		Validate.checkExpiryDate(args[0]);
	}
	
}