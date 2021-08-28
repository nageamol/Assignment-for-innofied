package com.abapp.my_work_for_innofied.di.builder;


import com.abapp.my_work_for_innofied.view.fragments.MainFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Amol Nage 19,April,2019
 * Xtensible Software Technologies Pvt. Ltd.,
 * Pune, India.
 */

@Module
public abstract class FragmentBuilderModule {

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract MainFragment contributeHomeFragment();

}
