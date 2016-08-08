package rhcloud.com.droidutils.tabutil.tabutil;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * Class that extends {@link ViewPager} and override some of their methods
 *
 * @since 1.0
 * @version 1.0
 */
public class TabBody extends ViewPager {

    /**
     * The implementation of a custom {@link FragmentPagerAdapter} used in the {@link TabBody#prepare(FragmentActivity)}.
     * @since 1.0
     * @version 1.0
     */
    private TabContainer tabContainer;

    /**
     * Default Constructor for a {@link android.view.View}
     * @param context
     * @since 1.0
     * @version 1.0
     */
    public TabBody(Context context) {
        super(context);
    }

    /**
     * Default Constructor for a {@link android.view.View}
     * @param context
     * @since 1.0
     * @version 1.0
     */
    public TabBody(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Prepare the {@link TabContainer} and set the {@link FragmentPagerAdapter}, that its the {@link TabContainer} itself.
     * @since 1.0
     * @version 1.0
     */
    public void prepare(FragmentActivity fragmentActivity){
        tabContainer = new TabContainer(fragmentActivity);
        setAdapter(tabContainer);
    }

    /**
     * Checks if the {@link TabContainer} has been properly prepare.
     * @since 1.0
     * @version 1.0
     */
    private void checkIfHasBeenPrepare(){
        if(tabContainer == null){
            throw new IllegalStateException("Tab Container is null, did you forget to call #prepare?");
        }
    }

    /**
     * Add the {@link TabFragment} to the {@link TabContainer}
     * @param tabFragment
     * @param position
     * @since 1.0
     * @version 1.0
     */
    public void addTab(@NonNull TabFragment tabFragment, int position){
        tabContainer.addTab(tabFragment,-1);
        setCurrentItem(position);
    }

    @Deprecated
    private void checkToReplace(@NonNull TabFragment tabFragment, int position) {
        Fragment tabContainerItem = tabContainer.getItem(position);

        Log.d("TAB","Item: " + tabFragment.getFragment().getArguments().getInt("idExpense"));
        if(tabContainerItem != null){
            Log.d("TAB","Item not null");
            if(tabContainerItem.getClass() == tabFragment.getFragment().getClass()){
                Log.d("TAB","Same class");
                if(getCurrentItem() == position){
                    Log.d("TAB","Same position");
                    if((position -1) > 0){
                        Log.d("TAB","Setting current back");
                        setCurrentItem(getCurrentItem() -1);
                    }
                }
                Log.d("TAB","Replacing");
                tabContainer.replaceTab(tabFragment,position);
            }else{
                Log.d("TAB","Not same class");
                tabContainer.addTab(tabFragment,position);
            }
        }else{
            Log.d("TAB","Item is null");
            tabContainer.addTab(tabFragment,position);
        }
    }

    /**
     * Add the {@link TabFragment} to the {@link TabContainer}
     * @param tabFragment
     * @since 1.0
     * @version 1.0
     */
    public void addTab(@NonNull TabFragment tabFragment){
        checkIfHasBeenPrepare();
        tabContainer.addTab(tabFragment,-1);
    }

    public TabFragment removeTabAt(int position){
        return tabContainer.removeTabAt(position);
    }


    /**
     * Getter for the {@link TabContainer}
     * @return tabContainer
     * @since 1.0
     * @version 1.0
     */
    public TabContainer getTabContainer() {
        return tabContainer;
    }
}
