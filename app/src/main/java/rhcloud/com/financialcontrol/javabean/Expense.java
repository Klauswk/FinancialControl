package rhcloud.com.financialcontrol.javabean;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 *
 * A java bean that represents a expense.
 *
 * Extends {@link RealmObject} for use in {@link rhcloud.com.financialcontrol.impl.ExpenseDAORealm}
 *
 * @since 1.0
 * @version 1.0
 */
public class Expense extends RealmObject {

    @PrimaryKey
    private int idExpense;
    private String value;
    private String description;
    private int expenseOption;

    public Expense(@NonNull int idExpense,@NonNull String value,@NonNull String description,@Nullable ExpenseOption expenseOption) {
        this.idExpense = idExpense;
        this.value = value;
        this.description = description;
        if(expenseOption == null){
            this.expenseOption = ExpenseOption.ETC.ordinal();
        }else{
            this.expenseOption = expenseOption.ordinal();
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
        return ExpenseOption.values()[expenseOption];
    }

    public void setExpenseOption(ExpenseOption expenseOption) {
        this.expenseOption = expenseOption.ordinal();
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
        return value + "$ - " + description;
    }
}
