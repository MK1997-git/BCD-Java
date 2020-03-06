/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BCDServletModel;


/** 
 * A enum type responsible for listing all possible types of calculation 
 * @author Maciej Kajstura 
 * @version 1.4.1
 **/
public class EnumMode {
    /** Enum value which holds information about the mode we want to use on our numbers. */
   public enum Mode {
       /**Indicates Addition mode. */
        Addition(0),
        /**Indicates Substraction mode. */
        Substraction(1),
        /**Indicates Multiplication mode. */
        Multiplication(2);
        /** The integer value every Enum_Mode holds. **/
        int Int_Representation;
       /**  
        * Constructor which takes one int value representing the int form of said enum.
        * @param intRepresentation_ Value of intRepresentation in the new object.
        **/
       private Mode(int intRepresentation_) {
            Int_Representation = intRepresentation_;
        }
       /** Translates an integer argument into Enum_Mode =.
        * @param i Int value which will be converted into mode value.
        * @return The mode value based off the input.
        **/
       public static Mode intToEnum(int i)
       {
           switch(i)
           {
               case 0:
                   return Addition;
                case 1:
                   return Substraction;
                case 2:
                   return Multiplication;
           }
            return Addition;
           
        }
   }
    
}
