package com.example.myapplication.Fragments;

import androidx.lifecycle.ViewModel;

public class DetailsViewModel extends ViewModel {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
