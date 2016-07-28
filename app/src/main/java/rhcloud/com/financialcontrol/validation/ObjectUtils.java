package rhcloud.com.financialcontrol.validation;

import static android.R.attr.value;

/**
 * Created by Developer on 27/07/2016.
 */

public class ObjectUtils {

    public static boolean checkForStringNullOrEmpty(String value){
        if(value == null){
            return true;
        }else if(value.isEmpty()){
            return true;
        }
        return false;
    }

    public static boolean checkForStringsNullOrEmpty(String... values){
        for(String s : values){
            if(checkForStringNullOrEmpty(s)){
                return true;
            }
        }
        return false;
    }
}
