package com.poqstudio.app.client

import com.poqstudio.app.client.di.module.network.networkModule
import com.poqstudio.app.platform.PoqApplication
import com.poqstudio.app.platform.PoqApplicationComponent
import com.poqstudio.app.platform.dagger.component.ActivityBuilder
import com.poqstudio.app.platform.injection.annotations.PoqComponent
import org.koin.core.context.loadKoinModules
import com.poqstudio.app.platform.dagger.module.data.analytics.FirebaseAnalyticsModule
import com.poqstudio.app.platform.data.analytics.EventsSource
import com.poqstudio.app.platform.data.analytics.firebase.FirebaseTrackingProvider
import javax.inject.Inject

@PoqComponent(
    extraModules = [
        ActivityBuilder::class,
        FirebaseAnalyticsModule::class
    ]
) class MyFirstPoqAppApplication : PoqApplication() {
    
    private lateinit var component: MyFirstPoqAppApplicationComponent
    
    @Inject
    lateinit var eventsSource: EventsSource

    @Inject
    lateinit var firebaseTrackingProvider: FirebaseTrackingProvider

    override fun createAppComponent(): PoqApplicationComponent {

        loadKoinModules(
            listOf(
                networkModule
            )
        )

        component = DaggerMyFirstPoqAppApplicationComponent.builder()
            .application(this)
            .build()
        return component
    }
    
    override fun inject() {
        component.inject(this)
    }
    
    override fun initAnalytics() {
        super.initAnalytics()
        firebaseTrackingProvider.subscribe(eventsSource)
    }

}
