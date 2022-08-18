package com.denztri.postdudeclient.ui.query;

public class QueryModel {
    private String value;
    private String key;
    private boolean isSelected;

    public QueryModel(String value, String key, boolean isSelected) {
        this.value = value;
        this.key = key;
        this.isSelected = isSelected;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
