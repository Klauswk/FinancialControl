package rhcloud.com.financialcontrol.dao;

import java.util.List;

import rhcloud.com.financialcontrol.javabean.Expense;

/**
 * Created by Developer on 21/07/2016.
 */

public interface ExpenseDAO {

    public void addExpense(Expense expense);
    public void removeById(int expenseId);
    public void removeExpense(Expense expense);
    public void updateExpense(Expense expense);
    public List<Expense> getExpenseList();
    public Expense getExpenseById(int id);
}
