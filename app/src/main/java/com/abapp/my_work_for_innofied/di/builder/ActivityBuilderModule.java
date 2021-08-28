package com.abapp.my_work_for_innofied.di.builder;

import com.abapp.my_work_for_innofied.view.activity.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Amol Nage 19,April,2019
 * Xtensible Software Technologies Pvt. Ltd.,
 * Pune, India.
 */

@Module
public abstract class ActivityBuilderModule {

    @SuppressWarnings("unused")
    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract MainActivity mainActivity();



}
