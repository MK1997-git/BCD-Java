package BCDServletController;


	

import BCDServletModel.Calc;
import BCDServletModel.EnumMode;
import BCDServletModel.CookieManipulation;
import BCDServletView.View;
import java.io.IOException;
import java.util.ArrayList;
 
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/** 
 * Servlet which calculates the BCD formulae based on given by the user arguments, and displays those on another webpage. 
 * @author Maciej Kajstura 
 * @version 1.4.1
 **/
//@WebServlet(name = "BCDServlet", urlPatterns = "/BCDServlet")
public class BCDServlet extends HttpServlet {
    /** Object of model type used to calculate equations.**/
    private final Calc model = new Calc();
    //CookieManipulation CookieManipulation = new CookieManipulation();
    /** List of cookies stored by the servlet.**/
    private ArrayList<Cookie> ck = new ArrayList<Cookie>();
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
        String userName = request.getParameter("userName");
        int firstNum = Integer.valueOf(request.getParameter("firstNum"));
        int secondNum = Integer.valueOf(request.getParameter("secondNum")); 
        EnumMode.Mode currentMode =  EnumMode.Mode.intToEnum(Integer.valueOf(request.getParameter("Mode")));
        View view = new View(response);
        model.setInput(firstNum, 0);
        model.setInput(secondNum, 1);
        try {
            model.calculation(currentMode);
            
            String value = firstNum+modeToSymbol(currentMode)+secondNum+" = "+model.getResultInt();
            Cookie newCookie = CookieManipulation.valueAssigment(ck,userName,value);
            ck.add(newCookie);
            for(Cookie a:ck)
            {
                if(a.getName().equals(userName))response.addCookie(a);
            }
            view.printOutStart();
            view.printOutResults(model.getInputInt(0),model.getInputInt(1),model.getResultInt(),model.getInput(0),model.getInput(1),model.getResult(),currentMode);
            view.printOutEnd();
           
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
        String userName = request.getParameter("userName");
        int firstNum = Integer.valueOf(request.getParameter("firstNum"));
        int secondNum = Integer.valueOf(request.getParameter("secondNum")); 
        EnumMode.Mode currentMode =  EnumMode.Mode.intToEnum(Integer.valueOf(request.getParameter("Mode")));
        View view = new View(response);
        model.setInput(firstNum, 0);
        model.setInput(secondNum, 1);
        try {
            model.calculation(currentMode);
            
            String value = firstNum+modeToSymbol(currentMode)+secondNum+" = "+model.getResultInt();
            Cookie newCookie = CookieManipulation.valueAssigment(ck,userName,value);
            ck.add(newCookie);
            for(Cookie a:ck)
            {
                if(a.getName().equals(userName))response.addCookie(a);
            }
            view.printOutStart();
            view.printOutResults(model.getInputInt(0),model.getInputInt(1),model.getResultInt(),model.getInput(0),model.getInput(1),model.getResult(),currentMode);
            view.printOutEnd();
           
        } catch (Exception e) {
            response.sendRedirect("index.jsp");
        }
    }
    private String modeToSymbol(EnumMode.Mode mode)
    {
        switch (mode)
        {
            case Addition:
                return " + ";
            case Substraction:
                return " - ";
            case Multiplication:
                return " * ";
        }
       return "ERROR"; 
    }

}