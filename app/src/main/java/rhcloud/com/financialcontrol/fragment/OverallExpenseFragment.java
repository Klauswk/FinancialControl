package rhcloud.com.financialcontrol.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.math.BigDecimal;

import rhcloud.com.droidutils.tabutil.tabutil.interfaces.Consumer;
import rhcloud.com.financialcontrol.FinancialApplication;
import rhcloud.com.financialcontrol.R;
import rhcloud.com.financialcontrol.databinding.FragmentOverallBinding;
import rhcloud.com.financialcontrol.impl.ExpenseDAOTestImpl;
import rhcloud.com.financialcontrol.impl.ExpenseServiceTestImpl;
import rhcloud.com.financialcontrol.javabean.ExpenseOption;
import rhcloud.com.financialcontrol.service.ExpenseService;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * @version 1.0
 * @since 1.0
 */

public class OverallExpenseFragment extends Fragment implements Consumer{

    private FragmentOverallBinding binding;
    private ExpenseService expenseService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_overall, container, false);

        expenseService = new ExpenseServiceTestImpl(((FinancialApplication)getActivity().getApplication()).getExpenseDAO());

        binding.setTotalExpense(expenseService.getTotalExpenses());
        binding.setTotalExpenseEtc(expenseService.getTotalExpensesByType(ExpenseOption.ETC));
        binding.setTotalExpenseMovie(expenseService.getTotalExpensesByType(ExpenseOption.MOVIE));
        binding.setTotalExpenseFood(expenseService.getTotalExpensesByType(ExpenseOption.FOOD));
        binding.setTotalExpenseTransport(expenseService.getTotalExpensesByType(ExpenseOption.TRANSPORT));

        return binding.getRoot();
    }

    @Override
    public void onDataChange() {
        binding.setTotalExpense(expenseService.getTotalExpenses());
        binding.setTotalExpenseEtc(expenseService.getTotalExpensesByType(ExpenseOption.ETC));
        binding.setTotalExpenseMovie(expenseService.getTotalExpensesByType(ExpenseOption.MOVIE));
        binding.setTotalExpenseFood(expenseService.getTotalExpensesByType(ExpenseOption.FOOD));
        binding.setTotalExpenseTransport(expenseService.getTotalExpensesByType(ExpenseOption.TRANSPORT));
    }
}
