package com.abapp.my_work_for_innofied.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.abapp.my_work_for_innofied.di.factory.ViewModelFactory;
import com.abapp.my_work_for_innofied.viewmodels.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by Amol Nage 19,April,2019
 * Xtensible Software Technologies Pvt. Ltd.,
 * Pune, India.
 */

@Module
public abstract class ViewModelModule {

    @Binds
    @SuppressWarnings("unused")
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);


    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsHomeViewModel(MainViewModel mainViewModel);



}
