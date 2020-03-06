/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BCDServletModel;


/** 
 * An exception which is thrown when it detects a negative input.
 * @author Maciej Kajstura 
 * @version 1.4.1
 **/
public class NegativeNumberException extends RuntimeException {//An exception which is thrown when it detects a negative input.
    /**
     * A method which is triggered upon triggering this exception.
     * @param errorMessage A text message which will be written on the screen.
     **/
    public NegativeNumberException(String errorMessage) {
        super(errorMessage);
    }
}
