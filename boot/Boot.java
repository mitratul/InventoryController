/*
 * Boot.java
 *
 * Created on May 21, 2007, 12:01 HRS
 * Last Modified on August 08, 2007, 00:20 HRS
 *
 */

package boot; 

/**
 *
 * @author brinto
 */
public class Boot {
	
	public static void main(String[] args) throws Exception{
		
		Runtime.getRuntime().exec("java -jar InventoryController.jar");
	}
}