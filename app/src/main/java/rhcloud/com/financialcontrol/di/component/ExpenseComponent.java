package rhcloud.com.financialcontrol.di.component;

import javax.inject.Singleton;

import dagger.Component;
import rhcloud.com.financialcontrol.di.module.ExpenseModule;
import rhcloud.com.financialcontrol.fragment.ExpenseListFragment;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * @version 1.0
 * @since 1.0
 */

@Singleton
@Component(modules = {ExpenseModule.class})
public interface ExpenseComponent {

    public void inject(ExpenseListFragment expenseListFragment);
}
