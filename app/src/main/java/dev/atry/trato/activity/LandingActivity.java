package dev.atry.trato.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import dev.atry.trato.R;
import dev.atry.trato.util.ChangeFont;

public class LandingActivity extends AppCompatActivity {

    TextView judul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        judul = (TextView) findViewById(R.id.judul);
    }

    @Override
    protected void onStart() {
        super.onStart();
        judul.setTypeface(new ChangeFont(this).AleoBold());
    }
}
