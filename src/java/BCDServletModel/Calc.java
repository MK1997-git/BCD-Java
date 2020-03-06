/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BCDServletModel;

//import bcdservermodel.NegativeNumberException;
import java.util.ArrayList;


/**
 * The class which serves as the "model" branch of the MCV design program
 * @author Maciej Kajstura 
 * @version 1.4.1
 **/
public class Calc { 
    /**First number in the BCD from - represented as a 2D array, composed of n 4-int long arrays, where n is the amount of numbers in the first input **/
    private ArrayList<int[]>firstNum= new ArrayList<>(); 
    /** Second number in the BCD from - represented as a 2D array, composed of n 4-int long arrays, where n is the amount of numbers in the second input **/
    private ArrayList<int[]>secondNum= new ArrayList<>();
    /** Result in the BCD from - represented as a 2D array, composed of n 4-int long arrays, where n is the amount of numbers in the result **/
    private ArrayList<int[]>result= new ArrayList<>(); //result in the BCD from - represented as a 2D array, composed of n 4-int long arrays, where n is the amount of numbers in the result
    /** Integers representing first input, second input, and the result in a not-BCD form. **/
    private int firstInt, secondInt, resultInt; //Integers representing first input, second input, and the result in a not-BCD form.
    /** A method calculating how long is specified int value - for example, 129 is three symbols long. Takes one int argument, and return its' length as a int.
     * @param number A number which length is calculated.
     * @return The length of the above number.
     **/
    private int length(int number){ //A method calculating how long is specified int value - for example, 129 is three symbols long. Takes one int argument, and return its' length as a int.
        int length=0;
        while(number!=0){
            number/=10;
            length++;
        }
        return length;
    }
    /** 
     *  A method returning one symbol from an int value. It takes three argumets - number, position of the seeked symbol, and the length of that word. For example, for the inputs (129,1,3) it will return "2".  
     * @param number Number which part program wants to obtain.
     * @param position Posistion of the symbol program wants to obtain.
     * @param length Length of the number.
     * @return pos-th symbol in the number.
     **/
    private int getOnePart(int number, int position, int length){//A method returning one symbol from an int value. It takes three argumets - number, position of the seeked symbol, and the length of that word. For example, for the inputs (129,1,3) it will return "2". 
        int Temporary, Order_Of_Magnitude;
        for(int i=0;i<position;i++){
            Order_Of_Magnitude=length-i-1;
            Temporary=(int) (number/Math.pow(10, Order_Of_Magnitude));
            number-=Temporary*Math.pow(10, Order_Of_Magnitude);
        }
        for(int i=0;i<length-position-1;i++){
            number/=10;
        }
        return number;
    }
    /** Converts one-digit number to BCD value. Takes one argument - the number to be converted - and return a 4-long int array representing said number in BCD code. Uses private int getOnePart(int number, int position, int length) method.
     * @param number A one digit int value program wants to convert to BCD form.
     * @return 4 element int array of a input data in the BCD form.
     **/
    private int[] convertPartToBCD(int number){ //Converts one-digit number to BCD value. Takes one argument - the number to be converted - and return a 4-long int array representing said number in BCD code. Uses private int Get_One_Part(int number, int position, int length) method.
        int currentIndex = 3, Value;
        int BCDNumber[] = new int[4];
        while(number!=0)
        {
            Value = (int) Math.pow(2, currentIndex);
            if(number>=Value)
            {
                number-=Value;
                BCDNumber[3-currentIndex]=1;
            }
            else BCDNumber[3-currentIndex]=0; 
            currentIndex--;
        }
        return BCDNumber;
    }
      /** Converts a number to its' BCD value. takes in one int as a argument, and returns array of array representing its' BCD code. Uses private int length(int number) and  private int[] convertToBCD(int number) methods.
       * @param number A none or multiple digit int value program wants to convert to BCD form.
       * @return An ArrayList of int arrays, describing the input number in the BCD form.
       **/
     private ArrayList<int[]> convertToBCD(int number){ //Converts a number to its' BCD value. takes in one int as a argument, and returns array of array representing its' BCD code. Uses private int length(int number) and  private int[] Convert_Part_To_BCD(int number) methods.
         int length = length(number);
         int Temporary=number;
         ArrayList<int[]> List = new ArrayList<>();
         for(int i=length-1;i>=0;i--){
            List.add(convertPartToBCD(getOnePart(Temporary,i,length)));
        }
        return List;
     }
     /** Converts one piece of BCD code back to the int value. Takes is one array representing BCD value of a number, and returns it in a integer form. 
      * @param BCDNumber An array of integer values, which is gonig to be converted back into one int value.
      * @return An integer value of an input BCD array.
      **/
     private int convertOnePartFromBCD(int[] BCDNumber){//Converts one piece of BCD code back to the int value. Takes is one array representing BCD value of a number, and returns it in a integer form.
         int value = 8;
         int returnValue=0;
         for(int i=0;i<4;i++)
         {
             if(BCDNumber[i]==1)returnValue+=value;
             value/=2;
         }
         return returnValue;
     }
     /** Converts one number from its' BCD from to an integer form, and returns the result.
      * @param BCDNumber An ArrayList of integer arrays, which is gonig to be converted back into one int value.
      *  @return An integer value of an input BCD ArrayList.
      **/
      private int convertFromBCD(ArrayList<int[]> BCDNumber){// Converts one number from its' BCD from to an integer form, and return the result.
         int returnValue=0, currentSum;
         for(int i=0;i<BCDNumber.size();i++)
         {
             currentSum=0;
             for(int j=0;j<4;j++)
             {
                 if(BCDNumber.get(i)[j]==1)
                 {
                     currentSum+=Math.pow(2,3-j);                 
                 }
                 
             }
             returnValue+=currentSum*Math.pow(10,i);
         }
         return returnValue;
     }
      /**
       * Method which role is to check whenever the value oveflows the integer range.
       * @param BCDNumber A number which will be checked for overflowing the integer range.
       * @return True if the value overflows, false if it does not.
       */
      private boolean checkForOveflow(ArrayList<int[]> BCDNumber)
      {
          long test = (long)convertFromBCD(BCDNumber);
          return test>Integer.MAX_VALUE;
      }
      /** A default contructor of Calc. Takes in no arguments, and sets the values of variables to zeroes. Uses convertToBCD(int number). **/
     public Calc(){// A default contructor of Calc. Takes in no arguments, and sets the values of variables to zeroes. Uses Convert_To_BCD(int number).
         firstInt=0;
         secondInt=0;
         resultInt=0;
         firstNum = convertToBCD(firstInt);
         secondNum = convertToBCD(secondInt);
         result = convertToBCD(0);
     }
     /** A  contructor of Calc taking two arguments. Sets the values of variables to the taken values, while checking if those are positive numbers. Uses convertToBCD(int number). 
      * @param firstInt_ First value in the future calclulations.
      * @param secondInt_ Second value in the future calclulation.
      **/
    public Calc(int firstInt_, int secondInt_){ // A  contructor of Calc taking two arguments. Sets the values of variables to the taken values, while checking if those are positive numbers. Uses convertToBCD(int number)..
        firstInt=firstInt_;
        secondInt=secondInt_;
        resultInt=0;
        if(firstInt<0||secondInt<0){
            firstInt = 0;
            secondInt = 0;
            throw new NegativeNumberException("One of the variables is a negative number.");
        }
        firstNum = convertToBCD(firstInt);
        secondNum = convertToBCD(secondInt);
        if(checkForOveflow(firstNum)||checkForOveflow(secondNum))
        {
            firstInt = 0;
            secondInt = 0;
            firstNum = convertToBCD(firstInt);
            secondNum = convertToBCD(secondInt);
            throw new NegativeNumberException("One of the variables is too big, and would overflow into the negative range.");
        }
        result = convertToBCD(0);
    }
    /** Sets the value of one variable to given value. Takes in two values, respectively the future value, and the variable its' supposed to replace. Uses convertToBCD(int number).
     * @param value Value which will be saved in the Calc object.
     * @param index Index of the spot it will be saved in.
     **/
    public void setInput(int value, int index){//Sets the value of one variable to given value. Takes in two values, respectively the future value, and the variable its' supposed to replace. Uses Convert_To_BCD(int number).
        if(value<0)
        {
            throw new NegativeNumberException("Given variable is a negative number.");       
        }
        ArrayList <int[]> tmp = convertToBCD(value);
        if(checkForOveflow(tmp))
        {
            throw new NegativeNumberException("Given variable is too big, and would overflow into the negative range.");  
        }
        else {
            if(index==0){
                firstInt = value;
                firstNum = tmp;
            }
            else{
                secondInt = value;
                secondNum = tmp;
            }
        }
    }
    /** Returns the [index] variable in the integer form.
     * @param index Index of the element program is looking for.
     * @return Integer value of mentioned element.
     **/
    public int getInputInt(int index){// Returns the [index] variable in the integer form.
        if(index==0){
            return firstInt;
        }
        else{
            return secondInt;
        }
    }
    /** Return the result in the integer form.
     * @return The calculation result in it's integer form.
     **/
    public int getResultInt(){// Return the result in the integer form.
        return resultInt;
    }
    /** Returns the [index] variable in the BCD form. 
     * @param index Index of the element program is looking for.
     * @return An ArrayList of integer arrays which represent mentioned above element in it's BCD form.
     **/
    public ArrayList<int[]> getInput(int index){//Returns the [index] variable in the BCD form.
        if(index==0){
            return firstNum;
        }
        else{
            return secondNum;
        }
    }
    /** Returns the [index] variable in the BCD form.
     * @return An ArrayList of integer arrays which represent the result in it's BCD form.
     **/
    public ArrayList<int[]> getResult(){//Returns the [index] variable in the BCD form.
        return result;
    }
    /** Return the minimal value from two integer arguments. 
     * @param a First of two integer values which will be evaluated.
     * @param b Second of two integer values which will be evaluated.
     * @result The integer value with lower value.
     **/
    private int min(int a, int b)//Return the minimal value from two integer arguments.
    {
        if (a>=b)return b;
        else return a;
        
    }
    /** Return the maximal value from two integer arguments.
     * @param a First of two integer values which will be evaluated.
     * @param b Second of two integer values which will be evaluated.
     * @return The integer value with higher value.
     */
    private int max(int a, int b)//Return the max value from two integer arguments.
    {
        if (a>=b)return a;
        else return b;
        
    }
    /** Shallow copies a BCD number, and then returns it.
     * @param orgList ArrayList which will be copied.
     * @return A new shallow copy of the orgList.
     **/
    private ArrayList<int[]> copy(ArrayList<int[]> orgList) //Shallow copies a BCD number, and then returns it.
    {
        ArrayList<int[]> newArray= new ArrayList<>();
        for(int i=0;i<orgList.size();i++){
            int[] temp = {0,0,0,0};
            System.arraycopy(orgList.get(i), 0, temp, 0, 4);
            newArray.add(temp);
        }
        return newArray;
    }
    /** Initializes int[length][] array, and fills it with zeroes, after which it returns it.
     * @param length The length which the new array is supposed to hold.
     * @return Newly created ArrayList filled with integer arrays {0,0,0,0}.
     **/
    private ArrayList<int[]> initializeResult(int length) //Initializes int[length][] array, and fills it with zeroes, after which it returns it.
    {
        ArrayList<int[]>  newArray = new ArrayList<>();
        for(int i=0;i<length;i++)
        {
            newArray.add(new int[] {0,0,0,0});
        }
        return newArray;
        
        
    } 
    /** Adds two numbers in BCD form. Takes in three arguments - two BCD numbers, and a flag which role is to state if the lefotver of the addition matters in this case. Returns the result in it's BCD form. Uses convertOnePartFromBCD, min, max, and initializeResult methods.
     * @param a The first of the ArrayList of int[] values which will be added in it's BCD form.
     * @param b The second of the ArrayList of int[] values which will be added in it's BCD form.
     * @param care A marker representing if it's the original add calc, or if the current occurence of the function is inside other instance of add. Helpful when determining overflows and such.
     * @return A ArrayList of int[] representing the addition result in it's BCD form.
     **/
    private ArrayList<int[]> add(ArrayList<int[]> a, ArrayList<int[]> b, int care){//Adds two numbers in BCD form. Takes in three arguments - two BCD numbers, and a flag which role is to state if the lefotver of the addition matters in this case. Returns the result in it's BCD form. Uses Convert_One_Part_From_BCD, min,max, and Initialize_results methods.
        int leftover = 0;
        int check;
        int length = min(a.size(),b.size());
        ArrayList<int[]> newArray = initializeResult(max(a.size(),b.size()));
        for(int i=0;i<length;i++){
            int startingLeftover = leftover;
            for(int j=3;j>=0;j--){
                newArray.get(i)[j] +=  a.get(i)[j]+b.get(i)[j]+ leftover;
                if( newArray.get(i)[j] > 1){
                    int[] NewIReducedByTwo = newArray.get(i);
                    NewIReducedByTwo[j]-=2;
                    newArray.set(i,NewIReducedByTwo);
                    leftover=1;
                }
                else {
                    leftover = 0;
                }
            }
            check = convertOnePartFromBCD(newArray.get(i));
            if((check>9&&care==1)||(check==0&&startingLeftover==1))
            {
                ArrayList<int[]> six2D = new ArrayList<>();
                six2D.add(new int[] {0,1,1,0});
                ArrayList<int[]> resultI = new ArrayList<>();
                resultI.add(newArray.get(i));
                resultI=add(resultI, six2D, 0);
                newArray.set(i,resultI.get(0)); 
                leftover=1;
            }
            if(leftover == 1){
                if(i==length-1 &&care==1&&a.size()==b.size())
                {
                    newArray.add(new int[] {0,0,0,1});
                    leftover = 0;
                }
                /*else{
                ArrayList<int[]> One_2D = new ArrayList<>();
                One_2D.add(new int[] {0,0,0,1});
                ArrayList<int[]> resultI = new ArrayList<>();
                resultI.add(New.get(i));
                //I'm so tired.
                resultI=Add(resultI, One_2D, 0);
                New.set(i,resultI.get(0)); 
                Leftover=0;
                    
                }*/
            }
        }
        for(int i=length;i<max(a.size(),b.size());i++){
                if(a.size()>b.size()){
                   newArray.set(i,a.get(i));
                }
                else {
                    newArray.set(i,b.get(i));
                }
                if(leftover == 1){
                    ArrayList<int[]> one2D = new ArrayList<>();
                    one2D.add(new int[] {0,0,0,1});
                    ArrayList<int[]> resultI = new ArrayList<>();
                    resultI.add(newArray.get(i));
                    resultI=add(resultI, one2D,0);
                    newArray.set(i,resultI.get(0));
                    leftover = 0;
            }
        }
        return newArray;
    }
    /** Creates a number which is a result of 9's compliments for the second var, and the it returns it. For example, 9's compliment for 23 is 99-23 = 76.
     * @return An integer representing the 9's compliment of the second value in this object.
     **/
    private int nineComplimentOfSecondNum(){//Creates a number which is a result of 9's compliments for the second var, and the it returns it. For example, 9's compliment for 23 is 99-23 = 76.
        int temp = 0;
        for(int i=0;i<secondNum.size();i++){
            temp+=Math.pow(10,i);
        }
        temp*=9;
        return temp-secondInt;
    }
    /** Checks if the first number in its BCD form is bigger than the second one.
     * @param first The first ArrayList of int arrays which will be compared.
     * @param second The second ArrayList of int arrays which will be compared.
     * @return Boolean value == true if first>second, otherwise false.
     **/
    private boolean checkIfValidForSubstraction(ArrayList<int[]> first,ArrayList<int[]> second)
    {
        if(first.size()>second.size())return true;
        else if(first.size()<second.size())return false;
        else{
            for(int i=0;i<first.size();i++)
            {
                @SuppressWarnings({"unchecked"})
                int firstInt=convertOnePartFromBCD(first.get(i));
                int secondInt=convertOnePartFromBCD(second.get(i));
                if(firstInt>secondInt)return true;
                else if(firstInt<secondInt)return false;
            }
        }
        return false;
    }
    /** Subtracts two of the saved vars, and returns the result. Uses convertToBCD and nineComplimentOfSecondNum methods.
     * @return A ArrayList of int[] representing the substraction result in it's BCD form.
     **/
    private ArrayList<int[]> subtract(){//Subtracts two of the saved vars, and returns the result. Uses convertToBCD method.
        if(!checkIfValidForSubstraction(firstNum,secondNum))throw new NegativeNumberException("Substraction would return a negative number");
        int newInt = firstInt+nineComplimentOfSecondNum();
        //int Temporary=(int) (New_Int/Math.pow(10, (int) (Math.log10(New_Int))));
        int temporary= (int)Math.pow(10, (int) (Math.log10(newInt)));
        newInt-=temporary;
        newInt++;
        ArrayList<int[]> New = convertToBCD(newInt);
        return New;
    }
    /** Multiplies two of the saved vars, and returns the result. Uses the Add method. 
     * @return A ArrayList of int[] representing the multiplication result in it's BCD form.
     **/
    private ArrayList<int[]> multiply(){//Multiplies two of the saved vars, and returns the result. Uses the Add method.
        ArrayList<int[]> newInt = copy(firstNum);
        for(int i=1;i<secondInt;i++)
        {
            newInt = add(newInt,firstNum,1);
        }
        return newInt;
    }
    /** Calculates two of the saved vars, and returns the result. The type of the calculation is chosen by the argument "mode". Uses the convertFromBCD method, and NegativeNumberException exception.
     * @param mode The type of operation conducted.
     * @return A ArrayList of int[] representing the operation result in it's BCD form.
     **/
    public ArrayList<int[]> calculation(EnumMode.Mode mode){//Calculates two of the saved vars, and returns the result. The type of the calculation is chosen by the argument "mode". Uses the Convert_From_BCD method, and negativenumberException exception.
        switch (mode) {
            case Addition:
                result = add(firstNum,secondNum,1);
                break;
            case Substraction:
                try{
                result = subtract();
                break;
                }catch(Exception e)
                {
                    throw new NegativeNumberException("Substraction would return a negative number");
                }
            case Multiplication:
                result = multiply();
                break;
            default:
                result = add(firstNum,secondNum,1);
                break;
        }

        resultInt = convertFromBCD(result);
        return result;
    }
}
