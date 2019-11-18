package com.example.stock.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.stock.R;
import com.example.stock.adapters.AgentListAdapter;
import com.example.stock.adapters.ItemListAdapter;
import com.example.stock.models.Items;
import com.example.stock.models.User;
import com.example.stock.networking.NetworkImplementations;
import com.example.stock.utils.StockUtils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ItemsFragment extends Fragment implements
        NavigationView.OnNavigationItemSelectedListener{


    @BindView(R.id.app_bar) Toolbar toolbar;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.rec_accounts) RecyclerView recyclerView;
    @BindView(R.id.drawer_layout) public DrawerLayout drawer;
    @BindView(R.id.sale_button) MaterialButton saleButton;


    private Context mContext;
    private NetworkImplementations mNetworkImplementations;


    private ItemListAdapter mAdapter;
    private List<Items> itemsList = new ArrayList<>();
    private TextView navUsername;


    private View view;


    private ItemsFragmentListener mListener;
    public ItemsFragment() {}
    public static ItemsFragment newInstance() {
        ItemsFragment fragment = new ItemsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_items, container, false);
        ButterKnife.bind(this, view);


        StockUtils.setUpToolbar(toolbar, getActivity());
        mContext = getContext();
        mNetworkImplementations = new NetworkImplementations(getContext());


        //enable options menu
        setHasOptionsMenu(true);


        mAdapter = new ItemListAdapter(itemsList, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);


//        recyclerView.addOnItemTouchListener(new ItemTouchListener(getContext(), recyclerView, new ItemTouchListener.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                Account account = accountList.get(position);
//                mListener.onAccountSelected(account);
//            }
//
//            @Override
//            public void onLongClick(View view, final int position) {
//
//            }
//        }));



        saleButton.setOnClickListener(v -> onSale());


        ItemsData();
        setUpDrawer(view);


        return view;
    }


    private void ItemsData(){

        for (int i = 1; i < 6; i++){
            itemsList.add(new Items("Item "+ i, 50 + i));
        }

    }


    private void setUpDrawer(View view) {

        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


//        expandableList = (ExpandableListView) view.findViewById(R.id.navigationmenu);
        NavigationView navigationView = view.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        navUsername = (TextView) headerView.findViewById(R.id.nav_name);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle beezcovery_navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_agents) {

            mListener.showAgents();

        }

//        if (id == R.id.nav_logout) {
//            logOut();
//        }

        DrawerLayout drawer = view.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void onSale(){

        //Todo: sales logic

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ItemsFragmentListener) {
            mListener = (ItemsFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ItemsFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface ItemsFragmentListener {

        void showAgents();
    }
}
