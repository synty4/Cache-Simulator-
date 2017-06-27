/**
 * This class converts integer to hexadecimal Strings 
 * and hexadecimal Strings to integers
 * */


public class Conversion {
	/**
	 * This method converts integer to hexadecimal
	 * @pre: input is an integer 
	 * @post: the output is an hexadecimal String 
	 * */
		public static String convert_to_hex(int value) {
	        String my_hex = Integer.toHexString(value);
		while(my_hex.length() < 8) {
			my_hex = "0" + my_hex;
		}
		return my_hex;
	    }
	
	/**
	 * This method converts  an hexadecimal string to integer 
	 * @pre: input is a String representing an hexadecimal number
	 * @post: the output is an integer 
	 * */
	  public static int convert_to_int(String s) {
		  String digits = "0123456789ABCDEF";
	        s = s.toUpperCase();
	        int val = 0;
	        for (int i = 0; i < s.length(); i++) {
	            char c = s.charAt(i);
	            int d = digits.indexOf(c);
	            val = 16*val + d;
	        }
	        return val;
	    }
}	
	    
	  
