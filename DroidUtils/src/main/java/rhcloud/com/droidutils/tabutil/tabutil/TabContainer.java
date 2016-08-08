package rhcloud.com.droidutils.tabutil.tabutil;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

import rhcloud.com.droidutils.tabutil.tabutil.interfaces.Consumer;
import rhcloud.com.droidutils.tabutil.tabutil.interfaces.Producer;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * Class that extends {@link FragmentStatePagerAdapter} and override some of their methods
 * @see rhcloud.com.droidutils.tabutil.tabutil.interfaces.Consumer
 * @see android.support.v4.app.FragmentPagerAdapter
 * @see android.support.v4.view.PagerAdapter
 * @since 1.0
 * @version 1.0
 */
public class TabContainer extends FragmentStatePagerAdapter implements Consumer {

    /**
     * The list of {@link TabFragment} presented in the {@link TabContainer}
     * @since 1.0
     * @version 1.0
     */
    private List<TabFragment> tabFragments;

    /**
     * The list of {@link Consumer} presented in the {@link TabContainer}
     * @since 1.0
     * @version 1.0
     */
    private List<Consumer> consumers;

    private FragmentManager manager;

    /**
     * Default constructor of the {@link TabContainer} class
     * @param fragmentActivity , to get the {@link android.support.v4.app.FragmentManager}
     * @since 1.0
     * @version 1.0
     */
    public TabContainer(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity.getSupportFragmentManager()); tabFragments = new ArrayList<>(3); consumers  = new ArrayList<>(3);
        manager = fragmentActivity.getSupportFragmentManager();
    }

    /**
     * Constructor of the {@link TabContainer} class
     * @param fragmentActivity , to get the {@link android.support.v4.app.FragmentManager}
     * @param tabFragments , to add all the {@link TabFragment} in the {@link TabContainer#tabFragments}
     * @since 1.0
     * @version 1.0
     */
    @Deprecated
    public TabContainer(@NonNull FragmentActivity fragmentActivity, @NonNull List<TabFragment> tabFragments) {
        this(fragmentActivity);
        this.tabFragments.addAll(tabFragments);
        for(TabFragment tf : tabFragments){
            checkForCallback(tf);
        }
    }

    /**
     * Constructor of the {@link TabContainer} class
     * @param fragmentActivity , to get the {@link android.support.v4.app.FragmentManager}
     * @param tabFragment , to add in the {@link TabContainer#tabFragments}
     * @since 1.0
     * @version 1.0
     */
    public TabContainer(@NonNull FragmentActivity fragmentActivity, @NonNull TabFragment tabFragment) {
        this(fragmentActivity);
        tabFragments.add(tabFragment);
        checkForCallback(tabFragment);
    }

    /**
     * Constructor of the {@link TabContainer} class
     * @param fragmentActivity , to get the {@link android.support.v4.app.FragmentManager}
     * @param fragment , to add all the {@link TabFragment} in the {@link TabContainer#tabFragments}
     * @param title , to add all the {@link TabFragment} in the {@link TabContainer#tabFragments}
     * @since 1.0
     * @version 1.0
     */
    @Deprecated
    public TabContainer(@NonNull FragmentActivity fragmentActivity, @NonNull Fragment fragment, @Nullable String title) {
        this(fragmentActivity,new TabFragment(fragment, title));
    }

    /**
     * Add the {@link TabFragment} to the {@link #tabFragments} and {@link #checkForCallback(TabFragment)}
     * @param tabFragment , the {@link TabFragment} that will be added
     * @param position , the position which will be add the {@link TabFragment}
     * @since 1.0
     * @version 1.0
     */
    public void addTab(@NonNull TabFragment tabFragment, int position){
        if(position == -1){
            tabFragments.add(tabFragment);
        }else{
            tabFragments.add(position,tabFragment);
        }
        checkForCallback(tabFragment);
        notifyDataSetChanged();
    }

    /**
     * Replace the {@link TabFragment} to the {@link #tabFragments} and {@link #checkForCallback(TabFragment)}
     * @param tabFragment , the {@link TabFragment} that will be added
     * @param position , the position which will be replace the {@link TabFragment}
     * @since 1.0
     * @version 1.0
     */
    @Deprecated
    public void replaceTab(@NonNull TabFragment tabFragment, int position){
        if(position == -1){
            tabFragments.add(tabFragment);
        }else{
            tabFragments.remove(position);
            tabFragments.add(tabFragment);
        }
        checkForCallback(tabFragment);
        notifyDataSetChanged();
    }

    /**
     * Check if the {@link TabFragment} implements {@link Consumer} or {@link Producer} class
     * @param tabFragment , the {@link TabFragment} to be checked.
     * @since 1.0
     * @version 1.0
     */
    private void checkForCallback(@NonNull TabFragment tabFragment) {
        if(tabFragment.getFragment() instanceof Consumer){
            consumers.add((Consumer)tabFragment.getFragment());
        }if(tabFragment.getFragment() instanceof Producer){
            Producer producer = (Producer) tabFragment.getFragment();
            producer.setConsumer(this);
        }
    }

    @Override
    public Fragment getItem(int position) {
        if(position >= tabFragments.size())
            return null;
        else
            return tabFragments.get(position).getFragment();
    }

    @Override
    public int getItemPosition(Object object){
        return PagerAdapter.POSITION_NONE;
    }

    /**
     * Getter for the {@link TabFragment}
     * @param position , the position to get
     * @return tabFragent
     * @since 1.0
     * @version 1.0
     */
    @Nullable
    public TabFragment getTabFragment(int position){
        if(position >= tabFragments.size())
            return null;
        else
            return tabFragments.get(position);
    }

    @Override
    public int getCount() {
        return tabFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if(position >= tabFragments.size())
                return null;
        else
            return tabFragments.get(position).getTitle();
    }

    @Override
    public void onDataChange() {
        for(Consumer c : consumers){
            c.onDataChange();
        }
    }

    public TabFragment removeTabAt(int position){
        TabFragment removed = tabFragments.remove(position);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(removed.getFragment());
        transaction.detach(removed.getFragment());
        transaction.commit();
        notifyDataSetChanged();
        return removed;
    }
}
