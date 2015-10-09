package com.udacity.gradle.builditbigger.tests;

import android.content.Context;
import android.support.v4.util.Pair;
import android.test.AndroidTestCase;

import com.example.Jokes;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.IEndpointsListener;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by andy on 10/7/15.
 */
public class BackendAsyncTest extends AndroidTestCase implements IEndpointsListener {

    CountDownLatch signal;

    public void testAsyncJoke() {
        EndpointsAsyncTask jokeTask = new EndpointsAsyncTask(this);
        Jokes jokes = new Jokes();
        this.signal = new CountDownLatch(1);

        try {
            jokeTask.execute(new Pair<Context, String>(getContext(), jokes.tellJoke()));

            signal.await(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Override
    public void onJokeTold(String joke) {
        assertNotNull(joke);
        assertNotSame(joke, "");
        signal.countDown();
    }
}
