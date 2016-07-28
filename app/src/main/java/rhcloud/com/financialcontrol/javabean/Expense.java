package rhcloud.com.financialcontrol.javabean;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Developer on 21/07/2016.
 */

public class Expense{

    private int idExpense;
    private String value;
    private String description;
    private ExpenseOption expenseOption;

    public Expense(@NonNull int idExpense,@NonNull String value,@NonNull String description,@Nullable ExpenseOption expenseOption) {
        this.idExpense = idExpense;
        this.value = value;
        this.description = description;
        if(expenseOption == null){
            this.expenseOption = ExpenseOption.ETC;
        }else{
            this.expenseOption = expenseOption;
        }
    }

    public Expense() {
    }

    @NonNull
    public int getIdExpense() {
        return idExpense;
    }

    public void setIdExpense(@NonNull int idExpense) {
        this.idExpense = idExpense;
    }

    @NonNull
    public String getValue() {
        return value;
    }

    public void setValue(@NonNull String value) {
        this.value = value;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    public ExpenseOption getExpenseOption() {
        return expenseOption;
    }

    public void setExpenseOption(ExpenseOption expenseOption) {
        this.expenseOption = expenseOption;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o == null)){
            if(o instanceof Expense){
                Expense e = (Expense) o;
                return this.getIdExpense() == e.getIdExpense();
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return idExpense + " - " + description;
    }
}
