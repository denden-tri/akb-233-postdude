package com.denztri.postdudeclient.ui.query;

import androidx.lifecycle.ViewModel;

import java.util.List;

public class QueryViewModel extends ViewModel {
    private List<QueryModel> list;

    public QueryViewModel() {
    }

    public List<QueryModel> getList() {
        return list;
    }

    public void setList(List<QueryModel> list) {
        this.list = list;
    }
}