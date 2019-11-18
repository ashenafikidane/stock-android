package com.example.stock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.stock.home.HomeActivity;
import com.example.stock.welcome.WelcomeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        navToWelcomeActivity();
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



    private void navToWelcomeActivity() {

        //start the Login page
        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
        finishAffinity();
        startActivity(intent);

    }


    private void navToHomeActivity() {

        //start home page
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        finishAffinity();
        startActivity(intent);

    }

}
