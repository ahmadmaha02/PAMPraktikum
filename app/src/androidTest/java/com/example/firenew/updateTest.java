package com.example.firenew;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.google.firebase.database.DatabaseReference;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class updateTest {
    @Rule
    public ActivityScenarioRule<update> activityScenarioRule = new ActivityScenarioRule<>(update.class);
    public ActivityScenario scenario;
    @Before
    public void setUp() throws Exception {
        scenario =activityScenarioRule.getScenario();

    }

    @Test
    public void onClick() {
        onView(withId(R.id.beky)).perform(click());
//        scenario.getState();
        assertEquals("DESTROYED", String.valueOf(scenario.getState()));
//        scenario.onActivity(activity ->
//                assertEquals(true,
//                activity.isDestroyed()));

    }
    @Test
    public void onClick2(){
        onView(withId(R.id.updet)).perform(click());
//        scenario.getState();
        assertEquals("DESTROYED", String.valueOf(scenario.getState()));
//        scenario.onActivity(activity ->
//                assertEquals(true,
//                activity.isDestroyed()));
    }
}