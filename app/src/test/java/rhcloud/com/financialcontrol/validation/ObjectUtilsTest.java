package rhcloud.com.financialcontrol.validation;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Developer on 29/07/2016.
 */
public class ObjectUtilsTest {

    @Test
    public void testIfNullObjectReturnTrue(){
        assertTrue(ObjectUtils.checkForStringNullOrEmpty(null));
    }

    @Test
    public void testIfEmptyStringReturnTrue(){
        String test = "";
        assertTrue(ObjectUtils.checkForStringNullOrEmpty(test));
    }

    @Test
    public void testIfNullObjectAndNullStringReturnTrue(){
        String test = "";
        assertTrue(ObjectUtils.checkForStringsNullOrEmpty(null,test));
    }

    @Test
    public void testIfNullStringListReturnTrue(){
        String[] values = null;
        assertTrue(ObjectUtils.checkForStringsNullOrEmpty(values));
    }

    @Test
    public void testIfEmptyListReturnTrue(){
        String[] values = {};
        assertTrue(ObjectUtils.checkForStringsNullOrEmpty(values));
    }

    @Test
    public void testIfStringReturnFalse(){
        String test = "Test";
        assertFalse(ObjectUtils.checkForStringNullOrEmpty(test));
    }

    @Test
    public void testIfNullObjectAndStringReturnTrue(){
        String test = "Test";
        assertTrue(ObjectUtils.checkForStringsNullOrEmpty(null,test));
    }

    @Test
    public void testIfStringListReturnFalse(){
        String[] values = {"Test 1","Test 2"};
        assertFalse(ObjectUtils.checkForStringsNullOrEmpty(values));
    }
}