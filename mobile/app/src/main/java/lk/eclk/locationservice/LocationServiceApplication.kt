package lk.eclk.locationservice

import android.app.Application
import lk.eclk.locationservice.data.Repository
import lk.eclk.locationservice.data.RepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton


class LocationServiceApplication : Application(), KodeinAware {


    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@LocationServiceApplication))

        // Dependency injection with kodein

        bind<Repository>() with singleton { RepositoryImpl() }

    }
}