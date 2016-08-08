package rhcloud.com.financialcontrol;

import android.app.Application;

import rhcloud.com.financialcontrol.dao.ExpenseDAO;
import rhcloud.com.financialcontrol.di.component.DaggerExpenseComponent;
import rhcloud.com.financialcontrol.di.component.ExpenseComponent;
import rhcloud.com.financialcontrol.di.module.ExpenseModule;
import rhcloud.com.financialcontrol.impl.ExpenseDAORealm;
import rhcloud.com.financialcontrol.impl.ExpenseDAOTestImpl;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 *
 * Default {@link Application} for this App.
 *
 * @since 1.0
 * @version 1.0
 * @see <a href="https://developer.android.com/reference/android/app/Application.html">Application</a>
 */
public class FinancialApplication extends Application {

    /**
     * The default {@link ExpenseDAO} used in the App.
     * @since 1.0
     */
    private ExpenseDAO expenseDAO;

    /**
     * The component to be expose for dagger
     *
     * @see <a href="https://google.github.io/dagger/api/latest/dagger/Component.html">Dagger component</a>
     */
    private ExpenseComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        this.expenseDAO = new ExpenseDAOTestImpl();
        //this.expenseDAO = new ExpenseDAORealm(getApplicationContext());
        component = DaggerExpenseComponent.builder().expenseModule(new ExpenseModule()).build();
    }

    public ExpenseDAO getExpenseDAO() {
        return expenseDAO;
    }

    public void setExpenseDAO(ExpenseDAO expenseDAO) {
        this.expenseDAO = expenseDAO;
    }

    public ExpenseComponent getComponent() {
        return component;
    }

    public void setComponent(ExpenseComponent component) {
        this.component = component;
    }
}
