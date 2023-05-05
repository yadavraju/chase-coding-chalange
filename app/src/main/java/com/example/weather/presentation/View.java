package com.example.weather.presentation;

import androidx.annotation.NonNull;

public abstract class View {
    abstract int getID();

    abstract int getChildCount();

    @NonNull
    abstract View getViewAt(int viewAt);

    public View findViewById(int id) {
        for (int i = 0; i < getChildCount(); i++) {
            View currentView = getViewAt(i);
            if (currentView.getID() == id) {
                return currentView;
            }
            int childCount = currentView.getChildCount();
            if (childCount < 1) {
                return null;
            }
            currentView.findViewById(id);
        }
        return null;
    }
}
