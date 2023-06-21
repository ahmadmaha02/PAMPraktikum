package com.example.firenew;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.os.Handler;
import android.os.Looper;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.google.errorprone.annotations.DoNotMock;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


public class plusTest {
    @Rule
    public ActivityScenarioRule<plus> activityScenarioRule = new ActivityScenarioRule<>(plus.class);
    public ActivityScenario scenario;
   @Before
   public void setUp(){
       scenario =activityScenarioRule.getScenario();
   }


    @Test
    public void cekformZeroInput() {
       scenario.onActivity(activity -> assertEquals(false,
               ((plus)activity).cekform()));
    }
    @Test
    public void cekformOneInput() {
       onView(withId(R.id.judul)).perform(typeText("a"));
        scenario.onActivity(activity -> assertEquals(false,
                ((plus)activity).cekform()));
    }
    @Test
    public void cekformTwoInput() {
        onView(withId(R.id.judul)).perform(typeText("a"));
        onView(withId(R.id.desc)).perform(typeText("a"));
        scenario.onActivity(activity -> assertEquals(true,
                ((plus)activity).cekform()));
    }
}
