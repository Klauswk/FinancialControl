package rhcloud.com.financialcontrol.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rhcloud.com.financialcontrol.R;
import rhcloud.com.financialcontrol.dao.ExpenseDAO;
import rhcloud.com.financialcontrol.databinding.FragmentExpenseDetailsBinding;
import rhcloud.com.financialcontrol.impl.ExpenseDAOTestImpl;


/**
 * Created by Developer on 21/07/2016.
 */

public class DetailExpenseFragment extends Fragment{

    private View rootView;
    private FragmentExpenseDetailsBinding binding;
    private ExpenseDAO expenseDAO;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_expense_details,container,false);
        rootView = binding.getRoot();
        expenseDAO = new ExpenseDAOTestImpl();
        binding.setExpense(expenseDAO.getExpenseById(getArguments().getInt("idExpense")));
        setRetainInstance(true);
        return rootView;
    }
}
