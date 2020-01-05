package com.example.strawpoll

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.strawpoll.ui.list.PollAdapter
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkCorrectNavigationToAboutFragment() {
        Espresso.onView(ViewMatchers.withId(R.id.drawerLayout)).perform(DrawerActions.open())
        Espresso.onView(ViewMatchers.withText("About")).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.strawpollLogo)).check(matches(isDisplayed()))
    }

    @Test
    fun checkCorrectNavigationToAboutFragmentAndBack() {
        Espresso.onView(ViewMatchers.withId(R.id.drawerLayout)).perform(DrawerActions.open())
        Espresso.onView(ViewMatchers.withText("About")).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.strawpollLogo)).check(matches(isDisplayed()))
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.poll_list)).check(matches(isDisplayed()))
    }

    @Test
    fun checkCorrectNavigationToCreateFragment() {
        Espresso.onView(ViewMatchers.withId(R.id.drawerLayout)).perform(DrawerActions.open())
        Espresso.onView(ViewMatchers.withText("Create a poll")).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.pollQuestion)).check(matches(isDisplayed()))
    }

    @Test
    fun checkCorrectNavigationToCreateFragmentAndBack() {
        Espresso.onView(ViewMatchers.withId(R.id.drawerLayout)).perform(DrawerActions.open())
        Espresso.onView(ViewMatchers.withText("Create a poll")).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.pollQuestion)).check(matches(isDisplayed()))
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.poll_list)).check(matches(isDisplayed()))
    }

    @Test
    fun checkCorrectNavigationToVoteFragment() {
        Espresso.onView(ViewMatchers.withId(R.id.poll_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<PollAdapter.ViewHolder>(
                2,
                click()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.voteButton)).check(matches(isDisplayed()))
    }

    @Test
    fun checkCorrectNavigationToVoteFragmentAndBack() {
        Espresso.onView(ViewMatchers.withId(R.id.poll_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<PollAdapter.ViewHolder>(
                2,
                click()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.voteButton)).check(matches(isDisplayed()))
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.poll_list)).check(matches(isDisplayed()))
    }

    @Test
    fun checkCorrectNavigationOnVoteAndBack() {
        Espresso.onView(ViewMatchers.withId(R.id.poll_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<PollAdapter.ViewHolder>(
                2,
                click()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.voteButton)).check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withText("Madrid")).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.voteButton)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.answerList)).check(matches(isDisplayed()))
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.poll_list)).check(matches(isDisplayed()))
    }

    @Test
    fun checkCorrectNavigationOnAlreadyVoted() {
        Espresso.onView(ViewMatchers.withId(R.id.poll_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<PollAdapter.ViewHolder>(
                2,
                click()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.answerList)).check(matches(isDisplayed()))
    }

    @Test
    fun checkCorrectNavigationOnPollCreation() {
        Espresso.onView(ViewMatchers.withId(R.id.drawerLayout)).perform(DrawerActions.open())
        Espresso.onView(ViewMatchers.withText("Create a poll")).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.pollQuestion))
            .perform(typeText("This is a question"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.editText))
            .perform(typeText("This is an answer"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.editText2))
            .perform(typeText("This is alsa an answer"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.createButton)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.voteButton)).check(matches(isDisplayed()))
    }

    @Test
    fun checkIncorrectPollCreationNoQuestion() {
        Espresso.onView(ViewMatchers.withId(R.id.drawerLayout)).perform(DrawerActions.open())
        Espresso.onView(ViewMatchers.withText("Create a poll")).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.editText))
            .perform(typeText("This is an answer"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.editText2))
            .perform(typeText("This is alsa an answer"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.createButton)).perform(click())
        Espresso.onView(ViewMatchers.withText("You will need to add a question first."))
            .inRoot(withDecorView(not(activityRule.activity.window.decorView))).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun checkIncorrectPollCreation1Answer() {
        Espresso.onView(ViewMatchers.withId(R.id.drawerLayout)).perform(DrawerActions.open())
        Espresso.onView(ViewMatchers.withText("Create a poll")).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.pollQuestion))
            .perform(typeText("This is a question"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.editText2))
            .perform(typeText("This is alsa an answer"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.createButton)).perform(click())
        Espresso.onView(ViewMatchers.withText("You will need to add 2 none empty answers first."))
            .inRoot(withDecorView(not(activityRule.activity.window.decorView))).check(
                matches(isDisplayed())
            )
    }
}