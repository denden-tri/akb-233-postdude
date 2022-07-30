package com.denztri.postdudeclient.ui.response;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.List;

public class ResponseViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private String responseBody;
    private String responseHeaders;
    private String responseCookie;
    private final MutableLiveData<List<String>> data;
    private MutableLiveData<String> headers;

    public ResponseViewModel() {
        data = new MutableLiveData<>();
        headers = new MutableLiveData<>();
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody, String responseHeaders, String responseCookie) {
        data.setValue(Arrays.asList(responseBody,responseHeaders,responseCookie));
        this.responseBody = responseBody;
    }

    public void setHeaders(String headers){
        this.headers.setValue(headers);
        this.responseBody = headers;
    }

    public LiveData<List<String>> getData(){
        return data;
    }


    public MutableLiveData<String> getHeaders() {
        return headers;
    }

    public void setHeaders(MutableLiveData<String> headers) {
        this.headers = headers;
    }

    public String getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(String responseHeaders) {
        this.responseHeaders = responseHeaders;
    }
}