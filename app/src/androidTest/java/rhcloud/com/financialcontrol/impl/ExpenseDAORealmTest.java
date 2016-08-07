package rhcloud.com.financialcontrol.impl;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import rhcloud.com.droidutils.tabutil.tabutil.interfaces.Consumer;
import rhcloud.com.financialcontrol.javabean.Expense;
import rhcloud.com.financialcontrol.javabean.ExpenseOption;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * @version 1.0
 * @since 1.0
 */
@RunWith(AndroidJUnit4.class)
public class ExpenseDAORealmTest{

    private ExpenseDAORealm expenseDAO;

    private Expense movie = new Expense(0, "10", "Movie", ExpenseOption.MOVIE);

    public ExpenseDAORealmTest(){
        expenseDAO = new ExpenseDAORealm(InstrumentationRegistry.getTargetContext());
    }

    @Test
    public void addExpense()  {
        Expense expense = new Expense(0, "123", "oi", ExpenseOption.ETC);
        expenseDAO.addExpense(expense);
        assertTrue(expenseDAO.getExpenseById(expense.getIdExpense()).getValue().contentEquals("123"));
    }

    @Test(expected = NullPointerException.class)
    public void addNullExpense()  {
        expenseDAO.addExpense(null);
    }

    @Test
    public void removeById()  {
        expenseDAO.setConsumer(new Consumer() {
            @Override
            public void onDataChange() {
                assertNull(expenseDAO.getExpenseById(movie.getIdExpense()));
            }
        });
        expenseDAO.addExpense(movie);
        expenseDAO.removeById(movie.getIdExpense());

    }

    @Test(expected = NullPointerException.class)
    public void removeNullExpense()  {
        expenseDAO.removeExpense(null);
    }

    @Test
    public void removeExpense()  {
        expenseDAO.setConsumer(new Consumer() {
            @Override
            public void onDataChange() {
                assertNull(expenseDAO.getExpenseById(movie.getIdExpense()));
                assertEquals(3, expenseDAO.getExpenseList().size());
            }
        });
        expenseDAO.addExpense(movie);
        expenseDAO.removeExpense(movie);
    }

    @Test
    public void updateExpense()  {
        expenseDAO.setConsumer(new Consumer() {
            @Override
            public void onDataChange() {
                assertTrue(expenseDAO.getExpenseById(movie.getIdExpense()).getValue().contentEquals("20"));
            }
        });
        expenseDAO.addExpense(movie);
        movie.setValue("20");
        expenseDAO.updateExpense(movie);
    }

    @Test(expected = NullPointerException.class)
    public void updateNullExpense()  {
        expenseDAO.updateExpense(null);
    }

}