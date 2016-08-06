package rhcloud.com.financialcontrol.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import rhcloud.com.droidutils.tabutil.tabutil.interfaces.Consumer;
import rhcloud.com.droidutils.tabutil.tabutil.interfaces.Producer;
import rhcloud.com.droidutils.validation.ObjectUtils;
import rhcloud.com.financialcontrol.FinancialApplication;
import rhcloud.com.financialcontrol.R;
import rhcloud.com.financialcontrol.dao.ExpenseDAO;
import rhcloud.com.financialcontrol.databinding.FragmentAddExpenseBinding;
import rhcloud.com.financialcontrol.impl.ExpenseDAOTestImpl;
import rhcloud.com.financialcontrol.javabean.Expense;
import rhcloud.com.financialcontrol.javabean.ExpenseOption;

import static rhcloud.com.financialcontrol.R.id.btnInsert;


/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * @version 1.0
 * @since 1.0
 */
public class AddExpenseFragment extends Fragment implements Producer, View.OnClickListener {

    private View rootView;
    private Expense expense;
    private FragmentAddExpenseBinding binding;
    private ExpenseDAO expenseDAO;
    private Consumer consumer;
    private ArrayAdapter<String> options;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_expense, container, false);
        rootView = binding.getRoot();
        options = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, ExpenseOption.values());
        binding.spOptions.setAdapter(options);
        binding.btnInsert.setOnClickListener(this);
        expense = new Expense();
        expenseDAO = ((FinancialApplication)getActivity().getApplication()).getExpenseDAO();
        binding.setExpense(expense);
        setRetainInstance(true);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case btnInsert:

                Expense exp = binding.getExpense();

                if (exp != null) {
                    if (ObjectUtils.checkForStringsNullOrEmpty(exp.getDescription(), exp.getValue())) {
                        Toast.makeText(getContext(), "There is empty fields", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                int i = binding.spOptions.getSelectedItemPosition();
                exp.setExpenseOption(ExpenseOption.values()[i]);
                expenseDAO.addExpense(exp);
                consumer.onDataChange();
                Toast.makeText(getContext(), "Expense Added!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void setConsumer(Consumer c) {
        this.consumer = c;
    }
}
