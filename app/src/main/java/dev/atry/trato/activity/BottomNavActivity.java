package dev.atry.trato.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import dev.atry.trato.R;
import dev.atry.trato.fragment.BottomFragment;
import dev.atry.trato.fragment.DiscoverFragment;
import dev.atry.trato.fragment.ProfileFragment;

public class BottomNavActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    DiscoverFragment discoverFragment = new DiscoverFragment();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout, discoverFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_dashboard:
                    ProfileFragment profileFragment = new ProfileFragment();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout, profileFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_notifications:
                    BottomFragment bottomFragment = new BottomFragment();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout, bottomFragment);
                    fragmentTransaction.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        DiscoverFragment discoverFragment = new DiscoverFragment();
        fragmentTransaction.add(R.id.frameLayout, discoverFragment, DiscoverFragment.class.getSimpleName());
        fragmentTransaction.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
