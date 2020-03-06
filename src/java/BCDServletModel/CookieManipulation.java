/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BCDServletModel;

import java.util.ArrayList;
import javax.servlet.http.Cookie;


/** 
 * Class which role is to handle various operation on cookies. 
 * @author Maciej Kajstura 
 * @version 1.4.1
 **/
public class CookieManipulation {
    /**
     * Function which creates a valid cookie based on previous entries in the list of cookies.
     * @param cookieBatch List of previously received cookies.
     * @param userName Username of the current user.
     * @param value Operation which the user has sent.
     * @return A new valid cookie.
     */
    public static Cookie valueAssigment(ArrayList<Cookie> cookieBatch, String userName, String value)
    {
        for(Cookie a:cookieBatch)
        {
            if(a.getName().equals(userName))
            {
                return new Cookie(userName,a.getValue()+", "+value);
            }
        }
        return new Cookie(userName,value);
    }
}
