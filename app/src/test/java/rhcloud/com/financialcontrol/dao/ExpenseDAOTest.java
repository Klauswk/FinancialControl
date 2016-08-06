package rhcloud.com.financialcontrol.dao;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import rhcloud.com.financialcontrol.impl.ExpenseDAOTestImpl;
import rhcloud.com.financialcontrol.javabean.Expense;
import rhcloud.com.financialcontrol.javabean.ExpenseOption;

import static org.junit.Assert.*;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 *
 * @since 1.0
 * @version 1.0
 */
public class ExpenseDAOTest {

    private ExpenseDAO expenseDAO;
    private ArrayList<Expense> expenses = new ArrayList<Expense>();
    private Expense movie = new Expense(0, "10", "Movie", ExpenseOption.MOVIE);

    @Before
    public void remakeData(){
        expenses.clear();
        expenses.add(movie);
        expenses.add(new Expense(1,"25.15","Lunch",ExpenseOption.FOOD));
        expenses.add(new Expense(2,"180.80","Market",ExpenseOption.FOOD));
        expenses.add(new Expense(3,"3.40","Bus",ExpenseOption.TRANSPORT));
        expenseDAO = new ExpenseDAOTestImpl(expenses);
    }

    @Test
    public void addExpense()  {
        expenseDAO.addExpense(new Expense(0,"123","oi", ExpenseOption.ETC));
        assertTrue(expenseDAO.getExpenseById(4).getValue().contentEquals("123"));
    }

    @Test(expected = NullPointerException.class)
    public void addNullExpense()  {
        expenseDAO.addExpense(null);
    }

    @Test
    public void removeById()  {
        expenseDAO.removeById(4);
        assertNull(expenseDAO.getExpenseById(4));
    }

    @Test(expected = NullPointerException.class)
    public void removeNullExpense()  {
        expenseDAO.removeExpense(null);
    }

    @Test
    public void removeExpense()  {
        expenseDAO.removeExpense(movie);
        assertNull(expenseDAO.getExpenseById(movie.getIdExpense()));
        assertEquals(3, expenseDAO.getExpenseList().size());
    }

    @Test
    public void updateExpense()  {
        Expense movie2 = new Expense(0, "20", "Movie", ExpenseOption.MOVIE);
        expenseDAO.updateExpense(movie2);
        assertTrue(expenseDAO.getExpenseById(0).getValue().contentEquals("20"));
    }

    @Test(expected = NullPointerException.class)
    public void updateNullExpense()  {
        expenseDAO.updateExpense(null);
    }
}