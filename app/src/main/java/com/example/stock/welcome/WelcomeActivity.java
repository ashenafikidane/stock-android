package com.example.stock.welcome;

import com.example.stock.MainActivity;
import com.example.stock.R;
import com.example.stock.home.HomeActivity;
import com.example.stock.networking.NetworkImplementations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class WelcomeActivity extends AppCompatActivity implements
        LoginFragment.LoginFragmentListener {


    private FragmentManager fm = getSupportFragmentManager();
    private Context mContext;
    private NetworkImplementations mNetworkImplementations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        mContext = getApplicationContext();
        mNetworkImplementations = new NetworkImplementations(mContext);

        // Set welcome fragment
        fm.beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .add(R.id.container, LoginFragment.newInstance())
                .commit();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                } else {
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }



    private void navToHomeActivity() {

        //start home page
        Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
        finishAffinity();
        startActivity(intent);

    }

    @Override
    public void onLogin() {

        navToHomeActivity();

    }
}
