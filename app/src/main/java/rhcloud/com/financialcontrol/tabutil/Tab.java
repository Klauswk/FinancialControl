package rhcloud.com.financialcontrol.tabutil;

import android.content.Context;
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
    private TabBody tabBody;

    /**
     * The tab itself
     */
    private TabLayout tabLayout;

    private Context context;

    /**
     * Base constructor.
     * @param activity , must extends {@link FragmentActivity}
     * @param tabViewId , the view id of the {@link TabBody}
     * @param tabLayoutId , the view id of the {@link TabLayout}
     * @since 1.0
     * @version 1.0
     */
    public Tab(@NonNull FragmentActivity activity, @IdRes int tabViewId, @IdRes int tabLayoutId){
        tabBody = (TabBody) activity.findViewById(tabViewId);
        tabBody.prepare(activity);

        tabLayout = (TabLayout) activity.findViewById(tabLayoutId);
        tabLayout.setupWithViewPager(tabBody);

        context = activity.getBaseContext();
    }

    /**
     * Base constructor.
     * @param activity , must extends {@link FragmentActivity}
     * @param tabBody , the {@link TabBody}
     * @param tabLayout , the {@link TabLayout}
     * @since 1.0
     * @version 1.0
     */
    public Tab(@NonNull FragmentActivity activity, @NonNull TabBody tabBody, @NonNull TabLayout tabLayout){
        this.tabBody = tabBody;
        tabBody.prepare(activity);

        this.tabLayout = tabLayout;
        tabLayout.setupWithViewPager(tabBody);
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
     * Getter for the {@link TabBody}
     * @return tabBody
     * @since 1.0
     * @version 1.0
     */
    public TabBody getTabBody() {
        return tabBody;
    }

    /**
     * Setter for the {@link TabBody}
     * @since 1.0
     * @version 1.0
     */
    public void setTabBody(TabBody tabBody) {
        this.tabBody = tabBody;
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
        tabBody.addTab(tabFragment,-1);
        for(int i = 0; i < tabBody.getTabContainer().getCount() ; i++){
            tabLayout.getTabAt(i).setCustomView(new TabView(context, tabBody.getTabContainer().getTabFragment(i)));
        }
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
        tabBody.addTab(tabFragment,position);
        for(int i = 0; i < tabBody.getTabContainer().getCount() ; i++){
            tabLayout.getTabAt(i).setCustomView(new TabView(context, tabBody.getTabContainer().getTabFragment(i)));
        }
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
