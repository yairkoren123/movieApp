package com.example.drawer_try.ui.Setting;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingViewModel extends ViewModel {

    //setting

    private MutableLiveData<String> mText;

    public SettingViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("This is setting fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}