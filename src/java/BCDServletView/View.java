/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BCDServletView;

import BCDServletModel.EnumMode;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;

/**
 * The class which serves as the "view" branch of the MCV design program. 
 * @author Maciej Kajstura 
 * @version 1.4.1
 */
public class View {
    /** PrintWriter type object, which allows the View to output the data on the webpage.**/
    private final PrintWriter writer;
    /**
     * Function which prints an BCD Number.
     * @param number BCD Number which will be printed.
     * @throws IOException 
     */
    private void printOutBCDNumber(ArrayList<int[]> number) throws IOException
    {
        for (int i=number.size()-1; i>=0;i--) {
            for(int digit: number.get(i)){
                writer.print(digit+" ");
            }
            writer.print(" ");
        }
    }
     /**
     * Function which prints the first part of the HTML webpage.
     */
    public void printOutStart()
    {
         writer.println("<!DOCTYPE html>");
         writer.println("<html>");
         writer.println("<head>");
         writer.println("<title>Result</title>");
         writer.println("<meta charset=\"UTF-8\">");
         writer.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
         writer.println("</head>");
         writer.println("<body>");
        
    }
    /**
     * Function which prints the second part of the HTML webpage, which contains the result of the calculation, its mode, and both arguments.
     * @param firstInt First argument in its int mode.
     * @param secondInt Second argument in its int mode.
     * @param thirdInt Result of the calculation in its int mode.
     * @param firstBCD First argument in its BCD mode.
     * @param secondBCD Second argument in its BCD mode.
     * @param thirdBCD Result of the calculation in its BCD mode.
     * @param currentMode The mode with which the result was calculated.
     * @throws IOException 
     */
    public void printOutResults(int firstInt, int secondInt, int thirdInt, ArrayList<int[]> firstBCD, ArrayList<int[]> secondBCD, ArrayList<int[]> thirdBCD, EnumMode.Mode currentMode) throws IOException
    {
            writer.print("First Argument = " + firstInt + ", in it's BCD form = ");
            printOutBCDNumber(firstBCD);
            writer.print("<br>");
            writer.print("Second Argument = " + secondInt + ", in it's BCD form = ");
            printOutBCDNumber(secondBCD);
            writer.print("<br>");
            writer.print("Calculation Mode: " + currentMode);
            writer.print("<br>");
            writer.print("Result = "+ thirdInt + ", in it's BCD form = ");
            printOutBCDNumber( thirdBCD);
            writer.print("<br>");   
    }
    /**
     * Function which prints the third and last part of the HTML webpage.
     */
    public void printOutEnd()
    {
         writer.println("<form name=\"BCDServletData\" action=\"BCDServletData\" method=\"POST\">");
         writer.println("<input type=\"submit\" value=\"Info about previous calculations\" name=\"find\"/>");
         writer.println("</form>");
         writer.println("</body>");
         writer.println("</html>");
        
    }
    /**
     * Constructor of the View object.
     * @param response Response from the first servlet.
     * @throws IOException 
     */
    public View(HttpServletResponse response) throws IOException
    {
        writer = response.getWriter();
    }
}
