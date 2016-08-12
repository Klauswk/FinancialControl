package rhcloud.com.financialcontrol.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import rhcloud.com.droidutils.tabutil.tabutil.TabFragment;
import rhcloud.com.droidutils.tabutil.tabutil.interfaces.Consumer;
import rhcloud.com.droidutils.tabutil.tabutil.interfaces.TabService;
import rhcloud.com.financialcontrol.FinancialApplication;
import rhcloud.com.financialcontrol.R;
import rhcloud.com.financialcontrol.activity.MainActivity;
import rhcloud.com.financialcontrol.dao.ExpenseDAO;
import rhcloud.com.financialcontrol.javabean.Expense;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 *
 * @since 1.0
 * @version 1.0
 */
public class ExpenseListFragment extends Fragment implements Consumer, AdapterView.OnItemClickListener{

    private ListView lvExpenses;
    private View rootView;

    //@Inject
    ExpenseDAO expenseDAO;

    private ArrayAdapter<Expense> adapter;
    private TabService tabService;

    public static ExpenseListFragment getInstance(TabService tabService){
        ExpenseListFragment expenseListFragment = new ExpenseListFragment();
        expenseListFragment.setTabService(tabService);
        return expenseListFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_expense_list, container, false);

        lvExpenses = (ListView) rootView.findViewById(R.id.lvExpenses);
        lvExpenses.setEmptyView(rootView.findViewById(R.id.tvEmpty));

        expenseDAO = ((FinancialApplication)getActivity().getApplication()).getExpenseDAO();

        // Test purpose of dagger 2
        //((FinancialApplication)getActivity().getApplication()).getComponent().inject(this);

        adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,expenseDAO.getExpenseList());
        lvExpenses.setAdapter(adapter);
        lvExpenses.setOnItemClickListener(this);
        setRetainInstance(true);

        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(getActivity() instanceof MainActivity){
            TabFragment tabFragment = new TabFragment(DetailExpenseFragment.getInstance(tabService),"Details");
            Bundle bundle = new Bundle();
            bundle.putInt("idExpense",adapter.getItem(position).getIdExpense());
            tabFragment.getFragment().setArguments(bundle);
            tabService.getOnAddTab().addTab(tabFragment);
        }
    }

    @Override
    public void onDataChange() {
        adapter.notifyDataSetChanged();
    }

    public TabService getTabService() {
        return tabService;
    }

    public void setTabService(TabService tabService) {
        this.tabService = tabService;
    }
}
