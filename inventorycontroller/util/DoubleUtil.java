

package inventorycontroller.util;


public class DoubleUtil {
	
	public static String getDisplayValue(String dv, int des){
		double d=Double.parseDouble(dv);
		return DoubleUtil.getDisplayValue(d, des);
	}
	
	public static String getDisplayValue(double d, int des){
		
		/*int i=(int)Math.rint(d);
		double diff=d-i;
		
		diff*=Math.pow(10, des);
		
		int di=(int)Math.rint(diff);
		//diff=Math.rint(diff);
		//diff/=Math.pow(10, des);
		//double res=i+diff;
		return i+(di/Math.pow(10, des))+"";*/
		double dd=d*Math.pow(10, des);
		
		int ii=(int)Math.rint(dd);
		return ii/Math.pow(10, des)+"";
	}
	
	
	/*public static void main(String[] args){
		
		System.out.println (DoubleUtil.getDisplayValue(0, 2));
	}*/
	
}