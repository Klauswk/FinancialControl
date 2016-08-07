package rhcloud.com.financialcontrol.service;

import rhcloud.com.financialcontrol.javabean.ExpenseOption;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 *
 * A service definition for the application
 *
 * @version 1.0
 * @since 1.0
 * @see <a href="https://en.wikipedia.org/wiki/Service_layers_pattern">Service Design Pattern</a>
 */
public interface ExpenseService {

    public Double getTotalExpenses();

    public Double getTotalExpensesByType(ExpenseOption option);

    public String[] getExpenseOptions();
}
