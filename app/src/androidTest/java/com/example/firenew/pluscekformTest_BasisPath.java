package com.example.firenew;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class pluscekformTest_BasisPath {
    @Rule
    public ActivityScenarioRule<plus> activityScenarioRule = new ActivityScenarioRule<>(plus.class);
    public ActivityScenario<plus> scenario;

    @Before
    public void setUp() throws Exception {
        scenario = activityScenarioRule.getScenario();
    }

    @Test
    public void cekform_zeroTest() {
        scenario.onActivity(activity -> assertEquals(false,((plus) activity).cekform()));
    }

    @Test
    public void cekform_judulOnlyTest() {
        onView(withId(R.id.judul)).perform(typeText("a"));

        scenario.onActivity(activity -> assertEquals(false,((plus) activity).cekform()));
    }

    @Test
    public void cekform_descOnlyTest() {
        onView(withId(R.id.desc)).perform(typeText("a"));

        scenario.onActivity(activity -> assertEquals(false,((plus) activity).cekform()));
    }
}
