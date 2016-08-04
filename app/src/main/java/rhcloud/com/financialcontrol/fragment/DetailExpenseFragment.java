package rhcloud.com.financialcontrol.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import rhcloud.com.financialcontrol.R;
import rhcloud.com.financialcontrol.dao.ExpenseDAO;
import rhcloud.com.financialcontrol.databinding.FragmentExpenseDetailsBinding;
import rhcloud.com.financialcontrol.impl.ExpenseDAOTestImpl;
import rhcloud.com.financialcontrol.javabean.Expense;
import rhcloud.com.financialcontrol.tabutil.Closeable;
import rhcloud.com.financialcontrol.validation.ObjectUtils;


/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * @version 1.0
 * @since 1.0
 */
public class DetailExpenseFragment extends Fragment implements View.OnClickListener, Closeable {

    private View rootView;
    private FragmentExpenseDetailsBinding binding;
    private ExpenseDAO expenseDAO;
    private Button btnEdit;
    private Button btnSave;
    private final ObservableBoolean stateOfButton = new ObservableBoolean();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        System.out.println("Creating onCreateView");

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_expense_details, container, false);
        rootView = binding.getRoot();
        btnEdit = (Button) rootView.findViewById(R.id.btnEdit);
        btnSave = (Button) rootView.findViewById(R.id.btnSave);
        btnEdit.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        expenseDAO = new ExpenseDAOTestImpl();
        binding.setExpense(expenseDAO.getExpenseById(getArguments().getInt("idExpense")));
        stateOfButton.set(false);
        binding.setState(stateOfButton);
        //setRetainInstance(true);

        btnEdit.setVisibility(View.GONE);

        return rootView;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Creating new fragment");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println("onAttach");
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("pause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Destroy");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("Resume");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnEdit:
                enableEdit();
                break;
            case R.id.btnSave:
                saveModifications();
                break;
        }
    }

    private void enableEdit() {
        stateOfButton.set(true);
    }

    private void saveModifications() {
        Expense exp = binding.getExpense();
        Toast.makeText(getContext(), exp.getDescription(), Toast.LENGTH_SHORT).show();
        if (!ObjectUtils.checkForStringsNullOrEmpty(exp.getDescription(), exp.getValue())) {
            expenseDAO.updateExpense(exp);
            Toast.makeText(getContext(), "Saved: " + exp.getDescription(), Toast.LENGTH_SHORT).show();
            stateOfButton.set(false);
        }
    }
}
