package rhcloud.com.financialcontrol.di.module;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import rhcloud.com.financialcontrol.dao.ExpenseDAO;
import rhcloud.com.financialcontrol.impl.ExpenseDAOTestImpl;
import rhcloud.com.financialcontrol.impl.ExpenseServiceImpl;
import rhcloud.com.financialcontrol.service.ExpenseService;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * @version 1.0
 * @since 1.0
 */

@Module
public class ExpenseModule {

    @Provides
    public ExpenseDAO provideExpenseDAO(){
        return new ExpenseDAOTestImpl();
    }

    @Provides
    @Inject
    public ExpenseService provideExpenseService(ExpenseDAO expenseDAO){
        return new ExpenseServiceImpl(expenseDAO);
    }
}
