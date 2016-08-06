package rhcloud.com.financialcontrol.impl;

import java.math.BigDecimal;
import java.util.List;

import rhcloud.com.financialcontrol.dao.ExpenseDAO;
import rhcloud.com.financialcontrol.javabean.Expense;
import rhcloud.com.financialcontrol.javabean.ExpenseOption;
import rhcloud.com.financialcontrol.service.ExpenseService;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * @version 1.0
 * @since 1.0
 */

public class ExpenseServiceTestImpl implements ExpenseService {

    private ExpenseDAO expenseDAO;

    public ExpenseServiceTestImpl( ExpenseDAO expenseDAO) {
        this.expenseDAO = expenseDAO;
    }

    @Override
    public Double getTotalExpenses() {
        Double totalExpense = 0.0;

        for(Expense ex : expenseDAO.getExpenseList()){
            totalExpense += Double.valueOf(ex.getValue());
        }
        return  new BigDecimal(totalExpense).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
    }

    @Override
    public Double getTotalExpensesByType(ExpenseOption option) {

        Double totalExpense = 0.0;

        for(Expense ex : expenseDAO.getExpenseList()){
            if(ex.getExpenseOption() == option){
                totalExpense += Double.valueOf(ex.getValue());
            }
        }
        return  new BigDecimal(totalExpense).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
    }

    @Override
    public String[] getExpenseOptions() {
        ExpenseOption[] options = ExpenseOption.values();
        String[] expenseOptions = new String[options.length];
        for (int i = 0; i < options.length; i++) {
            expenseOptions[i] = options[i].name();
        }

        return expenseOptions;
    }
}
