 /**
 * Assignment 2: Java regular expressions <br />
 * Test cookies using regular expressions
 * Zijian He
 * 1429876
 * Acknowledgement:
 * 		1. Learn basic usage of Regex on stackoverflow.
 * 		2. ASCII Table at http://www.asciitable.com/
 */

import java.util.regex.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.text.ParseException;

public class CookieTest {

    /**
     * Verify a cookie and return the verification result
     * @param cookie        {@code String}  The cookie string
     * @return              {@code boolean} True for a legal cookie; false for an illegal one
     */
    public static boolean verifyCookie(String cookie) {
        boolean legal = true;
        // TODO: Assignment 2 -- compose your regular expression, and use it to verify the cookie
        legal = true;
        try{
        	/**Pattern pattern = Pattern.compile("^Set-Cookie:");
        	*  Matcher matcher = pattern.matcher(cookie);
        	*/
            
            String cookieLow = cookie.toLowerCase();
        	char lastChar = cookie.charAt(cookie.length() - 1);

        	
        
            boolean result = Pattern.compile(
            		"^Set-Cookie: [^\\x00-\\x1F\\x7F\\x28\\x29\\x3C\\x3E\\x40\\x2C\\x3B\\x3A\\x5C\\x22\\x2F\\x5B\\x5D\\x3F\\x3D\\x7B\\x7D\\x20]+"
            		+ "=(([\"[\\x21\\x23-\\x2B\\x2D-\\x3A\\x3C-\\x5B\\x5D-\\x7E]\"])"
            		+ "|([\\x21\\x23-\\x2B\\x2D-\\x3A\\x3C-\\x5B\\x5D-\\x7E]))*").matcher(cookie).find();       
            if(result == false){
            	// System.out.println("error1");
            	throw new Exception();
            	}// Check the cookie header

        	
            if (!(lastChar > 31 && lastChar != 59)){
            	//System.out.println("error2");
            	throw new Exception();
            	}// Check trailing            
        	
            
             result = Pattern.compile("Expires=").matcher(cookie).find();             
             if(result == true){
            	 int expireIndex = cookie.indexOf("Expires=") + 7;
            	 
            	 if (expireIndex + 30 <= cookie.length()){
            		 String datestr = cookie.substring(expireIndex + 1,expireIndex + 30);
            		 if(!isValidDate(datestr)){
                     	//System.out.println("error3");
            			 throw new Exception();
            			 }
            		 }
            	 else{
                 	 //System.out.println("error4");
            		 throw new Exception();
            		 }
            	 }// Check Expire
             
             
             result = Pattern.compile("Max-Age=").matcher(cookie).find();             
             if(result == true){
            	 int Index = cookie.indexOf("Max-Age=") + 7;
            	 String Cookie = cookie.substring(Index + 1);
            	 int end = Cookie.indexOf(';');
            	 
            	 if(end >= 0){
            		 String Str = Cookie.substring(0, end);  
            		 if(!Pattern.matches("^[1-9]+", Str)){           
            			 //System.out.println("error12");            			 
            			 throw new Exception();
            			 }  
            		 }
            	 else{
            		 String Str = Cookie;
            		 if(!Pattern.matches("^[1-9]+",Str)){
                     	//System.out.println("error5");
            			 throw new Exception();
            			 }
            		 }
            	 }// Check Max-Age
             
             
             result = Pattern.compile("Domain=").matcher(cookie).find();             
             if(result == true){
            	 int domainIndex = cookie.indexOf("Domain=") + 6;            	 
            	 String domainCookie = cookie.substring(domainIndex + 1);
            	 int endDomain = domainCookie.indexOf(';');
            	 String domainStr = null;
            	 
            	 if(endDomain >= 0){
            		 domainStr = domainCookie.substring(0, endDomain);
            		 if(isValidDomain(domainStr) == false){
                     	 //System.out.println("error6");
            			 throw new Exception();
            			 }
            		 }
            	 else{
            		 domainStr = domainCookie.substring(0);
            		 if(isValidDomain(domainStr) == false){
                      	 //System.out.println("error7");
            			 throw new Exception();
            			 }
            		 }
            	 }//Domain
           

             result = Pattern.compile("Path=").matcher(cookie).find();             
             if(result == true){
            	 int pathIndex = cookie.indexOf("Path=") + 4;
            	 
            	 if(pathIndex == cookie.length() - 1){
                 	 //System.out.println("error8");
            		 throw new Exception();
            		 }
            	 String pathCookie = cookie.substring(pathIndex + 1);
            	 int endPath = pathCookie.indexOf(';');           	 
            	 
            	 if (endPath == 0){
                 	 //System.out.println("error9");
            		 throw new Exception();
            		 }   
            	 else if (endPath > 0){
            		 String pathStr = pathCookie.substring(0, endPath);  
            		 
            		 for(int i = 0; i < pathStr.length(); i++){
            			 if(pathStr.charAt(i) < 31 || pathStr.charAt(i) == 59){
            	             //System.out.println("error10");
            				 throw new Exception();
            				 }
            			 }
            		 }
            	 else{
            		 String pathStr = pathCookie.substring(0);  
            		 for(int i = 0; i < pathStr.length(); i++){
            			 if(pathStr.charAt(i) < 31||pathStr.charAt(i) == 59){
            	             //System.out.println("error11");
            				 throw new Exception();
            				 }
            			 }        
            		 }
            	 }//Path           
             

           result = Pattern.compile("secure").matcher(cookieLow).matches();
           if(result){
        	   result = Pattern.compile("Secure").matcher(cookie).matches();
        	   
        	   if(result == false){
               	   //System.out.println("error12");
        		   throw new Exception();
               }else{
            	   throw new Exception();
               }
           }//Check Secure
           

           result = Pattern.compile("httponly").matcher(cookieLow).find();
           if(result){

        	   result = Pattern.compile("HttpOnly").matcher(cookie).find();
               if(result == false){          
            	   //System.out.println("error13");
            	   throw new Exception();
            	   }
               }//httponly
           
        }catch(Exception e){
        	   legal = false;
        	   }
        return legal;
        }
    
    

