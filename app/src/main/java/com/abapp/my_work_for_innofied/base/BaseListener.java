package com.abapp.my_work_for_innofied.base;

import android.view.View;

/**
 * Created by Amol Nage 23,August,2019
 * Xtensible Software Technologies Pvt. Ltd.,
 * Pune, India.
 */
public interface BaseListener {

    void onStarted();
    void onClick(View view);
    void onComplete();
    void showToast(String msg);
    void showSnackBar(String msg);
}
