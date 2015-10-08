package com.example.andy.jokesui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokesUiActivity extends AppCompatActivity {

    public static String JOKE_KEY = "JOKE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes_ui);

        TextView tvJoke = (TextView) findViewById(R.id.joke_text_view);
        String joke = getIntent().getStringExtra(JOKE_KEY);

        if (joke != null) {
            tvJoke.setText(joke);
        } else {
            tvJoke.setText(R.string.no_joke_found);
        }
    }
}
