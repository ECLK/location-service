package lk.eclk.locationservice

import android.app.Application
import lk.eclk.locationservice.data.Repository
import lk.eclk.locationservice.data.RepositoryImpl
import lk.eclk.locationservice.data.db.AppDatabase
import lk.eclk.locationservice.data.remote.ConnectivityInterceptor
import lk.eclk.locationservice.data.remote.ConnectivityInterceptorImpl
import lk.eclk.locationservice.data.remote.api.LocationServiceApiService
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton


class LocationServiceApplication : Application(), KodeinAware {


    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@LocationServiceApplication))

        // Dependency injection with kodein

        //database
        bind() from singleton { AppDatabase(instance()) }

        bind<ConnectivityInterceptor>() with singleton {
            ConnectivityInterceptorImpl(
                instance()
            )
        }

        // api services
        bind() from singleton { LocationServiceApiService(instance()) }

        //Repository
        bind<Repository>() with singleton { RepositoryImpl() }

    }
}