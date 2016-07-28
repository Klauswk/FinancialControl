package rhcloud.com.financialcontrol.tabutil;

import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * Class that implements all the functions to simply work with {@link TabLayout}
 *
 * @since 1.0
 * @version 1.0
 */
public class Tab {

    /**
     * Act has a holder to the views, extends {@link android.support.v4.view.ViewPager}
     */
    private TabView tabView;

    /**
     * The tab itself
     */
    private TabLayout tabLayout;

    /**
     * Base constructor.
     * @param activity , must extends {@link FragmentActivity}
     * @param tabViewId , the view id of the {@link TabView}
     * @param tabLayoutId , the view id of the {@link TabLayout}
     * @since 1.0
     * @version 1.0
     */
    public Tab(@NonNull FragmentActivity activity, @IdRes int tabViewId, @IdRes int tabLayoutId){
        tabView = (TabView) activity.findViewById(tabViewId);
        tabView.prepare(activity);

        tabLayout = (TabLayout) activity.findViewById(tabLayoutId);
        tabLayout.setupWithViewPager(tabView);
    }

    /**
     * Base constructor.
     * @param activity , must extends {@link FragmentActivity}
     * @param tabView , the {@link TabView}
     * @param tabLayout , the {@link TabLayout}
     * @since 1.0
     * @version 1.0
     */
    public Tab(@NonNull FragmentActivity activity, @NonNull TabView tabView, @NonNull TabLayout tabLayout){
        this.tabView = tabView;
        tabView.prepare(activity);

        this.tabLayout = tabLayout;
        tabLayout.setupWithViewPager(tabView);
    }

    /**
     * A <a href="http://www.tutorialspoint.com/design_pattern/factory_pattern.htm">Factory pattern</a> method to create a {@link Tab}
     *
     * @param activity
     * @param tabViewId
     * @param tabLayoutId
     * @return tab , a new {@link Tab} object.
     * @since 1.0
     * @version 1.0
     */
    public static Tab createTab(@NonNull FragmentActivity activity, @IdRes int tabViewId, @IdRes int tabLayoutId){
        Tab tab = new Tab(activity,tabViewId,tabLayoutId);
        return tab;
    }

    /**
     * Getter for the {@link TabView}
     * @return tabView
     * @since 1.0
     * @version 1.0
     */
    public TabView getTabView() {
        return tabView;
    }

    /**
     * Setter for the {@link TabView}
     * @since 1.0
     * @version 1.0
     */
    public void setTabView(TabView tabView) {
        this.tabView = tabView;
    }

    /**
     * Getter for the {@link TabLayout}
     * @return tabLayout
     * @since 1.0
     * @version 1.0
     */
    public TabLayout getTabLayout() {
        return tabLayout;
    }

    /**
     * Setter for the {@link TabLayout}
     * @since 1.0
     * @version 1.0
     */
    public void setTabLayout(TabLayout tabLayout) {
        this.tabLayout = tabLayout;
    }

    /**
     * The base addTab method.
     * A <a href="http://www.tutorialspoint.com/design_pattern/factory_pattern.htm">Factory pattern</a> method to create a {@link Tab}
     * @param tabFragment , the {@link TabFragment} that will be added.
     * @since 1.0
     * @version 1.0
     * @return tab
     */
    public Tab addTab(@NonNull TabFragment tabFragment){
        tabView.addTab(tabFragment,-1);
        return this;
    }

    /**
     * The base addTab method.
     * A <a href="http://www.tutorialspoint.com/design_pattern/factory_pattern.htm">Factory pattern</a> method to create a {@link Tab}
     * @param tabFragment , the {@link TabFragment} that will be added.
     * @param position , the position to add the {@link TabFragment}, -1 will be add to the end
     * @since 1.0
     * @version 1.0
     * @return tab
     */
    public Tab addTab(@NonNull TabFragment tabFragment , int position){
        tabView.addTab(tabFragment,position);
        return this;
    }

    /**
     * A <a href="http://www.tutorialspoint.com/design_pattern/factory_pattern.htm">Factory pattern</a> method to create a {@link Tab}
     * @param fragment , the {@link Fragment} that will be added. The title will be the {@link Class#getSimpleName()}
     * @since 1.0
     * @version 1.0
     * @return tab
     */
    public Tab addTab(@NonNull Fragment fragment){
        return addTab(new TabFragment(fragment), -1);
    }

    /**
     * A <a href="http://www.tutorialspoint.com/design_pattern/factory_pattern.htm">Factory pattern</a> method to create a {@link Tab}
     * @param fragment , the {@link Fragment} that will be added.
     * @param title , the title that will be added.
     * @since 1.0
     * @version 1.0
     * @return tab
     */
    public Tab addTab(@NonNull Fragment fragment, @Nullable String title){
        return addTab(fragment,title,null);
    }

    /**
     * A <a href="http://www.tutorialspoint.com/design_pattern/factory_pattern.htm">Factory pattern</a> method to create a {@link Tab}
     * @param fragment , the {@link Fragment} that will be added.
     * @param title , the title that will be added.
     * @param drawable , the {@link Drawable} that will be added to the top of the title
     * @since 1.0
     * @version 1.0
     * @return tab
     */
    public Tab addTab(@NonNull Fragment fragment, @Nullable String title , @Nullable Drawable drawable){
        return addTab(new TabFragment(fragment,title,drawable), -1);
    }

    /**
     * A <a href="http://www.tutorialspoint.com/design_pattern/factory_pattern.htm">Factory pattern</a> method to create a {@link Tab}
     * @param fragment , the {@link Fragment} that will be added. The title will be the {@link Class#getSimpleName()}
     * @param position , the position to add the {@link TabFragment}, -1 will be add to the end
     * @since 1.0
     * @version 1.0
     * @return tab
     */
    public Tab addTab(@NonNull Fragment fragment, int position){
        return addTab(new TabFragment(fragment), position);
    }

    /**
     * A <a href="http://www.tutorialspoint.com/design_pattern/factory_pattern.htm">Factory pattern</a> method to create a {@link Tab}
     * @param fragment , the {@link Fragment} that will be added.
     * @param title , the title that will be added.
     * @param position , the position to add the {@link TabFragment}, -1 will be add to the end
     * @since 1.0
     * @version 1.0
     * @return tab
     */
    public Tab addTab(@NonNull Fragment fragment, @Nullable String title,int position){
        return addTab(fragment,title,null,position);
    }

    /**
     * A <a href="http://www.tutorialspoint.com/design_pattern/factory_pattern.htm">Factory pattern</a> method to create a {@link Tab}
     * @param fragment , the {@link Fragment} that will be added.
     * @param title , the title that will be added.
     * @param drawable , the {@link Drawable} that will be added to the top of the title
     * @param position , the position to add the {@link TabFragment}, -1 will be add to the end
     * @since 1.0
     * @version 1.0
     * @return tab
     */
    public Tab addTab(@NonNull Fragment fragment, @Nullable String title , @Nullable Drawable drawable, int position){
        return addTab(new TabFragment(fragment,title,drawable), position);
    }
}
