package rhcloud.com.financialcontrol.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import rhcloud.com.droidutils.tabutil.tabutil.Tab;
import rhcloud.com.droidutils.tabutil.tabutil.TabFragment;
import rhcloud.com.financialcontrol.R;
import rhcloud.com.financialcontrol.fragment.AddExpenseFragment;
import rhcloud.com.financialcontrol.fragment.ExpenseListFragment;
import rhcloud.com.financialcontrol.fragment.OverallExpenseFragment;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 *
 * @since 1.0
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    private Tab tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            createMainTab();
        }
    }

    private void createMainTab(){
        tab = Tab.createTab(this,R.id.tabBody,R.id.tabLayout).addTab(new ExpenseListFragment(),"Expenses",getResources().getDrawable(android.R.drawable.ic_menu_add)).addTab(new AddExpenseFragment(),"Add Expense").addTab(new OverallExpenseFragment(),"Total Expenses");
    }

    public void addTab(TabFragment tabFragment){
        tab.addTab(tabFragment);
    }

    public void addTab(TabFragment tabFragment, int position){
        tab.addTab(tabFragment,position);
    }
}
