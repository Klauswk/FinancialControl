package rhcloud.com.financialcontrol.service;

import rhcloud.com.financialcontrol.javabean.ExpenseOption;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * @version 1.0
 * @since 1.0
 */

public interface ExpenseService {

    public Double getTotalExpenses();

    public Double getTotalExpensesByType(ExpenseOption option);

}
