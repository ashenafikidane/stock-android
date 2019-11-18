package com.example.stock.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.stock.R;
import com.example.stock.adapters.AgentListAdapter;
import com.example.stock.networking.NetworkImplementations;
import com.example.stock.welcome.LoginFragment;

public class HomeActivity extends AppCompatActivity implements
        AgentsFragment.AgentsFragmentListener,
        ItemsFragment.ItemsFragmentListener,
        TransactionFragment.TransactionFragmentListener {



    private FragmentManager fm = getSupportFragmentManager();
    private Context mContext;
    private NetworkImplementations mNetworkImplementations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mContext = getApplicationContext();
        mNetworkImplementations = new NetworkImplementations(mContext);

        // Set welcome fragment
        fm.beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .add(R.id.container, ItemsFragment.newInstance())
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


    @Override
    public void onCreateAgent() {

    }

    @Override
    public void showAgents() {

        fm.beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .add(R.id.container, AgentsFragment.newInstance())
                .addToBackStack("ShowAgents")
                .commit();

    }
}
