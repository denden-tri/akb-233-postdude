package com.denztri.postdudeclient.ui.response;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ResponseViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private String responseBody;
    private MutableLiveData<String> data;

    public ResponseViewModel() {
        data = new MutableLiveData<>();
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        data.setValue(responseBody);
        this.responseBody = responseBody;
    }

    public LiveData<String> getData(){
        return data;
    }
}