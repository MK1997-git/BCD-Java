package BCDServletController;


	

import BCDServletModel.Calc;
import BCDServletModel.EnumMode;
import BCDServletView.View;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.servlet.RequestDispatcher;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/** 
 * Servlet which prints out the past calculations of the current user. 
 * @author Maciej Kajstura 
 * @version 1.4.1
 **/
//@WebServlet(name = "Controller", urlPatterns = "/calculateServlet")
public class BCDServletData extends HttpServlet {
   //private Calc model = new Calc();
    /** List of cookies stored by the servlet.**/
    private final ArrayList<Cookie> ck = new ArrayList<>();
     /**
     * Function which is triggered when the servlet is enabled using POST method.
     * @param request An object which sends the client information to the servlet.
     * @param response An object which sends the servlet information to the client.
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest request, 
      HttpServletResponse response)
      throws ServletException, IOException {
        Cookie [] cookiesBatch = request.getCookies();
        String userName = cookiesBatch[cookiesBatch.length-1].getName();
        try {
            PrintWriter writer = response.getWriter();
            for(Cookie a:cookiesBatch)
            {
                if(a.getName().equals(userName))writer.println(a.getName()+ " " + a.getValue());
            }
            
           
        } catch (Exception e) {
            response.sendRedirect("index.jsp");
        }


    }
        /**
     * Function which is triggered when the servlet is enabled using GET method.
     * @param request An object which sends the client information to the servlet.
     * @param response An object which sends the servlet information to the client.
     * @throws ServletException
     * @throws IOException 
     */
    @Override
     protected void doGet(HttpServletRequest request, 
      HttpServletResponse response)
      throws ServletException, IOException {
        Cookie [] cookiesBatch = request.getCookies();
        String userName = cookiesBatch[cookiesBatch.length-1].getName();
        try {
            PrintWriter writer = response.getWriter();
            for(Cookie a:cookiesBatch)
            {
                if(a.getName().equals(userName))writer.println(a.getName()+ " " + a.getValue());
            }
            
           
        } catch (Exception e) {
            response.sendRedirect("index.jsp");
        }


    }
}