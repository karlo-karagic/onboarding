package com.poqstudio.app.client

import com.poqstudio.app.client.dagger.DaggerMyFirstPoqAppApplicationComponent
import com.poqstudio.app.client.dagger.MyFirstPoqAppApplicationComponent
import com.poqstudio.app.client.di.module.network.networkModule
import com.poqstudio.app.platform.PoqApplication
import org.koin.core.KoinComponent
import org.koin.core.context.loadKoinModules


class MyFirstPoqAppApplication : PoqApplication(), KoinComponent {

    private lateinit var myFirstPoqAppApplicationComponent: MyFirstPoqAppApplicationComponent

    override fun createAppComponent(): MyFirstPoqAppApplicationComponent {
        loadKoinModules(listOf(networkModule))

        return DaggerMyFirstPoqAppApplicationComponent
                .builder()
                .application(this)
                .build()
    }

    override fun inject() {
        myFirstPoqAppApplicationComponent = createAppComponent()
        myFirstPoqAppApplicationComponent.inject(this)
    }
}