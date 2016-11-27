import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Assignment 2: Java regular expressions <br />
 * Test cookies using regular expressions
 * @author  MarcoXZh
 * @version 1.0.0
 */
public class CookieTest_Solution {

    /**
     * Verify a cookie and return the verification result
     * @param cookie        {@code String}  The cookie string
     * @return              {@code boolean} True for a legal cookie; false for an illegal one
     */
    public static boolean verifyCookie(String cookie) {
        // Regular Expression: Cookie-Pair
        String reName = "[\\x21-\\x7e&&[^\\(\\)\\<\\>@\\,;:\\\\\"/\\[\\]\\?\\=\\{\\}]]+";
        String reOctet = "[\\x21-\\x7e&&[^\"\\,;\\\\]]+";
        String reCookieValue = String.format("\"(%s)*\"|(%s)*", reOctet, reOctet);
        String reCookiePair = String.format("(%s)=(%s)", reName, reCookieValue);
        // Regular Expression: Cookie AV -- Expires
        String wkday = "(Mon|Tue|Wed|Thu|Fri|Sat|Sun)";
        String month = "(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)";
        String reExpiresAV = String.format("Expires=%s, \\d{2} %s \\d{4} \\d{2}:\\d{2}:\\d{2} GMT", wkday, month);
        // Regular Expression: Cookie AV -- Max-Age
        String reMaxAgeAV = "Max-Age=[1-9][0-9]*";
        // Regular Expression: Cookie AV -- Domain
        String reLabel = "[\\p{Alpha}]([\\p{Alnum}\\-]*\\p{Alnum})*";
        String reDomainAV = String.format("Domain=(%s)*(\\.%s)*", reLabel, reLabel);
        // Regular Expression: Cookie AV -- Path
        String rePathAV = "Path=[\\x20-\\x7e&&[^;]]+";
        // Regular Expression: Cookie AV -- Secure
        String reSecureAV = "Secure";
        // Regular Expression: Cookie AV -- HttpOnly
        String reHttpOnlyAV = "HttpOnly";
        // Regular Expression: Cookie AV -- Extension
        String reExtensionAV = "[\\x20-\\x7e&&[^;]]+";
        // Regular Expression: Cookie AV -- all
        String reCookieAV = String.format("(%s)|(%s)|(%s)|(%s)|%s|%s|%s", reExpiresAV, reMaxAgeAV,
                                          reDomainAV, rePathAV, reSecureAV, reHttpOnlyAV, reExtensionAV);

        // ==== NOTE: to test the cookie-avs, we remove the extension-av ====
        reCookieAV = reCookieAV.substring(0, reCookieAV.lastIndexOf('|'));

        // Regular Expression: Set-Cookie
        String reSetCookie = String.format("^Set-Cookie: *(%s)(; *(%s))*$", reCookiePair, reCookieAV);
        //System.out.println(reSetCookie);
        Pattern pattern = Pattern.compile(reSetCookie);
        Matcher matcher = pattern.matcher(cookie);
        return matcher.matches();
    } // public static boolean verifyCookie(String cookie)

    /**
     * Main entry
     * @param args          {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        String [] cookies = {
            // Legal cookies:
            "Set-Cookie: ns1=\"alss/0.foobar^\"",                                           // 01 name=value
            "Set-Cookie: ns1=",                                                             // 02 empty value
            "Set-Cookie: ns1=\"alss/0.foobar^\"; Expires=Tue, 18 Nov 2008 16:35:39 GMT",    // 03 Expires=time_stamp
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
        }; // String [] cookies = { ... };
        for (int i = 0; i < cookies.length; i++)
            System.out.println(String.format("Cookie %2d: %s", i+1, verifyCookie(cookies[i]) ? "Legal" : "Illegal"));
    } // public static void main(String[] args)

} // public class CookieTest

