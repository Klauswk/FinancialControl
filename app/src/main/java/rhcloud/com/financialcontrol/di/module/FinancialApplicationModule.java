package rhcloud.com.financialcontrol.di.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import rhcloud.com.financialcontrol.FinancialApplication;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * @version 1.0
 * @since 1.0
 */

@Module
public class FinancialApplicationModule {

    @Provides
    public FinancialApplication provideApplication(Application application){
        return (FinancialApplication) application;
    }
}
