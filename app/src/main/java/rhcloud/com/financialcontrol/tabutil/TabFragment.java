package rhcloud.com.financialcontrol.tabutil;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * Class that act has a javabean to the tabs.
 * @since 1.0
 * @version 1.0
 */
public class TabFragment{

    private Fragment fragment;
    private String title;
    private Drawable drawable;


    public TabFragment(@NonNull Fragment fragment, @Nullable String title) {
        this.fragment = fragment;
        if(title == null){
            this.title = fragment.getClass().getSimpleName();
        }else{
            this.title = title;
        }
    }

    public TabFragment(@NonNull Fragment fragment, @Nullable String title, @Nullable Drawable drawable) {
        this(fragment,title);
        if(drawable != null){
            this.drawable = drawable;
        }
    }

    public TabFragment(Fragment fragment) {
        this(fragment,null);
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

}
