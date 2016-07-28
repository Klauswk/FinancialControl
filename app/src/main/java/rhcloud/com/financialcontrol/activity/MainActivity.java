package rhcloud.com.financialcontrol.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import rhcloud.com.financialcontrol.R;
import rhcloud.com.financialcontrol.fragment.AddExpenseFragment;
import rhcloud.com.financialcontrol.fragment.ExpenseListFragment;
import rhcloud.com.financialcontrol.tabutil.Tab;
import rhcloud.com.financialcontrol.tabutil.TabFragment;

public class MainActivity extends AppCompatActivity {

    private Tab tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tab = Tab.createTab(this,R.id.tabView,R.id.tabLayout).addTab(new ExpenseListFragment(),"Expenses").addTab(new AddExpenseFragment(),"Add Expense");
    }

    public void addTab(TabFragment tabFragment){
        tab.addTab(tabFragment);
    }

    public void addTab(TabFragment tabFragment, int position){
        tab.addTab(tabFragment,position);

    }
}
