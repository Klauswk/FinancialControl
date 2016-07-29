package rhcloud.com.financialcontrol.fragment;

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
import rhcloud.com.financialcontrol.validation.ObjectUtils;


/**
 * Created by Developer on 21/07/2016.
 */

public class DetailExpenseFragment extends Fragment implements View.OnClickListener{

    private View rootView;
    private FragmentExpenseDetailsBinding binding;
    private ExpenseDAO expenseDAO;
    private Button btnEdit;
    private Button btnSave;
    private final ObservableBoolean stateOfButton = new ObservableBoolean();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_expense_details,container,false);
        rootView = binding.getRoot();
        btnEdit = (Button) rootView.findViewById(R.id.btnEdit);
        btnSave = (Button) rootView.findViewById(R.id.btnSave);
        btnEdit.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        expenseDAO = new ExpenseDAOTestImpl();
        binding.setExpense(expenseDAO.getExpenseById(getArguments().getInt("idExpense")));
        stateOfButton.set(false);
        binding.setState(stateOfButton);
        setRetainInstance(true);

        btnEdit.setVisibility(View.GONE);

        return rootView;
    }

    @Override
    public void onClick(View v){

        switch(v.getId()){
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

    private void saveModifications(){
        Expense exp = binding.getExpense();
        if(!ObjectUtils.checkForStringsNullOrEmpty(exp.getDescription(),exp.getValue())){
            expenseDAO.updateExpense(exp);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
            stateOfButton.set(false);
        }
    }
}
