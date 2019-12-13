package com.haybankz.dycestest.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.haybankz.dycestest.R;
import com.haybankz.dycestest.Utils;
import com.haybankz.dycestest.model.User;
import com.haybankz.dycestest.viewmodels.WalletViewModel;

public class WalletFragment extends Fragment {

    private WalletViewModel mViewModel;


    private TextView walletBalanceTextView;
    private EditText amountEditText;
    private Button fundWalletButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.wallet_fragment, container, false);

        walletBalanceTextView = v.findViewById(R.id.balance_textView);
        amountEditText = v.findViewById(R.id.amount_editText);
        fundWalletButton = v.findViewById(R.id.fund_wallet_btn);
        fundWalletButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amt = amountEditText.getText().toString();

                if(!TextUtils.isEmpty(amt)){
                    int balance = Integer.parseInt(amt);
                    if(balance < 100){

                        StringBuilder sb = new StringBuilder(getString(R.string.naira));
                        sb.append("100 is the minimum fund amount.");
                        amountEditText.setError(sb.toString());
                    }else{
                        mViewModel.fundWallet(requireContext(), Integer.parseInt(amountEditText.getText().toString()) );
                        amountEditText.setText("");
                        Toast.makeText(requireContext(), "Account funded successfully", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(WalletViewModel.class);
        // TODO: Use the ViewModel

        User user = (User) getArguments().getSerializable("user");

        mViewModel.setUser(user);


        observeLiveData();
    }

    private void observeLiveData(){

        mViewModel.getUserMutableLiveData().observe(requireActivity(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user != null){
                    StringBuilder sb = new StringBuilder(getString(R.string.naira));
                    sb.append(" ");
                    sb.append(Utils.formatAmount(user.getWalletBalance(), true));

                    walletBalanceTextView.setText(sb.toString());
                }
            }
        });
    }

}
