package com.example.myapplication;

public class RetrofitResponseObject {
    private Object object;

    private Integer requestCode;

    private Object secondaryObject;

    public RetrofitResponseObject(Object object, Integer requestCode) {
        this.object = object;
        this.requestCode = requestCode;
    }

    public RetrofitResponseObject(Object object, Integer requestCode, Object secondaryObject) {
        this.object = object;
        this.requestCode = requestCode;
        this.secondaryObject = secondaryObject;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Integer getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(Integer requestCode) {
        this.requestCode = requestCode;
    }

    public Object getSecondaryObject() {
        return secondaryObject;
    }

    public void setSecondaryObject(Object secondaryObject) {
        this.secondaryObject = secondaryObject;
    }
}
