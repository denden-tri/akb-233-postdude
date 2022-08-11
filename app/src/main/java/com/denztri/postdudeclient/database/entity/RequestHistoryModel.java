package com.denztri.postdudeclient.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RequestHistoryModel {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "url")
    public String url;

    @ColumnInfo(name = "http_method")
    public String httpMethod;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public RequestHistoryModel(String url, String httpMethod) {
        this.url = url;
        this.httpMethod = httpMethod;
    }
}
