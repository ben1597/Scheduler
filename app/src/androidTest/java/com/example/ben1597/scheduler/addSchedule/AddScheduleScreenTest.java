package com.example.ben1597.scheduler.addSchedule;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.ben1597.scheduler.MainActivity;
import com.example.ben1597.scheduler.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by ben1597 on 2017/3/19.
 */

@RunWith(AndroidJUnit4.class)
public class AddScheduleScreenTest {

    @Rule
    public IntentsTestRule<MainActivity> mAddNoteIntentsTestRule =
            new IntentsTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResource() {
        Espresso.registerIdlingResources(
                mAddNoteIntentsTestRule.getActivity().getCountingIdlingResource());
    }

    @Test
    public void addNullScheduleTest(){
        onView(withId(R.id.fab_add_schedules)).perform(click());
        onView(withId(R.id.add_schedule_title)).perform(typeText(""));
        onView(withId(R.id.add_schedule_description)).perform(typeText(""),
                closeSoftKeyboard());
        onView(withId(R.id.fab_add_schedules)).perform(click());
        // Verify empty notes snackbar is shown
//        String emptyNoteMessageText = getTargetContext().getString(R.string.empty_note_message);
//        onView(withText(emptyNoteMessageText)).check(matches(isDisplayed()));
//        pauseTestFor(2000);



    }

    private void pauseTestFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
