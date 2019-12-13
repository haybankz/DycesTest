package com.haybankz.dycestest.viewmodels;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.haybankz.dycestest.data.UserRepository;
import com.haybankz.dycestest.model.User;

public class WalletViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();

    UserRepository userRepository;

    public MutableLiveData<User> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public void setUser(User user){
        userMutableLiveData.setValue(user);

    }

    public void fundWallet(Context context, int amount){
        userRepository = new UserRepository(context);
        User user = userMutableLiveData.getValue();
        int newWalletBalance = user.getWalletBalance() + amount;
        user.setWalletBalance(newWalletBalance);
        userRepository.updateUser(user);
        userMutableLiveData.setValue(user);
    }

}
