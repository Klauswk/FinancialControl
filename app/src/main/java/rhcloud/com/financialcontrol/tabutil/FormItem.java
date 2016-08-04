package rhcloud.com.financialcontrol.tabutil;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import rhcloud.com.financialcontrol.R;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * @version 1.0
 * @since 1.0
 */

    @InverseBindingMethods(value = {
            @InverseBindingMethod(type = FormItem.class, attribute = "anwserText"),
    })
    public class FormItem extends LinearLayout {

        private TextView tvTitle;
        private EditText etAnwser;

        public FormItem(@NonNull Context context) {
            super(context);
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            inflater.inflate(R.layout.form_item, this);

            tvTitle = (TextView) findViewById(R.id.tvTitle);
            etAnwser = (EditText) findViewById(R.id.etAnwser);
        }

        public FormItem(@NonNull Context context, @NonNull String title) {
            this(context);
            setTvTitle(title);
        }

        public FormItem(@NonNull Context context, @NonNull String title, @NonNull String hint) {
            this(context, title);
            setAnwserHint(hint);
        }

        public FormItem(@NonNull Context context, @NonNull String title, @NonNull String hint, @NonNull String anwserText) {
            this(context, title, hint);
            setAnwserHint(anwserText);
        }


        public FormItem(@NonNull Context context, @NonNull AttributeSet attrs) {
            super(context, attrs);

            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            inflater.inflate(R.layout.form_item, this);

            tvTitle = (TextView) findViewById(R.id.tvTitle);
            etAnwser = (EditText) findViewById(R.id.etAnwser);

            TypedArray a = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.form_item,
                    0, 0);

            try {
                setTvTitle(a.getString(R.styleable.form_item_tvTitle));
                setAnwserHint(a.getString(R.styleable.form_item_anwserHint));
                setAnwserText(a.getString(R.styleable.form_item_anwserText));
                String isEnabled = a.getString(R.styleable.form_item_android_enabled);
                if (isEnabled != null) {
                    setEnable(Boolean.parseBoolean(isEnabled));
                }
            } finally {
                a.recycle();
            }
        }

        public void setTvTitle(String title) {
            tvTitle.setText(title);
        }

        public String getTvTitle() {
            return tvTitle.getText().toString();
        }

        public void setAnwserHint(String hint) {
            etAnwser.setHint(hint);
        }

        public String getAnwserHint() {
            return etAnwser.getHint().toString();
        }

        public void setEnable(boolean isEnable) {
            tvTitle.setEnabled(isEnable);
            etAnwser.setEnabled(isEnable);
        }

        public void setAnwserText(String anwserText) {
            etAnwser.setText(anwserText);
        }

        public String getAnwserText() {
            return etAnwser.getText().toString();
        }

        @InverseBindingAdapter(attribute = "form_item:anwserText")
        public static String setOnAnwserTextAttrChanged(final String value){

            Log.d("Test","Calling InverseBindingAdapter: " + value);
            return value;
        }


        @BindingAdapter(value = {"anwserTextAttrChanged"},
                requireAll = false)
        public static void setOnAnwserTextAttrChanged(final FormItem view,final InverseBindingListener anwserTextAttrChanged){

            Log.d("Test","Calling BindingAdapter: " + view.getAnwserText());


        if(anwserTextAttrChanged == null){

            }else{
            Log.d("Test","Calling here");
                anwserTextAttrChanged.onChange();

            }
        }

        @BindingAdapter(value = {"android:enabled"})
        public static void customEnable(FormItem formItem, boolean isEnable) {
            formItem.setEnable(isEnable);
        }
    }
