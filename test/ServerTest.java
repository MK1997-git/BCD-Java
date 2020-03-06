
import BCDServletModel.NegativeNumberException;
import BCDServletModel.Calc;
import BCDServletModel.EnumMode;
import BCDServletModel.CookieManipulation;
//import static java.lang.Math.pow;
import java.util.ArrayList;
import java.util.function.Consumer;
import javax.servlet.http.Cookie;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
//import static org.junit.Assert.*;


/**
 * A JUNIT class which is repsonsible for conducting tests on the model methods.
 * @author Maciej Kajstura 
 * @version 1.4.1
 **/
public class ServerTest {
    /**A default contructor of the ModelTests class.**/
    public ServerTest() {
    }
    /**A deprecated method, which served to translate ArrayLists of int arrays into a singular array of ints.
     *@param storeVal An ArrayLists of int arrays.
     * @return An int array.
     **/
    @Deprecated
    private int[] rewriteIntoArrayFromBCD(ArrayList<int[]> storeVal)
    {
        ArrayList<Integer> achievedResultsArrayList = new ArrayList<>();
        copyElements(storeVal,achievedResultsArrayList);
        int[] achievedResults= new int[achievedResultsArrayList.size()];
        for(int i=0;i<achievedResultsArrayList.size();i++)
        {
            achievedResults[i]=achievedResultsArrayList.get(i);
        }
        return achievedResults;
        
    }
    /**A deprecated method, which served to translate ArrayLists of integer values into a singular array of ints.
     *@param storeVal An ArrayLists of int values.
     * @return An int array.
     **/
    private int[] rewriteIntoArray(ArrayList<Integer> achievedResultsArrayList)
    {
        int[] achievedResults= new int[achievedResultsArrayList.size()];
        for(int i=0;i<achievedResultsArrayList.size();i++)
        {
            achievedResults[i]=achievedResultsArrayList.get(i);
        }
        return achievedResults;
        
    }
     /**A deprecated method, which served to translate ArrayLists of integer arrays into an ArrayList of integer values.
     *@param storeVal An ArrayLists of int arrays.
     *@param achievedResultsArrayList An ArrayList of integer values.
     **/
    private void copyElements(ArrayList<int[]> storeVal,ArrayList<Integer> achievedResultsArrayList)
    {
        storeVal.forEach(new Consumer<int[]>() {
            @Override
            public void accept(int[] tab) {
                for(int digit: tab)
                {
                    achievedResultsArrayList.add(digit);
                }
            }
        });
    }
    /* Welp.
    @Test
    public void getDataIntTest()
    {
        int[] correctResults = {2,3,5};
        Calc calcInstance = new Calc(2,3);
        calcInstance.calculation(Enum_Mode.Mode.Addition);
        int achievedResults[] = {calcInstance.getInputInt(0),calcInstance.getInputInt(1),calcInstance.getResultInt()};
        Assert.assertArrayEquals(achievedResults,correctResults);
    }

    @Test
     public void getDataBCDTest()
    {
        int[] correctResults = {0,0,1,0, 0,0,1,1,0,1,0,1};
        Calc calcInstance = new Calc(2,3);
        calcInstance.calculation(Enum_Mode.Mode.Addition);
        ArrayList<int[]> storeVal = new ArrayList<>();
        ArrayList<Integer> achievedResultsArrayList = new ArrayList<>();
        storeVal = calcInstance.getInput(0);
        copyElements(storeVal,achievedResultsArrayList);
        storeVal = calcInstance.getInput(1);
        copyElements(storeVal,achievedResultsArrayList);
        storeVal = calcInstance.getResult();
        copyElements(storeVal,achievedResultsArrayList);
        int[] achievedResults=rewriteIntoArray(achievedResultsArrayList);
        Assert.assertArrayEquals(achievedResults,correctResults);
    }
    
    @Test
    public void constructorTest()
    {
        Calc calcInstance = new Calc();
        calcInstance.calculation(Enum_Mode.Mode.Addition);
        Assert.assertEquals(0, calcInstance.getResultInt());
        
    }
    @Test
    public void constructorTest2()
    {
        Calc calcInstance = new Calc(8,1);
        calcInstance.calculation(Enum_Mode.Mode.Addition);
        Assert.assertEquals(9, calcInstance.getResultInt());
        
    }
    @Test
    public void constructorTest3()
    {
        Calc calcInstance = new Calc(17,96);
        calcInstance.calculation(Enum_Mode.Mode.Addition);
        Assert.assertEquals(113, calcInstance.getResultInt());
        
    }
    @Test(expected = NegativeNumberException.class)
    public void constructorTest4()
    {
        Calc calcInstance = new Calc(-2,5);
    }
    */
    /**A procedure testing addition in the instance of Calc class without any declared variables.**/
    @Test
    public void additionTest1()
    {
        Calc calcInstance = new Calc();
        calcInstance.calculation(EnumMode.Mode.Addition);
        Assert.assertEquals(0, calcInstance.getResultInt());
        
    }
    /**A procedure testing addition in the instance of Calc class with a pair of relatively simple values.**/
    @Test
    public void additionTest2()
    {
        Calc calcInstance = new Calc(2,3);
        calcInstance.calculation(EnumMode.Mode.Addition);
        Assert.assertEquals(5, calcInstance.getResultInt());
        
    }
     /**A procedure testing addition in the instance of Calc class with a pair of more complicated values.**/
    @Test
    public void additionTest3()
    {
        Calc calcInstance = new Calc(17,96);
        calcInstance.calculation(EnumMode.Mode.Addition);
        Assert.assertEquals(113, calcInstance.getResultInt());
        
    }
    /**A procedure testing substraction in the instance of Calc class with a pair of relatively simple values.**/
    @Test
    public void substractionTest1()
    {
        Calc calcInstance = new Calc(6,2);
        calcInstance.calculation(EnumMode.Mode.Substraction);
        Assert.assertEquals(4, calcInstance.getResultInt());
        
    }
    /**A procedure testing addition in the instance of Calc class with a pair of rvalues which would yield a negative result - will throw NegativeNumberException.**/
    @Test(expected = NegativeNumberException.class)
     public void substractionTest2()
    {
        Calc calcInstance = new Calc(2,6);
        calcInstance.calculation(EnumMode.Mode.Substraction);
    }
    /**A procedure testing multiplication in the instance of Calc class with a pair of relatively simple values.**/
    @Test
     public void multiplicationTest1()
    {
        Calc calcInstance = new Calc(5,10);
        calcInstance.calculation(EnumMode.Mode.Multiplication);
        Assert.assertEquals(50, calcInstance.getResultInt());
        
    }
    /**A procedure testing multiplication in the instance of Calc class where one value is equal zero.**/
    @Test
    public void multiplicationTest2()
    {
        Calc calcInstance = new Calc(0,5);
        calcInstance.calculation(EnumMode.Mode.Multiplication);
        Assert.assertEquals(0, calcInstance.getResultInt());
        
    }
    /*
    @Test
    public void SetInputTest1()
    {
        Calc calcInstance = new Calc();
        calcInstance.setInput(2, 0);
        Assert.assertEquals(2,calcInstance.getInputInt(0));
    }
    @Test
    public void SetInputTest2()
    {
        Calc calcInstance = new Calc();
        calcInstance.setInput(2, 1);
        Assert.assertEquals(2,calcInstance.getInputInt(1));
    }
    @Test
    public void SetInputTest3()
    {
        Calc calcInstance = new Calc();
        calcInstance.setInput(2, 3);
        Assert.assertEquals(2,calcInstance.getInputInt(3));
    }
    
    @Test(expected = NegativeNumberException.class)
    public void SetInputTest4()
    {
        Calc calcInstance = new Calc();
        calcInstance.setInput(-2, 3);
    }
    */
    /**A procedure testing Enum_Mode.Mode.intToEnum with a value which is one of the options in the switch tree of the method.**/
    @Test
    public void enumModeTest1()
    {
        Assert.assertEquals(EnumMode.Mode.Addition,EnumMode.Mode.intToEnum(0));
    }
    /**A procedure testing Enum_Mode.Mode.intToEnum with a value which isn't one of the options in the switch tree of the method.**/
    @Test
    public void enumModeTest2()
    {
        Assert.assertEquals(EnumMode.Mode.Addition,EnumMode.Mode.intToEnum(4));
    }
    /**A procedure testing CookieManipulation.valueAssigment with one cookie from one user being created.**/
    @Test
    public void cookieManipulationTest1()
    {
        ArrayList<Cookie> cookieBatch = new ArrayList<>();
        Cookie testCookie = CookieManipulation.valueAssigment(cookieBatch, "user","9+10 = 19");
        Assert.assertEquals("9+10 = 19",testCookie.getValue());
    }
     /**A procedure testing CookieManipulation.valueAssigment with two cookies from two users being created.**/
    @Test
    public void cookieManipulationTest2()
    {
       ArrayList<Cookie> cookieBatch = new ArrayList<>();
       CookieManipulation.valueAssigment(cookieBatch, "user","9+10 = 19");
       CookieManipulation.valueAssigment(cookieBatch, "user2","2*10 = 20");
       Cookie testCookie = CookieManipulation.valueAssigment(cookieBatch, "user","4-2 = 2");
       Assert.assertEquals("4-2 = 2",testCookie.getValue());
    }
    /**Does nothing.**/
    @BeforeClass
    public static void setUpClass() {
    }
     /**Does nothing.**/
    @AfterClass
    public static void tearDownClass() {
    }
     /**Does nothing.**/
    @Before
    public void setUp() {
    }
     /**Does nothing.**/
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
