package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.Jokes;
import com.example.andy.jokesui.JokesUiActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements IEndpointsListener {

    private Button mTellJoke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mTellJoke = (Button) root.findViewById(R.id.tell_joke_button);

        mTellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJoke();
            }
        });

        return root;
    }

    public void tellJoke() {
        // Show the loading bar
        getView().findViewById(R.id.progressBar).setVisibility(View.VISIBLE);

        Jokes jokes = new Jokes();

        new EndpointsAsyncTask(this).execute(new Pair<Context, String>(getActivity(), jokes.tellJoke()));
    }

    public void tellJokeByAndroidActivity(){
        Intent intent = new Intent(getActivity(), JokesUiActivity.class);

        Jokes jokes = new Jokes();

        intent.putExtra(JokesUiActivity.JOKE_KEY, jokes.tellJoke());

        startActivity(intent);
    }

    @Override
    public void onJokeTold(String joke) {
        // Hide the loading bar
        getView().findViewById(R.id.progressBar).setVisibility(View.GONE);

        Intent intent = new Intent(getActivity(), JokesUiActivity.class);

        Jokes jokes = new Jokes();

        intent.putExtra(JokesUiActivity.JOKE_KEY, jokes.tellJoke());

        startActivity(intent);
    }
}
