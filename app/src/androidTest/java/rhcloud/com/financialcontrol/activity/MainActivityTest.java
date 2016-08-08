package rhcloud.com.financialcontrol.activity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.runner.RunWith;

import rhcloud.com.financialcontrol.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

//@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    //@Test
    public void mainActivityTest() {
        ViewInteraction appCompatTextView = onView(
                allOf(withText("Add Expense"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.etDescription),
                        withParent(withId(R.id.rl1))));
        appCompatEditText.perform(scrollTo(), replaceText("Test"));

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.etValue),
                        withParent(withId(R.id.rl2))));
        appCompatEditText2.perform(scrollTo(), replaceText("123"));

        ViewInteraction appCompatSpinner = onView(
                withId(R.id.spOptions));
        appCompatSpinner.perform(scrollTo(), click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(android.R.id.text1), withText("ETC"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnInsert), withText("Insert")));

        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withText("Expenses"), isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.text1), withText("123$ - Test"),
                        withParent(withId(R.id.lvExpenses)),
                        isDisplayed()));
        textView.check(matches(withText("123$ - Test")));

        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(android.R.id.text1), withText("123$ - Test"),
                        childAtPosition(withId(R.id.lvExpenses), 4),
                        isDisplayed()));
        appCompatTextView4.perform(click());

        ViewInteraction appCompatTextView5 = onView(
                allOf(withText("Details"), isDisplayed()));
        appCompatTextView5.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.etAnwser), withText("4"), isDisplayed()));
        editText.check(matches(withText("4")));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.tvDescription), withText("Test"), isDisplayed()));
        editText2.check(matches(withText("Test")));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.tvValue), withText("123"), isDisplayed()));
        editText3.check(matches(withText("123")));

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.text1), withText("ETC"),
                        withParent(withId(R.id.spOptions)),
                        isDisplayed()));
        textView2.check(matches(withText("ETC")));

        ViewInteraction textView3 = onView(
                allOf(withId(android.R.id.text1), withText("ETC"),
                        withParent(withId(R.id.spOptions)),
                        isDisplayed()));
        textView3.check(matches(withText("ETC")));

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btnEdit), withText("Edit")));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.tvValue), withText("123")));
        appCompatEditText3.perform(scrollTo(), replaceText("1234"));

        ViewInteraction appCompatSpinner2 = onView(
                withId(R.id.spOptions));
        appCompatSpinner2.perform(scrollTo(), click());

        ViewInteraction appCompatTextView6 = onView(
                allOf(withId(android.R.id.text1), withText("FOOD"), isDisplayed()));
        appCompatTextView6.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.btnSave), withText("Save")));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.ibClose), isDisplayed()));
        imageButton.perform(click());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.etAnwser), withText("1453.34"), isDisplayed()));
        editText4.check(matches(withText("1453.34")));

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.etAnwser), withText("10.0"), isDisplayed()));
        editText5.check(matches(withText("10.0")));

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.etAnwser), withText("3.39"), isDisplayed()));
        editText6.check(matches(withText("3.39")));

        ViewInteraction editText7 = onView(
                allOf(withId(R.id.etAnwser), withText("1439.95"), isDisplayed()));
        editText7.check(matches(withText("1439.95")));

        ViewInteraction editText8 = onView(
                allOf(withId(R.id.etAnwser), withText("0.0"), isDisplayed()));
        editText8.check(matches(withText("0.0")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
