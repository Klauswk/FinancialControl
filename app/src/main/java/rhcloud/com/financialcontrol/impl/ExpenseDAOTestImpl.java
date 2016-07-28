package rhcloud.com.financialcontrol.impl;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rhcloud.com.financialcontrol.dao.ExpenseDAO;
import rhcloud.com.financialcontrol.javabean.Expense;
import rhcloud.com.financialcontrol.javabean.ExpenseOption;

/**
 * Created by Developer on 21/07/2016.
 */

public class ExpenseDAOTestImpl implements ExpenseDAO {

    private static List<Expense> expenseList;
    private static int idExpense = 0;

    public ExpenseDAOTestImpl(){
        if(expenseList == null){
            expenseList = new ArrayList<>();
            expenseList.add(new Expense(idExpense++,"10","Movie", ExpenseOption.MOVIE));
            expenseList.add(new Expense(idExpense++,"25.15","Lunch",ExpenseOption.FOOD));
            expenseList.add(new Expense(idExpense++,"180.80","Market",ExpenseOption.FOOD));
            expenseList.add(new Expense(idExpense++,"3.40","Bus",ExpenseOption.TRANSPORT));
        }
    }

    public ExpenseDAOTestImpl(@NonNull List<Expense> expenseList){
        this();
        this.expenseList.clear();
        this.expenseList.addAll(expenseList);
    }



    @Override
    public void addExpense(Expense expense) {
        checkForNullExpense(expense);
        Expense exp = new Expense();
        exp.setIdExpense(idExpense++);
        expense.setIdExpense(exp.getIdExpense());
        exp.setValue(expense.getValue());
        exp.setDescription(expense.getDescription());
        expenseList.add(exp);
    }

    private void checkForNullExpense(Expense expense) {
        if(expense == null){
            throw new NullPointerException("The expense cannot be null");
        }
    }

    @Override
    public void removeById(int expenseId) {
        for(int i = 0 ; i < expenseList.size() ; i++){
            if(expenseList.get(i).getIdExpense() == expenseId){
                expenseList.remove(i);
                break;
            }
        }
    }

    @Override
    public void removeExpense(Expense expense) {
        checkForNullExpense(expense);
        expenseList.remove(expense);
    }

    @Override
    public void updateExpense(Expense expense) {
        checkForNullExpense(expense);
        removeExpense(expense);
        addExpense(expense);
    }

    @Override
    @Nullable
    public Expense getExpenseById(int id) {
        for(int i = 0 ; i < expenseList.size() ; i++){
            if(expenseList.get(i).getIdExpense() == id){
                return expenseList.get(i);
            }
        }
        return null;
    }

    @Override
    @NonNull
    public List<Expense> getExpenseList() {
        return expenseList;
    }
}
