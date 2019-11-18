package com.example.stock.welcome;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.stock.R;
import com.example.stock.networking.NetworkImplementations;
import com.example.stock.utils.StockUtils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginFragment extends Fragment {


    //set views
    @BindView(R.id.email_text_input) TextInputLayout emailTextInput;
    @BindView(R.id.password_text_input) TextInputLayout passwordTextInput;
    @BindView(R.id.tv_forgot_password) TextView forgotPassword;


    @BindView(R.id.login_button) MaterialButton loginButton;
    @BindView(R.id.progress_circular) ProgressBar progressBar;
    @BindView(R.id.app_bar) Toolbar toolbar;


    private Context mContext;
    private NetworkImplementations mNetworkImplementations;
    private FragmentManager fm;



    private LoginFragmentListener mListener;
    private LoginFragment() {}
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        fm = getFragmentManager();
        mContext = getContext();
        mNetworkImplementations = new NetworkImplementations(mContext);
        StockUtils.setUpToolbar(toolbar, getActivity());


        loginButton.setOnClickListener(v -> onLoginButtonClicked());


        return view;
    }


    private void onLoginButtonClicked(){

        mListener.onLogin();

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LoginFragmentListener) {
            mListener = (LoginFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement LoginFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface LoginFragmentListener {

        void onLogin();
    }
}
