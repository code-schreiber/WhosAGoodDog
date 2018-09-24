package com.toolslab.gooddog.di

import com.toolslab.gooddog.view.home.HomeFragment
import com.toolslab.gooddog.view.login.LoginActivity
import com.toolslab.gooddog.view.mydog.MyDogFragment
import com.toolslab.gooddog.view.nav.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBindingModule {

    @ContributesAndroidInjector
    abstract fun loginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun myDogFragment(): MyDogFragment

}
