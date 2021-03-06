package rhcloud.com.financialcontrol.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import rhcloud.com.droidutils.tabutil.tabutil.interfaces.Closeable;
import rhcloud.com.droidutils.tabutil.tabutil.interfaces.FocusOnAdd;
import rhcloud.com.droidutils.tabutil.tabutil.interfaces.TabService;
import rhcloud.com.droidutils.validation.ObjectUtils;
import rhcloud.com.financialcontrol.FinancialApplication;
import rhcloud.com.financialcontrol.R;
import rhcloud.com.financialcontrol.dao.ExpenseDAO;
import rhcloud.com.financialcontrol.databinding.FragmentExpenseDetailsBinding;
import rhcloud.com.financialcontrol.impl.ExpenseServiceImpl;
import rhcloud.com.financialcontrol.javabean.Expense;
import rhcloud.com.financialcontrol.javabean.ExpenseOption;
import rhcloud.com.financialcontrol.service.ExpenseService;

import static java.security.AccessController.getContext;
import static rhcloud.com.financialcontrol.R.id.btnDelete;
import static rhcloud.com.financialcontrol.R.id.btnEdit;
import static rhcloud.com.financialcontrol.R.id.btnSave;


/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * @version 1.0
 * @since 1.0
 */
public class DetailExpenseFragment extends Fragment implements View.OnClickListener, Closeable, FocusOnAdd {

    private FragmentExpenseDetailsBinding binding;
    private ExpenseDAO expenseDAO;
    private final ObservableBoolean stateOfButton = new ObservableBoolean();
    private ArrayAdapter<String> options;
    private ExpenseService expenseService;
    private Expense expense;
    private TabService tabService;


    public static DetailExpenseFragment getInstance(TabService tabService){
        DetailExpenseFragment detailExpenseFragment = new DetailExpenseFragment();
        detailExpenseFragment.setTabService(tabService);
        return detailExpenseFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        expenseDAO = ((FinancialApplication)getActivity().getApplication()).getExpenseDAO();
        expenseService = new ExpenseServiceImpl(expenseDAO);
        expense = expenseDAO.getExpenseById(getArguments().getInt("idExpense"));
        options = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,expenseService.getExpenseOptions());

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_expense_details, container, false);
        binding.btnSave.setOnClickListener(this);
        binding.btnEdit.setOnClickListener(this);
        binding.btnDelete.setOnClickListener(this);
        binding.spOptions.setAdapter(options);
        binding.spOptions.setSelection(expense.getExpenseOption().ordinal());
        binding.btnEdit.setVisibility(View.GONE);
        stateOfButton.set(false);
        binding.setState(stateOfButton);
        binding.setExpense(expense);
        setRetainInstance(true);

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case btnEdit:
                enableEdit();
                break;
            case btnSave:
                saveModifications();
                break;
            case btnDelete:
                deleteExpense();
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.spOptions.setSelection(expense.getExpenseOption().ordinal());
    }

    private void deleteExpense(){
        expenseDAO.removeExpense(binding.getExpense());
        tabService.getOnTabRemove().removeTab(this);
    }

    private void enableEdit() {
        stateOfButton.set(true);
    }

    private void saveModifications() {
        Expense exp = binding.getExpense();
        if (!ObjectUtils.checkForStringsNullOrEmpty(exp.getDescription(), exp.getValue())) {
            int i = binding.spOptions.getSelectedItemPosition();


            exp.setExpenseOption(ExpenseOption.values()[i]);
            expenseDAO.updateExpense(exp);
            Toast.makeText(getContext(), "Saved: " + exp.getDescription(), Toast.LENGTH_SHORT).show();
            stateOfButton.set(false);
        }
    }

    public TabService getTabService() {
        return tabService;
    }

    public void setTabService(TabService tabService) {
        this.tabService = tabService;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
