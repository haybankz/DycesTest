package com.haybankz.dycestest.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.haybankz.dycestest.model.User;
import com.haybankz.dycestest.viewmodels.LoginViewModel;
import com.haybankz.dycestest.R;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;

    private EditText userNameEditText, passwordEditText;
    private Button signInButton;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.login_fragment, container, false);

        userNameEditText = v.findViewById(R.id.username_editText);
        passwordEditText = v.findViewById(R.id.password_editText);

        signInButton = v.findViewById(R.id.sign_in_btn);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mViewModel.signIn(requireContext(), userNameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });

        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        // TODO: Use the ViewModel


        observeLiveData();
    }

    private void observeLiveData(){

        mViewModel.getSignInLiveData().observe(requireActivity(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user != null){

                    Bundle b = new Bundle();
                    b.putSerializable("user", user);
                    Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_walletFragment, b);


                }else{
                    userNameEditText.setText("");
                    passwordEditText.setText("");
                    Toast.makeText(requireContext(), "Username/password is incorrect", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
