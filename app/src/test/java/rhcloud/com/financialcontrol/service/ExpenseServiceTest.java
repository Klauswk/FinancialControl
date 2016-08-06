package rhcloud.com.financialcontrol.service;

import org.junit.Before;
import org.junit.Test;

import rhcloud.com.financialcontrol.dao.ExpenseDAO;
import rhcloud.com.financialcontrol.impl.ExpenseDAOTestImpl;
import rhcloud.com.financialcontrol.impl.ExpenseServiceTestImpl;
import rhcloud.com.financialcontrol.javabean.Expense;
import rhcloud.com.financialcontrol.javabean.ExpenseOption;

import static org.junit.Assert.*;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * @version 1.0
 * @since 1.0
 */
public class ExpenseServiceTest {

    private ExpenseDAO expenseDAO;
    private ExpenseService expenseService;
    private Expense test;

    @Before
    public void setUp(){
        expenseDAO = new ExpenseDAOTestImpl();
        expenseService = new ExpenseServiceTestImpl(expenseDAO);
        test = new Expense(0,"123","Test value",ExpenseOption.ETC);

    }

    @Test
    public void testTotalExpenseValue(){
        assertEquals(Double.valueOf(219.35),expenseService.getTotalExpenses());
    }

    @Test
    public void testTotalExpenseByMovie(){
        assertEquals(Double.valueOf(10),expenseService.getTotalExpensesByType(ExpenseOption.MOVIE));
    }

    @Test
    public void testTotalExpenseByTransport(){
        assertEquals(Double.valueOf(3.39),expenseService.getTotalExpensesByType(ExpenseOption.TRANSPORT));
    }

    @Test
    public void testTotalExpenseByFood(){
        assertEquals(Double.valueOf(205.95),expenseService.getTotalExpensesByType(ExpenseOption.FOOD));
    }

    @Test
    public void testTotalExpenseByEtc(){
        assertEquals(Double.valueOf(0),expenseService.getTotalExpensesByType(ExpenseOption.ETC));
    }

    @Test
    public void testTotalExpenseByNull(){
        assertEquals(Double.valueOf(0),expenseService.getTotalExpensesByType(null));
    }

    @Test
    public void testIncrementalExpenseInTotal(){
        expenseDAO.addExpense(test);
        Double newTotal = Double.valueOf(219.35) + Double.valueOf(test.getValue());
        assertEquals(newTotal,expenseService.getTotalExpenses());
        assertEquals(Double.valueOf(test.getValue()),expenseService.getTotalExpensesByType(ExpenseOption.ETC));
        expenseDAO.removeExpense(test);
        assertEquals(Double.valueOf(0),expenseService.getTotalExpensesByType(ExpenseOption.ETC));
        assertEquals(Double.valueOf(219.35),expenseService.getTotalExpenses());
    }

    @Test
    public void testUpdateExpenseInTotal(){
        expenseDAO.addExpense(test);
        Double newTotal = Double.valueOf(219.35) + Double.valueOf(test.getValue());
        assertEquals(newTotal,expenseService.getTotalExpenses());
        assertEquals(Double.valueOf(test.getValue()),expenseService.getTotalExpensesByType(ExpenseOption.ETC));
        test.setValue("22");
        expenseDAO.updateExpense(test);
        newTotal = Double.valueOf(219.35) + Double.valueOf(test.getValue());
        assertEquals(newTotal,expenseService.getTotalExpenses());
        assertEquals(Double.valueOf(test.getValue()),expenseService.getTotalExpensesByType(ExpenseOption.ETC));
        expenseDAO.removeExpense(test);
    }
}