package rhcloud.com.financialcontrol;

import android.app.Application;

import rhcloud.com.financialcontrol.dao.ExpenseDAO;
import rhcloud.com.financialcontrol.impl.ExpenseDAOTestImpl;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 *
 * @since 1.0
 * @version 1.0
 */
public class FinancialApplication extends Application {

    private ExpenseDAO expenseDAO;

    @Override
    public void onCreate() {
        super.onCreate();
        this.expenseDAO = new ExpenseDAOTestImpl();
    }

    public ExpenseDAO getExpenseDAO() {
        return expenseDAO;
    }

    public void setExpenseDAO(ExpenseDAO expenseDAO) {
        this.expenseDAO = expenseDAO;
    }
}
