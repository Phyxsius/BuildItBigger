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
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class FreeMainActivityFragment extends Fragment implements IEndpointsListener {

    private Button mTellJoke;
    private InterstitialAd mInterstitialAd;

    public FreeMainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mTellJoke = (Button) root.findViewById(R.id.tell_joke_button);
        AdView mAdView = (AdView) root.findViewById(R.id.adView);


        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                tellJoke();
            }
        });

        requestNewInterstitial();

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mAdView.loadAd(adRequest);


        mTellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    tellJoke();
                }
            }
        });

        return root;
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
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
