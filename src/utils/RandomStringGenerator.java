package utils;

import java.util.Random;

public class RandomStringGenerator {
	
	private int length;
	private static final String numbers = "1234567890";
	private static final String lettersUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private String saltchars;
	
	public enum StringType{
		
		ALPHANUMERIC {
	        @Override
	        public String getSaltChars() {
	            return lettersUpper + numbers;
	        }
	    }, 
		NUMERIC{
	    	@Override
	    	public String getSaltChars() {
	    		return numbers;
	    	}
	    };
		
	    abstract String getSaltChars();
	}
	
	public RandomStringGenerator(int length, StringType stringType) {
		super();
		this.length = length;
		this.saltchars = stringType.getSaltChars();
	}
	
	public String newString(){
		
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        
        while (salt.length() < this.length) { 
            int index = (int) (rnd.nextFloat() * saltchars.length());
            salt.append(saltchars.charAt(index));
        }
        
        return salt.toString();

	}
	

}
