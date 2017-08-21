package com.sksanwar.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sksanwar.app.displayjokeslibrary.DisplayJokesActivity;

public class MainActivityFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main_fragment, container, false);

        AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        Button button = (Button) rootView.findViewById(R.id.show_joke_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retriveJokes();
            }
        });
        return rootView;
    }

    private void retriveJokes(){
        new FetchJokes(new Listner() {
            @Override
            public void onJokeLoaded(String joke) {
                Intent intent = new Intent(getActivity(), DisplayJokesActivity.class);
                intent.putExtra(DisplayJokesActivity.JOKE_EXTRA, joke);
                startActivity(intent);
            }
        }).execute();
    }
}