    private static boolean isValidDate(String datestr) {
		// TODO Auto-generated method stub
    	SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
        dateFormat.setLenient(false);
        try {
          dateFormat.parse(datestr.trim());
          } catch (ParseException e) {
          return false;
          }
        return true;
        }
    
    
    
    private static boolean isValidDomain(String inDomain) {
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	char character = '.';
    	for(int i = 0; i < inDomain.length(); i++){
    		if(inDomain.charAt(i) == character){
    			list.add(i);
    			}
    		}//Find the position of '.'
    	for(int j = 0;j < list.size();j++){
    		if(Character.isLetter(inDomain.charAt(list.get(j) + 1))== false){
    			return false; 
    			}
    		if(j < list.size() - 1){
    			if(Character.isLetter(inDomain.charAt(list.get(j + 1) - 1)) == false
    					&& Character.isDigit(inDomain.charAt(list.get(j + 1) - 1)) == false){
    				return false;
    				}
    			}
    		//the end of doamin
    		else if(Character.isLetter(inDomain.charAt(inDomain.length() - 1)) == false
    				&& Character.isDigit(inDomain.charAt(inDomain.length() - 1)) == false){
    			return false;
    			}
    		}   
    	return true;
    	}
    

	/**
     * Main entry
     * @param args          {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        String [] cookies = {
            // Legal cookies:
            "Set-Cookie: ns1=\"alss/0.foobar^\"",                                           // 01 name=value
            "Set-Cookie: ns1=",                                                             // 02 empty value
            "Set-Cookie: ns1=\"alss/0.foobar^\"; Expires=Tue, 18 Nov 2008 16:35:39 GMT",    // 03 Expires=timeStamp
            "Set-Cookie: ns1=; Domain=",                                                    // 04 empty domain
            "Set-Cookie: ns1=; Domain=.srv.a.com-0",                                        // 05 Domain=host_name
            "Set-Cookie: lu=Rg3v; Expires=Tue, 18 Nov 2008 16:35:39 GMT; Path=/; Domain=.example.com; HttpOnly", // 06
            // Illegal cookies:
            "Set-Cookie:",                                              // 07 empty cookie-pair
            "Set-Cookie: sd",                                           // 08 illegal cookie-pair: no "="
            "Set-Cookie: =alss/0.foobar^",                              // 09 illegal cookie-pair: empty name
            "Set-Cookie: ns@1=alss/0.foobar^",                          // 10 illegal cookie-pair: illegal name
            "Set-Cookie: ns1=alss/0.foobar^;",                          // 11 trailing ";"
            "Set-Cookie: ns1=; Expires=Tue 18 Nov 2008 16:35:39 GMT",   // 12 illegal Expires value
            "Set-Cookie: ns1=alss/0.foobar^; Max-Age=01",               // 13 illegal Max-Age: starting 0
            "Set-Cookie: ns1=alss/0.foobar^; Domain=.0com",             // 14 illegal Domain: starting 0
            "Set-Cookie: ns1=alss/0.foobar^; Domain=.com-",             // 15 illegal Domain: trailing non-letter-digit
            "Set-Cookie: ns1=alss/0.foobar^; Path=",                    // 16 illegal Path: empty
            "Set-Cookie: ns1=alss/0.foobar^; httponly",                 // 17 lower case
        };
        for (int i = 0; i < cookies.length; i++)
            System.out.println(String.format("Cookie %2d: %s", i+1, verifyCookie(cookies[i]) ? "Legal" : "Illegal"));
    }

}
