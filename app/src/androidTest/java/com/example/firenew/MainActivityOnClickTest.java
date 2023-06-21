package com.example.firenew;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MainActivityOnClickTest {
    @Rule
    public ActivityScenarioRule<login> activityScenarioRule = new ActivityScenarioRule<>(login.class);

    @Before
    public void setUp() {
        try {
            onView(withId(R.id.Email)).perform(typeText("lopon@gmail.com"));
            pressBack();
            onView(withId(R.id.password)).perform(typeText("palos22"));
            pressBack();
            onView(withId(R.id.Login)).perform(click());
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void tambahButtonTest() {
        onView(withId(R.id.plus)).perform(click());
        onView(withId(R.id.GAmbar)).check(matches(isDisplayed()));
    }

    @Test
    public void logoutButtonTest() {
        onView(withId(R.id.logout)).perform(click());
        onView(withId(R.id.Login)).check(matches(isDisplayed()));
    }

    @Test
    public void otherViewTest() {
        onView(withId(R.id.textView2)).perform(click());
        onView(withId(R.id.recy)).check(matches(isDisplayed()));
    }
}