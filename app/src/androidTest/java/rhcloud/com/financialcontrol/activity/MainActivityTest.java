package rhcloud.com.financialcontrol.activity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import rhcloud.com.financialcontrol.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction appCompatTextView = onView(
                allOf(withText("Add Expense"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.etDescription),
                        withParent(withId(R.id.rl1)),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Test"));

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.etValue),
                        withParent(withId(R.id.rl2)),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("22"));

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spOptions), isDisplayed()));
        appCompatSpinner.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(android.R.id.text1), withText("MOVIE"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnInsert), withText("Insert"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withText("Expenses"), isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.text1), withText("4 - Test"),
                        withParent(withId(R.id.lvExpenses)),
                        isDisplayed()));
        textView.check(matches(withText("4 - Test")));

        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(android.R.id.text1), withText("4 - Test"),
                        childAtPosition(withId(R.id.lvExpenses), 4),
                        isDisplayed()));
        appCompatTextView4.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.tvId), withText("Id: 4"), isDisplayed()));
        textView2.check(matches(withText("Id: 4")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.tvDescription), withText("Description: Test"), isDisplayed()));
        textView3.check(matches(withText("Description: Test")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.tvValue), withText("Value: 22"), isDisplayed()));
        textView4.check(matches(withText("Value: 22")));

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
