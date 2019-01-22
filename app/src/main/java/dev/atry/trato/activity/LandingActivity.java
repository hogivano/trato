package dev.atry.trato.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import dev.atry.trato.R;
import dev.atry.trato.util.ChangeFont;

public class LandingActivity extends AppCompatActivity {

    TextView judul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        judul = (TextView) findViewById(R.id.judul);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LandingActivity.this,LoginActivity.class));
                finish();
            }
        },2000);
    }

    @Override
    protected void onStart() {
        super.onStart();
        judul.setTypeface(new ChangeFont(this).AleoBold());
    }
}
