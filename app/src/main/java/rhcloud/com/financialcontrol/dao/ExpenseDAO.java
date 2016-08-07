package rhcloud.com.financialcontrol.dao;

import java.util.List;

import rhcloud.com.financialcontrol.javabean.Expense;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 *
 * Define the verbs needed to access and modify the {@link Expense} object.
 *
 * @since 1.0
 * @version 1.0
 */
public interface ExpenseDAO {

    public void addExpense(Expense expense);
    public void removeById(int expenseId);
    public void removeExpense(Expense expense);
    public void updateExpense(Expense expense);
    public List<Expense> getExpenseList();
    public Expense getExpenseById(int id);
}
