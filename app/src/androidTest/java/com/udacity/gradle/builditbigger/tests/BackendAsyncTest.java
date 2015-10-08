package com.udacity.gradle.builditbigger.tests;

import android.content.Context;
import android.support.v4.util.Pair;
import android.test.AndroidTestCase;

import com.example.Jokes;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

import java.util.concurrent.TimeUnit;

/**
 * Created by andy on 10/7/15.
 */
public class BackendAsyncTest extends AndroidTestCase {

    public void testAsyncJoke() {
        EndpointsAsyncTask jokeTask = new EndpointsAsyncTask();
        Jokes jokes = new Jokes();

        try {
            jokeTask.execute(new Pair<Context, String>(getContext(), jokes.tellJoke()));

            String joke = jokeTask.get(30, TimeUnit.SECONDS);

            assertNotNull(joke);
            assertNotSame(joke, "");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
