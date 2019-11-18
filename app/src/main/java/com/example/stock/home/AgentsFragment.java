package com.example.stock.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.stock.R;
import com.example.stock.adapters.AgentListAdapter;
import com.example.stock.models.User;
import com.example.stock.networking.NetworkImplementations;
import com.example.stock.utils.StockUtils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AgentsFragment extends Fragment {


    @BindView(R.id.app_bar) Toolbar toolbar;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.rec_accounts) RecyclerView recyclerView;
    @BindView(R.id.agents_fab) FloatingActionButton addAgent;


    private Context mContext;
    private NetworkImplementations mNetworkImplementations;


    private AgentListAdapter mAdapter;
    private List<User> agentList = new ArrayList<>();


    private View view;
    private AgentsFragmentListener mListener;
    private AgentsFragment() {}
    public static AgentsFragment newInstance() {
        AgentsFragment fragment = new AgentsFragment();
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
        view = inflater.inflate(R.layout.fragment_agent_list, container, false);
        ButterKnife.bind(this, view);


        StockUtils.setUpToolbar(toolbar, getActivity());
        mContext = getContext();
        mNetworkImplementations = new NetworkImplementations(getContext());


        //enable options menu
        setHasOptionsMenu(true);


        mAdapter = new AgentListAdapter(agentList, getContext());
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



        addAgent.setOnClickListener(v -> onNewAgent());


        AgentsData();
        return view;
    }


//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.home_menu, menu);
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//
//            case R.id.items:
//                mListener.onItems();
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }



    private void AgentsData(){

        for (int i=1  ; i < 5; i++){

            agentList.add(new User("Agent "+i));

        }

        mAdapter.notifyDataSetChanged();

    }


    private void onNewAgent(){

        MaterialAlertDialogBuilder selectorDialog = new MaterialAlertDialogBuilder(mContext);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.new_agent_layout, null);
        selectorDialog.setView(dialogView);


        TextInputLayout name = (TextInputLayout) dialogView.findViewById(R.id.nameInputField);
        MaterialButton createAgent = (MaterialButton) dialogView.findViewById(R.id.create_agent);



        AlertDialog dialog = selectorDialog.create();
        createAgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getEditText().getText().toString() != ""){
                    agentList.add(new User(name.getEditText().getText().toString()));
                    dialog.dismiss();
                }
                else {
                    name.setError("please add Name");
                }

            }
        });
        dialog.show();

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AgentsFragmentListener) {
            mListener = (AgentsFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement AgentsFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface AgentsFragmentListener {

        void onCreateAgent();

    }
}
