package com.sksanwar.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sksanwar.app.displayjokeslibrary.DisplayJokesActivity;

public class MainActivityFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main_fragment, container, false);

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
                Intent intent = new Intent(getContext(), DisplayJokesActivity.class);
                intent.putExtra(DisplayJokesActivity.JOKE_EXTRA, joke);
                startActivity(intent);
            }
        }).execute();
    }
}
