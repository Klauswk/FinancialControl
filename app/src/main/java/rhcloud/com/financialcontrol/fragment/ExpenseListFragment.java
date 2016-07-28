package rhcloud.com.financialcontrol.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import rhcloud.com.financialcontrol.R;
import rhcloud.com.financialcontrol.activity.MainActivity;
import rhcloud.com.financialcontrol.dao.ExpenseDAO;
import rhcloud.com.financialcontrol.impl.ExpenseDAOTestImpl;
import rhcloud.com.financialcontrol.javabean.Expense;
import rhcloud.com.financialcontrol.tabutil.Consumer;
import rhcloud.com.financialcontrol.tabutil.TabFragment;

/**
 * Created by Developer on 21/07/2016.
 */

public class ExpenseListFragment extends Fragment implements Consumer, AdapterView.OnItemClickListener{

    private ListView lvExpenses;
    private View rootView;
    private ExpenseDAO expenseDAO;
    private ArrayAdapter<Expense> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_expense_list, container, false);

        lvExpenses = (ListView) rootView.findViewById(R.id.lvExpenses);
        expenseDAO = new ExpenseDAOTestImpl();
        adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,expenseDAO.getExpenseList());
        lvExpenses.setAdapter(adapter);
        lvExpenses.setOnItemClickListener(this);
        setRetainInstance(true);

        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(getActivity() instanceof MainActivity){
            TabFragment tabFragment = new TabFragment(new DetailExpenseFragment(),"Details: " + adapter.getItem(position).getIdExpense() );
            Bundle bundle = new Bundle();
            bundle.putInt("idExpense",adapter.getItem(position).getIdExpense());
            tabFragment.getFragment().setArguments(bundle);
            ((MainActivity)getActivity()).addTab(tabFragment,2);
        }
    }

    @Override
    public void onDataChange() {
        adapter.notifyDataSetChanged();
    }
}
