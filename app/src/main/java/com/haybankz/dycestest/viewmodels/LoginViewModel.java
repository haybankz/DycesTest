package com.haybankz.dycestest.viewmodels;

import android.content.Context;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.haybankz.dycestest.data.UserRepository;
import com.haybankz.dycestest.model.User;

public class LoginViewModel extends ViewModel {

    UserRepository userRepository;
    // TODO: Implement the ViewModel
    private MutableLiveData<User> signedInLiveData = new MutableLiveData<>();


    public MutableLiveData<User> getSignInLiveData() {
        return signedInLiveData;
    }


    public void signIn(Context context, String username, String password){

        userRepository = new UserRepository(context);
        User user = userRepository.signIn(username, password);

        signedInLiveData.setValue(user);

    }


}
