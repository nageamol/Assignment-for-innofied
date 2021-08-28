package com.abapp.my_work_for_innofied.viewmodels;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.abapp.my_work_for_innofied.base.BaseListener;
import com.abapp.my_work_for_innofied.model.RecylerModel;
import com.abapp.my_work_for_innofied.network.Resource;
import com.abapp.my_work_for_innofied.network.repository.AppRepository;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {
    public ViewModelListener viewModelListener;
    AppRepository appRepository;

    @Inject
    MainViewModel(AppRepository appRepository) {
        this.appRepository = appRepository;

    }


    public void onClick(View view) {
        viewModelListener.onClick(view);
    }

    public LiveData<Resource<RecylerModel>> observeDataFromVM(int page, int pageSize) {
        return appRepository.getData(page, pageSize);
    }

    public interface ViewModelListener extends BaseListener {

    }
}