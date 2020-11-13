package com.poqstudio.app.client.dagger

import android.app.Application
import com.poqstudio.app.client.MyFirstPoqAppApplication
import com.poqstudio.app.platform.dagger.component.ActivityBuilder
import com.poqstudio.app.platform.dagger.component.PoqApplicationComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityBuilder::class
])
interface MyFirstPoqAppApplicationComponent : PoqApplicationComponent {
    fun inject(application: MyFirstPoqAppApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): MyFirstPoqAppApplicationComponent
    }
}