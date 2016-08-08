package rhcloud.com.droidutils.validation;


/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 *
 * @since 1.0
 * @version 1.0
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
        if(values == null || values.length == 0){
            return true;
        }
        for(String s : values){
            if(checkForStringNullOrEmpty(s)){
                return true;
            }
        }
        return false;
    }
}
